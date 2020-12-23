package main.visitor.typeChecker;

import main.ast.nodes.declaration.variableDec.VarDeclaration;
import main.ast.nodes.expression.*;
import main.ast.nodes.expression.operators.BinaryOperator;
import main.ast.nodes.expression.values.ListValue;
import main.ast.nodes.expression.values.NullValue;
import main.ast.nodes.expression.values.primitive.BoolValue;
import main.ast.nodes.expression.values.primitive.IntValue;
import main.ast.nodes.expression.values.primitive.StringValue;
import main.ast.types.NoType;
import main.ast.types.NullType;
import main.ast.types.Type;
import main.ast.types.functionPointer.FptrType;
import main.ast.types.list.ListNameType;
import main.ast.types.list.ListType;
import main.ast.types.single.BoolType;
import main.ast.types.single.ClassType;
import main.ast.types.single.IntType;
import main.ast.types.single.StringType;
import main.compileErrorException.typeErrors.ClassNotDeclared;
import main.compileErrorException.typeErrors.ConstructorArgsNotMatchDefinition;
import main.compileErrorException.typeErrors.UnsupportedOperandType;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.ClassSymbolTableItem;
import main.symbolTable.items.FieldSymbolTableItem;
import main.symbolTable.items.LocalVariableSymbolTableItem;
import main.symbolTable.utils.graph.Graph;
import main.visitor.Visitor;

import java.util.ArrayList;


public class ExpressionTypeChecker extends Visitor<Type> {
    private final Graph<String> classHierarchy;
    public ExpressionTypeChecker(Graph<String> classHierarchy) {
        this.classHierarchy = classHierarchy;
    }
    static Identifier className;

    @Override
    public Type visit(BinaryExpression binaryExpression) {
        Expression lValue = binaryExpression.getFirstOperand();
        Expression rValue = binaryExpression.getSecondOperand();
        BinaryOperator operator = binaryExpression.getBinaryOperator();
        Type rType = rValue.accept(this);
        Type lType = lValue.accept(this);
        boolean isLTypeNoType = lType.toString().equals("NoType");
        boolean isRTypeNoType = rType.toString().equals("NoType");
        switch (operator){
            case assign:

                break;
            case eq:
            case neq:
                if(lType.toString().equals("ListType") || rType.toString().equals("ListType")) {
                    binaryExpression.addError(new UnsupportedOperandType(binaryExpression.getLine(), operator.name()));
                    return new NoType();
                }
                if(isLTypeNoType || isRTypeNoType)
                    return new NoType();
                if(rType.toString().equals(lType.toString()))
                    return new BoolType();
                if(rType instanceof NullType || lType instanceof NullType)
                    if(rType instanceof FptrType || lType instanceof FptrType || rType instanceof ClassType || lType instanceof ClassType)
                        return new BoolType();
                binaryExpression.addError(new UnsupportedOperandType(binaryExpression.getLine(), operator.name()));
                return new NoType();
            case add:
            case sub:
            case mult:
            case mod:
            case div:
            case lt:
            case gt:
                if((isLTypeNoType || lType.toString().equals("IntType")) &&
                        (isRTypeNoType || rType.toString().equals("IntType"))){
                    if (isLTypeNoType || isRTypeNoType)
                        return new NoType();
                    return new IntType();
                }
                binaryExpression.addError(new UnsupportedOperandType(binaryExpression.getLine(), operator.name()));
                return new NoType();
            case and:
            case or:
                if((isLTypeNoType || lType.toString().equals("BoolType")) &&
                        (isRTypeNoType || rType.toString().equals("BoolType"))){
                    if (isLTypeNoType || isRTypeNoType)
                        return new NoType();
                    return new BoolType();

                }
                binaryExpression.addError(new UnsupportedOperandType(binaryExpression.getLine(), operator.name()));
                return new NoType();
        }
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
        String keyField = FieldSymbolTableItem.START_KEY + identifier.getName();
        String keyLocal = LocalVariableSymbolTableItem.START_KEY + identifier.getName();
        try {
            FieldSymbolTableItem filedSymbolTableItem = (FieldSymbolTableItem) SymbolTable.top.getItem(keyField, true);
            return filedSymbolTableItem.getType();
        }
        catch (ItemNotFoundException ex)
        {

        }
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
        String _className = newClassInstance.getClassType().getClassName().getName();
        String key = ClassSymbolTableItem.START_KEY + _className;
        ArrayList<Type> types = new ArrayList<>();
        for (Expression arg: newClassInstance.getArgs())
            types.add(arg.accept(this));

        try {
            ClassSymbolTableItem classSymbolTableItem = (ClassSymbolTableItem) SymbolTable.root.getItem(key, true);
            ArrayList<VarDeclaration> classArgs = classSymbolTableItem.getClassDeclaration().getConstructor().getArgs();
            if (newClassInstance.getArgs().size() != classArgs.size()) {
                newClassInstance.addError(new ConstructorArgsNotMatchDefinition(newClassInstance));
                return new NoType();
            }

            for (int i=0; i<classArgs.size(); i++) {
                if(types.get(i) instanceof NoType)
                    continue;
                if (classArgs.get(i).getType() instanceof ClassType && types.get(i) instanceof NullType)
                    continue;

                if (types.get(i) instanceof ClassType && classArgs.get(i).getType() instanceof ClassType){
                    String rArgClassName = ((ClassType) types.get(i)).getClassName().getName();
                    String lArgClassName = ((ClassType) classArgs.get(i).getType()).getClassName().getName();
                    if (classHierarchy.isSecondNodeAncestorOf(rArgClassName, lArgClassName))
                        continue;
                }

                if(!classArgs.get(i).getType().toString().equals(types.get(i).toString())) {
                    newClassInstance.addError(new ConstructorArgsNotMatchDefinition(newClassInstance));
                    return new NoType();
                }
            }

            return newClassInstance.getClassType();
        }
        catch (ItemNotFoundException ex)
        {
            newClassInstance.addError(new ClassNotDeclared(newClassInstance.getLine(), _className));
            return new NoType();
        }
    }

    @Override
    public Type visit(ThisClass thisClass) {
        return new ClassType(className);
    }

    @Override
    public Type visit(ListValue listValue) {
        ListType listType = new ListType();
        for (Expression element: listValue.getElements() ) {
            Type t = element.accept(this);
            listType.addElementType(new ListNameType(t));
        }
        return listType;
    }

    @Override
    public Type visit(NullValue nullValue) {
        return new NullType();
    }

    @Override
    public Type visit(IntValue intValue) {
        return new IntType();
    }

    @Override
    public Type visit(BoolValue boolValue) {
        return new BoolType();
    }

    @Override
    public Type visit(StringValue stringValue) {
        return new StringType();
    }
}
