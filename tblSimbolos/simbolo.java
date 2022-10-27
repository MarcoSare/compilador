package tblSimbolos;

public class simbolo {

    public String tipoSimbolo, nombre, tipo, tamanio, direccion, valor, id, descripcion;

    public simbolo(String[] simbolo){
        tipoSimbolo = simbolo[0];
        id = simbolo[1];
        descripcion = simbolo[2];
    }

    public String getToken(){
        return this.tipoSimbolo;
    }

    public String getId(){
        return this.id;
    }

    public String getDescripcion(){
        return this.descripcion;
    }
}
