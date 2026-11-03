package br.ufrj.cos482.ci;

import org.junit.jupiter.api.Test;

/*
 * Os oito testes da aula 20 e os dois testes de zero da aula 23. Com eles o
 * Jokenpo tem 100% dos branches no JaCoCo e 14 de 14 mutantes mortos no PIT.
 */
class JokenpoTest {

    private final Jokenpo jokenpo = new Jokenpo();

    @Test
    void papelPerdeParaTesoura() {
        jokenpo.jogar(1, 3);
    }

    @Test
    void tesouraGanhaDePapel() {
        jokenpo.jogar(3, 1);
    }

    @Test
    void escolhaInvalida() {
        jokenpo.jogar(4, 1);
    }

    @Test
    void empate() {
        jokenpo.jogar(1, 1);
    }

    @Test
    void jogador1Negativo() {
        jokenpo.jogar(-2, 4);
    }

    @Test
    void jogador2Negativo() {
        jokenpo.jogar(1, -2);
    }

    @Test
    void jogador2AcimaDeTres() {
        jokenpo.jogar(1, 4);
    }

    @Test
    void papelGanhaDePedra() {
        jokenpo.jogar(1, 2);
    }

    @Test
    void jogador1Zero() {
        jokenpo.jogar(0, 1);
    }

    @Test
    void jogador2Zero() {
        jokenpo.jogar(1, 0);
    }
}
