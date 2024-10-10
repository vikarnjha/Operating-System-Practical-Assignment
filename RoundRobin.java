import java.util.Scanner;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of processes:");
        int n = sc.nextInt();
        int[] bt = new int[n];
        int[] rem_bt = new int[n];  // remaining burst time
        int[] wt = new int[n];      // waiting time
        int[] tat = new int[n];     // turn around time

        System.out.println("Enter burst time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process " + (i + 1) + ": ");
            bt[i] = sc.nextInt();
            rem_bt[i] = bt[i];
        }

        System.out.println("Enter time quantum:");
        int quantum = sc.nextInt();

        int t = 0;  // current time
        boolean done;

        do {
            done = true;
            for (int i = 0; i < n; i++) {
                if (rem_bt[i] > 0) {
                    done = false;
                    if (rem_bt[i] > quantum) {
                        t += quantum;
                        rem_bt[i] -= quantum;
                    } else {
                        t += rem_bt[i];
                        wt[i] = t - bt[i];
                        rem_bt[i] = 0;
                    }
                }
            }
        } while (!done);

        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
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
// Enter burst time for each process:
// Process 1: 3
// Process 2: 2
// Process 3: 1
// Enter time quantum:
// 2

// Process Burst Time      Waiting Time    Turnaround Time
// 1       3               3               6
// 2       2               2               4
// 3       1               4               5
// Average Waiting Time: 3.0
// Average Turnaround Time: 5.0