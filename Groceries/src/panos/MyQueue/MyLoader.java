package panos.MyQueue;

import java.util.Queue;

public class MyLoader extends Thread {

	private Long loaderDelay = 800L;
	private int max_Qsize = 5;
	
	public Long getLoaderDelay() {
		return loaderDelay;
	}

	public void setLoaderDelay(Long loaderDelay) {
		this.loaderDelay = loaderDelay;
	}

	public int getMax_Qsize() {
		return max_Qsize;
	}

	public void setMax_Qsize(int max_Qsize) {
		this.max_Qsize = max_Qsize;
	}

	private Queue<MyWorker> q;

	public MyLoader(Queue<MyWorker> q) {
		this.q = q;
	}

	@Override
	public void run() {
		super.run();
		while (true) {
			synchronized (q) {
				if (q.size() < max_Qsize) {
					q.add(new MyWorker());
				}
			}
			try {
				sleep(loaderDelay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Task Loaded");
			System.out.println("Queue size = " + q.size());
		}
	}
}
