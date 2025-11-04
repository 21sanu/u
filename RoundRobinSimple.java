import java.util.Scanner;

public class RoundRobinSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //  Input number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int pid[] = new int[n];  // Process IDs
        int at[] = new int[n];   // Arrival times
        int bt[] = new int[n];   // Burst times
        int rt[] = new int[n];   // Remaining times
        int ct[] = new int[n];   // Completion times
        int tat[] = new int[n];  // Turnaround times
        int wt[] = new int[n];   // Waiting times

        //  Input AT and BT
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter Arrival Time for P" + pid[i] + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter Burst Time for P" + pid[i] + ": ");
            bt[i] = sc.nextInt();
            rt[i] = bt[i];  // initialize remaining time
        }

        //  Input Time Quantum
        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();

        int time = 0;  // Current CPU time
        int done = 0;  // Number of processes completed

        //  Round Robin Scheduling Loop
        while (done < n) {
            boolean idle = true; // Track if CPU is idle

            for (int i = 0; i < n; i++) {
                if (rt[i] > 0 && at[i] <= time) {
                    idle = false; // CPU is busy

                    if (rt[i] > tq) {
                        // Process executes for time quantum
                        time += tq;
                        rt[i] -= tq;
                    } else {
                        // Process finishes execution
                        time += rt[i];
                        rt[i] = 0;
                        ct[i] = time;          // store completion time
                        tat[i] = ct[i] - at[i]; // turnaround time
                        wt[i] = tat[i] - bt[i]; // waiting time
                        done++;
                    }
                }
            }

            if (idle) {
                // If no process is ready, CPU is idle
                time++;
            }
        }

        //  Display results
        System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                    ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        sc.close();
    }
}
