import java.util.*;
import java.io.*;

public class PidManager {

  public static int allocate_map(){
    pids = new HashMap();//hashmap to store pids and there values.

    for(int i = 0; i < MAX_PID+1; i++)//sets all the values of the keys to zero.
      pids.put(i, 0);
      
    return 1;
  }
  
  public static int allocate_pid(){
    if(pids.isEmpty())//checks if PidManager has not been initialized.
      return -1;

    int pid = 0;

    for(int i = MIN_PID; i <= MAX_PID; i++){
      if(!pids.get(MAX_PID).equals(0)){
        System.out.println("Failed to allocate pid.");
        return -1;
      }
      
      if(pids.get(i).equals(0)) {
        pid = i;
        pids.put(pid, 1);
        return pid;
      }   
    }
    return pid;
 }

  public static void release_pid(int pid){
    if(pids.isEmpty())
      System.out.println("Pid Manager has not been initiated.");
    
    if(pid > MAX_PID || pid < MIN_PID)
      System.out.println("This pid is out of range.");
    
    pids.put(pid, 0);
    System.out.println("released pid: " + pid);
    
  }

  private static int MIN_PID = 300;
  private static int MAX_PID = 5000;
  private static Map pids;
  
  public static void main(String[] args){
    allocate_map();
    System.out.println(allocate_map());

    for(int i = 0; i <= 5; i++){
      System.out.println("Allocated pid: " + allocate_pid());

    }
    release_pid(302);
    
  }
    
}
