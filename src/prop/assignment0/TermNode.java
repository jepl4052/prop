/**
 * @authors:
 * jens plate, jepl4052
 * erik hörnström, erho7892
 * marcus posette, mapo8904
 */

package prop.assignment0;

public class TermNode implements INode {

    // term = factor , [ ( ’*’ | ’/’) , term] ;

    private Lexeme lex;
    private INode child1, child2;
    @Override
    public Object evaluate(Object[] args) throws Exception {

        Double result = (Double) args[1];
        Token token = (Token) args[2];
        Double value = (Double) this.child1.evaluate(args);

        if(result != null && token != null) {

            if(token == Token.DIV_OP) {
                args[1] = result / value;
            }
            else {
                args[1] = result * value;
            }
        }
        else {
            args[1] = value;
        }

        if(this.lex != null) {
            args[2] = this.lex.token();
            this.child2.evaluate(args);
        }
        return args;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        Tabber.append(builder, tabs);
        builder.append("TermNode\n");
        tabs++;

        if (this.child1 != null) {
            this.child1.buildString(builder, tabs);
        }

        if (this.lex != null) {

            Tabber.append(builder, tabs);

            builder.append(this.lex.token())
                    .append( " ")
                    .append(this.lex.value())
                    .append( "\n");

            if(this.child2 != null) {
                this.child2.buildString(builder, tabs);
            }
        }
    }

    public void addLexeme(Lexeme l) {
        this.lex = l;
    }

    public void addChild(INode n) {
        if(this.child1 == null) {
            this.child1 = n;
        } else if (this.child2 == null) {
            this.child2 = n;
        }
    }
}
