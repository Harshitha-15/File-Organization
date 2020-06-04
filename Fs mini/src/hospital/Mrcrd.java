package hospital;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Mrcrd {
private MIndex[] sI = new MIndex[110010];
	
    private String Id,Name,Gender,Age,Description;
	int reccount = 0;

public void getData(){
    		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Id: ");
		Id = scanner.next();
		System.out.println("Enter the NAME: ");
		 Name = scanner.next();
		System.out.println("Enter the gender ");
		Gender= scanner.next();
		System.out.println("Enter the Age: ");
		Age= scanner.next();
		System.out.println("Enter the Description ");
		Description = scanner.next();
		
}
public void add(){
        String data=Id+","+ Name +","+ Gender +","+ Age +","+ Description ;

 try{			
			RandomAccessFile recordfile = new RandomAccessFile ("C:\\Users\\hp\\Downloads/hsptl.csv","rw");
			recordfile.seek(recordfile.length());
			long pos = recordfile.getFilePointer();
			recordfile.writeBytes(data+"\n");
			recordfile.close();
			
			RandomAccessFile indexfile = new RandomAccessFile ("C:\\Users\\hp\\Downloads/index.txt","rw");
			indexfile.seek(indexfile.length());
			indexfile.writeBytes(Id+","+pos+"\n");
			indexfile.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
		
 
}                     
    @SuppressWarnings("resource")
    public void priIndex(){

		String line
                        ,Id = null,pos = null;
		int count = 0;
		int sIIndex = 0;
		reccount=0;
		RandomAccessFile indexfile;
		 long starttime =System.nanoTime();
		try {
			indexfile = new RandomAccessFile("C:\\Users\\hp\\Downloads/index.txt","rw");

			try {
				
				while((line = indexfile.readLine())!= null){
                                     if(line.contains("*")) {
	                		continue;
	                	}
					count = 0;
                                                 
                                       
	          
					
					StringTokenizer st = new StringTokenizer(line,",");
					while (st.hasMoreTokens()){
					 count+=1;
					 if(count==1)
				    Id = st.nextToken();
					pos = st.nextToken();       
				    }
					sI[sIIndex] = new MIndex();
					sI[sIIndex].setRecPos(pos);
					sI[sIIndex].setId(Id);
					reccount++;
					sIIndex++;
                                        if(sIIndex==110010)
                                        {
                                            break;
                                        }
                                }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} //true tells to append data.
		
		System.out.println("total records" + reccount);
		long endtime =System.nanoTime();
        long totaltime=endtime-starttime;       
        System.out.println(totaltime/1000000+"msec");
		if (reccount==1) { return;}
		sortIndex();
	}
	
	
	
	public void sortIndex() {
		MIndex temp = new MIndex();
		
		for(int i=0; i<reccount; i++)
		    {	
				for(int j=i+1; j<reccount; j++) {
					if(sI[i].getId().compareTo(sI[j].getId())  > 0)
		            {
		                temp.setId(sI[i].getId()); 
				        temp.setRecPos(sI[i].getRecPos());
				
			        	sI[i].setId(sI[j].getId());
			        	sI[i].setRecPos(sI[j].getRecPos());
				
			        	sI[j].setId(temp.getId());
			        	sI[j].setRecPos(temp.getRecPos());
		            }
				}
					
			}	
		
	}
        public void search(){
            System.out.println("Enter the Id to search: ");
					@SuppressWarnings("resource")
					Scanner scanner = new Scanner(System.in);
					String Id = scanner.next();
					System.out.println(reccount);
					int pos = binarySearch(sI, 0, reccount-1, Id);
                                        
					
					if (pos == -1) {
						System.out.println("Record not found in the record file");
						return ;
					}
					
					RandomAccessFile recordfile;
					try {
						recordfile = new RandomAccessFile ("C:\\Users\\hp\\Downloads/hsptl.csv","rw");
						try {
							recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
							String record = recordfile.readLine();
							StringTokenizer st = new StringTokenizer(record,",");
							
							int count = 0;
                                                        
		               	    
		                	while (st.hasMoreTokens()){
		                		     count+=1;
		                  	    	 if(count==1){
		                  	    	 String tmp_Id= st.nextToken();
                                                 if(tmp_Id.contains("*"))
                                                 {
                                                     System.out.println("it has been deleted");
                                                     break;
                                                 }
						System.out.println("Id: "+tmp_Id);
		                  	         this.Id = tmp_Id;
		                  	    	
		                  	          String tmp_Name= st.nextToken();
		                     	      System.out.println("NAME: "+tmp_Name);
		                     	      this.Name = tmp_Name;
		                     	       
		                     	       String tmp_Gender = st.nextToken();
		                     	       System.out.println("gender: "+tmp_Gender);
		                     	       this.Gender = tmp_Gender;
		                  	    	 
		                     	        String tmp_Age = st.nextToken();
		                     	        System.out.println("Age: "+tmp_Age);
		                     	        this.Age = tmp_Age;
		                     	      
		                     	        String tmp_Description = st.nextToken();
		                     	        System.out.println("Description: "+tmp_Description);
		                     	        this.Description= tmp_Description;
		                     	     
		                     	           	 }

		                  	    	 else
		                  	    		 break;
		                       }
		                	
						} 
							catch (NumberFormatException e) {
							
							e.printStackTrace();
						} 
						catch (IOException e) {
							
							e.printStackTrace();
						}
						
						
						}
												
	                	catch (FileNotFoundException e) {
						
						e.printStackTrace();
					}
        }
        int binarySearch(MIndex s[], int l, int r, String Id) {
    	
    	int mid;
    	while (l<=r) {
            
    		mid = (l+r)/2;
    		if(s[mid].getId().compareTo(Id)==0) {return mid;}
    		if(s[mid].getId().compareTo(Id)<0) l = mid + 1;
    		if(s[mid].getId().compareTo(Id)>0) r = mid - 1;
    	}
    	return -1;
    	
    }

  public  void indexing() 
  {
         try{
        RandomAccessFile hey=new RandomAccessFile("C:\\Users\\hp\\Downloads/hsptl.csv","rw");
  
    			

        RandomAccessFile indexfile=new RandomAccessFile("C:\\Users\\hp\\Downloads/index.txt","rw");
        String line;
 long       pos=hey.getFilePointer();
        while((line = hey.readLine())!=null)
        {
            if(line.contains("*")) {
	                		continue;
	                	}

            String  columns[] = line.split(",");
          indexfile.writeBytes(columns[0]  +","+pos+"\n");
            pos=hey.getFilePointer();
        } indexfile.close();
        hey.close();
                
        
         
       
         }
    
    catch(IOException e)
    {
        System.out.println(e);
    }
  }

 public   void delete() throws IOException {
         System.out.println("Enter the Id to delete record ");
					@SuppressWarnings("resource")
					Scanner scanner = new Scanner(System.in);
					String Id = scanner.next();
        int pos = binarySearch(sI, 0, reccount-1, Id);
					
					if (pos == -1) {
						System.out.println("Record not found in the record file");
						return ;
					}
                                        RandomAccessFile recordfile;
                                        
					
						recordfile = new RandomAccessFile ("C:\\Users\\hp\\Downloads/hsptl.csv","rw");
						try {
							recordfile.seek(Long.parseLong(sI[pos].getRecPos()));
                                                        recordfile.writeBytes("*");
                                                        recordfile.close();
                                                
                                                        }    
                                                            
                                             catch (NumberFormatException e) {
							
							e.printStackTrace();
						} 
						catch (IOException e) {
							
							e.printStackTrace();
						}
						
						
						}
							


}
