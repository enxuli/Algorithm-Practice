
public class StringManipulate {

	
	//6. ZigZag Conversion
    public String convert(String s, int numRows) {
        char[] array = s.toCharArray();
        int length = array.length;
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) sb[i] = new StringBuilder();
        int index = 0;
        while(index< length){
            for (int i = 0; i < numRows; i++) if(index<length)sb[i].append(array[index++]);
            for (int i = numRows-2; i > 0; i--) if(index<length)sb[i].append(array[index++]);
        }
        for (int i = 1; i < numRows; i++) sb[0].append(sb[i]);
        return sb[0].toString();
        
    }
    //459 Repeated Substring Pattern
    //https://www.geeksforgeeks.org/searching-for-patterns-set-2-kmp-algorithm/
    //Introduce you the KMP ALGORITHM!!!
    public boolean repeatedSubstringPattern2(String str) {
        //This is the kmp issue
        int[] prefix = kmp(str);
        int len = prefix[str.length()-1];
        int n = str.length();
        return (len > 0 && n%(n-len) == 0);
    }
    private int[] kmp(String s){
        int len = s.length();
        int[] res = new int[len];
        char[] ch = s.toCharArray();
        int i = 0, j = 1;
        res[0] = 0;
        while(i < ch.length && j < ch.length){
            if(ch[j] == ch[i]){
                res[j] = i+1;
                i++;
                j++;
            }else{
                if(i == 0){
                    res[j] = 0;
                    j++;
                }else{
                    i = res[i-1];
                }
            }
        }
        return res;
    }
    //This can also be n*n/2
    public boolean repeatedSubstringPattern(String s) {
        int len = s.length();
        for(int i = len / 2; i >= 1; i--) {
            if(len % i == 0) {
                int m = len/i;
                String subS = s.substring(0, i);
                int j;
                for(j = 1; j < m; j++) {
                    if(!subS.equals(s.substring(j * i, i + j * i))) {
                        break;
                    }
                }
                if(j == m) {
                    return true;
                }
            }
        }
        return false;
    }
}
