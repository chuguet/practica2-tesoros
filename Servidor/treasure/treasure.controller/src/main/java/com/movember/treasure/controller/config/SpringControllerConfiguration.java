package com.movember.treasure.controller.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import com.movember.treasure.model.config.SpringModelConfiguration;

/**
 * Configuración de Spring por anotaciones se importa la configuración de spring
 * del modelo Se escanean los paquetes para poder inyectarlos en el contenedor
 * de spring.
 */
@Configuration
@ComponentScan({ "com.movember.quizz.controller" })
@Import(value = { SpringModelConfiguration.class })
public class SpringControllerConfiguration {
	/**
	 * Bean que especifica los tipos de petición entre la vista y el
	 * controlador.
	 * 
	 * @return the mapping jackson json view
	 */
	@Bean
	public MappingJacksonJsonView mappingJacksonJsonView() {
		return new MappingJacksonJsonView();
	}

	/**
	 * Bean que especifica los tipos de petición entre la vista y el
	 * controlador.
	 * 
	 * @return the content negotiating view resolver
	 */
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
		final ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
		contentNegotiatingViewResolver.setDefaultContentType(MediaType.APPLICATION_JSON);
		final ArrayList defaultViews = new ArrayList();
		defaultViews.add(mappingJacksonJsonView());
		contentNegotiatingViewResolver.setDefaultViews(defaultViews);
		return contentNegotiatingViewResolver;
	}
}
