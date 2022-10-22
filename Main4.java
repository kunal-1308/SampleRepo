import java.util.concurrent.TimeUnit;

public class Main4 {

    private static class ParallelTask implements Runnable {
        private Thread predecessor;

        @Override
        public void run() {

						Thread t = Thread.currentThread();
						String name = t.getName();
						long id = t.getId();
						boolean status = t.isAlive();
						Thread.State state = t.getState();
						System.out.println(name + " Started");
						System.out.println(" Thread ID = " + id);
						System.out.println(" Thread State = " + state);
						System.out.println(" Thread Status = " + status);
            

            if (predecessor != null) {
               
                try {
                    predecessor.join();
                   
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + " Finished");
        }

        public void setPredecessor(Thread t) {
            this.predecessor = t;
        }

    }

    public static void main(String[] args) {

        ParallelTask task1 = new ParallelTask();
        ParallelTask task2 = new ParallelTask();
        ParallelTask task3 = new ParallelTask();

        final Thread t1 = new Thread(task1, "Thread - 1");
        final Thread t2 = new Thread(task2, "Thread - 2");
        final Thread t3 = new Thread(task3, "Thread - 3");

        task2.setPredecessor(t1);
        task3.setPredecessor(t2);

        // now, let's start all three threads
        t1.start();
        t2.start();
        t3.start();
    }
}
