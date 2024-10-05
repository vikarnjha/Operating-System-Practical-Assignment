import java.util.Scanner;

public class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        int[] bt = new int[n];  // burst time
        int[] wt = new int[n];  // waiting time
        int[] tat = new int[n]; // turn around time
        int[] p = new int[n];   // process ids

        for (int i = 0; i < n; i++) {
            p[i] = i + 1;
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
        }

        // Sorting processes based on burst time
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (bt[i] > bt[j]) {
                    int temp = bt[i];
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

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(p[i] + "\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
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



// Output
// Enter the number of processes:
// 3
// Enter burst time for process 1: 2
// Enter burst time for process 2: 3
// Enter burst time for process 3: 1

// Process Burst Time      Waiting Time    Turnaround Time
// 3       1               0               1
// 1       2               1               3
// 2       3               3               6