package ar.edu.unahur.obj2.seguros

interface Seguro {
  fun costoPara(vehiculo: Vehiculo): Double
  fun puedeSerContratadoPor(vehiculo: Vehiculo) : Boolean
}

object ContraTerceros : Seguro {
  override fun costoPara(vehiculo: Vehiculo) =
    vehiculo.costoContraterceros()
  override fun puedeSerContratadoPor(vehiculo: Vehiculo) = true
}

object RoboHurto: Seguro {
  override fun costoPara(vehiculo: Vehiculo) =
    vehiculo.costoRoboHurto()

  override fun puedeSerContratadoPor(vehiculo: Vehiculo) =
    vehiculo.cumpleRequisitosRH()
}

object TodoRiesgo: Seguro {
  override fun costoPara(vehiculo: Vehiculo) : Double =
    vehiculo.valor * 0.07
  override fun puedeSerContratadoPor(vehiculo: Vehiculo) =
    vehiculo.cumpleRequisitosTR()

}
