import edu.unq.uis.planificador.{Restriccion, RecurrentInterval, Empleado}
import org.joda.time.{DateTimeConstants}
import org.scalatest._
import org.scalatest.matchers.ShouldMatchers
import com.github.nscala_time.time.Imports._

class EmpleadoSpec extends FlatSpec with ShouldMatchers {
  "Test1" should "lala" in {
    var jorge = new Empleado
    jorge.estaDisponiblePara(new DateTime("2004-12-13")) should be (false)
  }

  it should "acepta un dia de disponibilidad" in {
    var jorge = new Empleado
    jorge.agregarDisponibilidad(new RecurrentInterval(10, 18, DateTimeConstants.MONDAY))
    jorge.estaDisponiblePara(new DateTime("2014-03-24T09:00")) should be (false)
    jorge.estaDisponiblePara(new DateTime("2014-03-24T15:00")) should be (true)
  }

  it should "acepta varias disponiblilidades" in {
    var jorge = new Empleado
    jorge.agregarDisponibilidad(new RecurrentInterval(10, 18, DateTimeConstants.THURSDAY))
    jorge.agregarDisponibilidad(new RecurrentInterval(10, 18, DateTimeConstants.MONDAY))

    jorge.estaDisponiblePara(new DateTime("2014-03-24T11:00")) should be (true)
    jorge.estaDisponiblePara(new DateTime("2014-03-27T15:00")) should be (true)
  }

  it should "no estar disponible si hay una restriccion especifica para un dia normalmente habilitado" in {
    var jorge = new Empleado
    jorge.agregarDisponibilidad(new RecurrentInterval(10, 18, DateTimeConstants.MONDAY))
    jorge.agregarRestriccion( new Restriccion(new DateTime("2014-03-24T11:00")))

    jorge.estaDisponiblePara(new DateTime("2014-03-24T11:00")) should be (false)
  }
}
