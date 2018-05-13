package paczka;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.google.java.contract.Requires;

import utils.IOutputter;

public class FileOutputter implements IOutputter {
	private BufferedWriter out;
	private String filename;
	public FileOutputter(String filename) throws IOException {
		this.filename = filename;
		FileWriter fstream = new FileWriter(filename, true);
		out = new BufferedWriter(fstream);
	}

	@Override
	public void log(String msg) throws IOException {
		synchronized(this){
			out.write(msg);
			out.flush();
		}
		
	}

	@Override
	public void close() {
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFilename() {
		// TODO Auto-generated method stub
		return filename;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
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
		FileOutputter other = (FileOutputter) obj;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		return true;
	}

	
}
