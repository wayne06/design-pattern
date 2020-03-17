package designpattern.creational.factory.f3abstractfactory;


public interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();
    ISystemConfigParser createSystemParser();
}
