package main.visitor.codeGenerator;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.classDec.ClassDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.ConstructorDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.FieldDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.MethodDeclaration;
import main.ast.nodes.declaration.variableDec.VarDeclaration;
import main.ast.nodes.expression.*;
import main.ast.nodes.expression.operators.BinaryOperator;
import main.ast.nodes.expression.operators.UnaryOperator;
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
import main.ast.types.NullType;
import main.ast.types.Type;
import main.ast.types.functionPointer.FptrType;
import main.ast.types.list.ListNameType;
import main.ast.types.list.ListType;
import main.ast.types.single.BoolType;
import main.ast.types.single.ClassType;
import main.ast.types.single.IntType;
import main.ast.types.single.StringType;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.ClassSymbolTableItem;
import main.symbolTable.items.FieldSymbolTableItem;
import main.symbolTable.utils.graph.Graph;
import main.visitor.Visitor;
import main.visitor.typeChecker.ExpressionTypeChecker;

import java.io.*;
import java.util.ArrayList;

public class CodeGenerator extends Visitor<String> {
    ExpressionTypeChecker expressionTypeChecker;
    Graph<String> classHierarchy;
    private String outputPath;
    private FileWriter currentFile;
    private ClassDeclaration currentClass;
    private MethodDeclaration currentMethod;

    private int numOfUsedTemp;
    private int numOfUsedLabel;

    private String labelContinue;
    private String labelBreak;

    public CodeGenerator(Graph<String> classHierarchy) {
        this.classHierarchy = classHierarchy;
        this.expressionTypeChecker = new ExpressionTypeChecker(classHierarchy);
        this.prepareOutputFolder();
        this.numOfUsedTemp = 0 ;
        this.numOfUsedLabel = 0;
    }

    private void prepareOutputFolder() {
        this.outputPath = "output/";
        String jasminPath = "utilities/jarFiles/jasmin.jar";
        String listClassPath = "utilities/codeGenerationUtilityClasses/List.j";
        String fptrClassPath = "utilities/codeGenerationUtilityClasses/Fptr.j";
        try{
            File directory = new File(this.outputPath);
            File[] files = directory.listFiles();
            if(files != null)
                for (File file : files)
                    file.delete();
            directory.mkdir();
        }
        catch(SecurityException e) {//never reached

        }
        copyFile(jasminPath, this.outputPath + "jasmin.jar");
        copyFile(listClassPath, this.outputPath + "List.j");
        copyFile(fptrClassPath, this.outputPath + "Fptr.j");
    }

    private void copyFile(String toBeCopied, String toBePasted) {
        try {
            File readingFile = new File(toBeCopied);
            File writingFile = new File(toBePasted);
            InputStream readingFileStream = new FileInputStream(readingFile);
            OutputStream writingFileStream = new FileOutputStream(writingFile);
            byte[] buffer = new byte[1024];
            int readLength;
            while ((readLength = readingFileStream.read(buffer)) > 0)
                writingFileStream.write(buffer, 0, readLength);
            readingFileStream.close();
            writingFileStream.close();
        } catch (IOException e) {//never reached

        }
    }

    private void createFile(String name) {
        try {
            String path = this.outputPath + name + ".j";
            File file = new File(path);
            file.createNewFile();
            this.currentFile = new FileWriter(path);
        } catch (IOException e) {//never reached

        }
    }

    private void addCommand(String command) {
        try {
            command = String.join("\n\t\t", command.split("\n"));
            if(command.startsWith("Label_"))
                this.currentFile.write("\t" + command + "\n");
            else if(command.startsWith("."))
                this.currentFile.write(command + "\n");
            else
                this.currentFile.write("\t\t" + command + "\n");
            this.currentFile.flush();
        } catch (IOException e) {//never reached

        }
    }

    private String makeTypeSignature(Type t) {
        if (t instanceof IntType)
            return "java/lang/Integer";
        if (t instanceof BoolType)
            return "java/lang/Boolean";
        if (t instanceof StringType)
            return "java/lang/String";
        if (t instanceof ListType)
            return "List";
        if (t instanceof FptrType)
            return "Fptr";
        if (t instanceof ClassType)
            return ((ClassType)t).getClassName().getName();
        return null;
    }

    private String getFreshLabel(){
        String label = "Label_";
        label += numOfUsedLabel;
        numOfUsedLabel++;
        return label;
    }

    private void addDefaultConstructor() {
        String className = currentClass.getClassName().getName();

        addCommand(".method public <init>()V");
        addCommand(".limit stack 128");
        addCommand(".limit locals 128");
        addCommand("aload 0");

        if (currentClass.getParentClassName() == null){
            addCommand("invokespecial java/lang/Object/<init>()V");
        }
        else{
            String parentClassName = currentClass.getParentClassName().getName();
            addCommand("invokespecial " + parentClassName + "/<init>()V");
        }


        for(FieldDeclaration field : currentClass.getFields()){
            String fieldName = field.getVarDeclaration().getVarName().getName();
            Type fieldType = field.getVarDeclaration().getType();

            if(fieldType instanceof ClassType || fieldType instanceof FptrType){
                addCommand("aload 0");
                addCommand("aconst_null");
                addCommand("putfield " + className + "/" + fieldName + " L" + makeTypeSignature(fieldType) + ";\n");
            }
            else if(fieldType instanceof IntType || fieldType instanceof BoolType){
                addCommand("aload 0");
                addCommand("ldc 0");
                if(fieldType instanceof IntType)
                    addCommand("invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;");
                if(fieldType instanceof BoolType)
                    addCommand("invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;");
                addCommand("putfield " + className + "/" + fieldName + " L" + makeTypeSignature(fieldType) + ";\n");
            }
            else if(fieldType instanceof StringType){
                addCommand("aload 0");
                addCommand("ldc \"\"");
                addCommand("putfield " + className + "/" + fieldName + " L" + makeTypeSignature(fieldType) + ";\n");
            }
            else{
                addCommand("aload 0");
                initializeList((ListType) fieldType);
                addCommand("putfield " + className + "/" + fieldName + " L" + makeTypeSignature(fieldType) + ";\n");
            }
        }
        addCommand("return");
        addCommand(".end method");
    }

    private void addStaticMainMethod() {
        addCommand(".method public static main([Ljava/lang/String;)V");
        addCommand(".limit stack 128");
        addCommand(".limit locals 128");


        addCommand("new Main");
        addCommand("invokespecial Main/<init>()V");

        addCommand("return");
        addCommand(".end method");
    }

    private int slotOf(String identifier) {
        int count = 1;
        for(VarDeclaration arg : currentMethod.getArgs()){
            if(arg.getVarName().getName().equals(identifier))
                return count;
            count++;
        }
        for(VarDeclaration var : currentMethod.getLocalVars())
        {
            if(var.getVarName().getName().equals(identifier))
                return count;
            count++;
        }
        if (identifier.equals("")){
            int temp = numOfUsedTemp;
            numOfUsedTemp++;
            return count + temp;
        }
        return 0;
    }

    private void initializeList(ListType listType) {
        addCommand("new List");
        addCommand("dup");
        addCommand("new java/util/ArrayList");
        addCommand("dup");
        addCommand("invokespecial java/util/ArrayList/<init>()V");

        for (ListNameType element : listType.getElementsTypes()) {
            addCommand("dup");

            if(element.getType() instanceof ClassType || element.getType() instanceof FptrType){
                addCommand("aconst_null");
                addCommand("invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z");
            }
            else if(element.getType() instanceof IntType || element.getType() instanceof BoolType){
                addCommand("ldc 0");
                if(element.getType() instanceof IntType)
                    addCommand("invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;");
                if(element.getType() instanceof BoolType)
                    addCommand("invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;");
                addCommand("invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z");
            }
            else if(element.getType() instanceof StringType){
                addCommand("ldc \"\"");
                addCommand("invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z");
            }
            else{
                initializeList((ListType) element.getType());
                addCommand("invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z");
            }

            addCommand("pop");
        }
        addCommand("invokespecial List/<init>(Ljava/util/ArrayList;)V");
    }

    @Override
    public String visit(Program program) {
        for(ClassDeclaration classDeclaration : program.getClasses())
        {
            currentClass = classDeclaration;
            expressionTypeChecker.setCurrentClass(classDeclaration);
            classDeclaration.accept(this);
        }
        return null;
    }

    @Override
    public String visit(ClassDeclaration classDeclaration) {
        currentClass = classDeclaration;
        String className = classDeclaration.getClassName().getName();
        createFile(className);
        addCommand(".class " + className);
        if (classDeclaration.getParentClassName() == null)
            addCommand(".super java/lang/Object");
        else{
            String parentClassName = classDeclaration.getParentClassName().getName();
            addCommand(".super " + parentClassName);
        }

        for(FieldDeclaration field : classDeclaration.getFields())
        {
            field.accept(this);
        }

        if(classDeclaration.getConstructor() != null)
        {
            expressionTypeChecker.setCurrentMethod(classDeclaration.getConstructor());
            currentMethod = classDeclaration.getConstructor();
            classDeclaration.getConstructor().accept(this);
        }
        else{
            addDefaultConstructor();
        }

        for(MethodDeclaration method : classDeclaration.getMethods())
        {
            expressionTypeChecker.setCurrentMethod(method);
            currentMethod = method;
            method.accept(this);
        }
        return null;
    }

    @Override
    public String visit(ConstructorDeclaration constructorDeclaration) {

        if (constructorDeclaration.getArgs().size() > 0)
            addDefaultConstructor();

        if (currentClass.getClassName().getName().equals("Main"))
            addStaticMainMethod();


        this.visit((MethodDeclaration) constructorDeclaration);
        return null;
    }

    @Override
    public String visit(MethodDeclaration methodDeclaration) {

        String header = "";
        String className = currentClass.getClassName().getName();
        if (methodDeclaration instanceof ConstructorDeclaration){
            header += ".method public <init>(";
            for(VarDeclaration arg : methodDeclaration.getArgs()){
                header += "L" + makeTypeSignature(arg.getType()) + ";";
            }
            header += ")V";
        }
        else{
            header += ".method public " + methodDeclaration.getMethodName().getName() + "(";
            for(VarDeclaration arg : methodDeclaration.getArgs()){
                header += "L" + makeTypeSignature(arg.getType()) + ";";
            }
            if (methodDeclaration.getReturnType() instanceof NullType)
                header += ")V";
            else
                header += ")L"  + makeTypeSignature(methodDeclaration.getReturnType()) + ";";
        }

        addCommand(header);
        addCommand(".limit stack 128");
        addCommand(".limit locals 128");

        if(methodDeclaration instanceof ConstructorDeclaration) {

            if (currentClass.getParentClassName() == null){
                addCommand("aload 0");
                addCommand("invokespecial java/lang/Object/<init>()V");
            }
            else{
                String parentClassName = currentClass.getParentClassName().getName();
                addCommand("aload 0");
                addCommand("invokespecial " + parentClassName + "/<init>()V");
            }

            for(FieldDeclaration field : currentClass.getFields()){
                String fieldName = field.getVarDeclaration().getVarName().getName();
                Type fieldType = field.getVarDeclaration().getType();

                if(fieldType instanceof ClassType || fieldType instanceof FptrType){
                    addCommand("aload 0");
                    addCommand("aconst_null");
                    addCommand("putfield " + className + "/" + fieldName + " L" + makeTypeSignature(fieldType) + ";\n");
                }
                else if(fieldType instanceof IntType || fieldType instanceof BoolType){
                    addCommand("aload 0");
                    addCommand("ldc 0");
                    if(fieldType instanceof IntType)
                        addCommand("invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;");
                    if(fieldType instanceof BoolType)
                        addCommand("invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;");
                    addCommand("putfield " + className + "/" + fieldName + " L" + makeTypeSignature(fieldType) + ";\n");
                }
                else if(fieldType instanceof StringType){
                    addCommand("aload 0");
                    addCommand("ldc \"\"");
                    addCommand("putfield " + className + "/" + fieldName + " L" + makeTypeSignature(fieldType) + ";\n");
                }
                else{
                    addCommand("aload 0");
                    initializeList((ListType) fieldType);
                    addCommand("putfield " + className + "/" + fieldName + " L" + makeTypeSignature(fieldType) + ";\n");
                }
            }

        }

        for(VarDeclaration var : methodDeclaration.getLocalVars()){
            var.accept(this);
        }

        for(Statement stmt : methodDeclaration.getBody()){
            stmt.accept(this);
        }
        if(!methodDeclaration.getDoesReturn())
            addCommand("return");

        addCommand(".end method");

        numOfUsedTemp = 0;
        return null;
    }

    @Override
    public String visit(FieldDeclaration fieldDeclaration) {
        String fieldName = fieldDeclaration.getVarDeclaration().getVarName().getName();
        Type fieldType = fieldDeclaration.getVarDeclaration().getType();
        String signature = makeTypeSignature(fieldType);
        addCommand(".field " + fieldName + " L" + signature + ";");
        return null;
    }

    @Override
    public String visit(VarDeclaration varDeclaration) {
        int slot = slotOf(varDeclaration.getVarName().getName());
        Type type = varDeclaration.getType();

        if(type instanceof ClassType || type instanceof FptrType){
            addCommand("aconst_null");
            addCommand("astore " + slot);
        }
        else if(type instanceof IntType || type instanceof BoolType){
            addCommand("ldc 0");
            if(type instanceof IntType)
                addCommand("invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;");
            if(type instanceof BoolType)
                addCommand("invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;");
            addCommand("astore " + slot);
        }
        else if(type instanceof StringType){
            addCommand("ldc \"\"");
            addCommand("astore " + slot);
        }
        else{
            initializeList((ListType) type);
            addCommand("astore " + slot);
        }
        return null;
    }

    @Override
    public String visit(AssignmentStmt assignmentStmt) {
        BinaryExpression assignExpr = new BinaryExpression(assignmentStmt.getlValue(), assignmentStmt.getrValue(), BinaryOperator.assign);
        addCommand(assignExpr.accept(this));
        addCommand("pop");
        return null;
    }

    @Override
    public String visit(BlockStmt blockStmt) {
        for (Statement statement: blockStmt.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public String visit(ConditionalStmt conditionalStmt) {
        String labelFalse = getFreshLabel();
        String labelAfter = getFreshLabel();
        addCommand(conditionalStmt.getCondition().accept(this));
        addCommand("ifeq " + labelFalse);
        conditionalStmt.getThenBody().accept(this);
        addCommand("goto " + labelAfter);
        addCommand(labelFalse + ":");
        if (conditionalStmt.getElseBody() != null)
            conditionalStmt.getElseBody().accept(this);
        addCommand(labelAfter + ":");
        return null;
    }

    @Override
    public String visit(MethodCallStmt methodCallStmt) {
        expressionTypeChecker.setIsInMethodCallStmt(true);
        addCommand(methodCallStmt.getMethodCall().accept(this));
        addCommand("pop");
        expressionTypeChecker.setIsInMethodCallStmt(false);
        return null;
    }

    @Override
    public String visit(PrintStmt print) {
        addCommand("getstatic java/lang/System/out Ljava/io/PrintStream;");
        Type argType = print.getArg().accept(expressionTypeChecker);
        addCommand(print.getArg().accept(this));
        if (argType instanceof IntType)
            addCommand("invokevirtual java/io/PrintStream/print(I)V");
        if (argType instanceof BoolType)
            addCommand("invokevirtual java/io/PrintStream/print(Z)V");
        if (argType instanceof StringType)
            addCommand("invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V");
        return null;
    }

    @Override
    public String visit(ReturnStmt returnStmt) {
        Type type = returnStmt.getReturnedExpr().accept(expressionTypeChecker);
        if(type instanceof NullType) {
            addCommand("return");
        }
        else {
            addCommand( returnStmt.getReturnedExpr().accept(this) );
            if(type instanceof IntType)
                addCommand("invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;");
            if(type instanceof BoolType)
                addCommand("invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;");
            addCommand("areturn");
        }
        return null;
    }

    @Override
    public String visit(BreakStmt breakStmt) {
        addCommand("goto " + labelBreak);
        return null;
    }


    @Override
    public String visit(ContinueStmt continueStmt) {
        addCommand("goto " + labelContinue);
        return null;
    }

    @Override
    public String visit(ForeachStmt foreachStmt) {
        int tempIndex = slotOf("");
        int iteratorSlot = slotOf(foreachStmt.getVariable().getName());
        Type iteratorType = foreachStmt.getVariable().accept(expressionTypeChecker);

        ListType listType = (ListType) foreachStmt.getList().accept(expressionTypeChecker);
        int listSize = listType.getElementsTypes().size();

        String labelStart = getFreshLabel();
        String labelAfter = getFreshLabel();
        String labelUpdate = getFreshLabel();

        String labelTempContinue = labelContinue;
        String labelTempBreak = labelBreak;

        labelContinue = labelUpdate;
        labelBreak = labelAfter;

        addCommand(foreachStmt.getList().accept(this));

        addCommand("ldc 0");
        addCommand("istore " + tempIndex);

        addCommand(labelStart + ":");

        addCommand("iload " + tempIndex);
        addCommand("ldc " + listSize);
        addCommand("if_icmpge " + labelAfter);

        addCommand("dup");

        addCommand("iload " + tempIndex);
        addCommand("invokevirtual List/getElement(I)Ljava/lang/Object;\n");
        addCommand("checkcast " + makeTypeSignature(iteratorType) + "\n");
        addCommand("astore " + iteratorSlot);

        foreachStmt.getBody().accept(this);


        addCommand(labelUpdate + ":");
        addCommand("iload " + tempIndex);
        addCommand("ldc 1");
        addCommand("iadd");
        addCommand("istore " + tempIndex);
        
        addCommand("goto " + labelStart);
        addCommand(labelAfter + ":");
        addCommand("pop");

        labelContinue = labelTempContinue;
        labelBreak = labelTempBreak;
        return null;
    }

    @Override
    public String visit(ForStmt forStmt) {

        String labelStart = getFreshLabel();
        String labelAfter = getFreshLabel();
        String labelUpdate = getFreshLabel();

        String labelTempContinue = labelContinue;
        String labelTempBreak = labelBreak;

        labelContinue = labelUpdate;
        labelBreak = labelAfter;

        if (forStmt.getInitialize() != null)
            forStmt.getInitialize().accept(this);

        addCommand(labelStart + ":");

        if (forStmt.getCondition() != null) {
            addCommand(forStmt.getCondition().accept(this));
            addCommand("ifeq " + labelAfter);
        }

        forStmt.getBody().accept(this);

        addCommand(labelUpdate + ":");
        if(forStmt.getUpdate() != null)
            forStmt.getUpdate().accept(this);

        addCommand("goto " + labelStart);
        addCommand(labelAfter + ":");

        labelContinue = labelTempContinue;
        labelBreak = labelTempBreak;
        return null;
    }

    @Override
    public String visit(BinaryExpression binaryExpression) {
        BinaryOperator operator = binaryExpression.getBinaryOperator();
        Type operandType = binaryExpression.getFirstOperand().accept(expressionTypeChecker);
        String commands = "";
        if (operator == BinaryOperator.add) {
            commands += binaryExpression.getFirstOperand().accept(this);
            commands += binaryExpression.getSecondOperand().accept(this);
            commands += "iadd\n";
        }
        else if (operator == BinaryOperator.sub) {
            commands += binaryExpression.getFirstOperand().accept(this);
            commands += binaryExpression.getSecondOperand().accept(this);
            commands += "isub\n";
        }
        else if (operator == BinaryOperator.mult) {
            commands += binaryExpression.getFirstOperand().accept(this);
            commands += binaryExpression.getSecondOperand().accept(this);
            commands += "imul\n";
        }
        else if (operator == BinaryOperator.div) {
            commands += binaryExpression.getFirstOperand().accept(this);
            commands += binaryExpression.getSecondOperand().accept(this);
            commands += "idiv\n";
        }
        else if (operator == BinaryOperator.mod) {
            commands += binaryExpression.getFirstOperand().accept(this);
            commands += binaryExpression.getSecondOperand().accept(this);
            commands += "irem\n";
        }
        else if((operator == BinaryOperator.gt) || (operator == BinaryOperator.lt)) {
            commands += binaryExpression.getFirstOperand().accept(this);
            commands += binaryExpression.getSecondOperand().accept(this);
            String labelFalse = getFreshLabel();
            String labelAfter = getFreshLabel();
            if(operator == BinaryOperator.gt)
                commands += "if_icmple " + labelFalse + "\n";
            else
                commands += "if_icmpge " + labelFalse + "\n";
            commands += "ldc " + "1\n";
            commands += "goto " + labelAfter + "\n";
            commands += labelFalse + ":\n";
            commands += "ldc " + "0\n";
            commands += labelAfter + ":\n";
        }
        else if((operator == BinaryOperator.eq) || (operator == BinaryOperator.neq)) {
            commands += binaryExpression.getFirstOperand().accept(this);
            commands += binaryExpression.getSecondOperand().accept(this);
            String labelFalse = getFreshLabel();
            String labelAfter = getFreshLabel();
            if(operator == BinaryOperator.eq){
                if (!(operandType instanceof IntType) && !(operandType instanceof BoolType))
                    commands += "if_acmpne " + labelFalse + "\n";
                else
                    commands += "if_icmpne " + labelFalse + "\n";
            }
            else{
                if (!(operandType instanceof IntType) && !(operandType instanceof BoolType))
                    commands += "if_acmpeq " + labelFalse + "\n";
                else
                    commands += "if_icmpeq " + labelFalse + "\n";

            }
            commands += "ldc " + "1\n";
            commands += "goto " + labelAfter + "\n";
            commands += labelFalse + ":\n";
            commands += "ldc " + "0\n";
            commands += labelAfter + ":\n";
        }
        else if(operator == BinaryOperator.and) {
            String labelFalse = getFreshLabel();
            String labelAfter = getFreshLabel();
            commands += binaryExpression.getFirstOperand().accept(this);
            commands += "ifeq " + labelFalse + "\n";
            commands += binaryExpression.getSecondOperand().accept(this);
            commands += "ifeq " + labelFalse + "\n";
            commands += "ldc " + "1\n";
            commands += "goto " + labelAfter + "\n";
            commands += labelFalse + ":\n";
            commands += "ldc " + "0\n";
            commands += labelAfter + ":\n";
        }
        else if(operator == BinaryOperator.or) {
            String labelTrue = getFreshLabel();
            String labelAfter = getFreshLabel();
            commands += binaryExpression.getFirstOperand().accept(this);
            commands += "ifne " + labelTrue + "\n";
            commands += binaryExpression.getSecondOperand().accept(this);
            commands += "ifne " + labelTrue + "\n";
            commands += "ldc " + "0\n";
            commands += "goto " + labelAfter + "\n";
            commands += labelTrue + ":\n";
            commands += "ldc " + "1\n";
            commands += labelAfter + ":\n";
        }
        else if(operator == BinaryOperator.assign) {
            Type firstType = binaryExpression.getFirstOperand().accept(expressionTypeChecker);
            Type secondType = binaryExpression.getSecondOperand().accept(expressionTypeChecker);
            String secondOperandCommands = binaryExpression.getSecondOperand().accept(this);
            if(firstType instanceof ListType) {
                secondOperandCommands = "new List\ndup\n" + secondOperandCommands + "invokespecial List/<init>(LList;)V\n";
            }

            if(secondType instanceof IntType)
                secondOperandCommands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";
            if(secondType instanceof BoolType)
                secondOperandCommands += "invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;\n";


            if(binaryExpression.getFirstOperand() instanceof Identifier) {
                Identifier identifier = (Identifier)binaryExpression.getFirstOperand();
                int slot = slotOf(identifier.getName());
                commands += secondOperandCommands;
                commands += "astore " + slot + "\n";
                commands += "aload " + slot + "\n";
                if (secondType instanceof IntType)
                    commands += "invokevirtual java/lang/Integer/intValue()I\n";
                if (secondType instanceof BoolType)
                    commands += "invokevirtual java/lang/Boolean/booleanValue()Z\n";
            }
            else if(binaryExpression.getFirstOperand() instanceof ListAccessByIndex) {
                Expression instance = ((ListAccessByIndex) binaryExpression.getFirstOperand()).getInstance();
                Expression index = ((ListAccessByIndex) binaryExpression.getFirstOperand()).getIndex();
                commands += instance.accept(this);
                commands += index.accept(this);
                commands += secondOperandCommands;
                commands += "invokevirtual List/setElement(ILjava/lang/Object;)V\n";

                commands += instance.accept(this);
                commands += index.accept(this);
                commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
                commands += "checkcast " + makeTypeSignature(secondType) + "\n";
                if (secondType instanceof IntType)
                    commands += "invokevirtual java/lang/Integer/intValue()I\n";
                if (secondType instanceof BoolType)
                    commands += "invokevirtual java/lang/Boolean/booleanValue()Z\n";


            }
            else if(binaryExpression.getFirstOperand() instanceof ObjectOrListMemberAccess) {
                Expression instance = ((ObjectOrListMemberAccess) binaryExpression.getFirstOperand()).getInstance();
                Type memberType = binaryExpression.getFirstOperand().accept(expressionTypeChecker);
                String memberName = ((ObjectOrListMemberAccess) binaryExpression.getFirstOperand()).getMemberName().getName();
                Type instanceType = instance.accept(expressionTypeChecker);
                if(instanceType instanceof ListType) {
                    int index = 0;
                    ListType listType = (ListType)instanceType;
                    for(ListNameType listNameType : listType.getElementsTypes()){
                        if(listNameType.getName() == null){
                            index++;
                            continue;
                        }
                        if(listNameType.getName().getName().equals(memberName))
                            break;
                        index++;
                    }
                    commands += instance.accept(this);
                    commands += "ldc " + index + "\n";
                    commands += secondOperandCommands;
                    commands += "invokevirtual List/setElement(ILjava/lang/Object;)V\n";

                    commands += instance.accept(this);
                    commands += "ldc " + index + "\n";
                    commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
                    commands += "checkcast " + makeTypeSignature(secondType) + "\n";
                    if (secondType instanceof IntType)
                        commands += "invokevirtual java/lang/Integer/intValue()I\n";
                    if (secondType instanceof BoolType)
                        commands += "invokevirtual java/lang/Boolean/booleanValue()Z\n";

                }
                else if(instanceType instanceof ClassType) {
                    String className = ((ClassType)instanceType).getClassName().getName();
                    commands += instance.accept(this);
                    commands += secondOperandCommands;
                    commands += "putfield " + className + "/" + memberName + " L" + makeTypeSignature(memberType) + ";\n";

                    commands += instance.accept(this);
                    commands += "getfield " + className + "/" + memberName + " L" + makeTypeSignature(memberType) + ";\n";
                    if (secondType instanceof IntType)
                        commands += "invokevirtual java/lang/Integer/intValue()I\n";
                    if (secondType instanceof BoolType)
                        commands += "invokevirtual java/lang/Boolean/booleanValue()Z\n";
                }
            }
        }
        return commands;
    }

    @Override
    public String visit(UnaryExpression unaryExpression) {
        UnaryOperator operator = unaryExpression.getOperator();
        String commands = "";
        if(operator == UnaryOperator.minus) {
            commands += unaryExpression.getOperand().accept(this);
            commands += "ineg\n";
        }
        else if(operator == UnaryOperator.not) {
            String labelTrue = getFreshLabel();
            String labelAfter = getFreshLabel();
            commands += unaryExpression.getOperand().accept(this);
            commands +=  "ifne " + labelTrue + "\n";
            commands += "ldc " + "1\n";
            commands += "goto " + labelAfter + "\n";
            commands += labelTrue + ":\n";
            commands += "ldc " + "0\n";
            commands += labelAfter + ":\n";
        }
        else if((operator == UnaryOperator.predec) || (operator == UnaryOperator.preinc)) {
            if(unaryExpression.getOperand() instanceof Identifier) {
                Identifier identifier = (Identifier)unaryExpression.getOperand();
                int slot = slotOf(identifier.getName());

                commands += "aload " + slot + "\n";
                commands += "invokevirtual java/lang/Integer/intValue()I\n";
                commands += "ldc 1\n";

                if (operator == UnaryOperator.preinc)
                    commands += "iadd\n";
                else
                    commands += "isub\n";

                commands += "dup\n";
                commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";
                commands += "astore " + slot + "\n";
            }
            else if(unaryExpression.getOperand() instanceof ListAccessByIndex) {
                Expression instance = ((ListAccessByIndex) unaryExpression.getOperand()).getInstance();
                Expression index = ((ListAccessByIndex) unaryExpression.getOperand()).getIndex();
                Type memberType = unaryExpression.getOperand().accept(expressionTypeChecker);

                commands += instance.accept(this);
                commands += index.accept(this);

                commands += instance.accept(this);
                commands += index.accept(this);

                commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
                commands += "checkcast " + makeTypeSignature(memberType) + "\n";
                commands += "invokevirtual java/lang/Integer/intValue()I\n";
                commands += "ldc 1\n";

                if (operator == UnaryOperator.preinc)
                    commands += "iadd\n";
                else
                    commands += "isub\n";


                commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";

                commands += "invokevirtual List/setElement(ILjava/lang/Object;)V\n";

                commands += instance.accept(this);
                commands += index.accept(this);

                commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
                commands += "checkcast " + makeTypeSignature(memberType) + "\n";
                commands += "invokevirtual java/lang/Integer/intValue()I\n";
            }
            else if(unaryExpression.getOperand() instanceof ObjectOrListMemberAccess) {
                Expression instance = ((ObjectOrListMemberAccess) unaryExpression.getOperand()).getInstance();
                Type memberType = unaryExpression.getOperand().accept(expressionTypeChecker);
                String memberName = ((ObjectOrListMemberAccess) unaryExpression.getOperand()).getMemberName().getName();
                Type instanceType = instance.accept(expressionTypeChecker);
                if(instanceType instanceof ListType) {
                    int index = 0;
                    ListType listType = (ListType)instanceType;
                    for(ListNameType listNameType : listType.getElementsTypes()){
                        if(listNameType.getName() == null){
                            index++;
                            continue;
                        }
                        if(listNameType.getName().getName().equals(memberName))
                            break;
                        index++;
                    }
                    commands += instance.accept(this);
                    commands += "ldc " + index + "\n";

                    commands += instance.accept(this);
                    commands += "ldc " + index + "\n";

                    commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
                    commands += "checkcast " + makeTypeSignature(memberType) + "\n";
                    commands += "invokevirtual java/lang/Integer/intValue()I\n";
                    commands += "ldc 1\n";

                    if (operator == UnaryOperator.preinc)
                        commands += "iadd\n";
                    else
                        commands += "isub\n";


                    commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";

                    commands += "invokevirtual List/setElement(ILjava/lang/Object;)V\n";

                    commands += instance.accept(this);
                    commands += "ldc " + index + "\n";

                    commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
                    commands += "checkcast " + makeTypeSignature(memberType) + "\n";
                    commands += "invokevirtual java/lang/Integer/intValue()I\n";
                }
                else if(instanceType instanceof ClassType) {
                    String className = ((ClassType)instanceType).getClassName().getName();
                    commands += instance.accept(this);
                    commands += "dup\n";
                    commands += "getfield " + className + "/" + memberName + " L" + makeTypeSignature(memberType) + ";\n";
                    commands += "invokevirtual java/lang/Integer/intValue()I\n";
                    commands += "ldc 1\n";

                    if (operator == UnaryOperator.preinc)
                        commands += "iadd\n";
                    else
                        commands += "isub\n";

                    commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";
                    commands += "putfield " + className + "/" + memberName + " L" + makeTypeSignature(memberType) + ";\n";

                    commands += instance.accept(this);
                    commands += "getfield " + className + "/" + memberName + " L" + makeTypeSignature(memberType) + ";\n";
                    commands += "invokevirtual java/lang/Integer/intValue()I\n";
                }
            }
        }
        else if((operator == UnaryOperator.postdec) || (operator == UnaryOperator.postinc)) {
            if(unaryExpression.getOperand() instanceof Identifier) {
                Identifier identifier = (Identifier)unaryExpression.getOperand();
                int slot = slotOf(identifier.getName());

                commands += "aload " + slot + "\n";
                commands += "invokevirtual java/lang/Integer/intValue()I\n";
                commands += "dup\n";
                commands += "ldc 1\n";

                if (operator == UnaryOperator.postinc)
                    commands += "iadd\n";
                else
                    commands += "isub\n";

                commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";
                commands += "astore " + slot + "\n";
            }
            else if(unaryExpression.getOperand() instanceof ListAccessByIndex) {
                Expression instance = ((ListAccessByIndex) unaryExpression.getOperand()).getInstance();
                Expression index = ((ListAccessByIndex) unaryExpression.getOperand()).getIndex();
                Type memberType = unaryExpression.getOperand().accept(expressionTypeChecker);

                commands += instance.accept(this);
                commands += index.accept(this);

                commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
                commands += "checkcast " + makeTypeSignature(memberType) + "\n";
                commands += "invokevirtual java/lang/Integer/intValue()I\n";

                commands += instance.accept(this);
                commands += index.accept(this);

                commands += instance.accept(this);
                commands += index.accept(this);

                commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
                commands += "checkcast " + makeTypeSignature(memberType) + "\n";
                commands += "invokevirtual java/lang/Integer/intValue()I\n";
                commands += "ldc 1\n";

                if (operator == UnaryOperator.postinc)
                    commands += "iadd\n";
                else
                    commands += "isub\n";


                commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";

                commands += "invokevirtual List/setElement(ILjava/lang/Object;)V\n";
            }
            else if(unaryExpression.getOperand() instanceof ObjectOrListMemberAccess) {
                Expression instance = ((ObjectOrListMemberAccess) unaryExpression.getOperand()).getInstance();
                Type memberType = unaryExpression.getOperand().accept(expressionTypeChecker);
                String memberName = ((ObjectOrListMemberAccess) unaryExpression.getOperand()).getMemberName().getName();
                Type instanceType = instance.accept(expressionTypeChecker);
                if(instanceType instanceof ListType) {
                    int index = 0;
                    ListType listType = (ListType)instanceType;
                    for(ListNameType listNameType : listType.getElementsTypes()){
                        if(listNameType.getName() == null){
                            index++;
                            continue;
                        }
                        if(listNameType.getName().getName().equals(memberName))
                            break;
                        index++;
                    }

                    commands += instance.accept(this);
                    commands += "ldc " + index + "\n";

                    commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
                    commands += "checkcast " + makeTypeSignature(memberType) + "\n";
                    commands += "invokevirtual java/lang/Integer/intValue()I\n";

                    commands += instance.accept(this);
                    commands += "ldc " + index + "\n";

                    commands += instance.accept(this);
                    commands += "ldc " + index + "\n";

                    commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
                    commands += "checkcast " + makeTypeSignature(memberType) + "\n";
                    commands += "invokevirtual java/lang/Integer/intValue()I\n";
                    commands += "ldc 1\n";

                    if (operator == UnaryOperator.postinc)
                        commands += "iadd\n";
                    else
                        commands += "isub\n";


                    commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";

                    commands += "invokevirtual List/setElement(ILjava/lang/Object;)V\n";
                }
                else if(instanceType instanceof ClassType) {
                    String className = ((ClassType)instanceType).getClassName().getName();
                    commands += instance.accept(this);
                    commands += "getfield " + className + "/" + memberName + " L" + makeTypeSignature(memberType) + ";\n";
                    commands += "invokevirtual java/lang/Integer/intValue()I\n";

                    commands += instance.accept(this);
                    commands += "dup\n";
                    commands += "getfield " + className + "/" + memberName + " L" + makeTypeSignature(memberType) + ";\n";
                    commands += "invokevirtual java/lang/Integer/intValue()I\n";

                    commands += "ldc 1\n";

                    if (operator == UnaryOperator.postinc)
                        commands += "iadd\n";
                    else
                        commands += "isub\n";

                    commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";
                    commands += "putfield " + className + "/" + memberName + " L" + makeTypeSignature(memberType) + ";\n";
                }
            }
        }
        return commands;
    }

    @Override
    public String visit(ObjectOrListMemberAccess objectOrListMemberAccess) {
        Type memberType = objectOrListMemberAccess.accept(expressionTypeChecker);
        Type instanceType = objectOrListMemberAccess.getInstance().accept(expressionTypeChecker);
        String memberName = objectOrListMemberAccess.getMemberName().getName();
        String commands = "";
        if(instanceType instanceof ClassType) {
            String className = ((ClassType) instanceType).getClassName().getName();
            try {
                SymbolTable classSymbolTable = ((ClassSymbolTableItem) SymbolTable.root.getItem(ClassSymbolTableItem.START_KEY + className, true)).getClassSymbolTable();
                try {
                    classSymbolTable.getItem(FieldSymbolTableItem.START_KEY + memberName, true);
                    commands += objectOrListMemberAccess.getInstance().accept(this);
                    commands += "getfield " + className + "/" + memberName + " L" + makeTypeSignature(memberType) + ";\n";
                    if (memberType instanceof IntType)
                        commands += "invokevirtual java/lang/Integer/intValue()I\n";
                    if (memberType instanceof BoolType)
                        commands += "invokevirtual java/lang/Boolean/booleanValue()Z\n";

                } catch (ItemNotFoundException memberIsMethod) {
                    commands += "new Fptr\n";
                    commands += "dup\n";
                    commands += objectOrListMemberAccess.getInstance().accept(this);
                    commands += "ldc \"" + memberName + "\"\n";
                    commands += "invokespecial Fptr/<init>(Ljava/lang/Object;Ljava/lang/String;)V\n";
                }
            } catch (ItemNotFoundException classNotFound) { // never reached
            }
        }
        else if(instanceType instanceof ListType) {
            int index = 0;
            ListType listType = (ListType)instanceType;
            for(ListNameType listNameType : listType.getElementsTypes()){
                if(listNameType.getName() == null){
                    index++;
                    continue;
                }
                if(listNameType.getName().getName().equals(memberName))
                    break;
                index++;
            }
            commands += objectOrListMemberAccess.getInstance().accept(this);
            commands += "ldc " + index + "\n";
            commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";

            commands += "checkcast " + makeTypeSignature(memberType) + "\n";

            if (memberType instanceof IntType)
                commands += "invokevirtual java/lang/Integer/intValue()I\n";
            if (memberType instanceof BoolType)
                commands += "invokevirtual java/lang/Boolean/booleanValue()Z\n";
        }
        return commands;
    }

    @Override
    public String visit(Identifier identifier) {
        String commands = "";
        String name = identifier.getName();
        int slotNum = slotOf(name);
        Type type = identifier.accept(expressionTypeChecker);
        commands += "aload " + slotNum + "\n";

        if(type instanceof IntType)
            commands += "invokevirtual java/lang/Integer/intValue()I\n";
        if(type instanceof BoolType)
            commands += "invokevirtual java/lang/Boolean/booleanValue()Z\n";
        return commands;
    }

    @Override
    public String visit(ListAccessByIndex listAccessByIndex) {
        String commands = "";
        Type type = listAccessByIndex.accept(expressionTypeChecker);
        commands += listAccessByIndex.getInstance().accept(this);
        commands += listAccessByIndex.getIndex().accept(this);
        commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";

        commands += "checkcast " + makeTypeSignature(type) + "\n";

        if (type instanceof IntType)
            commands += "invokevirtual java/lang/Integer/intValue()I\n";
        if (type instanceof BoolType)
            commands += "invokevirtual java/lang/Boolean/booleanValue()Z\n";
        return commands;
    }

    @Override
    public String visit(MethodCall methodCall) {
        String commands = "";
        int tempIndex = slotOf("");
        ArrayList<Expression> args = methodCall.getArgs();
        Type retType = ((FptrType) methodCall.getInstance().accept(expressionTypeChecker)).getReturnType();
        commands += methodCall.getInstance().accept(this);
        commands += "new java/util/ArrayList\n";
        commands += "dup\n";
        commands += "invokespecial java/util/ArrayList/<init>()V\n";
        commands += "astore " + tempIndex + "\n";


        for(Expression arg : args){
            commands += "aload " + tempIndex + "\n";

            Type argType = arg.accept(expressionTypeChecker);

            if(argType instanceof ListType) {
                commands += "new List\n";
                commands += "dup\n";
            }

            commands += arg.accept(this);

            if(argType instanceof IntType)
                commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";

            if(argType instanceof BoolType)
                commands += "invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;\n";

            if(argType instanceof ListType) {
                commands += "invokespecial List/<init>(LList;)V\n";
            }

            commands += "invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z\n";
            commands += "pop\n";
        }

        commands += "aload " + tempIndex + "\n";
        commands += "invokevirtual Fptr/invoke(Ljava/util/ArrayList;)Ljava/lang/Object;\n";

        if(!(retType instanceof NullType))
            commands += "checkcast " + makeTypeSignature(retType) + "\n";

        if (retType instanceof IntType)
            commands += "invokevirtual java/lang/Integer/intValue()I\n";
        if (retType instanceof BoolType)
            commands += "invokevirtual java/lang/Boolean/booleanValue()Z\n";
        return commands;
    }

    @Override
    public String visit(NewClassInstance newClassInstance) {
        String commands = "";
        String className = newClassInstance.getClassType().getClassName().getName();
        String argsSignature = "";
        ArrayList<Expression> args = newClassInstance.getArgs();

        commands += "new " + className + "\n";
        commands += "dup\n";
        for(Expression arg : args){
            commands += arg.accept(this);
            Type argType = arg.accept(expressionTypeChecker);
            argsSignature += "L" + makeTypeSignature(argType) + ";";
            if(argType instanceof IntType)
                commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";
            if(argType instanceof BoolType)
                commands += "invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;\n";
        }
        commands += "invokespecial " + className + "/<init>(" + argsSignature + ")V\n";
        return commands;
    }

    @Override
    public String visit(ThisClass thisClass) {
        String commands = "";
        commands += "aload 0\n";
        return commands;
    }

    @Override
    public String visit(ListValue listValue) {
        String commands = "";
        int tempIndex = slotOf("");
        commands += "new List\n";
        commands += "dup\n";
        commands += "new java/util/ArrayList\n";
        commands += "dup\n";
        commands += "invokespecial java/util/ArrayList/<init>()V\n";
        commands += "astore " + tempIndex + "\n";
        ArrayList<Expression> elements = listValue.getElements();
        for (Expression element: elements) {
            commands += "aload " + tempIndex + "\n";
            commands += element.accept(this);
            Type elementType = element.accept(expressionTypeChecker);
            if(elementType instanceof IntType)
                commands += "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;\n";
            if(elementType instanceof BoolType)
                commands += "invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;\n";

            commands += "invokevirtual java/util/ArrayList/add(Ljava/lang/Object;)Z\n";
            commands += "pop\n";
        }
        commands += "aload " + tempIndex + "\n";
        commands += "invokespecial List/<init>(Ljava/util/ArrayList;)V\n";
        return commands;
    }

    @Override
    public String visit(NullValue nullValue) {
        String commands = "";
        commands += "aconst_null\n";
        return commands;
    }

    @Override
    public String visit(IntValue intValue) {
        String commands = "";
        commands += "ldc " + intValue.getConstant() +"\n";
        return commands;
    }

    @Override
    public String visit(BoolValue boolValue) {
        String commands = "";
        if(boolValue.getConstant())
            commands += "ldc " + "1\n";
        else
            commands += "ldc " + "0\n";
        return commands;
    }

    @Override
    public String visit(StringValue stringValue) {
        String commands = "";
        commands += "ldc \"" + stringValue.getConstant() + "\"\n";
        return commands;
    }
}