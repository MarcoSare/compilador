package analizadores.sintaxis;

import errores.nodoError;
import errores.pilaError;
import tblSimbolos.simbolo;
import tblSimbolos.tblSimbolo;

public class erBooleana {
    String cadena;
    int beginIndex =0;
    int endIndex =0;
    boolean estatus = false;
    int linea;
    pilaError PilaError;
    autCadenas AutCadenas;
    autNumeros AutNumeros;
    tblSimbolo TblSimbolo;
    public erBooleana(tblSimbolo TblSimbolo, String cadena, pilaError PilaError, int linea){
        this.TblSimbolo = TblSimbolo;
        this.cadena =cadena;
        this.PilaError = PilaError;
        this.linea = linea;
    }
simbolo c;

public boolean start(){
    q0();
    return estatus;
}
void q0(){
    endIndex = getFinalIndex(cadena);
    String aux = cadena.substring(beginIndex,endIndex);
    beginIndex = endIndex;
    if (aux.equals("verdad") || aux.equals("falso")|| veriEsVarBool(aux)){
        q1();
    }else{
        AutCadenas = new autCadenas(aux);
        AutNumeros = new autNumeros(aux);
        if(AutNumeros.start() || AutCadenas.start()||veriEsVarEnte(aux)||veriEsVarText(aux)){
            q2();
        }
        else{
            estatus = false;
            PilaError.push(new nodoError(String.valueOf(linea),"Error de sintaxis. Expresión booleana mal construida" , "0"));
        }
          
    }
}

void q1(){
    if(beginIndex==cadena.length()){
        qf();   
    }
   
    else{
        if(isOpeLog()){
           q0();
        }
        else{
            PilaError.push(new nodoError(String.valueOf(linea),"Error de sintaxis. operador desconocido" , "0"));
        }
    }
}


void q2(){
    if(isOpeComp()){
        q3();
    }
    else{
        PilaError.push(new nodoError(String.valueOf(linea),"Error de sintaxis. operador desconocido" , "0"));
    }
}


 void q3(){
    endIndex = getFinalIndex(cadena);
    String aux = cadena.substring(beginIndex,endIndex);
    beginIndex = endIndex;
    AutCadenas = new autCadenas(aux);
        AutNumeros = new autNumeros(aux);
        if(AutNumeros.start() || AutCadenas.start()||veriEsVarEnte(aux)||veriEsVarText(aux)){
            q1();
        }
        else 
        PilaError.push(new nodoError(String.valueOf(linea),"Error de sintaxis. Expresión booleana mal construida" , "0"));
 }


void qf(){
    estatus = true;
}
int getFinalIndex(String s){
    int i =beginIndex;
    boolean b = false;
    for(;s.length()> i && !b;i++){
        if(veriFianlIndex(s.charAt(i)))
        {
            i--;
            b=true;
        }
    }
    System.out.println("i: " +i);
    return i;
}

boolean veriFianlIndex(char c){
    if(c == '<' || c =='>' || c=='=' || c=='&' || c=='|' || c=='!')
    return true;
    return false;
}

boolean veriEsVarBool(String s){
    for(int i=0;this.TblSimbolo.tamanio()>i;i++){
        simbolo Simbolo = TblSimbolo.getSimbolo(i); 
        if(Simbolo.getToken().equals("VARBOOLEANO") && Simbolo.getDescripcion().equals(s))
        return true;
    }
    return false;
}


boolean veriEsVarEnte(String s){
    for(int i=0;this.TblSimbolo.tamanio()>i;i++){
        simbolo Simbolo = TblSimbolo.getSimbolo(i); 
        if(Simbolo.getToken().equals("VARENTERO") && Simbolo.getDescripcion().equals(s))
        return true;
    }
    return false;
}

boolean veriEsVarText(String s){
    for(int i=0;this.TblSimbolo.tamanio()>i;i++){
        simbolo Simbolo = TblSimbolo.getSimbolo(i); 
        if(Simbolo.getToken().equals("VARTEXTO") && Simbolo.getDescripcion().equals(s))
        return true;
    }
    return false;
}

boolean isOpeLog(){
    try{
        String opeLog = cadena.substring(beginIndex, beginIndex+2);
        System.out.println("ope" + opeLog);
        if(opeLog.equals("&&")||opeLog.equals("||") || opeLog.equals("==")){
            beginIndex+=2;
            return true;
        }
        
        else
        return false;

    }catch(Exception e){
        return false;
    }
}


boolean isOpeComp(){
    try{
        String opeComp = cadena.substring(beginIndex, beginIndex+2);
        System.out.println("ope c: " + opeComp);
        if(opeComp.equals(">=")||opeComp.equals("<=") || opeComp.equals("==") ||opeComp.equals("!=")){
            beginIndex+=2;
            return true;
        }
        
        else{
            opeComp = cadena.substring(beginIndex, beginIndex+1);
            System.out.println("ope1" + opeComp);
            if(opeComp.equals(">")||opeComp.equals("<")){
                beginIndex+=1;
                return true;
            }
            
        }
        return false;

    }catch(Exception e){
        System.out.println("ex: " + e);
        return false;
    }
}

}

