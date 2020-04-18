package config;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import auth.AuthenticationFilter;
import config.GsonMessageConfig;

public class HospitalResourceConfig extends ResourceConfig {
	
	public HospitalResourceConfig() {
	    packages("config");
	    register(LoggingFilter.class);
	    register(GsonMessageConfig.class);
	    register(AuthenticationFilter.class);
	  }

}
