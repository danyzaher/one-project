package kakudou.org.exemplebuilderpattern;

/**
 *
 * @author Kakudou
 */
class HumanQuery {

    public String query = "";
    
    public static class Builder {
        
        private final HumanQuery currentQuery;

        public Builder() {
            this.currentQuery = new HumanQuery();
        }
     
        public Builder select(String select){
            this.currentQuery.query += String.format("Donne moi %s ", select);
            
            return this;
        }
        
        public Builder from(String from){
            this.currentQuery.query += String.format("de %s ", from);
            
            return this;
        }
        
        public Builder where(String where){
            this.currentQuery.query += String.format("quand %s ", where);
            
            return this;
        }
        
        public Builder eq(String eq){
            this.currentQuery.query += String.format("est egal a %s ", eq);
            
            return this;
        }
        
        public Builder neq(String neq){
            this.currentQuery.query += String.format("n\'est pas egal a %s ", neq);
            
            return this;
        }
        
        public Builder like(String like){
            this.currentQuery.query += String.format("ressemble a %s ", like);
            
            return this;
        }
        
        public Builder and(String and){
            this.currentQuery.query += String.format("et %s ", and);
            
            return this;
        }
        
        public HumanQuery build(){
            this.currentQuery.query = this.currentQuery.query.trim() + ".";
            return this.currentQuery;
        }
        
    }
    
}
