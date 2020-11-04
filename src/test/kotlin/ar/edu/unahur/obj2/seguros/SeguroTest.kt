package ar.edu.unahur.obj2.seguros

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class SeguroTest : DescribeSpec({
  describe("Seguros") {
    val auto = Auto(100000, 1990, mutableListOf<Seguro>())
    val autoNuevo = Auto(200000, 2000, mutableListOf<Seguro>())

    val taxi = Taxi(200000, 1999, mutableListOf(),false)
    val taxiMalo = Taxi(200000, 2010, mutableListOf(),true)

    val camion = Camion (500000,2005, mutableListOf())
    val camionNuevo = Camion (1000000,2015, mutableListOf())

    describe("Pueden Contratar"){
      describe("Contra Terceros"){
        it("Auto"){
          auto.puedeContratar(ContraTerceros).shouldBeTrue()
        }
        it("Taxi"){
          taxi.puedeContratar(ContraTerceros).shouldBeTrue()
          taxiMalo.puedeContratar(ContraTerceros).shouldBeTrue()
        }
        it("Camion"){
          camion.puedeContratar(ContraTerceros).shouldBeTrue()
        }
      }
      describe("Robo y Hurto"){
        it ("Auto ") {
          auto.puedeContratar(RoboHurto).shouldBeTrue()
        }
        it ("Taxi viejo no puede ") {
          taxi.puedeContratar(RoboHurto).shouldBeFalse()
        }
        it ("Taxi nuevo si puede ") {
          taxiMalo.puedeContratar(RoboHurto).shouldBeTrue()
        }
        it ("camion viejo no puede ") {
          camion.puedeContratar(RoboHurto).shouldBeFalse()
        }
        it ("Camion nuevo si puede ") {
          camionNuevo.puedeContratar(RoboHurto).shouldBeTrue()
        }
      }
      describe("Todo Riesgo"){
        it("Auto , si"){
          auto.puedeContratar(TodoRiesgo).shouldBeTrue()
        }
        it("Taxi, no"){
          taxi.puedeContratar(TodoRiesgo).shouldBeFalse()
          taxiMalo.puedeContratar(TodoRiesgo).shouldBeFalse()
        }
        it("Camion, no"){
          camion.puedeContratar(TodoRiesgo).shouldBeFalse()
          camionNuevo.puedeContratar(TodoRiesgo).shouldBeFalse()
        }
      }
    }
    describe("Cuanto costaría..."){
      describe("seguro contra terceros"){
        it("para un Auto"){

        }
        it("para un taxi con infracciones"){

        }
        it("para un taxi sin infracciones"){

        }
        it("para un camion de más de 10 años"){

        }
        it("para un camion menos a 10 años"){

        }
      }
      describe("seguro Robo y Hurto"){
        it("para un Auto antes del 95"){

        }
        it("para un Auto desp dep 95"){

        }
        it("para un taxi"){

        }
        it("para un camion"){

        }

      }
      describe("seguro contra todo riesgo"){
        it("para un Auto"){

        }
        it("para un taxi"){

        }
        it("para un camion"){

        }
      }
    }
  }
})
