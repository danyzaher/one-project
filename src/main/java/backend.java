import ch.qos.logback.classic.Logger;
import org.apache.commons.cli.*;
import org.slf4j.LoggerFactory;

public class backend {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(backend.class.getName());
    public static void main(String[] args) throws ParseException {
        final Options options = new Options();
        final Option test = Option.builder().longOpt("test").build();
        options.addOption(test);
        final Option Name = Option.builder().longOpt("Name").hasArgs().argName("Name").build();
        options.addOption(Name);
        final CommandLineParser parser = new DefaultParser();
        final CommandLine cl = parser.parse(options,args);
        logger.info("Running service (test = {})", cl.hasOption("test"));
        logger.info("The client is online. The name of the client is {}",cl.getOptionValue("Name"));
    }
}
