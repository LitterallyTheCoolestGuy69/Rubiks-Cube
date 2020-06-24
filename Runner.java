import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JFrame;
import java.util.Scanner;

public class Runner extends JFrame
{
	private String[] sources = new String[] {"checking right","checking top", "checking left", "checking bottom"};
	private static Cube cube;
	public Runner(int size)
	{
		
		cube = new Cube(size);

	}
	
	public static void main(String[] args)
	{
		int size = 3;
		boolean validSize = false;
		Scanner s = new Scanner(System.in);
		while(!validSize)
		{
			validSize = true;
			System.out.print("Enter cube size: ");
			try
			{
				size = s.nextInt();
				if(size > 50)
				{
					validSize = false;
					System.out.println("Size must be less than 51");
				}
				else if(size <= 1)
				{
					validSize = false;
					System.out.println("Size must be greater than 1");
				}
			}
			catch(Exception e)
			{
				validSize = false;
				System.out.println("Not a valid input");
			}
		}
		
		
		Runner display = new Runner(size);
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		display.setSize(500, 500);
        display.setVisible(true);
        
        cube.scramble();
        
        while(true) {
        	display.getNextMove();
        }
        
        
	}
	
	public void paint(Graphics g)
	{
		double size = (double) cube.getSize();
		
		Color[][][] faces = cube.getFaces();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				g.setColor(faces[0][i][j]);
				g.fillPolygon(new int[]{(int)(50 + (300/size)* j),(int)(50 + (300/size)* j), (int)(50 + (300/size)*(j+1)),(int)(50 + (300/size)*(j+1))},
						      new int[]{(int)(150 + (300/size)*i),(int)(150 + (300/size)*(i+1)),(int)(150 + (300/size)*(i+1)),(int)(150 + (300/size)*i)},4);
			}                                                                       
		}
		
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				g.setColor(faces[1][i][j]);                                                                   
				g.fillPolygon(new int[] {(int)(350 + (75/size)*j), (int)(350 + (75/size)*(j+1)), (int)(350 + (75/size)*(j+1)),(int)(350 + (75/size)*j)},
						      new int[] {(int)(150 + ((300/size)*i) - (75/size)*j),(int)(150 + ((300/size)*i) - (75/size)*(j+1)),(int)(150 + ((300/size)*i) - (75/size)*(j+1) + (300/size)), (int)(150 + ((300/size)*i) - (75/size)*j + (300/size))},4);
		
			}
		}
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				g.setColor(faces[2][i][j]);
				g.fillPolygon(new int[]{(int)((125 - ((75/size)*i)) + ((300/size)*j)), (int)((125 - ((75/size)*i)) + ((300/size)*(j+1))), (int)((125 - ((75/size)*i)) + ((300/size)*(j+1)) - (75/size)), (int)((125 - ((75/size)*(i+1))) + ((300/size)*j))},
						      new int[]{(int)((75 + (75/size)*i)), (int)((75 + (75/size)*i)), (int)((75 + (75/size)*(i+1))), (int)((75 + (75/size)*(i+1)))}, 4);
						
			}
		}
		
		
		int Size = (int)size;
		g.setColor(Color.black);
		for(int i = 1; i < Size; i++)
		{
		    g.drawLine(50 + (300/Size)*i, 150, 50 + (300/Size)*i, 450);
		}
		for(int i = 1; i < Size; i++)
		{
		    g.drawLine(50, 150 + (300/Size)*i, 350, 150 + (300/Size)*i);
		}
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				g.drawRect(i+50,j+150,i+300,300);
				g.drawPolygon(new int[]{i+50,i+125,i+425,i+350}, 
			  	              new int[]{j+150,j+75,j+75,j+150}, 4);
			    g.drawPolyline(new int[]{i+350,i+350,i+425,i+425}, 
					           new int[]{j+150,j+450,j+375,j+75}, 4);
			}
		}
		
		for(int i = 1; i < Size; i++)
		{
			g.drawLine(125 - (75/Size)*i, 75 + (75/Size)*i,
					   425 - (75/Size)*i, 75 + (75/Size)*i);
		}
		for(int i = 1; i < Size; i++)
		{
			g.drawLine(50 + (300/Size)*i, 150, 125 + (300/Size)*i, 75);
		}
		for(int i = 1; i < Size; i++)
		{
			g.drawLine(350, 150 + (300/Size)*i, 425, 75 + (300/Size)*i);
		}
		for(int i = 1; i < Size; i++)
		{
			g.drawLine(350 + (75/Size)*i, 150 - (75/Size)*i, 350 + (75/Size)*i, 450 - (75/Size)*i);
		}
		
	}
	public void updateCube()
	{
		setSize(500,501);
		setSize(500,500);
	}
	
	
	public void getNextMove()
	{
		Scanner s = new Scanner(System.in);
		String[] moves = s.nextLine().split("-");
		try
		{
			Integer.parseInt(moves[0]);
			if(moves[1].equals("v"))
			{
				cube.turnDown(Integer.parseInt(moves[0]), true);
			}
			if(moves[1].equals("^"))
			{
				cube.turnUp(Integer.parseInt(moves[0]), true);
			}
			if(moves[1].equals("<"))
			{
				cube.turnLeft(Integer.parseInt(moves[0]), true);
			}
			if(moves[1].equals(">"))
			{
				cube.turnRight(Integer.parseInt(moves[0]), true);
			}
		}
		catch(Exception e)
		{
			if(moves[0].equals("^"))
			{
				if(moves[0].equals("^"))
				{
					cube.shiftUp();
				}
				else
				{
					cube.shiftClockwise();
				}
			}
			else if(moves[0].equals("v"))
			{
				if(moves[0].equals("v"))
				{
					cube.shiftDown();
				}
				else
				{
					cube.shiftCounterClockwise();
				}
			}
			else if( moves[0].equals(">"))
			{
				cube.shiftLeft();
			}
			else if( moves[0].equals("<"))
			{
				cube.shiftRight();
			}
		}
	}
	
	public Cube solveCube(Cube c)
	{
		Scanner s = new Scanner(System.in);
		int size = c.getSize(), countVar;
		boolean found, complete;
		if(c.getSize() >= 5 && c.getSize()%2 == 1)
		{
			//Turning the white center to be on the front face
			if(c.backFace[size/2][size/2] == Color.white)
			{
				c.shiftUp();
				c.shiftUp();
				System.out.println("test 1");
			}
			else if(c.leftFace[size/2][size/2] == Color.white)
			{
				c.shiftLeft();
				System.out.println("test 2");
			}
			else if(c.bottomFace[size/2][size/2] == Color.white)
			{
				c.shiftUp();
				System.out.println("test 3");
			}
			else if(c.topFace[size/2][size/2] == Color.white)
			{
				c.shiftDown();
				System.out.println("test 4");
			}
			else if(c.rightFace[size/2][size/2] == Color.white)
			{
				c.shiftRight();
				System.out.println("test 5");
			}
			
			Runner.printCube(c);
			
			
			
			for(int i = 1; i < size - 1; i++)  //This for loop creates the first white strip on the face
			{
				System.out.println(i);
				printFace(c.frontFace);
				if(c.frontFace[size/2][i] != Color.white)
				{
					System.out.println("Not already white");
					if(c.frontFace[i][size/2] == Color.white)
					{
						System.out.println("Found on face1");
						c.turnLeft(i, true);
						c.turnFace(true, true);
						c.turnRight(i,true);
						c.turnFace(false, true);
					}
					else if(c.frontFace[size-(i+1)][size/2] == Color.white)
					{
						System.out.println("Found on face");
						c.turnLeft(size-(i+1), true);
						c.turnFace(false, true);
						c.turnRight(size-(i+1),true);
						c.turnFace(true, true);
					}
					else
					{
						if(checkRing(c.backFace,i,size/2, Color.white))
						{
							System.out.println("On the back");
							c.turnFace(true, true);
							c.shiftRight();
							c.shiftRight();
							while(c.frontFace[i][size/2] != Color.white)
							{
								c.turnFace(true,true);
								System.out.println("hello2");
							}
							c.turnLeft(i, false);
							c.shiftLeft();
							c.shiftLeft();
							c.turnFace(false,true);
						}
						else
						{
							System.out.println("not on the back");
							while(!checkRing(c.rightFace,i,size/2,Color.white))
							{
								System.out.println("checking right");
								c.shiftClockwise();
								c.turnFace(false,true);
							}
							System.out.println("here");
							c.turnFace(true,true);
							c.shiftRight();
							while(c.frontFace[i][size/2] != Color.white)
							{
								c.turnFace(true,true);
								System.out.println("hello");
							}
							c.turnLeft(i, true);
							c.shiftLeft();
							c.turnFace(false, true);
						}
					}
				}
			}
			
			System.out.println("White strip completed");
			c.shiftCounterClockwise();
			c.shiftDown();
			printCube(c);
			
			
			Color[][] sub1, sub2, sub3;
			for(int col = 1; col < size - 1; col++) //Iterates through all the columns piecing them together
			{
				if(col == size/2)
					col++;
				System.out.println("Column " + col);
				for(int row = 1; row < size - 1; row++) //Iterates through all the rows
				{
					System.out.println("Row " + row);
					if(c.frontFace[row][col] != Color.white) //Checks if it is already white
					{
						
						found = false; //This variable tracks whether a tile has been found
						
						//This process checks for a suitable tile within the face
						sub1 = c.frontFace;
						for(int i = 1; i < size - 1; i++) //This makes a copy of the face,
							sub1[i][col] = Color.black; // and makes everything in the column "unavailable" as a tile
						
						for(int i = 1; i < 4; i++) //rotates the face 3 times searching for a usable tile
						{
							sub1 = Cube.rotateFace(true, sub1); //rotates the face
							if(sub1[row][col] == Color.white) //checks if the position is now white
							{
								System.out.println("Found on face");
								found = true;
								c.turnDown(col, true); //gets the current column out of the way
								for(int j = 0; j < i; j++) //moves the found tile into place
									c.turnFace(true, true);
								c.turnRight(row, true);
								c.turnUp(col,true);
								c.turnLeft(row, true);
							}
						}
						
						//If it gets through the above section of code and found is not true, the search is continued
						
						for(int i = 1; i < 4 && !found; i++) //This for loop checks the right, back, and left faces
						{
							System.out.println("Checking right");
							c.shiftRight(); //shifts right once
							if(checkRing(c.frontFace, row, col, Color.white)) //checks for an available tile
							{
								System.out.println("Found on right");
								while(c.frontFace[row][col] != Color.white) //if it is there, the face is rotated until the tile can be put into place
									c.turnFace(true, true);
								found = true;
								for(int j = 0; j < i; j++) //This for loop puts the tile into place by turning the row the same number of times the cube has been shifted right
								{
									c.turnLeft(row, true);
									c.shiftLeft();
								}
							}
						}
							
							
						//if the above segment of code leaves found as false still
						//This means that the tile is either on the top or bottom faces
						
						if(!found)
						{
							c.shiftRight();
							sub1 = c.topFace;
							for(int i = 0; i < 4 && !found; i++) //checks the top face for a piece
							{
								sub1 = Cube.rotateFace(true, sub1);
								if(sub1[row][col] == Color.white)
								{
									System.out.println("found on top");
									found = true;
									for(int j = 0; j < i; j++)
									{
										c.turnLeft(0, true);
									}
								}
							}
							if(found)
							{
								c.turnDown(col, true);
								c.turnRight(row, true);
								c.turnUp(col, true);
								c.turnLeft(row, true);
							}
							else
							{
								System.out.println("Not found on top");
								for(int i = col; i < size - 1; i++) //rotates every unsolved column down
								{
									if(i==size/2) //skips the middle column
										i++;
									c.turnDown(i, true);
								}
								for(int i = 1; i < size - 1; i++)//moves every row to the left
									c.turnLeft(i, true);
								
								for(int i = col; i < size - 1; i++) //rotates every unsolved column back up
								{
									if(i==size/2) //skips the middle column
										i++;
									c.turnUp(i, true);
								}
								
								//After all of this the remaining piece must be on the right face
								//So the right face rotates until the piece is found
								
								while(c.rightFace[row][col] != Color.white)
									c.turnUp(size-1, true);
								c.turnLeft(row, true);
							}
							
						}
						
						
						
						
					}
					printFace(c.frontFace);
					System.out.println();
				}
				c.turnDown(col, true); //puts the completed column in place
			}
			

			c.shiftUp(); //moves the solved center to the front
			
			System.out.println("here2");
			
			
			
		}
		
		return c;
	}
	
	
	public boolean checkRing(Color[][] face, int row, int col, Color c)
	{
		if(face[row][col] == c)
			return true;
		face = Cube.rotateFace(true, face);
		if(face[row][col] == c)
			return true;
		face = Cube.rotateFace(true, face);
		if(face[row][col] == c)
			return true;
		face = Cube.rotateFace(true, face);
		if(face[row][col] == c)
			return true;
		return false;
	}
	public void printFace(Color[][] face)
	{
		for(int i = 0; i < face.length; i++)
		{
			System.out.print("|");
			for(int j = 0; j < face.length; j++)
			{
				if(face[i][j] == Color.red)
					System.out.print("r |");
				else if(face[i][j] == Color.blue)
					System.out.print("b |");
				else if(face[i][j] == Color.green)
					System.out.print("g |");
				else if(face[i][j] == Color.white)
					System.out.print("w |");
				else if(face[i][j] == Color.yellow)
					System.out.print("y |");
				else if(face[i][j] == Color.orange)
					System.out.print("o |");
				else
					System.out.print("X |");
			}
			System.out.println("-----------------------------------");
		}
	}
	
	
	public static void printCube(Cube c)
	{
		int size = c.getSize();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				System.out.print("|"+convertColor(c.topFace[i][j]));
			}
			System.out.println("|");
		}
		System.out.print("-");
		for(int i = 0; i < size; i++)
		{
			System.out.print("--");
		}
		System.out.println();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				System.out.print("|"+convertColor(c.frontFace[i][j]));
			}
			System.out.print("|  ");
			for(int j = 0; j < size; j++)
			{
				System.out.print("|"+convertColor(c.rightFace[i][j]));
			}
			System.out.print("|  ");
			for(int j = 0; j < size; j++)
			{
				System.out.print("|"+convertColor(c.backFace[i][j]));
			}
			System.out.print("|  ");
			for(int j = 0; j < size; j++)
			{
				System.out.print("|"+convertColor(c.leftFace[i][j]));
			}
			System.out.println("|");
		}
		System.out.print("-");
		for(int i = 0; i < size; i++)
		{
			System.out.print("--");
		}
		System.out.println();
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				System.out.print("|"+convertColor(c.bottomFace[i][j]));
			}
			System.out.println("|");
		}
		System.out.println();
	}
	public static String convertColor(Color c)
	{
		if(c == Color.white)
			return "w";
		else if(c == Color.yellow)
			return "y";
		else if(c == Color.green)
			return "g";
		else if(c == Color.orange)
			return "o";
		else if(c == Color.red)
			return "r";
		else if(c == Color.blue)
			return "b";
		else if(c == Color.black)
			return "B";
		return "X";
	}
}




