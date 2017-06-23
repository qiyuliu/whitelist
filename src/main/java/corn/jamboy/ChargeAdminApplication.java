package corn.jamboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"corn.jamboy.service.impl","corn.jamboy.dao","corn.jamboy.contorller"})
@EnableAutoConfiguration
public class ChargeAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChargeAdminApplication.class, args);
	}
}
