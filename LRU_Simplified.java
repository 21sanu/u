import java.util.*;

public class LRU_Simplified {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Frames: ");
        int f = sc.nextInt();
        System.out.print("Pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Page reference string:");
        for (int i = 0; i < n; i++) pages[i] = sc.nextInt();

        int[] frames = new int[f];
        Arrays.fill(frames, -1); // empty frames
        int faults = 0;

        System.out.println("\nPage Replacement Process:");


        for (int i = 0; i < n; i++) {
            int page = pages[i];
            boolean found = false;

            // Check if page is already in frames
            for (int j = 0; j < f; j++) {
                if (frames[j] == page) {
                    found = true;
                    break;
                }
            }

            if (!found) { // Page fault occurs
                faults++;

                int replaceIndex = -1;

                // Find empty frame first
                for (int j = 0; j < f; j++) {
                    if (frames[j] == -1) {
                        replaceIndex = j;
                        break;
                    }
                }

                // If no empty frame, find LRU page
                if (replaceIndex == -1) {
                    int oldest = i;

                    for (int j = 0; j < f; j++) {
                    int lastUsed = -1;
                         for (int k = i - 1; k >= 0; k--) {
                             if (pages[k] == frames[j]) {
                                lastUsed = k;
                                break;
                             }
                          }
                          if (lastUsed < oldest) {
                             oldest = lastUsed;
                             replaceIndex = j;  // directly assign here
                          }
                    }
                }

                // Replace page
                frames[replaceIndex] = page;
                }

            // Print frames
            System.out.print("Step " + (i + 1) + " (Page " + page + "): ");
            for (int j = 0; j < f; j++) if (frames[j] != -1) System.out.print(frames[j] + " ");
            System.out.println();
        }

        System.out.println("\nTotal Page Faults: " + faults);
        sc.close();
    }  
}
