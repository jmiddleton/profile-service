package au.com.qantas.profile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageResourceConfig {

	@Bean
	 public ResourceBundleMessageSource messageSource() {
	  ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	  source.setBasenames("i18n/messages");  // name of the resource bundle 
	  source.setUseCodeAsDefaultMessage(false);
	  return source;
	 }

}
