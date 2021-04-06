import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class yamltest {
    public static void main(String[] args) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("client\\src\\main\\resources\\socketConnection.yaml"));

        Yaml y = new Yaml().load(reader);
        HashMap<?, ?> map = y.loadAs(reader, HashMap.class);
        System.out.println(map.toString());
    }
}
