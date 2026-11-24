import java.util.Scanner;

public class f1 {


    static String nomePiloto;
    static int habilidade = 50;
    static int pontos = 0;
    static int saldo = 500;
    static int corridasRealizadas = 0;

    static int[] historicoPontos = new int[5];
    static int indiceHistorico = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Solicitar nome do piloto
        System.out.print("Digite o nome do piloto: ");
        nomePiloto = scanner.nextLine();

        // Loop do menu
        int opcao;
        do {
            opcao = exibirMenu(scanner);

            switch (opcao) {
                case 1:
                    mostrarPiloto();
                    break;
                case 2:
                    treinarPiloto(scanner);
                    break;
                case 3:
                    participarCorrida(scanner);
                    break;
                case 4:
                    consultarResultados();
                    break;
                case 0:
                    System.out.println("Saindo do simulador. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }


    public static int exibirMenu(Scanner scanner) {
        System.out.println("========== JAVA F1 SIMULATOR ==========");
        System.out.println("Piloto: " + nomePiloto + " | Habilidade: " + habilidade);
        System.out.println("Pontos: " + pontos + " | Saldo: R$ " + saldo);
        System.out.println("1 - Visualizar piloto");
        System.out.println("2 - Treinar piloto");
        System.out.println("3 - Participar de corrida");
        System.out.println("4 - Consultar resultados");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");

        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Opção 1: Mostrar informações detalhadas
    public static void mostrarPiloto() {
        System.out.println("\n--- Detalhes do Piloto ---");
        System.out.println("Nome: " + nomePiloto);
        System.out.println("Habilidade: " + habilidade);
        System.out.println("Pontos Acumulados: " + pontos);
        System.out.println("Saldo: R$ " + saldo);
        System.out.println("Corridas Realizadas: " + corridasRealizadas);
        System.out.println("--------------------------\n");
    }

    // Opção 2: Treinar piloto
    public static void treinarPiloto(Scanner scanner) {

        if (habilidade >= 100) {
            System.out.println("Habilidade já está no máximo (100)!");
            return;
        }
        if (saldo < 300) {
            System.out.println("Saldo insuficiente! Treinamento custa R$ 300.");
            return;
        }


        saldo -= 300;

        // Sorteio de 1 a 10
        int sorteio = (int) (Math.random() * 10) + 1;


        habilidade = treinar(habilidade, sorteio);

        System.out.println("Treinar custa R$ 300. Sorteio: " + sorteio);
        if (sorteio >= 6) {
            System.out.println("Treinamento concluído! Habilidade: " + habilidade);
        } else {
            System.out.println("Treinamento falhou! Habilidade: " + habilidade);
        }
        System.out.println("Saldo atual: R$ " + saldo);
    }


    public static int treinar(int habilidadeAtual, int sorteio) {
        if (sorteio >= 6) {
            habilidadeAtual += 5;

            if (habilidadeAtual > 100) {
                habilidadeAtual = 100;
            }
        }
        return habilidadeAtual;
    }

    // Opção 3: Participar de corrida
    public static void participarCorrida(Scanner scanner) {
        System.out.println("\n--- Corrida ---");
        System.out.println("Escolha a estratégia:");
        System.out.println("1 - Conservadora (+0)");
        System.out.println("2 - Equilibrada (+5)");
        System.out.println("3 - Agressiva (+10)");
        System.out.print("Estratégia: ");

        int estrategia = -1;
        if (scanner.hasNextInt()) {
            estrategia = scanner.nextInt();
        } else {
            scanner.next();
        }


        if (estrategia < 1 || estrategia > 3) {
            System.out.println("Escolha inválida de estratégia. Corrida não iniciada.");
            return;
        }

        // Bônus da estratégia
        int bonus = 0;
        if (estrategia == 2) bonus = 5;
        if (estrategia == 3) bonus = 10;

        // Sorte da corrida (0 a 10)
        int sorteCorrida = (int) (Math.random() * 11);


        int desempenho = calcularDesempenho(habilidade, bonus, sorteCorrida);


        int pontosGanhos = calcularPontos(desempenho);
        int premioGanho = calcularPremio(desempenho);


        pontos += pontosGanhos;
        saldo += premioGanho;
        corridasRealizadas++;


        salvarHistorico(pontosGanhos);


        String posicao = "";
        if (desempenho >= 90) posicao = "1o lugar";
        else if (desempenho >= 75) posicao = "2o lugar";
        else if (desempenho >= 60) posicao = "3o lugar";
        else posicao = "5o lugar";

        System.out.println("Sorte: " + sorteCorrida + " | Desempenho: " + desempenho);
        System.out.println("Resultado: " + posicao + " | +" + pontosGanhos + " pontos | +R$ " + premioGanho);
        System.out.println("Saldo atual: R$ " + saldo);
    }
    public static int calcularDesempenho(int habilidade, int bonus, int sorte) {
        return habilidade + bonus + sorte;
    }


    public static int calcularPontos(int desempenho) {
        if (desempenho >= 90) return 25;
        if (desempenho >= 75) return 18;
        if (desempenho >= 60) return 15;
        return 10;
    }


    public static int calcularPremio(int desempenho) {
        if (desempenho >= 90) return 1000;
        if (desempenho >= 75) return 700;
        if (desempenho >= 60) return 500;
        return 300;
    }

    // Opção 4: Consultar resultados
    public static void consultarResultados() {
        System.out.println("\n--- Consultar Resultados ---");
        System.out.println("Total de Corridas: " + corridasRealizadas);
        System.out.println("Pontos Acumulados: " + pontos);
        System.out.println("Saldo Atual: R$ " + saldo);


        System.out.println("Últimas 5 corridas (pontos):");
        for (int i = 0; i < 5; i++) {
            if (i < indiceHistorico) {
                System.out.print(historicoPontos[i] + " ");
            } else {
                if (corridasRealizadas > 0) {
                    System.out.print("0 ");
                } else {
                    System.out.print("- ");
                }
            }
        }
        System.out.println("\n-----------------------------\n");
    }

    // Desafio Extra: Lógica do vetor
    public static void salvarHistorico(int pontosGanhos) {
        historicoPontos[indiceHistorico] = pontosGanhos;
        indiceHistorico++;
        if (indiceHistorico >= 5) {
            indiceHistorico = 0;
        }
    }
}