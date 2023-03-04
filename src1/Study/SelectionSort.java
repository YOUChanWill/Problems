package Study;

public class SelectionSort {

    public static void main(String[] args) {
        SelectionSort sort = new SelectionSort();
        int[] list = {1,2,6,3,2,9,4};
        sort.selectionSort(list);
        for (int nums :
                list) {
            System.out.print(nums);
        }
    }

    public int selectionSmallset(int[] list){
        int smallest = list[0];
        int smallest_index = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i] < smallest){
                smallest = list[i];
                smallest_index = i;
            }
        }
        return smallest_index;
    }

    public int[] selectionSort(int[] list){
        int[] ints = new int[list.length];
        int smallest;
        for (int i = 0; i < list.length; i++) {
            smallest = selectionSmallset(list);
            ints[i] = smallest;
        }
        return ints;
    }


}
