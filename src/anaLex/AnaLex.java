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
    public static List analisador_Lexico(String[] args) {
        int numLinha=1;
        Atomo atomo = new Atomo();
        List<Atomo> listaAtomos = new ArrayList<Atomo>();
        String identificador = new String();
        try{
             BufferedReader br = new BufferedReader(new FileReader(args[0]));
             while(br.ready()){
                char letra = (char) br.read();
                
                /** 
                 * Ignora linha de comentário
                 */
                br.mark(2);
                if(letra == '/' && (char) br.read() == '/'){
                    listaAtomos.add(new Atomo(numLinha, Token.COMENTARIO,"//"));
                    do{
                    letra= (char) br.read();
                    }while(letra != '\n');
                }
                else{
                    br.reset();
                }

                /**
                 * Ignora espaços
                 */
                if(letra == ' '){
                    letra= (char) br.read();
                }

                /**
                 * Ignora mudanças de linhas e as conta
                 */
                if(letra == '\n' ){
                    numLinha++;
                    letra= (char) br.read();
                }

                /**
                 * Reconhecedor de Palavras Reservadas e Identificadores
                 */
                if((Character.toUpperCase(letra) >= 'A' && Character.toUpperCase(letra) <= 'Z')){
                    /**
                     * Reconhece a Palavra Reservada END
                     */
                    br.mark(3);
                    if(Character.toUpperCase(letra) == 'E' && Character.toUpperCase((char) br.read()) == 'N' && Character.toUpperCase((char) br.read()) == 'D'){
                        listaAtomos.add(new Atomo(numLinha, Token.END, "END"));
                    }
                    else{
                        br.reset();
                    }

                    /**
                     * Reconhece a Palavra Reservada READ
                     */
                    br.mark(4);
                    if(Character.toUpperCase(letra) == 'R' && Character.toUpperCase((char) br.read()) == 'E' 
                            && Character.toUpperCase((char) br.read()) == 'A' && (char) Character.toUpperCase((char) br.read()) == 'D'){
                        listaAtomos.add(new Atomo(numLinha, Token.READ, "READ"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    /**
                     * Reconhece a Palavra Reservada LET
                     */
                    br.mark(3);
                    if(Character.toUpperCase(letra) == 'L' && Character.toUpperCase((char) br.read()) == 'E' && Character.toUpperCase((char) br.read()) == 'T'){
                        listaAtomos.add(new Atomo(numLinha, Token.LET, "LET"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    /**
                     * Reconhece a Palavra Reservada GO TO
                     */
                    br.mark(5);
                    if(Character.toUpperCase(letra) == 'G' && Character.toUpperCase((char) br.read()) == 'O' 
                            && Character.toUpperCase((char) br.read()) == ' ' && Character.toUpperCase((char) br.read()) == 'T' && Character.toUpperCase((char) br.read()) == 'O'){
                        listaAtomos.add(new Atomo(numLinha, Token.GO_TO, "GO TO"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    /**
                     * Reconhece a Palavra Reservada PRINT
                     */
                    br.mark(5);
                    if(Character.toUpperCase(letra) == 'P' && Character.toUpperCase((char) br.read()) == 'R' && Character.toUpperCase((char) br.read()) == 'I'
                            && Character.toUpperCase((char) br.read()) == 'N' && Character.toUpperCase((char) br.read()) == 'T'){
                        listaAtomos.add(new Atomo(numLinha, Token.PRINT, "PRINT"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    /**
                     * Reconhece a Palavra Reservada IF
                     */
                    br.mark(2);
                    if(Character.toUpperCase(letra) == 'I' && Character.toUpperCase((char) br.read()) == 'F'){
                        listaAtomos.add(new Atomo(numLinha, Token.IF, "IF"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    /**
                     * Reconhece a Palavra Reservada THEN
                     */
                    br.mark(4);
                    if(Character.toUpperCase(letra) == 'T' && Character.toUpperCase((char) br.read()) == 'H'
                            && Character.toUpperCase((char) br.read()) == 'E' && Character.toUpperCase((char) br.read()) == 'N'){
                        listaAtomos.add(new Atomo(numLinha, Token.THEN, "THEN"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }

                    /**
                     * Reconhece a Palavra Reservada ELSE
                     */
                    br.mark(4);
                    if( Character.toUpperCase(letra) == 'E' && Character.toUpperCase((char) br.read()) == 'L'
                            && Character.toUpperCase((char) br.read()) == 'S' && Character.toUpperCase((char) br.read()) == 'E'){
                        listaAtomos.add(new Atomo(numLinha, Token.ELSE, "ELSE"));
                        letra= (char) br.read();
                    }
                    else{
                        br.reset();
                    }
                    
                    /**
                     * Reconhece Identificadores
                     */
                    while((Character.toUpperCase(letra) >= 'A' && Character.toUpperCase(letra) <= 'Z') || (letra >= '0' && letra <= '9')){
                        identificador = identificador.concat(String.valueOf(letra));
                        letra = (char) br.read();
                    }

                }
                /**
                 * Reconhece Constantes Inteiros
                 */
                while(letra >= '0' && letra <= '9'){
                    identificador = identificador.concat(String.valueOf(letra));
                    letra = (char) br.read();
                }

                /**
                 * adiciona Constantes ou Identificadores à lista de átomos
                 * caso existam
                 */
                if(!identificador.isEmpty()){
                    int counter = 0;
                    for (char c : identificador.toCharArray())
                        {
                            if (Character.isDigit(c)){
                                counter++;
                            }
                        }
                    if(counter != identificador.length()){
                        listaAtomos.add(new Atomo(numLinha, Token.IDENTIFICADOR, identificador));
                    }
                    else{
                        listaAtomos.add(new Atomo(numLinha, Token.CONSTANTE_INTEIRO, identificador));
                    }
                    identificador = new String();
                }

                /**
                 * Reconhece Operadores aritméticos ("+", "−", "∗" e "/")
                 */
                br.mark(2);
                if(letra == '+' || letra == '-' || letra == '*')
                {
                    listaAtomos.add(new Atomo(numLinha, Token.OP_ARITMETICO, String.valueOf(letra)));
                }
                else if(letra == '/'){
                    br.mark(1);
                    if((char) br.read() == '/'){
                        listaAtomos.add(new Atomo(numLinha, Token.COMENTARIO,"//"));
                        do{
                            letra= (char) br.read();
                        }while(letra != '\n');
                    }
                    
                    else{
                        listaAtomos.add(new Atomo(numLinha, Token.OP_ARITMETICO, String.valueOf(letra)));
                        br.reset();
                    }
                }
                /**
                 * Reconhece Operadores relacionais (">", "<", "=")
                 */
                if(letra == '>' || letra == '<' || letra == '='){
                    listaAtomos.add(new Atomo(numLinha, Token.OP_RELACIONAL, String.valueOf(letra)));
                }

                /**
                 * Reconhece Separadores ( ":", ":=", "(", ")", ",", ";" ,)
                 */
                if(letra == ':'){
                    br.mark(1);
                    letra = (char) br.read();
                    if(letra == '='){
                        listaAtomos.add(new Atomo(numLinha, Token.ATRIBUICAO, ":="));
                    }
                    else{
                        listaAtomos.add(new Atomo(numLinha, Token.DOIS_PONTOS, ":"));
                        br.reset();
                    }
                }
                else if(letra == '('){
                    listaAtomos.add(new Atomo(numLinha, Token.ABRE_PAR, String.valueOf(letra)));
                }

                else if(letra == ')'){
                    listaAtomos.add(new Atomo(numLinha, Token.FECHA_PAR, String.valueOf(letra)));
                }

                else if(letra == ','){
                    listaAtomos.add(new Atomo(numLinha, Token.VIRGULA, String.valueOf(letra)));
                }

                else if(letra == ';'){
                    listaAtomos.add(new Atomo(numLinha, Token.PONTO_VIRGULA, String.valueOf(letra)));
                }
            }
         br.close();
      }catch(IOException ioe){
         ioe.printStackTrace();
      }
        for(int i = 0 ; i < listaAtomos.size(); i++){
                            listaAtomos.get(i).printAtomo();
                        }
        return listaAtomos;
   }
    
}