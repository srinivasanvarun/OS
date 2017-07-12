/*
Team Members:
-----------------------------------------------------
Varun Srinivasan - W1351349 - v1srinivasan@scu.edu
Sharon Subathran - W1358689 - ssubathran@scu.edu
*/

import java.util.Arrays;
import java.util.Scanner;

public class DiskArmSchedule {
	public static int subtract(int x, int y){
		return Math.abs(y-x);
	}
	public static int ScheduleFCFS(int x, int n[]){
		int sum=subtract(x,n[0]);
		System.out.println("---- First Come First Serve Scheduling ----");
		for(int i=0;i<n.length-1;i++){
			System.out.println("Reading track "+n[i]);
			sum+=subtract(n[i],n[i+1]);
			subtract(n[i],n[i+1]);
		}
		System.out.println("Reading track "+n[n.length-1]);
		return sum;
	}
	public static int nearestSSTF(int x, int[] n){
		int h=1000;
		int y=0;
		for(int i=0;i<n.length;i++){
			if(Math.abs(n[i]-x)<h){
				h=Math.abs(n[i]-x);
				y=n[i];
			}
		}
		return y;
	}
	public static int[] removeElementSSTF(int x, int[] n){
		int[] m;
		if(n.length>1){
			m = new int[n.length-1];
			int a=0;
			for(int i=0;i<n.length;i++){
				if(n[i]!=x){
					m[a]=n[i];
					a++;
				}
			}
		}
		else{
			m = new int[1];
			int a=0;
			for(int i=0;i<n.length;i++){
				if(n[i]!=x){
					m[a]=n[i];
					a++;
				}
			}
		}
		return m;
	}
	public static int ScheduleSSF(int x, int[] n){
		int nearest = 0;
		System.out.println("---- Shortest Seek Time First Scheduling ----");
		nearest = nearestSSTF(x,n);
		System.out.println("Reading track "+nearest);
		int sum = Math.abs(nearest-x);
		x=nearest;
		int[] f = removeElementSSTF(nearest,n);
		for(int i=0;i<n.length-1;i++){
			nearest = nearestSSTF(x,f);
			System.out.println("Reading track "+nearest);
			sum += Math.abs(nearest-x);
			x=nearest;
			f = removeElementSSTF(nearest,f);
		}
		return sum;
	}
	
	public static int countGreater(int x, int n[]) {
		int count = 0;
		for (int i = 0; i< n.length; i++){
			if(n[i] > x)
				count++;
		}
		return count;
	}
	
	
	public static int countLesser(int x, int n[]) {
		int count = 0;
		for (int i = 0; i< n.length; i++){
			if(n[i] < x)
				count++;
		}
		return count;
	}
	
	
	public static int ScheduleElevator(int x, int n[]){
		System.out.println("---- Elevator/SCAN Scheduling ----");
		Arrays.sort(n);
		int forward[] = new int[countGreater(x,n)];
		int backward[] = new int[countLesser(x,n)];
		int b=0, f=0, sum = 0;
		for (int i = 0; i<n.length; i++){
			if(n[i] > x) {
				forward[f] = n[i] ;
				f++;
			}else{
				backward[b] = n[i] ;
				b++;
			}
		}
		sum = subtract(x, backward[backward.length-1]);
		for (int i= backward.length-1; i>0; i-- ) {
			sum += subtract(backward[i], backward[i-1]);
			System.out.println("Reading track "+backward[i]);
		}
		System.out.println("Reading track "+backward[0]);
		System.out.println("Reading track 0");
		sum += subtract(backward[0], 0) + subtract(forward[0], 0);
		System.out.println("Reading track "+forward[0]);
		for (int i=0; i< forward.length-1; i++ ) {
			sum += subtract(forward[i+1], forward[i]);
			System.out.println("Reading track "+forward[i+1]);
		}
		
		return sum;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int initpos = 50;
		System.out.println("Enter the sequence of disk posiitions to be read:");
		String inp = sc.nextLine();
		String[] x = inp.split("\\s");
		int size = x.length;
		System.out.println("Number of read positions: "+size);
		int[] pos = new int[size];
		for(int i=0;i<size;i++){
			pos[i] = Integer.parseInt(x[i]);
		}
		System.out.println("The current position of the arm is: 50");
		//FCFS scheduling
		System.out.println("FCFS Total Distance: "+ScheduleFCFS(initpos,pos));
		
		//SSF scheduling
		System.out.println("SSTF Total Distance: "+ScheduleSSF(initpos,pos));
		
		//ELEVATOR scheduling
		System.out.println("SCAN Total Distance: "+ScheduleElevator(initpos,pos));
		
		sc.close();
	}
}