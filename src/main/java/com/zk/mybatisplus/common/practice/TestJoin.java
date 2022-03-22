package com.zk.mybatisplus.common.practice;

public class TestJoin {
 
	public static void main(String[] args) throws InterruptedException {
		ThreadTest0 t1=new ThreadTest0("A");
		ThreadTest0 t0=new ThreadTest0("B");
		t1.start();

		t0.start();

		t1.join();
	}
}
class ThreadTest0 extends Thread {
	private String name;
	public ThreadTest0(String name){
		this.name=name;
	}
	public void run(){
		for(int i=1;i<=5;i++){
				System.out.println(name+"-"+i);
		}		
	}
}