package paczka;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import com.google.java.contract.Ensures;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;

import utils.ELogLevel;
import utils.IOutputter;
import utils.Log;

public class DziennikZdarzen {
	private boolean isConsoleOutputEnabled = false;
	private ELogLevel outputLogLevel;
	private ArrayList<IOutputter> outputList;
	
	public DziennikZdarzen() {
		outputLogLevel = ELogLevel.DEBUG;
		outputList = new ArrayList<>();
	}
	@Requires("outputList.size() > 0")
	public void debug(String msg){
		if(outputLogLevel.equals(ELogLevel.DEBUG)){
			passLogToOutputter(ELogLevel.INFO,new Log(ELogLevel.DEBUG,msg));
		}
	}
	@Requires("outputList.size() > 0")
	public void info(String msg){
		if(outputLogLevel.equals(ELogLevel.ALERT) || outputLogLevel.equals(ELogLevel.WARNING) ){
			return;
		}
		passLogToOutputter(ELogLevel.INFO,new Log(ELogLevel.INFO,msg));
	}
	@Requires("outputList.size() > 0")
	public void warning(String msg){
		if(outputLogLevel.equals(ELogLevel.ALERT)){
			return;
		}
		passLogToOutputter(ELogLevel.WARNING,new Log(ELogLevel.WARNING,msg));
	}
	@Requires("outputList.size() > 0")
	public void alert(String msg){
		passLogToOutputter(ELogLevel.ALERT,new Log(ELogLevel.ALERT,msg));
	}
	public void debug(String msg,InetAddress address){
		if(outputLogLevel.equals(ELogLevel.DEBUG)){
			passLogToOutputter(ELogLevel.INFO,new Log(ELogLevel.ALERT,msg,address));
		}
	}
	@Requires("outputList.size() > 0")
	public void info(String msg,InetAddress address){
		if(outputLogLevel.equals(ELogLevel.ALERT) || outputLogLevel.equals(ELogLevel.WARNING) ){
			return;
		}
		passLogToOutputter(ELogLevel.INFO,new Log(ELogLevel.ALERT,msg,address));
	}
	@Requires("outputList.size() > 0")
	public void warning(String msg,InetAddress address){
		if(outputLogLevel.equals(ELogLevel.ALERT)){
			return;
		}
		passLogToOutputter(ELogLevel.WARNING,new Log(ELogLevel.ALERT,msg,address));
	}
	@Requires("outputList.size() > 0")
	public void alert(String msg,InetAddress address){
		if(!outputLogLevel.equals(ELogLevel.ALERT)){
			passLogToOutputter(ELogLevel.ALERT,new Log(ELogLevel.ALERT,msg,address));
		}
		else if(outputLogLevel.equals(ELogLevel.ALERT) && msg.length() > 10){
			passLogToOutputter(ELogLevel.ALERT,new Log(ELogLevel.ALERT,msg,address));
		}
	}
	private void passLogToOutputter(ELogLevel logLevel, Log log){
		for (IOutputter iOutputter : outputList) {
			try {
				iOutputter.log(log.getLog().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public synchronized void addOutputDestination(String filename){
		FileOutputter fileOut = null;
		try {
			fileOut = new FileOutputter(filename);
			if(!findOutputter(fileOut)){
				outputList.add(fileOut);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public synchronized void addOutputDestination(String destinationIp, int port){
		SocketOutputter socketOut;
		try {
			socketOut = new SocketOutputter(destinationIp,port);
			if(!findOutputter(socketOut)){
				outputList.add(socketOut);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addConsoleOutput(){
		if(isConsoleOutputEnabled == true){
			return;
		}
		ConsoleOutputter consoleOut = new ConsoleOutputter();
		outputList.add(consoleOut);
		isConsoleOutputEnabled = true;
	}
	public synchronized void removeOutputDestination(String filename){
		IOutputter pom = null;
		for (IOutputter iOutputter : outputList) {
			if(iOutputter.getClass() == FileOutputter.class && ((FileOutputter)iOutputter).getFilename().equals(filename)){
				iOutputter.close();
				pom = iOutputter;
			}
		}
		outputList.remove(pom);
	}
	public synchronized void removeOutputDestination(String destinationIp, int port){
		IOutputter pom = null;
		for (IOutputter iOutputter : outputList) {
			if(iOutputter.getClass() == SocketOutputter.class && ((SocketOutputter)iOutputter).getDestinationIp().toString().equals(destinationIp) && ((SocketOutputter)iOutputter).getPort() == port){
				iOutputter.close();
				pom = iOutputter;
			}
		}
		outputList.remove(pom);
	}
	public synchronized void removeConsoleOutputDestination(){
		IOutputter pom = null;
		for (IOutputter iOutputter : outputList) {
			if(iOutputter.getClass() == ConsoleOutputter.class){
				iOutputter.close();
				pom = iOutputter;
				isConsoleOutputEnabled = false;
			}
		}
		outputList.remove(pom);
	}
	@Ensures(value = { "outputList.size() == 0" })
	public synchronized void removeAllOutputDestinations(){
		for (IOutputter iOutputter : outputList) {
			iOutputter.close();
		}
		outputList.clear();
		isConsoleOutputEnabled = false;
	}
	public ELogLevel getOutputLogLevel() {
		return outputLogLevel;
	}
	public synchronized void setOutputLogLevel(ELogLevel outputLogLevel) {
		this.outputLogLevel = outputLogLevel;
	}
	private boolean findOutputter(IOutputter outputter){
		for (IOutputter iOutputter : outputList) {
			if(iOutputter.equals(outputter)){
				return true;
			}
		}
		return false;
	}
}
