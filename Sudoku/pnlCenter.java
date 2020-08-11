package Sudoku;

// imports
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.*;

public class pnlCenter extends JPanel{
//--------initializing--------
    // row and col of the whole board
    // width and height of each square
    int row, col, width, height;
    
    // btns: the textfields to get the board
    // board: the text of the board
    JTextField[][] fields;
    int total = 1+2+3+4+5+6+7+8+9;
    int[][] board;
    
    // constructor
    public pnlCenter(){
        init_var();
        setLayout(new GridLayout(row, col));
        init_btns();
    }
    
    // initializing variables
    public void init_var(){
        row = 9; col = 9;
        width = 3; height = 3;
        fields = new JTextField[row][col];
        board = new int[row][col]; // zero indexed
    }
    
    // initializing buttons
    public void init_btns(){
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++){
                fields[i][j] = new JTextField();
                fields[i][j].setBackground(Color.yellow);
                fields[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                fields[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                fields[i][j].setName(i+"-"+j);
                add(fields[i][j]);
            }
    }
//--------end--------
    
//--------pnlNorth--------
    // starts the program
    /*
    * First gets the board.
    * Then loops the "solve" method and everytime checks if it had made changes
    * or not. if not, then breaks the loop and finishes the game by setting the
    * buttons' texts
    */
    public void start(){
        int[][] checker;
        if(getBoard()){
            while(true){
                checker = getCopyOf(board);
                solve();
                if(isEqual(board, checker))
                    break;
            }
            finisher();
        }
    }
    
    // gets the board from textfields
    public boolean getBoard(){
        // raises exception if theres none int field
        try {
            for(int i=0; i<row; i++)
                for(int j=0; j<col; j++)
                    if (!"".equals(fields[i][j].getText()))
                        board[i][j] = Integer.parseInt(fields[i][j].getText());
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "wrong input");
        }
        return false;
    }
    
    // cleans the board
    public void clean(){
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                fields[i][j].setText("");
        board = new int[row][col];
    }
//--------end--------
    
//--------algorithm--------
    // algorithm that finds the answer
    /*
    * For each check, finds how many numbers can fit in that and there is only
    * one number, then puts that number in that.
    * This mehtod will be looped until the answer is found.
    */
    public void solve(){
        ArrayList<Integer> temp;
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++){
                temp = new ArrayList<>();
                for(int number=1; number<=9; number++)
                    if(canFit(number, new Point(i, j)))
                        temp.add(number);
                if(temp.size()==1)
                    board[i][j] = temp.get(0);
            }
    }
    
    // checks if the given number is in the given square
    public boolean inSquare(ArrayList<Point> sq, Point coord){
        for(Point p: sq)
            if(p.x==coord.x && p.y==coord.y)
                return true;
        return false;
    }
    
    // returns the coordinate of the square that the given coordinate is in
    public Point findSquare(Point coord){
        for(int i=0; i<row; i+=height)
            for(int j=0; j<col; j+=width)
                if(inSquare(square(i, j), coord))
                    return new Point(i, j);
        return new Point(); // it's just for the error that it gives
    }
    
    // checks if the given number can fit in the given coordinate
    public boolean canFit(int num, Point coord){
        Point temp;
        // if it's not a number
        if(board[coord.x][coord.y]==0){
            // checking row
            for(int j=0; j<col; j++)
                if(board[coord.x][j]==num)
                    return false;
            
            // checking col
            for(int i=0; i<row; i++)
                if(board[i][coord.y]==num)
                    return false;
            
            // checking square
            Point sqCoord = findSquare(coord);
            for(Point p: square(sqCoord.x, sqCoord.y))
                if(board[p.x][p.y]==num)
                    return false;
        }
        return true;
    }
//--------end--------
    
//--------side methods--------
    /* returns an array containing coordinates of the square starting from
    the given point (it's the top left corner) */
    public ArrayList<Point> square(int x, int y){
        ArrayList<Point> squares = new ArrayList<>();
        for(int i=x; i<x+height; i++)
            for(int j=y; j<y+width; j++)
                squares.add(new Point(i, j));
        return squares;
    }
    
    // copies src array to the dest array
    public int[][] getCopyOf(int[][] src){
        int[][] dest = new int[row][col];
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                dest[i][j] = src[i][j];
        return dest;
    }
    
    // returns if the two arrays are equal or not
    public boolean isEqual(int[][] a1, int[][] a2){
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                if(a1[i][j]!=a2[i][j]) return false;
        return true;
    }
    
    // sets the answer on the textfields
    public void finisher(){
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                fields[i][j].setText(Integer.toString(board[i][j]));
    }
//--------end--------
}