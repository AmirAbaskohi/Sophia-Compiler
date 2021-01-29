# Sophia-Compiler
This is a compiler for new Object Oriented language called Sophia.

## Documentation

#### General Structue
In this language, code file is in file in `.sop` extension. This file includes:

* One or more class
* One Main class that should have a constructor method
* Each class includes some variables(attributes) and some methods

#### General Rule Of Syntax
This language is case-sensetive. In this language tabs and spaces does not have impact on program output.
Details about scope and program lines will be explained further.

#### Comments
This language just support one line commands. This comments are in below format:

```
\\ This is my comment :)
```

#### Naming Rules
Names should follow these rules:

* Name should just contain `a..z`, `A..Z`, `_`, and digits.(There is no limit 
on name length)
* Should not start with digits
* It should not be from key words. Key words are:

| class | extends | this | def     |   func   | return |
|-------|---------|------|---------|:--------:|--------|
| if    | else    | for  | foreach | continue | break  |
| false | true    | int  | boolean |  string  | void   |
| list  | in      | null | new     |   print  |        |

* Each class name is unique
* Each method name in class in unique. There is no `method overloading` in 
this language
* Each variable name in each scope is unique, but you can use outer scope 
variables in inner scopes.
* It is not possibe to name same attribute and method in one class

### Class and Methods:
This is an example of class in Sophia:
```
class A extends B{
    x: int;
    y: bool;
    def A(p:int, q:bool){
        this.x = p;
        this.y = q;
    }
    def void foo(){
        print("foo");
    }
}
```
Main class should have a constructor without any arguments. Main does not 
inherit from any class.

In each method first we have variable declarations then statements.

Arguments are passed by value.

A class can have one constructor at most.

#### Inheritence:
Each class can at most inherits from another class.(In other word 
we don't have multi-Inheritence)

We do not have method overriding for class methods.

Also there is not overloading for class attributes.

#### this Key word
`this` key word is used to point to class that we are in that.
Using this key word we can access fields and methods of class.

#### Variables
Variable declaration in Sophia are in below format:

`identifier: type;`

In this language it is not possible to define multiple variables.

Sophia just have three primitive data type which are: `int`, `bool`, and `string`

Default values are:

|   type  |              default value             |
|:-------:|:--------------------------------------:|
|   int   |                    0                   |
|   bool  |                  false                 |
|  string |                   ""                   |
| objects |                  null                  |
|   list  | each member has it's own default value |

#### Pointer To Function:
Pointer to functions are in below format:
```
<void->returnType>
<argumentType1, argumentType2, ... -> returnType>
```

####  List:
Lists in Sophia are used for accumulating. The length is constant 
and can not be changed dynamically.

Below lists declarations are possible:
```
myList1: list(int, func<int->int>, bool);
myList2: list(number: int, fptr: func<int->int>>, bool)
myList2.number = 2;
myList1[2] = true;
myList1 = [3, myFunc, true];
```

#### Operators:
These operatoes are supported:
* A*B
* A-B
* A+B
* A/B
* A%B
* A = -20
* --A / ++A
* A++/A--
* A == B
* A != B
* A < B
* A > B
* A && B
* A || B
* !A
* =

#### Conditional Structures:
Conditional expressions are in below format:
```
if(cond1)
    // if body
else if(cond2)
    // else if body
else{
    // else body
}
```

#### Loops:
In this language two loop are possible:
* for loop
* foreach loop

```
for(intilizationStatement;conditionalStatement;updateStatement)
{
    // statements inside the body of loop
}

foreach(identifier in list)
{
    // statements inside the body of loop
}
```

#### Break And Continue
Also `break` and `continue` statements are possible to use.

#### Scopes
In general in Sophia these items are in new scope:
* Code lines in one class
* Parameters and code lines in one method
* Code lines in conditional and loop statements
* Code line in new scopes that starts with {}

#### Primitive Function
Sophia just has one primitive function `print`.

This function accepts an int, bool, or string value. `print` accepts 
expressions that has mentioned values. This is an example:

```
print("Hello World!");
```

### Phases:
* Phase1 -> Lexical Analyzer and Parser: sophia.g4

* Phase2:
    * Making AST Tree In Grammer
    * AST Tree Nodes
    * Name Analyzing
    * Printing Some Errors Realted To Name And Scoping
    * Printing AST Tree If There Was No Error

* Phase3:
    * Type checking
    * Checking base rules such as `Main` class conditions
    * Checking accessibilities

* Phase4:
    * Code generation using `Jasmin`
