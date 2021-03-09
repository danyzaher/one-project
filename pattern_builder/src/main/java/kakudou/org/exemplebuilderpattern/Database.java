package kakudou.org.exemplebuilderpattern;

/**
 *
 * @author Kakudou
 */
class Database {

   public void execute(Object query) {
        if (query.getClass() == SqlQuery.class) {
            this.executeSql((SqlQuery)query);   
        }else if(query.getClass() == HumanQuery.class) {
            this.executeHuman((HumanQuery)query); 
        }
    }

    private void executeSql(SqlQuery sqlQuery) {
        System.out.println("Execute the following SQL query:");
        System.out.println(sqlQuery.query);
        System.out.println("");
    }
    
    private void executeHuman(HumanQuery humanQuery) {
        System.out.println("Execute the following Human query:");
        System.out.println(humanQuery.query);
        System.out.println("");
    }
    
}
