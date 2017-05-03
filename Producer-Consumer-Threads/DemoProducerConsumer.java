
public class DemoProducerConsumer {
	public static void main(String a[]) throws InterruptedException{
		CircularBuffer c = new CircularBuffer(5);
		Thread tp = new Thread(new Producer(c,1000));
		Thread tc1 = new Thread(new Consumer(c,"Consumer 1",2500));
		Thread tc2 = new Thread(new Consumer(c,"Consumer 2",2500));
		
		tp.start();
		tc1.start();
		tc2.start();
		
		tc1.join();
		tc2.join();
	}
}
