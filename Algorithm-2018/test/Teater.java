public class Teater {
	public void Main() {
		Solutions sol = new Solutions();
		int[] input611 ={1,2,2,3,1,4,2};
		int sol611 = sol.sol611(input611);
		String str611 = Integer.toString(sol611);
		DisplaySol("611",str611);
		
	}
	
	public void DisplaySol(String quizindex,String sol) {
		String output = quizindex + " result:" + sol;
		System.out.println(output);
	}
}
