grammar Sophia;

@header{
    import main.ast.types.*;
    import main.ast.types.functionPointer.*;
    import main.ast.types.list.*;
    import main.ast.types.single.*;
    import main.ast.nodes.*;
    import main.ast.nodes.declaration.*;
    import main.ast.nodes.declaration.classDec.*;
    import main.ast.nodes.declaration.classDec.classMembersDec.*;
    import main.ast.nodes.declaration.variableDec.*;
    import main.ast.nodes.expression.*;
    import main.ast.nodes.expression.operators.*;
    import main.ast.nodes.expression.values.*;
    import main.ast.nodes.expression.values.primitive.*;
    import main.ast.nodes.statement.*;
    import main.ast.nodes.statement.loop.*;
}

sophia returns[Program sophiaProgram]: p=program { $sophiaProgram = $p.programRet } EOF;

program returns[Program programRet]:
    {
        $programRet = new Program();
        $programRet.setLine(1);
    }
    (
        c=sophiaClass
        { $programRet.addClass($c.sophiaClassRet); }
    )*;

sophiaClass returns[ClassDeclaration sophiaClassRet]:
    cl=CLASS cn=identifier
    {
        $sophiaClassRet = new ClassDeclaration($cn.identifierRet);
        $sophiaClassRet.setLine($cl.getLine());
    }
    (EXTENDS pcn=identifier { $sophiaClassRet.setParentClassName($pcn.identifierRet); })? LBRACE
    (((f=varDeclaration
    {
        $sophiaClassRet.addField(new FieldDeclaration($f.varDeclarationRet));
    }
    | m=method
    {
        $sophiaClassRet.addMethod($m.methodDeclarationRet);
    }
    )*
    cntr=constructor
    {
        $sophiaClassRet.setConstructor($cntr.constructorDeclarationRet);
    }
    (
    f2=varDeclaration
    {
        $sophiaClassRet.addField(new FieldDeclaration($f2.varDeclarationRet));
    }
    | m2=method
    {
        $sophiaClassRet.addMethod($m2.methodDeclarationRet);
    }
    )*)
    | ((f3=varDeclaration
    {
        $sophiaClassRet.addField(new FieldDeclaration($f3.varDeclarationRet));
    }
    | m3=method
    {
        $sophiaClassRet.addMethod($m3.methodDeclarationRet);
    }
    )*)) RBRACE;

varDeclaration returns[VarDeclaration varDeclarationRet]:
    id=identifier COLON t=type SEMICOLLON
    {
        $varDeclarationRet = new VarDeclaration($id.identifierRet, $t.typeRet);
        $varDeclarationRet.setLine($id.getLine());
    };

method returns[MethodDeclaration methodDeclarationRet] locals[Type returnType]:
    d=DEF (t=type { $returnType = $t.typeRet; }| VOID { $returnType = new NoType(); } ) id=identifier
    {
        $methodDeclarationRet = new MethodDeclaration($id.identifierRet, $returnType);
        $methodDeclarationRet.setLine($d.getLine());
    }
    LPAR margs=methodArguments
    {
        $methodDeclarationRet.setArgs($margs.methodArgumentsRet);
    }
    RPAR LBRACE mbody=methodBody
    {
        $methodDeclarationRet.setLocalVars($mbody.methodLocalVarsRet);
        $methodDeclarationRet.setBody($mbody.methodBodyRet);
    }
    RBRACE;

constructor: DEF identifier LPAR methodArguments RPAR LBRACE methodBody RBRACE;

methodArguments: (variableWithType (COMMA variableWithType)*)?;

variableWithType: identifier COLON type;

type: primitiveDataType | listType | functionPointerType | classType;

classType: identifier;

listType: LIST LPAR ((INT_VALUE SHARP type) | (listItemsTypes)) RPAR;

listItemsTypes: listItemType (COMMA listItemType)*;

listItemType: variableWithType | type;

functionPointerType: FUNC LESS_THAN (VOID | typesWithComma) ARROW (VOID | type) GREATER_THAN;

typesWithComma: type (COMMA type)*;

primitiveDataType: INT | STRING | BOOLEAN;

methodBody: (varDeclaration)* (statement)*;

statement: forStatement | foreachStatement | ifStatement | assignmentStatement | printStatement | continueBreakStatement | methodCallStatement | returnStatement | block;

block: LBRACE (statement)* RBRACE;

assignmentStatement: assignment SEMICOLLON;

assignment: orExpression ASSIGN expression;

printStatement: PRINT LPAR expression RPAR SEMICOLLON;

returnStatement: RETURN expression? SEMICOLLON;

methodCallStatement: methodCall SEMICOLLON;

methodCall: otherExpression ((LPAR methodCallArguments RPAR) | (DOT identifier) | (LBRACK expression RBRACK))* (LPAR methodCallArguments RPAR);

methodCallArguments: (expression (COMMA expression)*)?;

continueBreakStatement: (BREAK | CONTINUE) SEMICOLLON;

forStatement: FOR LPAR (assignment)? SEMICOLLON (expression)? SEMICOLLON (assignment)? RPAR statement;

foreachStatement: FOREACH LPAR identifier IN expression RPAR statement;

ifStatement: IF LPAR expression RPAR statement (ELSE statement)?;

expression: orExpression (ASSIGN expression)?;

orExpression: andExpression (OR andExpression)*;

andExpression: equalityExpression (AND equalityExpression)*;

equalityExpression: relationalExpression ((EQUAL | NOT_EQUAL) relationalExpression)*;

relationalExpression: additiveExpression ((GREATER_THAN | LESS_THAN) additiveExpression)*;

additiveExpression: multiplicativeExpression ((PLUS | MINUS) multiplicativeExpression)*;

multiplicativeExpression: preUnaryExpression ((MULT | DIVIDE | MOD) preUnaryExpression)*;

preUnaryExpression: ((NOT | MINUS | INCREMENT | DECREMENT) preUnaryExpression) | postUnaryExpression;

postUnaryExpression: accessExpression (INCREMENT | DECREMENT)?;

accessExpression: otherExpression ((LPAR methodCallArguments RPAR) | (DOT identifier) | (LBRACK expression RBRACK))*;

otherExpression: THIS | newExpression | values | identifier | LPAR (expression) RPAR;

newExpression: NEW classType LPAR methodCallArguments RPAR;

values: boolValue | STRING_VALUE | INT_VALUE | NULL | listValue;

boolValue: TRUE | FALSE;

listValue: LBRACK methodCallArguments RBRACK;

identifier: IDENTIFIER;


DEF: 'def';
EXTENDS: 'extends';
CLASS: 'class';

PRINT: 'print';
FUNC: 'func';

NEW: 'new';

CONTINUE: 'continue';
BREAK: 'break';
RETURN: 'return';

FOREACH: 'foreach';
IN: 'in';
FOR: 'for';
IF: 'if';
ELSE: 'else';

BOOLEAN: 'bool';
STRING: 'string';
INT: 'int';
VOID: 'void';
NULL: 'null';
LIST: 'list';

TRUE: 'true';
FALSE: 'false';

THIS: 'this';

ARROW: '->';
GREATER_THAN: '>';
LESS_THAN: '<';
NOT_EQUAL: '!=';
EQUAL: '==';

MULT: '*';
DIVIDE: '/';
MOD: '%';
PLUS: '+';
MINUS: '-';
AND: '&&';
OR: '||';
NOT: '!';
QUESTION_MARK: '?';

ASSIGN: '=';

INCREMENT: '++';
DECREMENT: '--';

LPAR: '(';
RPAR: ')';
LBRACK: '[';
RBRACK: ']';
LBRACE: '{';
RBRACE: '}';

SHARP: '#';
COMMA: ',';
DOT: '.';
COLON: ':';
SEMICOLLON: ';';

INT_VALUE: '0' | [1-9][0-9]*;
IDENTIFIER: [a-zA-Z_][A-Za-z0-9_]*;
STRING_VALUE: '"'~["]*'"';
COMMENT: ('//' ~( '\r' | '\n')*) -> skip;
WS: ([ \t\n\r]) -> skip;