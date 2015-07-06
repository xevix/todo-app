package controllers

import play.api._
import play.api.mvc._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfig
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.util.{ Success, Failure }
import slick.driver.JdbcProfile
import tables.TodoItemsTable

class Application extends Controller with TodoItemsTable with HasDatabaseConfig[JdbcProfile] {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import driver.api._
  val todoItems = TableQuery[TodoItems]

  def index = Action {
    val f = db.run(todoItems.result)
    f.onSuccess { case s => println(s"Result: $s") }
    Ok(views.html.index("Your new application is ready."))
  }

  def insert = Action {
    val f = db.run(todoItems += TodoItem("test item"))
    f onComplete {
      case Success(todoitems) => println("Insert succeeded!")
      case Failure(t) => println("An error has occured: " + t.getMessage)
    }

    Ok(views.html.index("query successful"))
  }
}
