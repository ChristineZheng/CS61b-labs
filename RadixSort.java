import java.util.ArrayList;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra
 * @version 1.4 - April 14, 2016
 *
 **/
public class RadixSort {
    /**
     * Does Radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     **/
    public static String[] sort(String[] asciis) {
        String[] copy = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            copy[i] = asciis[i];
        }
        sortHelper(copy, 0, asciis.length - 1, 0);
        return copy;
    }
    /**
     * Radix sort helper function that recursively calls itself to achieve the sorted array
     * destructive method that changes the passed in array, asciis
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelper(String[] asciis, int start, int end, int index) {
        //TODO use if you want to
        if (start < end) {

            for (int i = 0; i < asciis.length; i++) {
                System.out.println(asciis[i]);
            }
            int[] list = new int[257];
            System.out.println("start " + start);
            System.out.println("end " + end);
            for (int i = start; i <= end; i++) {
                if (asciis[i].length() <= index) {
                    list[0]++;
                } else {
                    int dec = (int) asciis[i].charAt(index) + 1;
                    System.out.println("char " + (dec - 1));
                    //System.out.println(dec);
                    list[dec]++;
                    System.out.println("list incre " + list[dec]);
                }
            }
            boolean isFirst = true;
            for (int i = 1; i < list.length; i++) {
                if (list[i] != 0) {
                    System.out.println("index " + i);
                    System.out.println("num at i " + list[i]);
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        int p = i;
                        System.out.println("not first at i " + p);
                        while (list[p - 1] == 0) {
                            p--;
                        }
                        System.out.println("p - 1 = " + (p - 1));
                        list[i] += list[p - 1];
                        System.out.println("updated " + list[i]);
                    }
                }
            }
            ArrayList<Integer> helper = new ArrayList<>();
            for (int i = 0; i < list.length; i++) {
                if (list[i] != 0) {
                    helper.add(list[i]);
                }
            }
            System.out.println("helper start .....");
            for (int i = 0; i < helper.size(); i++) {
                System.out.println(helper.get(i));
            }

            int asciisLength = asciis.length;
            //int asciisLength = end - start + 1;
            String[] result = new String[asciisLength];
            //for (int i = 0; i < asciisLength; i++) {
            for (int i = start; i <= end; i++) {
                String cur = asciis[i];
                System.out.println(cur);
                int first = (int) cur.charAt(index) + 1;
                System.out.println(cur.charAt(index));
                System.out.println("charAt + 1 " + first);
                System.out.println("list[first] " + list[first]);
                list[first]--;
                System.out.println("after decre/position " + list[first]);
                int position = list[first];
                result[position] = cur;
            }
            //asciis = result;
            for (int i = start; i <= end; i++) {
                System.out.println("i - start= " + result[i - start]);
                System.out.println("asciis at i= " + asciis[i]);
                asciis[i] = result[i - start];
            }

            System.out.println("begin asciis");
            for (int i = 0; i < asciis.length; i++) {
                System.out.println(asciis[i]);
            }
            boolean first = true;
            System.out.println("size " + helper.size());
            for (int i = 0; i < helper.size(); i++) {
                int cur = helper.get(i);
                System.out.println("cur " + cur);
                if (first) {
                    first = false;
                    if (cur > 1) {
                        System.out.println("asciis start at: " + asciis[0] + ", end at: " + (cur - 1));
                        sortHelper(asciis, 0, cur - 1, index + 1);
                    }
                } else {
                    int diff = cur - helper.get(i - 1);
                    System.out.println("diff " + diff);
                    System.out.println("  ");
                    if (diff > 1) {
                        System.out.println("hi");
                        sortHelper(asciis, helper.get(i - 1), cur - 1, index + 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] input = {"zs", "fases", "fsf23", "s3", "32d", "1@", "(fs", "663", "fascs"};
        String[] result = sort(input);
        System.out.println("result");
        for (String k : result) {
            System.out.println(k);
        }
    }
}
