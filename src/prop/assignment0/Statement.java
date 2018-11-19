package prop.assignment0;

public class Statement {
    private Lexeme id;
    private double value;

    public Statement(Lexeme id, double v) {
        this.id = id;
        this.value = v;
    }

    public Lexeme getId() {
        return this.id;
    }

    public double getValue() {
        return this.value;
    }
}