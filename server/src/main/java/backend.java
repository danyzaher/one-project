import ch.qos.logback.classic.Logger;
import org.apache.commons.cli.*;
import org.slf4j.LoggerFactory;

public class backend {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(backend.class.getName());
    public static void main(String[] args) throws ParseException {

        final Options options = new Options();
        final Option test = Option.builder().longOpt("test").build();
        options.addOption(test);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cl = parser.parse(options,args);
        logger.info("Running service (test = {})", cl.hasOption("test"));

    }
}
