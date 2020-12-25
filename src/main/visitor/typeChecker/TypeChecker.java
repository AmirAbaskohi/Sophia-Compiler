package main.visitor.typeChecker;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.classDec.ClassDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.ConstructorDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.FieldDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.MethodDeclaration;
import main.ast.nodes.declaration.variableDec.VarDeclaration;
import main.ast.nodes.expression.Expression;
import main.ast.nodes.statement.*;
import main.ast.nodes.statement.loop.BreakStmt;
import main.ast.nodes.statement.loop.ContinueStmt;
import main.ast.nodes.statement.loop.ForStmt;
import main.ast.nodes.statement.loop.ForeachStmt;
import main.ast.types.NoType;
import main.ast.types.NullType;
import main.ast.types.Type;
import main.ast.types.list.ListType;
import main.ast.types.single.BoolType;
import main.ast.types.single.IntType;
import main.ast.types.single.StringType;
import main.compileErrorException.typeErrors.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.ClassSymbolTableItem;
import main.symbolTable.items.SymbolTableItem;
import main.symbolTable.utils.graph.Graph;
import main.visitor.Visitor;

public class TypeChecker extends Visitor<Void> {
    private final Graph<String> classHierarchy;
    private final ExpressionTypeChecker expressionTypeChecker;

    private boolean isInLoop;
    private Type methodRetType;

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
        try {
            ClassSymbolTableItem classSymbolTableItem = (ClassSymbolTableItem)SymbolTable.root.getItem(key, true);
            SymbolTable.push(classSymbolTableItem.getClassSymbolTable());
            ExpressionTypeChecker.className = classDeclaration.getClassName();
        }
        catch (ItemNotFoundException ex){}
        //TODO
        SymbolTable.pop();
        return null;
    }

    @Override
    public Void visit(ConstructorDeclaration constructorDeclaration) {
        //TODO
        return null;
    }

    @Override
    public Void visit(MethodDeclaration methodDeclaration) {

        //TODO
        return null;
    }

    @Override
    public Void visit(FieldDeclaration fieldDeclaration) {
        //TODO
        return null;
    }

    @Override
    public Void visit(VarDeclaration varDeclaration) {
        //TODO
        return null;
    }

    @Override
    public Void visit(AssignmentStmt assignmentStmt) {
        //TODO
        return null;
    }

    @Override
    public Void visit(BlockStmt blockStmt) {
        //TODO
        return null;
    }

    @Override
    public Void visit(ConditionalStmt conditionalStmt) {
        Type conditionType = conditionalStmt.getCondition().accept(expressionTypeChecker);
        conditionalStmt.getThenBody().accept(this);
        if (conditionalStmt.getElseBody() != null)
            conditionalStmt.getElseBody().accept(this);
        if (!(conditionType instanceof BoolType))
            conditionalStmt.addError(new ConditionNotBool(conditionalStmt.getLine()));
        return null;
    }

    @Override
    public Void visit(MethodCallStmt methodCallStmt) {
        methodCallStmt.getMethodCall().accept(expressionTypeChecker);
        //TODO
        return null;
    }

    @Override
    public Void visit(PrintStmt print) {
        Type argType = print.getArg().accept(expressionTypeChecker);
        if(!(argType instanceof IntType) && !(argType instanceof StringType) && !(argType instanceof BoolType) && !(argType instanceof NoType))
            print.addError(new UnsupportedTypeForPrint(print.getLine()));
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        Type returnType;
        if (returnStmt.getReturnedExpr() != null)
            returnType = returnStmt.getReturnedExpr().accept(expressionTypeChecker);
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
        if(!(itType instanceof ListType))
        {
            foreachStmt.addError(new ForeachCantIterateNoneList(foreachStmt.getLine()));
            isInLoop =false;
            return null;
        }
        Type listItemType;
        if(! expressionTypeChecker.listHasSameType((ListType)itType))
        {
            foreachStmt.addError(new ForeachListElementsNotSameType(foreachStmt.getLine()));
            listItemType = ((ListType) itType).getElementsTypes().get(0).getType();
        }
        else
            listItemType = expressionTypeChecker.getListType((ListType) itType);

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
        if (forStmt.getCondition() != null)
            conditionType= forStmt.getCondition().accept(expressionTypeChecker);
        else
            conditionType = new BoolType();
        if (forStmt.getUpdate() != null)
            forStmt.getUpdate().accept(this);
        forStmt.getBody().accept(this);
        if (!(conditionType instanceof BoolType))
            forStmt.addError(new ConditionNotBool(forStmt.getLine()));
        isInLoop = false;
        return null;
    }

}
