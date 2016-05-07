package com.rojmal;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.IdGenerator;

@SpringBootApplication
public class webApp
{
//    private static Logger logger = LoggerFactory.getLogger(webApp.class);

    public static void main(String[] args)
    {        
//        logger.debug("main() :: application start");
    	System.out.println("application stated");
        SpringApplication.run(webApp.class, args);
//        logger.debug("main() :: application end");
    	System.out.println("application close");
    	
    	
    }
}
