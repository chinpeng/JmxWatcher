package com.voxeolabs.jmxwatcher;

import java.util.Hashtable;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxConnector {

	MBeanServerConnection msc = null;
	
	public JmxConnector(String jmxServer, String username, String password) throws Exception {
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + jmxServer + "/rmi");
		Map<String, String[]> env = new Hashtable<String, String[]>();
		if (username != null && username.length() > 0) {
			String[] creds = {username, password};
			env.put(JMXConnector.CREDENTIALS, creds);
			msc = JMXConnectorFactory.connect(url, env).getMBeanServerConnection();
		}
		else {
			msc = JMXConnectorFactory.connect(url, null).getMBeanServerConnection();
		}
	}

}
