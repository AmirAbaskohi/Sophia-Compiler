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

public class TypeChecker extends Visitor<Void> {
    private final Graph<String> classHierarchy;
    private final ExpressionTypeChecker expressionTypeChecker;

    public boolean isInLoop;
    public Type methodRetType;

    public TypeChecker(Graph<String> classHierarchy) {
        this.classHierarchy = classHierarchy;
        this.expressionTypeChecker = new ExpressionTypeChecker(classHierarchy);
        this.isInLoop = false;
        this.methodRetType = new NoType();
    }

    @Override
    public Void visit(Program program) {

        if (classHierarchy.isSecondNodeAncestorOf("NullType", "A"))
            System.out.println("arash rasouli");
        //TODO
        return null;
    }

    @Override
    public Void visit(ClassDeclaration classDeclaration) {
        String key = ClassSymbolTableItem.START_KEY + classDeclaration.getClassName().getName();

        try{
            String parentKey = ClassSymbolTableItem.START_KEY + classDeclaration.getParentClassName().getName();
            SymbolTable.root.getItem(parentKey, true);
        } catch (ItemNotFoundException exp){
            classDeclaration.addError(new ClassNotDeclared(classDeclaration.getLine(), classDeclaration.getParentClassName().getName()));
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
        String constructorName = constructorDeclaration.getMethodName().getName();

        String key = MethodSymbolTableItem.START_KEY + constructorDeclaration.getMethodName().getName();
        try {
            SymbolTableItem symbolTableItem = SymbolTable.top.getItem(key, true);
            MethodSymbolTableItem methodSymbolTableItem = (MethodSymbolTableItem) symbolTableItem;
            SymbolTable.push(methodSymbolTableItem.getMethodSymbolTable());
        }
        catch (ItemNotFoundException ex){
            System.out.println("Never happening!");
        }

        if(constructorName.equals(ExpressionTypeChecker.className.getName()))
            constructorDeclaration.addError(new ConstructorNotSameNameAsClass(constructorDeclaration.getLine()));
        for(VarDeclaration varDeclaration : constructorDeclaration.getArgs())
            varDeclaration.accept(this);
        for(VarDeclaration varDeclaration : constructorDeclaration.getLocalVars())
            varDeclaration.accept(this);
        for(Statement statement : constructorDeclaration.getBody())
            statement.accept(this);
        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(MethodDeclaration methodDeclaration) {
        boolean hasReturnTypeError = false;

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
                hasReturnTypeError = true;
            }
        }
        if(returnType instanceof ClassType){
            if(expressionTypeChecker.isClassDeclared((ClassType)returnType)) {
                methodDeclaration.addError(new ClassNotDeclared(methodDeclaration.getLine(), ((ClassType) returnType).getClassName().getName()));
                hasReturnTypeError = true;
            }
        }
        if(returnType instanceof FptrType){
            ArrayList<CompileErrorException> errors = expressionTypeChecker.checkFptrDecError((FptrType) returnType, methodDeclaration.getLine());
            for(CompileErrorException error : errors) {
                methodDeclaration.addError(error);
                hasReturnTypeError = true;
            }
        }

        methodRetType = hasReturnTypeError ? new NoType() : methodDeclaration.getReturnType();

        for(VarDeclaration varDeclaration : methodDeclaration.getArgs())
            varDeclaration.accept(this);
        for(VarDeclaration varDeclaration : methodDeclaration.getLocalVars())
            varDeclaration.accept(this);
        for(Statement statement : methodDeclaration.getBody())
            statement.accept(this);
        SymbolTable.pop();
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
            if(expressionTypeChecker.isClassDeclared((ClassType)type))
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
        Type rValue = assignmentStmt.getrValue().accept(expressionTypeChecker);
        Type lValue = assignmentStmt.getlValue().accept(expressionTypeChecker);
        if(rValue instanceof NullType && !(assignmentStmt.getrValue() instanceof NullValue)) {
            assignmentStmt.addError(new CantUseValueOfVoidMethod(assignmentStmt.getLine()));
            rValue = new NoType();
        }
        if(lValue instanceof NullType && !(assignmentStmt.getlValue() instanceof NullValue)) {
            assignmentStmt.addError(new CantUseValueOfVoidMethod(assignmentStmt.getLine()));
            lValue = new NoType();
        }
        //TODO
        if(!expressionTypeChecker.checkTypes(rValue, lValue, true))
            assignmentStmt.addError(new UnsupportedOperandType(assignmentStmt.getLine(), BinaryOperator.assign.name()));
        if(expressionTypeChecker.isValidLValueForAssignment(assignmentStmt.getlValue()))
            assignmentStmt.addError(new LeftSideNotLvalue(assignmentStmt.getLine()));
        return null;
    }

    @Override
    public Void visit(BlockStmt blockStmt) {
        for(Statement statement : blockStmt.getStatements())
            statement.accept(this);
        return null;
    }

    @Override
    public Void visit(ConditionalStmt conditionalStmt) {
        Type conditionType = conditionalStmt.getCondition().accept(expressionTypeChecker);
        conditionalStmt.getThenBody().accept(this);
        if (conditionalStmt.getElseBody() != null)
            conditionalStmt.getElseBody().accept(this);
        if (!(conditionType instanceof BoolType || conditionType instanceof NoType))
            conditionalStmt.addError(new ConditionNotBool(conditionalStmt.getLine()));
        return null;
    }

    @Override
    public Void visit(MethodCallStmt methodCallStmt) {
        methodCallStmt.getMethodCall().accept(expressionTypeChecker);
        return null;
    }

    @Override
    public Void visit(PrintStmt print) {
        Type argType = print.getArg().accept(expressionTypeChecker);

        if(argType instanceof NullType && !(print.getArg() instanceof NullValue)) {
            print.addError(new CantUseValueOfVoidMethod(print.getLine()));
            argType = new NoType();
        }

        if(!(argType instanceof IntType) && !(argType instanceof StringType) && !(argType instanceof BoolType) && !(argType instanceof NoType))
            print.addError(new UnsupportedTypeForPrint(print.getLine()));
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        Type returnType;
        if (returnStmt.getReturnedExpr() != null) {
            returnType = returnStmt.getReturnedExpr().accept(expressionTypeChecker);
            if(returnType instanceof NullType && !(returnStmt.getReturnedExpr() instanceof NullValue)) {
                returnStmt.addError(new CantUseValueOfVoidMethod(returnStmt.getLine()));
                returnType = new NoType();
            }
        }
        else
            returnType = new NullType();
        if (! expressionTypeChecker.checkTypes(returnType, methodRetType, true))
            returnStmt.addError(new ReturnValueNotMatchMethodReturnType(returnStmt));
        return null;
    }

    @Override
    public Void visit(BreakStmt breakStmt) {
        if (!isInLoop)
            breakStmt.addError(new ContinueBreakNotInLoop(breakStmt.getLine(), 0));
        return null;
    }

    @Override
    public Void visit(ContinueStmt continueStmt) {
        if (!isInLoop)
            continueStmt.addError(new ContinueBreakNotInLoop(continueStmt.getLine(), 1));
        return null;
    }

    @Override
    public Void visit(ForeachStmt foreachStmt) {
        isInLoop = true;
        Type identifierType = foreachStmt.getVariable().accept(expressionTypeChecker);
        Type itType = foreachStmt.getList().accept(expressionTypeChecker);
        foreachStmt.getBody().accept(this);

        if(itType instanceof NullType && !(foreachStmt.getList() instanceof NullValue)) {
            foreachStmt.addError(new CantUseValueOfVoidMethod(foreachStmt.getLine()));
            itType = new NoType();
        }

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
        //TODO

        isInLoop =false;
        return null;
    }

    @Override
    public Void visit(ForStmt forStmt) {
        isInLoop = true;
        Type conditionType;
        if (forStmt.getInitialize() != null)
            forStmt.getInitialize().accept(this);
        if (forStmt.getCondition() != null) {
            conditionType = forStmt.getCondition().accept(expressionTypeChecker);
            if (conditionType instanceof NullType && !(forStmt.getCondition() instanceof NullValue)) {
                forStmt.addError(new CantUseValueOfVoidMethod(forStmt.getLine()));
                conditionType = new NoType();
            }
        }
        else
            conditionType = new BoolType();
        if (forStmt.getUpdate() != null)
            forStmt.getUpdate().accept(this);
        forStmt.getBody().accept(this);
        if (!(conditionType instanceof BoolType || conditionType instanceof NoType))
            forStmt.addError(new ConditionNotBool(forStmt.getLine()));
        isInLoop = false;
        return null;
    }

}
