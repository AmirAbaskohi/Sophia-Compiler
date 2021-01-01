package main.visitor.typeChecker;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.classDec.ClassDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.ConstructorDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.FieldDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.MethodDeclaration;
import main.ast.nodes.declaration.variableDec.VarDeclaration;
import main.ast.nodes.expression.operators.BinaryOperator;
import main.ast.nodes.expression.values.NullValue;
import main.ast.nodes.statement.*;
import main.ast.nodes.statement.loop.BreakStmt;
import main.ast.nodes.statement.loop.ContinueStmt;
import main.ast.nodes.statement.loop.ForStmt;
import main.ast.nodes.statement.loop.ForeachStmt;
import main.ast.types.NoType;
import main.ast.types.NullType;
import main.ast.types.Type;
import main.ast.types.functionPointer.FptrType;
import main.ast.types.list.ListType;
import main.ast.types.single.BoolType;
import main.ast.types.single.ClassType;
import main.ast.types.single.IntType;
import main.ast.types.single.StringType;
import main.compileErrorException.CompileErrorException;
import main.compileErrorException.typeErrors.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.ClassSymbolTableItem;
import main.symbolTable.items.MethodSymbolTableItem;
import main.symbolTable.items.SymbolTableItem;
import main.symbolTable.utils.graph.Graph;
import main.visitor.Visitor;

import java.util.ArrayList;
import java.util.Stack;

public class TypeChecker extends Visitor<Void> {
    private final Graph<String> classHierarchy;
    private final ExpressionTypeChecker expressionTypeChecker;

    public boolean isInLoop;
    public Type methodRetType;
    public boolean hasReturnStmt;
    public Stack<Boolean> haveBeenPrinted;
    public Stack<Boolean> haveBeenUnreached;

    static boolean isInMethodCallStmt;

    public TypeChecker(Graph<String> classHierarchy) {
        this.classHierarchy = classHierarchy;
        this.expressionTypeChecker = new ExpressionTypeChecker(classHierarchy);
        this.isInLoop = false;
        this.methodRetType = new NoType();
        this.hasReturnStmt = false;
        isInMethodCallStmt = false;
        haveBeenPrinted = new Stack<>();
        haveBeenUnreached = new Stack<>();
    }

    @Override
    public Void visit(Program program) {
        String key = ClassSymbolTableItem.START_KEY + "Main";
        try{
            SymbolTable.root.getItem(key, true);
        }catch (ItemNotFoundException ex)
        {
            program.addError(new NoMainClass());
        }

        for (ClassDeclaration classDeclaration: program.getClasses()) {
            classDeclaration.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ClassDeclaration classDeclaration) {
        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;

        int line = classDeclaration.getLine();
        String className = classDeclaration.getClassName().getName();
        String parentClassName = "";
        if (classDeclaration.getParentClassName() != null)
            parentClassName = classDeclaration.getParentClassName().getName();
        String key = ClassSymbolTableItem.START_KEY + className;

        if (className.equals("Main")) {
            if (classDeclaration.getParentClassName() != null)
                classDeclaration.addError(new MainClassCantExtend(line));
            if (classDeclaration.getConstructor() == null)
                classDeclaration.addError(new NoConstructorInMainClass(classDeclaration));
        }
        if (parentClassName.equals("Main")) {
            classDeclaration.addError(new CannotExtendFromMainClass(line));
        }

        if (classDeclaration.getParentClassName() != null){
            try{
                String parentKey = ClassSymbolTableItem.START_KEY + parentClassName;
                SymbolTable.root.getItem(parentKey, true);
            } catch (ItemNotFoundException exp){
                classDeclaration.addError(new ClassNotDeclared(line, parentClassName));
            }
        }

        try {
            SymbolTableItem symbolTableItem = SymbolTable.root.getItem(key, true);
            ClassSymbolTableItem classSymbolTableItem = (ClassSymbolTableItem) symbolTableItem;
            SymbolTable.push(classSymbolTableItem.getClassSymbolTable());
            ExpressionTypeChecker.className = classDeclaration.getClassName();
        }
        catch (ItemNotFoundException ex){
            System.out.println("Never happening!");
        }

        for(FieldDeclaration fieldDeclaration : classDeclaration.getFields())
            fieldDeclaration.accept(this);

        for(MethodDeclaration methodDeclaration : classDeclaration.getMethods())
            methodDeclaration.accept(this);

        if(classDeclaration.getConstructor() != null)
            classDeclaration.getConstructor().accept(this);

        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(ConstructorDeclaration constructorDeclaration) {
        haveBeenPrinted.push(false);
        haveBeenUnreached.push(false);
        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;


        String constructorName = constructorDeclaration.getMethodName().getName();

        if (ExpressionTypeChecker.className.getName().equals("Main"))
        {
            if (constructorDeclaration.getArgs().size() != 0)
                constructorDeclaration.addError(new MainConstructorCantHaveArgs(constructorDeclaration.getLine()));
        }

        String key = MethodSymbolTableItem.START_KEY + constructorName;
        try {
            SymbolTableItem symbolTableItem = SymbolTable.top.getItem(key, true);
            MethodSymbolTableItem methodSymbolTableItem = (MethodSymbolTableItem) symbolTableItem;
            SymbolTable.push(methodSymbolTableItem.getMethodSymbolTable());
        }
        catch (ItemNotFoundException ex){
            System.out.println("Never happening!");
        }

        if(!constructorName.equals(ExpressionTypeChecker.className.getName()))
            constructorDeclaration.addError(new ConstructorNotSameNameAsClass(constructorDeclaration.getLine()));

        for(VarDeclaration varDeclaration : constructorDeclaration.getArgs())
            varDeclaration.accept(this);
        for(VarDeclaration varDeclaration : constructorDeclaration.getLocalVars())
            varDeclaration.accept(this);
        for(Statement statement : constructorDeclaration.getBody())
            statement.accept(this);
        SymbolTable.pop();

        haveBeenPrinted.pop();
        haveBeenUnreached.pop();

        return null;
    }

    @Override
    public Void visit(MethodDeclaration methodDeclaration) {

        haveBeenPrinted.push(false);
        haveBeenUnreached.push(false);

        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;

        String key = MethodSymbolTableItem.START_KEY + methodDeclaration.getMethodName().getName();
        try {
            SymbolTableItem symbolTableItem = SymbolTable.top.getItem(key, true);
            MethodSymbolTableItem methodSymbolTableItem = (MethodSymbolTableItem) symbolTableItem;
            SymbolTable.push(methodSymbolTableItem.getMethodSymbolTable());
        }
        catch (ItemNotFoundException ex){
            System.out.println("Never happening!");
        }

        Type returnType = methodDeclaration.getReturnType();
        if(returnType instanceof ListType) {
            ArrayList<CompileErrorException> errors = expressionTypeChecker.checkListDecError((ListType)returnType, methodDeclaration.getLine());
            for(CompileErrorException error : errors) {
                methodDeclaration.addError(error);
            }
        }
        if(returnType instanceof ClassType){
            if(!expressionTypeChecker.isClassDeclared((ClassType)returnType)) {
                methodDeclaration.addError(new ClassNotDeclared(methodDeclaration.getLine(), ((ClassType) returnType).getClassName().getName()));
            }
        }
        if(returnType instanceof FptrType){
            ArrayList<CompileErrorException> errors = expressionTypeChecker.checkFptrDecError((FptrType) returnType, methodDeclaration.getLine());
            for(CompileErrorException error : errors) {
                methodDeclaration.addError(error);
            }
        }

        methodRetType = methodDeclaration.getReturnType();

        for(VarDeclaration varDeclaration : methodDeclaration.getArgs())
            varDeclaration.accept(this);
        for(VarDeclaration varDeclaration : methodDeclaration.getLocalVars())
            varDeclaration.accept(this);
        for(Statement statement : methodDeclaration.getBody())
            statement.accept(this);

        if(!(returnType instanceof NullType) && !hasReturnStmt)
        {
            methodDeclaration.addError(new MissingReturnStatement(methodDeclaration));
            hasReturnStmt = false;
        }
        SymbolTable.pop();

        haveBeenPrinted.pop();
        haveBeenUnreached.pop();

        return null;
    }

    @Override
    public Void visit(FieldDeclaration fieldDeclaration) {
        VarDeclaration varDeclaration = fieldDeclaration.getVarDeclaration();
        varDeclaration.accept(this);
        return null;
    }

    @Override
    public Void visit(VarDeclaration varDeclaration) {
        Type type = varDeclaration.getType();
        if(type instanceof ListType) {
            ArrayList<CompileErrorException> errors = expressionTypeChecker.checkListDecError((ListType)type, varDeclaration.getLine());
            for(CompileErrorException error : errors)
                varDeclaration.addError(error);
        }
        if(type instanceof ClassType){
            if(! expressionTypeChecker.isClassDeclared((ClassType)type))
                varDeclaration.addError(new ClassNotDeclared(varDeclaration.getLine(), ((ClassType)type).getClassName().getName()));
        }
        if(type instanceof FptrType){
            ArrayList<CompileErrorException> errors = expressionTypeChecker.checkFptrDecError((FptrType) type, varDeclaration.getLine());
            for(CompileErrorException error : errors)
                varDeclaration.addError(error);
        }
        return null;
    }

    @Override
    public Void visit(AssignmentStmt assignmentStmt) {
        if(!(haveBeenPrinted.peek()) && haveBeenUnreached.peek()){
            assignmentStmt.addError(new UnreachableStatements(assignmentStmt));
            haveBeenPrinted.pop();
            haveBeenPrinted.push(true);
        }
        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;

        Type rType = assignmentStmt.getrValue().accept(expressionTypeChecker);
        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;
        Type lType = assignmentStmt.getlValue().accept(expressionTypeChecker);

        if(!expressionTypeChecker.checkTypes(rType, lType, true))
            assignmentStmt.addError(new UnsupportedOperandType(assignmentStmt.getLine(), BinaryOperator.assign.name()));

        if(expressionTypeChecker.isLValueViolated || expressionTypeChecker.aloneThis){
            assignmentStmt.addError(new LeftSideNotLvalue(assignmentStmt.getLine()));
        }

        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;

        return null;
    }

    @Override
    public Void visit(BlockStmt blockStmt) {
        if(!(haveBeenPrinted.peek()) && haveBeenUnreached.peek()){
            blockStmt.addError(new UnreachableStatements(blockStmt));
            haveBeenPrinted.pop();
            haveBeenPrinted.push(true);
        }
        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;
        for(Statement statement : blockStmt.getStatements())
            statement.accept(this);
        return null;
    }

    @Override
    public Void visit(ConditionalStmt conditionalStmt) {
        if(!(haveBeenPrinted.peek()) && haveBeenUnreached.peek()){
            conditionalStmt.addError(new UnreachableStatements(conditionalStmt));
            haveBeenPrinted.pop();
            haveBeenPrinted.push(true);
        }

        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;
        Type conditionType = conditionalStmt.getCondition().accept(expressionTypeChecker);

        haveBeenPrinted.push(haveBeenPrinted.peek());
        haveBeenUnreached.push(haveBeenUnreached.peek());

        conditionalStmt.getThenBody().accept(this);

        haveBeenPrinted.pop();
        Boolean hasThenBodyUnreachable = haveBeenUnreached.pop();

        Boolean hasElseBodyUnreachable = false;

        if (conditionalStmt.getElseBody() != null) {
            haveBeenPrinted.push(haveBeenPrinted.peek());
            haveBeenUnreached.push(haveBeenUnreached.peek());

            conditionalStmt.getElseBody().accept(this);

            haveBeenPrinted.pop();
            hasElseBodyUnreachable = haveBeenUnreached.pop();
        }

        if (!(conditionType instanceof BoolType || conditionType instanceof NoType))
            conditionalStmt.addError(new ConditionNotBool(conditionalStmt.getLine()));

        if(hasThenBodyUnreachable && hasElseBodyUnreachable)
        {
            haveBeenUnreached.pop();
            haveBeenUnreached.push(true);
            haveBeenPrinted.pop();
            haveBeenPrinted.push(false);
        }

        return null;
    }

    @Override
    public Void visit(MethodCallStmt methodCallStmt) {
        if(!(haveBeenPrinted.peek()) && haveBeenUnreached.peek()){
            methodCallStmt.addError(new UnreachableStatements(methodCallStmt));
            haveBeenPrinted.pop();
            haveBeenPrinted.push(true);
        }
        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;
        isInMethodCallStmt = true;
        methodCallStmt.getMethodCall().accept(expressionTypeChecker);
        isInMethodCallStmt = false;
        return null;
    }

    @Override
    public Void visit(PrintStmt print) {
        if(!(haveBeenPrinted.peek()) && haveBeenUnreached.peek()){
            print.addError(new UnreachableStatements(print));
            haveBeenPrinted.pop();
            haveBeenPrinted.push(true);
        }
        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;
        Type argType = print.getArg().accept(expressionTypeChecker);

        if(!(argType instanceof IntType) && !(argType instanceof StringType) && !(argType instanceof BoolType) && !(argType instanceof NoType))
            print.addError(new UnsupportedTypeForPrint(print.getLine()));
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        if(!(haveBeenPrinted.peek()) && haveBeenUnreached.peek()){
            returnStmt.addError(new UnreachableStatements(returnStmt));
            haveBeenPrinted.pop();
            haveBeenPrinted.push(true);
        }
        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;
        hasReturnStmt = true;
        haveBeenUnreached.pop();
        haveBeenUnreached.push(true);
        haveBeenPrinted.pop();
        haveBeenPrinted.push(false);
        Type returnType;
        if (returnStmt.getReturnedExpr() != null) {
            returnType = returnStmt.getReturnedExpr().accept(expressionTypeChecker);
        }
        else
            returnType = new NullType();
        if (! expressionTypeChecker.checkTypes(returnType, methodRetType, true))
            returnStmt.addError(new ReturnValueNotMatchMethodReturnType(returnStmt));
        return null;
    }

    @Override
    public Void visit(BreakStmt breakStmt) {
        if(!(haveBeenPrinted.peek()) && haveBeenUnreached.peek()) {
            breakStmt.addError(new UnreachableStatements(breakStmt));
            haveBeenPrinted.pop();
            haveBeenPrinted.push(true);
        }

        if (!isInLoop) {
            breakStmt.addError(new ContinueBreakNotInLoop(breakStmt.getLine(), 0));
        }
        else{
            haveBeenUnreached.pop();
            haveBeenUnreached.push(true);
            haveBeenPrinted.pop();
            haveBeenPrinted.push(false);
        }
        return null;
    }

    @Override
    public Void visit(ContinueStmt continueStmt) {
        if(!(haveBeenPrinted.peek()) && haveBeenUnreached.peek()){
            continueStmt.addError(new UnreachableStatements(continueStmt));
            haveBeenPrinted.pop();
            haveBeenPrinted.push(true);
        }
        if (!isInLoop) {
            continueStmt.addError(new ContinueBreakNotInLoop(continueStmt.getLine(), 1));
        }
        else{
            haveBeenUnreached.pop();
            haveBeenUnreached.push(true);
            haveBeenPrinted.pop();
            haveBeenPrinted.push(false);
        }
        return null;
    }

    @Override
    public Void visit(ForeachStmt foreachStmt) {
        if(!(haveBeenPrinted.peek()) && haveBeenUnreached.peek()){
            foreachStmt.addError(new UnreachableStatements(foreachStmt));
            haveBeenPrinted.pop();
            haveBeenPrinted.push(true);
        }

        haveBeenPrinted.push(haveBeenPrinted.peek());
        haveBeenUnreached.push(haveBeenUnreached.peek());

        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;
        boolean tempIsInLoop = isInLoop;
        isInLoop = true;
        Type identifierType = foreachStmt.getVariable().accept(expressionTypeChecker);
        Type itType = foreachStmt.getList().accept(expressionTypeChecker);
        foreachStmt.getBody().accept(this);

        if(itType instanceof NoType)
            return null;
        if(!(itType instanceof ListType))
        {
            foreachStmt.addError(new ForeachCantIterateNoneList(foreachStmt.getLine()));
            isInLoop =false;
            return null;
        }
        Type listItemType;
        if(! expressionTypeChecker.listHasSameType((ListType)itType))
            foreachStmt.addError(new ForeachListElementsNotSameType(foreachStmt.getLine()));
        listItemType = ((ListType) itType).getElementsTypes().get(0).getType();

        if(!expressionTypeChecker.checkTypes(identifierType, listItemType, false))
            foreachStmt.addError(new ForeachVarNotMatchList(foreachStmt));

        isInLoop = tempIsInLoop;

        haveBeenPrinted.pop();
        haveBeenUnreached.pop();

        return null;
    }

    @Override
    public Void visit(ForStmt forStmt) {
        if(!(haveBeenPrinted.peek()) && haveBeenUnreached.peek()){
            forStmt.addError(new UnreachableStatements(forStmt));
            haveBeenPrinted.pop();
            haveBeenPrinted.push(true);
        }

        haveBeenPrinted.push(haveBeenPrinted.peek());
        haveBeenUnreached.push(haveBeenUnreached.peek());

        expressionTypeChecker.isLValueViolated = false;
        expressionTypeChecker.aloneThis = false;
        boolean tempIsInLoop = isInLoop;
        isInLoop = true;
        Type conditionType;
        if (forStmt.getInitialize() != null)
            forStmt.getInitialize().accept(this);
        if (forStmt.getCondition() != null) {
            conditionType = forStmt.getCondition().accept(expressionTypeChecker);
        }
        else
            conditionType = new BoolType();
        if (forStmt.getUpdate() != null)
            forStmt.getUpdate().accept(this);
        forStmt.getBody().accept(this);
        if (!(conditionType instanceof BoolType || conditionType instanceof NoType))
            forStmt.addError(new ConditionNotBool(forStmt.getLine()));
        isInLoop = tempIsInLoop;

        haveBeenPrinted.pop();
        haveBeenUnreached.pop();

        return null;
    }

}
