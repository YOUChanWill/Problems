package ProgramLearn;

public class ToLowerCase {

    public String toLowerCase(String s) {

        return s.toLowerCase();

    }


    public String toLowerCaseAPI(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch >= 65 && ch <= 90) { //大写字母ASCII码范围为[65,90],小写字母范围为[97,122]
                ch |= 32;
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}
