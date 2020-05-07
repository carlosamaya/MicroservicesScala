package co.s4n.practice.core.domain.queries
import co.s4n.practice.core.domain.entities.dataReceiver
import co.s4n.practice.core.domain.entities.dataCommand
// ConcreteCommand
abstract class GetElementQuery extends dataCommand{
  val wire = new dataReceiver
  // Find existing element
  def GetElementById(id_element: Int): Unit ={
    return wire.GetElementById(id_element)
  }
  //Returns All Elements
  def GetAllElements(): Unit ={
    return wire.GetAllElements()
  }
}
