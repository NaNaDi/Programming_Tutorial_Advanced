import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SampleClass extends Thread{
	
	private int sum1 = 0;
	private int sum2 = 0;
	
	Runnable runner1 = new Runnable(){

		public void run() {
			// TODO Auto-generated method stub
			for(int i = 0; i<5; i++){
				System.out.println(Thread.currentThread().getName() + " iteration no. " + i);
			}
		}
		
	};
	
	Runnable runner2 = new Runnable(){

		public void run() {
			// TODO Auto-generated method stub
			for(int i = 0; i<5; i++){
				System.out.println(Thread.currentThread().getName() + " iteration no. " + i);
			}
		}
		
	};

	
	public void executeRunnables(){
		//ThreadPools helps to execute the threads parallel, FixedThreadPool specifies how many threads run in parallel
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(runner1);
		executor.submit(runner2);
		executor.shutdown();
	}
	
	public void executeThreads(){
		 System.out.println(Thread.currentThread().getName()); //main Thread
		    for(int i=0; i<10; i++){
		      new Thread("" + i){
		        public void run(){
		          System.out.println("Thread: " + getName() + " running");
		        }
		      }.start();
		    }
	}
	
	public void joinThreads(){
		final int[] arr = {1,2,3,4,5,6};
		Thread t1 = new Thread("first half"){
			public void run(){
				System.out.println("Thread " + Thread.currentThread().getName() + " started.");
				int temp = 0;
				for(int i = 0; i<=arr.length/2; i++){
					temp += arr[i];
				}
				sum1 = temp;
			}
		};
		Thread t2 = new Thread("second half"){
			public void run(){
				System.out.println("Thread " + Thread.currentThread().getName() + " started.");
				int temp = 0;
				for(int i = arr.length/2 + 1; i<arr.length; i++){
					temp += arr[i];
				}
				sum2 = temp;
			}
		};
		Thread t3 = new Thread("addition"){
			public void run(){
				System.out.println("Thread " + Thread.currentThread().getName() + " started.");
				System.out.println("sum: " + (sum1 + sum2));
			}
		};
		
		t1.start();
		t2.start();
		
		//makes sure that t3 will be executed AFTER t1 and t2 finished.
		try{
			t1.join();
			t2.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		t3.start();
	}

}
