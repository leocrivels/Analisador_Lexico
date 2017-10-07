/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anallex;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leo
 */
public class AnalLex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numLinha=1;
        Atomo atomo = new Atomo();
        List<Atomo> listaAtomos = new ArrayList<Atomo>();
        String identificador = new String();
        try{
         BufferedReader br = new BufferedReader(new FileReader("I:/teste.txt"));
         while(br.ready()){
            char letra = (char) br.read();
            if(letra == ' '){
                if(!identificador.isEmpty()){
                    //atomo.setAtomo(Token.IDENTIFICADOR);
                    //atomo.setLexema(identificador);
                    //atomo.defineAtributo();
                    //atomo.setNumLinha(numLinha);
                    //atomo.printAtomo();
                    atomo = new Atomo(numLinha, Token.IDENTIFICADOR,identificador);
                    atomo.printAtomo();
                    listaAtomos.add(atomo);
                    for(int i = 0 ; i < listaAtomos.size(); i++){
                            System.out.print(i + "  ");
                            listaAtomos.get(i).printAtomo();
                        }
                    identificador = new String();
            }
                letra= (char) br.read();
            }
            if(letra == '\n'){
                //System.out.print(numLinha);
                if(!identificador.isEmpty()){
                    /*atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);*/
                    //atomo.printAtomo();
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR,identificador));
                    identificador = new String();
                }
                numLinha++;
                letra = (char) br.read();
            }
            //Ignorando linha de comentário
            br.mark(2);
            if(letra == '/' && (char) br.read() == '/'){
                br.readLine();
                if(!identificador.isEmpty()){
                    /*atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);*/
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR,identificador));
                    //atomo.printAtomo();
                    identificador = new String();
                }
                numLinha++;
                letra= (char) br.read();
            }
            else{
                br.reset();
            }
                //System.out.print(letra);
            
                //Operadores aritméticos +, −, ∗ e /
                if(letra == '+' || letra == '-' || letra == '*' || letra == '/')
                {   
                    if(!identificador.isEmpty()){
                    /*atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);*/
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR,identificador));
                    
                    //atomo.printAtomo();
                    identificador = new String();
                }
                    
                    /*atomo.setAtomo(Token.OP_ARITMETICO);
                    atomo.setLexema(String.valueOf(letra));
                    atomo.defineAtributo();
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);*/
                    listaAtomos.add(new Atomo(numLinha, Token.OP_ARITMETICO,String.valueOf(letra)));
                    //atomo.printAtomo();
                    letra= (char) br.read();
                }

                //Operadores relacionais >, <, =
                if(letra == '>' || letra == '<' || letra == '='){
                    if(!identificador.isEmpty()){
                    /*atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);*/
                    listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR,identificador));
                    //atomo.printAtomo();
                    identificador = new String();
                }
                    
                    /*atomo.setAtomo(Token.OP_RELACIONAL);
                    atomo.setLexema(String.valueOf(letra));
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);*/
                    listaAtomos.add(new Atomo(numLinha, Token.OP_RELACIONAL, String.valueOf(letra)));
                    //atomo.printAtomo();
                    letra= (char) br.read();
                }

                //Separadores (Atomos Simples)
                if(letra == ':'){
                    if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                    
                    letra = (char) br.read();
                    if(letra == '='){
                        atomo.setAtomo(Token.ATRIBUICAO);
                        atomo.setLexema(":=");
                        atomo.defineAtributo();
                        atomo.setNumLinha(numLinha);
                        listaAtomos.add(atomo);
                        //atomo.printAtomo();
                    }
                    else{
                        atomo.setAtomo(Token.DOIS_PONTOS);
                        atomo.setLexema(":");
                        atomo.defineAtributo();
                        atomo.setNumLinha(numLinha);
                        listaAtomos.add(atomo);
                        //atomo.printAtomo();
                    }
                }
                if(letra == '('){
                    if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                    
                    atomo.setAtomo(Token.ABRE_PAR);
                    atomo.setLexema(String.valueOf(letra));
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    letra= (char) br.read();
                }
                if(letra == ')'){
                    if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                    
                    atomo.setAtomo(Token.FECHA_PAR);
                    atomo.setLexema(String.valueOf(letra));
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                }
                if(letra == ','){
                    if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                    
                    atomo.setAtomo(Token.VIRGULA);
                    atomo.setLexema(String.valueOf(letra));
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    letra= (char) br.read();
                }
                if(letra == ';'){
                    if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                    
                    atomo.setAtomo(Token.PONTO_VIRGULA);
                    atomo.setLexema(String.valueOf(letra));
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    letra= (char) br.read();
                }
                
                //Palavras Reservadas e Identificador
                if((letra >= 'A' && letra <= 'Z') || (letra >= 'a' && letra <= 'z') || (letra >= '0' && letra <= '9')){
                    //Palavra Reservada END
                    br.mark(3);
                    if(letra == 'E' && (char) br.read() == 'N' && (char) br.read() == 'D'){
                        if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                        
                        atomo.setAtomo(Token.END);
                        atomo.setLexema("END");
                        atomo.defineAtributo();
                        atomo.setNumLinha(numLinha);
                        listaAtomos.add(atomo);
                        //atomo.printAtomo();
                        for(int i = 0 ; i < listaAtomos.size(); i++){
                            System.out.print(i + "  ");
                            listaAtomos.get(i).printAtomo();
                        }
                        return;
                        //letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada READ
                    br.mark(4);
                    if(letra == 'R' && (char) br.read() == 'E' && (char) br.read() == 'A' && (char) br.read() == 'D'){
                        if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                        
                        atomo.setAtomo(Token.READ);
                        atomo.setLexema("READ");
                        atomo.setNumLinha(numLinha);
                        atomo.defineAtributo();
                        listaAtomos.add(atomo);
                        //atomo.printAtomo();
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada LET
                    br.mark(3);
                    if(letra == 'L' && (char) br.read() == 'E' && (char) br.read() == 'T'){
                        if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                        
                        atomo.setAtomo(Token.LET);
                        atomo.setLexema("LET");
                        atomo.defineAtributo();
                        atomo.setNumLinha(numLinha);
                        listaAtomos.add(atomo);
                        //atomo.printAtomo();
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada GO TO
                    br.mark(5);
                    if(letra == 'G' && (char) br.read() == 'O' && (char) br.read() == ' ' && (char) br.read() == 'T' && (char) br.read() == 'O'){
                        if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                        
                        atomo.setAtomo(Token.GO_TO);
                        atomo.setLexema("GO TO");
                        atomo.defineAtributo();
                        atomo.setNumLinha(numLinha);
                        listaAtomos.add(atomo);
                        //atomo.printAtomo();
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada PRINT
                    br.mark(5);
                    if(letra == 'P' && (char) br.read() == 'R' && (char) br.read() == 'I' && (char) br.read() == 'N' && (char) br.read() == 'T'){
                        if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                        
                        atomo.setAtomo(Token.PRINT);
                        atomo.setLexema("PRINT");
                        atomo.defineAtributo();
                        atomo.setNumLinha(numLinha);
                        listaAtomos.add(atomo);
                        //atomo.printAtomo();
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada IF
                    br.mark(2);
                    if(letra == 'I' && (char) br.read() == 'F'){
                        if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                        
                        atomo.setAtomo(Token.IF);
                        atomo.setLexema("IF");
                        atomo.defineAtributo();
                        atomo.setNumLinha(numLinha);
                        listaAtomos.add(atomo);
                        //atomo.printAtomo();
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada THEN
                    br.mark(4);
                    if(letra == 'T' && (char) br.read() == 'H' && (char) br.read() == 'E' && (char) br.read() == 'N'){
                        if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                        
                        atomo.setAtomo(Token.THEN);
                        atomo.setLexema("THEN");
                        atomo.defineAtributo();
                        atomo.setNumLinha(numLinha);
                        listaAtomos.add(atomo);
                        //atomo.printAtomo();
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    //Palavra Reservada ELSE
                    br.mark(5);
                    if(letra == 'E' && (char) br.read() == 'L' && (char) br.read() == 'S' && (char) br.read() == 'E'){
                        if(!identificador.isEmpty()){
                    atomo.setAtomo(Token.IDENTIFICADOR);
                    atomo.setLexema(identificador);
                    atomo.defineAtributo();
                    atomo.setNumLinha(numLinha);
                    listaAtomos.add(atomo);
                    //atomo.printAtomo();
                    identificador = new String();
                }
                        
                        atomo.setAtomo(Token.ELSE);
                        atomo.setLexema("ELSE");
                        atomo.defineAtributo();
                        atomo.setNumLinha(numLinha);
                        listaAtomos.add(atomo);
                        //atomo.printAtomo();
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }
                    
                    identificador = identificador.concat(String.valueOf(letra));
                }       
            }
         br.close();
      }catch(IOException ioe){
         ioe.printStackTrace();
      }
   }
    
}