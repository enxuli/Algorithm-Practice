
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
}
