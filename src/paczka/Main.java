package paczka;

public class Main {

	public static void main(String[] args) {
		DziennikZdarzen dziennikZdarzen = new DziennikZdarzen();
		dziennikZdarzen.addConsoleOutput();
		dziennikZdarzen.alert("sdad");
		dziennikZdarzen.addOutputDestination("mojLog.txt");
		dziennikZdarzen.addOutputDestination("mojLog2.txt");
		dziennikZdarzen.alert("asdad");
		dziennikZdarzen.removeOutputDestination("mojLog.txt");
		//dziennikZdarzen.addOutputDestination("localhost", 8888);
		dziennikZdarzen.alert("dad222");
		dziennikZdarzen.debug("asdsadasd");
		//dziennikZdarzen.removeConsoleOutputDestination();
	}

}
