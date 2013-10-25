	


class aiThread extends Thread {
		
	public boolean isDone = false;
	public int aiChoice = -1;
	public char[] board;
	public int depth;
	private MinMax myMinMax;


	public void run() {
		//System.out.println("MinMaxStart!");

		myMinMax = new MinMax(depth, board);
		aiChoice = myMinMax.evaluation();


		//System.out.println("MinMaxFinished!");
		//try {
			//sleep(100);
		//} catch(InterruptedException e) {
	
		//choice}
		isDone = true;
		System.out.println("Depth: "+depth+" calculated!");
	}	
}