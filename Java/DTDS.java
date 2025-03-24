package Java;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

class DTDS {

    static boolean emailSent = true;

    public static void main(String[] args) {

        byte x1 = Byte.MAX_VALUE;
        short x2 = Short.MAX_VALUE;
        int x3 = Integer.MAX_VALUE;
        long x4 = Long.MAX_VALUE;
        float x5 = Float.MAX_VALUE;
        double x6 = Double.MAX_VALUE;

        char x7 = Character.MAX_VALUE;
        String x8 = "something";

        var info = String.format("%b, %d, %d, %d, %d, %f, %f, %c, %s",
                emailSent, x1, x2, x3, x4, x5, x6, x7, x8);
        System.out.print(info);

        // Collections: Sequences, Set, Maps
        int[][] numbers = { { 1, 2 }, { 2, 3 } };
        List<String> names = Arrays.asList("Bishnu", "Brahma", "Mahesh");
        Set<Float> weights = Set.of(23.4f, 45.6f);
        Dictionary<String, Float> people = new Hashtable<>();
        people.put("bsr", 2.0f);
        System.out.println(numbers);

        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        var result = Arrays.stream(nums)
                .filter(i -> i % 2 == 0)
                .map(x -> x * x)                
                .toArray();
    }

    // Funtions
    boolean sendEmail(
            List<String> addresses,
            String sender,
            List<String> cc,
            String subject,
            List<String> body,
            EmailType emailType) {
        return emailSent;
    }
}

enum EmailType {
    INTERNAL, CONFENDENTIAL, PUBLIC
}
