package com.voxeolabs.jmxwatcher;

import java.io.IOException;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public class JmxNotification extends JmxConnector{

	MBeanServerConnection msc = null;

	public JmxNotification(String jmxServer, String username, String password)
			throws Exception {
		super(jmxServer, username, password);
		// TODO Auto-generated constructor stub
	}

	/**
	 * https://voxeolabs.atlassian.net/browse/prism-237
	 * 
	 *   <MediaControl> 
	 *      <MS uri="mrcp://127.0.0.1:10074/?ctrlport=10099"> 
	 *          <LicenseAlert threshold="0.86" release="0.75" interval="10"/> 
	 *      </MS> 
	 *    </MediaControl>
	 * 
	 * @throws MalformedObjectNameException
	 * @throws NullPointerException
	 * @throws InstanceNotFoundException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws IOException
	 */
	public void listenToLiscenAlart() throws MalformedObjectNameException, NullPointerException,
	InstanceNotFoundException, MBeanException, ReflectionException, IOException {
		ObjectName obj = new ObjectName("com.micromethod.sipmethod:type=server.module,name=mediacontrol");
		msc.addNotificationListener(obj, new EventListener(), null, null);
	}

	/**
	 * https://voxeolabs.atlassian.net/browse/TROPO-639
	 * 
	 * TropoRoutingInfoMBean will send out different notifications when meet the
	 * following conditions: 
	 * 
	 * 1. When a run-time node become inactive; 
	 * 2. When a run-time node is back to active; 
	 * 3. When all run-time nodes are dead.
	 * 
	 * @throws MalformedObjectNameException
	 * @throws NullPointerException
	 * @throws InstanceNotFoundException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws IOException
	 */
	public void listenToKeepAliveAlert() throws MalformedObjectNameException, NullPointerException,
	InstanceNotFoundException, MBeanException, ReflectionException, IOException {
		//ObjectName mediaObj = new ObjectName("com.voxeo.tropo:type=troporoutinginfo");
		ObjectName obj = new ObjectName("com.voxeo.tropo:type=troporoutinginfo,name=gatewayServerRouting");
		msc.addNotificationListener(obj, new EventListener(), null, null);
	}
	
	//helper.listenToLiscenAlart(); // you should be able to get the notification if there is any
	//helper.listenToKeepAliveAlert();
}
