package prop.assignment0;

public class ExpressionNode implements INode {

    //expr = term , [ ( ’+’ | ’-’ ) , expr ] ;

    private Lexeme lex;
    private INode child1, child2;

    @Override
    public Object evaluate(Object[] args) throws Exception {

        Double result = (Double) args[1];
        Token token = (Token) args[2];

        if(result != null && token != null) {
            args[1] = null;
            args[2] = null;
            this.child1.evaluate(args);

            if(token == Token.SUB_OP) {
                args[1] = result - (Double) args[1];
            } else {
                args[1] = result + (Double) args[1];
            }
        } else {
            this.child1.evaluate(args);
        }
        if(this.lex != null) {
            args[2] = this.lex.token();
            this.child2.evaluate(args);
        }
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        for(int i = 0; i < tabs; i++) { builder.append("\t"); }

        builder.append("ExpressionNode\n");
        tabs++;

        if(this.child1 != null) {
            this.child1.buildString(builder, tabs);
        }

        if(this.lex != null) {

            for(int i = 0; i < tabs; i++) { builder.append("\t"); }

            builder.append(this.lex.token())
                    .append(" ")
                    .append(this.lex.value())
                    .append("\n");

            if (this.child2 != null) {
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
        }
        else if(this.child2 == null) {
            this.child2 = n;
        }
    }
}
