//
//import model.Jogador;
//import model.Letra;
//import model.Rodada;
//import service.GameService;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.sql.SQLOutput;
//import java.util.*;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//public class JogoForca {
//    public static void main(String[] args) throws IOException {
//        ServerSocket serverSocket = new ServerSocket(4996);
//        try {
//            rodandoPartida(serverSocket);
//        } finally {
//            serverSocket.close();
//        }
//    }
//
//    public static void startForca() {
//        System.out.println("RODA RODA JEQUITI - SILVIO SANTOS");
//    }
//
//    public static void rodandoPartida(ServerSocket serverSocket) throws IOException {
//
//        Socket socket = serverSocket.accept();
//        System.out.println("Client connected");
//        //leitura cliente
//        InputStreamReader in = new InputStreamReader(socket.getInputStream());
//        BufferedReader bf = new BufferedReader(in);
//
//
//        //send cliente
//        PrintWriter pr = new PrintWriter(socket.getOutputStream());;
//
//        Scanner scanner = new Scanner(System.in);
//        List<Jogador> jogadores = new ArrayList<>();
//        criaJogadores(jogadores);
//        startForca();
//        boolean isPassaVez = false;
//        Jogador jogadorAtual = jogadores.get(0);
//        GameService gameService = new GameService();
//        Integer pontosDaVez = gameService.pontosDaVez();
//        List<Letra> palavraSorteada = gameService.sortingPalavra();
//        Rodada rodada;
//        showPalavraAtualizada(palavraSorteada);
//        while (gameService.isFimPartida()) {
//            pontosDaVez = gameService.pontosDaVez();
////            menu(jogadorAtual, pontosDaVez);
//            System.out.println();
//            String inputClient = bf.readLine();
//            String[] objeto = inputClient.split(" ");
//            System.out.println("Cliente: " + objeto[1]);
//            rodada = gameService.acertouLetra(objeto[1], palavraSorteada);
//            palavraSorteada = rodada.getPalavra();
//            isPassaVez = rodada.isPassouRodada();
//            if(!rodada.isPassouRodada()) {
//                Integer finalPontosDaVez = pontosDaVez;
//                jogadores.stream().filter(jog -> jog.getId() == Integer.valueOf(objeto[0]))
//                        .forEach(jog -> jog.setPontos(jog.getPontos() + finalPontosDaVez));
//
//            }
//            showPalavraAtualizada(rodada.getPalavra());
//
//        }
//        mostrarVencedor(jogadores);
//    }
//
//    public static void showPalavraAtualizada(List<Letra> palavra) {
//        System.out.println("Palavra: ");
//        palavra.stream().forEach(letra -> {
//            if(letra.getLetra().equalsIgnoreCase("\n") || letra.isAcertaram()) {
//                System.out.print(letra.getLetra());
//            } else {
//                System.out.print("_");
//            }
//        });
//    }
//    public static void menu(Jogador jogadorAtual, Integer pontosDaVez) {
//        System.out.println();
//        System.out.println("\nmodel.Jogador: " + jogadorAtual.getNome());
//        System.out.println("Pontos: " + jogadorAtual.getPontos());
//        System.out.println("Pontos da roleta: " + pontosDaVez);
//    }
//
//    public static List<Jogador> criaJogadores(List<Jogador> jogadores) {
//        jogadores.add(new Jogador(1, "Thome", 0));
//        jogadores.add(new Jogador(2, "Deborah", 0));
//        jogadores.add(new Jogador(3, "Sergio", 0));
//        return jogadores;
//    }
//
//    public static void mostrarVencedor(List<Jogador> jogadores) {
//        List<Jogador> jogadoresOrdenados = jogadores.stream()
//                .sorted((o1, o2)->o1.getPontos().
//                        compareTo(o2.getPontos()))
//                .collect(Collectors.toList());
//        Jogador vencedor = jogadoresOrdenados.get(jogadores.size() - 1);
//        System.out.println("\n VENCEDOR !!!!!");
//        System.out.println("    " + vencedor.getNome() + "     ");
//        System.out.println("    " + vencedor.getPontos() + "   ");
//    }
//
//}
