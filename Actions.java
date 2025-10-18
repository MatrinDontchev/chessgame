public class Actions
{
  // Files of chess board

  public static boolean whiteCanCastle = true, blackCanCastle = true ;

  public static boolean whiteTurn = true ;

  private static boolean correct = false;
  
  //Coordinates of original square 
  public static int r1 = 0 ;
  public static int c1 = 0 ;

  //Coordinates of new square
  public static int r2 = 0 ;
  public static int c2 = 0 ;

  //The chess board
  public String[][] board = 
  {
  {"r", "n", "b", "q", "k", "b", "n", "r"},
    
  {"p", "p", "p", "p", "p", "p", "p", "p"},
    
  {"_", "_", "_", "_", "_", "_", "_", "_"},
    
  {"_", "_", "_", "_", "_", "_", "_", "_"},
    
  {"_", "_", "_", "_", "_", "_", "_", "_"},
    
  {"_", "_", "_", "_", "_", "_", "_", "_"},
    
  {"P", "P", "P", "P", "P", "P", "P", "P"},
    
  {"R", "N", "B", "Q", "K", "B", "N", "R"}
  } ;

  /*public String[][] board = 
  {
  {"_", "_", "_", "_", "_", "_", "_", "_"},

  {"_", "_", "_", "_", "_", "_", "_", "_"},

  {"_", "_", "_", "_", "_", "_", "_", "_"},

  {"_", "_", "_", "Q", "_", "_", "_", "_"},

  {"_", "_", "_", "_", "_", "_", "_", "_"},

  {"_", "_", "_", "_", "_", "_", "_", "_"},

  {"_", "_", "_", "_", "_", "_", "_", "_"},

  {"_", "_", "_", "_", "_", "_", "_", "_"}
  } ;*/
  
  int[][] whiteCovered = 
  {
  {0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0},
  {2, 2, 3, 2, 2, 3, 2, 2},
  {1, 1, 1, 4, 4, 1, 1, 1},
  {0, 1, 1, 1, 1, 1, 1, 0},
  };

  int[][] blackCovered = 
  {
  {0, 1, 1, 1, 1, 1, 1, 0},
  {1, 1, 1, 4, 4, 1, 1, 1},
  {2, 2, 3, 2, 2, 3, 2, 2}, 
  {0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0},
  {0, 0, 0, 0, 0, 0, 0, 0},
  };

  public void setRow1(int row) {r1 = row;}
  public void setCol1(int col) {c1 = col;}
  public void setRow2(int row) {r2 = row;}
  public void setCol2(int col) {c2 = col;}
      
  public int getRow1() {return r1;}
  public int getCol1() {return c1;}
  public int getRow2() {return r2;}
  public int getCol2() {return c2;}
    
  public void covered(int[][] teamCovered)
  { 
    teamCovered = emptyCovered();

    String symbol;

    //print2(teamCovered);
    
    for(int r = 0; r < 8; r++)
      {
      for(int c = 0; c < 8; c++)
        {
        symbol = board[r][c];
        if(!symbol.equals("_"))
          {
          //System.out.println(r * 8 + c + 1);
          radiate(r, c, symbol.toLowerCase(), teamCovered); 
          }
        }
      }

    //print2(teamCovered);
  }

  public int[][] emptyCovered()
  {
    int[][] arr = new int[8][8];
    
    for(int r = 0; r < arr.length; r++)
      for(int c = 0; c < arr.length; c++)
        arr[r][c] = 0;

    return arr;
  }

  // Used to track the spots where an individual piece covers
  public void radiate(int row, int col, String letter, int[][] cover)
  {
    // Assumes piece can move north, east, south, west, northEast, southEast, southWest, and northWest
    boolean north = true;
    boolean east = true;
    boolean south = true;
    boolean west = true;

    boolean northEast = true;
    boolean southEast = true;
    boolean southWest = true;
    boolean northWest = true;

    int inc = 0;
    
    do
    {
      inc++;

      if(letter.equals("r") || letter.equals("k") || letter.equals("q"))
      {
        if(row - inc >= 0)  // Makes sure piece can move up and if so, it tracks spot it covers
          cover[row - inc][col]++ ; // Moves 1 up, adds 1 to cover
        if(col + inc <= 7) // Makes sure piece can move right and if so, it tracks spot it covers
          cover[row][col + inc]++ ; // Moves 1 right, adds 1 to cover
        if(row + inc <= 7) // Makes sure piece can move down and if so, it tracks spot it covers
          cover[row + inc][col]++ ; // Moves 1 down, adds 1 to cover
        if(col - inc >= 0) // Makes sure piece can move left and if so, it tracks spot it covers
          cover[row][col - inc]++ ; // Moves 1 left, adds 1 to cover
      }
      if(letter.equals("b") || letter.equals("k") || letter.equals("q"))
      {
        if(row - inc >= 0 && col + inc <= 7)  // Makes sure piece can move up and right, and if so, it tracks spot it covers  
          cover[row - inc][col + inc]++ ; // Moves 1 up 1 right, adds 1 to cover
        if(row + inc <= 7 && col + inc <= 7) // Makes sure piece can move right and down, and if so, it tracks spot it covers 
          cover[row + inc][col + inc]++ ; // Moves 1 right 1 down, adds 1 to cover
        if(row + inc <= 7 && col - inc >= 0) // Makes sure piece can move down and left, and if so, it tracks spot it covers
          cover[row + inc][col - inc]++ ; // Moves 1 down 1 left, adds 1 to cover
        if(row - inc >= 0 && col - inc >= 0) // Makes sure piece can move left and up, and if so, it tracks spot it covers
          cover[row - inc][col - inc]++ ; // Moves 1 left 1 up, adds 1 to cover
      }
      if(letter.equals("p"))
      {
        int direct = 1;
        
        if(!whiteTurn)
          direct = -1;
        
        if(row - inc >= 0 && col + inc <= 7)
          cover[row + direct][col + 1]++ ;
         
        if(row - inc >= 0 && col - inc >= 0)
          cover[row + direct][col - 1]++ ;
      }
      if(letter.equals("n"))
      {
        /*if(r1 > 1 && c1 > 0 && game.correctTeam(r1 - 2, c1 - 1, true) ||
           r1 > 1 && c1 < 7 && game.correctTeam(r1 - 2, c1 + 1, true) ||
           r1 > 0 && c1 > 1 && game.correctTeam(r1 - 1, c1 - 2, true) ||
           r1 > 0 && c1 < 6 && game.correctTeam(r1 - 1, c1 + 2, true) ||
           r1 < 6 && c1 > 0 && game.correctTeam(r1 + 2, c1 - 1, true) ||
           r1 < 6 && c1 < 7 && game.correctTeam(r1 + 2, c1 + 1, true) ||
           r1 < 7 && c1 > 1 && game.correctTeam(r1 + 1, c1 - 2, true) ||
           r1 < 7 && c1 < 6 && game.correctTeam(r1 + 1, c1 + 2, true))*/
        //return true ;
      }
    
    }
    while(!(letter.equals("k") || letter.equals("n") || letter.equals("p")) && inc < 7);
  }

  
//////////////////////////////////////////////////////////////////////

  
  // Prints the board
  public void print()
  {
    System.out.println() ;
    for(int i = 0; i < 8; i++)
    {
      System.out.print((8 - i) + ".  ") ;
      for(int j = 0; j < 8; j++)
      {
        if(board[i][j].equals("_"))
          System.out.print("- ");
        else
          System.out.print(board[i][j] + " ") ;
      }
      System.out.println();
    }
    System.out.println("\n    a b c d e f g h") ;
  }

  // Prints the squares covered by pieces
  public void print2(int[][] cover)
  {
    for(int i = 0; i < 8; i++)
    {
      for(int j = 0; j < 8; j++)
        System.out.print(cover[i][j] + " ") ;
      System.out.println() ;
    }
  }

  public void changeWhiteTurn() { whiteTurn = !whiteTurn; }
  public boolean getWhiteTurn() { return whiteTurn; }
  public boolean getWhiteCanCastle() { return whiteCanCastle; }
  public boolean getBlackCanCastle() { return blackCanCastle; }

/////////////////////////////////////////////////////////////////////////

  /* Process for performing a move from start to finish:

    gets player input

    1st input from
  
    1 - valid chess coordinate
    2 - correct team
    3 - whether piece can move

    2nd input to

    1 - valid chess coordinate
    2 - correct team
    3 - whether move is legal

    (conditional) asks for piece promotion if pawn goes to back rank
    
    perform move

    updates attacking squares
    
  */
    

    // 1 - Checks if input is a valid chess coordinate 
  public boolean validCoordinate(String move, boolean moving) 
  {
    if(move.length() == 2)
    {
      int r = 8 - Integer.parseInt(move.substring(1)) ;  
      int c = search(move.substring(0, 1)) ; 
        
      if(r < 8 && r >= 0 && c != -1)
      {
        if(!moving)
        {
          setRow1(r) ;
          setCol1(c) ;
        }
        else
        {
          setRow2(r) ;
          setCol2(c) ;
        }
        return true ;
      }
    }    
    return false ;
  } 



  
  // Ensures file is correct
  public int search(String target)
  {
    String[] files = {"a", "b", "c", "d", "e", "f", "g", "h"} ; 
    
    target = target.toLowerCase() ;

    for(int i = 0; i < 8; i++)
      if(files[i].equals(target))
        return i ;

    return -1 ;
  }




  // 2 - Checks if it's the piece's team's turn and makes sure it's capturing enemy pieces, not its own.
  public boolean correctTeam(int newR, int newC, boolean moving)
  {
    if(!moving) //Piece is chosen, ensures piece corresponds with team's turn
    {
      if((whiteTurn && board[newR][newC].compareTo("_") < 0) ||
        (!whiteTurn && board[newR][newC].compareTo("_") > 0))
          return true;
    }
    else //Piece is being moved, ensures piece doesn't capture another piece of the same team
    {
      if((whiteTurn && board[newR][newC].compareTo("_") >= 0) ||
         (!whiteTurn && board[newR][newC].compareTo("_") <= 0))
        return true;
    }
    return false ;
  }
  
  

  
  //Checks if inputter correctly types in the piece they want to promote to
  public boolean validPromotionPiece(String piece)
  {
    boolean valid = false ;
    
    piece = piece.toLowerCase() ;

    if(piece.equals("n") ||
       piece.equals("b") ||
       piece.equals("r") ||
       piece.equals("q"))
    {
      valid = true ;

      if(whiteTurn)
        board[r2][c2] = piece.toUpperCase() ;
      else
        board[r2][c2] = piece ;
    
    }
    return valid ;
  }


  
  
  // Goes through a series of steps to determine whether input is accurate and will be performed; only method with print outputs
  public boolean type(String coordinate, boolean moving)
  {       
    int[][] teamCovered;
    
    int tempR;
    int tempC;

    String symbol;

    boolean correct = false;

    // Step 1
    if(!validCoordinate(coordinate, moving))
    {
      System.out.println("\nNot a valid chess coordinate. Try again.");
      return false ;
    }

    //Ensures that arguments of step 2 could be either from and to coordinates
    if(!moving)
      {
        tempR = r1;
        tempC = c1;
      }
      else
      {
        tempR = r2;
        tempC = c2;
      }
    
    // Sets letter of piece's name to symbol
    symbol = board[r1][c1].toLowerCase() ;

    // Step 2
    if(!correctTeam(tempR, tempC, moving))
    {
      System.out.println("\nYou cannot select this square. Try again.") ;
      return false ;
    }
    
    // Step 3   
    if(symbol.equals("r"))
    {
      Rook rook = new Rook(moving, this) ;
      correct = rook.correct;
    }
    if(symbol.equals("b"))
    {
      Bishop bishop = new Bishop(moving, this) ;
      correct = bishop.correct;
    }
    if(symbol.equals("q"))
    {
      Queen queen = new Queen(moving, this) ;
      correct = queen.correct;
    }
    if(symbol.equals("k"))
    {
      King king = new King(moving, this) ;
      correct = king.correct;
    }
    if(symbol.equals("p"))
    {
      Pawn pawn = new Pawn(moving, this) ;
      correct = pawn.correct;
    }
    if(symbol.equals("n"))
    {
      Knight knight = new Knight(moving, this);
      correct = knight.correct;
    }

    if(whiteTurn)
      teamCovered = whiteCovered;
    else
      teamCovered = blackCovered;
    
    if(!correct)
      System.out.println("\nThis is an illegal move. Try again");
    
    covered(teamCovered);
    
    return correct;
  }  




  // Checks if team is in check
  public boolean isInCheck()
  {
    int kRow ;
    int kCol ;
    
    return false ;
  }



  
  //Updates the board
  public void performMove()
  {
    if(!board[r1][c1].equals("_"))
    {
    if(board[r2][c2].equals("k"))
      gameOver(true) ;
    else if (board[r2][c2].equals("K"))
      gameOver(false) ;
    
    String temp = board[r1][c1] ;
    
    board[r1][c1] = "_" ;
      
    board[r2][c2] = temp ;

    /*if(whiteTurn)
    {
      print2(wCover) ;
    }
    else
    {
      print2(bCover) ;
    }*/

    if(temp.equals("K"))
      whiteCanCastle = false ; 
    else if(temp.equals("k"))
      blackCanCastle = false ; 
    }
  }




  //Checks if castling can be done and performs said castle if possible
  public boolean performCastle(boolean shortCastle)
  {
    boolean valid = false;

    int rank = 7;
    String rook = "R";
    String king = "K";
    
    if(!whiteTurn)
    {
      rank = 0;
      rook = "r";
      king = "k";
    }

    if(whiteTurn && whiteCanCastle || !whiteTurn && blackCanCastle)
    {
      if(shortCastle)
      {
        System.out.println("Short");
        valid = board[rank][5].equals("_") &&
                board[rank][6].equals("_") &&
                board[rank][7].equals(rook) ;
        if(valid)
        {
          board[rank][7] = "_" ;
          board[rank][5] = rook ;
          board[rank][6] = king ;
          board[rank][4] = "_" ;
        }
      }
      else
      {
        System.out.println("hello");
        valid = board[rank][1].equals("_") &&
                board[rank][2].equals("_") &&
                board[rank][3].equals("_") &&
                board[rank][0].equals(rook) ;
        if(valid)
        {
          board[rank][0] = "_" ;
          board[rank][3] = rook ;
          board[rank][2] = king ;
          board[rank][4] = "_" ;
        }
      }
    }

    if(valid)
    {
      if(whiteTurn)
        whiteCanCastle = false;
      else
        blackCanCastle = false;
    }
    return valid ;
  
  }


  
  
  //Ends the game
  public void gameOver(boolean whiteWins)
  {
    if(whiteWins)
      System.out.print("\nWhite captured black's king. White wins the game!") ;
    else
      System.out.print("\nBlack captured white's king. Black wins the game!") ;

    System.exit(0) ;
  }
}