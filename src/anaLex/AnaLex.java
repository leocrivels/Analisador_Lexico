package anaLex;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal do Analisador Léxico, responsável por ler um arquivo
 * e retornar seus átomos
 * @author Leonardo Crivellaro e Ikaro Arruda
 */
public class AnaLex {

    /**
     * @param args arquivo de código
     */
    public static void main(String[] args) {
        int numLinha=1;
        Atomo atomo = new Atomo();
        List<Atomo> listaAtomos = new ArrayList<Atomo>();
        String identificador = new String();
        try{
         BufferedReader br = new BufferedReader(new FileReader(args[0]));
         while(br.ready()){
            char letra = (char) br.read();
            if(letra == ' '){
                if(!identificador.isEmpty()){
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR,identificador));
                    identificador = new String();
            }
                letra= (char) br.read();
            }
            if(letra == '\n'){
                //System.out.print(numLinha);
                if(!identificador.isEmpty()){
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR,identificador));
                    identificador = new String();
                }
                numLinha++;
                letra = (char) br.read();
            }
            
            //Ignorando linha de comentário
            br.mark(2);
            if(letra == '/' && (char) br.read() == '/'){
                if(!identificador.isEmpty()){
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR,identificador));
                    identificador = new String();
                }
                numLinha++;
                do{
                letra= (char) br.read();
                }while(letra != '\n');
            }
            else{
                br.reset();
            }
            
                //Operadores aritméticos +, −, ∗ e /
                if(letra == '+' || letra == '-' || letra == '*' || letra == '/')
                {   
                    if(!identificador.isEmpty()){
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR,identificador));
                    identificador = new String();
                }
                    listaAtomos.add(new Atomo(numLinha, Token.OP_ARITMETICO, String.valueOf(letra)));
                    letra= (char) br.read();
                }

                //Operadores relacionais >, <, =
                if(letra == '>' || letra == '<' || letra == '='){
                    if(!identificador.isEmpty()){
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR,identificador));
                    identificador = new String();
                }
                    listaAtomos.add(new Atomo(numLinha, Token.OP_RELACIONAL, String.valueOf(letra)));
                    letra= (char) br.read();
                }

                //Separadores (Atomos Simples)
                if(letra == ':'){
                    if(!identificador.isEmpty()){
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                    identificador = new String();
                }
                    letra = (char) br.read();
                    if(letra == '='){
                        listaAtomos.add(new Atomo(numLinha, Token.ATRIBUICAO, ":="));
                    }
                    else{
                        listaAtomos.add(new Atomo(numLinha, Token.DOIS_PONTOS, ":"));
                    }
                }
                if(letra == '('){
                    if(!identificador.isEmpty()){
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                    identificador = new String();
                }
                    listaAtomos.add(new Atomo(numLinha, Token.ABRE_PAR, String.valueOf(letra)));
                    letra= (char) br.read();
                }
                
                if(letra == ')'){
                    if(!identificador.isEmpty()){
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                    identificador = new String();
                }
                    listaAtomos.add(new Atomo(numLinha, Token.FECHA_PAR, String.valueOf(letra)));
                    letra = (char) br.read();
                }
                
                if(letra == ','){
                    if(!identificador.isEmpty()){
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                    identificador = new String();
                }
                    listaAtomos.add(new Atomo(numLinha, Token.VIRGULA, String.valueOf(letra)));
                    letra= (char) br.read();
                }
                
                if(letra == ';'){
                    if(!identificador.isEmpty()){
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                    identificador = new String();
                }
                    listaAtomos.add(new Atomo(numLinha, Token.PONTO_VIRGULA, String.valueOf(letra)));
                    letra= (char) br.read();
                }
                
                //Palavras Reservadas e Identificador
                if((letra >= 'A' && letra <= 'Z') || (letra >= 'a' && letra <= 'z') || (letra >= '0' && letra <= '9')){
                    //Palavra Reservada END
                    br.mark(3);
                    if(letra == 'E' && (char) br.read() == 'N' && (char) br.read() == 'D'){
                        if(!identificador.isEmpty()){
                            listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                            identificador = new String();
                        }
                        
                        listaAtomos.add(new Atomo(numLinha, Token.END, "END"));
                        for(int i = 0 ; i < listaAtomos.size(); i++){
                            listaAtomos.get(i).printAtomo();
                        }
                        return;
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada READ
                    br.mark(4);
                    if(letra == 'R' && (char) br.read() == 'E' && (char) br.read() == 'A' && (char) br.read() == 'D'){
                        if(!identificador.isEmpty()){
                            listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                            identificador = new String();
                        }
                        
                        listaAtomos.add(new Atomo(numLinha, Token.READ, "READ"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada LET
                    br.mark(3);
                    if(letra == 'L' && (char) br.read() == 'E' && (char) br.read() == 'T'){
                        if(!identificador.isEmpty()){
                            listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                            identificador = new String();
                        }

                        listaAtomos.add(new Atomo(numLinha, Token.LET, "LET"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada GO TO
                    br.mark(5);
                    if(letra == 'G' && (char) br.read() == 'O' && (char) br.read() == ' ' && (char) br.read() == 'T' && (char) br.read() == 'O'){
                        if(!identificador.isEmpty()){
                            listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                            identificador = new String();
                        }
                        
                        listaAtomos.add(new Atomo(numLinha, Token.GO_TO, "GO TO"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada PRINT
                    br.mark(5);
                    if(letra == 'P' && (char) br.read() == 'R' && (char) br.read() == 'I' && (char) br.read() == 'N' && (char) br.read() == 'T'){
                        if(!identificador.isEmpty()){
                            listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                            identificador = new String();
                        }

                        listaAtomos.add(new Atomo(numLinha, Token.PRINT, "PRINT"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada IF
                    br.mark(2);
                    if(letra == 'I' && (char) br.read() == 'F'){
                        if(!identificador.isEmpty()){
                            listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                            identificador = new String();
                        }
                        
                        listaAtomos.add(new Atomo(numLinha, Token.IF, "IF"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada THEN
                    br.mark(4);
                    if(letra == 'T' && (char) br.read() == 'H' && (char) br.read() == 'E' && (char) br.read() == 'N'){
                        if(!identificador.isEmpty()){
                            listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                            identificador = new String();
                        }
                        
                        listaAtomos.add(new Atomo(numLinha, Token.THEN, "THEN"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada ELSE
                    br.mark(5);
                    if(letra == 'E' && (char) br.read() == 'L' && (char) br.read() == 'S' && (char) br.read() == 'E'){
                        if(!identificador.isEmpty()){
                            listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                            identificador = new String();
                        }

                        listaAtomos.add(new Atomo(numLinha, Token.ELSE, "ELSE"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }
                    if((letra >= 'A' && letra <= 'Z') || (letra >= 'a' && letra <= 'z') || (letra >= '0' && letra <= '9')){
                        identificador = identificador.concat(String.valueOf(letra));
                    }
                }
            }
         br.close();
      }catch(IOException ioe){
         ioe.printStackTrace();
      }
   }
    
    /**
    *  Classe responsável por definir o comportamento e as características de um
    * átomo retornado pelo analisador léxico
    * @author Leonardo Crivellaro e Ikaro Arruda
    */
    private static class Atomo {
        
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
    
    /**
    *  Lista dos possíveis átomos da linguagem MINILANG
    * @author Leonardo Crivellaro e Ikaro Arruda
    */
   public enum Token {
       END,
       READ,
       LET,
       IDENTIFICADOR,
       GO_TO,
       OF,
       PRINT,
       IF,
       THEN,
       ELSE,
       ATRIBUICAO,
       ABRE_PAR,
       FECHA_PAR,
       VIRGULA,
       PONTO_VIRGULA,
       DOIS_PONTOS,
       OP_ARITMETICO,
       OP_RELACIONAL,
       SUBTRACAO,
       ADICAO,
       MULTIPLICACAO,
       DIVISAO,
       MAIOR,
       IGUAL,
       MENOR,
       INDEF
   }
    
}