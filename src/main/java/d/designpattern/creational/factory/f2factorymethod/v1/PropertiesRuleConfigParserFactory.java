package d.designpattern.creational.factory.f2factorymethod.v1;

public class PropertiesRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new PropertiesRuleConfigParse();
    }
}
