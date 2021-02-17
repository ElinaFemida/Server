import java.util.Arrays;
public class Gener<T> {
    private T[] gen;

    public Gener(T[] gen) {
        this.gen = gen;
    }

    public void changeElements(int firstElement, int secondElement) {
            T temp = gen [firstElement];
            gen[firstElement] = gen[secondElement];
            gen[secondElement] = temp;

        }

    @Override
    public String toString() {
        return Arrays.toString(gen);
    }
}
