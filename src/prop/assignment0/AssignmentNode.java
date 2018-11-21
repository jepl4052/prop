/**
 * @authors:
 * jens plate, jepl4052
 * erik hörnström, erho7892
 * marcus posette, mapo
 */

package prop.assignment0;

import java.text.DecimalFormat;
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

        // Cut all decimals except first one
        DecimalFormat oneDecimal = new DecimalFormat("#.#");
        String result = oneDecimal.format(args[1]);
        result = result.replace(',', '.');

        HashMap<String, Double> variables = (HashMap<String, Double>) args[0];
        variables.put(this.lex1.value().toString(), Double.parseDouble(result));

        StringBuilder s = new StringBuilder();
        s.append(this.lex1.value().toString())
                .append(" = ")
                .append(result)
                .append("\n");

        return s.toString();
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        Tabber.append(builder, tabs);
        builder.append("AssignmentNode\n");
        tabs++;

        Tabber.append(builder, tabs);

        builder.append(this.lex1.token())
                .append(" ")
                .append(this.lex1.value())
                .append("\n");

        Tabber.append(builder, tabs);

        builder.append(this.lex2.token())
                .append(" ")
                .append(this.lex2.value())
                .append("\n");

        if(expression != null) {
            expression.buildString(builder, tabs);
        }

        Tabber.append(builder, tabs);

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
