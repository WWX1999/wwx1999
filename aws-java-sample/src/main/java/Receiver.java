import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Receiver extends JFrame {

    JPanel Jpa=new JPanel();
    JLabel label=new JLabel("Queue name:");
    JTextField Jqueuename=new JTextField("FIFO_1",30);
    JTextArea JMsgArea=new JTextArea("等待接收....\r\n");
    JButton JSend=new JButton("Receive");

    public static void main(String[] args) {
        Receiver R=new Receiver();
    }

    public Receiver(){
        JSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag=true;
                while(flag) {
                    try {
                        String name = Jqueuename.getText();
                        String queueurl = QueueClient.getSQSurl(name);
                        //String msg=JMsgArea.getText();
                        String msg = QueueClient.Receive(queueurl);
                        JMsgArea.setText(msg);
                        flag=false;
                    } catch (Exception E) {
                    }
                }
            }
        });

        this.setTitle("Receiver");
        this.setSize(500,300);
        this.setLocation(200,200);
        this.setLayout(new BorderLayout(5,5));
        Jpa.setLayout(new BorderLayout(5,5));
        Jpa.add(label,BorderLayout.WEST);
        Jpa.add(Jqueuename,BorderLayout.CENTER);
        Jpa.add(JSend,BorderLayout.EAST);
        this.add(Jpa,BorderLayout.NORTH);
        this.add(JMsgArea);
        this.setVisible(true);
    }
}
