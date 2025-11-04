import java.util.*;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of frames
        System.out.print("Enter number of frames: ");
        int framesCount = sc.nextInt();

        // Input number of pages
        System.out.print("Enter number of pages: ");
        int pagesCount = sc.nextInt();

        int[] pages = new int[pagesCount];
        System.out.println("Enter page reference string:");
        for (int i = 0; i < pagesCount; i++) {
            pages[i] = sc.nextInt();
        }

        int[] frames = new int[framesCount];
        Arrays.fill(frames, -1); // initialize frames
        int pageFaults = 0;

        System.out.println("\nPage Replacement Process:");

        for (int i = 0; i < pagesCount; i++) {
            int page = pages[i];

            // Check if page is already in frames
            boolean found = false;
            for (int f : frames) {
                if (f == page) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                pageFaults++;

                int replaceIndex = -1;

                // If empty frame exists, use it
                for (int j = 0; j < framesCount; j++) {
                    if (frames[j] == -1) {
                        replaceIndex = j;
                        break;
                    }
                }

                // If no empty frame, find the page to replace
                if (replaceIndex == -1) {
                    int farthest = i;
                    

                    for (int j = 0; j < framesCount; j++) {
                        int nextUse = Integer.MAX_VALUE;

                        // Find next use of current page in frame
                        for (int k = i + 1; k < pagesCount; k++) {
                            if (frames[j] == pages[k]) {
                                nextUse = k;
                                break;
                            }
                        }

                        // Replace the page that is not used for longest time
                        if (nextUse > farthest) {
                            farthest = nextUse;
                            replaceIndex = j;
                        }
                    }
                }

                frames[replaceIndex] = page;
            }

            // Display current frame contents
            System.out.print("Step " + (i + 1) + " (Page " + page + "): ");
            for (int f : frames) {
                if (f != -1) System.out.print(f + " ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }
}
