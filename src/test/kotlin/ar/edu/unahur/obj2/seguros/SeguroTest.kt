package ar.edu.unahur.obj2.seguros

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class SeguroTest : DescribeSpec({
  describe("Seguros") {
    val auto = Auto(100000, 1990, mutableListOf<Seguro>())
    val autoNuevo = Auto(200000, 2000, mutableListOf<Seguro>())

    val taxi = Taxi(200000, 1999, mutableListOf(), false)
    val taxiNuevoInfractor = Taxi(300000, 2010, mutableListOf(), true)

    val camion = Camion(500000, 2005, mutableListOf())
    val camionNuevo = Camion(1000000, 2015, mutableListOf())

    describe("Pueden Contratar") {
      describe("Contra Terceros") {
        it("Auto") {
          auto.puedeContratar(ContraTerceros).shouldBeTrue()
        }
        it("Taxi") {
          taxi.puedeContratar(ContraTerceros).shouldBeTrue()
          taxiNuevoInfractor.puedeContratar(ContraTerceros).shouldBeTrue()
        }
        it("Camion") {
          camion.puedeContratar(ContraTerceros).shouldBeTrue()
        }
      }
      describe("Robo y Hurto") {
        it("Auto si puede") {
          auto.puedeContratar(RoboHurto).shouldBeTrue()
        }
        it("Taxi viejo no puede ") {
          taxi.puedeContratar(RoboHurto).shouldBeFalse()
        }
        it("Taxi nuevo si puede ") {
          taxiNuevoInfractor.puedeContratar(RoboHurto).shouldBeTrue()
        }
        it("camion viejo no puede ") {
          camion.puedeContratar(RoboHurto).shouldBeFalse()
        }
        it("Camion nuevo si puede ") {
          camionNuevo.puedeContratar(RoboHurto).shouldBeTrue()
        }
      }
      describe("Todo Riesgo") {
        it("Auto , si") {
          auto.puedeContratar(TodoRiesgo).shouldBeTrue()
        }
        it("Taxi, no") {
          taxi.puedeContratar(TodoRiesgo).shouldBeFalse()
          taxiNuevoInfractor.puedeContratar(TodoRiesgo).shouldBeFalse()
        }
        it("Camion, no") {
          camion.puedeContratar(TodoRiesgo).shouldBeFalse()
          camionNuevo.puedeContratar(TodoRiesgo).shouldBeFalse()
        }
      }
    }
    describe("Cuanto costaría...") {
      describe("seguro contra terceros") {
        it("para un Auto") {
          auto.costoDe(ContraTerceros).shouldBe(1000.0)
          autoNuevo.costoDe(ContraTerceros).shouldBe(2000.0)

        }
        it("para un taxi con infracciones valor 300.00") {
          taxiNuevoInfractor.costoDe(ContraTerceros).shouldBe(7000.0)

        }
        it("para un taxi sin infracciones valor 200.000") {
          taxi.costoDe(ContraTerceros).shouldBe(4000.0)
        }
        it("para un camion de más de 10 años valor 500.000") {
          camion.costoDe(ContraTerceros).shouldBe(10000.0)

        }
        it("para un camion menos a 10 años valor 1.000.000") {
          camionNuevo.costoDe(ContraTerceros).shouldBe(15000.0)

        }
      }
      describe("seguro Robo y Hurto") {
        it("para un Auto antes del 95") {
          auto.costoDe(RoboHurto).shouldBe(5000.0)
        }
        it("para un Auto desp dep 95") {
          autoNuevo.costoDe(RoboHurto).shouldBe(6000.0)
        }
        it("para un taxiNuevo") {
          taxiNuevoInfractor.costoDe(RoboHurto).shouldBe(15000.0)
        }
        it(" No se puede contratar para un taxi antiguo") {
          shouldThrowAny { taxi.costoDe(RoboHurto) }
        }

        it("para un camionNuevo") {
          camionNuevo.costoDe(RoboHurto).shouldBe(50000.0)

        }
        it(" no se puede contratar para un camion Antiguo") {
          shouldThrowAny { camion.costoDe(RoboHurto) }
        }
      }
      describe("seguro contra todo riesgo") {
        it("para un Auto") {
          auto.costoDe(TodoRiesgo).shouldBe(7000.0 plusOrMinus 0.1)
        }
        it("No se puede contratar para ningun taxi") {
          shouldThrowAny { taxi.costoDe(TodoRiesgo) }
          shouldThrowAny { taxiNuevoInfractor.costoDe(TodoRiesgo) }

        }
        it(" No se puede contratar para ningun camion") {
          shouldThrowAny { camion.costoDe(TodoRiesgo) }
          shouldThrowAny { camionNuevo.costoDe(TodoRiesgo) }

        }
      }
    }
    describe("Contratar Seguro"){
      describe("Auto") {
        it("El auto puede contratar todos los seguros ") {
          auto.contratar(ContraTerceros)
          auto.contratar(TodoRiesgo)
          auto.contratar(RoboHurto)
        }
      }
      describe("Taxi") {
        it("El Taxi Viejo solo puede contratar contra terceros") {
          taxi.contratar(ContraTerceros)
          shouldThrowAny { taxi.contratar(TodoRiesgo) }
          shouldThrowAny { taxi.contratar(RoboHurto) }
        }
        it("El Taxi Nuevo solo puede contratar contra terceros y roboHurto") {
          taxiNuevoInfractor.contratar(ContraTerceros)
          taxiNuevoInfractor.contratar(RoboHurto)
          shouldThrowAny { taxi.contratar(TodoRiesgo) }
        }
      }
      describe("camion") {
        it("El camion Viejo solo puede contratar contra terceros") {
          camion.contratar(ContraTerceros)
          shouldThrowAny { camion.contratar(TodoRiesgo) }
          shouldThrowAny { camion.contratar(RoboHurto) }
        }
        it("El camion Nuevo solo puede contratar contra terceros y roboHurto") {
          camionNuevo.contratar(ContraTerceros)
          camionNuevo.contratar(RoboHurto)
          shouldThrowAny {camionNuevo.contratar(TodoRiesgo) }
        }
      }
    }
    describe("costoTotalSeguros"){
      describe("Costo Total para auto "){
        auto.contratar(ContraTerceros)
        auto.contratar(RoboHurto)
        it ("con 2 seguros") {
          auto.costoTotalSeguros().shouldBe(6000.0)
        }
        it("con 3 seguros"){
          auto.contratar(TodoRiesgo)
          auto.costoTotalSeguros().shouldBe(13000.0)
        }
      }
      describe("costo para taxi") {
        it("Con 2 seguros") {
          taxiNuevoInfractor.contratar(ContraTerceros)
          taxiNuevoInfractor.contratar(RoboHurto)
          taxiNuevoInfractor.costoTotalSeguros().shouldBe(22000.0)
        }
      }
      describe("costo para camion Nuevo"){
        it("Con 2 seguros") {
          camionNuevo.contratar(RoboHurto)
          camionNuevo.contratar(ContraTerceros)
          camionNuevo.costoTotalSeguros().shouldBe(65000.0)
          }
      }
    }
  }
})
