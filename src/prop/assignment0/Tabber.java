/**
 * @authors:
 * jens plate, jepl4052
 * erik hörnström, erho7892
 * marcus posette, mapo8904
 */

package prop.assignment0;

public class Tabber {

    static public void append(StringBuilder builder, int tabs) {
        for(int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
    }
}
