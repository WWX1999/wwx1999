import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;


import java.util.List;


public class QueueClient {


    public static void main(String[] args) {

    }

    private static AmazonSQS sqs;
    static {
        sqs=AmazonSQSClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    }

    private QueueClient(){
    }

    public static String getSQSurl(String Queuename){
        return sqs.getQueueUrl(Queuename).getQueueUrl();
    }

    public static String CreateMyQueue(String Queuename){
        System.out.println("Create queue......");
        CreateQueueRequest request=new CreateQueueRequest(Queuename);
        System.out.println("Create successfully");
        return sqs.createQueue(request).getQueueUrl();
    }

    public static void DeleteQueue(String QueueUrl){
        System.out.println("Delete queue.....");
        sqs.deleteQueue(QueueUrl);
        System.out.println("Delete successfully");
    }

    public static void Send(String QueueUrl,String Message_){
        System.out.println("Sending message ...... " + QueueUrl);
        // 声明一个发送消息的请求
        SendMessageRequest request = new SendMessageRequest();
        // 指定要将消息发送到哪个队列
        request.withQueueUrl(QueueUrl);
        // 设置消息内容
        request.withMessageBody(Message_);
        // 发送消息
        sqs.sendMessage(request);
    }

    public static String Receive(String QueueUrl){
        System.out.println("Receiving message.......");
        ReceiveMessageRequest request=new ReceiveMessageRequest();
        request.withQueueUrl(QueueUrl);
        request.setMaxNumberOfMessages(5);
        request.withWaitTimeSeconds(10);
        List<Message> msgList=sqs.receiveMessage(request).getMessages();
        String re="";
        for(Message msg:msgList){
            System.out.println("get a message:");
            System.out.println(msg.getBody());
            re=re+"get a message:"+msg.getBody()+"\r\n";
            sqs.deleteMessage(QueueUrl,msg.getReceiptHandle());
        }
        return re;
    }
}

