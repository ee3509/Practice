package panos.MyQueue;

import java.util.Queue;

public class MyDispatcher extends Thread {

	private Long dispatcherDelay=1000l;
	Queue<?> q;

	public MyDispatcher(Queue<?> q) {
		this.q = q;
	}

	@Override
	public void run() {
		super.run();
		Thread thread;
		while (true) {
			synchronized (q) {
				if (!q.isEmpty()) {
					thread = (Thread) q.poll();
					thread.start();
					System.out.println("Dispatcher : Thread Started");
				}
			}
			try {
				sleep(dispatcherDelay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Long getDispatcherDelay() {
		return dispatcherDelay;
	}

	public void setDispatcherDelay(Long dispatcherDelay) {
		this.dispatcherDelay = dispatcherDelay;
	}

}
