package paczka;

import utils.IOutputter;

public class ConsoleOutputter implements IOutputter {

	@Override
	public void log(String msg) {
		System.out.println(msg);
	}

	@Override
	public void close() {
		return;
	}

}
