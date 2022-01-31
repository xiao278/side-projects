import java.util.ArrayList;

public class Polynomial extends Term{
    private char variable; //the variable
    public Polynomial(String input) {
        super(input);
        variable = 'x';
        this.factor(variable);
    }

    public Polynomial(String input, char variable) {
        super(input);
        this.variable = variable;
        this.factor(variable);
    }
}
