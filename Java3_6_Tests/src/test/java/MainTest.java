import org.junit.Assert;
import org.junit.Test;

public class MainTest{
    Main main = new Main();

    @Test
    public void testArrLastFour1() {
        int[] arr = {1, 4, 5, 6, 7, 4, 5};
        int[] expectedArr = {5};
        int[] resultArr = main.arrLastFour(arr);
        Assert.assertArrayEquals(expectedArr, resultArr);
    }

    @Test
    public void testArrLastFour2() {
        int[] arr = {1, 4, 5, 6, 7, 4};
        int[] expectedArr = {};
        int[] resultArr = main.arrLastFour(arr);
        Assert.assertArrayEquals(expectedArr, resultArr);
    }

    @Test
    public void testArrLastFour3() {
        int[] arr = {4, 4, 4, 4, 4, 4, 4, 1, 1, 1};
        int[] expectedArr = {1, 1, 1};
        int[] resultArr = main.arrLastFour(arr);
        Assert.assertArrayEquals(expectedArr, resultArr);
    }

    @Test(expected = RuntimeException.class)

    public void testArrLastFour4() {
        int[] arr = {1, 5, 6, 7, 5};
        int[] expectedArr = {};
        int[] resultArr = main.arrLastFour(arr);
        Assert.assertArrayEquals(expectedArr, resultArr);
    }

    @Test
    public void oneOrFour1() {
        int[] arr = {1, 1, 1, 1, 1, 1, 1, 4};
        Assert.assertTrue(main.oneOrFour(arr));

    }

    @Test
    public void oneOrFour2() {
        int[] arr = {1, 1, 1, 4, 4, 4, 44, 4, 4, 4};
        Assert.assertFalse(main.oneOrFour(arr));
    }

    @Test
    public void oneOrFour3() {
        int[] arr = {1, 1, 1, 1, 4, 3};
        Assert.assertFalse(main.oneOrFour(arr));
    }

    @Test
    public void oneOrFour4() {
        int[] arr = {1, 1, 1, 1};
        Assert.assertFalse(main.oneOrFour(arr));
    }

    @Test
    public void oneOrFour5() {
        int[] arr = {4, 4};
        Assert.assertFalse(main.oneOrFour(arr));
    }


}