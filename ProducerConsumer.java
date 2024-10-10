import java.util.LinkedList;

class ProducerConsumer {
    LinkedList<Integer> list = new LinkedList<>();
    int capacity = 2;

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (list.size() == capacity)
                    wait();
                System.out.println("Producer produced: " + value);
                list.add(value++);
                notify();
                Thread.sleep(1000);
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (list.isEmpty())
                    wait();
                int value = list.removeFirst();
                System.out.println("Consumer consumed: " + value);
                notify();
                Thread.sleep(1000);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer pc = new ProducerConsumer();
        Thread t1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
