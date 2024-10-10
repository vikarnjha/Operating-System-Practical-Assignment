import java.util.*;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of frames:");
        int frames = sc.nextInt();
        int[] frame = new int[frames];
        Arrays.fill(frame, -1);

        System.out.println("Enter number of pages:");
        int pages = sc.nextInt();
        int[] page = new int[pages];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < pages; i++) {
            page[i] = sc.nextInt();
        }

        int pageFaults = 0;
        for (int i = 0; i < pages; i++) {
            int flag = 0;
            for (int j = 0; j < frames; j++) {
                if (frame[j] == page[i]) {
                    flag = 1;
                    break;
                }
            }

            if (flag == 0) {
                if (i >= frames) {
                    int[] index = new int[frames];
                    Arrays.fill(index, Integer.MAX_VALUE);
                    for (int j = 0; j < frames; j++) {
                        for (int k = i + 1; k < pages; k++) {
                            if (frame[j] == page[k]) {
                                index[j] = k;
                                break;
                            }
                        }
                    }
                    int max = 0;
                    for (int j = 1; j < frames; j++) {
                        if (index[j] > index[max]) {
                            max = j;
                        }
                    }
                    frame[max] = page[i];
                } else {
                    frame[i] = page[i];
                }
                pageFaults++;
            }

            System.out.println("Frames after page " + page[i] + ": " + Arrays.toString(frame));
        }

        System.out.println("Total Page Faults: " + pageFaults);
    }
}


// Output
// Enter number of frames:
// 3
// Enter number of pages:
// 5
// Enter the page reference string:
// 1
// 2
// 3
// 2
// 4
// Frames after page 1: [1, -1, -1]
// Frames after page 2: [1, 2, -1]
// Frames after page 3: [1, 2, 3]
// Frames after page 2: [1, 2, 3]
// Frames after page 4: [4, 2, 3]
// Total Page Faults: 4