package ProgramLearn;

public class MergeAlternately {

    public String mergeAlternately(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length(), index = 0;
        char[] res = new char[len1 + len2];

        for (int i = 0; i < len1 || i < len2; i++) {
            if (i < len1) res[index++] = word1.charAt(i);
            if (i < len2) res[index++] = word2.charAt(i);
        }
        return new String(res);
    }
}
