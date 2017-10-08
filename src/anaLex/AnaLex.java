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
                    if(Character.toUpperCase(letra) == 'E' && Character.toUpperCase((char) br.read()) == 'N' && Character.toUpperCase((char) br.read()) == 'D'){
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
                    if(Character.toUpperCase(letra) == 'R' && Character.toUpperCase((char) br.read()) == 'E' && Character.toUpperCase((char) br.read()) == 'A' && (char) Character.toUpperCase((char) br.read()) == 'D'){
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
                    if(Character.toUpperCase(letra) == 'L' && Character.toUpperCase((char) br.read()) == 'E' && Character.toUpperCase((char) br.read()) == 'T'){
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
                    if(Character.toUpperCase(letra) == 'G' && Character.toUpperCase((char) br.read()) == 'O' && Character.toUpperCase((char) br.read()) == ' ' && Character.toUpperCase((char) br.read()) == 'T' && Character.toUpperCase((char) br.read()) == 'O'){
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
                    if(Character.toUpperCase(letra) == 'P' && Character.toUpperCase((char) br.read()) == 'R' && Character.toUpperCase((char) br.read()) == 'I' && Character.toUpperCase((char) br.read()) == 'N' && Character.toUpperCase((char) br.read()) == 'T'){
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
                    if(Character.toUpperCase(letra) == 'I' && Character.toUpperCase((char) br.read()) == 'F'){
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
                    if(Character.toUpperCase(letra) == 'T' && Character.toUpperCase((char) br.read()) == 'H' && Character.toUpperCase((char) br.read()) == 'E' && Character.toUpperCase((char) br.read()) == 'N'){
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
                    if( Character.toUpperCase(letra) == 'E' && Character.toUpperCase((char) br.read()) == 'L' && Character.toUpperCase((char) br.read()) == 'S' && Character.toUpperCase((char) br.read()) == 'E'){
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
    
}