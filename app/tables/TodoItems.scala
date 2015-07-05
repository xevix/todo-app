import slick.driver.H2Driver.api._
import slick.lifted.{ ProvenShape }

class TodoItems(tag: Tag)
    extends Table[(Int, String)](tag, "todo_items") {

  def id: Rep[Int] = column[Int]("id", O.PrimaryKey)
  def body: Rep[String] = column[String]("body")

  def * : ProvenShape[(Int, String)] =
    (id, body)
}
