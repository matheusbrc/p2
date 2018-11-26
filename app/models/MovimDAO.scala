package models;
import scala.collection.mutable.MutableList;
import play.api.db.Database;

object MovimDAO{
    
    def getMovim(db: Database, cdLivro: Int, cdCliente: Int): tbMovim = {
        db.withConnection{conn =>
            val ps = conn.prepareStatement("select * from tb_livro where cd_livro=? and cd_cliente=?")
            ps.setInt(1,cdLivro)
            ps.setInt(2,cdCliente)
            val res = ps.executeQuery()
            if(res.next())
                tbMovim(res.getInt(1),res.getInt(2))
            else
                tbMovim(0,0)
        }
    }
}