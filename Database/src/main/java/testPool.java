import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class testPool {
    public static Logger logger = LoggerFactory.getLogger("testPool");
    public static void main(String[] args){
        logger.info("main begin");
        Datasource datasource = new Datasource(10);
        logger.info("ConnectionPool with 10 connection ready");
        ArrayList<Connection> stock = new ArrayList<>();
        String Table = "distance";
        String column = "risque";
        String sql = "select * from " + Table;
        for (int i = 0; i<20 ; i++){
            Connection c =datasource.getConnection();
            try{
            Statement smt = c.createStatement();
            ResultSet rs = smt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getArray(column)) ;
            }} catch (Exception e){

            }
            stock.add(c);
        }
        int j = stock.size();
        for (int k=0; k<j;k++){
            datasource.setConnection(stock.get(0));
            stock.remove(0);
        }
        datasource.closeConnections();
        logger.info("ConnectionPool closed");

    }

}
