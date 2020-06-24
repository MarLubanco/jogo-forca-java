import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TSSLTransportFactory.TSSLTransportParameters;
import service.GameService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class JavaServer {

    public static CalculatorHandler handler;

    public static TProcessor processor;



    public static void main(String [] args) {
        ArrayList<BufferedWriter> clientes;
         ServerSocket server;
         String nome;
         Socket con;
         InputStream in;
         InputStreamReader inr;
         BufferedReader bfr;
        GameService gameService = new GameService();
        try {
            handler = new CalculatorHandler();
            processor = new TProcessor() {
                public void process(TProtocol tProtocol, TProtocol tProtocol1) throws TException {

                    while (gameService.isFimPartida()) {
                        String msg = "1 a";
                        System.out.println(msg);
                        gameService.receberMensagem(msg);
                    }
                    gameService.mostrarVencedor();

                }
            };

            Runnable simple = new Runnable() {
                public void run() {
                    simple(processor);
                }
            };

            new Thread(simple).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(TProcessor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

