import java.util.ArrayList;

public class Snake
	{
//		This is the POJO for the whole snake, which will hold an ArrayList of Body objects.
		private ArrayList<Body> snakeBody = new ArrayList<Body>();
		
		public Snake()
		{
			addToSnakeBody(0,0);
		}

		public Body getHead()
			{
				return snakeBody.get(0);
			}
		
		public ArrayList<Body> getSnakeBody()
			{
				return snakeBody;
			}

		public void setSnakeBody(ArrayList<Body> snakeBody)
			{
				this.snakeBody = snakeBody;
			}
		
		public void addToSnakeBody(int x, int y)
			{
				snakeBody.add(new Body(x,y));
			}
		
		public void changeBodyPositions()
			{
				if(snakeBody.size() > 1)
					{
						for(int i = 1; i < snakeBody.size(); i++)
							{
								snakeBody.get(i).setxPos(snakeBody.get(i-1).getxPos());
								snakeBody.get(i).setyPos(snakeBody.get(i-1).getyPos());
							}
					}
				
			}
	}
