public class Pawn extends Piece
{
  public Pawn(boolean moving, Actions game)
  {
    this.game = game;
    this.r1 = game.r1;
    this.c1 = game.c1;
    this.r2 = game.r2;
    this.c2 = game.c2;
    
    if(!moving)
      correct = canMove();
    else
      correct = behavior();
  }
  
  public boolean canMove()
  {
    int way = -1;
    
    if(!game.whiteTurn)
      way = 1;
    
    if(game.board[r1 + way][c1].equals("_") ||
     c1 > 0 && !game.board[r1 + way][c1 - 1].equals("_") && game.correctTeam(r1 + way, c1 - 1, true) ||
     c1 < 7 && !game.board[r1 + way][c1 + 1].equals("_") && game.correctTeam(r1 + way, c1 + 1, true))
      
      return true ;
    return false;
  }
  
  public boolean behavior()
  {        
    int rowDif = r1 - r2 ; // + if white's turn, - if black's turn
    int colDif = c1 - c2 ;

    int direction = 1 ;
    int pawnRow = 6 ;

    if(!game.whiteTurn)
    {
      direction = -1 ;
      pawnRow = 1 ;
    }

    if(r1 == pawnRow && rowDif == direction * 2 && colDif == 0 && 
       game.board[r1 - direction][c1].equals("_"))
    {
      game.performMove() ;
      return true ;
    }
    else if(rowDif == direction)
    {
      if(colDif == 0 && game.board[r1 - direction][c1].equals("_"))
      {
        game.performMove() ;
        return true ;
      }
      else if(Math.abs(colDif) == 1 && !(game.board[r1 - direction][c1 -         
              colDif].equals("_")))
      {  
        game.performMove() ;
        return true ;
      }
    }
    return false ;
  } 

  public void radiate()
  {

  }
}