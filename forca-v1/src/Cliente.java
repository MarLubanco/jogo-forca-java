import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",4996);
        Scanner scanner = new Scanner(System.in);
        PrintWriter pr = new PrintWriter(socket.getOutputStream());

        InputStreamReader in = new InputStreamReader(socket.getInputStream());
        BufferedReader bf = new BufferedReader(in);


        while (true) {
            System.out.println("Digite uma letra: ");
            String letra = scanner.next();
            pr.println("1 " + letra);
            pr.flush();
        }
    }
}
