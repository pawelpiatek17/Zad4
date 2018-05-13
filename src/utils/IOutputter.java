package utils;

import java.io.IOException;

public interface IOutputter {
	void log(String msg) throws IOException;
	void close();
	boolean equals(Object obj);
	int hashCode();
}
