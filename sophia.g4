grammar sophia;

program
    : classObject* EOF
    ;

classObject
    : classDeclaration LBRACE classBody RBRACE
    ;

classDeclaration
    : extendedDeclaration
    | unextendedDeclaration
    ;

extendedDeclaration
    : CLASS id=IDENTIFIER EXTENDS par=IDENTIFIER {System.out.println("ClassDec:"+$id.text+","+$par.text);}
    ;

unextendedDeclaration
    : CLASS id=IDENTIFIER {System.out.println("ClassDec:"+$id.text);}
    ;

classBody
    : variable* method* (constructor | ) method*
    ;

constructor
    : constructorDeclaration LBRACE constructorBody RBRACE
    ;

method
    : methodDeclaration LBRACE methodBody RBRACE
    ;

constructorDeclaration
    : DEF id=IDENTIFIER LPARANTHES argument RPARANTHES {System.out.println("ConstructorDec:"+$id.text);}
    ;

constructorBody
    : variable* ( statement | loop | decision | block )*
    ;

methodDeclaration
    : DEF returnType id=IDENTIFIER LPARANTHES argument RPARANTHES {System.out.println("MethodDec"+$id.text);}
    ;

methodBody
    : variable* ( statement | loop | decision | block )*
    ;

loop
    : forLoop | foreachLoop
    ;

forLoop
    : FOR LPARANTHES forCondition RPARANTHES loopBody
    ;

foreachLoop
    : FOREACH LPARANTHES foreachCondition RPARANTHES loopBody
    ;

forCondition
    : (assignStatement | ) DELIM (boolExp | calcExp | ) DELIM (assignStatement | )
    ;

foreachCondition
    : IDENTIFIER IN IDENTIFIER
    ;

loopBody
    : statement
    | loop
    | decision
    | block
    |
    ;

block
    : LPARANTHES variable* (statement | loop | decision | block)* RPARANTHES
    ;

argument
    : IDENTIFIER COLON type
    | IDENTIFIER COLON type COMMA argument
    ;

variable
    : id=IDENTIFIER COLON type DELIM {System.out.println("VarDec:"+$id.text);}
    ;

statement
    : assignStatement
    | BREAK
    | CONTINUE
    | returnStatement
    ;

type
    : PRIMITIVE_TYPE
    | IDENTIFIER
    | listType
    | functionPointer
    ;

functionPointer
    : FUNC LSIGN (VOID ARROW returnType) RSIGN
    | FUNC LSIGN (argumentType ARROW returnType) RSIGN
    ;

argumentType
    : type
    | type COMMA argumentType
    ;

returnType
    : type
    | VOID
    ;

listType
    : LIST LPARANTHES listDefinition RPARANTHES
    ;

listDefinition
    : sameTypeList
    | differentTypeListWithKey
    | differentTypeListWithoutKey
    ;

sameTypeList
    : number SHARP type
    ;

differentTypeListWithKey
    : IDENTIFIER COLON type
    | IDENTIFIER COLON type COMMA differentTypeListWithKey
    ;

differentTypeListWithoutKey
    : type
    | type COMMA type
    ;

number
    : NEGATIVE_NUMBER
    | POSITIVE_NUMBER
    | ZERO
    ;

calcExp
    :
    ;

boolExp
    :
    ;

assignStatement
    :
    ;

decision
    : IF LPARANTHES decisionCond RPARANTHES decisionBody
     (ELSE LPARANTHES decisionCond RPARANTHES decisionBody | )
    ;

decisionCond
    : boolExp
    | calcExp
    ;

decisionBody
    : statement
    | loop
    | decision
    | block
    ;

returnStatement
    : RETURN DELIM
    | RETURN IDENTIFIER DELIM
    | RETURN number DELIM
    | RETURN TRUE DELIM
    | RETURN FALSE DELIM
    | RETURN boolExp
    | RETURN calcExp
    ;

IDENTIFIER:
    ([A-Za-z] | '_') ([A-Za-z0-9] | '_')*
    ;

ZERO
    : '0'
    ;

NEGATIVE_NUMBER
    : '-' POSITIVE_NUMBER
    ;

POSITIVE_NUMBER
    : [1-9] [0-9]*
    ;

CLASS
    : 'class'
    ;

EXTENDS
    : 'extends'
    ;

DEF
    : 'def'
    ;

FUNC
    : 'func'
    ;

IN
    : 'in'
    ;

IF
    : 'if' {System.out.println("Conditional:if");}
    ;

ELSE
    : 'else' {System.out.println("Conditional:else");}
    ;

RETURN
    : 'return' {System.out.println("Return");}
    ;

TRUE
    : 'true'
    ;

FALSE
    : 'false'
    ;

SHARP
    : '#'
    ;

ARROW
    : '->'
    ;

BREAK
    : 'break' DELIM {System.out.println("Control:break");}
    ;

CONTINUE
    : 'continue' DELIM {System.out.println("Control:continue");}
    ;

PRIMITIVE_TYPE
    : INT
    | STRING
    | BOOLEAN
    ;

INT
    : 'int'
    ;

STRING
    : 'string'
    ;

BOOLEAN
    : 'boolean'
    ;

VOID
    : 'void'
    ;

LIST
    : 'list'
    ;

FOR:
    'for' {System.out.println("Loop:for");}
    ;

FOREACH:
    'foreach' {System.out.println("Loop:foreach");}
    ;

LBRACE:
    '{'
    ;

RBRACE:
    '}'
    ;

LPARANTHES:
    '('
    ;

RPARANTHES:
    ')'
    ;

LSIGN:
    '<'
    ;

RSIGN:
    '>'
    ;

DELIM:
    ';'
    ;

COLON:
    ':'
    ;

COMMA:
    ','
    ;

WS:
    [ \t\r\n] -> skip
    ;
