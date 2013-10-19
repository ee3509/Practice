package panos.MyQueue;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static Queue<MyWorker> queue = new LinkedList<MyWorker>();
	
	
	public static void main(String[] args) {
		
		MyLoader myLoader = new MyLoader(queue);
		MyDispatcher dispatcher = new MyDispatcher(queue);
		
		myLoader.setLoaderDelay(2l);
		myLoader.start();
		
		dispatcher.setDispatcherDelay(350l);
		dispatcher.start();
		
	}

}
