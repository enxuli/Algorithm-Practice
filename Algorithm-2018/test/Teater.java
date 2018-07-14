import java.util.List;

public class Teater {
	public static void main(String[] args) {
		Solutions sol = new Solutions();
		int[] input611 ={1,2,2,3,1,4,2};
		int sol611 = sol.sol611(input611);
		String str611 = Integer.toString(sol611);
		DisplaySol("611",str611);
		
		String input592 = "-1/2+1/2";
		String str592 = sol.sol592(input592);
		DisplaySol("592",str592);
		
		int[] input15 ={-1,0,1,2,-1,-4};
		//List<List<Integer>> sol15 = sol.sol15(input15);
		//List<List<Integer>> sol15p = sol.sol15p(input15);
		
		String input20 = "{[[()][{()}]}";
		String output20 = Boolean.toString(sol.sol20p(input20));
		DisplaySol("20",output20);
		
		//List<String> sol22p = sol.sol22p(3);
		
		
	}
	
	public static void DisplaySol(String quizindex,String sol) {
		String output = quizindex + " result:" + sol;
		System.out.println(output);
	}
}
