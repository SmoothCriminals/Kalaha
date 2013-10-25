import java.util.*;

public class MinMax{

	AiNodeMaster bestBoard;
	int nodeDepth;
	boolean first = true;
	public int nodeCount = 0;

	public MinMax(int _depth, char[] _board){
		nodeDepth = _depth;
		AiNodeMaster tree;
		tree = new AiNodeMaster(null, _board, (short)0, (char)10, (short)-32700, (short)32700);	
		bestBoard = tree;	
		DLS2(tree, _depth, (int)_board[14], _board);		
	}

	public int evaluation(){
		while(bestBoard.getParent().getChoise() != 10){
			bestBoard = bestBoard.getParent();
		}
		return bestBoard.getChoise();
	}


	public void DLS2(AiNodeMaster node, int depth, int Max, char[] _board) {
		char childBoard[];
		short childNodeValue = (short)0;
		int counter = 0;
		boolean end = false;
		
		
		if (depth > 0) {				
			for (int i = 1; i<7; i++) {
				childBoard = node.move((short)i, (short)_board[14], _board.clone());
				if (!Arrays.equals(childBoard, _board)) {
					
					if (MaxNode(Max, _board)) {
						node.setAlpha(alphaBeta(node, depth, Max, (short)-32700, (short)32700));
						if (Max == 1) {
							childNodeValue = (short)(node.getNodeValue() + ((childBoard[7] - childBoard[0])));
						}
						else{
							childNodeValue = (short)(node.getNodeValue() + ((childBoard[0] - childBoard[7])));
						}
					}
					else {
						node.setBeta(alphaBeta(node, depth, Max, (short)-32700, (short)32700));
						if (Max == 1) {
							childNodeValue = (short)(node.getNodeValue() - ((childBoard[0] - childBoard[7])));
						}
						else{
							childNodeValue = (short)(node.getNodeValue() - ((childBoard[7] - childBoard[0])));
						}
					}
					if (node.getAlpha() >= node.getBeta()) {
						end = true;
					}
					if(!end){
						node.childNode.add(new AiNodeMaster(node, childBoard, childNodeValue, (char)i, node.getAlpha(), node.getBeta() ));
						//System.out.println("Value: "+childNodeValue+" choise: "+i+" Depth: "+depth+" Alpha"+node.getAlpha()+" Beta: "+node.getBeta());
						if (depth == nodeDepth && counter == 0 && first) {
							bestBoard = node.childNode.get(counter);
							first = false;
						}
						if (bestBoard.getNodeValue() < childNodeValue) {
							//System.out.println(" the best choise is: "+i);
							bestBoard = node.childNode.get(counter);
						}		
						
						counter++;
						nodeCount++;
						DLS2(node.childNode.get(counter-1), depth-1, Max, childBoard);	
					}
					else{
						//System.out.println("break");
					}							
				}	
			}			
				
		}
	}	


	public boolean MaxNode(int Max, char[] _board){
		if ((int)_board[14] == Max){
			return true;
		}
		else{
			return false;
		}
	}

	public short alphaBeta(AiNodeMaster node, int depth, int Max, short alpha, short beta){

		if (depth == 0) {
			return node.getNodeValue();
		}
		if (MaxNode(Max, node.getBoard())) {
			for (int i = 0; i < node.childNode.size(); i++) {
				alpha = max(alpha, alphaBeta(node.childNode.get(i), depth-1, Max, alpha, beta));
				if (alpha >= beta) {
					break;
				}				
			}	
			return alpha;
		}
		else{
			for (int i = 0; i < node.childNode.size(); i++) {
				beta = min(beta, alphaBeta(node.childNode.get(i), depth-1, Max, alpha, beta));	
				if (alpha >= beta) {
					break;
				}				
			}
			return beta;
		}	
	}

	public short max(short m1, short m2){
		if (m2 > m1) {
			return m2;
		}
		else{
			return m1;
		}
	}

	public short min(short m1, short m2){
		if (m2 < m1) {
			return m2;
		}
		else{
			return m1;
		}
	}

}