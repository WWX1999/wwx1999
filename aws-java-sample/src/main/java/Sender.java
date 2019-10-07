import com.amazonaws.services.sqs.model.CreateQueueRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sender extends JFrame {

    JPanel Jpa=new JPanel();
    JLabel label=new JLabel("Queue name:");
    JTextField Jqueuename=new JTextField("FIFO_1",30);
    JTextArea JMsgArea=new JTextArea("Please input.....\r\n");
    JButton JSend=new JButton("Send");

    public static void main(String[] args) {
        Sender s=new Sender();
    }

    public Sender(){
        JSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean flag = true;
                while (flag) {
                    try {
                        String name = Jqueuename.getText();
                        String queueurl = QueueClient.getSQSurl(name);
                        String msg = JMsgArea.getText();
                        QueueClient.Send(queueurl, msg);
                        JMsgArea.setText("Send a message\r\n");
                        flag=false;
                    } catch (Exception E) {
                    }
                }
            }
        });

        this.setTitle("Sender");
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
