package main.visitor;

import main.SophiaCompiler;
import main.ast.nodes.Program;
import main.ast.nodes.declaration.classDec.ClassDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.ConstructorDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.FieldDeclaration;
import main.ast.nodes.declaration.classDec.classMembersDec.MethodDeclaration;
import main.ast.nodes.declaration.variableDec.VarDeclaration;
import main.symbolTable.SymbolTable;
import main.symbolTable.exceptions.ItemAlreadyExistsException;
import main.symbolTable.exceptions.ItemNotFoundException;
import main.symbolTable.items.ClassSymbolTableItem;
import main.symbolTable.items.FieldSymbolTableItem;
import main.symbolTable.items.LocalVariableSymbolTableItem;
import main.symbolTable.items.MethodSymbolTableItem;

import java.util.Collections;
public class FirstPassVisitor extends Visitor<Void> {

    @Override
    public Void visit(Program program) {
        int duplicate = 0;
        SymbolTable.root = new SymbolTable();
        SymbolTable.push(SymbolTable.root);
        for(ClassDeclaration classInProgram : program.getClasses())
        {
            ClassSymbolTableItem  classItem = new ClassSymbolTableItem(classInProgram);
            try{
                SymbolTable.top.put(classItem);
            } catch (ItemAlreadyExistsException ex)
            {
                SophiaCompiler.errors.add("Line:" + Integer.toString(classInProgram.getLine()) + ":" + "Redefinition of class " + classItem.getName());
                duplicate ++;
                String newName = classInProgram.getClassName().getName();
                newName = newName + String.join("", Collections.nCopies(duplicate, "*"));
                classItem.setName(newName);
                classInProgram.getClassName().setName(newName);
                try {
                    SymbolTable.top.put(classItem);
                } catch (ItemAlreadyExistsException dummyEx)
                {

                }
            }
            SymbolTable classSymbolTable = new SymbolTable(SymbolTable.root);
            SymbolTable.push(classSymbolTable);
            classInProgram.accept(this);
            classItem.setClassSymbolTable(classSymbolTable);
            SymbolTable.pop();
        }
        return null;
    }

    @Override
    public Void visit(ClassDeclaration classDeclaration) {
        for(FieldDeclaration field : classDeclaration.getFields())
        {
            FieldSymbolTableItem fieldItem = new FieldSymbolTableItem(field);
            try{
                SymbolTable.top.put(fieldItem);
            }
            catch (ItemAlreadyExistsException ex)
            {
                SophiaCompiler.errors.add("Line:" + Integer.toString(field.getLine()) + ":" + "Redefinition of field " + fieldItem.getName());
            }
        }
        ConstructorDeclaration constructor = classDeclaration.getConstructor();
        if(constructor != null)
        {
            MethodSymbolTableItem constructorItem = new MethodSymbolTableItem(constructor);
            try{
                String key = FieldSymbolTableItem.START_KEY;
                key = key + constructorItem.getName();
                try {
                    SymbolTable.top.getItem(key, true);
                    SophiaCompiler.errors.add("Line:" + Integer.toString(constructor.getLine()) + ":" + "Name of method " + constructorItem.getName() + " conflicts with a field's name");
                } catch (ItemNotFoundException notFoundExp)
                {

                }
                SymbolTable.top.put(constructorItem);
                SymbolTable methodSymbolTable = new SymbolTable(SymbolTable.top);
                SymbolTable.push(methodSymbolTable);
                constructor.accept(this);
                constructorItem.setMethodSymbolTable(methodSymbolTable);
                SymbolTable.pop();
            } catch (ItemAlreadyExistsException ex3)
            {
                SophiaCompiler.errors.add("Line:" + Integer.toString(constructor.getLine()) + ":" + "Redefinition of method " + constructorItem.getName());
            }
        }
        for(MethodDeclaration method : classDeclaration.getMethods())
        {
            MethodSymbolTableItem methodItem = new MethodSymbolTableItem(method);
            try{
                String key = FieldSymbolTableItem.START_KEY;
                key = key + methodItem.getName();
                try {
                    SymbolTable.top.getItem(key, true);
                    SophiaCompiler.errors.add("Line:" + Integer.toString(method.getLine()) + ":" + "Name of method " + methodItem.getName() + " conflicts with a field's name");
                } catch (ItemNotFoundException notFoundExp)
                {

                }
                SymbolTable.top.put(methodItem);
                SymbolTable methodSymbolTable = new SymbolTable(SymbolTable.top);
                SymbolTable.push(methodSymbolTable);
                method.accept(this);
                methodItem.setMethodSymbolTable(methodSymbolTable);
                SymbolTable.pop();
            } catch (ItemAlreadyExistsException ex3)
            {
                SophiaCompiler.errors.add("Line:" + Integer.toString(method.getLine()) + ":" + "Redefinition of method " + methodItem.getName());
            }
        }
        return null;
    }

    @Override
    public Void visit(ConstructorDeclaration constructorDeclaration) {
        for(VarDeclaration argument : constructorDeclaration.getArgs())
        {
            LocalVariableSymbolTableItem argumentItem = new LocalVariableSymbolTableItem(argument);
            try {
                SymbolTable.top.put(argumentItem);
            } catch(ItemAlreadyExistsException ex)
            {
                SophiaCompiler.errors.add("Line:" + Integer.toString(argument.getLine()) + ":" + "Redefinition of local variable " + argumentItem.getName());
            }
        }
        for(VarDeclaration localVar : constructorDeclaration.getLocalVars())
        {
            LocalVariableSymbolTableItem localVarItem = new LocalVariableSymbolTableItem(localVar);
            try {
                SymbolTable.top.put(localVarItem);
            } catch(ItemAlreadyExistsException ex)
            {
                SophiaCompiler.errors.add("Line:" + Integer.toString(localVar.getLine()) + ":" + "Redefinition of local variable " + localVarItem.getName());
            }
        }
        return null;
    }

    @Override
    public Void visit(MethodDeclaration methodDeclaration) {
        for(VarDeclaration argument : methodDeclaration.getArgs())
        {
            LocalVariableSymbolTableItem argumentItem = new LocalVariableSymbolTableItem(argument);
            try {
                SymbolTable.top.put(argumentItem);
            } catch(ItemAlreadyExistsException ex)
            {
                SophiaCompiler.errors.add("Line:" + Integer.toString(argument.getLine()) + ":" + "Redefinition of local variable " + argumentItem.getName());
            }
        }
        for(VarDeclaration localVar : methodDeclaration.getLocalVars())
        {
            LocalVariableSymbolTableItem localVarItem = new LocalVariableSymbolTableItem(localVar);
            try {
                SymbolTable.top.put(localVarItem);
            } catch(ItemAlreadyExistsException ex)
            {
                SophiaCompiler.errors.add("Line:" + Integer.toString(localVar.getLine()) + ":" + "Redefinition of local variable " + localVarItem.getName());
            }
        }
        return null;
    }

}
