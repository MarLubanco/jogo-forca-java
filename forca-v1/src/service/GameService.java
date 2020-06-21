package service;

import model.Jogador;
import model.Letra;
import model.Rodada;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class GameService {

    List<Jogador> jogadores = criaJogadores();
    Rodada rodada;
    Integer pontosDaVez = pontosDaVez();
    List<Letra> palavraSorteada = sortingPalavra();

    public GameService() {
    }

    public List<Letra> sortingPalavra() {
        List<String> palavras = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("palavras.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
                palavras.add(line);
            }
             sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random rand = new Random();
        int valorSorteado = rand.nextInt(palavras.size() -1);
        int palavraDois = rand.nextInt(palavras.size() -1);
        int palavraTres = rand.nextInt(palavras.size() - 1);
        String palavraSorteada = palavras.get(valorSorteado);
        String palavraSorteadaDois = palavras.get(palavraDois);
        String palavraSorteadaTres = palavras.get(palavraTres);
        List<Letra> palavraFinal = new ArrayList<>();

        char[] chars = palavraSorteada.toCharArray();
        char[] charsDois = palavraSorteadaDois.toCharArray();
        char[] charsTres = palavraSorteadaTres.toCharArray();

        List<String> palavraAux = new ArrayList<>();
        for(int i=0; i < chars.length ; i++) {
            palavraAux.add(String.valueOf(chars[i]));
        }
        palavraAux.add("\n");
        for(int i=0; i < charsDois.length ; i++) {
            palavraAux.add(String.valueOf(charsDois[i]));
        }
        palavraAux.add("\n");
        for(int i=0; i < charsTres.length ; i++) {
            palavraAux.add(String.valueOf(charsTres[i]));
        }
        palavraAux.stream()
                .forEach(letra -> palavraFinal.add(new Letra(letra.toString(), false)));
        return palavraFinal;
    }

    public Jogador trocaJogador(List<Jogador> jogadores, Integer jogadorAtual) {
        jogadorAtual = jogadorAtual == jogadores.size() ? 0 : jogadorAtual;
        Integer finalJogadorAtual = jogadorAtual;
        return jogadores.stream()
                .filter(jogador -> jogador.getId() == (finalJogadorAtual + 1))
                .findAny()
                .orElse(null);
    }
    public Integer pontosDaVez() {
        List<Integer> pontos = Arrays.asList(100, 150, 200, 300, 500, 600, 800, 1000);
        Random rand = new Random();
        int indexPonto = rand.nextInt(pontos.size());
        return pontos.get(indexPonto);
    }

    public Rodada acertouLetra(String letraEscolhida, List<Letra> palavra) {
        AtomicBoolean acertou = new AtomicBoolean(true);
        palavra.forEach(letra ->  {
            if(letra.getLetra().equalsIgnoreCase(letraEscolhida) && !letra.isAcertaram()) {
                letra.setAcertaram(true);
                acertou.set(false);
            }
        });
        return new Rodada(palavra, acertou.get());
    }

    public boolean isFimPartida(List<Letra> palavras) {
        long qtdLetrasFaltando = palavras.stream()
                .filter(letra -> !letra.getLetra().equalsIgnoreCase("\n") && !letra.isAcertaram())
                .count();
        return qtdLetrasFaltando > 0L ? true : false;
    }

    public void rodandoPartida(ServerSocket serverSocket) throws IOException {
        System.out.println("Boas vindas arrombados");
        showPalavraAtualizada(palavraSorteada);
//        while (isFimPartida(palavraSorteada)) {
//            pontosDaVez = pontosDaVez();
//            String inputClient = "1 rola";
//
//        }
//        mostrarVencedor(jogadores);
    }

    public static void showPalavraAtualizada(List<Letra> palavra) {
        System.out.println("Palavra: ");
        palavra.stream().forEach(letra -> {
            if(letra.getLetra().equalsIgnoreCase("\n") || letra.isAcertaram()) {
                System.out.print(letra.getLetra());
            } else {
                System.out.print("_");
            }
        });
    }
    public static void menu(Jogador jogadorAtual, Integer pontosDaVez) {
        System.out.println();
        System.out.println("\nmodel.Jogador: " + jogadorAtual.getNome());
        System.out.println("Pontos: " + jogadorAtual.getPontos());
        System.out.println("Pontos da roleta: " + pontosDaVez);
    }

    public static List<Jogador> criaJogadores() {
        List<Jogador> jogadores = new ArrayList<>();
        jogadores.add(new Jogador(1, "Thome", 0));
        jogadores.add(new Jogador(2, "Deborah", 0));
        jogadores.add(new Jogador(3, "Sergio", 0));
        return jogadores;
    }

    public static void mostrarVencedor(List<Jogador> jogadores) {
        List<Jogador> jogadoresOrdenados = jogadores.stream()
                .sorted((o1, o2)->o1.getPontos().
                        compareTo(o2.getPontos()))
                .collect(Collectors.toList());
        Jogador vencedor = jogadoresOrdenados.get(jogadores.size() - 1);
        System.out.println("\n VENCEDOR !!!!!");
        System.out.println("    " + vencedor.getNome() + "     ");
        System.out.println("    " + vencedor.getPontos() + "   ");
    }

    public void receberMensagem(String inputClient) {
        String[] objeto = inputClient.split(" ");
        System.out.println("Cliente: " + objeto[1]);
        rodada = acertouLetra(objeto[1], palavraSorteada);
        palavraSorteada = rodada.getPalavra();
        if(!rodada.isPassouRodada()) {
            Integer finalPontosDaVez = pontosDaVez;
            jogadores.stream()
                    .filter(jog -> jog.getId() == Integer.valueOf(objeto[0]))
                    .forEach(jog -> jog.setPontos(jog.getPontos() + finalPontosDaVez));

        }
        showPalavraAtualizada(rodada.getPalavra());
    }
}
