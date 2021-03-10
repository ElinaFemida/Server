import java.util.Arrays;

public class Tests {


    @Test(priority = 1)
    public static void test1() {
        System.out.println("It's test one!");
    }

    @Test(priority = 1)
    public static void test2() {
        System.out.println("It's test two!");
    }

    @Test(priority = 10)
    public static void test3() {
        System.out.println("It's test three!");
    }

    @Test(priority = 4)
    public static void test4() {
        System.out.println("It's test four!");
    }

    @Test(priority = 7)
    public static void test5() {
        System.out.println("It's test five!");
    }

    @Test(priority = 4)
    public static void test6() {
        System.out.println("It's test six!");
    }

    @Test(priority = 9)
    public static void test7() {
        System.out.println("It's test seven!");
    }

    @BeforeSuite
    public static void before() {
        System.out.println("It's first test!");
    }

    @AfterSuite
    public static void after() {
        System.out.println("It's last test!");
    }

//    @AfterSuite
//    public static void after2() {
//        System.out.println("It's fake last test!");
//    }
}
