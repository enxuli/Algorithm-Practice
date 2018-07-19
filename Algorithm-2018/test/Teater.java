import java.util.List;

public class Teater {
	public static void main(String[] args) {
		Solutions sol = new Solutions();
		BackTrackingCollection backsol = new BackTrackingCollection();
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
		
		int[] input33 = {6,7,8,9,0,1,2,3,4,5};
		int sol33 = sol.sol33(input33,0);
		String str33 = Integer.toString(sol33);
		DisplaySol("33",str33);
		
		String ouput38 = sol.sol38(4);
		String ouput38p = sol.sol38p(4);
		DisplaySol("38",ouput38p);
		
		String input131 = "aab";
		List<List<String>> output131 = backsol.sol131(input131);
		output131.get(1).size();
		DisplaySol("131",output131.toString());
		
		char[][] input79 = {{'A','A'}};
		String input79p = "AAA";
		String output79 = Boolean.toString(backsol.sol79(input79,input79p));
		DisplaySol("79",output79);
		
		String input93 = "123023490";
		List<String> output93 = backsol.sol93(input93);
		DisplaySol("93",output93.toString());
		
		int input357 = 5;
		int output357 = backsol.sol357(input357);
		DisplaySol("357",Integer.toString(output357));
	}
	
	public static void DisplaySol(String quizindex,String sol) {
		String output = quizindex + " result:" + sol;
		System.out.println(output);
	}
}
