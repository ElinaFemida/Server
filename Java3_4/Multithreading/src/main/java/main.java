public class main {
    static final Object monitor = new Object();
    static volatile char ch = 'A';
    static final int count = 5;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < count; i++) {
                    synchronized (monitor) {
                        while (ch != 'A') {
                            monitor.wait();
                        }
                        System.out.print(ch);
                        ch = 'B';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    thread.start();

       Thread thread1=  new Thread(() -> {
            try {
                for (int i = 0; i < count; i++) {
                    synchronized (monitor) {
                        while (ch != 'B') {
                            monitor.wait();
                        }
                        System.out.print(ch);
                        ch = 'C';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
       thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < count; i++) {
                    synchronized (monitor) {
                        while (ch != 'C') {
                            monitor.wait();
                        }
                        System.out.print(ch);
                        ch = 'A';
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
    }
}
