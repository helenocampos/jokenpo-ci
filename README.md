# jokenpo-ci

Projeto da demonstração da aula 24 da COS482, CI/CD para qualidade.

É o mesmo `Jokenpo` das aulas de teste estrutural e de teste de mutação, com os
dez testes do fim da aula 23. A diferença é que aqui ninguém roda o JaCoCo nem o
PIT à mão. O repositório tem um pipeline no GitHub Actions que roda os testes e
os gates a cada push e a cada pull request, e a `main` só aceita merge de PR com
o pipeline verde.

## O pipeline

O workflow está em [`.github/workflows/ci.yml`](.github/workflows/ci.yml). Os
passos rodam em ordem, e o primeiro que falhar interrompe os seguintes.

| Passo | Comando | Falha quando |
|---|---|---|
| Compilar e testar | `mvn test` | algum teste falha |
| Gate de cobertura (JaCoCo) | `mvn jacoco:check` | alguma classe tem menos de 80% dos branches cobertos |
| Gate de mutação (PIT) | `mvn org.pitest:pitest-maven:mutationCoverage` | o escore de mutação fica abaixo de 80% |
| Quality gate (SonarQube Cloud) | `mvn sonar:sonar` | o código novo reprova no quality gate Sonar way |
| Empacotar | `mvn package -DskipTests` | |

Os limiares dos dois gates, 80% de branches e 80% de escore, estão no
[`pom.xml`](pom.xml). Ao fim de cada execução, os relatórios do JaCoCo e do PIT
ficam disponíveis para download na página da execução, em *Artifacts*, e o
`.jar` também, quando todos os gates passam.

## Rodar na sua máquina

Os mesmos comandos do pipeline, na mesma ordem:

```bash
mvn test
mvn jacoco:check
mvn org.pitest:pitest-maven:mutationCoverage
```

Os relatórios ficam em `target/site/jacoco/index.html` e
`target/pit-reports/index.html`.

## As pull requests da demonstração

Cada branch abaixo tem uma mudança que um dos passos do pipeline barra.

| Branch | O que muda | Quem barra |
|---|---|---|
| `pr1-quebra-regra` | uma condição da regra do jogo | os testes |
| `pr2-funcao-sem-teste` | um método novo, sem teste | o gate de cobertura |
| `pr3-testes-sem-assert` | os testes perdem as asserções | o gate de mutação, porque a cobertura continua em 100% |
| `pr4-checkstyle` | liga o Checkstyle com a regra `NeedBraces` | o Checkstyle, no código que já estava na `main` |
| `pr5-nomes-com-igual` | aceita os nomes das jogadas, comparando `String` com `==` | o SonarQube, com os testes passando |

Para ver uma delas falhar na sua máquina, troque de branch e rode os comandos
acima. A `pr4-checkstyle` acrescenta `mvn checkstyle:check` como primeiro
passo, e a `pr5-nomes-com-igual` só reprova no SonarQube.

## Refazer no seu fork

1. Faça um fork deste repositório. Na aba *Actions* do fork, habilite os
   workflows.
2. Abra uma pull request de uma das branches para a `main` do **seu** fork e
   acompanhe a execução na aba *Checks* da PR.
3. O passo do SonarQube é pulado enquanto o fork não tiver o secret
   `SONAR_TOKEN`. Para ligá-lo, entre em <https://sonarcloud.io> com a conta do
   GitHub, importe o fork, desligue a *Automatic Analysis* em
   *Administration > Analysis Method*, gere um token e cadastre-o em
   *Settings > Secrets and variables > Actions* do fork. Depois, troque
   `helenocampos` no `pom.xml`, em `sonar.organization` e no começo de
   `sonar.projectKey`, pela chave da sua organização no SonarQube Cloud.
