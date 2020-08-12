package d.designpattern.creational.factory.f3abstractfactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConfigSource {

    public Config load(String configFilePath) throws InvalidConfigException {
        String extension = getFileExtension(configFilePath);

        IConfigParserFactory factory = ConfigParserFactoryMap.getParserFactory(extension);
        if (factory == null) {
            throw new InvalidConfigException("Config file format is not supported: " + extension);
        }

        IConfigParser parser = factory.createRuleParser();
        //IConfigParser parser = factory.createSystemParser();

        Config config = parser.parse(getFileContent(configFilePath));
        return config;
    }

    private String getFileContent(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                stringBuffer.append(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    private String getFileExtension(String filePath) {
        return filePath.split(".")[-1];
    }
}
