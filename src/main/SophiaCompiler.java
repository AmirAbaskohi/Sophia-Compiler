package main;

import main.ast.nodes.Program;
import main.symbolTable.SymbolTable;
import main.visitor.ASTTreePrinter;
import main.visitor.FirstPassVisitor;
import main.visitor.SecondPassVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parsers.SophiaLexer;
import parsers.SophiaParser;

import java.util.*;

public class SophiaCompiler {
    public static Set<String> errors = new HashSet<>();
    public void compile(CharStream textStream) {
        SophiaLexer sophiaLexer = new SophiaLexer(textStream);
        CommonTokenStream tokenStream = new CommonTokenStream(sophiaLexer);
        SophiaParser sophiaParser = new SophiaParser(tokenStream);
        Program program = sophiaParser.sophia().sophiaProgram;

        FirstPassVisitor firstPass = new FirstPassVisitor();
        firstPass.visit(program);
        SecondPassVisitor secondPass = new SecondPassVisitor();
        secondPass.visit(program);
        if(errors.size() != 0)
        {
            ArrayList<String> errs = new ArrayList<>();
            for(String er : errors)
            {
                errs.add(er);
            }

            Collections.sort(errs, new Comparator<String>(){
                public int compare (String a, String b)
                {
                    String[] aa,bb;
                    aa = a.split(":");
                    bb = b.split(":");
                    return  Integer.compare(Integer.parseInt(aa[1]), Integer.parseInt(bb[1]));
                }
            });

            for(String er : errs)
            {

                System.out.println(er);
            }
        }
        else
        {
            ASTTreePrinter astTreePrinter = new ASTTreePrinter();
            astTreePrinter.visit(program);
        }
    }

}
