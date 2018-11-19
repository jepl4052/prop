package prop.assignment0;

public class AssignmentNode implements INode {

    private Lexeme lex1, lex2, lex3;
    private INode expression;

    //assign = id , ’=’ , expr , ’;’ ;

    @Override
    public Object evaluate(Object[] args) throws Exception {

        return new Statement(lex1, (double) this.expression.evaluate(args));

    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        for(int i = 0; i < tabs; i++) { builder.append("\t"); }

        builder.append("AssignmentNode\n");
        tabs++;

        for(int i = 0; i < tabs; i++) { builder.append("\t"); }

        builder.append(this.lex1.token())
                .append(" ")
                .append(this.lex1.value())
                .append("\n");

        for(int i = 0; i < tabs; i++) { builder.append("\t"); }

        builder.append(this.lex2.token())
                .append(" ")
                .append(this.lex2.value())
                .append("\n");

        if(expression != null) {
            expression.buildString(builder, tabs);
        }

        for(int i = 0; i < tabs; i++) { builder.append("\t"); }

        builder.append(this.lex3.token())
                .append(" ")
                .append(this.lex3.value())
                .append("\n");

    }

    public void addLexeme(Lexeme l) {
        if(this.lex1 == null) {
            this.lex1 = l;
        }
        else if (this.lex2 == null) {
            this.lex2 = l;
        }
        else if(this.lex3 == null) {
            this.lex3 = l;
        }
    }

    public void addExpression(INode n) {
        this.expression = n;
    }
}
