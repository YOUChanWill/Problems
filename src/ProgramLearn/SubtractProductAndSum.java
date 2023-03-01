package ProgramLearn;

public class SubtractProductAndSum {

    public int subtractProductAndSum(int n) {
        String nums=String.valueOf(n); // 将int类型转为字符串
        int sub = 1, sum = 0;
        for (int i = 0; i < nums.length(); i++) {
            sub *= (int)nums.charAt(i)-(int)('0'); // 涉及到char->int，这里不能直接强制转化，不然获取的是ASCII码值
            sum += (int)nums.charAt(i)-(int)('0');
        }
        return sub - sum;
    }
}
