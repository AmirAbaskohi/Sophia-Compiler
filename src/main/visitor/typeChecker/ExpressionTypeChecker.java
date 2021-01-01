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
        this.isLValueViolated = false;
        this.aloneThis = false;
    }
    static Identifier className;
    public Type instanceTypeGlob;
    public boolean isLValueViolated;
    public boolean aloneThis;

    public ArrayList<CompileErrorException> checkListDecError(ListType listType, int line)
    {
        ArrayList<CompileErrorException> errors = new ArrayList<>();
        ArrayList<ListNameType> listMembers = listType.getElementsTypes();
        if(listMembers.size() == 0)
            errors.add(new CannotHaveEmptyList(line));
        HashSet<String> memberNames = new HashSet<>();
        for(ListNameType member : listMembers){
            if(member.getName().getName().equals(""))
                continue;
            if(memberNames.contains(member.getName().getName())){
                errors.add(new DuplicateListId(line));
                break;
            }
            memberNames.add(member.getName().getName());
        }
        for(ListNameType member : listMembers){
            Type memberType = member.getType();
            if(memberType instanceof ListType)
                errors.addAll(checkListDecError((ListType) memberType, line));

            if(memberType instanceof ClassType)
                if(! isClassDeclared((ClassType)memberType))
                    errors.add(new ClassNotDeclared(line, ((ClassType) memberType).getClassName().getName()));

            if(memberType instanceof FptrType)
                errors.addAll(checkFptrDecError((FptrType)memberType, line));
        }
        return errors;
    }

    public ArrayList<CompileErrorException> checkFptrDecError(FptrType fptrType, int line)
    {
        ArrayList<CompileErrorException> errors = new ArrayList<>();
        ArrayList<Type> retTypeAndArgTypes = new ArrayList<>(fptrType.getArgumentsTypes());
        retTypeAndArgTypes.add(fptrType.getReturnType());

        for(Type memberType : retTypeAndArgTypes){
            if(memberType instanceof ListType)
                errors.addAll(checkListDecError((ListType) memberType, line));

            if(memberType instanceof ClassType)
                if(! isClassDeclared((ClassType)memberType))
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
            ArrayList<Type> types = new ArrayList<>(fptrType.getArgumentsTypes());
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
                if(listNameType.getName().getName().equals(""))
                    continue;
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
        isLValueViolated = false;
        aloneThis = false;
        Type lType = lValue.accept(this);

        boolean isLTypeNoType = lType instanceof NoType;
        boolean isRTypeNoType = rType instanceof NoType;

        switch (operator) {
            case assign -> {
                if (! checkTypes(rType, lType, true)) {
                    binaryExpression.addError(new UnsupportedOperandType(binaryExpression.getLine(), operator.name()));
                    rType = new NoType();
                }

                if (isLValueViolated || aloneThis) {
                    binaryExpression.addError(new LeftSideNotLvalue(binaryExpression.getLine()));
                    isLValueViolated = false;
                    aloneThis = false;
                    rType = new NoType();
                }
                return rType;
            }
            case eq, neq -> {
                isLValueViolated = true;
                if (lType instanceof ListType || rType instanceof ListType) {
                    binaryExpression.addError(new UnsupportedOperandType(binaryExpression.getLine(), operator.name()));
                    return new NoType();
                }
                if (rType.toString().equals(lType.toString()))
                    return new BoolType();
                if (isLTypeNoType || isRTypeNoType)
                    return new NoType();
                if (rType instanceof NullType || lType instanceof NullType)
                    if (rType instanceof FptrType || lType instanceof FptrType || rType instanceof ClassType || lType instanceof ClassType)
                        return new BoolType();
                binaryExpression.addError(new UnsupportedOperandType(binaryExpression.getLine(), operator.name()));
                return new NoType();
            }
            case add, sub, mult, mod, div -> {
                isLValueViolated = true;
                if ((isLTypeNoType || lType instanceof IntType) && (isRTypeNoType || rType instanceof IntType)) {
                    if (isLTypeNoType || isRTypeNoType)
                        return new NoType();
                    return new IntType();
                }
                binaryExpression.addError(new UnsupportedOperandType(binaryExpression.getLine(), operator.name()));
                return new NoType();
            }
            case lt, gt -> {
                isLValueViolated = true;
                if ((isLTypeNoType || lType instanceof IntType) && (isRTypeNoType || rType instanceof IntType)) {
                    if (isLTypeNoType || isRTypeNoType)
                        return new NoType();
                    return new BoolType();
                }
                binaryExpression.addError(new UnsupportedOperandType(binaryExpression.getLine(), operator.name()));
                return new NoType();
            }
            case and, or -> {
                isLValueViolated = true;
                if ((isLTypeNoType || lType instanceof BoolType) && (isRTypeNoType || rType instanceof BoolType)) {
                    if (isLTypeNoType || isRTypeNoType)
                        return new NoType();
                    return new BoolType();
                }
                binaryExpression.addError(new UnsupportedOperandType(binaryExpression.getLine(), operator.name()));
                return new NoType();
            }
        }
        return null;
    }

    @Override
    public Type visit(UnaryExpression unaryExpression) {
        Expression operand = unaryExpression.getOperand();
        UnaryOperator operator = unaryExpression.getOperator();

        isLValueViolated = false;
        Type operandType = operand.accept(this);

        switch (operator) {
            case not -> {
                isLValueViolated = true;
                if (!(operandType instanceof BoolType || operandType instanceof NoType)) {
                    unaryExpression.addError(new UnsupportedOperandType(unaryExpression.getLine(), operator.name()));
                    operandType = new NoType();
                }
                return operandType;
            }
            case minus -> {
                isLValueViolated = true;
                if (!(operandType instanceof IntType || operandType instanceof NoType)) {
                    unaryExpression.addError(new UnsupportedOperandType(unaryExpression.getLine(), operator.name()));
                    operandType = new NoType();
                }
                return operandType;
            }
            case preinc, postinc, predec, postdec -> {
                if (!(operandType instanceof IntType || operandType instanceof NoType)) {
                    unaryExpression.addError(new UnsupportedOperandType(unaryExpression.getLine(), operator.name()));
                    operandType = new NoType();
                }

                if (isLValueViolated || aloneThis) {
                    unaryExpression.addError(new IncDecOperandNotLvalue(unaryExpression.getLine(), operator.name()));
                    operandType = new NoType();
                }
                isLValueViolated = true;
                aloneThis = false;
                return operandType;
            }
        }
        return new NoType();
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
                        isLValueViolated = true;
                        MethodSymbolTableItem methodSymbolTableItem = (MethodSymbolTableItem)symbolTableItem;
                        FptrType fptrType = makeFptrType(methodSymbolTableItem);
                        return fptrType;

                    } catch (ItemNotFoundException exMethod){
                        if(classType.getClassName().getName().equals(identifier.getName())) {
                            isLValueViolated = true;
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
                if(elementType.getName().getName().equals(""))
                    continue;
                if(elementKey.equals(elementType.getName().getName())) {
                    if(isValid(elementType.getType()))
                        return elementType.getType();
                    return new NoType();
                }
            }
            identifier.addError(new ListMemberNotFound(identifier.getLine(), identifier.getName()));
            return new NoType();
        }
        return null;
    }

    @Override
    public Type visit(ObjectOrListMemberAccess objectOrListMemberAccess) {
        Type instanceType = objectOrListMemberAccess.getInstance().accept(this);
        if (objectOrListMemberAccess.getInstance() instanceof ThisClass) {
            aloneThis = false;
        }
        Type result;
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
    public Type visit(ListAccessByIndex listAccessByIndex) {

        boolean tempIsLValueViolated, tempAloneThis;
        Type instanceType = listAccessByIndex.getInstance().accept(this);

        tempIsLValueViolated = isLValueViolated;
        tempAloneThis = aloneThis;
        Type indexType = listAccessByIndex.getIndex().accept(this);
        isLValueViolated = tempIsLValueViolated;
        aloneThis = tempAloneThis;

        boolean isConstant = listAccessByIndex.getIndex() instanceof IntValue;

        if(!(instanceType instanceof ListType || instanceType instanceof NoType) || !(indexType instanceof IntType || indexType instanceof NoType))
        {
            if(!(instanceType instanceof ListType || instanceType instanceof NoType))
                listAccessByIndex.addError(new ListAccessByIndexOnNoneList(listAccessByIndex.getLine()));

            if(!(indexType instanceof IntType || indexType instanceof NoType))
                listAccessByIndex.addError(new ListIndexNotInt(listAccessByIndex.getLine()));
        }
        if(!(instanceType instanceof ListType))
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
                return isValid(list.getElementsTypes().get(0).getType()) ? list.getElementsTypes().get(0).getType() : new NoType();
            return isValid(list.getElementsTypes().get(index).getType()) ? list.getElementsTypes().get(index).getType() : new NoType();
        }
        else{
            if(!(indexType instanceof IntType))
                return new NoType();

            return isValid(list.getElementsTypes().get(0).getType()) ? list.getElementsTypes().get(0).getType() : new NoType();
        }
    }

    @Override
    public Type visit(MethodCall methodCall) {
        isLValueViolated = true;
        boolean tempIsInMethodCallStmt;
        boolean shouldReturnNoType =false;

        tempIsInMethodCallStmt = TypeChecker.isInMethodCallStmt;
        TypeChecker.isInMethodCallStmt = false;
        Type instanceType = methodCall.getInstance().accept(this);

        if(TypeChecker.isInMethodCallStmt) {
            instanceType = new NoType();
        }
        TypeChecker.isInMethodCallStmt = tempIsInMethodCallStmt;


        ArrayList<Type> types = new ArrayList<>();
        for(Expression arg : methodCall.getArgs()) {
            tempIsInMethodCallStmt = TypeChecker.isInMethodCallStmt;
            TypeChecker.isInMethodCallStmt = false;
            Type argType = arg.accept(this);
            if(TypeChecker.isInMethodCallStmt) {
                types.add(new NoType());
            }
            else{
                types.add(argType);
            }
            TypeChecker.isInMethodCallStmt = tempIsInMethodCallStmt;
        }

        if(instanceType instanceof NoType)
            return new NoType();
        if(!(instanceType instanceof FptrType)) {
            methodCall.addError(new CallOnNoneFptrType(methodCall.getLine()));
            return new NoType();
        }

        ArrayList<Type> argTypes = ((FptrType)instanceType).getArgumentsTypes();
        Type retType = ((FptrType) instanceType).getReturnType();

        if (retType instanceof NullType && !TypeChecker.isInMethodCallStmt)
        {
            methodCall.addError(new CantUseValueOfVoidMethod(methodCall.getLine()));
            shouldReturnNoType = true;
        }

        if(types.size() != argTypes.size()) {
            methodCall.addError(new MethodCallNotMatchDefinition(methodCall.getLine()));
            return new NoType();
        }

        for (int i=0; i<types.size(); i++) {
            if(!checkTypes(types.get(i), argTypes.get(i), true)){
                methodCall.addError(new MethodCallNotMatchDefinition(methodCall.getLine()));
                return new NoType();
            }
        }
        if(shouldReturnNoType)
            return new NoType();

        return isValid(retType) ? retType : new NoType();
    }

    @Override
    public Type visit(NewClassInstance newClassInstance) {
        isLValueViolated = true;
        String _className = newClassInstance.getClassType().getClassName().getName();
        String key = ClassSymbolTableItem.START_KEY + _className;
        ArrayList<Type> types = new ArrayList<>();
        for (Expression arg: newClassInstance.getArgs()) {
            Type type = arg.accept(this);
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
        aloneThis = true;
        return new ClassType(className);
    }

    @Override
    public Type visit(ListValue listValue) {
        isLValueViolated = true;
        ListType listType = new ListType();
        for (Expression element: listValue.getElements() ) {
            Type t = element.accept(this);
            listType.addElementType(new ListNameType(t));
        }
        return listType;
    }

    @Override
    public Type visit(NullValue nullValue) {
        isLValueViolated = true;
        return new NullType();
    }

    @Override
    public Type visit(IntValue intValue) {
        isLValueViolated = true;
        return new IntType();
    }

    @Override
    public Type visit(BoolValue boolValue) {
        isLValueViolated = true;
        return new BoolType();
    }

    @Override
    public Type visit(StringValue stringValue) {
        isLValueViolated = true;
        return new StringType();
    }
}
