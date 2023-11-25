val fichero = io.Source.fromFile("C:\\Users\\flore\\OneDrive\\Escritorio\\UCM\\entregable_scala\\flights.csv")
for(line <- fichero.getLines){
  val cols = line.split(";").map(_.trim)
  println(cols)
}
fichero.getLines()