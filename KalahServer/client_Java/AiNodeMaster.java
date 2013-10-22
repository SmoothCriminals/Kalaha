import java.util.*;

public class AiNodeMaster {

	public AiNodeMaster  parentNode;
	public ArrayList<AiNodeMaster> childNode = new ArrayList<AiNodeMaster>();
	public char  alpha;
	public char  beta;
	public char choise;
	public char  nodeValue;
	public char  currentBoard[];

	public AiNodeMaster(AiNodeMaster _par, char _currentBoard[], char _nodeValue, char _choise) {
		parentNode = _par;
		currentBoard = _currentBoard;
		nodeValue = _nodeValue;
		choise = _choise;		
	}

	public char getAlpha() {
		return alpha;
	}

	public void setAlpha(char _alpha){
		alpha = _alpha;
	}

	public char getBeta() {
		return beta;
	}

	public void setBeta(char _beta){
		beta = _beta;
	}

	public char getNodeValue() {
		return nodeValue;
	}

	public AiNodeMaster getParent(){
		return parentNode;
	}

	public char getChoise() {
		return choise;
	}

	public char[] move(char ambo, int player, char[] board)
	{
		char start = ambo;
		char n = 0;
		int current;
		if(player == 2)
		{
			start +=7;
		}
		if (board[start] != 0) {
			n = board[start];
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
				board[current] = board[current]++;				
			}

			if ( (player == 1) && (current > 0 && current < 7) && (board[current] == 1) && (board[current+7] > 0)) {
				board[7] = board[7] + board[current] + board[current+7];
				board[14] = next(board[14]);
			}
			else if ((player == 2) && (current < 14 && current > 7) && (board[current] == 1) && (board[current] > 0) ) {
				board[0] = board[0] + board[current] + board[current-7];
				board[14] = next(board[14]);
			}

			if (!(((player == 1) && (current == 7)) || ((player == 2) && (current == 0)))) {
				board[14] = next(board[14]);
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