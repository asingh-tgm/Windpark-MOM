package windpark.controller;

import org.springframework.web.bind.annotation.*;
import windpark.jms.JmsConsumer;
import windpark.jms.JmsProducer;
import windpark.model.WindengineData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@RestController
@Slf4j
public class ProduceMessageController {

    @Autowired
    JmsProducer jmsProducer;

    @PostMapping(value="/api/windengine/01")
    public WindengineData sendMessage(@RequestBody WindengineData windengine) {
        jmsProducer.sendMessage(windengine);
        return windengine;
    }

    @RequestMapping(value="/json")
    public String getJson() {
        return JmsConsumer.data.toString();
    }
}
