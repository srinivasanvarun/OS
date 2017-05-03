public class Consumer implements Runnable{
	CircularBuffer p;
	String name;
	int time;
	public Consumer(CircularBuffer b, String x, int time){
		this.p = b;
		this.name = x;
		this.time = time;
	}
	public synchronized void run(){
		while(true){
			synchronized (this) {
				//System.out.println("$$$$ "+name+" trying to remove an item $$$$");
				p.del(name);
			}
			try{
				Thread.sleep(time);
			}catch(Exception e){
				e.printStackTrace();
			}
			}
	}
}
