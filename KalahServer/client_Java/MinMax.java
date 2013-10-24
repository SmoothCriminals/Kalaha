import java.util.*;

public class MinMax{

	AiNodeMaster bestBoard;
	int nodeDepth;

	public MinMax(int _depth, char[] _board){
		nodeDepth = _depth;
		AiNodeMaster tree;
		tree = new AiNodeMaster(null, _board, (short)0, (char)10);
		tree.setBeta((short)32700);
		tree.setAlpha((short)-32700);		
		DLS2(tree, _depth, _board[14], _board);		
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
					
					if (Max == _board[14] && Max == 1) {
						childNodeValue = (short)(node.getNodeValue() + ((childBoard[7] - childBoard[0])*depth));	
						end = alphaBeta(node, true, childNodeValue, (short)32700);
											
					} else if (Max == _board[14] && Max == 2){
						childNodeValue = (short)(node.getNodeValue() + ((childBoard[0] - childBoard[7])*depth));
						end = alphaBeta(node, true, childNodeValue, (short)32700);
					}
					else if(Max != _board[14] && Max == 1){
						childNodeValue = (short)(node.getNodeValue() - ((childBoard[0] - childBoard[7])*depth));
						end = alphaBeta(node, false, (short)-32700, childNodeValue);
					}
					else if(Max != _board[14] && Max == 2){
						childNodeValue = (short)(node.getNodeValue() - ((childBoard[7] - childBoard[0])*depth));
						end = alphaBeta(node, false, (short)-32700, childNodeValue);
					}
					
					if(!end){
						node.childNode.add(new AiNodeMaster(node, childBoard, childNodeValue, (char)i));
						if (nodeDepth == depth && counter == 0) {
							bestBoard = node.childNode.get(counter);
						}
						else if (bestBoard.getNodeValue() < childNodeValue) {
							bestBoard = node.childNode.get(counter);
						}
						
						counter++;
						
						DLS2(node.childNode.get(counter-1), depth-1, Max, childBoard);	
					}		
				}	
			}			
		}
	}


	public boolean alphaBeta(AiNodeMaster node, boolean MaxNode, short alpha, short beta){
		
		if (MaxNode) {
			for (int i = 0; i < node.childNode.size(); i++) {
				alpha = maxAlphaBeta(alpha, node.getAlpha());
				if (alpha >= beta) {
					return true;		
				}
			node.setAlpha(maxAlphaBeta(alpha, node.getAlpha()));	
			}			
		
			if (node.childNode.size() != 0) {
				node.setAlpha(alpha);
			}
		}
		else {
			for (int i = 0; i < node.childNode.size(); i++) {
				beta = maxAlphaBeta(beta, node.getBeta());
				if (beta <= alpha) {
					return true;		
				}
				node.setBeta(maxAlphaBeta(beta, node.getBeta()));	
			}
			if (node.childNode.size() != 0) {
				node.setBeta(beta);
			}
		}
		
		return false;
	}

	public short maxAlphaBeta(short m1, short m2){
		if (m2 > m1) {
			return m2;
		}
		else{
			return m1;
		}
	}

}