package ProgramLearn;

public class ArraySign {

    public int arraySign(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0){
                count++;// 计算负数的个数
            }
            if (nums[i] == 0){
                return 0;
            }
        }
        if (count % 2 == 1){
            return -1;
        }else return 1;
    }

//    public static int signFunc(int product){
//        if (product > 0){
//            return 1;
//        }else if (product < 0){
//            return -1;
//        }else return 0;
//    }

}
