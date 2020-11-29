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
        System.out.println("Line:"+Integer.toString(program.getLine())+":"+program.toString());
        for(ClassDeclaration classInProgram : program.getClasses())
        {
            classInProgram.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ClassDeclaration classDeclaration) {
        System.out.println("Line:"+Integer.toString(classDeclaration.getLine())+":"+classDeclaration.toString());
        classDeclaration.getClassName().accept(this);
        Identifier parentIdentifier = classDeclaration.getParentClassName();
        if(parentIdentifier != null)
            classDeclaration.getParentClassName().accept(this);
        for(FieldDeclaration filed : classDeclaration.getFields())
        {
            filed.accept(this);
        }
        ConstructorDeclaration constructor = classDeclaration.getConstructor();
        if(constructor != null)
            constructor.accept(this);
        for(MethodDeclaration method : classDeclaration.getMethods())
        {
            method.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ConstructorDeclaration constructorDeclaration) {
        System.out.println("Line:"+Integer.toString(constructorDeclaration.getLine())+":"+constructorDeclaration.toString());
        constructorDeclaration.getMethodName().accept(this);
        for(VarDeclaration argument : constructorDeclaration.getArgs())
        {
            argument.accept(this);
        }
        for(VarDeclaration localVar : constructorDeclaration.getLocalVars())
        {
            localVar.accept(this);
        }
        for(Statement statement : constructorDeclaration.getBody())
        {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(MethodDeclaration methodDeclaration) {
        System.out.println("Line:"+Integer.toString(methodDeclaration.getLine())+":"+methodDeclaration.toString());
        methodDeclaration.getMethodName().accept(this);
        for(VarDeclaration argument : methodDeclaration.getArgs())
        {
            argument.accept(this);
        }
        for(VarDeclaration localVar : methodDeclaration.getLocalVars())
        {
            localVar.accept(this);
        }
        for(Statement statement : methodDeclaration.getBody())
        {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(FieldDeclaration fieldDeclaration) {
        System.out.println("Line:"+Integer.toString(fieldDeclaration.getLine())+":"+fieldDeclaration.toString());
        fieldDeclaration.getVarDeclaration().accept(this);
        return null;
    }

    @Override
    public Void visit(VarDeclaration varDeclaration) {
        System.out.println("Line:"+Integer.toString(varDeclaration.getLine())+":"+varDeclaration.toString());
        varDeclaration.getVarName().accept(this);
        return null;
    }

    @Override
    public Void visit(AssignmentStmt assignmentStmt) {
        System.out.println("Line:"+Integer.toString(assignmentStmt.getLine())+":"+assignmentStmt.toString());
        assignmentStmt.getlValue().accept(this);
        assignmentStmt.getrValue().accept(this);
        return null;
    }

    @Override
    public Void visit(BlockStmt blockStmt) {
        System.out.println("Line:"+Integer.toString(blockStmt.getLine())+":"+blockStmt.toString());
        for(Statement statement : blockStmt.getStatements())
        {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ConditionalStmt conditionalStmt) {
        System.out.println("Line:"+Integer.toString(conditionalStmt.getLine())+":"+conditionalStmt.toString());
        conditionalStmt.getCondition().accept(this);
        conditionalStmt.getThenBody().accept(this);
        Statement elseStatement = conditionalStmt.getElseBody();
        if(elseStatement != null)
            elseStatement.accept(this);
        return null;
    }

    @Override
    public Void visit(MethodCallStmt methodCallStmt) {
        System.out.println("Line:"+Integer.toString(methodCallStmt.getLine())+":"+methodCallStmt.toString());
        methodCallStmt.getMethodCall().accept(this);
        return null;
    }

    @Override
    public Void visit(PrintStmt print) {
        System.out.println("Line:"+Integer.toString(print.getLine())+":"+print.toString());
        print.getArg().accept(this);
        return null;
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        System.out.println("Line:"+Integer.toString(returnStmt.getLine())+":"+returnStmt.toString());
        returnStmt.getReturnedExpr().accept(this);
        return null;
    }

    @Override
    public Void visit(BreakStmt breakStmt) {
        System.out.println("Line:"+Integer.toString(breakStmt.getLine())+":"+breakStmt.toString());
        return null;
    }

    @Override
    public Void visit(ContinueStmt continueStmt) {
        System.out.println("Line:"+Integer.toString(continueStmt.getLine())+":"+continueStmt.toString());
        return null;
    }

    @Override
    public Void visit(ForeachStmt foreachStmt) {
        System.out.println("Line:"+Integer.toString(foreachStmt.getLine())+":"+foreachStmt.toString());
        foreachStmt.getVariable().accept(this);
        foreachStmt.getList().accept(this);
        Statement statement = foreachStmt.getBody();
        if(statement != null)
            statement.accept(this);
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
