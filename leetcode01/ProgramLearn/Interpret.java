package ProgramLearn;

public class Interpret {

    public String interpret(String command) {
        return command.replace("()","o").replace("(al)","al");
    }
}
