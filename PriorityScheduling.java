import java.util.Scanner;

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        int[] bt = new int[n];
        int[] wt = new int[n];
        int[] tat = new int[n];
        int[] p = new int[n];
        int[] pr = new int[n];  // priorities

        for (int i = 0; i < n; i++) {
            p[i] = i + 1;
            System.out.print("Enter burst time and priority for process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            pr[i] = sc.nextInt();
        }

        // Sorting based on priority
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (pr[i] > pr[j]) {
                    int temp = pr[i];
                    pr[i] = pr[j];
                    pr[j] = temp;

                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    temp = p[i];
                    p[i] = p[j];
                    p[j] = temp;
                }
            }
        }

        wt[0] = 0;
        for (int i = 1; i < n; i++) {
            wt[i] = wt[i - 1] + bt[i - 1];
        }

        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }

        System.out.println("\nProcess\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(p[i] + "\t" + bt[i] + "\t\t" + pr[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
        }
        float avgWT = 0, avgTAT = 0;
        for (int i = 0; i < n; i++) {
            avgWT += wt[i];
            avgTAT += tat[i];
        }

        System.out.println("\nAverage Waiting Time: " + (avgWT / n));
        System.out.println("Average Turnaround Time: " + (avgTAT / n));
    }
}


//Output
// Enter the number of processes:
// 3
// Enter burst time and priority for process 1: 1 2
// Enter burst time and priority for process 2: 2 1
// Enter burst time and priority for process 3: 3 3

// Process Burst Time      Priority        Waiting Time    Turnaround Time
// 2       2               1               0               2
// 1       1               2               2               3
// 3       3               3               3               6

// Average Waiting Time: 1.6666666
// Average Turnaround Time: 3.6666667