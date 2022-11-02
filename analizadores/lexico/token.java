package analizadores.lexico;
import analizadores.sintaxis.autCadenas;
import analizadores.sintaxis.autNumeros;

public class token {
    
    autCadenas AutCadenas;
    autNumeros AutNumeros;
    lexemas lex;
    //String[] retorno;
    String[] vocabulario= {"SI","SINO","MIENTRAS","HACER",
                            "ENTERO","FLOTANTE","TEXTO",
                            "OPARITMETICO","OPLOGICO",
                            "OPCOMPARACION","OPASIGNACION",
                            "DELIMITADOR", "IDENTIFICADOR"};

    String getToken(String lexema){
        if(lexema.equals("si"))
            return "SI,1,Palabra reservada,"+lexema;
        if(lexema.equals("sino"))
            return "SINO,2,Palabra reservada,"+lexema;    
        if(lexema.equals("mientras"))
            return "MIENTRAS,3,Palabra reservada,"+lexema;    
        if(lexema.equals("hacer"))
            return "HACER,4,Palabra reservada,"+lexema;    
        if(lexema.equals("entero"))
            return "ENTERO,11,Palabra reservada,"+lexema;    
        if(lexema.equals("flotante"))
            return "FLOTANTE,12,Palabra reservada,"+lexema;    
        if(lexema.equals("texto"))
            return "TEXTO,13,Palabra reservada,"+lexema;    
        if(esOpArtimetico(lexema))
            return "OPARITMETICO,21,Operador aritmetico,"+lexema;    
        if(esOpLogico(lexema))
            return "OPLOGICO,31,Operador lógico,"+lexema;       
        if(esOpComparacion(lexema))
            return "OPCOMPARACION,41,Operador de comparación,"+lexema;    
        if(lexema.equals("="))
            return "OPASIGNACION,51,Operador de asignación,"+lexema;    
        if(lexema.equals(";"))
            return "DELIMITADOR,60,delimitador,"+lexema;
        String buscToken = buscarToken(lexema);
        if(buscToken!=null)
            return buscToken;
        return "TOKEN NO ENCONTRADO,-1,Token no especificado,"+lexema;
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
        if(lexema.equals("==") || lexema.equals("!=") || lexema.equals("<") 
        || lexema.equals(">") || lexema.equals(">=") || lexema.equals("<="))
            return true;
        return false;
    }

    String buscarToken(String lexema){
        AutCadenas = new autCadenas(lexema);
        if(AutCadenas.start())
            return "CADENA,70,Tipo de dato Cadena,"+lexema;
        AutNumeros = new autNumeros(lexema);
        if(AutNumeros.start())
            return "NUMERO,71,Tipo de dato Numero,"+lexema;
        return null;
    }

    public  String[] getListTokens(String linea){
        lex = new lexemas();
        String[] lexemas = lex.getLexemas(linea);
        String[] tokens = new String[lexemas.length];
        for(int i=0;lexemas.length>i;i++){
            tokens[i] = getToken(lexemas[i]);
            //System.out.println(tokens[i]);
        }
        return tokens;
    }
}