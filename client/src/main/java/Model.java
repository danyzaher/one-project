import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(Model.class.getName());

    public String showElement(Connection c , String nomElement ) throws SQLException {

        String sql = "select * from produit where nom="+nomElement+";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = null;
        while (rs.next()) {

            result = result + "/n" + rs.getArray(nomElement);
        }
        return result;
    }
   // System.out.println("Show is done");
}

