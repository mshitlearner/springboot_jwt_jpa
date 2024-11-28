package in.mshitlearner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class SpringbootJwtJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJwtJpaApplication.class, args);
		
		//BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		//System.out.println(enc.encode("Password@1"));
		
	}

}
