import java.util.Arrays;

public class temp {
    public static void main(String[] args) {
        int[] array = new int[] {12,44,13,88,23,94,11,39,20,16,5};
        
        int[] quad = new int[array.length];
        int[] doubleHashing = new int[array.length]; 
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]  +": \t" +(2 * array[i] + 5) % 11+ "\t" + 7 * (array[i]  % 7));
        }
        //Linear Probing
        for (int i = 0; i < array.length; i++) {
            int prob = (2 * array[i] + 5) % array.length;
            while(linear[prob] != null) {
                prob = (prob + 1) % array.length;
            }
            linear[prob] = array[i];
        }
        //Quad Probing
        for (int i = 0; i < array.length; i++) {
            int prob = (2 * array[i] + 5) % array.length;
            int temp = prob;
            int n = 0;
            while(quad[temp] != 0) {
                temp = (prob + (int)Math.pow(n, 2)) % array.length;
                n++;
                if(n == 100) {
                    break;
                }
            }
            quad[temp] = array[i];
        }
        for(int i = 0; i < array.length; i++) {
            int prob = (2 * array[i] + 5) % array.length;
            int offset = 7 * (array[i]  % 7);

            while(doubleHashing[prob] != 0)
            {
                prob = (prob + offset) % array.length;
            }
            doubleHashing[prob] = array[i];
        }
        System.out.println("Inserted Keys: " + Arrays.toString(array));
        System.out.println("Linear Probing: " + Arrays.toString(linear));
        System.out.println("Quad Probing: " + Arrays.toString(quad));
        System.out.println("Double Hashing: " + Arrays.toString(doubleHashing));
        delete(12);
        System.out.println("Linear Probing: " + Arrays.toString(linear));
    }

    static Integer[] linear = new Integer[11];
    static int hashCode(int key){
        return (2 * key + 5);
    }
    static void delete(int key) {
        int index = hashCode(key) % linear.length;
        int start = index;
        while (true) {
            if (linear[index] == null) {
                System.out.println(key + " Not Found");
                return;
            } else if (linear[index] == key) {
                linear[index] = null; // Mark the slot as empty
                break;
            }
            index = (index + 1) % linear.length;
            if (index == start) {
                System.out.println(key + " Not Found");
                return;
            }
        }
        // Rearrange elements
        int nextIndex = (index + 1) % linear.length;
        while (linear[nextIndex] != null) {
            int newIndex = hashCode(linear[nextIndex]) % linear.length;
            if (newIndex <= index) {
                linear[index] = linear[nextIndex];
                index = nextIndex;
                linear[nextIndex] = null;
            }
            nextIndex = (nextIndex + 1) % linear.length;
        }
    }
    
}
