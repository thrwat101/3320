import java.util.*;
import java.io.*;
public class DiskSchedule{

    public int FCFS(int init, String pos, int[] requests) {
        int prev = init;
        head_movements = prev;
        
        for(int i = 0; i < requests.length; ++i) {
                head_movements = head_movements + Math.abs(prev - requests[i]);
                prev = requests[i];
        }
        return head_movements;
    }

    public int SCAN(int init, String pos, int[] requests) {
                
        // Let us assume that disk arm move "towards larger value".
        
        int min_request = 2 * 5000;
        
        for(int i = 0; i < requests.length; ++i) {
                min_request = Math.min(min_request, requests[i]);
        }
        
        head_movements = (5000 - init);
        if(min_request < init) {
                head_movements = head_movements + (5000 - min_request);
        }
        
        return head_movements;
    }
    public int C_SCAN(int init, String pos, int[] requests) {
                
        // Let us assume that disk arm move "towards larger value".
        
        int nearest_left_of_start = 2 * 5000;
        
        for(int i = 0 ; i < requests.length; ++i) {
                if(requests[i] < init) {
                        nearest_left_of_start = Math.max(nearest_left_of_start, requests[i]);
                }
        }
        
        head_movements = (5000 - init) + (5000 - 0);
        if(nearest_left_of_start < 2 * 5000) {
                head_movements  = head_movements + nearest_left_of_start;
        }
        
        return head_movements;
        
    }

    private int cylinders = 5000;
    private int head_movements; 

    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        DiskSchedule disk = new DiskSchedule();
        int initial;
        String curr;

        int requests[] = {4078, 153, 2819, 3294, 1433, 211, 1594, 2004, 2335, 2007, 
            771, 1043, 3950, 2784, 1881,2931, 3599, 1245, 4086, 520, 3901, 2866, 947, 
            3794, 2353, 3970, 3948, 1815, 4621, 372, 2684, 3088, 827, 3126, 2083, 584, 
            4420, 1294, 917, 2881, 3659, 2868, 100, 1581, 4581, 1664, 1001, 1213, 3439, 
            4706};

        // User input initial position
        System.out.print("Enter the initial position of the disk head: ");
        initial = sc.nextInt();
        System.out.print("Enter the current direction in which it should start moving: ");
        curr = sc.next();

        int fcfs = disk.FCFS(initial, curr, requests);
        int scan =  disk.SCAN(initial, curr, requests);
        int c_scan = disk.C_SCAN(initial, curr, requests);
 
        System.out.println("Total head movements using FCFS: " + fcfs);
        System.out.println("Total head movements using SCAN: " + scan);
        System.out.println("Total head movements using C-SCAN: " + c_scan);


    }

}