import java.util.Comparator;

/**
 * My own comparator which can sort String in ACSII order
 *
 * Algorithm:
 *     Compare the characters in two Strings S1 and S2 from begin to end.
 *     if S1[i] < S2[i]
 *        then S1 < S2
 *     else
 *        then S1 > S2
 *     if S1 reaches the end
 *        S1 < S2
 *        if S2 reaches the end
 *            S1 > S2
 *        else
 *           S1 = S2
 *
 * Created by wuyanzhe on 7/8/15.
 */
public class MyComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {

        char[] parts1 = s1.toCharArray();
        char[] parts2 = s2.toCharArray();
        for(int i=0;i<parts1.length && i<parts2.length;i++){
            if(parts1[i] < parts2[i]){
                return -1;
            }else{
                if(parts1[i] > parts2[i]){
                    return 1;
                }
            }
        }
        if(parts1.length>parts2.length){
            return 1;
        }else{
            if(parts1.length<parts2.length){
                return -1;
            }else{
                return 0;
            }
        }
    }
}
