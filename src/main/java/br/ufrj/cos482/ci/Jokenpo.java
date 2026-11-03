package br.ufrj.cos482.ci;

public class Jokenpo {

    /**
     * Calcula o vencedor do jogo Jokenpo (pedra, papel e tesoura).
     * As escolhas podem ser 1 (papel), 2 (pedra) e 3 (tesoura).
     *
     * @param jogador1 escolha do jogador 1
     * @param jogador2 escolha do jogador 2
     * @return 0 se houve empate, 1 se o jogador 1 venceu, 2 se o jogador 2
     *         venceu, -1 se alguma escolha for invalida
     */
    public int jogar(int jogador1, int jogador2) {
        int retorno = 0;
        if ((jogador1 > 0) && (jogador1 <= 3) && (jogador2 > 0) && (jogador2 <= 3)) {
            if (jogador1 != jogador2) {
                if ((jogador1 - jogador2) == -1 || (jogador1 - jogador2 == 2))
                    retorno = 1;
                else
                    retorno = 2;
            }
        } else
            retorno = -1;
        return retorno;
    }

    /**
     * Devolve o nome de uma escolha, para exibir o resultado do jogo.
     *
     * @param escolha 1 (papel), 2 (pedra) ou 3 (tesoura)
     * @return o nome da escolha, ou "invalida" se ela nao existir
     */
    public String nome(int escolha) {
        if (escolha == 1) {
            return "papel";
        }
        if (escolha == 2) {
            return "pedra";
        }
        if (escolha == 3) {
            return "tesoura";
        }
        return "invalida";
    }
}
