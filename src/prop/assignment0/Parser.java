package prop.assignment0;

import java.io.IOException;

public class Parser implements IParser {

    private Tokenizer t = null;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        this.t = new Tokenizer();
        this.t.open(fileName);
        this.t.moveNext();
    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {

        if(this.t != null) {

            INode root = this.block();

            if (this.t.current().token() != Token.EOF) {
                throw new ParserException("ParserException: Reached EOF but no Token.EOF found");
            }

            return root;
        } else {
            throw new ParserException("ParserException: You have no Tokenizer instantiated.");
        }
    }

    @Override
    public void close() throws IOException {
        this.t.close();
    }

    private INode block() throws IOException, TokenizerException, ParserException {

        BlockNode bn = new BlockNode();
        Token currentToken = this.t.current().token();

        if(currentToken == Token.LEFT_CURLY) {
            bn.addLexeme(this.t.current());
            this.t.moveNext();
        } else {
            throw new ParserException("ParserException when creating blocknode. Expected { but got " + currentToken);
        }

        bn.addStatement(this.statement());
        currentToken = this.t.current().token();

        if(currentToken == Token.RIGHT_CURLY) {
            bn.addLexeme(this.t.current());
            this.t.moveNext();
        } else {
            throw new ParserException("ParserException when creating blocknode. Expected } but got " + currentToken);
        }

        return bn;
    }

    private INode statement() throws ParserException, IOException, TokenizerException {

        StatementsNode sn = new StatementsNode();
        Token currentToken = this.t.current().token();

        if(currentToken == Token.IDENT) {
            sn.addStatement(this.assign());
            sn.addStatement(this.statement());
        } else if (currentToken != Token.IDENT && currentToken != Token.RIGHT_CURLY) {
            throw new ParserException("ParserException when creating statementnode. Expected ID or } but got " + currentToken);
        }

        return sn;
    }

    private INode assign() throws IOException, TokenizerException, ParserException {

        AssignmentNode an = new AssignmentNode();
        an.addLexeme(this.t.current());
        this.t.moveNext();

        Token currentToken = this.t.current().token();

        if(currentToken == Token.ASSIGN_OP) {
            an.addLexeme(this.t.current());
            this.t.moveNext();
        } else {
            throw new ParserException("ParserException when creating assignmentnode. Expected = but got " + currentToken);
        }

        an.addExpression(this.expression());
        currentToken = this.t.current().token();

        if(currentToken == Token.SEMICOLON) {
            an.addLexeme(this.t.current());
            this.t.moveNext();
        } else {
            throw new ParserException("ParserException when creating assignmentnode. Expected ; but got " + currentToken);
        }

        return an;
    }

    private INode expression() throws IOException, TokenizerException, ParserException {

        ExpressionNode en = new ExpressionNode();
        en.addChild(this.term());

        Token currentToken = this.t.current().token();

        if(currentToken == Token.ADD_OP || currentToken == Token.SUB_OP) {
            en.addLexeme(this.t.current());
            this.t.moveNext();
            en.addChild(this.expression());
        }
        else if (currentToken != Token.IDENT
                        && currentToken != Token.INT_LIT
                        && currentToken != Token.RIGHT_PAREN
                        && currentToken != Token.SEMICOLON)
        {
            throw new ParserException("ParserException when creating expressionnode: Expected +, -, ID, INT, ) or ; but got" + currentToken);
        }

        return en;
    }

    private INode term() throws IOException, TokenizerException, ParserException {

        TermNode tn = new TermNode();
        tn.addChild(this.factor());

        Token currentToken = this.t.current().token();

        if(currentToken == Token.DIV_OP || currentToken == Token.MULT_OP) {
            tn.addLexeme(this.t.current());
            this.t.moveNext();
            tn.addChild(this.term());
        }
        else if (currentToken != Token.ADD_OP
                && currentToken != Token.SUB_OP
                && currentToken != Token.IDENT
                && currentToken != Token.INT_LIT
                && currentToken != Token.RIGHT_PAREN
                && currentToken != Token.SEMICOLON)
        {
            throw new ParserException("ParserException when creating termnode. Expected +, -, ID, INT, ) or ; but got " + currentToken);
        }

        return tn;
    }

    private INode factor() throws IOException, TokenizerException, ParserException {

        FactorNode fn = new FactorNode();
        Token currentToken = this.t.current().token();

        if(currentToken == Token.IDENT || currentToken == Token.INT_LIT) {
            fn.addLexeme(this.t.current());
            this.t.moveNext();
        }
        else if (currentToken == Token.LEFT_PAREN) {

            fn.addLexeme(this.t.current());
            this.t.moveNext();
            fn.addExpression(this.expression());
            currentToken = this.t.current().token();

            if(currentToken == Token.RIGHT_PAREN) {
                fn.addLexeme(this.t.current());
                this.t.moveNext();
            }
            else {
                throw new ParserException("ParserException when creating factornode. Expected ) but got " + currentToken);
            }
        }
        else if(currentToken != Token.DIV_OP
                && currentToken != Token.MULT_OP
                && currentToken != Token.IDENT
                && currentToken != Token.INT_LIT
                && currentToken != Token.LEFT_PAREN)
        {
            throw new ParserException("ParserException when creating factornode. Expected /, *, ID, INT or ( but got " + currentToken);
        }
        return fn;
    }
}
