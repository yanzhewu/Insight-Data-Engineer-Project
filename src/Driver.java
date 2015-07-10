import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by wuyanzhe on 7/7/15.
 */

/*The program will HashMap to check the uniqueness of words(Trie may occupy less space but a little bit slower for checking Uniqueness)
 *Tweets are limited in 140 chars
 */


public class Driver {

    public static void main(String[] args) {
        handle_data("tweet_input/tweets.txt", "tweet_output/");
        //test();
    }

    /**
     * Locate unique words with their frequency and compute rolling median number in the stream
     * @param filename
     * @param out_directory
     */
    public static void handle_data(String filename, String out_directory){
        File input_file = new File(filename);
        File output_file1 = new File(out_directory+"ft1.txt");
        File output_file2 = new File(out_directory+"ft2.txt");
        if(input_file.exists()){
            try{
                Helper.check_file(output_file1);
                Helper.check_file(output_file2);
                BufferedReader br = new BufferedReader(new FileReader(input_file));
                Words_Tweeted.handle_tweets(br,output_file1,output_file2);
            }catch (IOException e){
                e.printStackTrace();
            }

        }else{
            System.out.println("No input file in the directory.");
            System.exit(-1);
        }
    }

    /**
     * Aims to test some methods, check if they work properly
     */
    public static void test(){
//        String line1 = "is #bigdata finally the answer to end poverty? @lavanyarathnam http://ow.ly/o8gt3 #analytics";
//        String line2 = "interview: xia wang, astrazeneca on #bigdata and the promise of effective healthcare #kdn http://ow.ly/ot2uj";
//        String line3 = "big data is not just for big business. on how #bigdata is being deployed for small businesses: http://bddy.me/1bzukb3 @cxotodayalerts #smb";
//        int num = Helper.unique_num(line3);
//        System.out.println(num);

//        Map<String, Integer> map = new HashMap<String, Integer>();
//        String s1 = "@cxotodayalerts";
//        String s2 = "of";
//        String s3 = "#smb";
//        map.put(s2, 1);
//        map.put(s1, 1);
//        map.put(s3, 1);
//        List<String> list = new ArrayList<String>(map.keySet());
//        Collections.sort(list,new MyComparator());
//        System.out.println();

//        String s = "a    b    ";
//        String[] parts = s.split(" ");
//        System.out.println();



//        int[] occurrence = new int[140];
//        occurrence[11] = 1;
//        System.out.println(Helper.get_median(occurrence,1));
//        occurrence[30] = 1;
//        System.out.println(Helper.get_median(occurrence,2));
//        occurrence[50] = 1;
//        System.out.println(Helper.get_median(occurrence,3));
//        System.out.println(Math.round(2.0/3.0 * 100.0)/100.0);

//        String formatStr = "%-25s%15d%n";
//        String s = String.format(formatStr,"#analytics",1);
//        String s1 = String.format(formatStr,"#bigdata",1);
//        System.out.print(s);
//        System.out.print(s1);
//        System.out.println();


    }
}
