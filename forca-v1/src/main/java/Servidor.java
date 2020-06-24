//import service.GameService;
//
//import javax.swing.*;
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//
//public class Servidor extends Thread {
//
//    private static ArrayList<BufferedWriter> clientes;
//    private static ServerSocket server;
//    private String nome;
//    private Socket con;
//    private InputStream in;
//    private InputStreamReader inr;
//    private BufferedReader bfr;
//    GameService gameService;
//
//    public static void main(String[] args) throws IOException {
//        GameService gameService = new GameService();
//        try {
//            JLabel lblMessage = new JLabel("Porta do Servidor:");
//            JTextField txtPorta = new JTextField("12345");
//            Object[] texts = {lblMessage, txtPorta};
//            JOptionPane.showMessageDialog(null, texts);
//            server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
//            clientes = new ArrayList<BufferedWriter>();
//
//            JOptionPane.showMessageDialog(null, "Servidor ativo na porta: " +
//                    txtPorta.getText());
//
//            while (true) {
//                System.out.println("Aguardando conexão...");
//                Socket con = server.accept();
//                System.out.println("Cliente conectado...");
//                Thread t = new Servidor(gameService, con);
//                t.start();
//            }
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }
//
//    public Servidor(GameService gameService, Socket con) throws IOException {
//        this.gameService = gameService;
//        this.con = con;
//        try {
//            in = con.getInputStream();
//            inr = new InputStreamReader(in);
//            bfr = new BufferedReader(inr);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void run() {
//        try {
//            gameService.rodandoPartida(server);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            String msg;
//            OutputStream ou = this.con.getOutputStream();
//            Writer ouw = new OutputStreamWriter(ou);
//            BufferedWriter bfw = new BufferedWriter(ouw);
//            clientes.add(bfw)
//            ;
//            nome = msg = bfr.readLine();
//            while (this.gameService.isFimPartida()) {
//                msg = bfr.readLine();
//                sendToAll(bfw, msg);
//                this.gameService.receberMensagem(msg);
//            }
//            this.gameService.mostrarVencedor();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
//
//    public void sendToAll(BufferedWriter bwSaida, String msg) throws IOException {
//        BufferedWriter bwS;
//        for (BufferedWriter bw : clientes) {
//            bwS = (BufferedWriter) bw;
//            if (!(bwSaida == bwS)) {
//                bw.write(nome + " -> " + msg + "\r\n");
//                bw.flush();
//            }
//        }
//    }
//
//}
