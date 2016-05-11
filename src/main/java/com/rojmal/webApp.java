package com.rojmal;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class webApp
{
//    private static Logger logger = LoggerFactory.getLogger(webApp.class);

    public static void main(String[] args)
    {        
    	
    	System.out.println("application start");

//        logger.debug("main() :: application start");
    	System.out.println("application stated");
        SpringApplication.run(webApp.class, args);
//        logger.debug("main() :: application end");
    	System.out.println("application close");
    
    	
    }
}
