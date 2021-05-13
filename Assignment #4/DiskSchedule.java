import java.util.*;
import java.io.*;
public class DiskSchedule{

    public int FCFS(int[] requests, int initial_position) {
        int pos = initial_position; 
        
        for(int i = 0; i < requests.length; i++) {
            head_movements += Math.abs(requests[i] - pos);
            pos = requests[i];
        }

        return head_movements;
    }

    public int SCAN(int[] requests, int initial_position) {          
        head_movements = cylinders - initial_position;
        int request = 10000;
        
        for(int i = 0; i < requests.length; ++i) 
                request = Math.min(request, requests[i]);
        
        if(request < initial_position)
                head_movements += (cylinders - request);
        
        return head_movements;

    }
    public int LOOK(int[] requests, int initial_position) {         
        int max = 0;
        int min = cylinders;
                
        for(int i = 0 ; i < requests.length ; i++){
            max = Math.max(max, requests[i]);
            min = Math.min(min, requests[i]);
        }

        head_movements = max - initial_position;
                
        if(min < initial_position) {
            head_movements += (max - min);
        }
                
        return head_movements;
    }

    private int cylinders = 5000;
    private int head_movements; 

    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        DiskSchedule disk = new DiskSchedule();
        int initial_position;

        int requests[] = {4078, 153, 2819, 3294, 1433, 211, 1594, 2004, 2335, 2007, 
            771, 1043, 3950, 2784, 1881,2931, 3599, 1245, 4086, 520, 3901, 2866, 947, 
            3794, 2353, 3970, 3948, 1815, 4621, 372, 2684, 3088, 827, 3126, 2083, 584, 
            4420, 1294, 917, 2881, 3659, 2868, 100, 1581, 4581, 1664, 1001, 1213, 3439, 
            4706};

        // input initial position
        System.out.print("Enter the initial position of the disk head: ");
        initial_position = sc.nextInt();

        int fcfs = disk.FCFS(requests, initial_position);
        int scan =  disk.SCAN(requests, initial_position);
        int look = disk.LOOK(requests, initial_position);
 
        System.out.println("FCFS total head movements: " + fcfs);
        System.out.println("SCAN total head movements: " + scan);
        System.out.println("LOOK total head movements: " + look);


    }

}