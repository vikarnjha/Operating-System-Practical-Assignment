import java.util.Scanner;

public class BankersAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes:");
        int P = sc.nextInt();

        System.out.println("Enter number of resources:");
        int R = sc.nextInt();

        int[][] max = new int[P][R];
        int[][] alloc = new int[P][R];
        int[][] need = new int[P][R];
        int[] avail = new int[R];

        System.out.println("Enter the Max matrix:");
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < R; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the Allocation matrix:");
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < R; j++) {
                alloc[i][j] = sc.nextInt();
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }

        System.out.println("Enter the Available resources:");
        for (int i = 0; i < R; i++) {
            avail[i] = sc.nextInt();
        }

        boolean[] finish = new boolean[P];
        int[] safeSeq = new int[P];
        int count = 0;

        while (count < P) {
            boolean found = false;
            for (int p = 0; p < P; p++) {
                if (!finish[p]) {
                    int j;
                    for (j = 0; j < R; j++) {
                        if (need[p][j] > avail[j]) break;
                    }

                    if (j == R) {
                        for (int k = 0; k < R; k++) avail[k] += alloc[p][k];
                        safeSeq[count++] = p;
                        finish[p] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("System is in unsafe state.");
                return;
            }
        }

        System.out.println("System is in safe state. Safe sequence is:");
        for (int i = 0; i < P; i++) System.out.print(safeSeq[i] + " ");
    }
}

