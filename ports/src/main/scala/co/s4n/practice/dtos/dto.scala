package co.s4n.practice.dtos
// DTOs
object dto{
  @SuppressWarnings(Array("org.wartremover.warts.All"))
  val dataArray: Map[DataChunk] = Map(0 -> "")
  case class DataChunk(id_element: Int, name_element: String) //Data Chunk Structure Definition
  case class AddElement(name_element: String) // Data Element Create Definition
  case class GetElementById(id_element: Int) // Get Data Element by Id Definition
  case class GetAllElements() // Get All Data Elements
}
