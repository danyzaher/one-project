import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {
    private final static Logger logger = LoggerFactory.getLogger(Client.class.getName());

    public static void main(String[] args) throws ParseException {

        final Options options = new Options();
        final Option name = Option.builder().longOpt("name").hasArgs().argName("name").build();options.addOption(name);
        final CommandLineParser commandLineParser = new DefaultParser();
        final CommandLine client = commandLineParser.parse(options, args);
        String Username = "none";


        if (client.hasOption("name")) {
           Username = client.getOptionValue("name");
        }
        logger.info("The client programme is running... The name of the client is : name={}",Username);

    }

}
