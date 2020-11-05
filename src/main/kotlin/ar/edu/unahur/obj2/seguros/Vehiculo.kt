package ar.edu.unahur.obj2.seguros

import java.lang.Exception
import java.time.LocalDate


abstract class Vehiculo(val valor: Int, val anioFabricacion: Int, val seguros: MutableList<Seguro> , val tieneInfracciones : Boolean  ) {

  abstract fun puedeContratar(seguro: Seguro): Boolean

  fun costoDe(seguro: Seguro) =
    if ( puedeContratar(seguro) ) seguro.costoPara(this)
    else throw Exception ("No se puede contrarar el seguro ${seguro} para este tipo de autos")

  fun antiguedad() =
    LocalDate.now().year - anioFabricacion
  
  fun contratar(seguro: Seguro){
    if(puedeContratar(seguro)) seguros.add(seguro)
    else throw Exception ("No se puede contratar el seguro indicado")
  }
  abstract fun costoContraterceros(): Double
  open fun costoRoboHurto(): Double =
    valor * 0.05

  open fun cumpleRequisitosRH() =
    if (antiguedad() > antiguedadPermitidaRH()) false else true
  abstract fun antiguedadPermitidaRH() : Int
  open fun cumpleRequisitosTR() = false

  fun costoTotalSeguros()= seguros.sumByDouble{ this.costoDe(it) }
}

class Auto (valor: Int, anioFabricacion: Int, seguros: MutableList<Seguro>) :
        Vehiculo(valor, anioFabricacion , seguros , tieneInfracciones = false) {
  override fun puedeContratar(seguro: Seguro) =
    seguro.puedeSerContratadoPor(this)

  override fun costoContraterceros() =
    valor * 0.01
  override fun costoRoboHurto() =
    if (anioFabricacion >= 1995) valor * 0.03 else valor * 0.05

  override fun cumpleRequisitosRH() = true
  override fun antiguedadPermitidaRH() = 0
  override fun cumpleRequisitosTR() = true
}




class Camion(valor: Int, anioFabricacion: Int, seguros: MutableList<Seguro>) :
          Vehiculo(valor, anioFabricacion , seguros, tieneInfracciones = false) {

  override fun puedeContratar(seguro: Seguro) =
    seguro.puedeSerContratadoPor(this)

  override fun costoContraterceros() =
    if (antiguedad() > 10) valor * 0.02 else valor * 0.015

  override fun antiguedadPermitidaRH() = 10
}

class Taxi(valor: Int, anioFabricacion: Int , seguros: MutableList<Seguro> , tieneInfracciones: Boolean) :
        Vehiculo(valor, anioFabricacion, seguros ,  tieneInfracciones ) {

  override fun puedeContratar(seguro: Seguro) =
    seguro.puedeSerContratadoPor(this)

  override fun costoContraterceros() =
  valor * 0.02 + recargoContraterceros()

  fun recargoContraterceros() =
    if (tieneInfracciones) 1000 else 0

  override fun antiguedadPermitidaRH() = 12
}
