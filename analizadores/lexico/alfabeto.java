package analizadores.lexico;
public class alfabeto {
    /* 
    public static void main(String[] args) {
        alfabeto a = new alfabeto();
        Scanner sc = new Scanner(System.in);
        
        if(a.validar(sc.nextLine()))
            System.out.println("Correcto");
        else 
            System.out.println("Incorrecto");
    }*/

    public boolean validar(String linea){
        int tamanio = linea.length();
        char c;
        for(int i=0;tamanio>i;i++){
            c = linea.charAt(i); 
            if(!((c>=32 && c<=125)|| c==164||c==165))
                return false;
        }
        return true;
    }
}
