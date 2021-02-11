
import javax.swing.*;
import java.util.logging.Logger;

public class backend {
    private final static Logger logger = Logger.getLogger("backend");
    public static void main(String[] args) {
        JFrame frame = new App();
        logger.info("Running service.");
    }
}
