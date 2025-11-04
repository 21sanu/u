import java.util.Scanner;

public class PriorityNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n];      // process ids
        int[] at = new int[n];       // arrival times
        int[] bt = new int[n];       // burst times
        int[] priority = new int[n]; // priority (lower value = higher priority)
        int[] ct = new int[n];       // completion times
        int[] tat = new int[n];      // turnaround times
        int[] wt = new int[n];       // waiting times
        boolean[] done = new boolean[n]; // track completed processes

        // Input process details
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter Arrival Time for P" + pid[i] + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter Burst Time for P" + pid[i] + ": ");
            bt[i] = sc.nextInt();
            System.out.print("Enter Priority for P" + pid[i] + ": ");
            priority[i] = sc.nextInt();
        }

        int currentTime = 0, completed = 0;

        // Scheduling loop
        while (completed < n) {
            int idx = -1;
            int highestPriority = Integer.MAX_VALUE;

            // Find next process with highest priority that has arrived
            for (int i = 0; i < n; i++) {
                if (!done[i] && at[i] <= currentTime) {
                    if (priority[i] < highestPriority) {
                        highestPriority = priority[i];
                        idx = i;
                    } else if (priority[i] == highestPriority) {
                        // Tie-breaker: choose process with earlier arrival
                        if (at[i] < at[idx]) idx = i;
                    }
                }
            }

            if (idx == -1) {
                currentTime++; // CPU idle
            } else {
                ct[idx] = currentTime + bt[idx];
                tat[idx] = ct[idx] - at[idx];
                wt[idx] = tat[idx] - bt[idx];
                currentTime += bt[idx];
                done[idx] = true;
                completed++;
            }
        }

        // Display results
        System.out.println("\nProcess\tAT\tBT\tPriority\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                               priority[i] + "\t\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        // Average TAT and WT
        float avgTAT = 0, avgWT = 0;
        for (int i = 0; i < n; i++) {
            avgTAT += tat[i];
            avgWT += wt[i];
        }
        System.out.println("\nAverage Turnaround Time: " + (avgTAT / n));
        System.out.println("Average Waiting Time: " + (avgWT / n));

        sc.close();
    }
}
