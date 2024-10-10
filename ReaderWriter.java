import java.util.concurrent.Semaphore;

class ReaderWriter {
    static Semaphore readLock = new Semaphore(1);
    static Semaphore writeLock = new Semaphore(1);
    static int readCount = 0;

    static class Reader implements Runnable {
        public void run() {
            try {
                readLock.acquire();
                readCount++;
                if (readCount == 1) {
                    writeLock.acquire();
                }
                readLock.release();

                System.out.println(Thread.currentThread().getName() + " is reading...");
                Thread.sleep(1000);

                readLock.acquire();
                readCount--;
                if (readCount == 0) {
                    writeLock.release();
                }
                readLock.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Writer implements Runnable {
        public void run() {
            try {
                writeLock.acquire();
                System.out.println(Thread.currentThread().getName() + " is writing...");
                Thread.sleep(1000);
                writeLock.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Reader reader = new Reader();
        Writer writer = new Writer();
        Thread t1 = new Thread(reader);
        Thread t2 = new Thread(writer);
        Thread t3 = new Thread(reader);
        Thread t4 = new Thread(writer);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }
}


// Output
// Thread-0 is reading...
// Thread-2 is reading...
// Thread-1 is writing...
// Thread-3 is writing...