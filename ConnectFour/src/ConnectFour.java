import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
//Shreyes Joshi, 10/30/17, This is a button clicking tic tac toe game
public class ConnectFour implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton[][] button = new JButton[3][3];
	int board[][] =  new int [7][6];
	final int BLANK =  0;
	final int X_MOVE =  1;
	final int O_MOVE =  2;
	final int X_TURN =  0;
	final int O_TURN =  1;
	int turn =  X_TURN;
	int xWins = 0;
	int oWins = 0;
	//creating the label that will contain the number of wins
	JLabel xname  = new JLabel ("X has won: "+ xWins +" games");
	JLabel oname  = new JLabel ("O has won: "+  oWins + " games");
	//adding buttons for changing names
	JButton TwoP = new JButton("Two Players");
	JButton OneP = new JButton("One Player");
	JButton changeXname = new JButton("Change name");
	JTextField xChangeField = new JTextField();
	JButton changeOname = new JButton("Change name");
	JTextField oChangeField = new JTextField();
	//creating new containers for the playing field
	Container center = new Container();
	Container north = new Container();
	//setting original player name
	String xplayername = "X";
	String oplayername = "0";
	
	public ConnectFour() {
		//setting size
		frame.setSize(600,600);
		//setting layout for field and name structures
		frame.setLayout(new BorderLayout());
		center.setLayout(new GridLayout(3, 3));
		
		north.add(xname);
		//adding name to the grip
		north.add(changeXname);
		//Adding name change functions to the names
		changeXname.addActionListener(this);
		//if clicked do something
		north.add(xChangeField);
		//text box to insert the change game
		north.add(oname);
		north.add(changeOname);
		changeOname.addActionListener(this);
		north.add(oChangeField);
		frame.add(north, BorderLayout.NORTH);
		
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[0].length; j++) {
				//adding buttons for the 3 by three grid
				button [j][i] = new JButton("");
				center.add(button[j][i]);
				button[j][i].addActionListener(this);
				//if the button is clicked, execute an action
			}
		}
		frame.add(center, BorderLayout.CENTER);
		
		frame.setVisible(true);
		north.setLayout(new GridLayout(1,2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new GUITicTacToe();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton current = null;
		boolean gridButton =  false;
		for (int i = 0; i < button.length; i++) {
			for (int j = 0; j < button[0].length; j++) {
				if(event.getSource().equals(button[j][i])) {
					//if the source is that a button on the grid was clicked
					gridButton = true;
					//dont go into change name
					current = button[j][i];
					//set button clicked to the buton that was clicked
					if (board[j][i] == BLANK) {	
						//check if spot is blank
						if(turn == X_TURN) {
							//if turn is x then put an X on the spot clicked, and register it as an X spot for win check
							current.setText("X");
							board[j][i] =  X_MOVE;
							turn= O_TURN;
							//set turn to O
							if(checkXWin()) {
								//if someone has just one , you know its X
								xWins = xWins +1;
								xname.setText(xplayername+ " has won " + xWins + " games");
								//add onto x wins and add print in the x wins space
								resetBoard();
								turn = X_TURN;
								//reset board and set the turn to X in preparation for the next game
							}
							else if (checkTie()) {
								//if a tie reset board and set the turn to X in preparation for the next game
 
								resetBoard();
								turn = X_TURN;
							}
						}
						else if (turn == O_TURN) {
							//logic same as X, works for O
							current.setText("O");
							board[j][i]= O_MOVE;
							turn =  X_TURN;
							if(checkOWin()) {
								oWins = oWins +1;
								oname.setText(oplayername + " has won "+ oWins + " games");
								resetBoard();
								
							}
							else if (checkTie()) {
								resetBoard();
							}
						}
					}
			}
			
		}
		if(gridButton == false) {
			if(event.getSource().equals(changeXname) == true) {
				//if the name is  changed
				if (!xChangeField.getText().equals("")){
						xplayername = xChangeField.getText();
						xname.setText(xplayername+ " has won " + xWins + " games");
						//print the X wins
				}
			}
			else if(event.getSource().equals(changeOname)== true) {
				//if the O name is changed
				if (!oChangeField.getText().equals("")){
				oplayername = oChangeField.getText();
				oname.setText(oplayername+ " has won " + oWins + " games");
				//print 0
				}
			}
		}
		
		
		}
	}
	public boolean checkXWin(){
		//checking all possible x win situations returning value to true if x has won
		if(board[0][0]== X_MOVE && board[0][1]== X_MOVE && board[0][2]== X_MOVE ){
			return true;
		}
		else if(board[1][0]== X_MOVE && board[1][1]== X_MOVE && board[1][2]== X_MOVE ){
			return true;
		}
		else if(board[2][0]== X_MOVE && board[2][1]== X_MOVE && board[2][2]== X_MOVE ){
			return true;
		}
		else if(board[0][0]== X_MOVE && board[1][0]== X_MOVE && board[2][0]== X_MOVE) {
			return true;
		}
		else if(board[0][1]== X_MOVE && board[1][1]== X_MOVE && board[2][1]== X_MOVE) {
			return true;
		}
		else if(board[0][2]== X_MOVE && board[1][2]== X_MOVE && board[2][2]== X_MOVE) {
			return true;
		}
		else if(board[0][0]== X_MOVE && board[1][1]== X_MOVE && board[2][2]== X_MOVE) {
			return true;
		}
		else if(board[0][2]== X_MOVE && board[1][1]== X_MOVE && board[2][0]== X_MOVE) {
			return true;
		}
		else{
			return false;
			//false if they did not
		}
		
	}
	
	public boolean checkOWin(){
		//checking all possible 0 win situations returning value to true if o has won
		if(board[0][0]== O_MOVE && board[0][1]== O_MOVE && board[0][2]== O_MOVE ){
			return true;
		}
		else if(board[1][0]== O_MOVE && board[1][1]== O_MOVE && board[1][2]== O_MOVE ){
			return true;
		}
		else if(board[2][0]== O_MOVE && board[2][1]== O_MOVE && board[2][2]== O_MOVE ){
			return true;
		}
		else if(board[0][0]== O_MOVE && board[1][0]== O_MOVE && board[2][0]== O_MOVE) {
			return true;
		}
		else if(board[0][1]== O_MOVE && board[1][1]== O_MOVE && board[2][1]== O_MOVE) {
			return true;
		}
		else if(board[0][2]== O_MOVE && board[1][2]== O_MOVE && board[2][2]== O_MOVE) {
			return true;
		}
		else if(board[0][0]== O_MOVE && board[1][1]== O_MOVE && board[2][2]== O_MOVE) {
			return true;
		}
		else if(board[0][2]== O_MOVE && board[1][1]== O_MOVE && board[2][0]== O_MOVE) {
			return true;
		}
		else{
			return false;
			//false if they did not
		}
		
	}
	public boolean checkTie() {
		//function inspired by Sachin Pillai
		System.out.println();
		for(int row = 0; row < board.length; row++){
			for(int column = 0; column < board[0].length; column++){
				if(board[row][column]== BLANK){
					return false;
					//if there is a possible space to play return true
				}
			}
		}
		return true;
		//if there isn't an open spot return as a tie
		
	}
	public void resetBoard(){
		for(int a = 0; a < board.length; a++ ) {
			for (int b = 0; b < board.length; b++) {
				board[a][b] = BLANK;
				button[a][b].setText("");	
			}
		}
	}
}
