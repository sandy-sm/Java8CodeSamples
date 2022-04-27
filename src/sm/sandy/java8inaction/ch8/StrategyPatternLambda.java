package sm.sandy.java8inaction.ch8;

import com.sun.xml.internal.ws.util.StringUtils;

interface Strategy {
    String process(String text);
}
public class StrategyPatternLambda {


    public static void main(String[] args) {
        Strategy capitalizeStrategy = (text -> StringUtils.capitalize(text));
        System.out.println(capitalizeStrategy.process("hello world"));

        Strategy lowerCase = (text -> text.toLowerCase());
        System.out.println(lowerCase.process("HELLO World"));

        Strategy removeSpecialChars = (text -> text.replaceAll("[^a-zA-Z0-9]", " "));
        System.out.println(removeSpecialChars.process("hello@ wor!ld !!"));
    }

}
