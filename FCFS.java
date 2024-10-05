import java.util.Scanner;

public class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of processes: ");
        int n = sc.nextInt();
        int[] pid = new int[n];
        int[] bt = new int[n];
        int[] wt = new int[n];
        int[] tat = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.println("Enter the burst time for process " + pid[i] + ": ");
            bt[i] = sc.nextInt();
        }

        wt[0] = 0; // First process has no waiting time
        for (int i = 1; i < n; i++) {
            wt[i] = wt[i - 1] + bt[i - 1]; // Waiting time of the current process
        }

        for (int i = 0; i < n; i++) {
            tat[i] = wt[i] + bt[i]; // Turnaround time = waiting time + burst time
        }

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t\t" + bt[i] + "\t\t" + wt[i] + "\t\t" + tat[i]);
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
// Enter the burst time for process 1: 
// 2
// Enter the burst time for process 2: 
// 3
// Enter the burst time for process 3: 
// 1

// Process      Burst Time      Waiting Time    Turnaround Time
// 1               2               0               2
// 2               3               2               5
// 3               1               5               6

// Average Waiting Time: 2.3333333
// Average Turnaround Time: 4.3333335