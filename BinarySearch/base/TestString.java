package base;

public class TestString {
    public static void main(String[] args) {
        String str1 = "AAABBB";
        String str2 = new String("AAA") + new String("BBB");
        System.out.println(str2.intern() == str2);
    }
}
