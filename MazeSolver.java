import java.util.Scanner; 
import java.io.File; 
import java.io.IOException; 
import java.io.FileWriter; 
import java.io.BufferedWriter; 

/**
* public Class MazeSolver 
* Scans files and calls an instance of the contents in order to call solver and solve. 
*/ 
public class MazeSolver{
  public static void main(String[] args){
    
    if(args.length == 0){
      try{
        Scanner scanner = new Scanner(System.in);
        File inputFile = new File("f");
        BufferedWriter writer = new BufferedWriter(new FileWriter("f"));
        while (scanner.hasNextLine()){
          writer.write(scanner.nextLine() + "\n");
        }
        writer.close(); 
        Maze maze = new Maze(inputFile);
        MazeViewer viewer = new MazeViewer(maze); 
        maze.solver(maze.getStart()); 
      }
      catch(IOException e){
        System.out.println("IO Exception");
      }
    }
    else{
      String argsString = args[0].toString(); 
      File myFile = new File(argsString); 
      Maze maze = new Maze(myFile); 
      MazeViewer viewer = new MazeViewer(maze); 
      maze.solver(maze.getStart()); 
    
    }

    
    
  }

  
}