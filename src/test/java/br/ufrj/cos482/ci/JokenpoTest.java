package br.ufrj.cos482.ci;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * Os oito testes da aula 20 e os dois testes de zero da aula 23. Com eles o
 * Jokenpo tem 100% dos branches no JaCoCo e 14 de 14 mutantes mortos no PIT.
 */
class JokenpoTest {

    private final Jokenpo jokenpo = new Jokenpo();

    @Test
    void papelPerdeParaTesoura() {
        assertEquals(2, jokenpo.jogar(1, 3));
    }

    @Test
    void tesouraGanhaDePapel() {
        assertEquals(1, jokenpo.jogar(3, 1));
    }

    @Test
    void escolhaInvalida() {
        assertEquals(-1, jokenpo.jogar(4, 1));
    }

    @Test
    void empate() {
        assertEquals(0, jokenpo.jogar(1, 1));
    }

    @Test
    void jogador1Negativo() {
        assertEquals(-1, jokenpo.jogar(-2, 4));
    }

    @Test
    void jogador2Negativo() {
        assertEquals(-1, jokenpo.jogar(1, -2));
    }

    @Test
    void jogador2AcimaDeTres() {
        assertEquals(-1, jokenpo.jogar(1, 4));
    }

    @Test
    void papelGanhaDePedra() {
        assertEquals(1, jokenpo.jogar(1, 2));
    }

    @Test
    void jogador1Zero() {
        assertEquals(-1, jokenpo.jogar(0, 1));
    }

    @Test
    void jogador2Zero() {
        assertEquals(-1, jokenpo.jogar(1, 0));
    }

    @Test
    void papelGanhaDePedraPeloNome() {
        assertEquals(1, jokenpo.jogar("papel", "pedra"));
    }

    @Test
    void pedraPerdeParaPapelPeloNome() {
        assertEquals(2, jokenpo.jogar("pedra", "papel"));
    }

    @Test
    void tesouraGanhaDePapelPeloNome() {
        assertEquals(1, jokenpo.jogar("tesoura", "papel"));
    }

    @Test
    void empatePeloNome() {
        assertEquals(0, jokenpo.jogar("tesoura", "tesoura"));
    }

    @Test
    void nomeInvalido() {
        assertEquals(-1, jokenpo.jogar("lagarto", "pedra"));
    }
}
