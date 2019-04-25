import java.awt.*;
import java.util.*;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int GRASS = 4;
  
  public static final int LEFT = 0;
  public static final int RIGHT = 1;
  public static final int DOWN = 2;
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[5];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "SAND";
    names[WATER] = "WATER";
    names[GRASS] = "GRASS";

    
    
    this.grid = new int [numRows][numCols];
    
    //1. Add code to initialize the data member grid with same dimensions
    
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
	  if(row <= grid.length && col <= grid[0]. length)
	  {
		  grid[row][col] = tool;
	  }
    //2. Assign the values associated with the parameters to the grid
   
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
	  
	  int width = grid[0].length;
	  int length = grid.length;
	  for (int rows = 0; rows < length; rows++)
	  {
		  for (int cols = 0; cols < width; cols++)
			  if(grid[rows][cols] == 0)
			  {
				  display.setColor(rows,  cols, Color.BLACK);
			  }
			  else if(grid[rows][cols] == 1)
			  {
				  display.setColor(rows,  cols,  Color.GRAY);
			  }
			  else if(grid[rows][cols] == 2)
			  {
				  display.setColor(rows,  cols,  Color.YELLOW);
			  }
			  else if(grid[rows][cols] == 3)
			  {
				  display.setColor(rows,  cols,  Color.BLUE);
			  }
				  else if(grid[rows][cols] == 4)
					  display.setColor(rows, cols, Color.GREEN);
			  }
				  
			  
	  }
      //Step 3
   //Hint - use a nested for loop
    
  

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
	  int randomRow = (int)(Math.random() * grid.length);
	  int randomCol = (int)(Math.random() * grid[0].length);
	  
	  int randomTool = grid[randomRow][randomCol];
	  if(randomTool ==2)
	  {
		  if(randomRow != grid.length -1 && grid[randomRow +1][randomCol] == EMPTY)
		  {
			  if(grid[randomRow +1][randomCol] == EMPTY)
		  
		  {
			  grid[randomRow +1] [randomCol] = 2;
			  grid[randomRow] [randomCol] = 0;
		  }
			  else if(grid[randomRow+1][randomCol] == WATER)
			  {
				  grid[randomRow][randomCol] = SAND;
				  grid[randomRow-1][randomCol] = WATER;
				  grid[randomRow+1][randomCol] = SAND;
			  }
		  }
	  }
	  if(randomTool == 3)
	  {
		  if((randomRow < grid.length-1 && randomCol < grid[0].length)&& grid[randomRow +1][randomCol] == EMPTY)
		  {
			  int randomDirection = (int)(Math.random() * 3);
			  if(randomDirection == DOWN)
			  {
				  grid[randomRow][randomCol] = EMPTY;
				  grid[randomRow +1][randomCol] = WATER;
				  
			  }
			  if(randomDirection == RIGHT)
			  {
				  if(randomRow < grid.length-1 && randomCol + 1 < grid[0].length) {
					  grid[randomRow][randomCol] = EMPTY;
					  grid[randomRow][randomCol+1] = WATER;
				  }
			  }
			  if(randomDirection == LEFT)
			  {
				  if(randomCol < grid[0].length && randomCol + 1 < 0)
				  {
					  grid[randomRow][randomCol] = EMPTY;
					  grid[randomRow][randomCol-1] = WATER;
				  }
			  }
		  }
	  }
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    //int someRandom = (int) (Math.random() * scalar)
    //remember that you need to watch for the edges of the array
    
    
  }
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
