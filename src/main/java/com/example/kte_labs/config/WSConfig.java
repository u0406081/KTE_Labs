package com.example.kte_labs.config;

import com.example.kte_labs.service.MainService;
import com.example.kte_labs.service.SOAPServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;

@Configuration
public class WSConfig {
    @Autowired
    private Bus bus;

    @Autowired
    MainService service;

    @Bean
    public Endpoint soapEndpoint() {
        SOAPServiceImpl impl = new SOAPServiceImpl(service);
        EndpointImpl endpoint = new EndpointImpl(bus, impl);
        endpoint.publish("/SoapService");
        return endpoint;
    }
}
