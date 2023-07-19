public class TurtleG{
	private boolean is_pen_up;
	private int[][] floor= new int[20][20];
	private int[] commands;
	private int[] turtle_pos= new int[2];
	private boolean row_up, col_down, row_down, col_up;
	private int counter;
	
	public TurtleG(int[] commands) {
		//initializing instance variables and setting
		this.commands= commands;
		this.initializeFloor();
		this.is_pen_up=true;
		this.initializeTurtlePos();
		this.row_up=true;
		this.col_down=false;
		this.col_up=false;
		this.row_down=false;
		this.counter=0;
	}
	
	//method to initialize the 20-by-20 array floor
	private void initializeFloor() {
		for(int i=0; i<20; i++)
			for(int j=0; j<20; j++)
				this.floor[i][j]=0;
	}
	
	//method to initialize the array position tracker length 2
	private void initializeTurtlePos() {
		for(int pos=0; pos<turtle_pos.length; pos++)
			this.turtle_pos[pos]=0;
	}
	
	//method to update position tracker
	private void trackTurtlePos(int row, int col) {
		this.turtle_pos[0]=row;
		this.turtle_pos[1]=col;
	}
	
	//turtle head facing row up moving through columns
	private void rowUP(int steps)
	{	
		int col;		
		for(col=this.turtle_pos[1]; col<20; col++)
		{
			if(steps != 0) {
				if(this.is_pen_up)//when pen is up
					this.floor[this.turtle_pos[0]][col]=0;
				else//when pen is down
					this.floor[this.turtle_pos[0]][col]=1;
				if(col<19)
					this.turtle_pos[1]=(col+1);
			}
			else
			{
				break;
			}
			steps--;
		}
	}
	
	//turtle head facing column down moving through rows
	private void colDown(int steps) {
		
		int row;
		for(row=this.turtle_pos[0]; row<20; row++)
		{
			if(steps != 0) {
				if(this.is_pen_up)//when pen is up
					this.floor[row][this.turtle_pos[1]]=0;
				else//when pen is down
					this.floor[row][this.turtle_pos[1]]=1;
				if(row<19)
					this.turtle_pos[0]=row+1;
			}
			else
			{
				break;
			}
			steps--;
		}
		
	}
	
	//turtle head facing row down moving through columns
	private void rowDown(int steps) {
		int col;		
		for(col=this.turtle_pos[1]; col>=0; col--)
		{
			if(steps != 0) {
				if(this.is_pen_up)//when pen is up
					this.floor[this.turtle_pos[0]][col]=0;
				else//when pen is down
					this.floor[this.turtle_pos[0]][col]=1;
				if(col>=1)//when turtle moves closer to bounds
					this.turtle_pos[1]=(col-1);
				
			}
			else
			{
				break;
			}
			steps--;
		}
		
	}
	
	//turtle head facing column up moving through rows
	private void colUP(int steps) {
		int row;
		for(row=this.turtle_pos[0]; row>=0; row--)
		{
			
			if(steps != 0) {
				if(this.is_pen_up)//when pen is up
					this.floor[row][this.turtle_pos[1]]=0;
				else//when pen is down
					this.floor[row][this.turtle_pos[1]]=1;
				if(row>=1)//when turtle moves closer to bounds
					this.turtle_pos[0]=(row-1);
			}
			else
			{
				break;
			}
			steps--;
		}
	}
	
	//method to display turtle drawings
	private void displayFloor() {
		for(int row=0; row<20; row++)
			for(int col=0; col<20; col++)
			{
				if(this.floor[row][col] != 0)
					System.out.print("=");
				else
					System.out.print(" ");
				
				if(col % 19 == 0 && col != 0)
					System.out.println();
			}
	}
	
	private void setPenPos(int val) {
		if(val == 1)
			this.is_pen_up=true;
		else
			this.is_pen_up=false;
	}
	
	//method to set head direction when turning right
	private void setHeadDirectionRight(){
		if(this.row_up){
			this.col_down=true;
			this.row_up=false;
		}
		else if(this.col_down) {
			this.row_down=true;
			this.col_down=false;
		}
		else if(this.row_down) {
			this.col_up=true;
			this.row_down=false;
		}
		else if(this.col_up) {
			this.row_up=true;
			this.col_up=false;
		}
	}
	
	//method to set head direction when turning left
	private void setHeadDirectionLeft() {
		if(this.row_up) {
			this.col_up=true;
			this.row_up=false;
		}
		else if(this.col_up) {
			this.row_down=true;
			this.col_up=false;
		}
		else if(this.row_down) {
			this.col_down=true;
			this.row_down=false;
		}
		else if(this.col_down) {
			this.row_up=true;
			this.col_down=false;
		}
	}
	
	//method to dictate turtle movement
	private void moveTurle(int steps) {
		if(this.row_up)
			this.rowUP(steps);
		else if(this.col_down)
			this.colDown(steps);
		else if(this.row_down)
			this.rowDown(steps);
		else if(this.col_up)
			this.colUP(steps);
	}
	
	public void eatComs() {
		while(this.commands[counter]!=9) {
			
			switch(commands[counter]) {
			case 1://pen up
				this.setPenPos(1);
				break;
			case 2://pen down
				this.setPenPos(2);
				break;
			case 3://Turn right
				this.setHeadDirectionRight();
				break;
			case 4://Turn left
				this.setHeadDirectionLeft();
				break;
			case 5://movement command
				//System.out.println("Moved");
				this.moveTurle(commands[counter+1]);
				counter++;
				break;
			}
			if(commands[counter]==6)
				this.displayFloor();
			counter++;
		}
	}
	
	/*public void showTurlePos() {
		System.out.printf("Row: %d Col: %d%n", this.turtle_pos[0], this.turtle_pos[1]);
	}*/
	
	/*public void showTurns() {
		if(this.row_up){
			System.out.println("Column down");
		}
		else if(this.col_down) {
			System.out.println("Row down");
		}
		else if(this.row_down) {
			System.out.println("Column up");
		}
		else if(this.col_up) {
			System.out.println("Row up");
		}
	}*/
}