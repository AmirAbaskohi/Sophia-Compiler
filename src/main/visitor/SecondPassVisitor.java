package main.visitor;

import main.SophiaCompiler;
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
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemAlreadyExistsException;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.ClassSymbolTableItem;
import main.symbolTable.items.FieldSymbolTableItem;
import main.symbolTable.items.MethodSymbolTableItem;
import main.symbolTable.items.SymbolTableItem;

import java.util.ArrayList;

public class SecondPassVisitor extends Visitor<Void> {

    @Override
    public Void visit(Program program) {
        for(ClassDeclaration classInProgram : program.getClasses())
        {
            if(classInProgram.getParentClassName() != null)
            {
                String parentKey = ClassSymbolTableItem.START_KEY;
                String classKey = ClassSymbolTableItem.START_KEY;
                parentKey = parentKey + classInProgram.getParentClassName().getName();
                classKey = classKey + classInProgram.getClassName().getName();
                try {
                    ClassSymbolTableItem parentClassItem = (ClassSymbolTableItem) SymbolTable.root.getItem(parentKey, true);
                    ClassSymbolTableItem classItem = (ClassSymbolTableItem) SymbolTable.root.getItem(classKey, true);
                    classItem.getClassSymbolTable().pre = parentClassItem.getClassSymbolTable();
                }
                catch (ItemNotFoundException ex)
                {

                }
            }
        }

        for(ClassDeclaration classInProgram : program.getClasses())
        {
            String classKey = ClassSymbolTableItem.START_KEY;
            classKey = classKey + classInProgram.getClassName().getName();
            try {
                ClassSymbolTableItem classItem = (ClassSymbolTableItem) SymbolTable.root.getItem(classKey, true);
                if (classItem.checkInheritanceCycle())
                {
                    SophiaCompiler.errors.add("Line:" + Integer.toString(classInProgram.getLine()) + ":" + "Class " + classInProgram.getClassName().getName() + " is in an inheritance cycle");
                }
            }
            catch (ItemNotFoundException ex){}
        }

        for(ClassDeclaration classInProgram : program.getClasses())
        {
            classInProgram.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ClassDeclaration classDeclaration) {
        String classKey = ClassSymbolTableItem.START_KEY;
        classKey = classKey + classDeclaration.getClassName().getName();
        try {
            ClassSymbolTableItem classItem = (ClassSymbolTableItem) SymbolTable.root.getItem(classKey, true);
            SymbolTable classSymbolTable = classItem.getClassSymbolTable();
            for (FieldDeclaration field : classDeclaration.getFields()) {
                String key = FieldSymbolTableItem.START_KEY;
                key = key + field.getVarDeclaration().getVarName().getName();
                String methodKey = MethodSymbolTableItem.START_KEY;
                methodKey = methodKey + field.getVarDeclaration().getVarName().getName();
                try {
                    classSymbolTable.getItem(key, false);
                    SophiaCompiler.errors.add("Line:" + Integer.toString(field.getLine()) + ":" + "Redefinition of field " + field.getVarDeclaration().getVarName().getName());
                }
                catch (ItemNotFoundException ex2)
                {

                }
                ArrayList<MethodSymbolTableItem> conflictsWithParentMethod = classSymbolTable.getFieldConflictsWithParentMethods(methodKey);
                for(MethodSymbolTableItem conflict : conflictsWithParentMethod)
                {
                    SophiaCompiler.errors.add("Line:" + Integer.toString(conflict.getMethodDeclaration().getLine()) +
                            ":" + "Name of method " + conflict.getMethodDeclaration().getMethodName().getName() +
                            " conflicts with a field's name");
                }

            }
            ConstructorDeclaration constructor = classDeclaration.getConstructor();
            if (constructor != null) {
                String fieldKey = FieldSymbolTableItem.START_KEY;
                fieldKey = fieldKey + constructor.getMethodName().getName();
                String key = MethodSymbolTableItem.START_KEY;
                key = key + constructor.getMethodName().getName();
                try {
                    classSymbolTable.getItem(fieldKey, false);
                    SophiaCompiler.errors.add("Line:" + Integer.toString(constructor.getLine()) + ":" + "Name of method " + constructor.getMethodName().getName() + " conflicts with a field's name");
                }
                catch (ItemNotFoundException ex3)
                {

                }
                try {
                    classSymbolTable.getItem(key, false);
                    SophiaCompiler.errors.add("Line:" + Integer.toString(constructor.getLine()) + ":" + "Redefinition of method " + constructor.getMethodName().getName());
                }
                catch (ItemNotFoundException ex4)
                {

                }
            }
            for (MethodDeclaration method : classDeclaration.getMethods()) {
                String fieldKey = FieldSymbolTableItem.START_KEY;
                fieldKey = fieldKey + method.getMethodName().getName();
                String key = MethodSymbolTableItem.START_KEY;
                key = key + method.getMethodName().getName();
                try {
                    classSymbolTable.getItem(fieldKey, false);
                    SophiaCompiler.errors.add("Line:" + Integer.toString(method.getLine()) + ":" + "Name of method " + method.getMethodName().getName() + " conflicts with a field's name");
                } catch (ItemNotFoundException ex5) {

                }
                try {
                    classSymbolTable.getItem(key, false);
                    SophiaCompiler.errors.add("Line:" + Integer.toString(method.getLine()) + ":" + "Redefinition of method " + method.getMethodName().getName());
                } catch (ItemNotFoundException ex6) {

                }
            }
        } catch (ItemNotFoundException ex)
        {

        }
        return null;
    }

}
