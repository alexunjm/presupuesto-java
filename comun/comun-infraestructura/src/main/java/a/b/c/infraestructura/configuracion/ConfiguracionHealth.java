package a.b.c.infraestructura.configuracion;

import javax.sql.DataSource;

import a.b.c.infraestructura.actuator.DataSourceHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracionHealth {
	
	
	private final  DataSource dataSource;
	
	
	public ConfiguracionHealth(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Bean
    public DataSourceHealthIndicator dataSourceHealthIndicator() {
		return new DataSourceHealthIndicator(this.dataSource);
       
    } 

}
