package com.voxeolabs.test;

import java.io.IOException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public class JmxPoller extends JmxConnector implements Runnable{
	
	public int sleepTime = 3000;
	private GraphiteLogger GraphiteLogger;
	
	public JmxPoller(String jmxServer, String username, String password, int sleepTime) throws Exception {
		super(jmxServer, username, password);
		setSleepTime(sleepTime);
	}

	public JmxPoller(String jmxServer, String username, String password, int sleepTime, GraphiteLogger GraphiteLogger) throws Exception {
		super(jmxServer, username, password);
		setSleepTime(sleepTime);
		setGraphiteLogger(GraphiteLogger);
	}
	
	public JmxPoller(String jmxServer, String username, String password) throws Exception {
		super(jmxServer, username, password);
		setSleepTime(30000);
	}
	
	private void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	private void setGraphiteLogger(GraphiteLogger gl){
		this.GraphiteLogger = gl;
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
	
	public int getActiveAppSession() throws MalformedObjectNameException, NullPointerException,
	InstanceNotFoundException, MBeanException, ReflectionException, IOException {
		ObjectName mediaObj = new ObjectName("com.micromethod.sipmethod:type=server.service,name=sip");
		Object port = msc.invoke(mediaObj, "getActiveAppSession", new Object[0], new String[0]);
		return Integer.parseInt(port.toString());
	}
	

	public int getActiveSession() throws MalformedObjectNameException, NullPointerException,
	InstanceNotFoundException, MBeanException, ReflectionException, IOException {
		ObjectName mediaObj = new ObjectName("com.micromethod.sipmethod:type=server.service,name=sip");
		Object port = msc.invoke(mediaObj, "getActiveSession", new Object[0], new String[0]);
		return Integer.parseInt(port.toString());
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep( this.sleepTime );
				System.out.println("Licensed media license port:"+this.getLicensedMediaPorts());
				this.GraphiteLogger.logToGraphite("licensedMediaPorts", this.getLicensedMediaPorts());
				this.GraphiteLogger.logToGraphite("activeMediaPorts", this.getActiveMediaPorts());
				this.GraphiteLogger.logToGraphite("activeAppSession", this.getActiveAppSession());
				this.GraphiteLogger.logToGraphite("activeSession", this.getActiveSession());
				
				
			} catch ( InterruptedException exception ){
				System.out.println("SHIT -> " + exception);
			} catch (MalformedObjectNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MBeanException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReflectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
