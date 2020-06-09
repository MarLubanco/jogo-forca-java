
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JogoForca {
    public static void main(String[] args) {
        rodandoPartida();
    }

    public static void startForca() {
        System.out.println("RODA RODA JEQUITI - SILVIO SANTOS");
    }

    public static void rodandoPartida() {
        Scanner scanner = new Scanner(System.in);
        List<Jogador> jogadores = new ArrayList<>();
        criaJogadores(jogadores);
        startForca();
        boolean isPassaVez = false;
        boolean isPalavraDescoberta = false;
        Jogador jogadorAtual = jogadores.get(0);
        GameService gameService = new GameService();
        Integer pontosDaVez = gameService.pontosDaVez();
        List<Letra> palavraSorteada = gameService.sortingPalavra();
        Rodada rodada;
        showPalavraAtualizada(palavraSorteada);
        while (!isPalavraDescoberta) {
            if (isPassaVez) {
                jogadorAtual = gameService.trocaJogador(jogadores, jogadorAtual.getId());
            } else {
                pontosDaVez = gameService.pontosDaVez();
            }
            menu(jogadorAtual, pontosDaVez);
            System.out.println();
            System.out.println("Escolha uma letra: ");
            String letraEscolhida = scanner.next();
            rodada = gameService.acertouLetra(letraEscolhida, palavraSorteada);
            palavraSorteada = rodada.getPalavra();
            isPassaVez = rodada.isPassouRodada();
            if(!rodada.isPassouRodada()) {
                jogadorAtual.setPontos(jogadorAtual.getPontos() + pontosDaVez);
            }
            showPalavraAtualizada(rodada.getPalavra());

        }
    }

    public static void showPalavraAtualizada(List<Letra> palavra) {
        System.out.println("Palavra: ");
        palavra.stream().forEach(letra -> {
            if(letra.isAcertaram()) {
                System.out.print(letra.getLetra());
            } else {
                System.out.print("_");
            }
        });
    }
    public static void menu(Jogador jogadorAtual, Integer pontosDaVez) {
        System.out.println();
        System.out.println("\nJogador: " + jogadorAtual.getNome());
        System.out.println("Pontos: " + jogadorAtual.getPontos());
        System.out.println("Pontos da roleta: " + pontosDaVez);
    }

    public static List<Jogador> criaJogadores(List<Jogador> jogadores) {
        jogadores.add(new Jogador(1, "Thome", 0));
        jogadores.add(new Jogador(2, "Deborah", 0));
        jogadores.add(new Jogador(3, "Sergio", 0));
        return jogadores;
    }
}
