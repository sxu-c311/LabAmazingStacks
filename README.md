# Stacks for backtracking
## Tasks
Use a stack to create a maze.
1.	Download the NetBeans project.
3.	Try running the program. It pops up a window with a picture of a room full of cells. We will carve out a maze from this room by breaking down walls.
4.	You will implement the `carveMazeUsingStack` method of the CarvedMaze class. Currently the method is being called, but since it does nothing, no maze is created. Look at the classes, and explain how (where) the method is called, going all the way back to the main method.



5.	The idea of this method is to start in the starting cell and move through the maze, “carving” out cells as you go. (Use the `carve` method to carve out the cell and break down the wall in between.) Any time there is a choice on which direction to take, make the decision randomly. (You can call the `randomUncarvedNeighbor` method to make this decision.) The picture shows what it might look like part way through.
6.	If the carving process hits a cell that has already been carved, you have to backtrack. `randomUncarvedNeighbor` returns null when all the neighbors have already been carved, so you can use the result of this method call to check for this case. You have to backtrack to a cell that has uncarved neighbors. We will not break down any walls during backtracking. (If too many walls are broken, the maze will be too easy.) This backtracking procedure ensures that once the maze is completely carved out, any cell can be reached from any other cell. (In particular, there is a path from the start to the end, so the maze has a solution.) This is why we use a carving/backtracking procedure rather than just randomly breaking down walls.
7.	Implement the `carveMazeUsingStack` method. It carves out the cells one by one and uses a stack to deal with the backtracking. You can stop carving when you reach the end cell.
    1.	Outline your approach first using pseudocode:



    2.	Implement the method, and show the code and result to the instructor: ______
8.	Now fix the code so that the entire maze is carved (ignore the end cell). ______
9.	Try to solve some of the random mazes. Do they seem a bit easy? The person solving the maze doesn’t have to make too many choices. This is because the program only backtracks when it reaches a dead end. There are algorithms that generate better mazes. Explore some of the options at this website:
http://weblog.jamisbuck.org/2011/2/7/maze-generation-algorithm-recap
