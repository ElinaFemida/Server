import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
//1st task
        Gener<String> arr = new Gener<>(new String[]{"A", "N", "N", "U", "S", "H", "K", "A"});
        System.out.println(arr);
        arr.changeElements(0, 1);
        System.out.println(arr);

//2d task
        ArrayList<Gener> list1 = new ArrayList();
        new ArrayList();
        List<Gener> list2 = Arrays.asList(arr);
        System.out.println("Первый вариант: " + list2);
        List<Gener> list3 = new ArrayList();
        Collections.addAll(list3, arr);
        System.out.println("Второй вариант: " + list3);

        // 3d task
        FruitBox<Apple> appleBox1 = new FruitBox();
        FruitBox<Apple> appleBox2 = new FruitBox();
        FruitBox<Orange> orangeBox1 = new FruitBox();
        appleBox1.addFruit(new Apple());
        appleBox2.addFruit(new Apple());
        orangeBox1.addFruit(new Orange());
        System.out.println(orangeBox1.getWeight());
        orangeBox1.addFruit(new Orange());
        appleBox1.addFruit(new Apple());
        appleBox1.addFruit(new Apple());
        System.out.println(orangeBox1.compare(appleBox1));
        System.out.println(orangeBox1.compare(appleBox2));
        System.out.println(appleBox1.compare(appleBox2));
        appleBox1.addIntoBox(appleBox2);
        System.out.println(orangeBox1.compare(appleBox2));
    }

}
