import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static final AtomicBoolean isWinner = new AtomicBoolean(false);
    private CountDownLatch cdl;
    private final CyclicBarrier cb;
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch cdl, CyclicBarrier cb) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cdl = cdl;
        this.cb = cb;
    }

    @Override
    public void run() {
        String winner = null;
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            cdl.countDown();
            cdl.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);}
                if (isWinner.compareAndSet(false, true)) {
                    winner = name;
            }
                cb.await();
                if (winner != null){
            System.out.println("Winner is: " + winner);}
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}