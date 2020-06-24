import jdk.internal.org.jline.utils.ShutdownHooks;
import org.apache.thrift.TException;

import com.soofaloofa.taskmanager.Task;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TMessage;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;

public class JavaClient {
    public static void main(String [] args) {

        try {
            TTransport transport;
            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);

            Task task = new Task();
            perform();

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform() throws TException
    {
        System.out.println("ping()");
    }
}