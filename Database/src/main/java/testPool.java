import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class testPool {
    public static Logger logger = LoggerFactory.getLogger("testPool");
    public static void main(String[] args){
        Datasource datasource = new Datasource(10);

    }

}
