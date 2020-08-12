package d.designpattern.creational.factory.f3abstractfactory;

import d.designpattern.creational.factory.f2factorymethod.v2.*;
import d.designpattern.creational.factory.f2factorymethod.v2.IRuleConfigParser;
import designpattern.creational.factory.f2factorymethod.v2.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RuleConfigSource {

    public RuleConfig load(String ruleConfigFilePath) throws InvalidRuleConfigException {

        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParserFactory factory = RuleConfigParserFactoryMap.getParserFactory(ruleConfigFileExtension);

        if (factory == null) {
            throw new InvalidRuleConfigException("Rule config file format is not supported: " + ruleConfigFileExtension);
        }
        IRuleConfigParser parser = factory.createParser();

        String configText = getFileContent(ruleConfigFilePath);
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
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
