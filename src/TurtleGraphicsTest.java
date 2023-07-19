//Program simulates turtle graphics
//Author: B.L.Hobeni
//created on 7/11/2023
public class TurtleGraphicsTest {
	public static void main(String[] args)
	{	
		int[] commands= {2,5,10,4,4,5,5,4,5,15
						 ,4,5,5,3,3,5,10,3,5,1,1,3,5,2,2,4,5,1,1,3,5,3,
						 2,5,1,1,5,2,4,5,1,2,5,1,1,5,1,4,5,3,2,5,1,1,5,1,
						 5,1,2,3,5,1,1,5,1,1,3,5,3,2,5,1,1,5,2,2,4,5,1,6,9};
		TurtleG turtle= new TurtleG(commands);
		turtle.eatComs();
	}
}
