import java.util.*;

public class AiNodeMaster {

	public AiNodeMaster  parentNode;
	public ArrayList<AiNodeMaster> childNode = new ArrayList<AiNodeMaster>();
	public short  alpha;
	public short  beta;
	public char choise;
	public short  nodeValue;
	public char  currentBoard[];

	public AiNodeMaster(AiNodeMaster _par, char _currentBoard[], short _nodeValue, char _choise, short _alpha, short _beta) {
		parentNode = _par;
		currentBoard = _currentBoard;
		nodeValue = _nodeValue;
		choise = _choise;	
		alpha = _alpha;
		beta = _beta;	
	}

	public short getAlpha() {
		return alpha;
	}

	public void setAlpha(short _alpha){
		alpha = _alpha;
	}

	public short getBeta() {
		return beta;
	}

	public void setBeta(short _beta){
		beta = _beta;
	}

	public short getNodeValue() {
		return nodeValue;
	}

	public AiNodeMaster getParent(){
		return parentNode;
	}

	public char getChoise() {
		return choise;
	}

	public char[] move(short ambo, short player, char[] board)
	{
		short start = ambo;
		short n = 0;
		short current;
		if(player == 2)
		{
			start +=7;
		}
		if ((int)board[start] != 0) {
			n = (short)board[start];
			board[start] = 0;
			current = start;


			for(int i=n; i>0; i--)
			{
				current++;
				if (current > 13) {
					current = 0;
				}
				if (player == 2 && current == 7) {
					current++;
				}
				else if (player == 1 && current == 0) {
					current++;
				}
				board[current] = (char)((int)board[current]+1);				
			}

			if ( (player == 1) && (current > 0 && current < 7) && ((int)board[current] == 1) && ((int)board[current+7] > 0)) {
				board[7] = (char)((int)board[7] + (int)board[current] + (int)board[current+7]);
				board[14] = (char)next(board[14]);
			}
			else if ((player == 2) && (current < 14 && current > 7) && ((int)board[current] == 1) && ((int)board[current] > 0) ) {
				board[0] = (char)((int)board[0] + (int)board[current] + (int)board[current-7]);
				board[14] = (char)next(board[14]);
			}

			if (!(((player == 1) && (current == 7)) || ((player == 2) && (current == 0)))) {
				board[14] = (char)next(board[14]);
			}
		}	
		return board;
	}

	public int next(int prev)
	{
		if(prev == 1)
			return 2;
		else
			return 1;
	}
	

	public char[] getBoard() {
		return currentBoard;
	}

}