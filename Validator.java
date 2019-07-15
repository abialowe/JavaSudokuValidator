//Abi Lowe
//03/31/17
//This program takes a potential sudoku solution and input and checks to see if it is valid/correct. 

package sudokuValidator;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Validator
{
	public static void main(String[] args)
	{
		// Try to open the input file for reading
		Scanner fin;
		try{
			Scanner scanner = fin = new Scanner(new File("correct_puzzle.txt"));
	
		}catch(IOException e)
		{
			return;
		}
		
		//Reads the sudoku amount and integers
		int sets = fin.nextInt();
		
		// For each Sudoku, it's read and determined whether or not it is valid
		for(int set = 1; set <= sets; set++)
		{
			int[][] sudoku = new int[9][9];
			
			// Sudoku is read from the file 
			for(int row=0; row<9; row++)
			{
				String line = fin.next();
				for(int col=0; col<9; col++)
					sudoku[row][col] = line.charAt(col)-'0'; 
			}
			
			// This checks the Sudoku and prints out to the user whether or not the sudoku puzzle is correct
			System.out.print("Sudoku #"+set+":  ");
			if(checkSudoku(sudoku))
				System.out.println("Sudoku puzzle is corrrect! :)");
			else
				System.out.println("Sudoku puzzle is incorrect :(");
			System.out.println();
		}
		
		
	}
	
	// This checks the sudoku to see if the solution is correct
	// A sudoku is valid only if all its row, columns, and subgrids are all valid
	public static boolean checkSudoku(int[][] sudoku)
	{
		// Verifies all the rows
		for(int row = 0; row < 9; row++)
			if(!checkRow(sudoku, row))
				return false;
		
		// Verifies all the columns
		for(int col = 0; col < 9; col++)
			if(!checkCol(sudoku,col))
				return false;
		
		// Verifies the subgrids 3x3
		for(int row = 0; row < 9; row += 3)
			for(int col = 0; col < 9; col += 3)
				if(!checkBox(sudoku,row,col))
					return false;
		
		// If all the previous tests pass, then the sudoku is valid and validated
		return true;
	}
	
	// Checks the specified row to make sure that each digit
	// appears once and only once
	public static boolean checkRow(int[][] sudoku, int row)
	{
		boolean[] hit = new boolean[10];
		
		for(int col = 0; col < 9; col++)
		{
			// If a number is reused in the row then the Sudoku is invalid
			if(hit[sudoku[row][col]])
				return false;
			
			// Marks a number as used in the row
			hit[sudoku[row][col]] = true;
		}
		// Input can only have digits 1 - 9 appear
		// If all 9 of these digits are only seen once, then they must have only been used once
		return true;
	}
	
	// Checks a specified column to make sure each digit
	// appears once and only once
	public static boolean checkCol(int[][] sudoku, int col)
	{
		boolean[] hit = new boolean[10];
		
		for(int row = 0; row < 9; row++)
		{
			// If a number appears more than once then the Sudoku is invalid
			if(hit[sudoku[row][col]])
				return false;
			
			// Marks a number as having been used
			hit[sudoku[row][col]] = true;
		}
		// Input can only have digits 1 - 9 appear
		// If all 9 of these digits are only seen once, then they must have only been used once
		return true;
	}
	
	// Checks the 3x3 sub-grid(box) to make sure that each digit
	// appears once and only once
	public static boolean checkBox(int[][] sudoku, int row, int col)
	{
		boolean[] hit = new boolean[10];
		
		// Loops through each cell in a box
		for(int r = row; r < row+3; r++)
		{
			for(int c = col; c < col+3; c++)
			{				
				// If a number appears more than once then the Sudoku is invalid
				if(hit[sudoku[r][c]])
					return false;
				
				// Marks a number as having been used
				hit[sudoku[r][c]] = true;
			}
		}
		// Input can only have digits 1 - 9 appear
		// If all 9 of these digits are only seen once, then they must have only been used once
		return true;
	}
}
