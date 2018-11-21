/**
 * @authors:
 * jens plate, jepl4052
 * erik hörnström, erho7892
 * marcus posette, mapo8904
 */

package prop.assignment0;

import java.io.IOException;

public class Tokenizer implements ITokenizer {

    private Scanner scanner = null;
    private Lexeme currentLexeme;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        this.currentLexeme = null;
        this.scanner = new Scanner();
        this.scanner.open(fileName);

    }

    @Override
    public Lexeme current() {
        return this.currentLexeme;

    }

    @Override
    public void moveNext() throws IOException, TokenizerException {

        if(this.scanner != null) {

            while (Character.isWhitespace(this.scanner.current()) || this.scanner.current() == Scanner.NULL) {
                //this.currentLexeme = new Lexeme(Scanner.NULL, Token.NULL); //could be implemented, but why?
                this.scanner.moveNext();
            }

            char currentChar = this.scanner.current();

            if (currentChar == this.scanner.EOF) {
                this.currentLexeme = new Lexeme(currentChar, Token.EOF);
            }
            else if (Character.toString(currentChar).matches("[a-z]")) {
                this.checkChar(
                        this.getIdOrInt(
                                new StringBuilder(), "[a-z]"
                        )
                );
            }
            else if (Character.toString(currentChar).matches("[\\d]")) {
                this.checkChar(
                        this.getIdOrInt(
                                new StringBuilder(), "[\\d]"
                        )
                );
            }
            else {
                this.checkChar(Character.toString(currentChar));
                this.scanner.moveNext();
            }
        }
    }

    private String getIdOrInt(StringBuilder identifier, String regex) throws IOException {

        String currentChar = Character.toString(this.scanner.current());

        if(currentChar.matches(regex)) {
            identifier.append(currentChar);
            this.scanner.moveNext();
            this.getIdOrInt(identifier, regex);
        }
        return identifier.toString();
    }

    private void checkChar(String findChar) throws TokenizerException {

        switch (findChar) {

            case "+":
                this.currentLexeme = new Lexeme(findChar, Token.ADD_OP);
                break;

            case "-":
                this.currentLexeme = new Lexeme(findChar, Token.SUB_OP);
                break;

            case "*":
                this.currentLexeme = new Lexeme(findChar, Token.MULT_OP);
                break;

            case "/":
                this.currentLexeme = new Lexeme(findChar, Token.DIV_OP);
                break;

            case "=":
                this.currentLexeme = new Lexeme(findChar, Token.ASSIGN_OP);
                break;

            case ";":
                this.currentLexeme = new Lexeme(findChar, Token.SEMICOLON);
                break;

            case "(":
                this.currentLexeme = new Lexeme(findChar, Token.LEFT_PAREN);
                break;

            case ")":
                this.currentLexeme = new Lexeme(findChar, Token.RIGHT_PAREN);
                break;

            case "{":
                this.currentLexeme = new Lexeme(findChar, Token.LEFT_CURLY);
                break;

            case "}":
                this.currentLexeme = new Lexeme(findChar, Token.RIGHT_CURLY);
                break;

            default:
                if(findChar.matches("[a-z]+")) {
                    this.currentLexeme = new Lexeme(findChar, Token.IDENT);
                }
                else if (findChar.matches("[\\d]+")) {
                    this.currentLexeme = new Lexeme(Double.parseDouble(findChar), Token.INT_LIT);
                }
                else {
                    throw new TokenizerException("TokenizerException: Not valid: " + findChar);
                }
        }
    }

    @Override
    public void close() throws IOException {
        this.scanner.close();
    }
}
