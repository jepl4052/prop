/**
 * @authors:
 * jens plate, jepl4052
 * erik hörnström, erho7892
 * marcus posette, mapo8904
 */

package prop.assignment0;

public class StatementsNode implements INode {

    //stmts = [ assign , stmts ] ;

    private INode child1, child2;

    @Override
    public Object evaluate(Object[] args) throws Exception {

        return (this.child1 != null) ? this.child1.evaluate(args).toString() + this.child2.evaluate(args).toString() : "";
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        Tabber.append(builder, tabs);
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
