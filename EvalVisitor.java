import antlrcalc.*;

class EvalVisitor extends CalcBaseVisitor<Float> {
    /**
     * Terminal node. Parses the number and returns a float to the parent expression.
     * @param context
     * @return parsed number if it is a valid number, null if it is not.
     */
    @Override
    public Float visitNumber(CalcParser.NumberContext context) {

        var text = context.NUMBER().getText();
        Float i = null;
        try {
            i = Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return null;
        }
        if (context.SUB() != null && i != null)
            return -i;
        return i;
    }

    /**
     * Expression that takes left and right subtrees and applies the operation that is in the op token. 
     * The operation used is determined by looking if there is a MUL token in the expression, if it is not,
     * by the grammar, there should be a DIV token, so division is applied. 
     * Possible improvement would be to check for DIV also, and throw exceptions if there is no op token.
     * @param context
     * @return multiplication or division of left and right subtrees results, or null if there was a problem in number parsing.
     */
    @Override
    public Float visitMultiplicationOrDivision(CalcParser.MultiplicationOrDivisionContext context) {
        Float i = null;
        Float left = this.visit(context.left);
        Float right = this.visit(context.right);
        if (left != null && right != null) {
            if (context.MUL() != null) {
                i = left * right;
            } else
                i = left / right;
        }
        return i;
    }
    /**
     * Expression that takes left and right subtrees and applies the operation that is in the op token. 
     * The operation used is determined by looking if there is an ADD token in the expression, if it is not,
     * by the grammar, there should be a SUB token, so substraction is applied. 
     * Possible improvement would be to check for SUB also, and throw exceptions if there is no op token.
     * @param context
     * @return addition or substraction of left and right subtrees results, or null if there was a problem in number parsing.
     */
    @Override
    public Float visitAdditionOrSubtraction(CalcParser.AdditionOrSubtractionContext context) {
        var operator = context.operator.getText();
        Float i = null;
        Float left = this.visit(context.left);
        Float right = this.visit(context.right);
        if (left != null && right != null) {
            if (context.ADD() != null) {
                i = left + right;
            } else {
                i = left - right;
            }
        }
        return i;
    }

    /**
     * Signed parentheses. By the grammar, there could be ADD(...), SUB(...) or (...). ADD is ignored, because
     * the result doesn't change. If there is a SUB token, the result should be negative of the result of the subtree
     * marked by inner in the context.
     * @param context
     * @return The result of the inner expression, negated, if there is a SUB token, or null if there was a number parsing problem.
     */
    @Override
    public Float visitParentheses(CalcParser.ParenthesesContext context) {
        Float i = visit(context.inner);
        if (context.SUB() != null && i != null)
            return -i;
        return i;
    }
    /**
     * visit method that is the starting point of the expression (root of the tree).
     * Posible improvements, check for EOF token at the end and throw exceptions if needed.
     * @param context
     * @return Float that is the result of the subtree, or null if there was a number parsing error.
     */
    @Override
    public Float visitStart(CalcParser.StartContext context) {
        return this.visit(context.expression());
    }
}
