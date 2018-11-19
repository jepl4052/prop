package prop.assignment0;

public class ExpressionNode implements INode {

    //expr = term , [ ( ’+’ | ’-’ ) , expr ] ;

    private Lexeme lex;
    private INode child1, child2;

    @Override
    public Object evaluate(Object[] args) throws Exception {

        if(this.child2 != null) {
            if(this.lex.token() == Token.ADD_OP) {
                // a = 4 + 3 + 2
                // child1.value + child2.value then get rest of child 2
                return (double) this.child1.evaluate(args) + (double) this.child2.evaluate(args);
            } else {
                return (double) this.child1.evaluate(args) - (double) this.child2.evaluate(args);
            }
        } else {
            return this.child1.evaluate(args);
        }

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
