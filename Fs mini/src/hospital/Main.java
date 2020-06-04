package hospital;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) throws IOException{
		@SuppressWarnings("resource")
	
	
		Scanner scanner = new Scanner(System.in);
		Mrcrd pi = new Mrcrd();
		Demo si = new Demo();
		si.indexing();
		si.secIndex();
        pi.indexing();
        pi.priIndex();
                while (true){
                		System.out.println("WELCOME");
                       
	                System.out.println("ENTER YOUR CHOICE"); 
                       
			System.out.println("1>Enter the details: \n"
					+ "2>Enter the ID to Search: \n"
					+ "3>Enter the condition to Search: \n"
					+ "4>To Build Index using primary key: \n"
					+ "5>To Build Index secondary key: \n"
					+ "6>Enter the primary key to be Deleted: \n"
					+ "7>Enter the secondary key to be Deleted: \n"
					+ "8>Exit");
		 int choice = scanner.nextInt();
		
			switch(choice){

				case 1: long starttime =System.nanoTime();
                                      pi.getData();
                                      pi.add();
                                      pi.priIndex();
                                      si.secIndex();
                                      long endtime =System.nanoTime();
                                      long totaltime=endtime-starttime;
                                      System.out.println(totaltime/1000000+"msec");
						break;
				case 2: long starttime1 =System.nanoTime();
                                  pi.search();
                                  long endtime1 =System.nanoTime();
                                      long totaltime1=endtime1-starttime1;
                                      System.out.println(totaltime1/1000000+"msec");
				    break;
				case 3: long starttime2 =System.nanoTime();
                		si.search();
                		long endtime2 =System.nanoTime();
                		long totaltime2=endtime2-starttime2;
                		System.out.println(totaltime2/1000000+"msec");
                		break;    
				case 4:long starttime3 =System.nanoTime();
                                  pi.indexing();
                                  long endtime3 =System.nanoTime();
                                      long totaltime3=endtime3-starttime3;
                                      System.out.println(totaltime3/1000000+"msec");
						break;
				case 5:long starttime4 =System.nanoTime();
                		si.indexing();
                		long endtime4 =System.nanoTime();
                		long totaltime4=endtime4-starttime4;
                    	System.out.println(totaltime4/1000000+"msec");
                    	break;
                case 6:long starttime5 =System.nanoTime();
						pi.delete();
				        long endtime5=System.nanoTime();
                        long totaltime5=endtime5-starttime5;
                        System.out.println(totaltime5/1000000+"msec");
                        break;
                case 7:long starttime6 =System.nanoTime();
						si.delete();
						long endtime6=System.nanoTime();
                        long totaltime6=endtime6-starttime6;
                        System.out.println(totaltime6/1000000+"msec");
                        break;        
				case 8: System.out.println("Exiting..");
						return ;
						
				default : System.out.println("Enter a valid choice!");
			}	
			}
		
			}                 		
	}
	
