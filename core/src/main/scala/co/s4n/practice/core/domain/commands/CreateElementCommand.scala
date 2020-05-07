package co.s4n.practice.core.domain.commands
import co.s4n.practice.core.domain.entities.dataCommand
import co.s4n.practice.core.domain.entities.dataReceiver

// ConcreteCommand
abstract class CreateElementCommand extends dataCommand {
  val wire = new dataReceiver
  //Create new Element in List
  def CreateElement(newElement: Map[Int, String]): Unit ={
    wire.AddElement(newElement)
  }
}