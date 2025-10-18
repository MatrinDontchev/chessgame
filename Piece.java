public abstract class Piece
{
  public boolean correct;
  
  protected Actions game;

  protected int r1;
  protected int c1;
  protected int r2;
  protected int c2;

  public abstract boolean canMove();

  public abstract boolean behavior();

  public abstract void radiate();
}