package panos.MyQueue;


public class MyWorker extends Thread{

	@Override
	public void run() {
		super.run();
		
		try {
			sleep((long) (Math.random()*1000));
			System.out.println("Worker Done");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
