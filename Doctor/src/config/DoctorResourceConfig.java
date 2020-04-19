package config;

import auth.AuthenticationFilter;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class DoctorResourceConfig extends ResourceConfig {
	public DoctorResourceConfig() {
		packages("config");
		register(LoggingFilter.class);
		register(GsonMessageConfig.class);
		register(AuthenticationFilter.class);
	}
}
