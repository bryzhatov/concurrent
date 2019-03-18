/**
 * @author Dmitry Bryzhatov
 * @since 2019-03-18
 */
public class PetersonAlgorithm {
    private final boolean[] peterson_mutex = new boolean[2];
    private static volatile int shared_resource;

    public static void main(String[] args) throws InterruptedException {
        PetersonAlgorithm peterson = new PetersonAlgorithm();

        Thread thread1 = new Thread(() -> {
            peterson.lock();
            peterson.critical_section();
            peterson.unLock();
        });
        thread1.setName("0");

        Thread thread2 = new Thread(() -> {
            peterson.lock();
            peterson.critical_section();
            peterson.unLock();
        });
        thread2.setName("1");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(shared_resource);
    }

    private void critical_section() {
        for (int i = 0; i < 100000; i++) {
            shared_resource++;
        }
    }

    private void lock() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        peterson_mutex[id] = true;
        int brotherThread = 1 - id;
        while (peterson_mutex[brotherThread]){}
    }

    private void unLock() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        peterson_mutex[id] = false;
    }
}
