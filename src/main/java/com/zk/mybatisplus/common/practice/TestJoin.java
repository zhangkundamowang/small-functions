package com.zk.mybatisplus.common.practice;

public class TestJoin {
 
	public static void main(String[] args) throws InterruptedException {
		ThreadTest2 t1=new ThreadTest2("A");
		ThreadTest2 t2=new ThreadTest2("B");
		t1.start();

		t2.start();

		t1.join();
	}
}
class ThreadTest2 extends Thread {
	private String name;
	public ThreadTest2(String name){
		this.name=name;
	}
	public void run(){
		for(int i=1;i<=5;i++){
				System.out.println(name+"-"+i);
		}		
	}
}