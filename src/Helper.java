import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

/**
 * Created by wuyanzhe on 7/8/15.
 */
public class Helper {
    /*
     * This provides the following functions:
     *    1. Compute unique words from a string which splits by whitespace
     *    2. Compute the median number from a int array and a count (find the number at count/2)
     *    3. Check whether a file exists, if not, create it
     *    4. Add words in HashMap with their occurrences
     *    5. Get the position of first non-zero value in an array start from i
     *    6. Get the number at a specific position in the array
     *    7. Write data from HashMap to given file
     */

    /**
     * 1
     * @param line
     * @return number of unique words in a string
     */
    public static int unique_num(String line){
        Set<String> dict = new HashSet<String>();
        String[] words = line.trim().split(" ");
        int count = 0;
        for(int i=0;i<words.length;i++){
            if(!words[i].trim().equals("") && dict.add(words[i])){
                count++;
            }
        }
        return count;
    }

    /**
     * 2
     * Algorithm:
     *     Add the number from the head of the array until the sum reaches count/2
     *
     *
     * @param occurrence
     * @param count
     * @return
     */
    public static double get_median(long[] occurrence, long count){

        double res = 0.00;
        if(count==1){
            res = getNextOccurrence(occurrence,0);
        }else {
            if ((count & 1) == 0) {
                long pos1 = count / 2;
                long pos2 = count / 2 + 1;
                res = Math.round(((double)getOccurrenceAt(occurrence, pos1) + (double)getOccurrenceAt(occurrence, pos2)) / 2 * 100.0) / 100.0; //keep two digits
            } else {
                long pos = count / 2 + 1;
                res = Math.round((double)getOccurrenceAt(occurrence, pos)*100.0)/100.0; //keep two digits
            }
        }
        return res;
    }

    /**
     * 3
     * @param filename
     */
    public static void check_file(String filename){
        File file = new File(filename);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 3
     * @param file
     */
    public static void check_file(File file){
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 4
     * @param words
     * @param line
     */
    public static void addWords(Map<String, Long> words, String line){
        String[] parts = line.trim().split(" ");
        for(String s:parts){
            if(!s.trim().equals("")) {
                if (words.containsKey(s)) {
                    words.put(s, words.get(s) + 1);
                } else {
                    words.put(s, (long)1);
                }
            }
        }
    }

    /**
     * 5
     * @param occurrence
     * @param start
     * @return
     */
    public static int getNextOccurrence(long[] occurrence, int start){
        for(int i=start;i<occurrence.length;i++){
            if(occurrence[i] != 0){
                return i;
            }
        }
        return 0;
    }

    /**
     * 6
     * @param occurrence
     * @param pos
     * @return
     */
    public static int getOccurrenceAt(long[] occurrence, long pos){
        long sum = 0;
        for(int i=0;i<occurrence.length;i++){
            if(occurrence[i]!=0) {
                sum += occurrence[i];
                if (sum >= pos) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * 7
     * @param words
     * @param output
     */

    public static void populateWords(Map<String, Long> words, File output){
        List<String> list = new ArrayList<String>(words.keySet());
        Collections.sort(list,new MyComparator());
        String formatStr = "%-25s%15d%n";
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output));
            for(String curr:list){
                String line = String.format(formatStr,curr,words.get(curr));
                bufferedWriter.write(line);
            }
            bufferedWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
