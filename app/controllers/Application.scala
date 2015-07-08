package controllers

import play.api._
import play.api.mvc._
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfig
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.util.{ Success, Failure }
import slick.driver.JdbcProfile
import tables.{ TodoItemsTable, TodoItem }

class Application extends Controller with TodoItemsTable with HasDatabaseConfig[JdbcProfile] {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  import driver.api._

  val todoItems = TableQuery[TodoItems]

  def index = Action.async {
    db.run(todoItems.result).map { todos =>
      println(s"Result: $todos")
      Ok(views.html.index("Index has loaded", todos))
    }
  }

  def insert = Action {
    val f = db.run(DBIO.seq(
      todoItems += TodoItem("test item"),
      todoItems += TodoItem("checked", complete = true)
    ))
    f onComplete {
      case Success(todoitems) => println("Insert succeeded!")
      case Failure(t) => println("An error has occured: " + t.getMessage)
    }

    Redirect("/")
  }
}
