// Generated from C:/Users/TheRealRondon/Desktop/Sophia-Phase2/src/main/grammar\Sophia.g4 by ANTLR 4.8
package parsers;

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SophiaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DEF=1, EXTENDS=2, CLASS=3, PRINT=4, FUNC=5, NEW=6, CONTINUE=7, BREAK=8, 
		RETURN=9, FOREACH=10, IN=11, FOR=12, IF=13, ELSE=14, BOOLEAN=15, STRING=16, 
		INT=17, VOID=18, NULL=19, LIST=20, TRUE=21, FALSE=22, THIS=23, ARROW=24, 
		GREATER_THAN=25, LESS_THAN=26, NOT_EQUAL=27, EQUAL=28, MULT=29, DIVIDE=30, 
		MOD=31, PLUS=32, MINUS=33, AND=34, OR=35, NOT=36, QUESTION_MARK=37, ASSIGN=38, 
		INCREMENT=39, DECREMENT=40, LPAR=41, RPAR=42, LBRACK=43, RBRACK=44, LBRACE=45, 
		RBRACE=46, SHARP=47, COMMA=48, DOT=49, COLON=50, SEMICOLLON=51, INT_VALUE=52, 
		IDENTIFIER=53, STRING_VALUE=54, COMMENT=55, WS=56;
	public static final int
		RULE_sophia = 0, RULE_program = 1, RULE_sophiaClass = 2, RULE_varDeclaration = 3, 
		RULE_method = 4, RULE_constructor = 5, RULE_methodArguments = 6, RULE_variableWithType = 7, 
		RULE_type = 8, RULE_classType = 9, RULE_listType = 10, RULE_listItemsTypes = 11, 
		RULE_listItemType = 12, RULE_functionPointerType = 13, RULE_typesWithComma = 14, 
		RULE_primitiveDataType = 15, RULE_methodBody = 16, RULE_statement = 17, 
		RULE_block = 18, RULE_assignmentStatement = 19, RULE_assignment = 20, 
		RULE_printStatement = 21, RULE_returnStatement = 22, RULE_methodCallStatement = 23, 
		RULE_methodCall = 24, RULE_methodCallArguments = 25, RULE_continueBreakStatement = 26, 
		RULE_forStatement = 27, RULE_foreachStatement = 28, RULE_ifStatement = 29, 
		RULE_expression = 30, RULE_orExpression = 31, RULE_andExpression = 32, 
		RULE_equalityExpression = 33, RULE_relationalExpression = 34, RULE_additiveExpression = 35, 
		RULE_multiplicativeExpression = 36, RULE_preUnaryExpression = 37, RULE_postUnaryExpression = 38, 
		RULE_accessExpression = 39, RULE_otherExpression = 40, RULE_newExpression = 41, 
		RULE_values = 42, RULE_boolValue = 43, RULE_listValue = 44, RULE_identifier = 45;
	private static String[] makeRuleNames() {
		return new String[] {
			"sophia", "program", "sophiaClass", "varDeclaration", "method", "constructor", 
			"methodArguments", "variableWithType", "type", "classType", "listType", 
			"listItemsTypes", "listItemType", "functionPointerType", "typesWithComma", 
			"primitiveDataType", "methodBody", "statement", "block", "assignmentStatement", 
			"assignment", "printStatement", "returnStatement", "methodCallStatement", 
			"methodCall", "methodCallArguments", "continueBreakStatement", "forStatement", 
			"foreachStatement", "ifStatement", "expression", "orExpression", "andExpression", 
			"equalityExpression", "relationalExpression", "additiveExpression", "multiplicativeExpression", 
			"preUnaryExpression", "postUnaryExpression", "accessExpression", "otherExpression", 
			"newExpression", "values", "boolValue", "listValue", "identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'def'", "'extends'", "'class'", "'print'", "'func'", "'new'", 
			"'continue'", "'break'", "'return'", "'foreach'", "'in'", "'for'", "'if'", 
			"'else'", "'bool'", "'string'", "'int'", "'void'", "'null'", "'list'", 
			"'true'", "'false'", "'this'", "'->'", "'>'", "'<'", "'!='", "'=='", 
			"'*'", "'/'", "'%'", "'+'", "'-'", "'&&'", "'||'", "'!'", "'?'", "'='", 
			"'++'", "'--'", "'('", "')'", "'['", "']'", "'{'", "'}'", "'#'", "','", 
			"'.'", "':'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DEF", "EXTENDS", "CLASS", "PRINT", "FUNC", "NEW", "CONTINUE", 
			"BREAK", "RETURN", "FOREACH", "IN", "FOR", "IF", "ELSE", "BOOLEAN", "STRING", 
			"INT", "VOID", "NULL", "LIST", "TRUE", "FALSE", "THIS", "ARROW", "GREATER_THAN", 
			"LESS_THAN", "NOT_EQUAL", "EQUAL", "MULT", "DIVIDE", "MOD", "PLUS", "MINUS", 
			"AND", "OR", "NOT", "QUESTION_MARK", "ASSIGN", "INCREMENT", "DECREMENT", 
			"LPAR", "RPAR", "LBRACK", "RBRACK", "LBRACE", "RBRACE", "SHARP", "COMMA", 
			"DOT", "COLON", "SEMICOLLON", "INT_VALUE", "IDENTIFIER", "STRING_VALUE", 
			"COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Sophia.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SophiaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SophiaContext extends ParserRuleContext {
		public Program sophiaProgram;
		public ProgramContext p;
		public TerminalNode EOF() { return getToken(SophiaParser.EOF, 0); }
		public ProgramContext program() {
			return getRuleContext(ProgramContext.class,0);
		}
		public SophiaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sophia; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterSophia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitSophia(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitSophia(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SophiaContext sophia() throws RecognitionException {
		SophiaContext _localctx = new SophiaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sophia);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			((SophiaContext)_localctx).p = program();
			 _localctx.sophiaProgram = ((SophiaContext)_localctx).p.programRet 
			setState(94);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public Program programRet;
		public SophiaClassContext c;
		public List<SophiaClassContext> sophiaClass() {
			return getRuleContexts(SophiaClassContext.class);
		}
		public SophiaClassContext sophiaClass(int i) {
			return getRuleContext(SophiaClassContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			        ((ProgramContext)_localctx).programRet =  new Program();
			        _localctx.programRet.setLine(1);
			    
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CLASS) {
				{
				{
				setState(97);
				((ProgramContext)_localctx).c = sophiaClass();
				 _localctx.programRet.addClass(((ProgramContext)_localctx).c.sophiaClassRet); 
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SophiaClassContext extends ParserRuleContext {
		public ClassDeclaration sophiaClassRet;
		public Token cl;
		public IdentifierContext cn;
		public IdentifierContext pcn;
		public VarDeclarationContext f;
		public MethodContext m;
		public ConstructorContext cntr;
		public VarDeclarationContext f2;
		public MethodContext m2;
		public VarDeclarationContext f3;
		public MethodContext m3;
		public TerminalNode LBRACE() { return getToken(SophiaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SophiaParser.RBRACE, 0); }
		public TerminalNode CLASS() { return getToken(SophiaParser.CLASS, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode EXTENDS() { return getToken(SophiaParser.EXTENDS, 0); }
		public ConstructorContext constructor() {
			return getRuleContext(ConstructorContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public SophiaClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sophiaClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterSophiaClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitSophiaClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitSophiaClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SophiaClassContext sophiaClass() throws RecognitionException {
		SophiaClassContext _localctx = new SophiaClassContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sophiaClass);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			((SophiaClassContext)_localctx).cl = match(CLASS);
			setState(106);
			((SophiaClassContext)_localctx).cn = identifier();

			        ((SophiaClassContext)_localctx).sophiaClassRet =  new ClassDeclaration(((SophiaClassContext)_localctx).cn.identifierRet);
			        _localctx.sophiaClassRet.setLine(((SophiaClassContext)_localctx).cl.getLine());
			    
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(108);
				match(EXTENDS);
				setState(109);
				((SophiaClassContext)_localctx).pcn = identifier();
				 _localctx.sophiaClassRet.setParentClassName(((SophiaClassContext)_localctx).pcn.identifierRet); 
				}
			}

			setState(114);
			match(LBRACE);
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				{
				setState(123);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(121);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case IDENTIFIER:
							{
							setState(115);
							((SophiaClassContext)_localctx).f = varDeclaration();

							        _localctx.sophiaClassRet.addField(new FieldDeclaration(((SophiaClassContext)_localctx).f.varDeclarationRet));
							    
							}
							break;
						case DEF:
							{
							setState(118);
							((SophiaClassContext)_localctx).m = method();

							        _localctx.sophiaClassRet.addMethod(((SophiaClassContext)_localctx).m.methodDeclarationRet);
							    
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(125);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				setState(126);
				((SophiaClassContext)_localctx).cntr = constructor();

				        _localctx.sophiaClassRet.setConstructor(((SophiaClassContext)_localctx).cntr.constructorDeclarationRet);
				    
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DEF || _la==IDENTIFIER) {
					{
					setState(134);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case IDENTIFIER:
						{
						setState(128);
						((SophiaClassContext)_localctx).f2 = varDeclaration();

						        _localctx.sophiaClassRet.addField(new FieldDeclaration(((SophiaClassContext)_localctx).f2.varDeclarationRet));
						    
						}
						break;
					case DEF:
						{
						setState(131);
						((SophiaClassContext)_localctx).m2 = method();

						        _localctx.sophiaClassRet.addMethod(((SophiaClassContext)_localctx).m2.methodDeclarationRet);
						    
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(138);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case 2:
				{
				{
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DEF || _la==IDENTIFIER) {
					{
					setState(145);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case IDENTIFIER:
						{
						setState(139);
						((SophiaClassContext)_localctx).f3 = varDeclaration();

						        _localctx.sophiaClassRet.addField(new FieldDeclaration(((SophiaClassContext)_localctx).f3.varDeclarationRet));
						    
						}
						break;
					case DEF:
						{
						setState(142);
						((SophiaClassContext)_localctx).m3 = method();

						        _localctx.sophiaClassRet.addMethod(((SophiaClassContext)_localctx).m3.methodDeclarationRet);
						    
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(149);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			}
			setState(152);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public VarDeclaration varDeclarationRet;
		public IdentifierContext id;
		public TypeContext t;
		public TerminalNode COLON() { return getToken(SophiaParser.COLON, 0); }
		public TerminalNode SEMICOLLON() { return getToken(SophiaParser.SEMICOLLON, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			((VarDeclarationContext)_localctx).id = identifier();
			setState(155);
			match(COLON);
			setState(156);
			((VarDeclarationContext)_localctx).t = type();
			setState(157);
			match(SEMICOLLON);

			        ((VarDeclarationContext)_localctx).varDeclarationRet =  new VarDeclaration(((VarDeclarationContext)_localctx).id.identifierRet, ((VarDeclarationContext)_localctx).t.typeRet);
			        _localctx.varDeclarationRet.setLine(((VarDeclarationContext)_localctx).id.identifierRet.getLine());
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodContext extends ParserRuleContext {
		public MethodDeclaration methodDeclarationRet;
		public Type returnType;
		public Token d;
		public TypeContext t;
		public IdentifierContext id;
		public MethodArgumentsContext margs;
		public MethodBodyContext mbody;
		public TerminalNode LPAR() { return getToken(SophiaParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SophiaParser.RPAR, 0); }
		public TerminalNode LBRACE() { return getToken(SophiaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SophiaParser.RBRACE, 0); }
		public TerminalNode DEF() { return getToken(SophiaParser.DEF, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public MethodArgumentsContext methodArguments() {
			return getRuleContext(MethodArgumentsContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public TerminalNode VOID() { return getToken(SophiaParser.VOID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_method);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			((MethodContext)_localctx).d = match(DEF);
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNC:
			case BOOLEAN:
			case STRING:
			case INT:
			case LIST:
			case IDENTIFIER:
				{
				setState(161);
				((MethodContext)_localctx).t = type();
				 ((MethodContext)_localctx).returnType =  ((MethodContext)_localctx).t.typeRet; 
				}
				break;
			case VOID:
				{
				setState(164);
				match(VOID);
				 ((MethodContext)_localctx).returnType =  new NullType(); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(168);
			((MethodContext)_localctx).id = identifier();

			        ((MethodContext)_localctx).methodDeclarationRet =  new MethodDeclaration(((MethodContext)_localctx).id.identifierRet, _localctx.returnType);
			        _localctx.methodDeclarationRet.setLine(((MethodContext)_localctx).d.getLine());
			    
			setState(170);
			match(LPAR);
			setState(171);
			((MethodContext)_localctx).margs = methodArguments();

			        _localctx.methodDeclarationRet.setArgs(((MethodContext)_localctx).margs.methodArgumentsRet);
			    
			setState(173);
			match(RPAR);
			setState(174);
			match(LBRACE);
			setState(175);
			((MethodContext)_localctx).mbody = methodBody();

			        _localctx.methodDeclarationRet.setLocalVars(((MethodContext)_localctx).mbody.methodLocalVarsRet);
			        _localctx.methodDeclarationRet.setBody(((MethodContext)_localctx).mbody.methodBodyRet);
			    
			setState(177);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorContext extends ParserRuleContext {
		public ConstructorDeclaration constructorDeclarationRet;
		public Token d;
		public IdentifierContext id;
		public MethodArgumentsContext margs;
		public MethodBodyContext mbody;
		public TerminalNode LPAR() { return getToken(SophiaParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SophiaParser.RPAR, 0); }
		public TerminalNode LBRACE() { return getToken(SophiaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SophiaParser.RBRACE, 0); }
		public TerminalNode DEF() { return getToken(SophiaParser.DEF, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public MethodArgumentsContext methodArguments() {
			return getRuleContext(MethodArgumentsContext.class,0);
		}
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public ConstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitConstructor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitConstructor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorContext constructor() throws RecognitionException {
		ConstructorContext _localctx = new ConstructorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_constructor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			((ConstructorContext)_localctx).d = match(DEF);
			setState(180);
			((ConstructorContext)_localctx).id = identifier();
			setState(181);
			match(LPAR);

			        ((ConstructorContext)_localctx).constructorDeclarationRet =  new ConstructorDeclaration(((ConstructorContext)_localctx).id.identifierRet);
			        _localctx.constructorDeclarationRet.setLine(((ConstructorContext)_localctx).d.getLine());
			    
			setState(183);
			((ConstructorContext)_localctx).margs = methodArguments();

			        _localctx.constructorDeclarationRet.setArgs(((ConstructorContext)_localctx).margs.methodArgumentsRet);
			    
			setState(185);
			match(RPAR);
			setState(186);
			match(LBRACE);
			setState(187);
			((ConstructorContext)_localctx).mbody = methodBody();

			        _localctx.constructorDeclarationRet.setLocalVars(((ConstructorContext)_localctx).mbody.methodLocalVarsRet);
			        _localctx.constructorDeclarationRet.setBody(((ConstructorContext)_localctx).mbody.methodBodyRet);
			    
			setState(189);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodArgumentsContext extends ParserRuleContext {
		public ArrayList<VarDeclaration> methodArgumentsRet;
		public VariableWithTypeContext v1;
		public VariableWithTypeContext v2;
		public List<VariableWithTypeContext> variableWithType() {
			return getRuleContexts(VariableWithTypeContext.class);
		}
		public VariableWithTypeContext variableWithType(int i) {
			return getRuleContext(VariableWithTypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SophiaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SophiaParser.COMMA, i);
		}
		public MethodArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterMethodArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitMethodArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitMethodArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodArgumentsContext methodArguments() throws RecognitionException {
		MethodArgumentsContext _localctx = new MethodArgumentsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_methodArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((MethodArgumentsContext)_localctx).methodArgumentsRet =  new ArrayList<>(); 
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(192);
				((MethodArgumentsContext)_localctx).v1 = variableWithType();
				 _localctx.methodArgumentsRet.add(((MethodArgumentsContext)_localctx).v1.variableWithTypeRet) 
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(194);
					match(COMMA);
					setState(195);
					((MethodArgumentsContext)_localctx).v2 = variableWithType();
					 _localctx.methodArgumentsRet.add(((MethodArgumentsContext)_localctx).v2.variableWithTypeRet) 
					}
					}
					setState(202);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableWithTypeContext extends ParserRuleContext {
		public VarDeclaration variableWithTypeRet;
		public IdentifierContext id;
		public TypeContext t;
		public TerminalNode COLON() { return getToken(SophiaParser.COLON, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableWithTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableWithType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterVariableWithType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitVariableWithType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitVariableWithType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableWithTypeContext variableWithType() throws RecognitionException {
		VariableWithTypeContext _localctx = new VariableWithTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variableWithType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			((VariableWithTypeContext)_localctx).id = identifier();
			setState(206);
			match(COLON);
			setState(207);
			((VariableWithTypeContext)_localctx).t = type();

			        ((VariableWithTypeContext)_localctx).variableWithTypeRet =  new VarDeclaration(((VariableWithTypeContext)_localctx).id.identifierRet, ((VariableWithTypeContext)_localctx).t.typeRet);
			        _localctx.variableWithTypeRet.setLine(((VariableWithTypeContext)_localctx).id.identifierRet.getLine());
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public Type typeRet;
		public PrimitiveDataTypeContext pdt;
		public ListTypeContext lt;
		public FunctionPointerTypeContext fpt;
		public ClassTypeContext ct;
		public PrimitiveDataTypeContext primitiveDataType() {
			return getRuleContext(PrimitiveDataTypeContext.class,0);
		}
		public ListTypeContext listType() {
			return getRuleContext(ListTypeContext.class,0);
		}
		public FunctionPointerTypeContext functionPointerType() {
			return getRuleContext(FunctionPointerTypeContext.class,0);
		}
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		try {
			setState(222);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case STRING:
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(210);
				((TypeContext)_localctx).pdt = primitiveDataType();
				 ((TypeContext)_localctx).typeRet =  ((TypeContext)_localctx).pdt.primitiveDataTypeRet; 
				}
				break;
			case LIST:
				enterOuterAlt(_localctx, 2);
				{
				setState(213);
				((TypeContext)_localctx).lt = listType();
				 ((TypeContext)_localctx).typeRet =  ((TypeContext)_localctx).lt.listTypeRet; 
				}
				break;
			case FUNC:
				enterOuterAlt(_localctx, 3);
				{
				setState(216);
				((TypeContext)_localctx).fpt = functionPointerType();
				 ((TypeContext)_localctx).typeRet =  ((TypeContext)_localctx).fpt.functionPointerTypeRet; 
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(219);
				((TypeContext)_localctx).ct = classType();
				 ((TypeContext)_localctx).typeRet =  ((TypeContext)_localctx).ct.classTypeRet; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassTypeContext extends ParserRuleContext {
		public ClassType classTypeRet;
		public IdentifierContext id;
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ClassTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterClassType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitClassType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitClassType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassTypeContext classType() throws RecognitionException {
		ClassTypeContext _localctx = new ClassTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			((ClassTypeContext)_localctx).id = identifier();
			 classTypeRet = new ClassType(((ClassTypeContext)_localctx).id.identifierRet); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListTypeContext extends ParserRuleContext {
		public ListType listTypeRet;
		public Token size;
		public TypeContext t;
		public ListItemsTypesContext lit;
		public TerminalNode LIST() { return getToken(SophiaParser.LIST, 0); }
		public TerminalNode LPAR() { return getToken(SophiaParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SophiaParser.RPAR, 0); }
		public TerminalNode SHARP() { return getToken(SophiaParser.SHARP, 0); }
		public TerminalNode INT_VALUE() { return getToken(SophiaParser.INT_VALUE, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ListItemsTypesContext listItemsTypes() {
			return getRuleContext(ListItemsTypesContext.class,0);
		}
		public ListTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterListType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitListType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitListType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListTypeContext listType() throws RecognitionException {
		ListTypeContext _localctx = new ListTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_listType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			match(LIST);
			setState(228);
			match(LPAR);
			setState(238);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_VALUE:
				{
				{
				setState(229);
				((ListTypeContext)_localctx).size = match(INT_VALUE);
				setState(230);
				match(SHARP);
				setState(231);
				((ListTypeContext)_localctx).t = type();
				}

				        ((ListTypeContext)_localctx).listTypeRet =  new ListType(Integer.parseInt(((ListTypeContext)_localctx).size.getText()), new ListNameType(((ListTypeContext)_localctx).t.typeRet));
				    
				}
				break;
			case FUNC:
			case BOOLEAN:
			case STRING:
			case INT:
			case LIST:
			case IDENTIFIER:
				{
				{
				setState(235);
				((ListTypeContext)_localctx).lit = listItemsTypes();
				}
				 ((ListTypeContext)_localctx).listTypeRet =  new ListType(((ListTypeContext)_localctx).lit.listItemTypesRet); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(240);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListItemsTypesContext extends ParserRuleContext {
		public ArrayList<ListNameType> listItemTypesRet;
		public ListItemTypeContext lit1;
		public ListItemTypeContext lit2;
		public List<ListItemTypeContext> listItemType() {
			return getRuleContexts(ListItemTypeContext.class);
		}
		public ListItemTypeContext listItemType(int i) {
			return getRuleContext(ListItemTypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SophiaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SophiaParser.COMMA, i);
		}
		public ListItemsTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listItemsTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterListItemsTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitListItemsTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitListItemsTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListItemsTypesContext listItemsTypes() throws RecognitionException {
		ListItemsTypesContext _localctx = new ListItemsTypesContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_listItemsTypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 listItemTypesRet = new ArrayList<>(); 
			setState(243);
			((ListItemsTypesContext)_localctx).lit1 = listItemType();
			 listItemTypesRet.add(((ListItemsTypesContext)_localctx).lit1.listItemTypeRet); 
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(245);
				match(COMMA);
				setState(246);
				((ListItemsTypesContext)_localctx).lit2 = listItemType();
				 listItemTypesRet.add(((ListItemsTypesContext)_localctx).lit2.listItemTypeRet); 
				}
				}
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListItemTypeContext extends ParserRuleContext {
		public ListNameType listItemTypeRet;
		public VariableWithTypeContext vwt;
		public TypeContext t;
		public VariableWithTypeContext variableWithType() {
			return getRuleContext(VariableWithTypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ListItemTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listItemType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterListItemType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitListItemType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitListItemType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListItemTypeContext listItemType() throws RecognitionException {
		ListItemTypeContext _localctx = new ListItemTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_listItemType);
		try {
			setState(260);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				((ListItemTypeContext)_localctx).vwt = variableWithType();
				 listItemTypeRet = new ListNameType(((ListItemTypeContext)_localctx).vwt.variableWithTypeRet); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
				((ListItemTypeContext)_localctx).t = type();
				 listItemTypeRet = new ListNameType(((ListItemTypeContext)_localctx).t.typeRet); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionPointerTypeContext extends ParserRuleContext {
		public FptrType functionPointerTypeRet;
		public TypesWithCommaContext twc;
		public TypeContext t;
		public TerminalNode FUNC() { return getToken(SophiaParser.FUNC, 0); }
		public TerminalNode LESS_THAN() { return getToken(SophiaParser.LESS_THAN, 0); }
		public TerminalNode ARROW() { return getToken(SophiaParser.ARROW, 0); }
		public TerminalNode GREATER_THAN() { return getToken(SophiaParser.GREATER_THAN, 0); }
		public List<TerminalNode> VOID() { return getTokens(SophiaParser.VOID); }
		public TerminalNode VOID(int i) {
			return getToken(SophiaParser.VOID, i);
		}
		public TypesWithCommaContext typesWithComma() {
			return getRuleContext(TypesWithCommaContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunctionPointerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionPointerType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterFunctionPointerType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitFunctionPointerType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitFunctionPointerType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionPointerTypeContext functionPointerType() throws RecognitionException {
		FunctionPointerTypeContext _localctx = new FunctionPointerTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_functionPointerType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(FUNC);
			setState(263);
			match(LESS_THAN);
			 ((FunctionPointerTypeContext)_localctx).functionPointerTypeRet =  new FptrType(); 
			setState(270);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				{
				setState(265);
				match(VOID);
				 _localctx.functionPointerTypeRet.addArgumentType(new NulType()); 
				}
				break;
			case FUNC:
			case BOOLEAN:
			case STRING:
			case INT:
			case LIST:
			case IDENTIFIER:
				{
				setState(267);
				((FunctionPointerTypeContext)_localctx).twc = typesWithComma();
				 _localctx.functionPointerTypeRet.setArgumentsTypes(((FunctionPointerTypeContext)_localctx).twc.typeWithCommaRet); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(272);
			match(ARROW);
			setState(278);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VOID:
				{
				setState(273);
				match(VOID);
				 _localctx.functionPointerTypeRet.setReturnType(new NulType()); 
				}
				break;
			case FUNC:
			case BOOLEAN:
			case STRING:
			case INT:
			case LIST:
			case IDENTIFIER:
				{
				setState(275);
				((FunctionPointerTypeContext)_localctx).t = type();
				 _localctx.functionPointerTypeRet.setReturnType(((FunctionPointerTypeContext)_localctx).t.typeRet); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(280);
			match(GREATER_THAN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypesWithCommaContext extends ParserRuleContext {
		public ArrayList<Type> typeWithCommaRet;
		public TypeContext t1;
		public TypeContext t2;
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SophiaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SophiaParser.COMMA, i);
		}
		public TypesWithCommaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typesWithComma; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterTypesWithComma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitTypesWithComma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitTypesWithComma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypesWithCommaContext typesWithComma() throws RecognitionException {
		TypesWithCommaContext _localctx = new TypesWithCommaContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_typesWithComma);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 typeWithCommaRet = new ArrayList<>(); 
			setState(283);
			((TypesWithCommaContext)_localctx).t1 = type();
			 _localctx.typeWithCommaRet.add(((TypesWithCommaContext)_localctx).t1.typeRet); 
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(285);
				match(COMMA);
				setState(286);
				((TypesWithCommaContext)_localctx).t2 = type();
				 _localctx.typeWithCommaRet.add(((TypesWithCommaContext)_localctx).t2.typeRet); 
				}
				}
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimitiveDataTypeContext extends ParserRuleContext {
		public Type primitiveDataTypeRet;
		public TerminalNode INT() { return getToken(SophiaParser.INT, 0); }
		public TerminalNode STRING() { return getToken(SophiaParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(SophiaParser.BOOLEAN, 0); }
		public PrimitiveDataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveDataType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterPrimitiveDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitPrimitiveDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitPrimitiveDataType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimitiveDataTypeContext primitiveDataType() throws RecognitionException {
		PrimitiveDataTypeContext _localctx = new PrimitiveDataTypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_primitiveDataType);
		try {
			setState(300);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(294);
				match(INT);
				 ((PrimitiveDataTypeContext)_localctx).primitiveDataTypeRet =  new IntType(); 
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(296);
				match(STRING);
				 ((PrimitiveDataTypeContext)_localctx).primitiveDataTypeRet =  new StringType(); 
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 3);
				{
				setState(298);
				match(BOOLEAN);
				 ((PrimitiveDataTypeContext)_localctx).primitiveDataTypeRet =  new BoolType(); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodBodyContext extends ParserRuleContext {
		public ArrayList<VarDeclaration> methodLocalVarsRet;
		public ArrayList<Statement> methodBodyRet;
		public VarDeclarationContext vd;
		public StatementContext s;
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterMethodBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitMethodBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitMethodBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_methodBody);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{

			        ((MethodBodyContext)_localctx).methodLocalVarsRet =  new ArrayList<>();
			        ((MethodBodyContext)_localctx).methodBodyRet =  new ArrayList<>();
			    
			setState(308);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(303);
					((MethodBodyContext)_localctx).vd = varDeclaration();
					 _localctx.methodLocalVarsRet.add(((MethodBodyContext)_localctx).vd.varDeclarationRet); 
					}
					} 
				}
				setState(310);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRINT) | (1L << NEW) | (1L << CONTINUE) | (1L << BREAK) | (1L << RETURN) | (1L << FOREACH) | (1L << FOR) | (1L << IF) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << THIS) | (1L << MINUS) | (1L << NOT) | (1L << INCREMENT) | (1L << DECREMENT) | (1L << LPAR) | (1L << LBRACK) | (1L << LBRACE) | (1L << INT_VALUE) | (1L << IDENTIFIER) | (1L << STRING_VALUE))) != 0)) {
				{
				{
				setState(311);
				((MethodBodyContext)_localctx).s = statement();
				 _localctx.methodLocalVarsRet.add(((MethodBodyContext)_localctx).s.statementRet); 
				}
				}
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Statement statementRet;
		public ForStatementContext forStmnt;
		public ForeachStatementContext foreachStmnt;
		public IfStatementContext ifStmnt;
		public AssignmentStatementContext assignStmnt;
		public PrintStatementContext pStmnt;
		public ContinueBreakStatementContext cbStmnt;
		public MethodCallStatementContext methcStmnt;
		public ReturnStatementContext rStmnt;
		public BlockContext bStmnt;
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public ForeachStatementContext foreachStatement() {
			return getRuleContext(ForeachStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public ContinueBreakStatementContext continueBreakStatement() {
			return getRuleContext(ContinueBreakStatementContext.class,0);
		}
		public MethodCallStatementContext methodCallStatement() {
			return getRuleContext(MethodCallStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_statement);
		try {
			setState(346);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				((StatementContext)_localctx).forStmnt = forStatement();
				 ((StatementContext)_localctx).statementRet =  ((StatementContext)_localctx).forStmnt.forStatementRet; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(322);
				((StatementContext)_localctx).foreachStmnt = foreachStatement();
				 ((StatementContext)_localctx).statementRet =  ((StatementContext)_localctx).foreachStmnt.foreachStatementRet; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(325);
				((StatementContext)_localctx).ifStmnt = ifStatement();
				 ((StatementContext)_localctx).statementRet =  ((StatementContext)_localctx).ifStmnt.ifStatementRet; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(328);
				((StatementContext)_localctx).assignStmnt = assignmentStatement();
				 ((StatementContext)_localctx).statementRet =  ((StatementContext)_localctx).assignStmnt.assignmentStatementRet; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(331);
				((StatementContext)_localctx).pStmnt = printStatement();
				 ((StatementContext)_localctx).statementRet =  ((StatementContext)_localctx).pStmnt.printStatementRet; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(334);
				((StatementContext)_localctx).cbStmnt = continueBreakStatement();
				 ((StatementContext)_localctx).statementRet =  ((StatementContext)_localctx).cbStmnt.continueBreakStatementRet; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(337);
				((StatementContext)_localctx).methcStmnt = methodCallStatement();
				 ((StatementContext)_localctx).statementRet =  ((StatementContext)_localctx).methcStmnt.methodCallStatementRet; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(340);
				((StatementContext)_localctx).rStmnt = returnStatement();
				 ((StatementContext)_localctx).statementRet =  ((StatementContext)_localctx).rStmnt.returnStatementRet; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(343);
				((StatementContext)_localctx).bStmnt = block();
				 ((StatementContext)_localctx).statementRet =  ((StatementContext)_localctx).bStmnt.blockRet; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public BlockStmt blockRet;
		public StatementContext s;
		public TerminalNode LBRACE() { return getToken(SophiaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SophiaParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 blockRet = new BlockStmt(); 
			setState(349);
			match(LBRACE);
			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PRINT) | (1L << NEW) | (1L << CONTINUE) | (1L << BREAK) | (1L << RETURN) | (1L << FOREACH) | (1L << FOR) | (1L << IF) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << THIS) | (1L << MINUS) | (1L << NOT) | (1L << INCREMENT) | (1L << DECREMENT) | (1L << LPAR) | (1L << LBRACK) | (1L << LBRACE) | (1L << INT_VALUE) | (1L << IDENTIFIER) | (1L << STRING_VALUE))) != 0)) {
				{
				{
				setState(350);
				((BlockContext)_localctx).s = statement();
				 blockRet.addStatement(((BlockContext)_localctx).s.statementRet); 
				}
				}
				setState(357);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(358);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentStatementContext extends ParserRuleContext {
		public AssignmentStmt assignmentStatementRet;
		public AssignmentContext a;
		public TerminalNode SEMICOLLON() { return getToken(SophiaParser.SEMICOLLON, 0); }
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			((AssignmentStatementContext)_localctx).a = assignment();
			 ((AssignmentStatementContext)_localctx).assignmentStatementRet =  ((AssignmentStatementContext)_localctx).a.assignRet; 
			setState(362);
			match(SEMICOLLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public AssignmentStmt assignRet;
		public OrExpressionContext oex;
		public Token a;
		public ExpressionContext ex;
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(SophiaParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			((AssignmentContext)_localctx).oex = orExpression();
			setState(365);
			((AssignmentContext)_localctx).a = match(ASSIGN);
			setState(366);
			((AssignmentContext)_localctx).ex = expression();
			 ((AssignmentContext)_localctx).assignRet =  new AssignmentStmt(((AssignmentContext)_localctx).oex.orExpressionRet, ((AssignmentContext)_localctx).ex.expressionRet); _localctx.assignRet.setLine(((AssignmentContext)_localctx).a.getLine()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrintStatementContext extends ParserRuleContext {
		public PrintStmt printStatementRet;
		public Token p;
		public ExpressionContext exp;
		public TerminalNode LPAR() { return getToken(SophiaParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SophiaParser.RPAR, 0); }
		public TerminalNode SEMICOLLON() { return getToken(SophiaParser.SEMICOLLON, 0); }
		public TerminalNode PRINT() { return getToken(SophiaParser.PRINT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterPrintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitPrintStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitPrintStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_printStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			((PrintStatementContext)_localctx).p = match(PRINT);
			setState(370);
			match(LPAR);
			setState(371);
			((PrintStatementContext)_localctx).exp = expression();
			 ((PrintStatementContext)_localctx).printStatementRet =  new PrintStmt(((PrintStatementContext)_localctx).exp.expressionRet); _localctx.printStatementRet.setLine(((PrintStatementContext)_localctx).p.getLine()); 
			setState(373);
			match(RPAR);
			setState(374);
			match(SEMICOLLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public ReturnStmt returnStatementRet;
		public Token r;
		public ExpressionContext exp;
		public TerminalNode SEMICOLLON() { return getToken(SophiaParser.SEMICOLLON, 0); }
		public TerminalNode RETURN() { return getToken(SophiaParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitReturnStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitReturnStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_returnStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			((ReturnStatementContext)_localctx).r = match(RETURN);
			setState(377);
			((ReturnStatementContext)_localctx).exp = expression();
			setState(378);
			if (!( ((ReturnStatementContext)_localctx).returnStatementRet =  new ReturnStmt(((ReturnStatementContext)_localctx).exp.expressionRet); _localctx.returnStatementRet.setLine(((ReturnStatementContext)_localctx).r.getLine()); )) throw new FailedPredicateException(this, " $returnStatementRet = new ReturnStmt($exp.expressionRet); $returnStatementRet.setLine($r.getLine()); ");
			setState(379);
			match(SEMICOLLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodCallStatementContext extends ParserRuleContext {
		public MethodCallStmt methodCallStatementRet;
		public MethodCallContext mc;
		public TerminalNode SEMICOLLON() { return getToken(SophiaParser.SEMICOLLON, 0); }
		public MethodCallContext methodCall() {
			return getRuleContext(MethodCallContext.class,0);
		}
		public MethodCallStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCallStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterMethodCallStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitMethodCallStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitMethodCallStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodCallStatementContext methodCallStatement() throws RecognitionException {
		MethodCallStatementContext _localctx = new MethodCallStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_methodCallStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			((MethodCallStatementContext)_localctx).mc = methodCall();
			 ((MethodCallStatementContext)_localctx).methodCallStatementRet =  new MethodCallStmt(((MethodCallStatementContext)_localctx).mc.methodCallRet); 
			setState(383);
			match(SEMICOLLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodCallContext extends ParserRuleContext {
		public MethodCall methodCallRet;
		public Expression instance;
		public OtherExpressionContext oe;
		public MethodCallArgumentsContext mca1;
		public IdentifierContext id;
		public ExpressionContext e;
		public Token l;
		public MethodCallArgumentsContext mca2;
		public OtherExpressionContext otherExpression() {
			return getRuleContext(OtherExpressionContext.class,0);
		}
		public List<TerminalNode> RPAR() { return getTokens(SophiaParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(SophiaParser.RPAR, i);
		}
		public List<TerminalNode> LPAR() { return getTokens(SophiaParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(SophiaParser.LPAR, i);
		}
		public List<MethodCallArgumentsContext> methodCallArguments() {
			return getRuleContexts(MethodCallArgumentsContext.class);
		}
		public MethodCallArgumentsContext methodCallArguments(int i) {
			return getRuleContext(MethodCallArgumentsContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(SophiaParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(SophiaParser.DOT, i);
		}
		public List<TerminalNode> LBRACK() { return getTokens(SophiaParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(SophiaParser.LBRACK, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(SophiaParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(SophiaParser.RBRACK, i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MethodCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterMethodCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitMethodCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitMethodCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodCallContext methodCall() throws RecognitionException {
		MethodCallContext _localctx = new MethodCallContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_methodCall);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(385);
			((MethodCallContext)_localctx).oe = otherExpression();
			 ((MethodCallContext)_localctx).instance =  ((MethodCallContext)_localctx).oe.otherExpressionRet; 
			setState(406);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(404);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case LPAR:
						{
						{
						setState(387);
						match(LPAR);
						setState(388);
						((MethodCallContext)_localctx).mca1 = methodCallArguments();
						setState(389);
						match(RPAR);
						}
						 ((MethodCallContext)_localctx).instance =  new MethodCall(_localctx.instance, ((MethodCallContext)_localctx).mca1.methodCallArgumentsRet); 
						}
						break;
					case DOT:
						{
						{
						setState(393);
						match(DOT);
						setState(394);
						((MethodCallContext)_localctx).id = identifier();
						}
						 ((MethodCallContext)_localctx).instance =  new ObjectOrListMemberAccess(_localctx.instance, ((MethodCallContext)_localctx).id.identifierRet); 
						}
						break;
					case LBRACK:
						{
						{
						setState(398);
						match(LBRACK);
						setState(399);
						((MethodCallContext)_localctx).e = expression();
						setState(400);
						match(RBRACK);
						}
						 ((MethodCallContext)_localctx).instance =  new ListAccessByIndex(_localctx.instance, ((MethodCallContext)_localctx).e.expressionRet); 
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(408);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			{
			setState(409);
			((MethodCallContext)_localctx).l = match(LPAR);
			setState(410);
			((MethodCallContext)_localctx).mca2 = methodCallArguments();
			setState(411);
			match(RPAR);
			}

			        ((MethodCallContext)_localctx).methodCallRet =  new MethodCall(_localctx.instance, ((MethodCallContext)_localctx).mca2.methodCallArgumentsRet);
			        _localctx.methodCallRet.setLine(((MethodCallContext)_localctx).l.getLine());
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodCallArgumentsContext extends ParserRuleContext {
		public ArrayList<Expression> methodCallArgumentsRet;
		public ExpressionContext exp1;
		public ExpressionContext exp2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SophiaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SophiaParser.COMMA, i);
		}
		public MethodCallArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodCallArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterMethodCallArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitMethodCallArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitMethodCallArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodCallArgumentsContext methodCallArguments() throws RecognitionException {
		MethodCallArgumentsContext _localctx = new MethodCallArgumentsContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_methodCallArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((MethodCallArgumentsContext)_localctx).methodCallArgumentsRet =  new ArrayList<>(); 
			setState(427);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << THIS) | (1L << MINUS) | (1L << NOT) | (1L << INCREMENT) | (1L << DECREMENT) | (1L << LPAR) | (1L << LBRACK) | (1L << INT_VALUE) | (1L << IDENTIFIER) | (1L << STRING_VALUE))) != 0)) {
				{
				setState(416);
				((MethodCallArgumentsContext)_localctx).exp1 = expression();
				 _localctx.methodCallArgumentsRet.add(((MethodCallArgumentsContext)_localctx).exp1.expressionRet); 
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(418);
					match(COMMA);
					setState(419);
					((MethodCallArgumentsContext)_localctx).exp2 = expression();
					 _localctx.methodCallArgumentsRet.add(((MethodCallArgumentsContext)_localctx).exp2.expressionRet); 
					}
					}
					setState(426);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueBreakStatementContext extends ParserRuleContext {
		public Statement continueBreakStatementRet;
		public Token b;
		public Token c;
		public TerminalNode SEMICOLLON() { return getToken(SophiaParser.SEMICOLLON, 0); }
		public TerminalNode BREAK() { return getToken(SophiaParser.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(SophiaParser.CONTINUE, 0); }
		public ContinueBreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueBreakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterContinueBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitContinueBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitContinueBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContinueBreakStatementContext continueBreakStatement() throws RecognitionException {
		ContinueBreakStatementContext _localctx = new ContinueBreakStatementContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_continueBreakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BREAK:
				{
				setState(429);
				((ContinueBreakStatementContext)_localctx).b = match(BREAK);

				    ((ContinueBreakStatementContext)_localctx).continueBreakStatementRet =  new BreakStmt();
				    _localctx.continueBreakStatementRet.setLine(((ContinueBreakStatementContext)_localctx).b.getLine());

				}
				break;
			case CONTINUE:
				{
				setState(431);
				((ContinueBreakStatementContext)_localctx).c = match(CONTINUE);

				    ((ContinueBreakStatementContext)_localctx).continueBreakStatementRet =  new ContinueStmt();
				    _localctx.continueBreakStatementRet.setLine(((ContinueBreakStatementContext)_localctx).c.getLine());

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(435);
			match(SEMICOLLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatementContext extends ParserRuleContext {
		public forStatement forStatementRet;
		public Token f;
		public AssignmentContext a1;
		public ExpressionContext e;
		public AssignmentContext a2;
		public StatementContext s;
		public TerminalNode LPAR() { return getToken(SophiaParser.LPAR, 0); }
		public List<TerminalNode> SEMICOLLON() { return getTokens(SophiaParser.SEMICOLLON); }
		public TerminalNode SEMICOLLON(int i) {
			return getToken(SophiaParser.SEMICOLLON, i);
		}
		public TerminalNode RPAR() { return getToken(SophiaParser.RPAR, 0); }
		public TerminalNode FOR() { return getToken(SophiaParser.FOR, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_forStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ForStatementContext)_localctx).forStatementRet =  new ForStmt(); 
			setState(438);
			((ForStatementContext)_localctx).f = match(FOR);
			 _localctx.forStatementRet.setLine(((ForStatementContext)_localctx).f.getLine()); 
			setState(440);
			match(LPAR);
			setState(444);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << THIS) | (1L << MINUS) | (1L << NOT) | (1L << INCREMENT) | (1L << DECREMENT) | (1L << LPAR) | (1L << LBRACK) | (1L << INT_VALUE) | (1L << IDENTIFIER) | (1L << STRING_VALUE))) != 0)) {
				{
				setState(441);
				((ForStatementContext)_localctx).a1 = assignment();
				 _localctx.forStatementRet.setInitialize(((ForStatementContext)_localctx).a1.assignRet); 
				}
			}

			setState(446);
			match(SEMICOLLON);
			setState(450);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << THIS) | (1L << MINUS) | (1L << NOT) | (1L << INCREMENT) | (1L << DECREMENT) | (1L << LPAR) | (1L << LBRACK) | (1L << INT_VALUE) | (1L << IDENTIFIER) | (1L << STRING_VALUE))) != 0)) {
				{
				setState(447);
				((ForStatementContext)_localctx).e = expression();
				 _localctx.forStatementRet.setCondition(((ForStatementContext)_localctx).e.expressionRet); 
				}
			}

			setState(452);
			match(SEMICOLLON);
			setState(456);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NEW) | (1L << NULL) | (1L << TRUE) | (1L << FALSE) | (1L << THIS) | (1L << MINUS) | (1L << NOT) | (1L << INCREMENT) | (1L << DECREMENT) | (1L << LPAR) | (1L << LBRACK) | (1L << INT_VALUE) | (1L << IDENTIFIER) | (1L << STRING_VALUE))) != 0)) {
				{
				setState(453);
				((ForStatementContext)_localctx).a2 = assignment();
				 _localctx.forStatementRet.setUpdate(((ForStatementContext)_localctx).a2.assignRet); 
				}
			}

			setState(458);
			match(RPAR);
			setState(459);
			((ForStatementContext)_localctx).s = statement();
			 _localctx.forStatementRet.setBody(((ForStatementContext)_localctx).s.statementRet); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForeachStatementContext extends ParserRuleContext {
		public ForeachStmt foreachStatementRet;
		public Token f;
		public IdentifierContext id;
		public ExpressionContext e;
		public StatementContext s;
		public TerminalNode LPAR() { return getToken(SophiaParser.LPAR, 0); }
		public TerminalNode IN() { return getToken(SophiaParser.IN, 0); }
		public TerminalNode RPAR() { return getToken(SophiaParser.RPAR, 0); }
		public TerminalNode FOREACH() { return getToken(SophiaParser.FOREACH, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForeachStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreachStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterForeachStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitForeachStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitForeachStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeachStatementContext foreachStatement() throws RecognitionException {
		ForeachStatementContext _localctx = new ForeachStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_foreachStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			((ForeachStatementContext)_localctx).f = match(FOREACH);
			setState(463);
			match(LPAR);
			setState(464);
			((ForeachStatementContext)_localctx).id = identifier();
			setState(465);
			match(IN);
			setState(466);
			((ForeachStatementContext)_localctx).e = expression();
			setState(467);
			match(RPAR);
			setState(468);
			((ForeachStatementContext)_localctx).s = statement();

			    ((ForeachStatementContext)_localctx).foreachStatementRet =  new ForeachStmt(((ForeachStatementContext)_localctx).id.identifierRet, ((ForeachStatementContext)_localctx).e.expressionRet);
			    _localctx.foreachStatementRet.setBody(((ForeachStatementContext)_localctx).s.statementRet);
			    _localctx.foreachStatementRet.setLine(((ForeachStatementContext)_localctx).f.getLine());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public ConditionalStmt ifStatementRet;
		public Token i;
		public ExpressionContext e;
		public StatementContext s1;
		public StatementContext s2;
		public TerminalNode LPAR() { return getToken(SophiaParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SophiaParser.RPAR, 0); }
		public TerminalNode IF() { return getToken(SophiaParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(SophiaParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			((IfStatementContext)_localctx).i = match(IF);
			setState(472);
			match(LPAR);
			setState(473);
			((IfStatementContext)_localctx).e = expression();
			setState(474);
			match(RPAR);
			setState(475);
			((IfStatementContext)_localctx).s1 = statement();

			    ((IfStatementContext)_localctx).ifStatementRet =  new ConditionalStmt(((IfStatementContext)_localctx).e.expressionRet, ((IfStatementContext)_localctx).s1.statementRet);
			    _localctx.ifStatementRet.setLine(((IfStatementContext)_localctx).i.getLine());

			setState(481);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				{
				setState(477);
				match(ELSE);
				setState(478);
				((IfStatementContext)_localctx).s2 = statement();
				 _localctx.ifStatementRet.setElseBody(((IfStatementContext)_localctx).s2.statementRet); 
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Expression expressionRet;
		public BinaryOperator op;
		public int line;
		public OrExpressionContext oe;
		public Token a;
		public ExpressionContext e;
		public OrExpressionContext orExpression() {
			return getRuleContext(OrExpressionContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(SophiaParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(483);
			((ExpressionContext)_localctx).oe = orExpression();
			 ((ExpressionContext)_localctx).expressionRet =  ((ExpressionContext)_localctx).oe.orExpressionRet; 
			setState(490);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(485);
				((ExpressionContext)_localctx).a = match(ASSIGN);

				    ((ExpressionContext)_localctx).op =  BinaryOperator.assign;
				    ((ExpressionContext)_localctx).line =  ((ExpressionContext)_localctx).a.getLine();

				setState(487);
				((ExpressionContext)_localctx).e = expression();

				    ((ExpressionContext)_localctx).expressionRet =  new BinaryExpression(_localctx.expressionRet, _localctx.op, ((ExpressionContext)_localctx).e.expressionRet);
				    _localctx.expressionRet.setline(_localctx.line);

				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrExpressionContext extends ParserRuleContext {
		public Expression orExpressionRet;
		public BinaryOperator op;
		public int line;
		public AndExpressionContext ae1;
		public Token o;
		public AndExpressionContext ae2;
		public List<AndExpressionContext> andExpression() {
			return getRuleContexts(AndExpressionContext.class);
		}
		public AndExpressionContext andExpression(int i) {
			return getRuleContext(AndExpressionContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(SophiaParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(SophiaParser.OR, i);
		}
		public OrExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrExpressionContext orExpression() throws RecognitionException {
		OrExpressionContext _localctx = new OrExpressionContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_orExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			((OrExpressionContext)_localctx).ae1 = andExpression();
			 ((OrExpressionContext)_localctx).orExpressionRet =  ((OrExpressionContext)_localctx).ae1.andExpressionRet; 
			setState(501);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(494);
					((OrExpressionContext)_localctx).o = match(OR);

					    ((OrExpressionContext)_localctx).op =  BinaryOperator.or;
					    ((OrExpressionContext)_localctx).line =  ((OrExpressionContext)_localctx).o.getLine();

					setState(496);
					((OrExpressionContext)_localctx).ae2 = andExpression();

					    ((OrExpressionContext)_localctx).orExpressionRet =  new BinaryExpression(_localctx.orExpressionRet, _localctx.op, ((OrExpressionContext)_localctx).ae2.andExpressionRet);
					    _localctx.orExpressionRet.setline(_localctx.line);

					}
					} 
				}
				setState(503);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AndExpressionContext extends ParserRuleContext {
		public Expression andExpressionRet;
		public BinaryOperator op;
		public int line;
		public EqualityExpressionContext ee1;
		public Token a;
		public EqualityExpressionContext ee2;
		public List<EqualityExpressionContext> equalityExpression() {
			return getRuleContexts(EqualityExpressionContext.class);
		}
		public EqualityExpressionContext equalityExpression(int i) {
			return getRuleContext(EqualityExpressionContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(SophiaParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(SophiaParser.AND, i);
		}
		public AndExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_andExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndExpressionContext andExpression() throws RecognitionException {
		AndExpressionContext _localctx = new AndExpressionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_andExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(504);
			((AndExpressionContext)_localctx).ee1 = equalityExpression();
			 ((AndExpressionContext)_localctx).andExpressionRet =  ((AndExpressionContext)_localctx).ee1.equalityExpressionRet; 
			setState(513);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(506);
					((AndExpressionContext)_localctx).a = match(AND);

					    ((AndExpressionContext)_localctx).op =  BinaryOperator.and;
					    ((AndExpressionContext)_localctx).line =  ((AndExpressionContext)_localctx).a.getLine();

					setState(508);
					((AndExpressionContext)_localctx).ee2 = equalityExpression();

					    ((AndExpressionContext)_localctx).andExpressionRet =  new BinaryExpression(_localctx.andExpressionRet, _localctx.op, ((AndExpressionContext)_localctx).ee2.equalityExpressionRet);
					    _localctx.andExpressionRet.setLine(_localctx.line);

					}
					} 
				}
				setState(515);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EqualityExpressionContext extends ParserRuleContext {
		public Expression equalityExpressionRet;
		public BinaryOperator op;
		public int line;
		public RelationalExpressionContext re1;
		public Token e;
		public Token n;
		public RelationalExpressionContext re2;
		public List<RelationalExpressionContext> relationalExpression() {
			return getRuleContexts(RelationalExpressionContext.class);
		}
		public RelationalExpressionContext relationalExpression(int i) {
			return getRuleContext(RelationalExpressionContext.class,i);
		}
		public List<TerminalNode> EQUAL() { return getTokens(SophiaParser.EQUAL); }
		public TerminalNode EQUAL(int i) {
			return getToken(SophiaParser.EQUAL, i);
		}
		public List<TerminalNode> NOT_EQUAL() { return getTokens(SophiaParser.NOT_EQUAL); }
		public TerminalNode NOT_EQUAL(int i) {
			return getToken(SophiaParser.NOT_EQUAL, i);
		}
		public EqualityExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equalityExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterEqualityExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitEqualityExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitEqualityExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityExpressionContext equalityExpression() throws RecognitionException {
		EqualityExpressionContext _localctx = new EqualityExpressionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_equalityExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(516);
			((EqualityExpressionContext)_localctx).re1 = relationalExpression();
			 ((EqualityExpressionContext)_localctx).equalityExpressionRet =  ((EqualityExpressionContext)_localctx).re1.relationalExpressionRet; 
			setState(529);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(522);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case EQUAL:
						{
						setState(518);
						((EqualityExpressionContext)_localctx).e = match(EQUAL);

						    ((EqualityExpressionContext)_localctx).op =  BinaryOperator.eq;
						    ((EqualityExpressionContext)_localctx).line =  ((EqualityExpressionContext)_localctx).e.getLine();

						}
						break;
					case NOT_EQUAL:
						{
						setState(520);
						((EqualityExpressionContext)_localctx).n = match(NOT_EQUAL);

						    ((EqualityExpressionContext)_localctx).op =  BinaryOperator.neq;
						    ((EqualityExpressionContext)_localctx).line =  ((EqualityExpressionContext)_localctx).n.getLine();

						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(524);
					((EqualityExpressionContext)_localctx).re2 = relationalExpression();

					    ((EqualityExpressionContext)_localctx).equalityExpressionRet =  new BinaryExpression(_localctx.equalityExpressionRet, _localctx.op, ((EqualityExpressionContext)_localctx).re2.relationalExpressionRet);
					    _localctx.equalityExpressionRet.setLine(_localctx.line);

					}
					} 
				}
				setState(531);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationalExpressionContext extends ParserRuleContext {
		public Expression relationalExpressionRet;
		public BinaryOperator op;
		public int line;
		public AdditiveExpressionContext ae1;
		public Token g;
		public Token l;
		public AdditiveExpressionContext ae2;
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<TerminalNode> GREATER_THAN() { return getTokens(SophiaParser.GREATER_THAN); }
		public TerminalNode GREATER_THAN(int i) {
			return getToken(SophiaParser.GREATER_THAN, i);
		}
		public List<TerminalNode> LESS_THAN() { return getTokens(SophiaParser.LESS_THAN); }
		public TerminalNode LESS_THAN(int i) {
			return getToken(SophiaParser.LESS_THAN, i);
		}
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitRelationalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitRelationalExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_relationalExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(532);
			((RelationalExpressionContext)_localctx).ae1 = additiveExpression();
			 ((RelationalExpressionContext)_localctx).relationalExpressionRet =  ((RelationalExpressionContext)_localctx).ae1.additiveExpressionRet; 
			setState(545);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(538);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case GREATER_THAN:
						{
						setState(534);
						((RelationalExpressionContext)_localctx).g = match(GREATER_THAN);

						    ((RelationalExpressionContext)_localctx).op =  BinaryOperator.gt;
						    ((RelationalExpressionContext)_localctx).line =  ((RelationalExpressionContext)_localctx).g.getLine();

						}
						break;
					case LESS_THAN:
						{
						setState(536);
						((RelationalExpressionContext)_localctx).l = match(LESS_THAN);

						    ((RelationalExpressionContext)_localctx).op =  BinaryOperator.lt;
						    ((RelationalExpressionContext)_localctx).line =  ((RelationalExpressionContext)_localctx).l.getLine();

						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(540);
					((RelationalExpressionContext)_localctx).ae2 = additiveExpression();

					    ((RelationalExpressionContext)_localctx).relationalExpressionRet =  new BinaryExpression(_localctx.relationalExpressionRet, _localctx.op, ((RelationalExpressionContext)_localctx).ae2.additiveExpressionRet);
					    _localctx.relationalExpressionRet.setLine(_localctx.line);

					}
					} 
				}
				setState(547);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public Expression additiveExpressionRet;
		public BinaryOperator op;
		public int line;
		public MultiplicativeExpressionContext me1;
		public Token p;
		public Token m;
		public MultiplicativeExpressionContext me2;
		public List<MultiplicativeExpressionContext> multiplicativeExpression() {
			return getRuleContexts(MultiplicativeExpressionContext.class);
		}
		public MultiplicativeExpressionContext multiplicativeExpression(int i) {
			return getRuleContext(MultiplicativeExpressionContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(SophiaParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(SophiaParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(SophiaParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(SophiaParser.MINUS, i);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitAdditiveExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitAdditiveExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_additiveExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			((AdditiveExpressionContext)_localctx).me1 = multiplicativeExpression();
			 ((AdditiveExpressionContext)_localctx).additiveExpressionRet =  ((AdditiveExpressionContext)_localctx).me1.multiplicativeExpressionRet; 
			setState(561);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(554);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case PLUS:
						{
						setState(550);
						((AdditiveExpressionContext)_localctx).p = match(PLUS);

						    ((AdditiveExpressionContext)_localctx).op =  BinaryOperator.add;
						    ((AdditiveExpressionContext)_localctx).line =  ((AdditiveExpressionContext)_localctx).p.getLine();

						}
						break;
					case MINUS:
						{
						setState(552);
						((AdditiveExpressionContext)_localctx).m = match(MINUS);

						    ((AdditiveExpressionContext)_localctx).op =  BinaryOperator.sub;
						    ((AdditiveExpressionContext)_localctx).line =  ((AdditiveExpressionContext)_localctx).m.getLine();

						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(556);
					((AdditiveExpressionContext)_localctx).me2 = multiplicativeExpression();

					    ((AdditiveExpressionContext)_localctx).additiveExpressionRet =  new BinaryExpression(_localctx.additiveExpressionRet, _localctx.op, ((AdditiveExpressionContext)_localctx).me2.multiplicativeExpressionRet);
					    _localctx.additiveExpressionRet.setLine(_localctx.line);

					}
					} 
				}
				setState(563);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public Expression multiplicativeExpressionRet;
		public BinaryOperator op;
		public int line;
		public PreUnaryExpressionContext pre1;
		public Token mu;
		public Token d;
		public Token mo;
		public PreUnaryExpressionContext pre2;
		public List<PreUnaryExpressionContext> preUnaryExpression() {
			return getRuleContexts(PreUnaryExpressionContext.class);
		}
		public PreUnaryExpressionContext preUnaryExpression(int i) {
			return getRuleContext(PreUnaryExpressionContext.class,i);
		}
		public List<TerminalNode> MULT() { return getTokens(SophiaParser.MULT); }
		public TerminalNode MULT(int i) {
			return getToken(SophiaParser.MULT, i);
		}
		public List<TerminalNode> DIVIDE() { return getTokens(SophiaParser.DIVIDE); }
		public TerminalNode DIVIDE(int i) {
			return getToken(SophiaParser.DIVIDE, i);
		}
		public List<TerminalNode> MOD() { return getTokens(SophiaParser.MOD); }
		public TerminalNode MOD(int i) {
			return getToken(SophiaParser.MOD, i);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitMultiplicativeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitMultiplicativeExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_multiplicativeExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(564);
			((MultiplicativeExpressionContext)_localctx).pre1 = preUnaryExpression();
			 ((MultiplicativeExpressionContext)_localctx).multiplicativeExpressionRet =  ((MultiplicativeExpressionContext)_localctx).pre1.preUnaryExpressionRet; 
			setState(579);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(572);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case MULT:
						{
						setState(566);
						((MultiplicativeExpressionContext)_localctx).mu = match(MULT);

						    ((MultiplicativeExpressionContext)_localctx).op =  BinaryOperator.mult;
						    ((MultiplicativeExpressionContext)_localctx).line =  ((MultiplicativeExpressionContext)_localctx).mu.getLine();

						}
						break;
					case DIVIDE:
						{
						setState(568);
						((MultiplicativeExpressionContext)_localctx).d = match(DIVIDE);

						    ((MultiplicativeExpressionContext)_localctx).op =  BinaryOperator.div;
						    ((MultiplicativeExpressionContext)_localctx).line =  ((MultiplicativeExpressionContext)_localctx).d.getLine();

						}
						break;
					case MOD:
						{
						setState(570);
						((MultiplicativeExpressionContext)_localctx).mo = match(MOD);

						    ((MultiplicativeExpressionContext)_localctx).op =  BinaryOperator.mod;
						    ((MultiplicativeExpressionContext)_localctx).line =  ((MultiplicativeExpressionContext)_localctx).mo.getLine();

						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(574);
					((MultiplicativeExpressionContext)_localctx).pre2 = preUnaryExpression();

					    ((MultiplicativeExpressionContext)_localctx).multiplicativeExpressionRet =  new BinaryExpression(_localctx.multiplicativeExpressionRet, _localctx.op, ((MultiplicativeExpressionContext)_localctx).pre2.preUnaryExpressionRet);
					    _localctx.multiplicativeExpressionRet.setLine(_localctx.line);

					}
					} 
				}
				setState(581);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PreUnaryExpressionContext extends ParserRuleContext {
		public Expression preUnaryExpressionRet;
		public UnaryOperator op;
		public int line;
		public Token n;
		public Token m;
		public Token i;
		public Token d;
		public PreUnaryExpressionContext pre;
		public PostUnaryExpressionContext poe;
		public PreUnaryExpressionContext preUnaryExpression() {
			return getRuleContext(PreUnaryExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(SophiaParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(SophiaParser.MINUS, 0); }
		public TerminalNode INCREMENT() { return getToken(SophiaParser.INCREMENT, 0); }
		public TerminalNode DECREMENT() { return getToken(SophiaParser.DECREMENT, 0); }
		public PostUnaryExpressionContext postUnaryExpression() {
			return getRuleContext(PostUnaryExpressionContext.class,0);
		}
		public PreUnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preUnaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterPreUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitPreUnaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitPreUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreUnaryExpressionContext preUnaryExpression() throws RecognitionException {
		PreUnaryExpressionContext _localctx = new PreUnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_preUnaryExpression);
		try {
			setState(598);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case NOT:
			case INCREMENT:
			case DECREMENT:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(590);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NOT:
					{
					setState(582);
					((PreUnaryExpressionContext)_localctx).n = match(NOT);

					    ((PreUnaryExpressionContext)_localctx).op =  UnaryOperator.not;
					    ((PreUnaryExpressionContext)_localctx).line =  ((PreUnaryExpressionContext)_localctx).n.getLine();

					}
					break;
				case MINUS:
					{
					setState(584);
					((PreUnaryExpressionContext)_localctx).m = match(MINUS);

					    ((PreUnaryExpressionContext)_localctx).op =  UnaryOperator.minus;
					    ((PreUnaryExpressionContext)_localctx).line =  ((PreUnaryExpressionContext)_localctx).m.getLine();

					}
					break;
				case INCREMENT:
					{
					setState(586);
					((PreUnaryExpressionContext)_localctx).i = match(INCREMENT);

					    ((PreUnaryExpressionContext)_localctx).op =  UnaryOperator.preinc;
					    ((PreUnaryExpressionContext)_localctx).line =  ((PreUnaryExpressionContext)_localctx).i.getLine();

					}
					break;
				case DECREMENT:
					{
					setState(588);
					((PreUnaryExpressionContext)_localctx).d = match(DECREMENT);

					    ((PreUnaryExpressionContext)_localctx).op =  UnaryOperator.predec;
					    ((PreUnaryExpressionContext)_localctx).line =  ((PreUnaryExpressionContext)_localctx).d.getLine();

					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(592);
				((PreUnaryExpressionContext)_localctx).pre = preUnaryExpression();

				    ((PreUnaryExpressionContext)_localctx).preUnaryExpressionRet =  new UnaryExpression(((PreUnaryExpressionContext)_localctx).pre.preUnaryExpressionRet, _localctx.op);
				    _localctx.preUnaryExpressionRet.setLine(_localctx.line);


				}
				}
				break;
			case NEW:
			case NULL:
			case TRUE:
			case FALSE:
			case THIS:
			case LPAR:
			case LBRACK:
			case INT_VALUE:
			case IDENTIFIER:
			case STRING_VALUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(595);
				((PreUnaryExpressionContext)_localctx).poe = postUnaryExpression();
				 ((PreUnaryExpressionContext)_localctx).preUnaryExpressionRet =  ((PreUnaryExpressionContext)_localctx).poe.postUnaryExpressionRet; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PostUnaryExpressionContext extends ParserRuleContext {
		public Expression postUnaryExpressionRet;
		public AccessExpressionContext ae;
		public Token i;
		public Token d;
		public AccessExpressionContext accessExpression() {
			return getRuleContext(AccessExpressionContext.class,0);
		}
		public TerminalNode INCREMENT() { return getToken(SophiaParser.INCREMENT, 0); }
		public TerminalNode DECREMENT() { return getToken(SophiaParser.DECREMENT, 0); }
		public PostUnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postUnaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterPostUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitPostUnaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitPostUnaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostUnaryExpressionContext postUnaryExpression() throws RecognitionException {
		PostUnaryExpressionContext _localctx = new PostUnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_postUnaryExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(600);
			((PostUnaryExpressionContext)_localctx).ae = accessExpression();
			 ((PostUnaryExpressionContext)_localctx).postUnaryExpressionRet =  ((PostUnaryExpressionContext)_localctx).ae.accessExpressionRet; 
			setState(606);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(602);
				((PostUnaryExpressionContext)_localctx).i = match(INCREMENT);

				    ((PostUnaryExpressionContext)_localctx).postUnaryExpressionRet =  new UnaryExpression(_localctx.postUnaryExpressionRet, UnaryOperator.postinc);
				    _localctx.postUnaryExpressionRet.setLine(((PostUnaryExpressionContext)_localctx).i.getLine());

				}
				break;
			case 2:
				{
				setState(604);
				((PostUnaryExpressionContext)_localctx).d = match(DECREMENT);

				    ((PostUnaryExpressionContext)_localctx).postUnaryExpressionRet =  new UnaryExpression(_localctx.postUnaryExpressionRet, UnaryOperator.postdec);
				    _localctx.postUnaryExpressionRet.setLine(((PostUnaryExpressionContext)_localctx).d.getLine());

				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AccessExpressionContext extends ParserRuleContext {
		public Expression accessExpressionRet;
		public OtherExpressionContext oe;
		public Token lp;
		public MethodCallArgumentsContext mca;
		public IdentifierContext id;
		public Token lb;
		public ExpressionContext e;
		public OtherExpressionContext otherExpression() {
			return getRuleContext(OtherExpressionContext.class,0);
		}
		public List<TerminalNode> RPAR() { return getTokens(SophiaParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(SophiaParser.RPAR, i);
		}
		public List<TerminalNode> DOT() { return getTokens(SophiaParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(SophiaParser.DOT, i);
		}
		public List<TerminalNode> RBRACK() { return getTokens(SophiaParser.RBRACK); }
		public TerminalNode RBRACK(int i) {
			return getToken(SophiaParser.RBRACK, i);
		}
		public List<TerminalNode> LPAR() { return getTokens(SophiaParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(SophiaParser.LPAR, i);
		}
		public List<MethodCallArgumentsContext> methodCallArguments() {
			return getRuleContexts(MethodCallArgumentsContext.class);
		}
		public MethodCallArgumentsContext methodCallArguments(int i) {
			return getRuleContext(MethodCallArgumentsContext.class,i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> LBRACK() { return getTokens(SophiaParser.LBRACK); }
		public TerminalNode LBRACK(int i) {
			return getToken(SophiaParser.LBRACK, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AccessExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitAccessExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitAccessExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccessExpressionContext accessExpression() throws RecognitionException {
		AccessExpressionContext _localctx = new AccessExpressionContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_accessExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(608);
			((AccessExpressionContext)_localctx).oe = otherExpression();
			 ((AccessExpressionContext)_localctx).accessExpressionRet =  ((AccessExpressionContext)_localctx).oe.otherExpressionRet; 
			setState(629);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(627);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case LPAR:
						{
						{
						setState(610);
						((AccessExpressionContext)_localctx).lp = match(LPAR);
						setState(611);
						((AccessExpressionContext)_localctx).mca = methodCallArguments();
						setState(612);
						match(RPAR);
						}

						    ((AccessExpressionContext)_localctx).accessExpressionRet =  new MethodCall(_localctx.accessExpressionRet, ((AccessExpressionContext)_localctx).mca.methodCallArgumentsRet);
						    _localctx.accessExpressionRet.setLine(((AccessExpressionContext)_localctx).lp.getLine());

						}
						break;
					case DOT:
						{
						{
						setState(616);
						match(DOT);
						setState(617);
						((AccessExpressionContext)_localctx).id = identifier();
						}

						    ((AccessExpressionContext)_localctx).accessExpressionRet =  new ObjectOrListMemberAccess(_localctx.accessExpressionRet, ((AccessExpressionContext)_localctx).id.identifierRet);
						    _localctx.accessExpressionRet.setLine(((AccessExpressionContext)_localctx).id.identifierRet.getLine());

						}
						break;
					case LBRACK:
						{
						{
						setState(621);
						((AccessExpressionContext)_localctx).lb = match(LBRACK);
						setState(622);
						((AccessExpressionContext)_localctx).e = expression();
						setState(623);
						match(RBRACK);
						}

						    ((AccessExpressionContext)_localctx).accessExpressionRet =  new ListAccessByIndex(_localctx.accessExpressionRet, ((AccessExpressionContext)_localctx).e.expressionRet);
						    _localctx.accessExpressionRet.setLine(((AccessExpressionContext)_localctx).lb.getLine());

						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(631);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OtherExpressionContext extends ParserRuleContext {
		public Expression otherExpressionRet;
		public Token t;
		public NewExpressionContext n;
		public ValuesContext v;
		public IdentifierContext id;
		public ExpressionContext e;
		public TerminalNode THIS() { return getToken(SophiaParser.THIS, 0); }
		public NewExpressionContext newExpression() {
			return getRuleContext(NewExpressionContext.class,0);
		}
		public ValuesContext values() {
			return getRuleContext(ValuesContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(SophiaParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SophiaParser.RPAR, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OtherExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_otherExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterOtherExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitOtherExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitOtherExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OtherExpressionContext otherExpression() throws RecognitionException {
		OtherExpressionContext _localctx = new OtherExpressionContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_otherExpression);
		try {
			setState(648);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case THIS:
				enterOuterAlt(_localctx, 1);
				{
				setState(632);
				((OtherExpressionContext)_localctx).t = match(THIS);

				    ((OtherExpressionContext)_localctx).otherExpressionRet =  new ThisClass();
				    &otherExpressionRet.setLine(((OtherExpressionContext)_localctx).t.getLine());

				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 2);
				{
				setState(634);
				((OtherExpressionContext)_localctx).n = newExpression();
				 ((OtherExpressionContext)_localctx).otherExpressionRet =  ((OtherExpressionContext)_localctx).n.newExpressionRet; 
				}
				break;
			case NULL:
			case TRUE:
			case FALSE:
			case LBRACK:
			case INT_VALUE:
			case STRING_VALUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(637);
				((OtherExpressionContext)_localctx).v = values();
				 ((OtherExpressionContext)_localctx).otherExpressionRet =  ((OtherExpressionContext)_localctx).v.valuesRet; 
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 4);
				{
				setState(640);
				((OtherExpressionContext)_localctx).id = identifier();
				 ((OtherExpressionContext)_localctx).otherExpressionRet =  ((OtherExpressionContext)_localctx).id.identifierRet; 
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 5);
				{
				setState(643);
				match(LPAR);
				{
				setState(644);
				((OtherExpressionContext)_localctx).e = expression();
				}
				 ((OtherExpressionContext)_localctx).otherExpressionRet =  ((OtherExpressionContext)_localctx).e.expressionRet; 
				setState(646);
				match(RPAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NewExpressionContext extends ParserRuleContext {
		public NewClassInstance newExpressionRet;
		public Token n;
		public ClassTypeContext c;
		public MethodCallArgumentsContext mca;
		public TerminalNode LPAR() { return getToken(SophiaParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SophiaParser.RPAR, 0); }
		public TerminalNode NEW() { return getToken(SophiaParser.NEW, 0); }
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public MethodCallArgumentsContext methodCallArguments() {
			return getRuleContext(MethodCallArgumentsContext.class,0);
		}
		public NewExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterNewExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitNewExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitNewExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewExpressionContext newExpression() throws RecognitionException {
		NewExpressionContext _localctx = new NewExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_newExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(650);
			((NewExpressionContext)_localctx).n = match(NEW);
			setState(651);
			((NewExpressionContext)_localctx).c = classType();

			    ((NewExpressionContext)_localctx).newExpressionRet =  new NewClassInstance(((NewExpressionContext)_localctx).c.classTypeRet);
			    _localctx.newExpressionRet.setLine(((NewExpressionContext)_localctx).n.getLine());

			setState(653);
			match(LPAR);
			setState(654);
			((NewExpressionContext)_localctx).mca = methodCallArguments();
			 _localctx.newExpressionRet.setArgs(((NewExpressionContext)_localctx).mca.methodCallArgumentsRet); 
			setState(656);
			match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValuesContext extends ParserRuleContext {
		public Value valuesRet;
		public BoolValueContext b;
		public Token sv;
		public Token iv;
		public Token n;
		public ListValueContext l;
		public BoolValueContext boolValue() {
			return getRuleContext(BoolValueContext.class,0);
		}
		public TerminalNode STRING_VALUE() { return getToken(SophiaParser.STRING_VALUE, 0); }
		public TerminalNode INT_VALUE() { return getToken(SophiaParser.INT_VALUE, 0); }
		public TerminalNode NULL() { return getToken(SophiaParser.NULL, 0); }
		public ListValueContext listValue() {
			return getRuleContext(ListValueContext.class,0);
		}
		public ValuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_values; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterValues(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitValues(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitValues(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValuesContext values() throws RecognitionException {
		ValuesContext _localctx = new ValuesContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_values);
		try {
			setState(670);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(658);
				((ValuesContext)_localctx).b = boolValue();
				_localctx.valuesRet = ((ValuesContext)_localctx).b.boolValueRet
				}
				break;
			case STRING_VALUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(661);
				((ValuesContext)_localctx).sv = match(STRING_VALUE);

				    ((ValuesContext)_localctx).valuesRet =  new StringValue((((ValuesContext)_localctx).sv!=null?((ValuesContext)_localctx).sv.getText():null));
				    _localctx.valuesRet.setLine(((ValuesContext)_localctx).sv.getLine());

				}
				break;
			case INT_VALUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(663);
				((ValuesContext)_localctx).iv = match(INT_VALUE);

				    ((ValuesContext)_localctx).valuesRet =  new IntValue(Integer.parseInt((((ValuesContext)_localctx).iv!=null?((ValuesContext)_localctx).iv.getText():null)));
				    _localctx.valuesRet.setLine(((ValuesContext)_localctx).iv.getLine());

				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(665);
				((ValuesContext)_localctx).n = match(NULL);

				    ((ValuesContext)_localctx).valuesRet =  new NullValue();
				    _localctx.valuesRet.setLine(((ValuesContext)_localctx).n.getLine());

				}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 5);
				{
				setState(667);
				((ValuesContext)_localctx).l = listValue();
				 ((ValuesContext)_localctx).valuesRet =  ((ValuesContext)_localctx).l.listValueRet; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolValueContext extends ParserRuleContext {
		public BoolValue boolValueRet;
		public Token t;
		public Token f;
		public TerminalNode TRUE() { return getToken(SophiaParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(SophiaParser.FALSE, 0); }
		public BoolValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterBoolValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitBoolValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitBoolValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolValueContext boolValue() throws RecognitionException {
		BoolValueContext _localctx = new BoolValueContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_boolValue);
		try {
			setState(676);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(672);
				((BoolValueContext)_localctx).t = match(TRUE);

				    &boolValueRet = new BoolValue(true);
				    &boolValueRet.setLine(((BoolValueContext)_localctx).t.getLine());

				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 2);
				{
				setState(674);
				((BoolValueContext)_localctx).f = match(FALSE);

				    &boolValueRet = new BoolValue(false);
				    &boolValueRet.setLine(((BoolValueContext)_localctx).f.getLine());

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListValueContext extends ParserRuleContext {
		public ListValue listValueRet;
		public Token l;
		public MethodCallArgumentsContext mca;
		public TerminalNode RBRACK() { return getToken(SophiaParser.RBRACK, 0); }
		public TerminalNode LBRACK() { return getToken(SophiaParser.LBRACK, 0); }
		public MethodCallArgumentsContext methodCallArguments() {
			return getRuleContext(MethodCallArgumentsContext.class,0);
		}
		public ListValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterListValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitListValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitListValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListValueContext listValue() throws RecognitionException {
		ListValueContext _localctx = new ListValueContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_listValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			 ((ListValueContext)_localctx).listValueRet =  new ListValue(); 
			setState(679);
			((ListValueContext)_localctx).l = match(LBRACK);
			 _localctx.listValueRet.setLine(((ListValueContext)_localctx).l.getLine()); 
			setState(681);
			((ListValueContext)_localctx).mca = methodCallArguments();
			 _localctx.listValueRet.setElements(((ListValueContext)_localctx).mca.methodCallArgumentsRet); 
			setState(683);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public Identifier identifierRet;
		public Token id;
		public TerminalNode IDENTIFIER() { return getToken(SophiaParser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SophiaListener ) ((SophiaListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SophiaVisitor ) return ((SophiaVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(685);
			((IdentifierContext)_localctx).id = match(IDENTIFIER);

			    ((IdentifierContext)_localctx).identifierRet =  new Identifier((((IdentifierContext)_localctx).id!=null?((IdentifierContext)_localctx).id.getText():null)) ;
			    &identifierRet.setLine(((IdentifierContext)_localctx).id.getLine());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 22:
			return returnStatement_sempred((ReturnStatementContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean returnStatement_sempred(ReturnStatementContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return  ((ReturnStatementContext)_localctx).returnStatementRet =  new ReturnStmt(((ReturnStatementContext)_localctx).exp.expressionRet); _localctx.returnStatementRet.setLine(((ReturnStatementContext)_localctx).r.getLine()); ;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u02b3\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3g\n\3\f\3\16"+
		"\3j\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4s\n\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\7\4|\n\4\f\4\16\4\177\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u0089"+
		"\n\4\f\4\16\4\u008c\13\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u0094\n\4\f\4\16"+
		"\4\u0097\13\4\5\4\u0099\n\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\5\6\u00a9\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\7\b\u00c9\n\b\f\b\16\b\u00cc\13\b\5\b\u00ce\n\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00e1\n\n\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00f1\n"+
		"\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00fc\n\r\f\r\16\r\u00ff\13"+
		"\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0107\n\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\5\17\u0111\n\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u0119\n\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0124\n"+
		"\20\f\20\16\20\u0127\13\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u012f\n"+
		"\21\3\22\3\22\3\22\3\22\7\22\u0135\n\22\f\22\16\22\u0138\13\22\3\22\3"+
		"\22\3\22\7\22\u013d\n\22\f\22\16\22\u0140\13\22\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u015d\n\23\3\24\3\24"+
		"\3\24\3\24\3\24\7\24\u0164\n\24\f\24\16\24\u0167\13\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\7\32\u0197\n\32\f\32\16\32\u019a\13\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u01a9\n\33\f\33\16\33\u01ac"+
		"\13\33\5\33\u01ae\n\33\3\34\3\34\3\34\3\34\5\34\u01b4\n\34\3\34\3\34\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u01bf\n\35\3\35\3\35\3\35\3\35"+
		"\5\35\u01c5\n\35\3\35\3\35\3\35\3\35\5\35\u01cb\n\35\3\35\3\35\3\35\3"+
		"\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\3\37\3\37\3\37\3\37\5\37\u01e4\n\37\3 \3 \3 \3 \3 \3 \3 \5 \u01ed"+
		"\n \3!\3!\3!\3!\3!\3!\3!\7!\u01f6\n!\f!\16!\u01f9\13!\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\7\"\u0202\n\"\f\"\16\"\u0205\13\"\3#\3#\3#\3#\3#\3#\5#\u020d"+
		"\n#\3#\3#\3#\7#\u0212\n#\f#\16#\u0215\13#\3$\3$\3$\3$\3$\3$\5$\u021d\n"+
		"$\3$\3$\3$\7$\u0222\n$\f$\16$\u0225\13$\3%\3%\3%\3%\3%\3%\5%\u022d\n%"+
		"\3%\3%\3%\7%\u0232\n%\f%\16%\u0235\13%\3&\3&\3&\3&\3&\3&\3&\3&\5&\u023f"+
		"\n&\3&\3&\3&\7&\u0244\n&\f&\16&\u0247\13&\3\'\3\'\3\'\3\'\3\'\3\'\3\'"+
		"\3\'\5\'\u0251\n\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u0259\n\'\3(\3(\3(\3(\3"+
		"(\3(\5(\u0261\n(\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3"+
		")\3)\7)\u0276\n)\f)\16)\u0279\13)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*"+
		"\3*\3*\3*\3*\5*\u028b\n*\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,"+
		"\3,\3,\3,\3,\3,\5,\u02a1\n,\3-\3-\3-\3-\5-\u02a7\n-\3.\3.\3.\3.\3.\3."+
		"\3.\3/\3/\3/\3/\2\2\60\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,"+
		".\60\62\64\668:<>@BDFHJLNPRTVXZ\\\2\2\2\u02ce\2^\3\2\2\2\4b\3\2\2\2\6"+
		"k\3\2\2\2\b\u009c\3\2\2\2\n\u00a2\3\2\2\2\f\u00b5\3\2\2\2\16\u00c1\3\2"+
		"\2\2\20\u00cf\3\2\2\2\22\u00e0\3\2\2\2\24\u00e2\3\2\2\2\26\u00e5\3\2\2"+
		"\2\30\u00f4\3\2\2\2\32\u0106\3\2\2\2\34\u0108\3\2\2\2\36\u011c\3\2\2\2"+
		" \u012e\3\2\2\2\"\u0130\3\2\2\2$\u015c\3\2\2\2&\u015e\3\2\2\2(\u016a\3"+
		"\2\2\2*\u016e\3\2\2\2,\u0173\3\2\2\2.\u017a\3\2\2\2\60\u017f\3\2\2\2\62"+
		"\u0183\3\2\2\2\64\u01a1\3\2\2\2\66\u01b3\3\2\2\28\u01b7\3\2\2\2:\u01d0"+
		"\3\2\2\2<\u01d9\3\2\2\2>\u01e5\3\2\2\2@\u01ee\3\2\2\2B\u01fa\3\2\2\2D"+
		"\u0206\3\2\2\2F\u0216\3\2\2\2H\u0226\3\2\2\2J\u0236\3\2\2\2L\u0258\3\2"+
		"\2\2N\u025a\3\2\2\2P\u0262\3\2\2\2R\u028a\3\2\2\2T\u028c\3\2\2\2V\u02a0"+
		"\3\2\2\2X\u02a6\3\2\2\2Z\u02a8\3\2\2\2\\\u02af\3\2\2\2^_\5\4\3\2_`\b\2"+
		"\1\2`a\7\2\2\3a\3\3\2\2\2bh\b\3\1\2cd\5\6\4\2de\b\3\1\2eg\3\2\2\2fc\3"+
		"\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\5\3\2\2\2jh\3\2\2\2kl\7\5\2\2lm"+
		"\5\\/\2mr\b\4\1\2no\7\4\2\2op\5\\/\2pq\b\4\1\2qs\3\2\2\2rn\3\2\2\2rs\3"+
		"\2\2\2st\3\2\2\2t\u0098\7/\2\2uv\5\b\5\2vw\b\4\1\2w|\3\2\2\2xy\5\n\6\2"+
		"yz\b\4\1\2z|\3\2\2\2{u\3\2\2\2{x\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2"+
		"\2\2~\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0081\5\f\7\2\u0081\u008a\b\4\1"+
		"\2\u0082\u0083\5\b\5\2\u0083\u0084\b\4\1\2\u0084\u0089\3\2\2\2\u0085\u0086"+
		"\5\n\6\2\u0086\u0087\b\4\1\2\u0087\u0089\3\2\2\2\u0088\u0082\3\2\2\2\u0088"+
		"\u0085\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2"+
		"\2\2\u008b\u0099\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008e\5\b\5\2\u008e"+
		"\u008f\b\4\1\2\u008f\u0094\3\2\2\2\u0090\u0091\5\n\6\2\u0091\u0092\b\4"+
		"\1\2\u0092\u0094\3\2\2\2\u0093\u008d\3\2\2\2\u0093\u0090\3\2\2\2\u0094"+
		"\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0099\3\2"+
		"\2\2\u0097\u0095\3\2\2\2\u0098}\3\2\2\2\u0098\u0095\3\2\2\2\u0099\u009a"+
		"\3\2\2\2\u009a\u009b\7\60\2\2\u009b\7\3\2\2\2\u009c\u009d\5\\/\2\u009d"+
		"\u009e\7\64\2\2\u009e\u009f\5\22\n\2\u009f\u00a0\7\65\2\2\u00a0\u00a1"+
		"\b\5\1\2\u00a1\t\3\2\2\2\u00a2\u00a8\7\3\2\2\u00a3\u00a4\5\22\n\2\u00a4"+
		"\u00a5\b\6\1\2\u00a5\u00a9\3\2\2\2\u00a6\u00a7\7\24\2\2\u00a7\u00a9\b"+
		"\6\1\2\u00a8\u00a3\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00ab\5\\/\2\u00ab\u00ac\b\6\1\2\u00ac\u00ad\7+\2\2\u00ad\u00ae\5\16"+
		"\b\2\u00ae\u00af\b\6\1\2\u00af\u00b0\7,\2\2\u00b0\u00b1\7/\2\2\u00b1\u00b2"+
		"\5\"\22\2\u00b2\u00b3\b\6\1\2\u00b3\u00b4\7\60\2\2\u00b4\13\3\2\2\2\u00b5"+
		"\u00b6\7\3\2\2\u00b6\u00b7\5\\/\2\u00b7\u00b8\7+\2\2\u00b8\u00b9\b\7\1"+
		"\2\u00b9\u00ba\5\16\b\2\u00ba\u00bb\b\7\1\2\u00bb\u00bc\7,\2\2\u00bc\u00bd"+
		"\7/\2\2\u00bd\u00be\5\"\22\2\u00be\u00bf\b\7\1\2\u00bf\u00c0\7\60\2\2"+
		"\u00c0\r\3\2\2\2\u00c1\u00cd\b\b\1\2\u00c2\u00c3\5\20\t\2\u00c3\u00ca"+
		"\b\b\1\2\u00c4\u00c5\7\62\2\2\u00c5\u00c6\5\20\t\2\u00c6\u00c7\b\b\1\2"+
		"\u00c7\u00c9\3\2\2\2\u00c8\u00c4\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8"+
		"\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd"+
		"\u00c2\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\17\3\2\2\2\u00cf\u00d0\5\\/\2"+
		"\u00d0\u00d1\7\64\2\2\u00d1\u00d2\5\22\n\2\u00d2\u00d3\b\t\1\2\u00d3\21"+
		"\3\2\2\2\u00d4\u00d5\5 \21\2\u00d5\u00d6\b\n\1\2\u00d6\u00e1\3\2\2\2\u00d7"+
		"\u00d8\5\26\f\2\u00d8\u00d9\b\n\1\2\u00d9\u00e1\3\2\2\2\u00da\u00db\5"+
		"\34\17\2\u00db\u00dc\b\n\1\2\u00dc\u00e1\3\2\2\2\u00dd\u00de\5\24\13\2"+
		"\u00de\u00df\b\n\1\2\u00df\u00e1\3\2\2\2\u00e0\u00d4\3\2\2\2\u00e0\u00d7"+
		"\3\2\2\2\u00e0\u00da\3\2\2\2\u00e0\u00dd\3\2\2\2\u00e1\23\3\2\2\2\u00e2"+
		"\u00e3\5\\/\2\u00e3\u00e4\b\13\1\2\u00e4\25\3\2\2\2\u00e5\u00e6\7\26\2"+
		"\2\u00e6\u00f0\7+\2\2\u00e7\u00e8\7\66\2\2\u00e8\u00e9\7\61\2\2\u00e9"+
		"\u00ea\5\22\n\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\b\f\1\2\u00ec\u00f1\3"+
		"\2\2\2\u00ed\u00ee\5\30\r\2\u00ee\u00ef\b\f\1\2\u00ef\u00f1\3\2\2\2\u00f0"+
		"\u00e7\3\2\2\2\u00f0\u00ed\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3\7,"+
		"\2\2\u00f3\27\3\2\2\2\u00f4\u00f5\b\r\1\2\u00f5\u00f6\5\32\16\2\u00f6"+
		"\u00fd\b\r\1\2\u00f7\u00f8\7\62\2\2\u00f8\u00f9\5\32\16\2\u00f9\u00fa"+
		"\b\r\1\2\u00fa\u00fc\3\2\2\2\u00fb\u00f7\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\31\3\2\2\2\u00ff\u00fd\3\2\2"+
		"\2\u0100\u0101\5\20\t\2\u0101\u0102\b\16\1\2\u0102\u0107\3\2\2\2\u0103"+
		"\u0104\5\22\n\2\u0104\u0105\b\16\1\2\u0105\u0107\3\2\2\2\u0106\u0100\3"+
		"\2\2\2\u0106\u0103\3\2\2\2\u0107\33\3\2\2\2\u0108\u0109\7\7\2\2\u0109"+
		"\u010a\7\34\2\2\u010a\u0110\b\17\1\2\u010b\u010c\7\24\2\2\u010c\u0111"+
		"\b\17\1\2\u010d\u010e\5\36\20\2\u010e\u010f\b\17\1\2\u010f\u0111\3\2\2"+
		"\2\u0110\u010b\3\2\2\2\u0110\u010d\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0118"+
		"\7\32\2\2\u0113\u0114\7\24\2\2\u0114\u0119\b\17\1\2\u0115\u0116\5\22\n"+
		"\2\u0116\u0117\b\17\1\2\u0117\u0119\3\2\2\2\u0118\u0113\3\2\2\2\u0118"+
		"\u0115\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011b\7\33\2\2\u011b\35\3\2\2"+
		"\2\u011c\u011d\b\20\1\2\u011d\u011e\5\22\n\2\u011e\u0125\b\20\1\2\u011f"+
		"\u0120\7\62\2\2\u0120\u0121\5\22\n\2\u0121\u0122\b\20\1\2\u0122\u0124"+
		"\3\2\2\2\u0123\u011f\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125"+
		"\u0126\3\2\2\2\u0126\37\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129\7\23\2"+
		"\2\u0129\u012f\b\21\1\2\u012a\u012b\7\22\2\2\u012b\u012f\b\21\1\2\u012c"+
		"\u012d\7\21\2\2\u012d\u012f\b\21\1\2\u012e\u0128\3\2\2\2\u012e\u012a\3"+
		"\2\2\2\u012e\u012c\3\2\2\2\u012f!\3\2\2\2\u0130\u0136\b\22\1\2\u0131\u0132"+
		"\5\b\5\2\u0132\u0133\b\22\1\2\u0133\u0135\3\2\2\2\u0134\u0131\3\2\2\2"+
		"\u0135\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u013e"+
		"\3\2\2\2\u0138\u0136\3\2\2\2\u0139\u013a\5$\23\2\u013a\u013b\b\22\1\2"+
		"\u013b\u013d\3\2\2\2\u013c\u0139\3\2\2\2\u013d\u0140\3\2\2\2\u013e\u013c"+
		"\3\2\2\2\u013e\u013f\3\2\2\2\u013f#\3\2\2\2\u0140\u013e\3\2\2\2\u0141"+
		"\u0142\58\35\2\u0142\u0143\b\23\1\2\u0143\u015d\3\2\2\2\u0144\u0145\5"+
		":\36\2\u0145\u0146\b\23\1\2\u0146\u015d\3\2\2\2\u0147\u0148\5<\37\2\u0148"+
		"\u0149\b\23\1\2\u0149\u015d\3\2\2\2\u014a\u014b\5(\25\2\u014b\u014c\b"+
		"\23\1\2\u014c\u015d\3\2\2\2\u014d\u014e\5,\27\2\u014e\u014f\b\23\1\2\u014f"+
		"\u015d\3\2\2\2\u0150\u0151\5\66\34\2\u0151\u0152\b\23\1\2\u0152\u015d"+
		"\3\2\2\2\u0153\u0154\5\60\31\2\u0154\u0155\b\23\1\2\u0155\u015d\3\2\2"+
		"\2\u0156\u0157\5.\30\2\u0157\u0158\b\23\1\2\u0158\u015d\3\2\2\2\u0159"+
		"\u015a\5&\24\2\u015a\u015b\b\23\1\2\u015b\u015d\3\2\2\2\u015c\u0141\3"+
		"\2\2\2\u015c\u0144\3\2\2\2\u015c\u0147\3\2\2\2\u015c\u014a\3\2\2\2\u015c"+
		"\u014d\3\2\2\2\u015c\u0150\3\2\2\2\u015c\u0153\3\2\2\2\u015c\u0156\3\2"+
		"\2\2\u015c\u0159\3\2\2\2\u015d%\3\2\2\2\u015e\u015f\b\24\1\2\u015f\u0165"+
		"\7/\2\2\u0160\u0161\5$\23\2\u0161\u0162\b\24\1\2\u0162\u0164\3\2\2\2\u0163"+
		"\u0160\3\2\2\2\u0164\u0167\3\2\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2"+
		"\2\2\u0166\u0168\3\2\2\2\u0167\u0165\3\2\2\2\u0168\u0169\7\60\2\2\u0169"+
		"\'\3\2\2\2\u016a\u016b\5*\26\2\u016b\u016c\b\25\1\2\u016c\u016d\7\65\2"+
		"\2\u016d)\3\2\2\2\u016e\u016f\5@!\2\u016f\u0170\7(\2\2\u0170\u0171\5>"+
		" \2\u0171\u0172\b\26\1\2\u0172+\3\2\2\2\u0173\u0174\7\6\2\2\u0174\u0175"+
		"\7+\2\2\u0175\u0176\5> \2\u0176\u0177\b\27\1\2\u0177\u0178\7,\2\2\u0178"+
		"\u0179\7\65\2\2\u0179-\3\2\2\2\u017a\u017b\7\13\2\2\u017b\u017c\5> \2"+
		"\u017c\u017d\6\30\2\3\u017d\u017e\7\65\2\2\u017e/\3\2\2\2\u017f\u0180"+
		"\5\62\32\2\u0180\u0181\b\31\1\2\u0181\u0182\7\65\2\2\u0182\61\3\2\2\2"+
		"\u0183\u0184\5R*\2\u0184\u0198\b\32\1\2\u0185\u0186\7+\2\2\u0186\u0187"+
		"\5\64\33\2\u0187\u0188\7,\2\2\u0188\u0189\3\2\2\2\u0189\u018a\b\32\1\2"+
		"\u018a\u0197\3\2\2\2\u018b\u018c\7\63\2\2\u018c\u018d\5\\/\2\u018d\u018e"+
		"\3\2\2\2\u018e\u018f\b\32\1\2\u018f\u0197\3\2\2\2\u0190\u0191\7-\2\2\u0191"+
		"\u0192\5> \2\u0192\u0193\7.\2\2\u0193\u0194\3\2\2\2\u0194\u0195\b\32\1"+
		"\2\u0195\u0197\3\2\2\2\u0196\u0185\3\2\2\2\u0196\u018b\3\2\2\2\u0196\u0190"+
		"\3\2\2\2\u0197\u019a\3\2\2\2\u0198\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199"+
		"\u019b\3\2\2\2\u019a\u0198\3\2\2\2\u019b\u019c\7+\2\2\u019c\u019d\5\64"+
		"\33\2\u019d\u019e\7,\2\2\u019e\u019f\3\2\2\2\u019f\u01a0\b\32\1\2\u01a0"+
		"\63\3\2\2\2\u01a1\u01ad\b\33\1\2\u01a2\u01a3\5> \2\u01a3\u01aa\b\33\1"+
		"\2\u01a4\u01a5\7\62\2\2\u01a5\u01a6\5> \2\u01a6\u01a7\b\33\1\2\u01a7\u01a9"+
		"\3\2\2\2\u01a8\u01a4\3\2\2\2\u01a9\u01ac\3\2\2\2\u01aa\u01a8\3\2\2\2\u01aa"+
		"\u01ab\3\2\2\2\u01ab\u01ae\3\2\2\2\u01ac\u01aa\3\2\2\2\u01ad\u01a2\3\2"+
		"\2\2\u01ad\u01ae\3\2\2\2\u01ae\65\3\2\2\2\u01af\u01b0\7\n\2\2\u01b0\u01b4"+
		"\b\34\1\2\u01b1\u01b2\7\t\2\2\u01b2\u01b4\b\34\1\2\u01b3\u01af\3\2\2\2"+
		"\u01b3\u01b1\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b6\7\65\2\2\u01b6\67"+
		"\3\2\2\2\u01b7\u01b8\b\35\1\2\u01b8\u01b9\7\16\2\2\u01b9\u01ba\b\35\1"+
		"\2\u01ba\u01be\7+\2\2\u01bb\u01bc\5*\26\2\u01bc\u01bd\b\35\1\2\u01bd\u01bf"+
		"\3\2\2\2\u01be\u01bb\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0"+
		"\u01c4\7\65\2\2\u01c1\u01c2\5> \2\u01c2\u01c3\b\35\1\2\u01c3\u01c5\3\2"+
		"\2\2\u01c4\u01c1\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c6\3\2\2\2\u01c6"+
		"\u01ca\7\65\2\2\u01c7\u01c8\5*\26\2\u01c8\u01c9\b\35\1\2\u01c9\u01cb\3"+
		"\2\2\2\u01ca\u01c7\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb\u01cc\3\2\2\2\u01cc"+
		"\u01cd\7,\2\2\u01cd\u01ce\5$\23\2\u01ce\u01cf\b\35\1\2\u01cf9\3\2\2\2"+
		"\u01d0\u01d1\7\f\2\2\u01d1\u01d2\7+\2\2\u01d2\u01d3\5\\/\2\u01d3\u01d4"+
		"\7\r\2\2\u01d4\u01d5\5> \2\u01d5\u01d6\7,\2\2\u01d6\u01d7\5$\23\2\u01d7"+
		"\u01d8\b\36\1\2\u01d8;\3\2\2\2\u01d9\u01da\7\17\2\2\u01da\u01db\7+\2\2"+
		"\u01db\u01dc\5> \2\u01dc\u01dd\7,\2\2\u01dd\u01de\5$\23\2\u01de\u01e3"+
		"\b\37\1\2\u01df\u01e0\7\20\2\2\u01e0\u01e1\5$\23\2\u01e1\u01e2\b\37\1"+
		"\2\u01e2\u01e4\3\2\2\2\u01e3\u01df\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4="+
		"\3\2\2\2\u01e5\u01e6\5@!\2\u01e6\u01ec\b \1\2\u01e7\u01e8\7(\2\2\u01e8"+
		"\u01e9\b \1\2\u01e9\u01ea\5> \2\u01ea\u01eb\b \1\2\u01eb\u01ed\3\2\2\2"+
		"\u01ec\u01e7\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed?\3\2\2\2\u01ee\u01ef\5"+
		"B\"\2\u01ef\u01f7\b!\1\2\u01f0\u01f1\7%\2\2\u01f1\u01f2\b!\1\2\u01f2\u01f3"+
		"\5B\"\2\u01f3\u01f4\b!\1\2\u01f4\u01f6\3\2\2\2\u01f5\u01f0\3\2\2\2\u01f6"+
		"\u01f9\3\2\2\2\u01f7\u01f5\3\2\2\2\u01f7\u01f8\3\2\2\2\u01f8A\3\2\2\2"+
		"\u01f9\u01f7\3\2\2\2\u01fa\u01fb\5D#\2\u01fb\u0203\b\"\1\2\u01fc\u01fd"+
		"\7$\2\2\u01fd\u01fe\b\"\1\2\u01fe\u01ff\5D#\2\u01ff\u0200\b\"\1\2\u0200"+
		"\u0202\3\2\2\2\u0201\u01fc\3\2\2\2\u0202\u0205\3\2\2\2\u0203\u0201\3\2"+
		"\2\2\u0203\u0204\3\2\2\2\u0204C\3\2\2\2\u0205\u0203\3\2\2\2\u0206\u0207"+
		"\5F$\2\u0207\u0213\b#\1\2\u0208\u0209\7\36\2\2\u0209\u020d\b#\1\2\u020a"+
		"\u020b\7\35\2\2\u020b\u020d\b#\1\2\u020c\u0208\3\2\2\2\u020c\u020a\3\2"+
		"\2\2\u020d\u020e\3\2\2\2\u020e\u020f\5F$\2\u020f\u0210\b#\1\2\u0210\u0212"+
		"\3\2\2\2\u0211\u020c\3\2\2\2\u0212\u0215\3\2\2\2\u0213\u0211\3\2\2\2\u0213"+
		"\u0214\3\2\2\2\u0214E\3\2\2\2\u0215\u0213\3\2\2\2\u0216\u0217\5H%\2\u0217"+
		"\u0223\b$\1\2\u0218\u0219\7\33\2\2\u0219\u021d\b$\1\2\u021a\u021b\7\34"+
		"\2\2\u021b\u021d\b$\1\2\u021c\u0218\3\2\2\2\u021c\u021a\3\2\2\2\u021d"+
		"\u021e\3\2\2\2\u021e\u021f\5H%\2\u021f\u0220\b$\1\2\u0220\u0222\3\2\2"+
		"\2\u0221\u021c\3\2\2\2\u0222\u0225\3\2\2\2\u0223\u0221\3\2\2\2\u0223\u0224"+
		"\3\2\2\2\u0224G\3\2\2\2\u0225\u0223\3\2\2\2\u0226\u0227\5J&\2\u0227\u0233"+
		"\b%\1\2\u0228\u0229\7\"\2\2\u0229\u022d\b%\1\2\u022a\u022b\7#\2\2\u022b"+
		"\u022d\b%\1\2\u022c\u0228\3\2\2\2\u022c\u022a\3\2\2\2\u022d\u022e\3\2"+
		"\2\2\u022e\u022f\5J&\2\u022f\u0230\b%\1\2\u0230\u0232\3\2\2\2\u0231\u022c"+
		"\3\2\2\2\u0232\u0235\3\2\2\2\u0233\u0231\3\2\2\2\u0233\u0234\3\2\2\2\u0234"+
		"I\3\2\2\2\u0235\u0233\3\2\2\2\u0236\u0237\5L\'\2\u0237\u0245\b&\1\2\u0238"+
		"\u0239\7\37\2\2\u0239\u023f\b&\1\2\u023a\u023b\7 \2\2\u023b\u023f\b&\1"+
		"\2\u023c\u023d\7!\2\2\u023d\u023f\b&\1\2\u023e\u0238\3\2\2\2\u023e\u023a"+
		"\3\2\2\2\u023e\u023c\3\2\2\2\u023f\u0240\3\2\2\2\u0240\u0241\5L\'\2\u0241"+
		"\u0242\b&\1\2\u0242\u0244\3\2\2\2\u0243\u023e\3\2\2\2\u0244\u0247\3\2"+
		"\2\2\u0245\u0243\3\2\2\2\u0245\u0246\3\2\2\2\u0246K\3\2\2\2\u0247\u0245"+
		"\3\2\2\2\u0248\u0249\7&\2\2\u0249\u0251\b\'\1\2\u024a\u024b\7#\2\2\u024b"+
		"\u0251\b\'\1\2\u024c\u024d\7)\2\2\u024d\u0251\b\'\1\2\u024e\u024f\7*\2"+
		"\2\u024f\u0251\b\'\1\2\u0250\u0248\3\2\2\2\u0250\u024a\3\2\2\2\u0250\u024c"+
		"\3\2\2\2\u0250\u024e\3\2\2\2\u0251\u0252\3\2\2\2\u0252\u0253\5L\'\2\u0253"+
		"\u0254\b\'\1\2\u0254\u0259\3\2\2\2\u0255\u0256\5N(\2\u0256\u0257\b\'\1"+
		"\2\u0257\u0259\3\2\2\2\u0258\u0250\3\2\2\2\u0258\u0255\3\2\2\2\u0259M"+
		"\3\2\2\2\u025a\u025b\5P)\2\u025b\u0260\b(\1\2\u025c\u025d\7)\2\2\u025d"+
		"\u0261\b(\1\2\u025e\u025f\7*\2\2\u025f\u0261\b(\1\2\u0260\u025c\3\2\2"+
		"\2\u0260\u025e\3\2\2\2\u0260\u0261\3\2\2\2\u0261O\3\2\2\2\u0262\u0263"+
		"\5R*\2\u0263\u0277\b)\1\2\u0264\u0265\7+\2\2\u0265\u0266\5\64\33\2\u0266"+
		"\u0267\7,\2\2\u0267\u0268\3\2\2\2\u0268\u0269\b)\1\2\u0269\u0276\3\2\2"+
		"\2\u026a\u026b\7\63\2\2\u026b\u026c\5\\/\2\u026c\u026d\3\2\2\2\u026d\u026e"+
		"\b)\1\2\u026e\u0276\3\2\2\2\u026f\u0270\7-\2\2\u0270\u0271\5> \2\u0271"+
		"\u0272\7.\2\2\u0272\u0273\3\2\2\2\u0273\u0274\b)\1\2\u0274\u0276\3\2\2"+
		"\2\u0275\u0264\3\2\2\2\u0275\u026a\3\2\2\2\u0275\u026f\3\2\2\2\u0276\u0279"+
		"\3\2\2\2\u0277\u0275\3\2\2\2\u0277\u0278\3\2\2\2\u0278Q\3\2\2\2\u0279"+
		"\u0277\3\2\2\2\u027a\u027b\7\31\2\2\u027b\u028b\b*\1\2\u027c\u027d\5T"+
		"+\2\u027d\u027e\b*\1\2\u027e\u028b\3\2\2\2\u027f\u0280\5V,\2\u0280\u0281"+
		"\b*\1\2\u0281\u028b\3\2\2\2\u0282\u0283\5\\/\2\u0283\u0284\b*\1\2\u0284"+
		"\u028b\3\2\2\2\u0285\u0286\7+\2\2\u0286\u0287\5> \2\u0287\u0288\b*\1\2"+
		"\u0288\u0289\7,\2\2\u0289\u028b\3\2\2\2\u028a\u027a\3\2\2\2\u028a\u027c"+
		"\3\2\2\2\u028a\u027f\3\2\2\2\u028a\u0282\3\2\2\2\u028a\u0285\3\2\2\2\u028b"+
		"S\3\2\2\2\u028c\u028d\7\b\2\2\u028d\u028e\5\24\13\2\u028e\u028f\b+\1\2"+
		"\u028f\u0290\7+\2\2\u0290\u0291\5\64\33\2\u0291\u0292\b+\1\2\u0292\u0293"+
		"\7,\2\2\u0293U\3\2\2\2\u0294\u0295\5X-\2\u0295\u0296\b,\1\2\u0296\u02a1"+
		"\3\2\2\2\u0297\u0298\78\2\2\u0298\u02a1\b,\1\2\u0299\u029a\7\66\2\2\u029a"+
		"\u02a1\b,\1\2\u029b\u029c\7\25\2\2\u029c\u02a1\b,\1\2\u029d\u029e\5Z."+
		"\2\u029e\u029f\b,\1\2\u029f\u02a1\3\2\2\2\u02a0\u0294\3\2\2\2\u02a0\u0297"+
		"\3\2\2\2\u02a0\u0299\3\2\2\2\u02a0\u029b\3\2\2\2\u02a0\u029d\3\2\2\2\u02a1"+
		"W\3\2\2\2\u02a2\u02a3\7\27\2\2\u02a3\u02a7\b-\1\2\u02a4\u02a5\7\30\2\2"+
		"\u02a5\u02a7\b-\1\2\u02a6\u02a2\3\2\2\2\u02a6\u02a4\3\2\2\2\u02a7Y\3\2"+
		"\2\2\u02a8\u02a9\b.\1\2\u02a9\u02aa\7-\2\2\u02aa\u02ab\b.\1\2\u02ab\u02ac"+
		"\5\64\33\2\u02ac\u02ad\b.\1\2\u02ad\u02ae\7.\2\2\u02ae[\3\2\2\2\u02af"+
		"\u02b0\7\67\2\2\u02b0\u02b1\b/\1\2\u02b1]\3\2\2\2\66hr{}\u0088\u008a\u0093"+
		"\u0095\u0098\u00a8\u00ca\u00cd\u00e0\u00f0\u00fd\u0106\u0110\u0118\u0125"+
		"\u012e\u0136\u013e\u015c\u0165\u0196\u0198\u01aa\u01ad\u01b3\u01be\u01c4"+
		"\u01ca\u01e3\u01ec\u01f7\u0203\u020c\u0213\u021c\u0223\u022c\u0233\u023e"+
		"\u0245\u0250\u0258\u0260\u0275\u0277\u028a\u02a0\u02a6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}