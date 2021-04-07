import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.Thread.currentThread;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThread implements Runnable{
    public static int thread_id;
    private int sleep_time;
    private PidManager pids = new PidManager();

    MultiThread(int thread_id, int sleep_time, PidManager pids) {
        this.thread_id = thread_id;
        this.sleep_time = sleep_time;
        this.pids = pids;
        System.out.println("Creating Thread: " + thread_id);
    }

    @Override
    public void run() {
        Integer pid;  
        Lock myLock = new ReentrantLock(); // creating a java reentrant lock object.
 
        System.out.println("Running Thread: " + currentThread().getName());

        myLock.lock(); 
        // calling the lock function. 
        //this will make sure only one thread calls allocate_pid() at a time.

        try{
            System.out.println("Lock aquired.");
            pid = pids.allocate_pid();

            if(pid == -1) {                                         
                System.out.println("All PIDs are in use");
                pid = pids.allocate_pid();
            }

        } finally { 
            System.out.println("Releasing Lock.");
            myLock.unlock(); 
        }

        currentThread().setName(pid.toString());                     
        System.out.println("Allocated PID: " + pid);


        try {
            Thread.sleep(sleep_time);                                 

        } catch (InterruptedException e) {
            System.out.println("Thread " + currentThread().getName() + " interrupted.");
        }
        
        Integer pid_release = Integer.valueOf(currentThread().getName()); 
        
        myLock.lock();
        // locking once again.
        // only one thread is released at a time.

        try{

            pids.release_pid(pid_release);

        }finally { myLock.unlock(); }
                                           

        System.out.println("Exiting Thread: " + currentThread().getName()); 
    }

    public static class ThreadPoolTest {
        public static void main(String args[]) {
            PidManager pm = new PidManager();
            pm.allocate_map();

            //creating a thread pool for our multi thread
            ExecutorService pool = Executors.newFixedThreadPool(50);

            for (int i = 1; i <= 5; i++) {
                MultiThread task1 = new MultiThread(i, (int)(Math.random()*1000), pm);  
                pool.execute(task1);    
            }

            pool.shutdown();
        }

    }

}
