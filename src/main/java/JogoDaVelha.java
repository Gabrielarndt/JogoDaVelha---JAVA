import java.util.Scanner;

public class JogoDaVelha {

    static char[][] dados = new char[3][3];

    public static void main(String[] args) {
        setup();
        iniciarJogo();
    }

    static void setup() {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                dados[linha][coluna] = '-';
            }
        }
    }

    static boolean verificarVencedor(char simbolo) {
        for (int linha = 0; linha < 3; linha++) {
            if (dados[linha][0] == simbolo && dados[linha][1] == simbolo && dados[linha][2] == simbolo) {
                return true;
            }
        }
        for (int coluna = 0; coluna < 3; coluna++) {
            if (dados[0][coluna] == simbolo && dados[1][coluna] == simbolo && dados[2][coluna] == simbolo) {
                return true;
            }
        }
        if (dados[0][0] == simbolo && dados[1][1] == simbolo && dados[2][2] == simbolo) {
            return true;
        } else if (dados[0][2] == simbolo && dados[1][1] == simbolo && dados[2][0] == simbolo) {
            return true;
        }
        return false;
    }

    static void imprimirTabuleiro() {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                System.out.print(dados[linha][coluna] + " ");
            }
            System.out.println();
        }
    }

    static void iniciarJogo() {
        char simbolo = 'X';
        int jogadas = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Jogador " + simbolo + ", faça sua jogada (linha coluna): ");
            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();
            if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
                System.out.println("Posição inválida! Tente novamente.");
                continue;
            }
            if (dados[linha][coluna] != '-') {
                System.out.println("Posição ocupada! Tente novamente.");
                continue;
            }
            dados[linha][coluna] = simbolo;
            imprimirTabuleiro();
            jogadas++;
            if (verificarVencedor(simbolo)) {
                System.out.println("Jogador " + simbolo + " venceu!");
                break;
            } else if (jogadas == 9) {
                System.out.println("Empate!");
                break;
            }
            simbolo = (simbolo == 'X') ? 'O' : 'X';
        }
        scanner.close();
    }
}
