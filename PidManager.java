import java.util.*;
import java.io.*;

public class PidManager {

  public static int allocate_map(){
    pids = new HashMap();
     
    if(pids == null)
      return -1;
     
    for(int i = 0; i < MAX_PID+1; i++)
      pids.put(i, 0);
  
    return 1;
  }
  
public static int allocate_pid(){
  if(pids == null) 
      return -1;

    int pi = 0;

  for(int i = MIN_PID; i < MAX_PID; i++){
    if(pids.get(i).equals(0)) {
      pi = i;
      pids.put(pi, 1);
      return pi;
    }
  }
    
    return 1;
 }

  //public int release_pid(int pid){

 // }

  private static int MIN_PID = 300;
  private static int MAX_PID = 5000;
  private static Map pids;
  
  public static void main(String[] args){

      allocate_map();
      System.out.println(allocate_pid());
      System.out.println(allocate_pid());
      System.out.println(allocate_pid());
      
  }
    
}
