public class Bishop extends Piece
{
  public Bishop(boolean moving, Actions game)
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
  {//Only returns true IF at least one spot isn't a square of the same team
    if(r1 > 0 && c1 > 0 && game.correctTeam(r1 - 1, c1 - 1, true) ||
       r1 > 0 && c1 < 7 && game.correctTeam(r1 - 1, c1 + 1, true) ||
       r1 < 7 && c1 > 0 && game.correctTeam(r1 + 1, c1 - 1, true) ||
       r1 < 7 && c1 < 7 && game.correctTeam(r1 + 1, c1 + 1, true))
      return true ;
    return false;
  }

  public boolean behavior()
  {
    int fromR;
    int toR;
    int fromC;
    int toC;

    if(r1 > r2)
    {
      fromR = r2;
      toR = r1;
    }
    else
    {
      fromR = r1;
      toR = r2;
    }

    if(c1 > c2)
    {
      fromC = c2;
      toC = c1;
    }
    else
    {
      fromC = c1;
      toC = c2;
    }

    // if the bishop moves more than 1 square, then check if there are any pieces blocking the bishop
    if(Math.abs(r2 - r1) >= 2) 
    {
      if((r1 > r2 && c1 > c2) || (r1 < r2 && c1 < c2))
      {
        for(int r = fromR + 1, c = fromC + 1; c < toC; r++, c++)
          if(!(game.board[r][c].equals("_")))
            return false;
      }
      else if((r1 < r2 && c1 > c2) || (r1 > r2 && c1 < c2))
      {
        for(int r = toR - 1, c = fromC + 1; c < toC; r--, c++)
          if(!(game.board[r][c].equals("_")))
            return false;
      }
    }

    if(Math.abs(r2 - r1) == Math.abs(c2 - c1))
    {
      game.performMove();
      return true;
    }

    return false ;
  }

  public void radiate()
  {
    
  }
}