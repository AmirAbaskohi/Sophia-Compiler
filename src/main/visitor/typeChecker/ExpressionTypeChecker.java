package main.visitor.typeChecker;

import main.ast.nodes.declaration.classDec.classMembersDec.ConstructorDeclaration;
import main.ast.nodes.declaration.variableDec.VarDeclaration;
import main.ast.nodes.expression.*;
import main.ast.nodes.expression.operators.BinaryOperator;
import main.ast.nodes.expression.operators.UnaryOperator;
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
import main.compileErrorException.CompileErrorException;
import main.compileErrorException.typeErrors.*;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.*;
import main.symbolTable.utils.graph.Graph;
import main.visitor.Visitor;

import java.util.ArrayList;
import java.util.HashSet;


public class ExpressionTypeChecker extends Visitor<Type> {
    private final Graph<String> classHierarchy;
    public ExpressionTypeChecker(Graph<String> classHierarchy)
    {
        this.classHierarchy = classHierarchy;
        this.instanceTypeGlob = null;
    }
    static Identifier className;
    public Type instanceTypeGlob;

    public boolean isValidLValueForAssignment(Expression lValue)
    {
        return true;
    }

    public ArrayList<CompileErrorException> checkListDecError(ListType listType, int line)
    {
        ArrayList<CompileErrorException> errors = new ArrayList<>();
        ArrayList<ListNameType> listMembers = listType.getElementsTypes();
        if(listMembers.size() == 0)
            errors.add(new CannotHaveEmptyList(line));
        HashSet<String> memeberNames = new HashSet<>();
        for(ListNameType member : listMembers){
            if(memeberNames.contains(member.getName().getName())){
                errors.add(new DuplicateListId(line));
                break;
            }
            memeberNames.add(member.getName().getName());
        }
        for(ListNameType member : listMembers){
            Type memberType = member.getType();
            if(memberType instanceof ListType)
                errors.addAll(checkListDecError((ListType) memberType, line));

            if(memberType instanceof ClassType)
                if(isClassDeclared((ClassType)memberType))
                    errors.add(new ClassNotDeclared(line, ((ClassType) memberType).getClassName().getName()));

            if(memberType instanceof FptrType)
                errors.addAll(checkFptrDecError((FptrType)memberType, line));
        }
        return errors;
    }

    public ArrayList<CompileErrorException> checkFptrDecError(FptrType fptrType, int line)
    {
        ArrayList<CompileErrorException> errors = new ArrayList<>();
        ArrayList<Type> retTypeAndArgTypes = fptrType.getArgumentsTypes();
        retTypeAndArgTypes.add(fptrType.getReturnType());
        for(Type memberType : retTypeAndArgTypes){
            if(memberType instanceof ListType)
                errors.addAll(checkListDecError((ListType) memberType, line));

            if(memberType instanceof ClassType)
                if(isClassDeclared((ClassType)memberType))
                    errors.add(new ClassNotDeclared(line, ((ClassType) memberType).getClassName().getName()));

            if(memberType instanceof FptrType)
                errors.addAll(checkFptrDecError((FptrType)memberType, line));
        }
        return errors;
    }

    public boolean isClassDeclared(ClassType classType)
    {
        String className = classType.getClassName().getName();
        String key = ClassSymbolTableItem.START_KEY + className;
        try{
            SymbolTable.root.getItem(key, true);
            return true;
        }catch (ItemNotFoundException ex){
            return false;
        }
    }

    public boolean checkList(ListType a, ListType b)
    {
        ArrayList<ListNameType> firstTypes = a.getElementsTypes();
        ArrayList<ListNameType> secondTypes = b.getElementsTypes();
        if(firstTypes.size() != secondTypes.size())
            return false;
        for(int i = 0 ; i < firstTypes.size() ; i++)
        {
            Type first = firstTypes.get(i).getType();
            Type second = secondTypes.get(i).getType();
            if(first instanceof NoType || second instanceof NoType)
                continue;
            if(!first.toString().equals(second.toString()))
                return false;
            if(first instanceof ListType)
                if(!checkList((ListType)first, (ListType)second))
                    return false;
            if(first instanceof FptrType)
                if(!checkFunctionPointer((FptrType)first, (FptrType)second))
                    return false;
        }
        return true;
    }

    public boolean checkFunctionPointer(FptrType a, FptrType b)
    {
        ArrayList<Type> firstArgumentTypes = a.getArgumentsTypes();
        ArrayList<Type> secondArgumentTypes = b.getArgumentsTypes();
        if(firstArgumentTypes.size() != secondArgumentTypes.size())
            return false;
        if(!(a.getReturnType() instanceof NoType || b.getReturnType() instanceof NoType)) {
            if (!a.getReturnType().toString().equals(b.getReturnType().toString()))
                return false;
            if (a.getReturnType() instanceof ListType)
                if (!checkList((ListType) a.getReturnType(), (ListType) b.getReturnType()))
                    return false;
            if (a.getReturnType() instanceof FptrType)
                if (!checkFunctionPointer((FptrType) a.getReturnType(), (FptrType) b.getReturnType()))
                    return false;
        }
        for(int i = 0 ; i < firstArgumentTypes.size() ; i++)
        {
            Type first = firstArgumentTypes.get(i);
            Type second = secondArgumentTypes.get(i);
            if(first instanceof NoType || second instanceof NoType)
                continue;
            if(!first.toString().equals(second.toString()))
                return false;
            if(first instanceof ListType)
                if(!checkList((ListType)first, (ListType)second))
                    return false;
            if(first instanceof FptrType)
                if(!checkFunctionPointer((FptrType)first, (FptrType)second))
                    return false;
        }
        return true;
    }

    public boolean listHasSameType(ListType inputList)
    {
        boolean isFirst = true;
        Type baseType = new NoType();
        for(ListNameType listNameType : inputList.getElementsTypes())
        {
            Type elementType = listNameType.getType();
            if (elementType instanceof NoType)
                continue;
            if(isFirst) {
                baseType = elementType;
                isFirst = false;
            }
            else {
                boolean areSame;
                if(baseType instanceof ListType && elementType instanceof ListType)
                    areSame = this.checkList(((ListType) elementType), ((ListType) baseType));
                else if(baseType instanceof FptrType && elementType instanceof FptrType)
                    areSame = this.checkFunctionPointer(((FptrType) elementType), ((FptrType) baseType));
                else
                    areSame = baseType.toString().equals(elementType.toString());
                if(!areSame)
                    return false;
            }
        }
        return true;
    }

    public boolean checkTypes(Type from, Type to, boolean isSubAllowed)
    {
        if(from instanceof NoType || to instanceof NoType)
            return true;
        if ((to instanceof ClassType || to instanceof FptrType ) && from instanceof NullType)
            return true;

        if ((from instanceof ClassType) && (to instanceof ClassType) && isSubAllowed){
            String fromClassName = ((ClassType) from).getClassName().getName();
            String toClassName = ((ClassType) to).getClassName().getName();
            return classHierarchy.isSecondNodeAncestorOf(fromClassName, toClassName);
        }

        if (from instanceof ListType && to instanceof ListType)
            return checkList((ListType) from, (ListType) to);

        if (from instanceof FptrType && to instanceof FptrType)
            return checkFunctionPointer((FptrType) from, (FptrType) to);

        return to.toString().equals(from.toString());
    }

    public boolean isValid(Type type)
    {
        if(type instanceof ClassType){
            return isClassDeclared((ClassType)type);
        }
        if(type instanceof FptrType){
            FptrType fptrType = (FptrType)type;
            ArrayList<Type> types = fptrType.getArgumentsTypes();
            types.add(fptrType.getReturnType());
            for(Type t : types){
                if(!isValid(t))
                    return false;
            }
        }
        if(type instanceof ListType){
            ArrayList<ListNameType> listNameTypes = ((ListType) type).getElementsTypes();
            if(listNameTypes.size() == 0)
                return false;
            HashSet<String> keys = new HashSet<>();
            for(ListNameType listNameType : listNameTypes){
                if(keys.contains(listNameType.getName().getName()))
                    return false;
                keys.add(listNameType.getName().getName());
            }
            for(ListNameType listNameType : listNameTypes){
                if(!isValid(listNameType.getType()))
                    return false;
            }
        }
        return true;
    }

    public FptrType makeFptrType(MethodSymbolTableItem methodSymbolTableItem)
    {
        FptrType result = new FptrType();
        result.setReturnType(methodSymbolTableItem.getReturnType());
        result.setArgumentsTypes(methodSymbolTableItem.getArgTypes());
        return result;
    }

    @Override
    public Type visit(BinaryExpression binaryExpression) {
        Expression lValue = binaryExpression.getFirstOperand();
        Expression rValue = binaryExpression.getSecondOperand();
        BinaryOperator operator = binaryExpression.getBinaryOperator();
        Type rType = rValue.accept(this);
        Type lType = lValue.accept(this);
        if(lType instanceof NullType && !(lValue instanceof NullValue)){
            binaryExpression.addError(new CantUseValueOfVoidMethod(binaryExpression.getLine()));
            lType = new NoType();
        }
        if(rType instanceof NullType && !(rValue instanceof NullValue)){
            binaryExpression.addError(new CantUseValueOfVoidMethod(binaryExpression.getLine()));
            rType = new NoType();
        }
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

    // ERROR 7 SHOULD BE HANDLED
    @Override
    public Type visit(UnaryExpression unaryExpression) {
        Expression operand = unaryExpression.getOperand();
        UnaryOperator operator = unaryExpression.getOperator();
        Type operandType = operand.accept(this);
        switch (operator) {
            case not -> {
                if (operandType instanceof BoolType || operandType instanceof NoType)
                    return operandType;
            }
            case minus, preinc, postinc, predec, postdec -> {
                if (operandType instanceof IntType || operandType instanceof NoType)
                    return operandType;
            }
        }
        unaryExpression.addError(new UnsupportedOperandType(unaryExpression.getLine(), operator.name()));
        return new NoType();
    }

    @Override
    public Type visit(ObjectOrListMemberAccess objectOrListMemberAccess) {
        Type instanceType = objectOrListMemberAccess.getInstance().accept(this);
        Type result;
        if(instanceType instanceof NullType && !(objectOrListMemberAccess.getInstance() instanceof NullValue)){
            objectOrListMemberAccess.addError(new CantUseValueOfVoidMethod(objectOrListMemberAccess.getLine()));
            instanceType = new NoType();
        }
        if(instanceType instanceof ClassType){
            instanceTypeGlob = instanceType;
            result = objectOrListMemberAccess.getMemberName().accept(this);
        }
        else if(instanceType instanceof ListType){
            instanceTypeGlob = instanceType;
            result = objectOrListMemberAccess.getMemberName().accept(this);
        }
        else if(instanceType instanceof NoType)
            result = new NoType();
        else{
            objectOrListMemberAccess.addError(new MemberAccessOnNoneObjOrListType(objectOrListMemberAccess.getLine()));
            result = new NoType();
        }
        instanceTypeGlob = null;
        return result;
    }

    @Override
    public Type visit(Identifier identifier) {

        if(instanceTypeGlob == null){
            String key = LocalVariableSymbolTableItem.START_KEY + identifier.getName();
            try{
                SymbolTableItem symbolTableItem = SymbolTable.top.getItem(key, true);
                LocalVariableSymbolTableItem localVariableSymbolTableItem = (LocalVariableSymbolTableItem)symbolTableItem;
                Type localVarType = localVariableSymbolTableItem.getType();
                if(isValid(localVarType))
                    return localVarType;
                return new NoType();
            } catch (ItemNotFoundException ex){
                identifier.addError(new VarNotDeclared(identifier.getLine(), identifier.getName()));
                return new NoType();
            }
        }

        if(instanceTypeGlob instanceof ClassType){
            ClassType classType = (ClassType)instanceTypeGlob;
            SymbolTableItem symbolTableItem;
            String key = ClassSymbolTableItem.START_KEY + classType.getClassName().getName();
            try{
                symbolTableItem = SymbolTable.root.getItem(key, true);
                ClassSymbolTableItem classSymbolTableItem = (ClassSymbolTableItem)symbolTableItem;
                String fieldKey = FieldSymbolTableItem.START_KEY + identifier.getName();
                String methodKey = MethodSymbolTableItem.START_KEY + identifier.getName();
                try {
                    symbolTableItem = classSymbolTableItem.getClassSymbolTable().getItem(fieldKey, true);
                    FieldSymbolTableItem fieldSymbolTableItem = (FieldSymbolTableItem)symbolTableItem;
                    Type filedType = fieldSymbolTableItem.getType();
                    if(isValid(filedType))
                        return filedType;
                    return new NoType();
                } catch (ItemNotFoundException exField){
                    try {
                        symbolTableItem = classSymbolTableItem.getClassSymbolTable().getItem(methodKey, true);
                        MethodSymbolTableItem methodSymbolTableItem = (MethodSymbolTableItem)symbolTableItem;
                        FptrType fptrType = makeFptrType(methodSymbolTableItem);
                        if(isValid(fptrType))
                            return fptrType;
                        return new NoType();
                    } catch (ItemNotFoundException exMethod){
                        if(classType.getClassName().getName().equals(identifier.getName())) {
                            FptrType defaultCtorFptr = new FptrType();
                            defaultCtorFptr.setReturnType(new NullType());
                            return defaultCtorFptr;
                        }
                        identifier.addError(new MemberNotAvailableInClass(identifier.getLine(), identifier.getName(), classType.getClassName().getName()));
                        return new NoType();
                    }
                }
            } catch (ItemNotFoundException ex){
                System.out.println("Never happening!");
            }
        }

        if(instanceTypeGlob instanceof ListType){
            ListType listType = (ListType)instanceTypeGlob;
            String elementKey = identifier.getName();
            for(ListNameType elementType : listType.getElementsTypes()){
                if(elementKey.equals(elementType.getName().getName()))
                    return elementType.getType();
            }
            identifier.addError(new ListMemberNotFound(identifier.getLine(), identifier.getName()));
            return new NoType();
        }

        return null;
    }

    @Override
    public Type visit(ListAccessByIndex listAccessByIndex) {
        Type instanceType = listAccessByIndex.getInstance().accept(this);
        if(instanceType instanceof NullType && !(listAccessByIndex.getInstance() instanceof NullValue)){
            listAccessByIndex.addError(new CantUseValueOfVoidMethod(listAccessByIndex.getLine()));
            instanceType = new NoType();
        }
        Type indexType = listAccessByIndex.getIndex().accept(this);
        if(indexType instanceof NullType && !(listAccessByIndex.getIndex() instanceof NullValue)){
            listAccessByIndex.addError(new CantUseValueOfVoidMethod(listAccessByIndex.getLine()));
            indexType = new NoType();
        }
        boolean isConstant = listAccessByIndex.getIndex() instanceof IntValue;
        if(!(instanceType instanceof ListType || instanceType instanceof NoType) || !(indexType instanceof IntType || indexType instanceof NoType))
        {
            if(!(instanceType instanceof ListType || instanceType instanceof NoType))
                listAccessByIndex.addError(new ListAccessByIndexOnNoneList(listAccessByIndex.getLine()));
            if(!(indexType instanceof IntType || indexType instanceof NoType))
                listAccessByIndex.addError(new ListIndexNotInt(listAccessByIndex.getLine()));
            return new NoType();
        }
        if(instanceType instanceof NoType || indexType instanceof NoType)
            return new NoType();

        boolean areDifferentType = !(listHasSameType((ListType)instanceType));
        ListType list = (ListType)instanceType;

        if(areDifferentType){
            if(!isConstant){
                listAccessByIndex.addError(new CantUseExprAsIndexOfMultiTypeList(listAccessByIndex.getLine()));
                return new NoType();
            }
            Expression indexExp = listAccessByIndex.getIndex();
            int index = ((IntValue)indexExp).getConstant();
            int size = ((ListType)instanceType).getElementsTypes().size();
            if(index > size - 1)
                return list.getElementsTypes().get(0).getType();
            return list.getElementsTypes().get(index).getType();
        }
        return list.getElementsTypes().get(0).getType();
    }

    @Override
    public Type visit(MethodCall methodCall) {
        Type instanceType = methodCall.getInstance().accept(this);
        if(instanceType instanceof NullType && !(methodCall.getInstance() instanceof NullValue)){
            methodCall.addError(new CantUseValueOfVoidMethod(methodCall.getLine()));
            instanceType = new NoType();
        }
        ArrayList<Type> types = new ArrayList<>();
        for(Expression arg : methodCall.getArgs()) {
            Type argType = arg.accept(this);
            if(argType instanceof NullType && !(arg instanceof NullValue)) {
                methodCall.addError(new CantUseValueOfVoidMethod(methodCall.getLine()));
                types.add(new NoType());
            }
            else
                types.add(argType);
        }
        if(instanceType instanceof NoType)
            return new NoType();
        if(!(instanceType instanceof FptrType)) {
            methodCall.addError(new CallOnNoneFptrType(methodCall.getLine()));
            return new NoType();
        }
        ArrayList<Type> argTypes = ((FptrType)instanceType).getArgumentsTypes();
        if(types.size() != argTypes.size())
        {
            methodCall.addError(new MethodCallNotMatchDefinition(methodCall.getLine()));
            return new NoType();
        }
        for (int i=0; i<types.size(); i++) {
            if(!checkTypes(types.get(i), argTypes.get(i), true)){
                methodCall.addError(new MethodCallNotMatchDefinition(methodCall.getLine()));
                return new NoType();
            }
        }
        return ((FptrType) instanceType).getReturnType();
    }

    @Override
    public Type visit(NewClassInstance newClassInstance) {
        String _className = newClassInstance.getClassType().getClassName().getName();
        String key = ClassSymbolTableItem.START_KEY + _className;
        ArrayList<Type> types = new ArrayList<>();
        for (Expression arg: newClassInstance.getArgs()) {
            Type type = arg.accept(this);
            if(type instanceof NullType && !(arg instanceof NullValue)){
                newClassInstance.addError(new CantUseValueOfVoidMethod(newClassInstance.getLine()));
                types.add(new NoType());
            }
            else
                types.add(type);
        }
        try {
            ClassSymbolTableItem classSymbolTableItem = (ClassSymbolTableItem) SymbolTable.root.getItem(key, true);
            ConstructorDeclaration constructorDeclaration = classSymbolTableItem.getClassDeclaration().getConstructor();
            ArrayList<VarDeclaration> classArgs = new ArrayList<>();
            if(constructorDeclaration != null)
                classArgs = classSymbolTableItem.getClassDeclaration().getConstructor().getArgs();
            if (newClassInstance.getArgs().size() != classArgs.size()) {
                newClassInstance.addError(new ConstructorArgsNotMatchDefinition(newClassInstance));
                return new NoType();
            }
            for (int i=0; i<classArgs.size(); i++) {
                if(!checkTypes(types.get(i), classArgs.get(i).getType(), true)){
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
            if(t instanceof NullType && !(element instanceof NullValue)){
                listValue.addError(new CantUseValueOfVoidMethod(listValue.getLine()));
                listType.addElementType(new ListNameType(new NoType()));
            }
            else
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
