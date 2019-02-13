package ca.mcgill.ecse428.dietplanner;
import junit.runner.Version;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DietPlannersApplication {

	public static void main(String[] args) {
		//System.out.println("JUnit version is: " + Version.id());
		SpringApplication.run(DietPlannersApplication.class, args);
		

		
	}

}

