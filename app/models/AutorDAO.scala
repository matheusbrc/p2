package models;
import scala.collection.mutable.MutableList;
import play.api.db.Database;

object AutorDAO{
    
    def getAutor(db: Database, cdAutor Int): tbAutor = {
        db.withConnection{conn =>
            val ps = conn.prepareStatement("select * from tb_autor where cd_autor=?")
            ps.setInt(1,cdAutor)
            val res = ps.executeQuery()
            if(res.next())
                tbAutor(res.getInt(1),res.getString(2))
            else
                tbAutor(0,"")
        }
    }
    
    def listAutor(db: Database): MutableList[tbAutor] = {
    
        val list_sA = MutableList[tbAutor]()
    
        db.withConnection { conn =>
          val stm = conn.createStatement()
          val res = stm.executeQuery("""select * from autor""")
          while (res.next()) {
            list_sA.+=(tbAutor(res.getInt(1)
                   ,res.getString(2)))
          }
        }
        list_sA
    }
}