package ProgramLearn;

public class FreqAlphabets {

    public String freqAlphabets(String s) {
        // 1 - 9 只有一位数， 10 - 26 有 10# - 26# 三位数
        StringBuilder sb = new StringBuilder();
        char[] map = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for(int i = 0; i < s.length(); ){
            if(i + 2 < s.length() && s.charAt(i + 2) == '#'){
                sb.append(map[Integer.valueOf(s.substring(i, i + 2)) - 1]);
                i += 3;
            }else{
                sb.append((char)(map[s.charAt(i) - '0'] - 1));
                i++;
            }
        }
        return sb.toString();
    }
}
