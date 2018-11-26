package models;
import scala.collection.mutable.MutableList;
import play.api.db.Database;

object ClienteDAO{
    
    def getCliente(db: Database, cdCliente: Int): Cliente = {
        db.withConnection{conn =>
            val ps = conn.prepareStatement("select * from tb_cliente where cd_cliente=?")
            ps.setInt(1,cdCliente)
            val res = ps.executeQuery()
            if(res.next())
                Cliente(res.getString(2),res.getString(3))
            else
                Cliente("","")
        }
    }
}