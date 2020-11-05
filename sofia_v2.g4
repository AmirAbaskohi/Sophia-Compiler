grammar sophia_v2;

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
    : IDENTIFIER IN (boolExp | calcExp)
    ;

loopBody
    : statement
    | loop
    | decision
    | block
    |
    ;

block
    : LBRACE (statement | loop | decision | block)* RBRACE
    ;

argument
    : IDENTIFIER COLON type
    | IDENTIFIER COLON type COMMA argument
    ;

variable
    : id=IDENTIFIER COLON type DELIM {System.out.println("VarDec:"+$id.text);}
    ;

statement
    : BREAK DELIM
    | CONTINUE DELIM
    | returnStatement
    | printFunction
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
    : NUMBER
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

printFunction
    : PRINTFUNC LPARANTHES (boolExp | calcExp) RPARANTHES
    ;

returnStatement
    : RETURN DELIM
    | RETURN IDENTIFIER DELIM
    | RETURN number DELIM
    | RETURN TRUE DELIM
    | RETURN FALSE DELIM
    | RETURN boolExp DELIM
    | RETURN calcExp DELIM
    ;


// from here are my codes

assignExp :
    exp (txt = ASSIGN exp {System.out.println("Operator:"+$txt.text);})+
    ;

exp : exp1 | exp2 | exp3 | exp4 | exp5 | exp6 | exp7 | exp8 | exp9 | exp10 | exp11 ;

exp11 :
    (expVar | paranthesBlock | exp10)
    (txt = OP11 (expVar | paranthesBlock | exp10) {System.out.println("Operator:"+$txt.text);})*
    ;
exp10 :
    (expVar | paranthesBlock | exp9)
    (txt = OP10 (expVar | paranthesBlock | exp9) {System.out.println("Operator:"+$txt.text);})*
    ;
exp9 :
    (expVar | paranthesBlock | exp8)
    (txt = OP9 (expVar | paranthesBlock | exp8) {System.out.println("Operator:"+$txt.text);})*
    ;
exp8 :
    (expVar | paranthesBlock | exp7)
    (txt = OP8 (expVar | paranthesBlock | exp7) {System.out.println("Operator:"+$txt.text);})*
    ;
exp7 :
    (expVar | paranthesBlock | exp6)
    (txt = (OP7 | OP3) (expVar | paranthesBlock | exp6) {System.out.println("Operator:"+$txt.text);})*
    ;
exp6 :
    (expVar | paranthesBlock | exp5)
    (txt = OP6 (expVar | paranthesBlock | exp5) {System.out.println("Operator:"+$txt.text);})*
    ;
exp5 :
    txt = (OP5 | OP4 | OP3) (expVar | paranthesBlock | exp4) {System.out.println("Operator:"+$txt.text);}
    | (expVar | paranthesBlock | exp4)
    ;
exp4 :
    (expVar | paranthesBlock | exp3) txt = OP4 {System.out.println("Operator:"+$txt.text);}
    | (expVar | paranthesBlock | exp3)
    ;

exp3 :
    (expVar | paranthesBlock | exp1) (exp2 | exp1)+ | (expVar | paranthesBlock)
    ;
exp2 :
    LBRACKET exp RBRACKET
    ;
exp1 :
    DOT IDENTIFIER
    ;

paranthesBlock : LPARANTHES exp RPARANTHES | LPARANTHES assignExp RPARANTHES ;

methodCall : IDENTIFIER argumentPart+ ;

newObject : NEW IDENTIFIER argumentPart ;

argumentPart : LPARANTHES (exp (COMMA exp)* | ) RPARANTHES ;

expVar : NUMBER | IDENTIFIER  | STRING_VALUE |expKeyWords | methodCall | newObject;

expKeyWords : BOOL_VALUE | NEW | THIS ;

OP3 : '-';
OP4 : '++' | '--';
OP5 : '!';
OP6 : '%' | '/' | '*';

OP7 : '+';
OP8 : RSIGN | LSIGN ;
OP9 : '==' | '!=' ;
OP10 : '&&';
OP11 : '||';

//end of my codes

ZERO
    : '0'
    ;

NUMBER
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

NEW
    : 'new'
    ;

THIS
    : 'this'
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

DOT
    : '.'
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
    : 'bool'
    ;

VOID
    : 'void'
    ;

LIST
    : 'list'
    ;

FOR
    :'for' {System.out.println("Loop:for");}
    ;

FOREACH
    :'foreach' {System.out.println("Loop:foreach");}
    ;

PRINTFUNC
    : 'print' {System.out.println("Built-in:print");}
    ;

IDENTIFIER
    :([A-Za-z] | '_') ([A-Za-z0-9] | '_')*
    ;

LBRACE
    :'{'
    ;

RBRACE
    :'}'
    ;

LPARANTHES
    :'('
    ;

RPARANTHES
    :')'
    ;

LBRACKET
    :'['
    ;

RBRACKET
    :']'
    ;

LSIGN
    :'<'
    ;

RSIGN
    :'>'
    ;

DELIM
    :';'
    ;

COLON
    :':'
    ;

COMMA
    :','
    ;

ASSIGN
    :'='
    ;

STRING_VALUE
    : '"' ~('"')* '"'
    ;

BOOL_VALUE
    : TRUE | FALSE
    ;

COMMENT
    : '//' ~( '\r' | '\n' )* -> skip
    ;

WS:
    [ \t\r\n] -> skip
    ;
