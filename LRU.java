import java.util.*;

public class LRU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int framesCount = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        ArrayList<Integer> frames = new ArrayList<>();
        int faults = 0;

        System.out.println("\nPage Replacement Process:");

        for (int i = 0; i < n; i++) {
            int page = pages[i];

            // If page is already in frame → no fault
            if (frames.contains(page)) {
                // Move it to the end (most recently used)
                frames.remove(Integer.valueOf(page));
                frames.add(page);
            } else {
                // Page fault occurs
                faults++;

                // If frames are full → remove least recently used (first one)
                if (frames.size() == framesCount) {
                    frames.remove(0);
                }
                // Add new page
                frames.add(page);
            }

            // Print current frame status
            System.out.print("Step " + (i + 1) + " (Page " + page + "): ");
            for (int num : frames) System.out.print(num + " ");
            System.out.println();
        }

        System.out.println("\nTotal Page Faults: " + faults);
        sc.close();
    }
}