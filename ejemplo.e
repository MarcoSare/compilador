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
booleano f = falso ;

// Declaracion de estructura de desicion
si (a<=b||b>=a){
}
si (e&&f){
}

// Declaracion de estructura de iteracion
mientras (a <= b || b >= a){
}
mientras (e && f){
}

// Imprimir datos en la consola
imprimir "> Inicio, "+c+" "+d+", Fin <" ;
imprimir "> Todas las variables declaradas <" ;
imprimir a+" "+b+" "+c+" "+d+" "+e+" "+f;
imprimir "a: "+a ;
imprimir "b: "+b ;
imprimir "c: "+c ;
imprimir "d: "+d ;
imprimir "e: "+e ;
imprimir "f: "+f ;

// Validacion y reasignacion de expresiones enteras
imprimir "> Actualizacion de las variables <" ;
a = 2*(23+6)-1 ;
b = 2*23+6-1 ;
imprimir "a: "+a ;
imprimir "b: "+b ;

// Llave de cierre del bloque principal
}
