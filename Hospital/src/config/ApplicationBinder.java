package config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import web.UserEndpoint;

public class ApplicationBinder extends AbstractBinder {
	@Override
	protected void configure() {
		bind(UserEndpoint.class).to(UserEndpoint.class);
	}
}
