package d.designpattern.creational.factory.f2factorymethod.v2;

public class PropertiesRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new PropertiesRuleConfigParse();
    }
}
