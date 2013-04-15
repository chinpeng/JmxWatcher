package com.voxeolabs.test;

public class GraphiteFactory implements Runnable {
	public GraphiteFactory() {
	}
	@Override
	public void run() {
		while(true){
			try{
				Thread.sleep( this.sleepTime );
				System.out.println("test 1");
			} catch ( InterruptedException exception ){
				System.out.println("SHIT -> " + exception);
		}
	}
		
	}
	 
}