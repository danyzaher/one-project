package kakudou.org.exemplebuilderpattern;

/**
 *
 * @author Kakudou
 */
class SqlQuery {

    public String query = "";
    
    public static class Builder {
        
        private final SqlQuery currentQuery;

        public Builder() {
            this.currentQuery = new SqlQuery();
        }
     
        public Builder select(String select){
            this.currentQuery.query += String.format("SELECT '%s' ", select);
            
            return this;
        }
        
        public Builder from(String from){
            this.currentQuery.query += String.format("FROM '%s' ", from);
            
            return this;
        }
        
        public Builder where(String where){
            this.currentQuery.query += String.format("WHERE '%s' ", where);
            
            return this;
        }
        
        public Builder eq(String eq){
            this.currentQuery.query += String.format("= '%s' ", eq);
            
            return this;
        }
        
        public Builder neq(String neq){
            this.currentQuery.query += String.format("<> '%s' ", neq);
            
            return this;
        }
        
        public Builder like(String like){
            this.currentQuery.query += String.format("LIKE '%%%s%%' ", like);
            
            return this;
        }
        
        public Builder and(String and){
            this.currentQuery.query += String.format("AND '%s' ", and);
            
            return this;
        }
        
        public SqlQuery build(){
            this.currentQuery.query = this.currentQuery.query.trim() + ";";
            return this.currentQuery;
        }
        
    }
    
}
