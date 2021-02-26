import java.util.*;
import java.io.*;

public class PidManager {

  public static int allocate_map(){
    pids = new HashMap();

    for(int i = 0; i < MAX_PID+1; i++)
      pids.put(i, 0);
      
    return 1;
  }
  
  public static int allocate_pid(){
    if(pids.isEmpty())
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
      return -1;
    
    
  }

  private static int MIN_PID = 300;
  private static int MAX_PID = 5000;
  private static Map pids;
  
  public static void main(String[] args){
    allocate_map();
    System.out.println(allocate_map());

    for(int i = 0; i <= 4701; i++){
      System.out.println("Allocated pid: " + allocate_pid());

    }
    
  }
    
}
