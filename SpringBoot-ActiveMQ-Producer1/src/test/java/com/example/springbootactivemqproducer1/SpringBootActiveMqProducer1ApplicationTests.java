package com.example.springbootactivemqproducer1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;

@SpringBootTest
class SpringBootActiveMqProducer1ApplicationTests {


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    void contextLoads() {




    }

}
