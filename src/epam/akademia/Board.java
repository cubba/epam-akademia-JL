/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epam.akademia;

/**
 *
 * @author cubba
 */
public class Board {
    private int size;
    private char [][] boardCells;
    
    public Board(int size){
        this.size = size;
        boardCells = new char [size][size];
        
//        for(int i = 0; i< 3 ; i++){
//            for(int j = 0; j< 3; j++){
//                boardCells[i][j] = 'O';
//            }
//        }
    }
    
    public void setCell(int row, int col, char value){
        boardCells[row-1][col-1] = value;
    }
    
    public int getSize(){return this.size;}
    
    public void printBoard(){
        int boardSize = boardCells[0].length;
        for(int i = 0; i <= boardSize; i++){
            if(i == 0){
                System.out.print("\t");
            }else{
                System.out.print(i + "\t");
            }
        }
        System.out.println();
        
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j<boardSize; j++){
                if(j==0){
                    System.out.print(i+1);
                }
                
                System.out.print("\t" + boardCells[i][j]);    
                
                if(j== boardSize-1){
                    System.out.println();
                }
            }
        }   
    }
    
    public boolean isCellEmpty(int row, int col) {
        if (boardCells[row - 1][col - 1] == '\u0000') {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isBoardFull(){
        for(int i = 0; i<size; i++){
            for(int j = 0; j< size; j++){
                if(boardCells[i][j] == '\u0000')
                    return false;
            }
        }
        return true;
    }
    
    public boolean checkIfWon(int row, int col, char value, int numberToWin){
        row = row - 1;
        col = col - 1;
        
        int counter = 0;
        //pionowe sprawdzanie
        for(int i = 0; i <size; i++){
            if(boardCells[i][col] == value){
                counter++;
            }else{
                counter = 0;
            }
            
            if(counter == numberToWin)
                return true;
        }
        counter = 0;
        //poziome sprawdzanie
        for(int i = 0; i <size; i++){
            if(boardCells[row][i] == value){
                counter++;
            }else{
                counter = 0;
            }
            
            if(counter == numberToWin)
                return true;
        }

       
        counter = 0;
           
       //sprawdzanie na skos w prawo w poki co dla 3x3
       
       for(int i = 0; i< size; i++){
           if(boardCells[i][i] == value){
                counter++;
            }else{
                counter = 0;
            }
        
            if(counter == numberToWin)
                return true;
       }
       
       //sprawdzanie na skos w prawo poki co dla 3x3
       if(boardCells[2][0] == value && boardCells[1][1] == value && boardCells[0][2] == value){
           return true;
       }
        
        return false;
        
    }
    
}
