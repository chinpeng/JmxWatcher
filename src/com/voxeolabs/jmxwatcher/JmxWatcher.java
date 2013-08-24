package com.voxeolabs.jmxwatcher;
import java.lang.Thread;

public class JmxWatcher {

	public static void main(String[] args) throws Exception {

		//JMXTest helper = new JMXTest("172.21.0.110:47520", "voxeolabs", "voxeolabs");
		//JMXTest helper = new JMXTest("127.0.0.1:47520", null,null); //"voxeolabs", "voxeolabs");
		//JMXTest helper = new JMXTest(args[0], args[1], args[2]);
          //5000,
		Thread t1 = new Thread(
			new JmxPoller(
					"127.0.0.1:47520",
					new GraphiteLogger("127.0.0.1",12003)
				)
		);
		t1.start();
		//Thread.currentThread();
		//Thread.sleep(5000);
	}
}
