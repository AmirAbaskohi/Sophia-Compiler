package main.visitor;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.classDec.ClassDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.ConstructorDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.FieldDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.MethodDeclaration;
import main.ast.nodes.declaration.variableDec.VarDeclaration;
import main.ast.nodes.expression.*;
import main.ast.nodes.expression.values.ListValue;
import main.ast.nodes.expression.values.NullValue;
import main.ast.nodes.expression.values.primitive.BoolValue;
import main.ast.nodes.expression.values.primitive.IntValue;
import main.ast.nodes.expression.values.primitive.StringValue;
import main.ast.nodes.statement.*;
import main.ast.nodes.statement.loop.BreakStmt;
import main.ast.nodes.statement.loop.ContinueStmt;
import main.ast.nodes.statement.loop.ForStmt;
import main.ast.nodes.statement.loop.ForeachStmt;

public class ASTTreePrinter extends Visitor<Void> {

    @Override
    public Void visit(Program program) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ClassDeclaration classDeclaration) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ConstructorDeclaration constructorDeclaration) {
        //Todo
        return null;
    }

    @Override
    public Void visit(MethodDeclaration methodDeclaration) {
        //Todo
        return null;
    }

    @Override
    public Void visit(FieldDeclaration fieldDeclaration) {
        //Todo
        return null;
    }

    @Override
    public Void visit(VarDeclaration varDeclaration) {
        //Todo
        return null;
    }

    @Override
    public Void visit(AssignmentStmt assignmentStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(BlockStmt blockStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ConditionalStmt conditionalStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(MethodCallStmt methodCallStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(PrintStmt print) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(BreakStmt breakStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ContinueStmt continueStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ForeachStmt foreachStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ForStmt forStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(BinaryExpression binaryExpression) {
        //Todo
        return null;
    }

    @Override
    public Void visit(UnaryExpression unaryExpression) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ObjectOrListMemberAccess objectOrListMemberAccess) {
        //Todo
        return null;
    }

    @Override
    public Void visit(Identifier identifier) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ListAccessByIndex listAccessByIndex) {
        //Todo
        return null;
    }

    @Override
    public Void visit(MethodCall methodCall) {
        //Todo
        return null;
    }

    @Override
    public Void visit(NewClassInstance newClassInstance) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ThisClass thisClass) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ListValue listValue) {
        //Todo
        return null;
    }

    @Override
    public Void visit(NullValue nullValue) {
        //Todo
        return null;
    }

    @Override
    public Void visit(IntValue intValue) {
        //Todo
        return null;
    }

    @Override
    public Void visit(BoolValue boolValue) {
        //Todo
        return null;
    }

    @Override
    public Void visit(StringValue stringValue) {
        //Todo
        return null;
    }

}
