import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SnakeRunner extends Canvas
	{
		static Snake snake = new Snake();
		static int xSize = 876;
		static int ySize = 780;
		static String dir = "right";
		static boolean gameOver=false;

		static ArrayList<Body> fruits = new ArrayList<Body>();
		static boolean needsFruit = true;

		
		public static void main(String[] args)
			{
//				System.out.println("starting main");
				JFrame frame = new JFrame("Snake");
		        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        SnakeRunner ex = new SnakeRunner();
		        frame.getContentPane().add(ex);
		        frame.pack();
		        frame.setResizable(false);
		        frame.setVisible(true);
		        ex.requestFocus();
//		        while(true)
//		        	{
//		        		ex.moveSnake();
//		        		ex.repaint();
//		        	}
//		        System.out.println("ending main");
			}
		 public SnakeRunner()
			 {
//		        System.out.println("starting runner");
				setSize(new Dimension(xSize, ySize));
		        setBackground(Color.BLACK);
		        addKeyListener(new KeyAdapter() 
		        {
		            @Override
		            public void keyPressed(KeyEvent e) 
		            {
		                switch(e.getKeyCode())
		                {
		                	case KeyEvent.VK_DOWN:
		                		dir = "down";
		                		break;
		                	case KeyEvent.VK_UP:
		                		dir = "up";
		                		break;
		                	case KeyEvent.VK_RIGHT:
		                		dir = "right";
		                		break;
		                	case KeyEvent.VK_LEFT:
		                		dir = "left";
		                		break;
		                	case KeyEvent.VK_SPACE:
		                		dir = null;
		                		break;
		                }
		            }
		            
		        });
		        
		     
//		       System.out.println("ending runner"); 
		    }

		 public void paint(Graphics g)
		 {
//			 System.out.println("painting");
			 if(!gameOver)
			 {
			 g.setColor(Color.CYAN);
			 for(Body b: snake.getSnakeBody())
				 {
//					 g.fillRect(snake.getHead().getxPos(), snake.getHead().getyPos(), 24, 24);
					 g.fillRect(b.getxPos(), b.getyPos(), 24, 24);
				 }
			 g.setColor(Color.RED);
			 if(fruits.size() > 0)
				 {
					 g.fillRect(fruits.get(0).getxPos(), fruits.get(0).getyPos(), 24, 24);
				 }
			 else
				 {
					 needsFruit = true;
				 }
				moveSnake();
			 
			 if(needsFruit)
	            	{
	            		createNewFruit();
	            	}
		        for(Body b: snake.getSnakeBody())
		        	{
//		        		System.out.println(b.getxPos() + " " + b.getyPos());
		        		if((b.getxPos() == fruits.get(0).getxPos()) && (b.getyPos() == fruits.get(0).getyPos()))
		        			{
		        				eatFruit();
		        			}
		        	}
			 }
			 else
			 {
					 g.setColor(Color.white);
					 Font f=new Font("bet", Font.PLAIN, 50);
					 g.setFont(f);
					 g.drawString("GAME OVER", 288, 390);	 
			 }

			
		 }
		 public void moveSnake()
		 {
			 int startX = snake.getHead().getxPos();
			 int startY = snake.getHead().getyPos();
			 try
				 {
			 if(!gameOver)
				   {
					   switch(dir)
					   {
						   case "up":
							   if(snake.getHead().getyPos() > 0)
									{
										snake.getHead().setyPos(snake.getHead().getyPos()-24);
									}
								else
									{
										gameOver = true;
									}
							   break;
						   case "down":
							   if(snake.getHead().getyPos() < (ySize - 24))
									{
										snake.getHead().setyPos(snake.getHead().getyPos()+24);
									}
								else
									{
										gameOver = true;
									}
							   break;
						   case "right":
							   if(snake.getHead().getxPos() < (xSize - 24))
									{
										snake.getHead().setxPos(snake.getHead().getxPos()+24);
									}
								else
									{
										gameOver = true;
									}
							   break;
						   case "left":
							   if(snake.getHead().getxPos() > 0)
									{
										snake.getHead().setxPos(snake.getHead().getxPos()-24);
									}
								else
									{
										gameOver = true;
									}
							   break;
					   }
				changeBodyPositions(snake, startX, startY, 1);
				repaint(); 
				Thread.sleep(100);
				
				   }
			 else
			 {
			 repaint(); 
			 }
				 }
			 catch(Exception e)
				 {
					System.out.println(e.getMessage());
					 System.out.println("bet"); 
				 }
				
				
		 }
		 public void changeBodyPositions(Snake s, int x1, int y1, int pos)
		 {
			 if(s.getSnakeBody().size() == pos)
				 {
					 return;
				 }
			 else
				 {
					 int x2 = s.getSnakeBody().get(pos).getxPos();
					 int y2 = s.getSnakeBody().get(pos).getyPos();
					 s.getSnakeBody().get(pos).setxPos(x1);
					 s.getSnakeBody().get(pos).setyPos(y1);
					 changeBodyPositions(s, x2, y2, pos + 1);
				 }
		 }
		 public void createNewFruit()
		 {
			 int fruitX = ((int) (Math.random()*37)) * 24;
			 int fruitY = ((int) (Math.random()*33)) * 24;
			 fruits.add(new Body(fruitX,fruitY));
			 needsFruit = false;
		 }
		 public void eatFruit()
		 {
			 fruits.remove(fruits.get(0));
			 int xPos = snake.getSnakeBody().get(snake.getSnakeBody().size()-1).getxPos();
			 int yPos = snake.getSnakeBody().get(snake.getSnakeBody().size()-1).getyPos();
			 int size = snake.getSnakeBody().size();
			 size /= 3;
			 for(int i = 1; i < (size) + 2; i++)
				 {
					 switch(dir)
					 {
						 case "up":
							 snake.addToSnakeBody(xPos, yPos + 24);
							 break;
						 case "down":
							 snake.addToSnakeBody(xPos, yPos - 24);
							 break;
						 case "right":
							 snake.addToSnakeBody(xPos - 24, yPos);
							 break;
						 case "left":
							 snake.addToSnakeBody(xPos + 24, yPos);
							 break;
						 default:
							 System.out.println("bet!");
							 break;
					 }
				 }
			 System.out.println(snake.getSnakeBody().size());
			 
		 }
	}

