package ar.edu.unahur.obj2.seguros

interface Seguro {
  fun costoPara(vehiculo: Vehiculo): Double
  fun valorSeguroAuto(auto: Auto) : Double
  fun valorSeguroCamion(camion: Camion): Double
  fun valorSeguroTaxi(taxi: Taxi): Double


}

class ContraTerceros : Seguro {

  override fun costoPara(vehiculo: Vehiculo)=vehiculo.precio(this)


  override fun valorSeguroAuto(auto: Auto): Double {
    return auto.valor * 0.1
  }

  override fun valorSeguroCamion(camion: Camion): Double {

    if (camion.esAntiguo()){
      return camion.valor * 0.2
    }else{
      return camion.valor * 0.15
    }
  }

  override fun valorSeguroTaxi(taxi: Taxi): Double {
    var valor = taxi.valor * 0.02
    if (taxi.infraccion) {
      valor += 1000
    }
    return valor
  }
}


class RoboHurto: Seguro {

  override fun costoPara(vehiculo: Vehiculo) = vehiculo.precio(this)

  override fun valorSeguroAuto(auto: Auto): Double {
    if (auto.antiguedad < 1995) {
      return auto.valor * 0.03
    } else {
      return auto.valor * 0.05
    }
  }

  override fun valorSeguroCamion(camion: Camion): Double {
    if (camion.esAntiguo()){
      return throw Error ( " No se puede asegurar , es Antiguo ")
    } else {
      return camion.valor*0.5
    }
  }

  override fun valorSeguroTaxi(taxi: Taxi): Double {
    if (taxi.esAntiguo()){
      return throw Error ( " No se puede asegurar , es Antiguo ")
    } else {
      return taxi.valor*0.05
    }
  }
}

class TodoRiesgo: Seguro {
  override fun costoPara(vehiculo: Vehiculo): Double {
    return vehiculo.precio(this);
  }

  override fun valorSeguroAuto(auto: Auto): Double {
    return auto.valor*0.07
  }

  override fun valorSeguroCamion(camion: Camion): Double {
    return throw Error ("no se aseguran camiones")
  }

  override fun valorSeguroTaxi(taxi: Taxi): Double {
    return throw Error("no se aseguran taxis")
  }



}
