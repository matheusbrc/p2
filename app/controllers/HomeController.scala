package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.db.Database
import scala.collection.mutable.MutableList
import models.tbCliente
import models.tbAutor
import models.tbLivro
import models.tbMovim

@Singleton
class HomeController @Inject()(db: Database, cc: ControllerComponents)
extends AbstractController(cc) with play.api.i18n.I18nSupport {
  
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
               ,res.getString(3)
               ,res.getString(4)
               ,res.getInt(5)))
      }
    }
    Ok(views.html.sL(list_sL))
  }
  
  def sM() = Action {
    val list_sM = MutableList[tbMovim]()
    db.withConnection { conn =>
      val stm = conn.createStatement()
      val res = stm.executeQuery("""select * from tb_movimento""")
      while (res.next()) {
        list_sM.+=(tbMovim(res.getInt(1)
               ,res.getInt(2)))
      }
    }
    Ok(views.html.sM(list_sM))
  }
  
  def emp(cdLiv: Int, cdCli: Int) = Action {
    db.withConnection { conn =>
      val ps = conn.prepareStatement("select cd_livro from tb_livro where cd_livro = ?")
      ps.setInt(1,cdLiv)
      val res = ps.executeQuery()
      val ps2 = conn.prepareStatement("select cd_cliente from tb_cliente where cd_cliente = ?")
      ps2.setInt(1,cdCli)
      val res2 = ps2.executeQuery()
      if(res.next() || res2.next()){
        val ps3 = conn.prepareStatement("select count(cd_livro) from tb_movimento where cd_livro = ? and cd_cliente = ?")
        ps3.setInt(1,cdLiv)
        ps3.setInt(2,cdCli)
        val res3 = ps3.executeQuery()
        res3.next()
        val ps4 = conn.prepareStatement("select nr_quantidade from tb_livro where cd_livro = ?")
        ps4.setInt(1,cdLiv)
        val res4 = ps4.executeQuery()
        res4.next()
        if(res4.getInt(1) - res3.getInt(1) > 0){
          val ps5 = conn.prepareStatement("insert into tb_movimento(cd_livro,cd_cliente) values (?,?)")
          ps5.setInt(1,cdLiv)
          ps5.setInt(2,cdCli)
          ps5.execute()
          val ps6 = conn.prepareStatement("update tb_livro set nr_quantidade = nr_quantidade-1 where cd_livro = ?")
          ps6.setInt(1,cdLiv)
          ps6.execute()
        }
      }
      Redirect("/movimentos")
    }
  }
  
  def dev(cdLiv: Int, cdCli: Int) = Action {
    db.withConnection{ conn =>
      val ps = conn.prepareStatement("select * from tb_movimento where cd_livro = ? and cd_cliente = ?")
      ps.setInt(1,cdLiv)
      ps.setInt(2,cdCli)
      val res = ps.executeQuery()
      if(res.next()){
        val ps = conn.prepareStatement("delete from tb_movimento where cd_livro=? and cd_cliente = ?")
        ps.setInt(1,cdLiv)
        ps.setInt(2,cdCli)
        ps.execute()
        val ps2 = conn.prepareStatement("update tb_livro set nr_quantidade = nr_quantidade+1 where cd_livro = ?")
        ps2.setInt(1,cdLiv)
        ps2.execute()
      }
      Redirect("/movimentos")
    }
  }
  
  def LxA(cdAut: Int) = Action {
    val list_sL = MutableList[tbLivro]()
    db.withConnection { conn =>
      val ps = conn.prepareStatement("select * from tb_livro where cd_autor = ?")
      ps.setInt(1,cdAut)
      val res = ps.executeQuery()
      while (res.next()) {
        list_sL.+=(tbLivro(res.getInt(1)
               ,res.getInt(2)
               ,res.getString(3)
               ,res.getString(4)
               ,res.getInt(5)))
      }
    }
    Ok(views.html.sL(list_sL))
  }
}