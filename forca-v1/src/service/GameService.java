package service;

import model.Jogador;
import model.Letra;
import model.Rodada;

import java.io.*;
import java.net.ServerSocket;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class GameService {

    List<Jogador> jogadores = criaJogadores();
    Rodada rodada;
    Integer pontosDaVez = pontosDaVez();
    List<Letra> palavraSorteada = sortingPalavra();
    List<String> letrasTestadas = new ArrayList<>();

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
        String palavraSorteada = palavras.get(valorSorteado);
        List<Letra> palavraFinal = new ArrayList<>();

        char[] chars = palavraSorteada.toCharArray();

        List<String> palavraAux = new ArrayList<>();
        for(int i=0; i < chars.length ; i++) {
            palavraAux.add(String.valueOf(chars[i]));
        }
        palavraAux.add("\n");
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
        List<Integer> pontos = Arrays.asList(5, 10, 6, 4, 8, 15, 20, 1);
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

    public boolean isFimPartida() {
        long qtdLetrasFaltando = this.palavraSorteada.stream()
                .filter(letra -> !letra.getLetra().equalsIgnoreCase("\n") && !letra.isAcertaram())
                .count();
        return qtdLetrasFaltando > 0L ? true : false;
    }

    public void rodandoPartida(ServerSocket serverSocket) throws IOException {
        System.out.println("Boas vindas ao RODA RODA");
        showPalavraAtualizada(palavraSorteada);
//        while (isFimPartida(palavraSorteada)) {
//            pontosDaVez = pontosDaVez();
//            String inputClient = "1 rola";
//
//        }
//        mostrarVencedor(jogadores);
    }

    public void showLetrasTestadas() {
        String letras = "";
        for (String letrasTestada : letrasTestadas) {
            letras += letrasTestada + " - ";
        }
        System.out.println("Letras anteriores: " + letras);
    }

    public void showPalavraAtualizada(List<Letra> palavra) {
        showHanking();
        showLetrasTestadas();
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

    public void mostrarVencedor() {
        List<Jogador> jogadoresOrdenados = jogadores.stream()
                .sorted((o1, o2)->o1.getPontos().
                        compareTo(o2.getPontos()))
                .collect(Collectors.toList());
        Jogador vencedor = jogadoresOrdenados.get(jogadores.size() - 1);
        System.out.println("\n VENCEDOR !!!!!");
        System.out.println("    " + vencedor.getNome() + "     ");
        System.out.println("    " + vencedor.getPontos() + "   ");
    }

    public void showHanking() {
        System.out.println("   HANKING                  ");
        jogadores.stream().forEach(jogador -> {
           System.out.println("    " + jogador.getNome() + " - " + jogador.getPontos() );
        });
    }

    public String showHankingIndividual() {
        String hanking = " \n    HANKING \n ";
        for (Jogador jogador : jogadores) {
            hanking += "    " + jogador.getNome() + " - " + jogador.getPontos() + "\n";
        };
        return hanking;
    }

    public void receberMensagem(String inputClient) {
        String[] objeto = inputClient.split(" ");
        if(objeto[1].equals(":ranking")) {
            showHanking();
        }

        String nomeJogador = jogadores.stream()
                .filter(jog -> jog.getId() == Integer.valueOf(objeto[0]))
                .map(Jogador::getNome).collect(Collectors.joining());
        System.out.println("\n Jogador(a): " + nomeJogador + " escolheu letra: " + objeto[1] + "\n");
        rodada = acertouLetra(objeto[1], palavraSorteada);
        palavraSorteada = rodada.getPalavra();
        Integer finalPontosDaVez =0;
        if(!rodada.isPassouRodada()) {
            finalPontosDaVez = pontosDaVez;
        } if(letrasTestadas.contains(objeto[1])) {
            finalPontosDaVez = -1;
        } else if(rodada.isPassouRodada()) {
            finalPontosDaVez = -3;
        }

        if(!objeto[1].equals(":ranking")) {
            letrasTestadas.add(objeto[1]);
        }

        Integer finalPontosDaVez1 = finalPontosDaVez;
        jogadores.stream()
                .filter(jog -> jog.getId() == Integer.valueOf(objeto[0]))
                .forEach(jog -> {
                    System.out.println("Jogador " + jog.getNome() + " recebe: " + finalPontosDaVez1 + " pontos");
                    jog.setPontos(jog.getPontos() + finalPontosDaVez1);
                });

        showPalavraAtualizada(rodada.getPalavra());
    }
}
