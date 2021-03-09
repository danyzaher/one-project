package kakudou.org.exemplebuilderpattern;

/**
 *
 * @author Kakudou
 */
public class Main {

    public static void main(String[] args) {
        Database db = new Database();

        SqlQuery sqlQueryUser= new SqlQuery.Builder().select("user,id").from("users").where("username").eq("foo").and("password").eq("bar").build();
        db.execute(sqlQueryUser);
        
        HumanQuery humanQueryUser= new HumanQuery.Builder().select("user,id").from("users").where("username").eq("foo").and("password").eq("bar").build();
        db.execute(humanQueryUser);
        
        SqlQuery sqlQueryAllAdmin = new SqlQuery.Builder().select("*").from("MaSuperTable").where("role").like("admin").and("etat").neq("interdit").build();
        db.execute(sqlQueryAllAdmin);

        HumanQuery humanQueryAllAdmin = new HumanQuery.Builder().select("*").from("MaSuperTable").where("role").like("admin").and("etat").neq("interdit").build();
        db.execute(humanQueryAllAdmin);         
    }
   
}
