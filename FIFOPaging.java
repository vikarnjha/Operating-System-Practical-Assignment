import java.util.*;

public class FIFOPaging {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of frames:");
        int frames = sc.nextInt();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        System.out.println("Enter number of pages:");
        int pages = sc.nextInt();
        int[] page = new int[pages];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < pages; i++) {
            page[i] = sc.nextInt();
        }

        int pageFaults = 0;
        for (int i = 0; i < pages; i++) {
            if (!set.contains(page[i])) {
                if (queue.size() == frames) {
                    int val = queue.poll();
                    set.remove(val);
                }
                queue.offer(page[i]);
                set.add(page[i]);
                pageFaults++;
            }
            System.out.println("Frames after page " + page[i] + ": " + queue);
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
// 4
// 2
// 5
// Frames after page 1: [1]
// Frames after page 2: [1, 2]
// Frames after page 4: [1, 2, 4]
// Frames after page 2: [1, 2, 4]
// Frames after page 5: [2, 4, 5]
// Total Page Faults: 4