import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Client {
    private final static Logger logger = LoggerFactory.getLogger(Client.class.getName());

    public static void main(String[] args) throws ParseException {
        final Options opts = new Options();
        final Option Name = Option.builder().longOpt("Name").hasArgs().argName("Name").build();
        opts.addOption(Name);
        final CommandLineParser commandLineParser = new DefaultParser();
        final CommandLine cl = commandLineParser.parse(opts, args);
        logger.info("The client is online. The name of the client is {}",cl.getOptionValue("Name"));
    }
}