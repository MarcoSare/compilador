package analizadores.lexico;
import analizadores.sintaxis.autCadenas;
import analizadores.sintaxis.autNumeros;
public class token {
    
    autCadenas AutCadenas;
    autNumeros AutNumeros;
    lexemas lex;

    String[] vocabulario= {"SI","SINO", "MIENTRAS", "HACER", "ENTERO","FLOTANTE","TEXTO",
                            "OPARITMETICO","OPLOGICO","OPCOMPARACION","OPASIGNACION","DELIMITADOR", "IDENTIFICADOR"};

    String getToken(String lexema){
        if(lexema.equals("si"))
            return "SI";
        if(lexema.equals("sino"))
            return "SINO";
        if(lexema.equals("mientras"))
            return "MIENTRAS";
        if(lexema.equals("hacer"))
            return "HACER";
        if(lexema.equals("entero"))
            return "ENTERO";
        if(lexema.equals("flotante"))
            return "FLOTANTE";
        if(lexema.equals("texto"))
            return "TEXTO";
        if(esOpArtimetico(lexema))
            return "OPARITMETICO";
        if(esOpLogico(lexema))
            return "OPLOGICO";
        if(esOpComparacion(lexema))
            return "OPCOMPARACION";
        if(lexema.equals("="))
            return "OPASIGNACION";
        if(lexema.equals(";"))
            return "DELIMINTADOR";

        String buscToken = buscarToken(lexema);
        if(buscToken!=null)
        return buscToken;

    return "TOKEN NO ENCONTRADO";
    }
    

    boolean esOpArtimetico(String lexema){
        if(lexema.equals("+") || lexema.equals("*") || lexema.equals("/") || lexema.equals("-") || lexema.equals("%"))
        return true;
        return false;
    }

    boolean esOpLogico(String lexema){
        if(lexema.equals("&&") || lexema.equals("||") || lexema.equals("!"))
        return true;
        return false;
    }

    boolean esOpComparacion(String lexema){
        if(lexema.equals("==") || lexema.equals("!=") || lexema.equals("<")|| lexema.equals(">")|| lexema.equals(">=") || lexema.equals("<="))
        return true;
        return false;
    }

    String buscarToken(String lexema){
        AutCadenas = new autCadenas(lexema);
        if(AutCadenas.start())
        return "CADENA";

        AutNumeros = new autNumeros(lexema);
        if(AutNumeros.start())
        return "NUMERO";

        return null;
    }


   public  String[] getListTokens(String linea){
        lex = new lexemas();
        String[] lexemas = lex.getLexemas(linea);
        String[] tokens = new String[lexemas.length];
        for(int i=0;lexemas.length>i;i++){
            tokens[i] = getToken(lexemas[i]);
            System.out.println(tokens[i]);
        }
           
        return tokens;
    }
    
}
