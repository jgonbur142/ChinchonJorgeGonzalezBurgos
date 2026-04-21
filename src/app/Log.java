package app;

import java.util.ArrayList;
import java.util.List;

public class Log {

	private static Log instance;
	private List<String> log = new ArrayList<>();
	
	public static Log getInstance() {
		if (instance == null) {
			instance = new Log();
		}
		return instance;
	}
	
	public List<String> getLog(){
		return log;
	}
	
	public void addLog(String mensaje) {
		log.add(mensaje);
	}
	
	public void showCompleteLog() {
		for (int i = 1;i<=log.size();i++) {
			System.out.printf("[Log nº%d] %s\n",i,log);
		}
	}
	
	public void showLastLog() {
		System.out.println(log.getLast());
	}
	
}
