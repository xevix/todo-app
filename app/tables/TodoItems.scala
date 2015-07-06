package tables

import slick.driver.H2Driver.api._
import slick.lifted.{ ProvenShape }

case class TodoItem(body: String, id: Option[Int] = None)

class TodoItems(tag: Tag) extends Table[TodoItem](tag, "todo_items") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def body = column[String]("body")

  def * = (body, id.?) <> (TodoItem.tupled, TodoItem.unapply)

}
