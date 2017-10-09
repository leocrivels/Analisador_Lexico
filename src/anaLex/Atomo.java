package anaLex;

/**
 *  Classe responsável por definir o comportamento e as características de um
 * átomo retornado pelo analisador léxico
 * @author Leonardo Crivellaro e Ikaro Arruda
 */
public class Atomo {
    private int numLinha;
    private Token atomo;
    private Token atributo;
    private String lexema;
    
    /**
     * Contrutor de átomo indefinido
     */
    public Atomo(){
        numLinha = 0;
        atomo = Token.INDEF;
        atributo = Token.INDEF;
    }
    
    /**
     * Construtor de átomo com atributos definidos
     * @param numLinha número da linha
     * @param atomo Átomo retornado
     * @param lexema Lexema
     */
    public Atomo(int numLinha, Token atomo, String lexema){
        this.numLinha = numLinha;
        this.atomo = atomo;
        this.lexema = lexema;
        if(this.atomo == Token.OP_ARITMETICO || this.atomo == Token.OP_RELACIONAL){
            defineAtributo();
        }
        else
            this.atributo = Token.INDEF;
    }

    public int getNumLinha() {
        return numLinha;
    }

    public void setNumLinha(int numLinha) {
        this.numLinha = numLinha;
    }

    public Token getAtomo() {
        return atomo;
    }

    public void setAtomo(Token atomo) {
        this.atomo = atomo;
    }

    public Token getAtributo() {
        return atributo;
    }

    public void setAtributo(Token atributo) {
        this.atributo = atributo;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
    
    /**
     * Método responsável por Definir qual o atributo do átomo
     */
    private void defineAtributo(){
            switch (this.lexema) {
                case "-":
                    this.atributo = Token.SUBTRACAO;
                    break;
                case "+":
                    this.atributo = Token.ADICAO;
                    break;
                case "*":
                    this.atributo = Token.MULTIPLICACAO;
                    break;
                case "/":
                    this.atributo = Token.DIVISAO;
                    break;
                case ">":
                    this.atributo = Token.MAIOR;
                    break;
                case "<":
                    this.atributo = Token.MENOR;
                    break;
                case "=":
                    this.atributo = Token.IGUAL;
                    break;
                default:
                    this.atributo = Token.INDEF;
                }
    }
    
    /**
     * Método responsável por imprimir os atributos do objeto átomo
     */
    public void printAtomo(){
        System.out.print("Linha:" + numLinha + "\n");
        System.out.print("Lexema:" + lexema + "\n");
        System.out.print("Atomo:" + atomo + "\n");
        System.out.print("Atributo:" + atributo + "\n\n");
    }
}
