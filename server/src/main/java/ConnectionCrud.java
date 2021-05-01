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
    public String getCompanyName() throws SQLException{
        String sql = "Select name from Company;";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result += rs.getArray("name")+ "\n";}
        return result;
    }
    public String getCompanyId(String name) throws SQLException{
        String sql = "Select address from Company where name=" + name + ";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result += rs.getArray("address")+ "\n";}
        return result;
    }
    public String getRoomInOrder() throws SQLException {
        String sql = "Select room_s_number from room  where room_s_number in (select getroomavailable()) order by grade asc;";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result += rs.getArray("room_s_number")+ "\n";}
        return result;
    }
    public String getCapacityInOrder() throws SQLException {
        String sql = "Select capacity from room  where room_s_number in (select getroomavailable()) order by grade asc;";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result += rs.getArray("capacity")+ "\n";}
        return result;
    }

    public String getPrice(String id, String electro) throws SQLException {
        logger.info("in getPrice");
        String sql = "Select getprice(" + id + "," + electro + ") as price;";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result = rs.getArray("price")+ "\n";
        }
        return result;
    }

    public String getMenu(String company) throws  SQLException{
        logger.info("in getMenu");
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
    public String getRoomAvailable() throws SQLException {
        logger.info("in getRoomAvailable");
        String sql = "Select getroomavailable() as room;";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result += rs.getArray("room")+ "\n";}
        return result;
    }
    public String getStoreHighValue(String id) throws SQLException {
        logger.info("in getStoreHighValue");
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
    public String getRoomName() throws SQLException {
        String sql = "Select name from room;";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while (rs.next()) {
            result += rs.getArray("name")+ "\n";}
        return result;
    }
    public String getTempExt(String s) throws SQLException {
        logger.info("in getTempExt");

        String sql ="SELECT value_of FROM MEASURE WHERE id_sensor = 2 ORDER BY - id_measure limit 1";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while(rs.next()){
            logger.info("in the while");
            result += rs.getArray("value_of");
        }
        return result;

    }
    public String getTempInt(String roomName) throws SQLException {
        logger.info("in getTempInt");
        String sql ="SELECT value_of FROM MEASURE WHERE id_sensor in (SELECT be_present.id_sensor FROM equipplace INNER JOIN be_present ON be_present.id_equipplace = equipplace.id_equipplace INNER JOIN room ON room.room_s_number = equipplace.id_room WHERE be_present.id_sensor in (SELECT id_sensor from sensor where description ='capteur de température') AND equipplace.id_room in (SELECT room_s_number from room where name = '"+ roomName +"')) ORDER BY - id_measure limit 1 ;";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String tempint = "";
        while(rs.next()){
            logger.info("in the while");
            tempint += rs.getArray("value_of");
        }
        return tempint;

    }
    public String getLightInt(String roomName) throws SQLException {

        logger.info("in getLightInt");
        String sql ="SELECT value_of FROM MEASURE WHERE id_sensor in (SELECT be_present.id_sensor FROM equipplace INNER JOIN be_present ON be_present.id_equipplace = equipplace.id_equipplace INNER JOIN room ON room.room_s_number = equipplace.id_room WHERE be_present.id_sensor in (SELECT id_sensor from sensor where description ='capteur de luminosité') AND equipplace.id_room in (SELECT room_s_number from room where name = '"+ roomName +"')) ORDER BY - id_measure limit 1 ;";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String tempint = "";
        while(rs.next()){
            logger.info("in the while");
            tempint += rs.getArray("value_of");
        }
        return tempint;

    }
    public String getGeneralTempInt(String s) throws SQLException{
        logger.info("in getGeneralTempInt");
        String sql ="SELECT temperature FROM PARAMETER_OF ORDER BY - id_parameter limit 1";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while(rs.next()){
            logger.info("in the while");
            result += rs.getArray("temperature");
        }
        return result;
    }
    public String getEquipementAvailable(String roomName) throws SQLException{
        logger.info("in getEquipementAvailable");
        String sql = "select distinct equipement.type from compatible inner join equipplace on equipplace.id_equipplace=compatible.id_equipplace inner join equipement on equipement.type= compatible.type_equip where equipplace.id_equipplace not in (Select id_equipplace from be_present) and equipement.id_equipement not in (Select id_equipement from be_present where id_equipement is not null) and equipplace.id_room in (Select room_s_number from room where name='"+roomName+"');";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while(rs.next()){
            logger.info("in the while");
            result += rs.getArray("type")+ "\n";
        }
        return result;
    }
    public String getSensorAvailable(String roomName) throws SQLException{
        logger.info("in getSensorAvailable");
        String sql = "select distinct sensor.description from compatible_sensor inner join equipplace on equipplace.id_equipplace=compatible_sensor.id_equipplace inner join sensor on sensor.description= compatible_sensor.description where equipplace.id_equipplace not in (Select id_equipplace from be_present) and sensor.id_sensor not in (Select id_sensor from be_present where id_sensor is not null) and equipplace.id_room in (Select room_s_number from room where name='"+roomName+"');";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while(rs.next()){
            logger.info("in the while");
            result += rs.getArray("description")+ "\n";
        }
        return result;
    }
    public String getEtatEquipement(String id) throws SQLException{
        logger.info("in getEtatEquipement");
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
    public void deleteBePresentEquipement(String id) throws SQLException{
        logger.info("in delete");
        String sql = "delete from be_present where id_equipement="+id+";";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }
    public String getPlaceEquip(String type,String room) throws SQLException{
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
    public void insertBePresentEquipement(String idPlace, String type) throws SQLException{
        logger.info("in insert equipement");
        String sql = "insert into be_present values ((Select equipement.id_equipement from equipement where equipement.type='"+type+"' and equipement.id_equipement not in (Select be_present.id_equipement from be_present where be_present.id_equipement is not null) limit 1),"+idPlace+",null);";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }
    public void insertBePresentSensor(String idPlace, String type) throws SQLException{
        logger.info("in insert sensor");
        String sql = "insert into be_present values (null,"+idPlace+",(Select sensor.id_sensor from sensor where (sensor.description='"+type+"') and (sensor.id_sensor not in (Select be_present.id_sensor from be_present where be_present.id_sensor is not null)) limit 1));";
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
        logger.info("in update opacity");
        String sql = "update equipement set valueof = " + valueopacity + " where id_equipement =" + id + ";";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }
    public void updateStoreHigh(String id, String valuehighstore) throws SQLException {
        logger.info("in update");
        String sql = "update equipement set valueof = " + valuehighstore + " where id_equipement =" + id + ";";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }
    public String getEtatSensor(String id) throws SQLException{
        logger.info("in getEtatSensor");
        String sql = "select animated from sensor where id_sensor="+id+";";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while(rs.next()){
            logger.info("in the while");
            result += rs.getArray("animated");
        }
        return result;
    }
    public void setTaken(String rent, String companyName, String idroom) throws SQLException {
        logger.info("in settaken");
        String sql = "insert into location values (" + idroom + ",(Select id_company from company where name ='"+ companyName+"') ," + rent + ", CURRENT_DATE);";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }
    public void deleteBePresentSensor(String id) throws SQLException{
        logger.info("in delete sensor");
        String sql = "delete from be_present where id_sensor="+id+";";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }
    public String getPlaceSensor(String type,String room) throws SQLException{
        logger.info("in getPlace");
        String sql = "select equipplace.id_equipplace,equipplace.position_x,equipplace.position_y from compatible_sensor inner join equipplace on equipplace.id_equipplace=compatible_sensor.id_equipplace where compatible_sensor.description = '"+type+"' and  equipplace.id_room in (Select room_s_number from room where name='"+room+"') and equipplace.id_equipplace not in (Select id_equipplace from be_present ); ";
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
    public String getSizeRoom(String roomName) throws SQLException{
        logger.info("in getSizeRoom");
        String sql = "select width, height from room where name='"+roomName+"';";
        Statement smt = c.createStatement();
        ResultSet rs = smt.executeQuery(sql);
        String result = "";
        while(rs.next()){
            logger.info("in the while");
            result += rs.getArray("width")+ "\n";
            result += rs.getArray("height") + "\n";

        }
        return result;
    }
    public void updateBePresentEquip(String idplace, String idEquip) throws SQLException {
        logger.info("in update be_presentEquip");
        String sql = "update be_present set id_equipplace = " + idplace + " where id_equipement =" + idEquip + ";";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }
    public void updateBePresentSensor(String idplace, String idEquip) throws SQLException {
        logger.info("in update be_presentSensor");
        String sql = "update be_present set id_equipplace = " + idplace + " where id_sensor =" + idEquip + ";";
        Statement smt = c.createStatement();
        logger.info(String.valueOf(smt.executeUpdate(sql)));
    }

}
