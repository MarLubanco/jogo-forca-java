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
        int valorSorteado = rand.nextInt(palavras.size());
        String palavraSorteada = palavras.get(valorSorteado);
        List<Letra> palavraFinal = new ArrayList<>();
        char[] chars = palavraSorteada.toCharArray();
        List<String> palavraAux = new ArrayList<>();
        for(int i=0; i < chars.length ; i++) {
            palavraAux.add(String.valueOf(chars[i]));
        }
        palavraAux.stream()
                .forEach(letra -> palavraFinal.add(new Letra(letra.toString(), false)));
        return palavraFinal;
    }

    public Jogador trocaJogador(List<Jogador> jogadores, Integer jogadorAtual) {
        return jogadores.stream()
                .filter(jogador -> jogador.getId() == (jogadorAtual + 1))
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
}
