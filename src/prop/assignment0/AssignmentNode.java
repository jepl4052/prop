package prop.assignment0;

import java.util.HashMap;

public class AssignmentNode implements INode {

    private Lexeme lex1, lex2, lex3;
    private INode expression;

    //assign = id , ’=’ , expr , ’;’ ;

    @Override
    public Object evaluate(Object[] args) throws Exception {

        args[1] = null;
        args[2] = null;
        this.expression.evaluate(args);

        Double result = (Double) args[1];

        HashMap<String, Double> variables = (HashMap<String, Double>) args[0];
        variables.put(this.lex1.value().toString(), result);

        StringBuilder s = new StringBuilder();
        s.append(this.lex1.value().toString())
                .append(" = ")
                .append(result)
                .append("\n");
        return s.toString();
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
