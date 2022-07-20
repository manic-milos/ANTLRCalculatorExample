grammar Calc;

start: expression EOF;


expression:
	left = expression operator = (MUL | DIV) right = expression		# MultiplicationOrDivision
	| left = expression operator = (ADD | SUB) right = expression	# AdditionOrSubtraction
	| (ADD | SUB)? '(' inner = expression ')'						# Parentheses
	| (ADD | SUB)? NUMBER											# Number;

/*
 * Tokens (terminal)
 */
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
NUMBER: [0-9]+ ([.] [0-9]+)* | ([.] [0-9]+)+;
WHITESPACE: [ \r\n\t]+ -> skip;
