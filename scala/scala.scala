import scala.util.Random

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
