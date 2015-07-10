import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuyanzhe on 7/7/15.
 */
public class Words_Tweeted {
    private static long[] occurrence = new long[140];
    private static Map<String, Long> words = new HashMap<String, Long>();
    private static long lines_count;

    /**
     * Hash unique words with their occurrences and compute median number with rolling median algorithm
     * @param bufferedReader
     * @param output1
     * @param output2
     * @throws IOException
     */
    public static void handle_tweets(BufferedReader bufferedReader, File output1, File output2) throws IOException{
        Arrays.fill(occurrence,0); // Initialize it to all zero
        String line = null;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output2)); //append
        while((line=bufferedReader.readLine())!=null){
            // median part starts
            int num = Helper.unique_num(line);
            occurrence[num]++;
            lines_count++;
            double median = Helper.get_median(occurrence, lines_count); //done
            bufferedWriter.write(String.valueOf(median)+"\n"); // keep two digits
            //median part ends

            Helper.addWords(words, line); //hash unique words
        }
        bufferedWriter.close();
        Helper.populateWords(words,output1);
    }

}
