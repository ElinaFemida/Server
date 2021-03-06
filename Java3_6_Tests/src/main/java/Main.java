import java.util.Arrays;

public class Main {

    public int[] arrLastFour(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 4) {
                return Arrays.copyOfRange(arr, i + 1, arr.length);
            }
        }
        throw new RuntimeException();
    }

    public boolean oneOrFour(int[] arr) {
        boolean isOneHere = false;
        boolean isFourHere = false;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] != 1) && (arr[i] != 4)) {
                return false;
            }
            if (arr[i] == 1) {
                isOneHere = true;
            }
            else  {
                isFourHere = true;
            }
        }
        return isFourHere && isOneHere;
    }

}
