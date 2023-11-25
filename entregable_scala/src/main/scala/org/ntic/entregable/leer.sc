import scala.io.Source.fromFile

val hola = fromFile("C:\\Users\\flore\\OneDrive\\Escritorio\\UCM\\entregable_scala\\flights.csv")

for (line <- hola.getLines){
  val cols = line.split(";").map((_.trim))
  println(cols)
}