package com.voxeolabs.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class GraphiteNotification {

		private int graphitePort = 9001;
		private String graphiteHost = "127.0.0.1";
		
		public GraphiteNotification(String host,int port) throws ClientProtocolException, IllegalStateException, IOException{
			 setGraphiteHost(host);
			 setGraphitePort(port);
		}

		public int sendMetric() throws ClientProtocolException, IOException, IllegalStateException {
			 	int r = -1;
				HttpClient httpclient = new DefaultHttpClient();
				HttpGet httpget = new HttpGet("http://" + graphiteHost + ":" + graphitePort + "/foo/" + getTimeStamp());
				HttpResponse response = httpclient.execute(httpget);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					try {
						r = response.getStatusLine().getStatusCode();
					} finally {
						instream.close();
					}
				}
			return r;
		 }

		 // Getters and Setters

		 public void setGraphitePort(int port){
			 this.graphitePort = port;
		 }
		
		 public int getGraphitePort(){
			 return this.graphitePort;
		 }
		 
		 public String setGraphiteHost(String host){
			return this.graphiteHost= host;
		 }

		 public String getGraphiteHost(){
			return this.graphiteHost;
		 }
		 
		 private long getTimeStamp(){
			 return (new Date()).getTime();
		 }
	}

