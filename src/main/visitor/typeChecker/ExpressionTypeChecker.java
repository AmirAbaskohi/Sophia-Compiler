package main.visitor.typeChecker;

import main.ast.nodes.expression.*;
import main.ast.nodes.expression.values.ListValue;
import main.ast.nodes.expression.values.NullValue;
import main.ast.nodes.expression.values.primitive.BoolValue;
import main.ast.nodes.expression.values.primitive.IntValue;
import main.ast.nodes.expression.values.primitive.StringValue;
import main.ast.types.Type;
import main.symbolTable.utils.graph.Graph;
import main.visitor.Visitor;


public class ExpressionTypeChecker extends Visitor<Type> {
    private final Graph<String> classHierarchy;

    public ExpressionTypeChecker(Graph<String> classHierarchy) {
        this.classHierarchy = classHierarchy;
    }

    @Override
    public Type visit(BinaryExpression binaryExpression) {
        //TODO
        return null;
    }

    @Override
    public Type visit(UnaryExpression unaryExpression) {
        //TODO
        return null;
    }

    @Override
    public Type visit(ObjectOrListMemberAccess objectOrListMemberAccess) {
        //TODO
        return null;
    }

    @Override
    public Type visit(Identifier identifier) {
        //TODO
        return null;
    }

    @Override
    public Type visit(ListAccessByIndex listAccessByIndex) {
        //TODO
        return null;
    }

    @Override
    public Type visit(MethodCall methodCall) {
        //TODO
        return null;
    }

    @Override
    public Type visit(NewClassInstance newClassInstance) {
        //TODO
        return null;
    }

    @Override
    public Type visit(ThisClass thisClass) {
        //TODO
        return null;
    }

    @Override
    public Type visit(ListValue listValue) {
        //TODO
        return null;
    }

    @Override
    public Type visit(NullValue nullValue) {
        //TODO
        return null;
    }

    @Override
    public Type visit(IntValue intValue) {
        //TODO
        return null;
    }

    @Override
    public Type visit(BoolValue boolValue) {
        //TODO
        return null;
    }

    @Override
    public Type visit(StringValue stringValue) {
        //TODO
        return null;
    }
}
