import java.util.*;

public class FIFO {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int pages[] = new int[n];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        Queue<Integer> queue = new LinkedList<>();
        int pageFaults = 0;

        System.out.println("\nPage Replacement Process:");
        for (int i = 0; i < n; i++) {
            int page = pages[i];

            // If page not already in queue (frame)
            if (!queue.contains(page)) {
                pageFaults++;

                // If frame is full, remove oldest page
                if (queue.size() == frames) {
                    queue.poll();
                }

                // Add new page
                queue.add(page);
            }

            // Display frame contents
            System.out.print("Step " + (i + 1) + " (Page " + page + "): ");
            for (int f : queue) {
                System.out.print(f + " ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }
}
