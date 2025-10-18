import java.applet.Applet ;
import javax.swing.JFrame ;
import java.util.Scanner ;
import java.awt.Graphics ;
import java.awt.Color ;

public class Main extends JFrame
{
  static boolean play = true ;
  static int move = 1 ;

  boolean validCoordinate;
  boolean correctTeam;
  
  Actions game = new Actions() ;
  
  public static void main(String[] args) 
  {
    Main t = new Main() ;
    t.setSize(600, 600) ;
    t.setVisible(true) ;
    t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
  }

/////////////////////////////////////////////////////////////
  
  public void paint(Graphics p)
  {
    Color lSqr = new Color(225, 198, 153) ;
    Color dSqr = new Color(100, 60, 10) ;
    
    ////////////////////////////////////////////////////////////

    int x1 = 0 ;
    int y1 = 0 ;
    int x2 = 0 ;
    int y2 = 0 ;

    String letter = "x";

    Scanner input = new Scanner(System.in) ;

    System.out.println("Welcome to chess made 100% in Java!") ;

    //game.print() ;
    
    System.out.println("\nIn order to move, type in the coordinates from files, a to h, and ranks, 1 to 8, of the piece you want to move and then where you want to move that piece. Let's begin!") ;
    
//////////////////////////////////////////////
    while(play)
    {
      drawBoard(p, lSqr, dSqr);
      
      System.out.print("\nMove "+ move + " - ") ;
      
      if(game.getWhiteTurn())
        System.out.println("White team, choose a piece to move:") ;
      else
        System.out.println("Black team, choose a piece to move:") ;

      while(!game.type(input.nextLine(), false))
        System.out.print("") ;

      System.out.println("\nChoose where to move that piece:");

      while(!game.type(input.nextLine(), true))
        System.out.print("") ;

      x1 = game.getCol1();
      y1 = game.getRow1();
      x2 = game.getCol2();
      y2 = game.getRow2();
      
      letter = game.board[y2][x2].toLowerCase() ;

      if(letter.equals("p"))
        if(y2 == 0 || y2 == 7)
        {
          System.out.println("\nType in the first sounding letter of the piece you want to promote to:") ;
          while(!(game.validPromotionPiece(input.nextLine())))
            System.out.println("\nInvalid input. Try again.") ;

          letter = game.board[y2][x2].toLowerCase() ;
        }
           
      //game.print() ;
    
      if(game.getWhiteTurn())
        p.setColor(Color.WHITE) ;
      else 
        p.setColor(Color.BLACK) ;
  
      if(!game.getWhiteTurn())
        move++ ;
      
      game.changeWhiteTurn() ;
    }
  }
  
  public void drawBoard(Graphics p, Color lSqr, Color dSqr)
  {
    p.setColor(Color.BLACK) ;
    
    String[] files = {"a", "b", "c", "d", "e", "f", "g", "h"} ;

    for(int i = 0; i < 8; i++)
    {
      p.drawString(8 - i + "", 80, 130 + 50 * i) ;
      p.drawString(files[i], 120 + 50 * i, 530) ;
    }
    
    p.setColor(lSqr) ;

    for(int i = 0; i < 8; i++)
    {
      for(int j = 0; j < 8; j++)
      {
        p.fillRect(100 + 50 * j, 100 + 50 * i, 50, 50) ;  

        if(j != 7)
        {
          if(p.getColor() == lSqr)
            p.setColor(dSqr) ;
          else
            p.setColor(lSqr) ;
        }
      }
    }

    p.setColor(dSqr) ;

    for(int r = 0; r < 8; r++)
      for(int c = 0; c < 8; c++)
      {
        String temp = game.board[r][c].toLowerCase() ;
        String tempCase = game.board[r][c] ;

        if(tempCase.compareTo("_") < 0)
          p.setColor(Color.WHITE) ;
        else
          p.setColor(Color.BLACK) ;

        if(temp.equals("r"))
          drawRook(p, 100 + 50 * c, 100 + 50 * r) ;
        else if(temp.equals("b"))
          drawBishop(p, 100 + 50 * c, 100 + 50 * r) ;
        else if(temp.equals("n"))
          drawKnight(p, 100 + 50 * c, 100 + 50 * r) ;
        else if(temp.equals("k"))
          drawKing(p, 100 + 50 * c, 100 + 50 * r) ;
        else if(temp.equals("q"))
          drawQueen(p, 100 + 50 * c, 100 + 50 * r) ;
        else if(temp.equals("p"))
          drawPawn(p, 100 + 50 * c, 100 + 50 * r) ;
      }
  }
  
  public void drawRook(Graphics p, int x, int y)
  { 
    p.fillRect(15 + x, 9 + y, 4, 3) ;
    p.fillRect(23 + x, 9 + y, 4, 3) ;
    p.fillRect(31 + x, 9 + y, 4, 3) ;
    p.fillRect(15 + x, 12 + y, 20, 8) ;
    p.fillRect(18 + x, 20 + y, 14, 18) ;
    p.fillRect(16 + x, 38 + y, 18, 5) ;
  }
  
  public void drawBishop(Graphics p, int x, int y)
  {
    p.fillArc(18 + x, 10 + y, 14, 25, 60, 335) ;
    p.fillRect(18 + x, 33 + y, 14, 6) ;
  }
  
  public void drawKnight(Graphics p, int x, int y)
  {
    int[] kX = {14, 26, 14, 10, 30, 32, 35, 35 } ;
    int[] kY = {34, 26, 28, 23, 16, 20, 28, 34} ;

    for(int i = 0; i < kX.length; i++)
    {
      kX[i] += x ;
      kY[i] += y ;      
    }
    
    p.fillPolygon(kX, kY, kX.length) ;
    p.fillRect(12 + x, 34 + y, 26, 4) ;
  }

  public void drawKing(Graphics p, int x, int y)
  {
    p.fillOval(8 + x, 20 + y, 34, 20) ;
    p.fillRect(23 + x, 8 + y, 4, 16) ;
    p.fillRect(17 + x, 12 + y, 16, 4) ;
  }

  public void drawQueen(Graphics p, int x, int y)
  {
    int[] qX = {17, 17, 21, 25, 29, 33, 33} ;
    int[] qY = {35, 15, 35, 10, 35, 10, 35} ;

    for(int i = 0; i < qX.length; i++)
    {
      qX[i] += x ;
      qY[i] += y - 3 ;      
    }

    p.fillPolygon(qX, qY, qX.length) ;
    p.fillRect(15 + x, 32 + y, 20, 10) ;
  }

  public void drawPawn(Graphics p, int x, int y)
  {
    p.fillOval(13 + x, 21 + y, 24, 24) ;
    p.fillOval(18 + x, 12 + y, 14, 14) ;
    p.fillOval(21 + x, 7 + y, 8, 8) ;
    p.fillRect(13 + x, 32 + y, 24, 13) ;
  }

  /*public void fillSquares(Graphics p, int x1, int y1, int x2, int y2 )
  {
    Color lSqr = new Color(225, 198, 153) ;
    Color dSqr = new Color(100, 60, 10) ;

    if((y1 % 2 == 0 && x1 % 2 == 0) ||
       (y1 % 2 == 1 && x1 % 2 == 1))
      p.setColor(lSqr) ;
    else
      p.setColor(dSqr) ;
    
    p.fillRect(100 + 50 * x1, 100 + 50 * y1, 50, 50) ;

    if((y2 % 2 == 0 && x2 % 2 == 0) ||
       (y2 % 2 == 1 && x2 % 2 == 1))
      p.setColor(lSqr) ;
    else
      p.setColor(dSqr) ;

    p.fillRect(100 + 50 * x2, 100 + 50 * y2, 50, 50) ;
  }*/
}