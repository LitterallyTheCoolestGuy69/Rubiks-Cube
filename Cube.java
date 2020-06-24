import java.awt.Color;
import java.util.Random;

public class Cube 
{
	private int size;
	public Color[][] frontFace; 
	public Color[][] rightFace; 
	public Color[][] backFace; 
	public Color[][] leftFace; 
	public Color[][] topFace; 
	public Color[][] bottomFace; 
	private String movements = "";
	
	public Cube(int size)
	{
		this.size = size;
		
		
		
		
		frontFace = new Color[size][size];
		rightFace = new Color[size][size];
		backFace = new Color[size][size];
		leftFace = new Color[size][size];               
		topFace = new Color[size][size];
		bottomFace = new Color[size][size];
		
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				///*
				topFace[i][j] = Color.white;
				/*
				rightFace[i][j] = Color.blue;
				backFace[i][j] = Color.orange;
				leftFace[i][j] = Ctolor.green;
				frontFace[i][j] = Color.red;
				bottomFace[i][j] = Color.yellow;
				/*
				*/
				
				
				/*
				topFace[i][j] = Color.cyan;
				*/
				frontFace[i][j] = Color.cyan;
				rightFace[i][j] = Color.cyan;
				backFace[i][j] = Color.cyan;
				leftFace[i][j] = Color.cyan;
				bottomFace[i][j] = Color.cyan;
				//*/
			}
		}
		
		//topFace[2][2] = Color.white;
		//rightFace[3][2] = Color.white;
	}
	public void turnUp(int col, boolean single)
	{
		Color holder;
		int count = 0;
		if(single)
			count = 1;
		else
			count = 2;
		while(count != 0)
		{
			count--;
			for(int i = 0; i < size; i++)
			{
				holder = frontFace[i][col];
				frontFace[i][col] = bottomFace[i][col];
				bottomFace[i][col] = backFace[size-(i+1)][size-(1+col)];
				backFace[size-(i+1)][size-(1+col)] = topFace[i][col];
				topFace[i][col] = holder;
			}
			
			if(col == 0)
			{
				leftFace = rotateFace(false, leftFace);
			}
			if(col == size - 1)
			{
				rightFace = rotateFace(true, rightFace);
			}
			
		}
	}
	public void turnDown(int col, boolean single)
	{
		Color holder;
		int count = 0;
		if(single)
			count = 1;
		else
			count = 2;
		while(count != 0)
		{
			count--;
			for(int i = 0; i < size; i++)
			{
				holder = topFace[i][col];
				topFace[i][col] = backFace[size-(i+1)][size-(1+col)];
				backFace[size-(i+1)][size-(1+col)] = bottomFace[i][col];
				bottomFace[i][col] = frontFace[i][col];
				frontFace[i][col] = holder;
				
			}
			
			if(col == 0)
			{
				leftFace = rotateFace(true, leftFace);
			}
			if(col == size - 1)
			{
				rightFace = rotateFace(false, rightFace);
			}
		}
	}
	
	
	
	
	
	public void turnLeft(int row, boolean single)
	{
		Color holder;
		int count;
		if(single)
			count = 1;
		else
			count = 2;
		
		while(count != 0)
		{
			count--;
			for(int i = 0; i < size; i++)
			{
				holder = rightFace[row][i];
				rightFace[row][i] = backFace[row][i];
				backFace[row][i] = leftFace[row][i];
				leftFace[row][i] = frontFace[row][i];
				frontFace[row][i] = holder;
			}	
			if(row == 0)
			{
				topFace = rotateFace(true, topFace);
			}	
			if(row == size - 1)
			{
				bottomFace = rotateFace(false, bottomFace);
			}
		}
	}
	
	public void turnRight(int row, boolean single)
	{
		Color holder;
		int count;
		if(single)
			count = 1;
		else
			count = 2;
		
		while(count != 0)
		{
			count--;
			for(int i = 0; i < size; i++)
			{				
				holder = frontFace[row][i] ;
				frontFace[row][i] = leftFace[row][i];
				leftFace[row][i] = backFace[row][i];
				backFace[row][i] = rightFace[row][i];
				rightFace[row][i] = holder;
			}	
			if(row == 0)
			{
				topFace = rotateFace(false, topFace);
			}	
			if(row == size - 1)
			{
				bottomFace = rotateFace(true, bottomFace);
			}
		}
	}
	public void turnFace(boolean clockwise, boolean single)
	{
		shiftLeft();
		if(clockwise)
			turnUp(size-1, single);
		else
			turnDown(size-1,single);
		shiftRight();
		if(!single)
		{
			shiftLeft();
			if(clockwise)
				turnUp(size-1, single);
			else
				turnDown(size-1,single);
			shiftRight();
		}
	}
	
	public void shiftUp()
	{
		for(int i = 0; i < size; i++)
		{
			turnUp(i,true);
		}
	}
	public void shiftDown()
	{

		for(int i = 0; i < size; i++)
		{
			turnDown(i,true);
		}
	}
	public void shiftRight()
	{
		for(int i = 0; i < size; i++)
		{
			turnLeft(i,true);
		}
	}
	public void shiftLeft()
	{
		for(int i = 0; i < size; i++)
		{
			turnRight(i,true);
		}
	}
	public void shiftClockwise()
	{
		shiftRight();
		shiftDown();
		shiftLeft();
	}
	public void shiftCounterClockwise()
	{
		shiftRight();
		shiftUp();
		shiftLeft();
	}
	
	public Color[][][] getFaces()
	{
		return new Color[][][] {frontFace, rightFace, topFace, leftFace, backFace, bottomFace};
	}
	public int getSize()
	{
		return size;
	}
	
	public static Color[][] rotateFace(boolean Clockwise, Color[][] face)
	{
		int size = face.length;
		Color holder;
		if(!Clockwise)
		{
			if(size % 2 == 1)
			{
				for(int i = 1; i <= size/2; i++)
				{	
					holder = face[size/2 + i][size/2 + i];
					face[size/2 + i][size/2 + i] = face[size/2 + i][size/2 - i];
					face[size/2 + i][size/2 - i] = face[size/2 - i][size/2 - i];
					face[size/2 - i][size/2 - i] = face[size/2 - i][size/2 + i];
					face[size/2 - i][size/2 + i] = holder;
					for(int j = 1; j < 2*i; j++)
					{
						holder = face[size/2 + i][(size/2 - i) + j];
						face[size/2 + i][(size/2 - i) + j] = face[(size/2 - i) + j][size/2 - i];
						face[(size/2 - i) + j][size/2 - i] = face[size/2 - i][(size/2 + i) - j];
						face[size/2 - i][(size/2 + i) - j] = face[(size/2 + i) - j][size/2 + i];
						face[(size/2 + i) - j][size/2 + i] = holder;
					}
				}
			}
			else
			{
				for(int i = 0; i < size/2; i++)
				{
					holder = face[size/2 + i][size/2 + i];
					face[size/2 + i][size/2 + i] = face[size/2 + i][(size/2 - 1) - i];
					face[size/2 + i][(size/2 - 1) - i] = face[(size/2 - 1) - i][(size/2 - 1) - i];
					face[(size/2 - 1) - i][(size/2 - 1) - i] = face[(size/2 - 1) - i][size/2 + i];
					face[(size/2 - 1) - i][size/2 + i] = holder;
					for(int j = 1; j <= 2*i; j++)
					{
						holder = face[size/2 + i][((size/2 - 1) - i) + j];
						face[size/2 + i][((size/2 - 1) - i) + j] = face[((size/2 - 1) - i) + j][(size/2 - 1) - i];
						face[((size/2 - 1) - i) + j][(size/2 - 1) - i] = face[(size/2 - 1) - i][size/2 + i - j];
						face[(size/2 - 1) - i][size/2 + i - j] = face[(size/2) + i - j][size/2 + i];
						face[(size/2) + i - j][size/2 + i] = holder;
					}
				}
			}
		}	
		else
		{
			if(size % 2 == 1)
			{
				for(int i = 1; i <= size/2; i++)
				{
					holder = face[size/2 - i][size/2 + i];
					face[size/2 - i][size/2 + i] = face[size/2 - i][size/2 - i];
					face[size/2 - i][size/2 - i] = face[size/2 + i][size/2 - i];
					face[size/2 + i][size/2 - i] = face[size/2 + i][size/2 + i];
					face[size/2 + i][size/2 + i] = holder;
				
					for(int j = 1; j < 2*i; j++)
					{						
						holder = face[(size/2 + i) - j][size/2 + i];
						face[(size/2 + i) - j][size/2 + i] = face[size/2 - i][(size/2 + i) - j];
						face[size/2 - i][(size/2 + i) - j] = face[(size/2 - i) + j][size/2 - i];
						face[(size/2 - i) + j][size/2 - i] = face[size/2 + i][(size/2 - i) + j];
						face[size/2 + i][(size/2 - i) + j] = holder;
					}
				}
			}
			else
			{
				for(int i = 0; i < size/2; i++)
				{
					holder = face[(size/2 - 1) - i][size/2 + i];
					face[(size/2 - 1) - i][size/2 + i] = face[(size/2 - 1) - i][(size/2 - 1) - i];
					face[(size/2 - 1) - i][(size/2 - 1) - i] = face[size/2 + i][(size/2 - 1) - i];
					face[size/2 + i][(size/2 - 1) - i] = face[size/2 + i][size/2 + i];
					face[size/2 + i][size/2 + i] = holder;
					for(int j = 1; j <= 2*i; j++)
					{
						holder = face[(size/2) + i - j][size/2 + i];
						face[(size/2) + i - j][size/2 + i] = face[(size/2 - 1) - i][size/2 + i - j] ;
						face[(size/2 - 1) - i][size/2 + i - j] = face[((size/2 - 1) - i) + j][(size/2 - 1) - i];
						face[((size/2 - 1) - i) + j][(size/2 - 1) - i] = face[size/2 + i][((size/2 - 1) - i) + j];
						face[size/2 + i][((size/2 - 1) - i) + j] = holder;
					}
				}
			}
		}
		return face;
	}
	
	
	public void scramble()
	{
		int x;
		Random r = new Random();
		for(int i = 0; i < 4*size; i++)
		{
			x = (int) (Math.random()*9);
			if(x == 0)
			{
				x = (int)(Math.random()*size);
				turnUp(x, r.nextBoolean());
				System.out.println("column " + x + "turns up");
			}
			else if(x == 1)
			{
				x = (int)(Math.random()*size);
				turnDown(x, r.nextBoolean());
				System.out.println("column " + x + "turns down");
			}
			else if(x == 2)
			{
				x = (int)(Math.random()*size);
				turnRight(x, r.nextBoolean());
				System.out.println("row " + x + "turns right");
			}
			else if(x == 3)
			{
				x = (int)(Math.random()*size);
				turnLeft(x, r.nextBoolean());
				System.out.println("row " + x + "turns left");
			}
			else if(x == 4)
			{
				shiftRight();
				System.out.println("shift right");
			}
			else if(x == 5)
			{
				shiftLeft();
				System.out.println("shift left");
			}
			else if(x == 6)
			{
				shiftUp();
				System.out.println("shift up");
			}
			else if(x == 7)
			{
				shiftClockwise();
				System.out.println("shift clockwise");
			}
			else if(x == 8)
			{
				shiftCounterClockwise();
				System.out.println("shift counterclockwise");
			}
			else if(x == 9)
			{
				shiftDown();
				System.out.println("shift down");
			}
		}
	}
	public boolean isSolved()
	{
		return sideSolved(topFace) && sideSolved(bottomFace) && 
			   sideSolved(rightFace) && sideSolved(leftFace) && 
			   sideSolved(frontFace) && sideSolved(backFace);
	}
	public boolean sideSolved(Color[][] face)
	{
		Color check = face[0][0];
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(face[i][j] != check)
					return false;
			}
		}
		return true;
	}
}
