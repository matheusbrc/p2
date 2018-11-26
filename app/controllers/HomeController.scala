package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db.Database
import scala.collection.mutable.MutableList
import models.tbCliente
import models.tbAutor
import models.tbLivro

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(db: Database, cc: ControllerComponents)
extends AbstractController(cc) with play.api.i18n.I18nSupport {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
  
  def sC() = Action {
    val list_sC = MutableList[tbCliente]()
    db.withConnection { conn =>
      val stm = conn.createStatement()
      val res = stm.executeQuery("""select * from tb_cliente""")
      while (res.next()) {
        list_sC.+=(tbCliente(res.getInt(1)
               ,res.getString(2)
               ,res.getString(3)))
      }
    }
    Ok(views.html.sC(list_sC))
  }
  
  def sA() = Action {
    val list_sA = MutableList[tbAutor]()
    db.withConnection { conn =>
      val stm = conn.createStatement()
      val res = stm.executeQuery("""select * from tb_autor""")
      while (res.next()) {
        list_sA.+=(tbAutor(res.getInt(1)
               ,res.getString(2)))
      }
    }
    Ok(views.html.sA(list_sA))
  }
  
  def sL() = Action {
    val list_sL = MutableList[tbLivro]()
    db.withConnection { conn =>
      val stm = conn.createStatement()
      val res = stm.executeQuery("""select * from tb_livro""")
      while (res.next()) {
        list_sL.+=(tbLivro(res.getInt(1)
               ,res.getInt(2)
               ,res.getInt(3)
               ,res.getString(4)
               ,res.getString(5)
               ,res.getInt(6)))
      }
    }
    Ok(views.html.sL(list_sL))
  }
}
