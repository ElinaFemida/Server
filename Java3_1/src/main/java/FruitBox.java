import java.util.ArrayList;
import java.util.List;

public class FruitBox<T extends Fruit> {
    private final List<T> fruits = new ArrayList<>();


    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        if (fruits.size() > 0) {
            return fruits.size() * fruits.get(0).getWeight();
        }
        return 0;
    }

    public boolean compare(FruitBox<?> another) {
        return Math.abs(this.getWeight() - another.getWeight()) < 0.001;
    }

    public void addIntoBox(FruitBox<T> another) {

        another.fruits.addAll(fruits);
        fruits.clear();
        System.out.println(this.getWeight());
        System.out.println(another.getWeight());


    }

}

