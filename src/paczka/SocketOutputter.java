package paczka;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import utils.IOutputter;

public class SocketOutputter implements IOutputter {
	private int port;
	private InetAddress address;
	private PrintWriter out;
	private Socket socket;
	public SocketOutputter(String destinationIp, int port) throws IOException,UnknownHostException {
		address = InetAddress.getByName(destinationIp);
		Socket socket = new Socket(destinationIp, port);
		out=new PrintWriter(socket.getOutputStream(), true);
	}

	@Override
	public void log(String msg) {
		synchronized(this){
			out.println(msg);
		}

	}

	@Override
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();

	}

	public InetAddress getDestinationIp() {
		// TODO Auto-generated method stub
		return address;
	}

	public int getPort() {
		// TODO Auto-generated method stub
		return port;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + port;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocketOutputter other = (SocketOutputter) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (port != other.port)
			return false;
		return true;
	}
	
}
