package org.ntic.entregable
import io.Source.fromFile
object FileUtils {

  def isInvalid(s: String): Boolean = {
    /**
     * This function is used to check if the line is valid or not
     * @param s: String
     * @return Boolean: true if the line is invalid, false otherwise
     */
    
    // Como el parametro "s" recoge cada linea del csv lo que hago
    // es separar cada header por su delimitador
    // si el header esta vacio o el numero de headers es distinto de 13 entonces es invalido

    val fields = s.split(FlightsLoaderConfig.delimiter)
    if(s.isEmpty || fields.length != FlightsLoaderConfig.headersLength){
      true
    }else{
      false
    }
  }

  def loadFile(filePath: String): Seq[Flight] = {
    /**
     * This function is used to load the file
     * @param filePath: String
     * @return Seq[org.ntic.entregable.Flight]
     */
      // Lee el fichero y devuelve un lista de lineas
    val linesList: List[String] =  fromFile(filePath).getLines.toList
    // obtengo los headers
    val headers = linesList.head

    // cuenta el numero de headers que hay en el csv, como en el config, tengo creada una variable "headersLength"
    //que esta igualada a 13 que es el numero de headers pues puedo hacer una comprobacion
    val countHeader  = headers.split(FlightsLoaderConfig.delimiter).length
    require(countHeader == FlightsLoaderConfig.headersLength, "compruebo el numero de headers sea = 13")
    // con la funcion tail obtengo las demas filas excepto la primera fila que son los headers
    val rows = linesList.tail

    // filtro las filas que son invalidas
    val invalidRows: List[String] = rows.filter(isInvalid)
    // filtro las filas validas
    val validRows: List[String] = rows.filterNot(isInvalid)
    // hago un mapeo para convertir las filas validas en objeto de tipo Flight y devuelve una lista
    val flights: Seq[Flight] = validRows.map(Flight.fromString)
    // retorna las filas validas en una lista
    flights
  }

}

