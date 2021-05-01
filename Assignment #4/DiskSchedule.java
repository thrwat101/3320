import java.util.*;
import java.io.*;
public class DiskSchedule{

    public int FCFS(int pos) {
        int prev = pos;
        head_movements = prev;
        
        for(int i = 0 ; i < 1000 ; ++i) {
                head_movements = head_movements + Math.abs(prev - requests[i]);
                prev = requests[i];
        }
        return head_movements;
    }

    public int SCAN(int pos) {
                
        // Let us assume that disk arm move "towards larger value".
        
        int min_request = 2 * 5000;
        
        for(int i = 0 ; i < 1000 ; ++i) {
                min_request = Math.min(min_request, requests[i]);
        }
        
        head_movements = (5000 - pos);
        if(min_request < pos) {
                head_movements = head_movements + (5000 - min_request);
        }
        
        return head_movements;
    }
    public int C_SCAN(int pos) {
                
        // Let us assume that disk arm move "towards larger value".
        
        int nearest_left_of_start = 2 * 5000;
        
        for(int i = 0 ; i < 1000 ; ++i) {
                if(requests[i] < pos) {
                        nearest_left_of_start = Math.max(nearest_left_of_start, requests[i]);
                }
        }
        
        head_movements = (5000 - pos) + (5000 - 0);
        if(nearest_left_of_start < 2 * 5000) {
                head_movements  = head_movements + nearest_left_of_start;
        }
        
        return head_movements;
        
    }

    public int cylinders = 5000;
    public int requests[] = new int[50];
    public int head_movements; 

    public static void main(String []args) {

        DiskSchedule fcfs = new DiskSchedule();
        DiskSchedule scan = new DiskSchedule();
        DiskSchedule c_scan = new DiskSchedule();
        
        int requests[] = {4078, 153, 2819, 3294, 1433, 211, 1594, 2004, 2335, 2007, 771, 1043, 3950, 2784, 1881,
            2931, 3599, 1245, 4086, 520, 3901, 2866, 947, 3794, 2353, 3970, 3948, 1815, 4621, 372, 2684, 3088, 
            827, 3126, 2083, 584, 4420, 1294, 917, 2881, 3659, 2868, 100, 1581, 4581, 1664, 1001, 1213, 3439, 4706};


        int initial_position; 
        int current_position;  
        Random rand = new Random();
        
        for(int i = 0; i <= 50; i++) {
            initial_position = requests[i];
            requests[i] = rand.nextInt(5000);
        }

        /*System.out.println("Total head movements using FCFS: " + fcfs.FCFS(initial_position));
        System.out.println("Total head movements using SCAN: " + scan.SCAN(initial_position));
        System.out.println("Total head movements using C-SCAN: " + c_scan.C_SCAN(initial_position));*/


    }

}