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
        sleep_time = (int) (Math.random() * 50 + 1);
        System.out.println("Creating Thread: " + thread_id); 
    }

    MultiThread() {
        thread_id++;
        sleep_time = (int) (Math.random() * 50 + 1);
        System.out.println("Creating Thread: " + thread_id); 
    }

    @Override
    public void run() {
        Integer pid;

        System.out.println("Running Thread: " + currentThread().getName()); //show running thread
        pid = pids.allocate_pid();                                    //assigned our pid object from the Main class to the new_pid variable

        while (pid == -1) {                                         // to make sure that each process created gets its own PID
            System.out.println("All PIDs are in use");
            pid = pids.allocate_pid();
        }


        currentThread().setName(pid.toString());                       //PID  was assigned to the thread
        System.out.println("Allocated PID: " + pid);


        try {
            Thread.sleep(sleep_time);                                 //Thread sleeps for a while - time is random

        } catch (InterruptedException e) {
            System.out.println("Thread " + currentThread().getName() + " interrupted.");
        }

        Integer release_pid = Integer.valueOf(currentThread().getName()); //current thread is assigned to the new variable pid_to_release which will be released later


        pids.release_pid(release_pid);                                   //pids object called the release_pid method from the Main class to get released

        System.out.println("Exiting Thread: " + currentThread().getName()); //prints out the existing current thread
    }

    /*
      The thread pool is primarily used to reduce the number of application threads
      and provide management of the worker threads.
    */

    public static class ThreadPoolTest {

        public static void main(String args[]) {
            PidManager pids = new PidManager();

            if (pids.allocate_map() == 1) 
                System.out.println("Successfully allocated map of PIDs");
            else 
                System.out.println("Failed to allocate map of PIDs");
            
            ExecutorService pool1 = Executors.newFixedThreadPool(50);
            ExecutorService pool2 = Executors.newFixedThreadPool(50);
            
            for (int i = 1; i < 51; i++) {
                MultiThread task1 = new MultiThread(i);
                pool1.execute(task1);
            }
 
            for (int i = 1; i < 51; i++) {
                MultiThread task2 = new MultiThread(i);
                pool2.execute(task2);
            }    

            pool1.shutdown();
            pool2.shutdown();


            while (!pool1.isTerminated()) {
            }
            System.out.println("Finished all threads in Pool-1");

            while (!pool2.isTerminated()) {
            }
            System.out.println("Finished all threads in Pool-2");

           
        }

    }

}
