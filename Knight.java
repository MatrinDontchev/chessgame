public class Knight extends Piece
{
  public Knight(boolean moving, Actions game)
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
    if(r1 > 1 && c1 > 0 && game.correctTeam(r1 - 2, c1 - 1, true) ||
       r1 > 1 && c1 < 7 && game.correctTeam(r1 - 2, c1 + 1, true) ||
       r1 > 0 && c1 > 1 && game.correctTeam(r1 - 1, c1 - 2, true) ||
       r1 > 0 && c1 < 6 && game.correctTeam(r1 - 1, c1 + 2, true) ||
       r1 < 6 && c1 > 0 && game.correctTeam(r1 + 2, c1 - 1, true) ||
       r1 < 6 && c1 < 7 && game.correctTeam(r1 + 2, c1 + 1, true) ||
       r1 < 7 && c1 > 1 && game.correctTeam(r1 + 1, c1 - 2, true) ||
       r1 < 7 && c1 < 6 && game.correctTeam(r1 + 1, c1 + 2, true))
      return true ;
    return false;
  }
  
  public boolean behavior()
  {
    int rowDif = Math.abs(r2 - r1) ;
    int colDif = Math.abs(c2 - c1) ;

    if((rowDif == 2 && colDif == 1) ||
       (rowDif == 1 && colDif == 2))
    {
      game.performMove() ;
      return true ;
    }

    return false ;
  }

  public void radiate()
  {

  }
}