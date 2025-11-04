import java.util.Scanner;

public class FCFS_Scheduling {
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

        // Input arrival and burst time for each process
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter Arrival Time for Process " + pid[i] + ": ");
            at[i] = sc.nextInt();
            System.out.print("Enter Burst Time for Process " + pid[i] + ": ");
            bt[i] = sc.nextInt();
        }

        // Sorting by arrival time (FCFS works on arrival order)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[i] > at[j]) {
                    // swap arrival time
                    int temp = at[i];
                    at[i] = at[j];
                    at[j] = temp;

                    // swap burst time
                    temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    // swap process id
                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                }
            }
        }

        // Calculating Completion Time (CT)
        ct[0] = at[0] + bt[0];
        for (int i = 1; i < n; i++) {
            if (at[i] > ct[i - 1]) {
                ct[i] = at[i] + bt[i]; // CPU was idle
            } else {
                ct[i] = ct[i - 1] + bt[i];
            }
        }

        // Calculating TAT and WT
        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];     // TAT = CT - AT
            wt[i] = tat[i] - bt[i];     // WT = TAT - BT
        }

        // Display results
        System.out.println("\nProcess\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
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

