import java.util.*;
public class Producer implements Runnable {
	CircularBuffer p;
	int time;
	public Producer(CircularBuffer b, int time){
		this.p = b;
		this.time = time;
	}
	public synchronized void run(){
		Random r = new Random();
		int x;
		while(true){
			synchronized (this) {
				x=r.nextInt(100);
				System.out.println("###### Producer trying to add ######");
				p.add(x);
			}
			try{
				Thread.sleep(time);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
