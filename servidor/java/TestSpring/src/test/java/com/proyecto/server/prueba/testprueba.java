package com.proyecto.server.prueba;


import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.proyecto.server.Dto.prueba;


//public class testprueba extends CamelTestSupport{

public class testprueba extends CamelSpringTestSupport{ 	
	
	private static final String PROPERTIES_FILE_DIR = "src/test/resources/";
	
	    @Test
	    public  void testP() throws Exception {
			// TODO Auto-generated method stub	    	
	    	context.getRouteDefinition("myRoute").adviceWith(context, new AdviceWithRouteBuilder() {
				@Override
				public void configure() throws Exception {
					replaceFromWith("direct:inicio"); 
					weaveById("log_l").after().log("jodase");
					weaveAddLast().to("mock://endRoute");
					
				}
			});
			context.start();
			prueba request = new prueba();
			request.setIdApp("id");
			
			//ProducerTemplate producerT = context.createProducerTemplate();
			//producerT.sendBody("direct:inicio","");
			
			template.sendBody("direct:inicio", request);
		}
	    
	    @Override
	    protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("spring/camel-context.xml");
	    }
	    
}
