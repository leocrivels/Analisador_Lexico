/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anallex;

/**
 *
 * @author Leo
 */
public class Atomo {
    private int numLinha;
    private Token atomo;
    private Token atributo;
    private String lexema;
    
    public Atomo(){
        numLinha = 0;
        atomo = Token.INDEF;
        atributo = Token.INDEF;
    }
    
    public Atomo(int numLinha, Token atomo, String lexema){
        this.numLinha = numLinha;
        this.atomo = atomo;
        this.lexema = lexema;
        defineAtributo();
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
    
    public void printAtomo(){
        System.out.print("Linha:" + numLinha + "\n");
        System.out.print("Lexema:" + lexema + "\n");
        System.out.print("Atomo:" + atomo + "\n");
        System.out.print("Atributo:" + atributo + "\n\n");
    }
}
