package models;
import scala.collection.mutable.MutableList;
import play.api.db.Database;

object ClienteDAO{
    
    def getCliente(db: Database, cdCliente: Int): tbCliente = {
        db.withConnection{conn =>
            val ps = conn.prepareStatement("select * from tb_cliente where cd_cliente=?")
            ps.setInt(1,cdCliente)
            val res = ps.executeQuery()
            if(res.next())
                tbCliente(res.getInt(1),res.getString(2),res.getString(3))
            else
                tbCliente(0,"","")
        }
    }
    
    def listCliente(db: Database): MutableList[tbCliente] = {
    
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
        list_sC
    }
}