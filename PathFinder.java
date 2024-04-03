import java.io.*;
import java.util.*;

/*
 * Position is a self-referential class to represent a position in a path
 */
class Position{
    public int i;     //row
    public int j;     //column
    public char val;  //1, 0, or 'X'

    // reference to the previous position (parent) that leads to this position on a path
    Position parent;

    Position(int x, int y, char v){
        i=x; j = y; val=v;
    }

    Position(int x, int y, char v, Position p){
        i=x; j = y; val=v;
        parent=p;
    }

}


public class PathFinder {

    // main method: reads in maze file and finds path using both stackSearch and queueSearch
    public static void main(String[] args) throws IOException {
        if(args.length<1){
            System.err.println("***Usage: java PathFinder maze_file");
            System.exit(-1);
        }

        char [][] maze;
        maze = readMaze(args[0]);
        printMaze(maze);
        Position [] path = stackSearch(maze);
        if( path == null ){
            System.out.println("Maze is NOT solvable (no valid path identified in stackSearch).");
        } else {
            System.out.println("stackSearch Solution:");
            printPath(path);
            printMaze(maze);
        }

        char [][] maze2 = readMaze(args[0]);
        path = queueSearch(maze2);
        if( path == null ){
            System.out.println("Maze is NOT solvable (no valid path identified in queueSearch).");
        } else {
            System.out.println("queueSearch Solution:");
            printPath(path);
            printMaze(maze2);
        }
    }

    // TODO: implement the method queueSearch
    // public static Position [] queueSearch(char [][] maze)

    // TODO: implement the method stackSearch
    public static Position [] stackSearch(char [][] maze) {
    	Stack<Position> xPath=new Stack<>();
        int xLength=maze.length;
    }

    // prints path through maze
    public static void printPath(Position [] path){
        System.out.print("Path: ");
        for(Position p : path){
            System.out.print("(" + p.i + "," + p.j + ") ");
        }
        System.out.println();
    }

    // reads in maze from file
    public static char [][] readMaze(String filename) throws IOException{
        char [][] maze;
        Scanner scanner;
        try{
            scanner = new Scanner(new FileInputStream(filename));
        }
        catch(IOException ex){
            System.err.println("*** Invalid filename: " + filename);
            return null;
        }

        int N = scanner.nextInt();
        scanner.nextLine();
        maze = new char[N][N];
        int i=0;
        while(i < N && scanner.hasNext()){
            String line =  scanner.nextLine();
            String [] tokens = line.split("\\s+");
            int j = 0;
            for (; j< tokens.length; j++){
                maze[i][j] = tokens[j].charAt(0);
            }
            if(j!=N){
                System.err.println("*** Invalid line: " + i + " has wrong # columns: " + j);
                return null;
            }
            i++;
        }
        if(i!=N){
            System.err.println("*** Invalid file: has wrong number of rows: " + i);
            return null;
        }
        return maze;
    }

    // prints maze array
    public static void printMaze(char[][] maze){
        System.out.println("Maze: ");
        if(maze==null || maze[0] == null){
            System.err.println("*** Invalid maze array");
            return;
        }

        for(int i=0; i< maze.length; i++){
            for(int j = 0; j< maze[0].length; j++){
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

}
