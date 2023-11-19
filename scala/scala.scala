import scala.util.Random

// Exercise 1
/*Escribir en Scala el código equivalente a:
for (int i=10; i>=0; i--) println(i) */

for(x <- 10 to 0 by -1){
  println(x)

}

// exercise 2

/*Definir una función cuya firma es: countDown(n: Int): Unit. Esta función
imprimirá los números de n a 0
*/
def countDown(n: Int): Unit = {
  for(x <- n to 0 by -1){
    println(x)
  }
}

countDown(9)


// Exercise 3
/*Escribir el código que asigne a la variable 'a' una colección (da igual si se
define para, Seq, List, Array) de n enteros aleatorios entre 0 (incluído) y n
(excluido)
*/

val n = 2

val a: List[Int] = List.fill(n) (Random.nextInt())
println(a)

// Exercise 4

/*Dado una colección de enteros, se pide generar una nueva colección que
contenga todos los números positivos de la colección original en el orden de la
primera colección seguidos por los ceros y negativos, todos en su orden
original*/



val enteros: List[Int] = List(-1,2,-3,3,4,-5,6,-7,8,-9,2)
val pos: List[Int] = enteros.filter(_>0)
val neg: List[Int] = enteros.filter(_<0)
val nueva: List[Int] = pos.concat(neg)
println(nueva)


// Exercise 5
/*Definir una función que calcule la media de un Array[Double]*/

def media(numbers: List[Double]): Unit = {
  val entero: Double = numbers.sum / numbers.count(z => true)
  println(entero)
}
val doble: List[Double] = List(1.2,1.2,3.4,4.3,6.2,8.9)
media(doble)

// Exercise 6
/*Definir una función que reciba un argumento de tipo Array[Int] y devuelva un
Array[Int] sin duplicados.*/
def sinD(num: Array[Int]): Array[Int] ={
  num.distinct
}

val d: Array[Int] = Array(1,2,1,2,4,5,6,2,3,5,6,7)
val res2: Array[Int] = sinD(d)









for (i <- 10 to 0 by -1){
  println(i)
}

def countDown(n: Int): Unit = {
  for (i <- n to 0 by -1) {
    println(i)
  }
}
countDown(10)
val rand = new Random()
val n: Int = 3

val lista: List[Int] = List.fill(n) (rand.nextInt())

def function(numbers: List[Int]): Unit = {
  val positivos: List[Int] = numbers.filter(_ > 0)
  val negativos: List[Int] = numbers.filter(_ < 0)
  val res: List[Int] = positivos.concat(negativos)
  println(res)
}

val colecct: List[Int] = List(-1,2,-3,5,6,-7,8,-10,13)
function(colecct)

// Ejericio 5

def average(numbers: List[Double]): Unit = {
  val countElements = numbers.count(z => true)
  val calcular = numbers.sum / countElements
  println(calcular)
}

val coleccion: List[Double] = List(1.2,1.3,3.3,2.4,4.3,6.1,3.1,9.9,0.11)
average(coleccion)

// ejercicio 6
def funcionSet(numbers: List[Int]): Set[Int] ={
  numbers.toSet
}

val listado: List[Int] = List(1,1,2,2,3,3,4,5,6,6,6,6,1,2,3,4,5,6)
val res: Set[Int] = funcionSet(listado: List[Int])
println(res)

// Ejericicio 7
def funcion1(numbers: Seq[Int]): Seq[Int] = {
  numbers.filter(_ != 0)
}
val secuencia: Seq[Int] = Seq(1,2,3,4,0,1,2,4,0,0,0,0,0,1,2,3,0)
val re: Seq[Int] = funcion1(secuencia)
println(re)


// Ejercicio 8
def incrementarValores(mapa: Map[String, Int]): Map[String, Int] = {
  mapa.map { case (clave, valor) => clave -> (valor + 100) }
}

// Ejemplo de uso
val mapaEntrada = Map("a" -> 10, "b" -> 20, "c" -> 100, "d" -> 200)
val mapaSalida = incrementarValores(mapaEntrada)
println(mapaSalida)


// ejercicio 9
/*Definir una función que reciba una colección: minmax(values: Array[Int]) que
devuelva un par (tupla) con el menor y mayor valor del array+/
 */

def minmax(values: Array[Int]): (Int,Int) = {
  (values.min,values.max)
}

val cole: Array[Int] = Array(8,1,4,2,6,636,36,31,25,32)

val res1: (Int,Int) = minmax(cole: Array[Int])

// ejercicio 10

def funcion(word: String): Unit = {

}


funcion("Albacete")