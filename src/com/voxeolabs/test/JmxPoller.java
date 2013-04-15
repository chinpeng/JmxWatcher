package com.voxeolabs.test;

import java.io.IOException;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public class JmxPoller extends JmxConnector implements Runnable{

	public JmxPoller(String jmxServer, String username, String password) throws Exception {
		super(jmxServer, username, password);
		// TODO Auto-generated constructor stub
	}

	public int getActiveMediaPorts() throws MalformedObjectNameException, NullPointerException,
	InstanceNotFoundException, MBeanException, ReflectionException, IOException {
		ObjectName mediaObj = new ObjectName("com.micromethod.sipmethod:type=server.module,name=mediacontrol");
		Object port = msc.invoke(mediaObj, "getActiveMRCPPorts", new Object[0], new String[0]);
		return Integer.parseInt(port.toString());
	}

	public int getLicensedMediaPorts() throws MalformedObjectNameException, NullPointerException,
	InstanceNotFoundException, MBeanException, ReflectionException, IOException {
		ObjectName mediaObj = new ObjectName("com.micromethod.sipmethod:type=server.module,name=mediacontrol");
		Object port = msc.invoke(mediaObj, "getLicensedMRCPPorts", new Object[0], new String[0]);
		return Integer.parseInt(port.toString());
	}

	@Override
	public void run() {
		while(true){
			try{
				Thread.sleep( 3000 );
				System.out.println("test 1");
			} catch ( InterruptedException exception ){
				System.out.println("SHIT -> " + exception);
		}
	}
		
	}
}
