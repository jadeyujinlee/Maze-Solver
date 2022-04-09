import java.lang.Math; 
import java.io.FileNotFoundException; 
import java.util.ArrayList; 
import java.io.File; 
import java.util.Scanner; 

/** 
* Represents our maze 
* @implements DisplayableMaze 
* @param height, width, theMaze 
* Randomly generates a maze 
*/
public class Maze implements DisplayableMaze {

  public int height; 
  public int width; 
  public MazeContents theMaze [] []; 

  public Maze(int height, int width){
    this.height = height;
    this.width = width; 
    theMaze = new MazeContents [height] [width]; 
    for(int h = 0; h < height; ++h){
      for(int w = 0; w < width; ++w){
        if(h == 0 || w == 0 || h == height - 1 || w == width -1){
          theMaze [h] [w] = MazeContents.WALL; 
        }
        else if(h == 1 && w == 1){
          theMaze [h] [w] = MazeContents.OPEN;
        }
        else if(h == height - 2 && w == width - 2){
          theMaze [h] [w] = MazeContents.OPEN;
        }
        
        else{
          if(Math.random() < 0.5 && Math.random() < 0.45){
            theMaze [h] [w] = MazeContents.WALL; 
          }
          else{
            theMaze [h] [w] = MazeContents.OPEN;
          }
        }
      }
    }
  }
  
  /** 
  * @param myFile 
  * @throws Exception FileNotFoundException 
  * Copy constructor that scans command line input
  * Scans through a file
  */
  public Maze(File myFile){
    int height; 
    int width; 
    ArrayList<Character[]> fileMaze = new ArrayList<Character[]>(); 
    try{
      Scanner input = new Scanner(myFile); 
      while(input.hasNext()){ 
        String nextLine = input.nextLine(); 
        Character[] line = new Character[nextLine.length()]; 
        for (int i = 0; i < nextLine.length(); i++){
          line[i] = nextLine.charAt(i); 
        }
        fileMaze.add(line); 
      }
      input.close(); 
    }
    catch(FileNotFoundException e){
      System.out.println("No file here.");
    }


    height = fileMaze.size(); 
    width = fileMaze.get(0).length; 

    this.height = height; 
    this.width = width; 

    theMaze = new MazeContents [height] [width]; 
    for (int h = 0; h < height; ++h){
      Character[] row = fileMaze.get(h); 
      for (int w = 0; w < width; ++w){
        if (row[w].equals('#')){
          theMaze[h][w] = MazeContents.WALL; 
          

        }
        else{
          theMaze[h][w] = MazeContents.OPEN; 
           
        }
      }
    }
  }

  /** @return height of maze grid */
  public int getHeight() {
    return height;
  }

  /** @return width of maze grid */
  public int getWidth() {
    return width;
  }

  /** @return contents of maze grid at row i, column j */
  public MazeContents getContents(int i, int j) {
    return theMaze[i][j]; 
  }

  /** @return location of maze start point */
  public MazeLocation getStart() {
    return new MazeLocation(1,1);
  }

  /** @return location of maze finish point */
  public MazeLocation getFinish() {
    return new MazeLocation(getHeight()-2,getWidth()-2);
  }  

  /** 
  * @param location 
  * @returns true for different recursive logics 
  * @returns false for different recursive logics. 
  * Method using enumerated directions and constants
  * Uses fields from MazeContents, MazeDirection, and MazeLocation 
  */ 
  public boolean solver(MazeLocation location){
    try{Thread.sleep(50);} catch (InterruptedException e) {};
    
    int h = location.getRow(); 
    int w = location.getCol(); 
    MazeContents locationContents = theMaze [h][w]; 

    if(location.equals(getFinish())){
      locationContents = MazeContents.PATH; 
      theMaze[h][w] = locationContents; 
      
      return true; 
      
    }

    else if(locationContents == MazeContents.WALL || locationContents == MazeContents.VISITED || locationContents == MazeContents.DEAD_END){
      return false; 
      
    }

    else{
      //making contents editable 
      locationContents = MazeContents.VISITED; 
      theMaze[h][w] = locationContents; 

      //east
      MazeDirection east = MazeDirection.EAST;
      MazeLocation eastLoc = location.neighbor(east); 
      boolean eastResult = solver(eastLoc); 
      if(eastResult){
        locationContents = MazeContents.PATH;
        theMaze[h][w] = locationContents; 
        return true; 

      }
      
      //south 
      MazeDirection south = MazeDirection.SOUTH; 
      MazeLocation southLoc = location.neighbor(south); 
      boolean southResult = solver(southLoc); 
      if(southResult){
        locationContents = MazeContents.PATH;
        theMaze[h][w] = locationContents; 
        return true; 

      }

      //north 
      MazeDirection north = MazeDirection.NORTH; 
      MazeLocation northLoc = location.neighbor(north);
      boolean northResult = solver(northLoc);
      if(northResult){
        locationContents = MazeContents.PATH;
        theMaze[h][w] = locationContents; 
        return true; 

      }

      //west 
      MazeDirection west = MazeDirection.WEST; 
      MazeLocation westLoc = location.neighbor(west); 
      boolean westResult = solver(westLoc); 
      if(westResult){
        locationContents = MazeContents.PATH;
        theMaze[h][w] = locationContents; 
        return true; 

      }

      if(northResult || eastResult || southResult || westResult){
        locationContents = MazeContents.PATH;
        theMaze[h][w] = locationContents; 
        return true; 
      }
      

    }
    locationContents = MazeContents.DEAD_END;
    theMaze[h][w] = locationContents; 
    return false; 
    
  }

    
}