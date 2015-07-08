package tables

import slick.driver.JdbcProfile

case class TodoItem(body: String, complete: Boolean = false, id: Option[Int] = None)

trait TodoItemsTable {
  protected val driver: JdbcProfile
  import driver.api._

  class TodoItems(tag: Tag) extends Table[TodoItem](tag, "todo_items") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def body = column[String]("body")
    def complete = column[Boolean]("complete")

    def * = (body, complete, id.?) <> (TodoItem.tupled, TodoItem.unapply)

  }

}
