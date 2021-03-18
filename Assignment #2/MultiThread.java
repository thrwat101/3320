import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.Thread.currentThread;
public class MultiThread implements Runnable{
    public static int thread_id = 0;
    private int sleep_time;
    private PidManager pids = new PidManager();

    MultiThread(int thread_id, int sleep_time, PidManager pids) {
        this.thread_id = thread_id;
        this.sleep_time = sleep_time;
        this.pids = pids;
        System.out.println("Creating Thread: " + thread_id);
    }

    MultiThread(int thread_id) {
        this.thread_id = thread_id;
        sleep_time = (int) (Math.random() * 1000);
        pids.allocate_map();
        System.out.println("Creating Thread: " + thread_id); 
    }

    MultiThread() {
        thread_id++;
        sleep_time = (int) (Math.random() * 1000);
        pids.allocate_map();
        System.out.println("Creating Thread: " + thread_id); 
    }

    @Override
    public void run() {
        Integer pid;

        System.out.println("Running Thread: " + currentThread().getName()); 
        pid = pids.allocate_pid();                                    

        while (pid == -1) {                                         
            System.out.println("All PIDs are in use");
            pid = pids.allocate_pid();
        }


        currentThread().setName(pid.toString());                     
        System.out.println("Allocated PID: " + pid);


        try {
            Thread.sleep(sleep_time);                                 

        } catch (InterruptedException e) {
            System.out.println("Thread " + currentThread().getName() + " interrupted.");
        }

        Integer release_pid = Integer.valueOf(currentThread().getName()); 

        pids.release_pid(release_pid);                                   

        System.out.println("Exiting Thread: " + currentThread().getName()); 
    }

    public static class ThreadPoolTest {
        public static void main(String args[]) {
            PidManager pm = new PidManager();
            pm.allocate_map();

            //creating thread pools for each multi thread
            ExecutorService pool1 = Executors.newFixedThreadPool(5); 
            ExecutorService pool2 = Executors.newFixedThreadPool(5);

            for (int i = 1; i <= 5; i++) {
                MultiThread task1 = new MultiThread(i, (int) (Math.random() * 1000), pm);  
                pool1.execute(task1);    
            }
            
            for (int i = 1; i <= 5; i++) {
                MultiThread task2 = new MultiThread(i, (int) (Math.random() * 1000), pm);
                pool2.execute(task2);
            }

            pool1.shutdown();
            pool2.shutdown();
  
        }

    }

}
