package service;

import model.Jogador;
import model.Letra;
import model.Rodada;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameService {

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
}
