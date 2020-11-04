package ar.edu.unahur.obj2.seguros



abstract class Vehiculo(val valor: Int, val antiguedad: Int , val seguros: MutableList<Seguro> , val infraccion : Boolean ) {
  abstract fun puedeContratar(seguro: Seguro): Boolean
  abstract fun precio (seguro: Seguro) : Double;

  fun contratar(seguro: Seguro) {
    seguros.add(seguro)
  }
}

class Auto(valor: Int, antiguedad: Int, seguros: MutableList<Seguro>) : Vehiculo(valor, antiguedad , seguros , infraccion = false) {
  override fun puedeContratar(seguro: Seguro): Boolean {
    TODO("Not yet implemented")
  }

  override fun precio(seguro: Seguro) = seguro.valorSeguroAuto(this)


}




class Camion(valor: Int, antiguedad: Int, seguros: MutableList<Seguro>) : Vehiculo(valor, antiguedad , seguros, infraccion = false) {

   override fun precio(seguro: Seguro) = seguro.valorSeguroCamion(this)


  override fun puedeContratar(seguro: Seguro): Boolean {
    TODO("Not yet implemented")
  }

}

class Taxi(valor: Int, antiguedad: Int , seguros: MutableList<Seguro> , infraccion: Boolean) : Vehiculo(valor, antiguedad, seguros ,  infraccion ) {
  override fun puedeContratar(seguro: Seguro): Boolean {
    TODO("Not yet implemented")
  }

  override fun precio(seguro: Seguro) = seguro.valorSeguroTaxi(this)


}
