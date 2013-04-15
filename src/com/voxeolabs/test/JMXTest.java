package com.voxeolabs.test;
import java.lang.Thread;

public class JMXTest {

	public static void main(String[] args) throws Exception {

		//JMXTest helper = new JMXTest("172.21.0.110:47520", "voxeolabs", "voxeolabs");
		//JMXTest helper = new JMXTest("127.0.0.1:47520", null,null); //"voxeolabs", "voxeolabs");
		//JMXTest helper = new JMXTest(args[0], args[1], args[2]);

		Thread t1 = new Thread(
			new JmxPoller(
					"10.6.1.203:47520",
					"xxxx",
					"xxxxxx",
					5000,
					new GraphiteLogger("192.168.56.4",2003)
				)
		);
		t1.start();
		//Thread.currentThread();
		//Thread.sleep(5000);
	}
}