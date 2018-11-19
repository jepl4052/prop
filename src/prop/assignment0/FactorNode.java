package prop.assignment0;

public class FactorNode implements INode {

    private Lexeme lex1, lex2;
    private INode expression;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        if(this.lex2 != null) {
            System.out.println("Factor, 1");
            return this.expression.evaluate(args);
        }
        if (this.lex1.token() == Token.IDENT) {

            Statement stmt = new Statement(null, 0);

            for(int i = 0; i < args.length; i++) {

                System.out.println(args[i].getClass() + " = " + stmt.getClass());

                if (args[i].getClass() == stmt.getClass()) {
                    stmt = (Statement) args[i];
                }

                System.out.println(stmt.getId().value() + " = " + lex1.value());

                if (stmt.getId().value().equals(lex1.value())) {
                        System.out.println("Factor, 2");
                        return stmt.getValue();
                    }
            }
        }
        System.out.println("Factor, 3");
        return Double.parseDouble(this.lex1.value().toString());
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
