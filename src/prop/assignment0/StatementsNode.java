package prop.assignment0;

public class StatementsNode implements INode {

    //stmts = [ assign , stmts ] ;

    private INode child1, child2;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        for(int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
        builder.append("StatementsNode\n");
        tabs++;
        if(this.child1 != null) {
            this.child1.buildString(builder, tabs);
        }
        if(this.child2 != null) {
            this.child2.buildString(builder, tabs);
        }
    }

    public void addStatement(INode stmt) {
        if(this.child1 == null) {
            this.child1 = stmt;
        } else if (child2 == null) {
            this.child2 = stmt;
        }
    }
}
