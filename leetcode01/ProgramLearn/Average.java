package ProgramLearn;

public class Average {
    public double average(int[] salary) {
        double ave = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < salary.length; i++) {
            ave += salary[i];
            if (salary[i] < min){
                min = salary[i];
            }
            if (salary[i] > max) {
                max = salary[i];
            }
        }
        return  (ave - max - min)/(salary.length - 2);
    }
}
