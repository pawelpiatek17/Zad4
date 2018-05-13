package utils;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	StringBuilder message;
	ELogLevel eLogLevel;
	public Log(ELogLevel eLevel, String msg) {
		eLogLevel = eLevel;
		message = new StringBuilder();
		switch(eLogLevel){
			case DEBUG: {
				message.append("DEBUG: ");
				break;
			}
			case INFO: {
				message.append("INFO: ");
				break;
			}
			case WARNING: {
				message.append("WARNING: ");
				break;
			}
			case ALERT: {
				message.append("ALERT: ");
				break;
			}
			default:{
				break;
			}
		}
		message.append(dateFormat.format(new Date()) + ": ");
		message.append(msg);
		message.append(System.getProperty("line.separator"));
	}
	public Log(ELogLevel eLevel, String msg, InetAddress address) {
		eLogLevel = eLevel;
		message = new StringBuilder();
		switch(eLogLevel){
			case DEBUG: {
				message.append("DEBUG: ");
				break;
			}
			case INFO: {
				message.append("INFO: ");
				break;
			}
			case WARNING: {
				message.append("WARNING: ");
				break;
			}
			case ALERT: {
				message.append("ALERT: ");
				break;
			}
			default:{
				break;
			}
		}
		message.append(address.toString() + ": ");
		message.append(dateFormat.format(new Date()) + ": ");
		message.append(msg);
		message.append(System.getProperty("line.separator"));
	}
	public StringBuilder getLog() {
		return message;
	}
	
}
