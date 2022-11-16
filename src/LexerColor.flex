import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
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

/* Comentario */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

/* Identificador */
Letter = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digit = [0-9]
String = ('\w*'|"\w*")
Identifier = {Letter}({Letter}|{Digit})*

/* Número */
Number = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comment} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{WhiteSpace} { /*Ignorar*/ }

/* Identificador */

/* operadores de agrupacion*/
"(" | ")" { return textColor(yychar, yylength(), new Color(169, 155, 179)); }

/* palabras reservadas */
for | in { return textColor(yychar, yylength(), new Color(255, 87, 51)); }
range | enumerate { return textColor(yychar, yylength(), new Color(0, 111, 189)); }

. { /* Ignorar */ }