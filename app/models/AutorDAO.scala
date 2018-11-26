package models;
import scala.collection.mutable.MutableList;
import play.api.db.Database;

object ClienteDAO{
    
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
}