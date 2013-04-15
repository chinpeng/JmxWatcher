package com.voxeolabs.test;


import java.lang.Thread;

public class JMXTest {

	
	private static JmxPoller poller;
	private static Runnable JmxPoller;

	public static void main(String[] args) throws Exception {
		//GraphiteLogger GL = new GraphiteLogger("192.168.56.4",2003);
		//GL.logToGraphite("prism.memory", 100L);
		
//		JmxPoller poller = new JmxPoller("127.0.0.1:47520", null,null);
//		System.out.println("Licensed media license port:"+poller.getLicensedMediaPorts());
//		System.out.println("Active media license port:"+poller.getActiveMediaPorts());
		
		//JmxWatcher watcher = new JmxWatcher("127.0.0.1:47520", null,null);
		
		//JMXTest helper = new JMXTest("172.21.0.110:47520", "voxeolabs", "voxeolabs");
		//  JMXTest helper = new JMXTest("127.0.0.1:47520", null,null); //"voxeolabs", "voxeolabs");
		//JMXTest helper = new JMXTest(args[0], args[1], args[2]);
		//


		Thread t1 = new Thread(
				poller = new JmxPoller("127.0.0.1:47520", null,null);
				System.out.println("Licensed media license port:"+poller.getLicensedMediaPorts());
				System.out.println("Active media license port:"+poller.getActiveMediaPorts());
				);
				//new GraphiteFactory("127.0.0.1",9001,3000)
				//int s = gf.sendMetric();
				//System.out.println("=> " + s);
		
		
		t1.start();
		//Thread.currentThread();
		//Thread.sleep(5000);
	}
}

