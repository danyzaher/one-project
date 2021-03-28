import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private final static Logger logger = LoggerFactory.getLogger(Client.class.getName());

    public static void main(String[] args) throws ParseException {

        final Options options = new Options();
        final Option name = Option.builder().longOpt("name").hasArgs().argName("name").build();options.addOption(name);
        final CommandLineParser commandLineParser = new DefaultParser();
        final CommandLine cl = commandLineParser.parse(options, args);
        String Username = "client";

        if (cl.hasOption("name")) {
           Username = cl.getOptionValue("name");
        }
        logger.info("The client program is running... The name of the client is {}",Username);

    }
}
