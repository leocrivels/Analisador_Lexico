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
        defineAtributo();
        this.lexema = lexema;
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
    
    public void defineAtributo(){
        if(atomo == Token.OP_ARITMETICO ){
            switch (lexema) {
                case "-":
                    atributo = Token.SUBTRACAO;
                    break;
                case "+":
                    atributo = Token.ADICAO;
                    break;
                case "*":
                    atributo = Token.MULTIPLICACAO;
                    break;
                case "/":
                    atributo = Token.DIVISAO;
                    break;
                default:
                    break;
            }
        }
        else if(atomo == Token.OP_RELACIONAL){
            switch (lexema) {
                case ">":
                    atributo = Token.MAIOR;
                    break;
                case "<":
                    atributo = Token.MENOR;
                    break;
                case "=":
                    atributo = Token.IGUAL;
                    break;
                default:
                    break;
            }
        }
        else{
            atributo = Token.INDEF;
        }
    }
    
    public void printAtomo(){
        System.out.print("Linha:" + numLinha + "\n");
        System.out.print("Atomo:" + atomo + "\n");
        System.out.print("Atributo:" + atributo + "\n");
        System.out.print("Lexema:" + lexema + "\n");
    }
}
