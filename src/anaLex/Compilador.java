package anaLex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leo
 */
public class Compilador {
    private static int flagParenteses = 0; 
    
    public static void main(String[] args) {
        List<Atomo> listaAtomos = new ArrayList<Atomo>();
        listaAtomos = analisador_Lexico(args);
        listaAtomos.remove(listaAtomos.size()-1);
        
        for(int i = 0 ; i < listaAtomos.size(); i++){
                            listaAtomos.get(i).printAtomo();
                        }
        
        analisador_Sintatico(listaAtomos);
    }
    
    /**
     * Função principal do Analisador Léxico, responsável por ler um arquivo
     * e retornar seus átomos
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
                     * Reconhece a Palavra Reservada OF
                     */
                    br.mark(2);
                    if(Character.toUpperCase(letra) == 'O' && Character.toUpperCase((char) br.read()) == 'F' ){
                        listaAtomos.add(new Atomo(numLinha, Token.OF, "OF"));
                        letra = (char) br.read();
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
        return listaAtomos;
   }
    
    public static void analisador_Sintatico(List<Atomo> listaAtomos){
        for(int i = 0 ; i < listaAtomos.size(); i++){
            i = comando(listaAtomos, i);
            if(i == listaAtomos.size()-1 && listaAtomos.get(i).getAtomo() == Token.END){
                
                System.out.print("Análise Sintática bem sucedida");
            }
            else if((i < listaAtomos.size() && listaAtomos.get(i).getAtomo() == Token.PONTO_VIRGULA) || listaAtomos.get(i-1).getAtomo() == Token.COMENTARIO){
                i = comando(listaAtomos, i);
            }
            else{
                System.out.print("Erro: Esperado ';' na linha "+ listaAtomos.get(i).getNumLinha() +"Análise Sintática mal sucedida");
                break;
            }
        }
        }
    
    public static int comando(List<Atomo> listaAtomos, int i){
        Atomo atomo = listaAtomos.get(i);
        if(atomo.getAtomo() == Token.IDENTIFICADOR){
            atomo = listaAtomos.get(++i);
            if(atomo.getAtomo() == Token.DOIS_PONTOS){
                 i = comando(listaAtomos, i);
            }
            else{
                    System.out.print("Erro: Esperado identificador na linha " + atomo.getNumLinha());
                }
        }
            else if(atomo.getAtomo() == Token.LET){
                atomo = listaAtomos.get(++i);
                if(atomo.getAtomo() == Token.IDENTIFICADOR){
                    atomo = listaAtomos.get(++i);
                    if(atomo.getAtomo() == Token.ATRIBUICAO){
                        i = expressao(listaAtomos, ++i);
                        //atomo = listaAtomos.get(i);
                    }
                    else{
                        System.out.print("Erro: Esperado ':=' após o primeiro identificador na linha " + atomo.getNumLinha());
                    }
                }
                else{
                    System.out.print("Erro: Esperado identificador na linha " + atomo.getNumLinha());
                }
            }
            else if(atomo.getAtomo() == Token.GO_TO){
                atomo = listaAtomos.get(++i);
                if(atomo.getAtomo() == Token.IDENTIFICADOR){
                    atomo = listaAtomos.get(++i);
                    if(atomo.getAtomo() == Token.OF){
                        atomo = listaAtomos.get(++i);
                        if(atomo.getAtomo() == Token.IDENTIFICADOR){
                            while(atomo.getAtomo() == Token.IDENTIFICADOR){
                                atomo = listaAtomos.get(++i);
                                if(atomo.getAtomo() == Token.VIRGULA){
                                    atomo = listaAtomos.get(++i);
                                    if(atomo.getAtomo() != Token.IDENTIFICADOR){
                                        System.out.print("Erro: Esperado identificador após ',' na função FOR na linha " + atomo.getNumLinha());
                                    }
                                }
                                else if(atomo.getAtomo() != Token.PONTO_VIRGULA && atomo.getAtomo() != Token.END){
                                    System.out.print("Erro: Esperado ',' na função FOR na linha " + atomo.getNumLinha());
                                }
                            }
                        }
                        else{
                            System.out.print("Erro: Esperado identificadores na função FOR na linha " + atomo.getNumLinha());
                        }
                    }
                    else{
                        return i;
                    }
                    
                }
                else{
                    System.out.print("Erro: Esperado identificador na linha " + atomo.getNumLinha());
                }
                
            }
            else if(atomo.getAtomo() == Token.READ){
                atomo = listaAtomos.get(++i);
                if(atomo.getAtomo() == Token.IDENTIFICADOR){
                    while(atomo.getAtomo() == Token.IDENTIFICADOR){
                        atomo = listaAtomos.get(++i);
                        if(atomo.getAtomo() == Token.VIRGULA){
                            atomo = listaAtomos.get(++i);
                            }
                            else if(atomo.getAtomo() != Token.PONTO_VIRGULA && atomo.getAtomo() != Token.END){
                                System.out.print("Erro: Esperado ',' na função READ na linha " + atomo.getNumLinha());
                            }
                    }
                }
                else{
                    System.out.print("Erro: Esperado identificadores na função READ na linha " + atomo.getNumLinha());
                }
            }
            else if(atomo.getAtomo() == Token.PRINT){
                i = expressao(listaAtomos, ++i);
                atomo = listaAtomos.get(i);
                if(atomo.getAtomo() == Token.VIRGULA){
                    while(atomo.getAtomo() == Token.VIRGULA){
                        atomo = listaAtomos.get(++i);
                        if(atomo.getAtomo() == Token.IDENTIFICADOR){
                            atomo = listaAtomos.get(++i);
                        }
                        else{
                            System.out.print("Erro: Esperado identificador após a vírgula na função PRINT na linha " + atomo.getNumLinha());
                        }
                    }
                }
                return i;
            }
            else if(atomo.getAtomo() == Token.IF){
                i = expressao(listaAtomos, ++i);
                atomo = listaAtomos.get(i);
                if(atomo.getAtomo() == Token.OP_RELACIONAL ){
                    i = expressao(listaAtomos, ++i);
                    atomo = listaAtomos.get(i);
                    if(atomo.getAtomo() == Token.THEN){
                        i = comando(listaAtomos, ++i);
                        atomo = listaAtomos.get(i);
                        if(atomo.getAtomo() == Token.ELSE){
                            i = comando(listaAtomos, ++i);
                            atomo = listaAtomos.get(i);
                        }
                        else{
                            System.out.print("Erro: Esperado ELSE na linha " + atomo.getNumLinha());
                        }
                    }
                    else{
                        System.out.print("Erro: Esperado THEN na linha " + atomo.getNumLinha());
                    }
                }
                else{
                    System.out.print("Erro: Esperado Operador Relacional na linha " + atomo.getNumLinha());
                }
                }
            else if(atomo.getAtomo() == Token.COMENTARIO){
                i++;
            }
        return i;
    }
    
     public static int expressao(List<Atomo> listaAtomos, int i){
         Atomo atomo = listaAtomos.get(i);
         if(atomo.getAtomo() == Token.IDENTIFICADOR || atomo.getAtomo() == Token.CONSTANTE_INTEIRO){
             i++;
             if(i < listaAtomos.size()-1){
                atomo = listaAtomos.get(i);
                if(atomo.getAtomo() == Token.OP_ARITMETICO){
                    i = expressao(listaAtomos, ++i);
             }
                else if(atomo.getAtomo() == Token.FECHA_PAR){
                    i = expressao(listaAtomos, i);
                }
             }
             else{
                 return i;
             }
         }
         else if (atomo.getAtomo() == Token.ABRE_PAR){
             flagParenteses++;
             i = expressao(listaAtomos, ++i);
             atomo = listaAtomos.get(i);
             if (atomo.getAtomo() == Token.FECHA_PAR){
                atomo = listaAtomos.get(++i);
                if(atomo.getAtomo() == Token.OP_ARITMETICO){
                    i = expressao(listaAtomos, ++i);
                 }
                else{
                    return i;
                }
             }
             else{
                 System.out.print("Erro: Esperado ')' na linha " + atomo.getNumLinha());
             }
         }
         /* Verifica se */
         /*else if (atomo.getAtomo() == Token.FECHA_PAR){
             flagParenteses--;
             i++;
             atomo = listaAtomos.get(i);
             if(flagParenteses == 0){
                 if(atomo.getAtomo() == Token.OP_ARITMETICO){
                    i = expressao(listaAtomos, ++i);
                 }
                 else{
                 return i;
                 }
             }
             else if(flagParenteses > 0){
                 if(atomo.getAtomo() != Token.OP_ARITMETICO){
                     System.out.print("Erro: Esperado operador na linha " + atomo.getNumLinha());
                }
                 else{
                    i = expressao(listaAtomos, ++i);
                    }
                }
             else{
                 System.out.print("Erro: Esperado '(' anterior à ocorrência de ')' na linha " + atomo.getNumLinha());
             }
         }*/
         return i;
     }
     
    }
