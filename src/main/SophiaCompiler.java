package main;

import main.ast.nodes.Program;
import main.visitor.nameAnalyzer.NameAnalyzer;
import main.visitor.typeChecker.TypeChecker;
import main.visitor.utils.ErrorReporter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parsers.SophiaLexer;
import parsers.SophiaParser;


public class SophiaCompiler {
    public void compile(CharStream textStream) {
        SophiaLexer sophiaLexer = new SophiaLexer(textStream);
        CommonTokenStream tokenStream = new CommonTokenStream(sophiaLexer);
        SophiaParser sophiaParser = new SophiaParser(tokenStream);
        Program program = sophiaParser.sophia().sophiaProgram;
        ErrorReporter errorReporter = new ErrorReporter();

        NameAnalyzer nameAnalyzer = new NameAnalyzer(program);
        nameAnalyzer.analyze();
        int numberOfErrors = program.accept(errorReporter);
        if(numberOfErrors > 0)
            System.exit(1);

        TypeChecker typeChecker = new TypeChecker(nameAnalyzer.getClassHierarchy());
        program.accept(typeChecker);
        numberOfErrors = program.accept(errorReporter);
        if(numberOfErrors > 0)
            System.exit(1);

        System.out.println("Compilation successful");

    }
}
