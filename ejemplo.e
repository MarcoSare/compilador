// Bloque de codigo principal
principal {

// Declaracion de variables numericas
entero a = 1 ;
entero b = 10 ;

// Declaracion de variables de texto
texto c = "Hola" ;
texto d = "Mundo" ;

// Declaracion de variables booleanas
booleano e = verdad ;
booleano faa = falso ;

// Imprimir datos en la consola
imprimir "> Todas las variables declaradas <" ;
imprimir a+" "+b+" "+c+" "+d+" "+e+" "+faa;
imprimir "a: "+a ;
imprimir "b: "+b ;
imprimir "c: "+c ;
imprimir "d: "+d ;
imprimir "e: "+e ;
imprimir "f: "+faa ;

// Validacion y reasignacion de expresiones enteras
imprimir "> Actualizacion de las variables <" ;
a = b*(23+6)-1 ;
b = 2*23+a-1 ;
imprimir "a: "+a ;
imprimir "b: "+b ;


// Declaracion de estructura de desicion
imprimir "> Estructuras de desicion <" ;
si (a<b||b>=a){
imprimir "Condicion 1" ;
}
si (e&&faa){
imprimir "Condicion 2" ;
}
si (a <= b || b > a){
imprimir "Condicion 3" ;
}
si (e && faa && a<b){
imprimir "Condicion 4" ;
}

// Declaracion de estructura de iteracion
imprimir "> Estructuras de iteracion <" ;
entero cont = 0 ;
entero fin = 5 ;
mientras (fin > cont){
cont = cont+1 ;
imprimir "Cont: "+cont ;
}

// Llave de cierre del bloque principal
}
