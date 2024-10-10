import java.util.*;

public class LRUPageReplacement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of frames:");
        int frames = sc.nextInt();
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> lruMap = new HashMap<>();

        System.out.println("Enter number of pages:");
        int pages = sc.nextInt();
        int[] page = new int[pages];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < pages; i++) {
            page[i] = sc.nextInt();
        }

        int pageFaults = 0, time = 0;
        for (int i = 0; i < pages; i++) {
            if (!set.contains(page[i])) {
                if (set.size() == frames) {
                    int lru = Collections.min(lruMap.entrySet(), Map.Entry.comparingByValue()).getKey();
                    set.remove(lru);
                    lruMap.remove(lru);
                }
                set.add(page[i]);
                pageFaults++;
            }
            lruMap.put(page[i], time++);
            System.out.println("Frames after page " + page[i] + ": " + set);
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