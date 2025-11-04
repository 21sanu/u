import java.util.Scanner;

public class SJF_NonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];   // process ids
        int at[] = new int[n];    // arrival times
        int bt[] = new int[n];    // burst times
        int ct[] = new int[n];    // completion times
        int tat[] = new int[n];   // turnaround times
        int wt[] = new int[n];    // waiting times
        boolean completed[] = new boolean[n]; // to mark completed processes

        // Input
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter Arrival Time for Process " + pid[i] + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter Burst Time for Process " + pid[i] + ": ");
            bt[i] = sc.nextInt();
        }

        int currentTime = 0, completedCount = 0;

        while (completedCount < n) {
            int idx = -1;
            int minBT = Integer.MAX_VALUE;

            // Find process with minimum burst time among available processes
            for (int i = 0; i < n; i++) {
                if (!completed[i] && at[i] <= currentTime) {
                    if (bt[i] < minBT) {
                        minBT = bt[i];
                        idx = i;
                    }
                }
            }

            if (idx == -1) {
                // No process has arrived yet, CPU is idle
                currentTime++;
            } else {
                // Schedule this process
                currentTime += bt[idx];
                ct[idx] = currentTime;
                tat[idx] = ct[idx] - at[idx];
                wt[idx] = tat[idx] - bt[idx];
                completed[idx] = true;
                completedCount++;
            }
        }

        // Display results
        System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                    ct[i] + "\t" + tat[i] + "\t" + wt[i]);
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

