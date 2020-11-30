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

sophia returns[Program sophiaProgram]: p=program { $sophiaProgram = $p.programRet; } EOF;

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
        FieldDeclaration field = new FieldDeclaration($f.varDeclarationRet);
        field.setLine($f.varDeclarationRet.getLine());
        $sophiaClassRet.addField(field);
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
        FieldDeclaration field = new FieldDeclaration($f2.varDeclarationRet);
        field.setLine($f2.varDeclarationRet.getLine());
        $sophiaClassRet.addField(field);
    }
    | m2=method
    {
        $sophiaClassRet.addMethod($m2.methodDeclarationRet);
    }
    )*)
    | ((f3=varDeclaration
    {
        FieldDeclaration field = new FieldDeclaration($f3.varDeclarationRet);
        field.setLine($f3.varDeclarationRet.getLine());
        $sophiaClassRet.addField(field);
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
        $varDeclarationRet.setLine($id.identifierRet.getLine());
    };

method returns[MethodDeclaration methodDeclarationRet] locals[Type returnType]:
    d=DEF (t=type { $returnType = $t.typeRet; }| VOID { $returnType = new NullType(); } ) id=identifier
    {
        $methodDeclarationRet = new MethodDeclaration($id.identifierRet, $returnType);
        $methodDeclarationRet.setLine($d.getLine());
    }
    LPAR margs=methodArguments RPAR
    {
        $methodDeclarationRet.setArgs($margs.methodArgumentsRet);
    }
    LBRACE mbody=methodBody RBRACE
    {
        $methodDeclarationRet.setLocalVars($mbody.methodLocalVarsRet);
        $methodDeclarationRet.setBody($mbody.methodBodyRet);
    };

constructor returns[ConstructorDeclaration constructorDeclarationRet]:
    d=DEF id=identifier LPAR margs=methodArguments RPAR
    {
        $constructorDeclarationRet = new ConstructorDeclaration($id.identifierRet);
        $constructorDeclarationRet.setLine($d.getLine());
        $constructorDeclarationRet.setArgs($margs.methodArgumentsRet);
    }
    LBRACE mbody=methodBody RBRACE
    {
        $constructorDeclarationRet.setLocalVars($mbody.methodLocalVarsRet);
        $constructorDeclarationRet.setBody($mbody.methodBodyRet);
    };

methodArguments returns[ArrayList<VarDeclaration> methodArgumentsRet]:
    { $methodArgumentsRet = new ArrayList<>(); }
    (v1=variableWithType { $methodArgumentsRet.add($v1.variableWithTypeRet); }
    (COMMA v2=variableWithType { $methodArgumentsRet.add($v2.variableWithTypeRet); })*
    )?;

variableWithType returns[VarDeclaration variableWithTypeRet]:
    id = identifier COLON t = type
    {
        $variableWithTypeRet = new VarDeclaration($id.identifierRet, $t.typeRet);
        $variableWithTypeRet.setLine($id.identifierRet.getLine());
    };

type returns[Type typeRet]:
    pdt=primitiveDataType { $typeRet = $pdt.primitiveDataTypeRet; }
    | lt=listType { $typeRet = $lt.listTypeRet; }
    | fpt=functionPointerType { $typeRet = $fpt.functionPointerTypeRet; }
    | ct=classType { $typeRet = $ct.classTypeRet; } ;

classType returns[ClassType classTypeRet]:
    id=identifier { $classTypeRet = new ClassType($id.identifierRet); };

listType returns[ListType listTypeRet]:
    LIST LPAR ((size=INT_VALUE SHARP t=type)
    {
        $listTypeRet = new ListType(Integer.parseInt($size.getText()), new ListNameType($t.typeRet));
    }
    | (lit=listItemsTypes) { $listTypeRet = new ListType($lit.listItemTypesRet); }) RPAR;

listItemsTypes returns[ArrayList<ListNameType> listItemTypesRet]:
    { $listItemTypesRet = new ArrayList<>(); } lit1=listItemType { $listItemTypesRet.add($lit1.listItemTypeRet); }
    (COMMA lit2=listItemType { $listItemTypesRet.add($lit2.listItemTypeRet); })*;

listItemType returns[ListNameType listItemTypeRet]:
    vwt=variableWithType { $listItemTypeRet = new ListNameType($vwt.variableWithTypeRet); }
    | t=type { $listItemTypeRet = new ListNameType($t.typeRet); } ;

functionPointerType returns[FptrType functionPointerTypeRet]:
    FUNC LESS_THAN
    { $functionPointerTypeRet = new FptrType(); }
    (VOID { $functionPointerTypeRet.addArgumentType(new NullType()); }
    | twc=typesWithComma { $functionPointerTypeRet.setArgumentsTypes($twc.typeWithCommaRet); }) ARROW

    (VOID { $functionPointerTypeRet.setReturnType(new NullType()); }
    | t=type { $functionPointerTypeRet.setReturnType($t.typeRet); }) GREATER_THAN;

typesWithComma returns[ArrayList<Type> typeWithCommaRet]:
    { $typeWithCommaRet = new ArrayList<>(); }
    t1=type { $typeWithCommaRet.add($t1.typeRet); } (COMMA t2=type { $typeWithCommaRet.add($t2.typeRet); })*;

primitiveDataType returns[Type primitiveDataTypeRet]:
    INT { $primitiveDataTypeRet = new IntType(); }
    | STRING { $primitiveDataTypeRet = new StringType(); }
    | BOOLEAN { $primitiveDataTypeRet = new BoolType(); } ;

methodBody returns[ArrayList<VarDeclaration> methodLocalVarsRet, ArrayList<Statement> methodBodyRet]:
    {
        $methodLocalVarsRet = new ArrayList<>();
        $methodBodyRet = new ArrayList<>();
    }
    (vd=varDeclaration { $methodLocalVarsRet.add($vd.varDeclarationRet); })*
    (s=statement { $methodBodyRet.add($s.statementRet); })*;

statement returns[Statement statementRet]:
    forStmnt=forStatement { $statementRet = $forStmnt.forStatementRet; }
    | foreachStmnt=foreachStatement { $statementRet = $foreachStmnt.foreachStatementRet; }
    | ifStmnt=ifStatement { $statementRet = $ifStmnt.ifStatementRet; }
    | assignStmnt=assignmentStatement { $statementRet = $assignStmnt.assignmentStatementRet; }
    | pStmnt=printStatement { $statementRet = $pStmnt.printStatementRet; }
    | cbStmnt=continueBreakStatement { $statementRet = $cbStmnt.continueBreakStatementRet; }
    | methcStmnt=methodCallStatement { $statementRet = $methcStmnt.methodCallStatementRet; }
    | rStmnt=returnStatement { $statementRet = $rStmnt.returnStatementRet; }
    | bStmnt=block { $statementRet = $bStmnt.blockRet; };

block returns[BlockStmt blockRet]:
    lb=LBRACE
    {
        $blockRet = new BlockStmt();
        $blockRet.setLine($lb.getLine());
    }
    (s=statement { $blockRet.addStatement($s.statementRet); })* RBRACE;

assignmentStatement returns[AssignmentStmt assignmentStatementRet]:
    a=assignment { $assignmentStatementRet = $a.assignRet; } SEMICOLLON;

assignment returns[AssignmentStmt assignRet]:
    oex=orExpression a=ASSIGN ex=expression
    {
        $assignRet = new AssignmentStmt($oex.orExpressionRet, $ex.expressionRet);
        $assignRet.setLine($a.getLine());
    };

printStatement returns[PrintStmt printStatementRet]:
    p=PRINT LPAR exp=expression RPAR SEMICOLLON
    {
        $printStatementRet = new PrintStmt($exp.expressionRet);
        $printStatementRet.setLine($p.getLine());
    };

returnStatement returns[ReturnStmt returnStatementRet]:
    r=RETURN
    {
        $returnStatementRet = new ReturnStmt();
        $returnStatementRet.setLine($r.getLine());
    }
    (exp=expression
    {
        $returnStatementRet.setReturnedExpr($exp.expressionRet);
    })? SEMICOLLON;

methodCallStatement returns[MethodCallStmt methodCallStatementRet]: mc=methodCall
    {
        $methodCallStatementRet = new MethodCallStmt($mc.methodCallRet);
        $methodCallStatementRet.setLine($mc.methodCallRet.getLine());
    }
    SEMICOLLON;


methodCall returns[MethodCall methodCallRet] locals[Expression instance]:
    oe = otherExpression
    { $instance = $oe.otherExpressionRet; }
    (( lp = LPAR mca1 = methodCallArguments RPAR)
    {
        $instance = new MethodCall($instance, $mca1.methodCallArgumentsRet);
        $instance.setLine($lp.getLine());
    }
    | (DOT id = identifier)
    {
        $instance = new ObjectOrListMemberAccess($instance, $id.identifierRet);
        $instance.setLine($id.identifierRet.getLine());
    }
    | (lb = LBRACK e = expression RBRACK)
    {
        $instance = new ListAccessByIndex($instance, $e.expressionRet);
        $instance.setLine($lb.getLine());
    })*
    ( l = LPAR mca2 = methodCallArguments RPAR)
    {
        $methodCallRet = new MethodCall($instance, $mca2.methodCallArgumentsRet);
        $methodCallRet.setLine($l.getLine());
    };

methodCallArguments returns[ArrayList<Expression> methodCallArgumentsRet]:
{ $methodCallArgumentsRet = new ArrayList<>(); }
(exp1 = expression
{ $methodCallArgumentsRet.add($exp1.expressionRet); }
(COMMA exp2 = expression
{ $methodCallArgumentsRet.add($exp2.expressionRet); })*)?;

continueBreakStatement returns[Statement continueBreakStatementRet]:
(b = BREAK
{
    $continueBreakStatementRet = new BreakStmt();
    $continueBreakStatementRet.setLine($b.getLine());
}
| c = CONTINUE
{
    $continueBreakStatementRet = new ContinueStmt();
    $continueBreakStatementRet.setLine($c.getLine());
}
) SEMICOLLON;

forStatement returns[ForStmt forStatementRet]:
{ $forStatementRet = new ForStmt(); }
f = FOR
{ $forStatementRet.setLine($f.getLine()); }
LPAR (a1 = assignment
{ $forStatementRet.setInitialize($a1.assignRet); })?
SEMICOLLON (e = expression
{ $forStatementRet.setCondition($e.expressionRet); })?
SEMICOLLON (a2 = assignment
{ $forStatementRet.setUpdate($a2.assignRet); })?
RPAR s = statement
{ $forStatementRet.setBody($s.statementRet); }
;

foreachStatement returns[ForeachStmt foreachStatementRet]:
f = FOREACH LPAR id = identifier IN e = expression RPAR s = statement
{
    $foreachStatementRet = new ForeachStmt($id.identifierRet, $e.expressionRet);
    $foreachStatementRet.setBody($s.statementRet);
    $foreachStatementRet.setLine($f.getLine());
};

ifStatement returns[ConditionalStmt ifStatementRet]:
i = IF LPAR e = expression RPAR s1 = statement
{
    $ifStatementRet = new ConditionalStmt($e.expressionRet, $s1.statementRet);
    $ifStatementRet.setLine($i.getLine());
}
(ELSE s2 = statement
{ $ifStatementRet.setElseBody($s2.statementRet); })?;

expression returns[Expression expressionRet]:
oe = orExpression
{ $expressionRet = $oe.orExpressionRet; }
( a = ASSIGN e = expression
{
    $expressionRet = new BinaryExpression($expressionRet, $e.expressionRet, BinaryOperator.assign);
    $expressionRet.setLine($a.getLine());
})?;

orExpression returns[Expression orExpressionRet]:
ae1 = andExpression
{ $orExpressionRet = $ae1.andExpressionRet; }
( o = OR ae2 = andExpression
{
    $orExpressionRet = new BinaryExpression($orExpressionRet, $ae2.andExpressionRet, BinaryOperator.or);
    $orExpressionRet.setLine($o.getLine());
})*;

andExpression returns[Expression andExpressionRet]:
ee1 = equalityExpression
{ $andExpressionRet = $ee1.equalityExpressionRet; }
( a = AND ee2 = equalityExpression
{
    $andExpressionRet = new BinaryExpression($andExpressionRet, $ee2.equalityExpressionRet, BinaryOperator.and);
    $andExpressionRet.setLine($a.getLine());
})*;

equalityExpression returns[Expression equalityExpressionRet] locals[BinaryOperator op, int line]:
re1 = relationalExpression
{ $equalityExpressionRet = $re1.relationalExpressionRet; }
(( e = EQUAL
{
    $op = BinaryOperator.eq;
    $line = $e.getLine();
}
| n = NOT_EQUAL
{
    $op = BinaryOperator.neq;
    $line = $n.getLine();
})
re2 = relationalExpression
{
    $equalityExpressionRet = new BinaryExpression($equalityExpressionRet, $re2.relationalExpressionRet, $op);
    $equalityExpressionRet.setLine($line);
})*;

relationalExpression returns[Expression relationalExpressionRet] locals[BinaryOperator op, int line]:
ae1 = additiveExpression
{ $relationalExpressionRet = $ae1.additiveExpressionRet; }
(( g = GREATER_THAN
{
    $op = BinaryOperator.gt;
    $line = $g.getLine();
}
| l = LESS_THAN
{
    $op = BinaryOperator.lt;
    $line = $l.getLine();
}
)
ae2 = additiveExpression
{
    $relationalExpressionRet = new BinaryExpression($relationalExpressionRet, $ae2.additiveExpressionRet, $op);
    $relationalExpressionRet.setLine($line);
})*;

additiveExpression returns[Expression additiveExpressionRet] locals[BinaryOperator op, int line]:
me1 = multiplicativeExpression
{ $additiveExpressionRet = $me1.multiplicativeExpressionRet; }
(( p = PLUS
{
    $op = BinaryOperator.add;
    $line = $p.getLine();
}
| m = MINUS
{
    $op = BinaryOperator.sub;
    $line = $m.getLine();
})
me2 = multiplicativeExpression
{
    $additiveExpressionRet = new BinaryExpression($additiveExpressionRet, $me2.multiplicativeExpressionRet, $op);
    $additiveExpressionRet.setLine($line);
})*;

multiplicativeExpression returns[Expression multiplicativeExpressionRet] locals[BinaryOperator op, int line]:
pre1 = preUnaryExpression
{ $multiplicativeExpressionRet = $pre1.preUnaryExpressionRet; }
(( mu = MULT
{
    $op = BinaryOperator.mult;
    $line = $mu.getLine();
}
| d = DIVIDE
{
    $op = BinaryOperator.div;
    $line = $d.getLine();
}
| mo = MOD
{
    $op = BinaryOperator.mod;
    $line = $mo.getLine();
})
pre2 = preUnaryExpression
{
    $multiplicativeExpressionRet = new BinaryExpression($multiplicativeExpressionRet, $pre2.preUnaryExpressionRet, $op);
    $multiplicativeExpressionRet.setLine($line);
})*;

preUnaryExpression returns[Expression preUnaryExpressionRet] locals[UnaryOperator op, int line]:
(( n = NOT
{
    $op = UnaryOperator.not;
    $line = $n.getLine();
}
| m = MINUS
{
    $op = UnaryOperator.minus;
    $line = $m.getLine();
}
| i = INCREMENT
{
    $op = UnaryOperator.preinc;
    $line = $i.getLine();
}
| d = DECREMENT
{
    $op = UnaryOperator.predec;
    $line = $d.getLine();
})
pre = preUnaryExpression
{
    $preUnaryExpressionRet = new UnaryExpression($pre.preUnaryExpressionRet, $op);
    $preUnaryExpressionRet.setLine($line);

})
| poe = postUnaryExpression
{ $preUnaryExpressionRet = $poe.postUnaryExpressionRet; };

postUnaryExpression returns[Expression postUnaryExpressionRet]:
ae = accessExpression
{ $postUnaryExpressionRet = $ae.accessExpressionRet; }
( i = INCREMENT
{
    $postUnaryExpressionRet = new UnaryExpression($postUnaryExpressionRet, UnaryOperator.postinc);
    $postUnaryExpressionRet.setLine($i.getLine());
}
| d = DECREMENT
{
    $postUnaryExpressionRet = new UnaryExpression($postUnaryExpressionRet, UnaryOperator.postdec);
    $postUnaryExpressionRet.setLine($d.getLine());
})?;

accessExpression returns[Expression accessExpressionRet]:
oe = otherExpression
{ $accessExpressionRet = $oe.otherExpressionRet; }
(( lp = LPAR mca = methodCallArguments RPAR)
{
    $accessExpressionRet = new MethodCall($accessExpressionRet, $mca.methodCallArgumentsRet);
    $accessExpressionRet.setLine($lp.getLine());
}
| (DOT id = identifier)
{
    $accessExpressionRet = new ObjectOrListMemberAccess($accessExpressionRet, $id.identifierRet);
    $accessExpressionRet.setLine($id.identifierRet.getLine());
}
| (lb = LBRACK e = expression RBRACK)
{
    $accessExpressionRet = new ListAccessByIndex($accessExpressionRet, $e.expressionRet);
    $accessExpressionRet.setLine($lb.getLine());
})*;

otherExpression returns[Expression otherExpressionRet]:
t = THIS
{
    $otherExpressionRet = new ThisClass();
    $otherExpressionRet.setLine($t.getLine());
}
| n = newExpression
{ $otherExpressionRet = $n.newExpressionRet; }
| v = values
{ $otherExpressionRet = $v.valuesRet; }
| id = identifier
{ $otherExpressionRet = $id.identifierRet; }
| LPAR (e = expression)
{ $otherExpressionRet = $e.expressionRet; }
RPAR;

newExpression returns[NewClassInstance newExpressionRet]:
n = NEW c = classType
{
    $newExpressionRet = new NewClassInstance($c.classTypeRet);
    $newExpressionRet.setLine($n.getLine());
}
LPAR mca = methodCallArguments
{ $newExpressionRet.setArgs($mca.methodCallArgumentsRet); }
RPAR;

values returns[Value valuesRet]:
b = boolValue
{$valuesRet = $b.boolValueRet;}
| sv = STRING_VALUE
{
    String sVal = $sv.text;
    $valuesRet = new StringValue(sVal.substring(1, sVal.length()-1));
    $valuesRet.setLine($sv.getLine());
}
| iv = INT_VALUE
{
    $valuesRet = new IntValue(Integer.parseInt($iv.text));
    $valuesRet.setLine($iv.getLine());
}
| n = NULL
{
    $valuesRet = new NullValue();
    $valuesRet.setLine($n.getLine());
}
| l = listValue
{ $valuesRet = $l.listValueRet; };

boolValue returns[BoolValue boolValueRet]:
t = TRUE
{
    $boolValueRet = new BoolValue(true);
    $boolValueRet.setLine($t.getLine());
}
| f = FALSE
{
    $boolValueRet = new BoolValue(false);
    $boolValueRet.setLine($f.getLine());
};

listValue returns[ListValue listValueRet]:
{ $listValueRet = new ListValue(); }
l = LBRACK
{ $listValueRet.setLine($l.getLine()); }
mca = methodCallArguments
{ $listValueRet.setElements($mca.methodCallArgumentsRet); }
RBRACK;

identifier returns[Identifier identifierRet]:
id = IDENTIFIER
{
    $identifierRet = new Identifier($id.text) ;
    $identifierRet.setLine($id.getLine());
};


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