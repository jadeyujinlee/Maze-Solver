# Maze-Solver

Although it may have not been the most efficient implementation for a self-solving maze, this project was an exercise in recursion. 
There are some files with given support code, but essentially the maze is solved by a series of implicit "explorations" that are put onto the call stack after every square of a maze is explored. If the solver hits a dead end (i.e, meaning "FAILED!"), the code will recursively find a different route to take until it hits the final destination of the maze. 

There is a solvable maze commented out in the Maze.java file, as well as two other separate mazes in different files to test out the code. 

The DisplayableMaze.java, MazeContents.java, MazeDirection.java, MazeLocation.java, and MazeViewer.java files are of given starter code written by Professor Nicholas Howe of Smith College. 

