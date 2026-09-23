package sorting;

import java.util.Arrays;

/**
 * File 04 — Q402: Bubble, Insertion, Selection, Merge Sort
 */
class ClassicSorting {

    static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int key = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = key;
        }
    }

    static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }

    static void mergeSort(int[] nums) {
        mergeSort(nums, new int[nums.length], 0, nums.length - 1);
    }

    static void mergeSort(int[] nums, int[] temp, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(nums, temp, lo, mid);
        mergeSort(nums, temp, mid + 1, hi);
        merge(nums, temp, lo, mid, hi);
    }

    static void merge(int[] nums, int[] temp, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        int k = lo;
        while (i <= mid && j <= hi) {
            temp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= hi) {
            temp[k++] = nums[j++];
        }
        System.arraycopy(temp, lo, nums, lo, hi - lo + 1);
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    void main() {
        int[] nums = {5, 2, 4, 6, 1, 3};
        bubbleSort(nums);
        IO.println("Bubble:     " + Arrays.toString(nums));

        nums = new int[]{5, 2, 4, 6, 1, 3};
        insertionSort(nums);
        IO.println("Insertion:  " + Arrays.toString(nums));

        nums = new int[]{5, 2, 4, 6, 1, 3};
        selectionSort(nums);
        IO.println("Selection:  " + Arrays.toString(nums));

        nums = new int[]{5, 2, 4, 6, 1, 3};
        mergeSort(nums);
        IO.println("Merge:      " + Arrays.toString(nums));
    }
}
