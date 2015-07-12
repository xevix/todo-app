package tables

import org.joda.time.DateTime
import slick.driver.JdbcProfile

case class TodoItem(body: String, dueAt: DateTime = DateTime.now(), complete: Boolean = false, id: Option[Int] = None)

trait TodoItemsTable {
  protected val driver: JdbcProfile
  import driver.api._
  import com.github.tototoshi.slick.H2JodaSupport._

  class TodoItems(tag: Tag) extends Table[TodoItem](tag, "todo_items") {

    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def dueAt = column[DateTime]("due_at")
    def body = column[String]("body")
    def complete = column[Boolean]("complete")

    def * = (body, dueAt, complete, id.?) <> (TodoItem.tupled, TodoItem.unapply)

  }

}
