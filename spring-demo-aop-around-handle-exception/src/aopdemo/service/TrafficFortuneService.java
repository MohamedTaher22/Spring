package aopdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

@Component
public class TrafficFortuneService {

	public String getFrotune() {
		
		// simulate a delay
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// return a fortune
		return "Excepect heavy traffic this morning";
	}

	public String getFrotune(boolean tripWire) {
		
		if(tripWire) {
			throw new RuntimeException("Ring road is closed!");
		}
				
		return getFrotune();
	}
}
