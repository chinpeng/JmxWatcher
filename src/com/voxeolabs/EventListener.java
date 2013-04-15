package com.voxeolabs.test;

import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeDataSupport;

public class EventListener implements NotificationListener {

		@Override
		public void handleNotification(Notification notification, Object handback) {
			System.out.println("Receiving notification...");
			System.out.println("Type:" + notification.getType());
			System.out.println("Message:" + notification.getMessage());
			System.out.println("Data: " + notification.getUserData());
			System.out.println("==> " + ((CompositeDataSupport)notification.getUserData()).get("nodeIp"));
		}
	}
