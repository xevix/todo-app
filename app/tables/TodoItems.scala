package tables

import slick.driver.JdbcProfile

trait TodoItemsTable {
  protected val driver: JdbcProfile
  import driver.api._

  case class TodoItem(body: String, id: Option[Int] = None)

  class TodoItems(tag: Tag) extends Table[TodoItem](tag, "todo_items") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def body = column[String]("body")

    def * = (body, id.?) <> (TodoItem.tupled, TodoItem.unapply)

  }

}
