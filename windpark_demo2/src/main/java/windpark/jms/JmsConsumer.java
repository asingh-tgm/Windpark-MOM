package windpark.jms;

import windpark.model.WindengineData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class JmsConsumer implements MessageListener {
    public static ArrayList<WindengineData> data = new ArrayList<>();

    @Override
    @JmsListener(destination = "${active-mq.topic}")
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            WindengineData windengine = (WindengineData)objectMessage.getObject();
            //do additional processing
            data.add(windengine);
            log.info("Received Message: "+ windengine.toString());
        } catch(Exception e) {
            log.error("Received Exception while processing message: "+ e);
        }

    }
    public List<WindengineData> getData(){
        return this.data;
    }
}
