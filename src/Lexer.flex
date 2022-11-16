import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }
%}
/* Variables básicas de comentarios y espacios */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = {LineTerminator} | [ \t\f]
TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
CommentContent = ( [^*] | \*+ [^/*] )*
DocumentationComment  = "/**" {CommentContent } "*"+ "/"

/* comentarios */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

/* Identificador */
Letter = [A-Za-z_]
Digit = [0-9]
String = ('\w*'|"\w*")
Identifier = {Letter}({Letter}|{Digit})*

/* número */
Number = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comment}|{WhiteSpace} { /*Ignore*/ }

/* numero */
{Number} { return token(yytext(), "NUMBER", yyline, yycolumn); }

/* cadena de texto */
{String} { return token(yytext(), "STRING", yyline, yycolumn); }

/* operadores de agrupacion*/
"(" { return token(yytext(), "SYMBOL_PARENTESIS_O", yyline, yycolumn); }
")" { return token(yytext(), "SYMBOL_PARENTESIS_C", yyline, yycolumn); }

/* signos */
"," { return token(yytext(), "SYMBOL_COMA", yyline, yycolumn); }
":" { return token(yytext(), "SYMBOL_TWO_POINTS", yyline, yycolumn); }

/* operador de asignacion */
= { return token(yytext(), "SYMBOL_EQUALS", yyline, yycolumn); }

/* simbolos de estructura for */
for {return token(yytext(), "RESERVED_FOR", yyline, yycolumn); }
in {return token(yytext(), "RESERVED_IN", yyline, yycolumn); }
range {return token(yytext(), "RESERVED_RANGE", yyline, yycolumn); }
enumerate {return token(yytext(), "RESERVED_ENUMERATE", yyline, yycolumn); }

/* Identificador */
{Identifier} { return token(yytext(), "IDENTIFIER", yyline, yycolumn); }

. { return token(yytext(), "ERROR", yyline, yycolumn); }