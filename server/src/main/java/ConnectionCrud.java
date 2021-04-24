import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class ConnectionCrud {
    private Connection c;
    private final static Logger logger = LoggerFactory.getLogger(ConnectionCrud.class.getName());
    public ConnectionCrud() {}

    public void setC(Connection c) {
        this.c = c;
    }

    public Connection getC() {
        return c;
    }

    public void addElement(String Table, String column, Object value) throws SQLException {
        String sql = "INSERT INTO \"" + Table + "\"(" + column + ") " + "VALUES ('" + value + "');";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }
    public void addElement(String Table, String column, String column1, Object value, Object value1) throws SQLException {
        String sql = "INSERT INTO \"" + Table + "\"(" + column + "," + column1+ ") " + "VALUES ('" + value + "','" + value1 + "');";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }
    public void eraseElement( String Table, String idcolumn, String id) throws SQLException {
        String sql = "DELETE FROM \"" + Table + "\" WHERE " + idcolumn + " = " + id + ";";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }

    public  void deleteElement( String Table, String idcolumn) throws SQLException {
        String sql = "delete from \"" + Table + "\" where " + idcolumn + " = (select max(" + idcolumn + ") from \"" + Table + "\");";
        Statement smt = c.createStatement();
        smt.executeUpdate(sql);
    }
    public String showElement(String Table, String idcolumn, String columna) throws SQLException {
        String sql = "select * from \"" + Table + "\";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = null;
        while (rs.next()) {
            result += rs.getArray(idcolumn) + "   " + rs.getArray(columna);
        }
        return result;
    }
    public  String showElement(String Table) throws SQLException {
        String sql = "select * from \"" + Table + "\";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = null;
        while (rs.next()) {
            result += rs.getArray(1) + "        |               " + rs.getArray(2) + "            |       "  + rs.getArray(3) + "\n";
        }
        return result;
    }
    public String getCompanyName() throws SQLException{
        String sql = "Select name from Company;";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result += rs.getArray("name")+ "\n";}
        return result;
    }

    public String getMenu(String company) throws SQLException{
        logger.info("in geMenu");
        String sql = "Select room.name, floor.floor_s_number,building.address from floor inner join room on floor.id_floor=room.id_floor inner join building on floor.address=building.address where room.room_s_number in ( Select room_s_number from location inner join company on company.id_company=location.id_company where company.name= '"+company+"');";
        logger.info(sql);
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            logger.info("in the while");
            result += rs.getArray("name") + "\n";
            result += rs.getArray("floor_s_number")+ "\n";
            result += rs.getArray("address") + "\n";
        }
        return result;
    }

    public String getWinStore() throws SQLException{
        String sql = ""; // To change
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result += rs.getArray("name");}
        return result;
    }

    public String getEquipement(String room) throws SQLException{
        logger.info("in getEquipement");
        String sql = "Select equipement.id_equipement,equipement.type,equipplace.position_x,equipplace.position_y from be_present inner join equipement on be_present.id_equipement=equipement.id_equipement inner join equipplace on be_present.id_equipplace=equipplace.id_equipplace where equipplace.id_room in (Select room_s_number from room where name='"+room+"');";
        logger.info(sql);
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            logger.info("in the while");
            result += rs.getArray("id_equipement")+ "\n";
            result += rs.getArray("type") + "\n";
            result += rs.getArray("position_x")+ "\n";
            result += rs.getArray("position_y") + "\n";
        }
        return result;
    }
    public String getOpacityValue(String id) throws SQLException {
        logger.info("in getOpacityValue");
        String sql ="select valueof from equipement where id_equipement ="+ id +";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while(rs.next()){
            logger.info("in the while");
            result += rs.getArray("valueof");
        }
        return result;

    }
    public String getEquipementAvailable(String roomName) throws SQLException{
        logger.info("in getEquipementAvailable");
        String sql = "select distinct equipement.type from compatible inner join equipplace on equipplace.id_equipplace=compatible.id_equipplace inner join equipement on equipement.type= compatible.type_equip where equipplace.id_equipplace not in (Select id_equipplace from be_present) and equipement.id_equipement not in (Select id_equipement from be_present) and equipplace.id_room in (Select room_s_number from room where name='"+roomName+"');";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while(rs.next()){
            logger.info("in the while");
            result += rs.getArray("type")+ "\n";
        }
        return result;
    }
    public String getEtat(String id) throws SQLException{
        logger.info("in getEtat");
        String sql = "select animated from equipement where id_equipement="+id+";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while(rs.next()){
            logger.info("in the while");
            result += rs.getArray("animated");
        }
        return result;
    }
    public void deleteBePresent(String id) throws SQLException{
        logger.info("in delete");
        String sql = "delete from be_present where id_equipement="+id+";";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }
    public String getPlace(String type,String room) throws SQLException{
        logger.info("in getPlace");
        String sql = "select equipplace.id_equipplace,equipplace.position_x,equipplace.position_y from compatible inner join equipplace on equipplace.id_equipplace=compatible.id_equipplace where compatible.type_equip = '"+type+"' and  equipplace.id_room in (Select room_s_number from room where name='"+room+"') and equipplace.id_equipplace not in (Select id_equipplace from be_present ); ";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while(rs.next()){
            logger.info("in the while");
            result += rs.getArray("id_equipplace")+ "\n";
            result += rs.getArray("position_x") + "\n";
            result += rs.getArray("position_y")+ "\n";
        }
        return result;
    }
    public void insertBePresent(String idPlace, String type) throws SQLException{
        logger.info("in delete");
        String sql = "insert into be_present values ((Select id_equipement from equipement where type='"+type+"' and id_equipement not in (Select id_equipement from be_present) limit 1),"+idPlace+");";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }
    public String getSensor(String room) throws SQLException{
        logger.info("in getSensor");
        String sql = "Select sensor.id_sensor,sensor.description,equipplace.position_x,equipplace.position_y from be_present inner join sensor on be_present.id_sensor=sensor.id_sensor inner join equipplace on be_present.id_equipplace=equipplace.id_equipplace where equipplace.id_room in (Select room_s_number from room where name='"+room+"');";
        logger.info(sql);
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            logger.info("in the while");
            result += rs.getArray("id_sensor")+ "\n";
            result += rs.getArray("description") + "\n";
            result += rs.getArray("position_x")+ "\n";
            result += rs.getArray("position_y") + "\n";
        }
        return result;
    }
    public void updateOpacity(String id, String valueopacity) throws SQLException {
        logger.info("in update");
        String sql = "update equipement set valueof = " + valueopacity + " where id_equipement =" + id + ";";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }
}
