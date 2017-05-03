public class  CircularBuffer
{
	int Q[] = new int[100];
	int n, front, rear;
	boolean full=false,empty=false;
	public CircularBuffer(int nn)
	{
		n=nn+1;
		front = rear = 0;
	}
	public synchronized void add(int v)
	{
		if(full){
			try{wait();}catch(Exception e){e.printStackTrace();}
		}
		if((rear+1) % n != front)
		{
			synchronized (this) {
				rear = (rear+1)%n;
				Q[rear] = v;
				System.out.print("After adding "+v+" the buffer looks like ");
				//this.disp();
				int i;
				if(front != rear)
				{
					i = (front +1) %n;
					System.out.print("[");
					while(i!=rear)
					{
						System.out.print(Q[i]+" ");
						i = (i+1) % n;
					}
					System.out.println(Q[rear]+"]");
				}
				else
					System.out.println("it is empty !");
				notify();
			}
		}
		else{
			full = true;
			System.out.println("Cannot add "+v+". Buffer is full!");
			notify();
		}
	}
	public synchronized void del(String name)
	{
		int v;
		if(empty){
			try{wait();}catch(Exception e){e.printStackTrace();}
		}
		if(front!=rear)
		{
			synchronized (this) {
				front = (front+1)%n;
				v = Q[front];
				System.out.print("After deleting "+v+" by $$$ "+name+" $$$ the buffer looks like ");
				//this.disp();
				int i;
				if(front != rear)
				{
					i = (front +1) %n;
					System.out.print("[");
					while(i!=rear)
					{
						System.out.print(Q[i]+" ");
						i = (i+1) % n;
					}
					System.out.println(Q[rear]+"]");
				}
				else
					System.out.println("it's empty !");
				full=false;
				notifyAll();
			}
		}
		else{
			empty = true;
			notifyAll();
			System.out.println("Cannot delete by $$$ "+name+" $$$. Buffer is empty!");
		}
	}
	public void disp()
	{
		int i;
		if(front != rear)
		{
			i = (front +1) %n;
			System.out.print("[");
			while(i!=rear)
			{
				System.out.print(Q[i]+" ");
				i = (i+1) % n;
			}
			System.out.println(Q[rear]+"]");
		}
		else
			System.out.println("Buffer is empty !");
	}
}