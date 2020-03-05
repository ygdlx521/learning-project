package online.daliang.sorts;

public class MergeSort {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            System.out.println(left + " " + right);
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            int i = left;
            int j = mid + 1;
            int k = 0;
            int[] temp = new int[right - left + 1];
            while (i <= mid && j <= right) {

                if (arr[i] < arr[j]) {
                    temp[k] = arr[i];
                    i++;
                } else {
                    temp[k] = arr[j];
                    j++;
                }
                k++;
            }
            int start = k;
            if (j > right) {
                for (int l = start; l < right - left + 1; l++) {
                    temp[k] = arr[i];
                    k++;
                    i++;
                }
            }
            start = k;
            if (i > mid) {
                for (int l = start; l < right - left + 1; l++) {
                    temp[k] = arr[j];
                    k++;
                    j++;
                }
            }
            for(int l=left;l<right + 1;l++){
                arr[l] = temp[l - left];
            }
            for (int f : arr) {
                System.out.print(f);
                System.out.print(" ");
            }
            System.out.println();
            System.out.println("----------------------------");

        }

    }

    public static void main(String[] args) {
        int[] arr = {9,8,7,6,4,4,4,4,4,4,4,5,23,23,23,23,2,32,32,3,24,3,2,1};
        for (int value : arr) {
            System.out.print(value);
            System.out.print(" ");
        }
        System.out.println();
        mergeSort(arr,0,arr.length-1);
        for (int i : arr) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
