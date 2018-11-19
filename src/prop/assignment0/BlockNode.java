package prop.assignment0;

public class BlockNode implements INode {

    // block = ’{’ , stmts , ’}’ ;

    private Lexeme leftLexeme, rightLexeme;
    private INode stmts;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append("BlockNode\n")
                .append(this.leftLexeme.token())
                .append(" ")
                .append(this.leftLexeme.value())
                .append("\n");
        tabs++;
        if(this.stmts != null) {
            this.stmts.buildString(builder, tabs);
        }
        builder.append(this.rightLexeme.token())
                .append(" ")
                .append(this.rightLexeme.value())
                .append("\n");
    }

    public void addLexeme(Lexeme l) {
        if(this.leftLexeme == null) {
            this.leftLexeme = l;
        }
        else if (this.rightLexeme == null) {
            this.rightLexeme = l;
        }
    }

    public void addStatement(INode n) {
        this.stmts = n;
    }
}
