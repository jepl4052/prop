package prop.assignment0;

import java.util.HashMap;

public class FactorNode implements INode {

    private Lexeme lex1, lex2;
    private INode expression;

    @Override
    public Object evaluate(Object[] args) throws Exception {

        if(this.expression == null) {
            if(this.lex1.token() == Token.INT_LIT) {
                return Double.parseDouble(this.lex1.value().toString());
            } else {
                HashMap<String, Double> variables = (HashMap<String, Double>) args[0];
                if(variables.containsKey(this.lex1.value())) {
                    return variables.get(this.lex1.value());
                } else {
                    return 0.0;
                }
            }
        } else {
            Object[] arr = {args[0], null, null};
            this.expression.evaluate(arr);
            return arr[1];
        }
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        for(int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
        builder.append("FactorNode\n");
        tabs++;

        if(this.lex1.token() == Token.IDENT || this.lex1.token() == Token.INT_LIT) {
            for(int i = 0; i < tabs; i++) {
                builder.append("\t");
            }
            builder.append(this.lex1.token())
                    .append(" ")
                    .append(this.lex1.value())
                    .append("\n");

        } else if (this.lex1.token() == Token.LEFT_PAREN) {
            for(int i = 0; i < tabs; i++) {
                builder.append("\t");
            }
            builder.append(this.lex1.token())
                    .append(" ")
                    .append(this.lex1.value())
                    .append("\n");

            if(this.expression != null)
                this.expression.buildString(builder, tabs);

            for(int i = 0; i < tabs; i++) {
                builder.append("\t");
            }
            builder.append(this.lex2.token())
                    .append(" ")
                    .append(this.lex2.value())
                    .append("\n");
        }
    }

    public void addExpression(INode n) {
        this.expression = n;
    }

    public void addLexeme(Lexeme l) {
        if(this.lex1 == null) {
            this.lex1 = l;
        } else if (this.lex2 == null) {
            this.lex2 = l;
        }
    }
}
