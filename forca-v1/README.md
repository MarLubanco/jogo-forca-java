## Jogo Forca

### O jogo consiste em 4 versões

    . V1.0 - Implementação do jogo da forca, onde existem três jogadores
    cada um tem sua vez de jogar, enquanto for acertando as letras o jogador continua,
    quando erra, a vez é passada para o próximo jogador da fila, assim continua até que
    as palavras sejam descobertas.
    
    
    . V2.0 - Implementada com Socket, então todos os jogadores podem ir falando as letras simultaneamentes.
    
    
    . V3.1 - Implementado com RPC, junto com Apache Thrift, porém só conseguimos implemntar
    para somente um jogador jogar, ao invés de multijogadores.
## Jogar 
    clone o repositório:
    git clone https://github.com/MarLubanco/jogo-forca-java.git
    cd jogo-forca
    git fetch --all
### Jogar V1
    
##### rode o comando
     git checkout feat-v1
     
     -- Primeiro
     start a classe JogoForca.java
     

### Jogar V2
    
##### rode o comando
     git checkout feat-v2
     
    -- Primeiro
     start a classe Servidor.java
     
     após o servidor estar ativo, execute as classes
     JogadorUm, JogadorDois e JogadorTres.
     
     Irá aparecer um chat dos três jogadores, basta selecionar o jogador
     que deseja e ir digitando a letra.
     
 
### Jogar V3.1
    
##### rode o comando
     git checkout feat-v3.1
     
     -- Primeiro
     start a classe MultiplicationServer.java
     
     após o servidor estar ativo, execute as classe JogadorUm
     digite uma letra no console do jogador
     