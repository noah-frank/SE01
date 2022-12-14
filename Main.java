import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main{

    public static void main(String args []) throws InterruptedException{

        int [][] world2 = {{0,1,0,0},{0,1,0,0},{0,1,0,0},{0,0,0,0}}; 

        int [][] world = {{1,0,0,0},{1,0,1,0},{1,1,1,0},{0,0,1,0}};
        

        System.out.println("\033[2J");

        printTwoDimArray(world);
        System.out.println("Run: 0");

        TimeUnit.SECONDS.sleep(1);


        world = extendWorld(world);
        world = extendWorld(world);
        world = extendWorld(world);
        world = extendWorld(world);

        runGame(world);

    }


    public static int [][] extendIfNeeded(int [][] world){


        if(Arrays.asList(world[0]).contains(1)){    // add row before first row

        }

        if(Arrays.asList(world[world.length-1]).contains(1)){    // add row behind last row

        }

        return world;

    }


    public static int [][] extendWorld(int [][] world){ // extends a world one row and colum in each direction (up,down,left,right)

        int [][] extendedWorld = new int [world.length+2][world[0].length+2];

        for(int i = 1; i < world.length; i++){
            for(int j = 1; j < world[0].length; j++){
                extendedWorld[i][j] = world[i-1][j-1];
            }
        }

        return extendedWorld;

    }



    public static void runGame(int [][] world) throws InterruptedException{

        int previousIteration [][] = world;
        int nextIteration [][];

        ArrayList<int [][]> previousStates = new ArrayList<int [][]>();
        
        previousStates.add(world.clone());
        

        for(int i = 1; true; i++){
            System.out.println("\033[2J");
            nextIteration = generateNextIteration(previousIteration);
            printTwoDimArray(nextIteration);
            System.out.println("Run: " + i);

            
            boolean finalState = false;
            boolean formerState = false;

            if(Arrays.equals(previousIteration, nextIteration)){
                System.out.println("Final state"); 
                finalState = true;                                                        // stop iterating if world did not change
            } else {

                for(int [][] state : previousStates){       
                          
                    // stop iterating if former state                               
                    if(Arrays.deepEquals(state, nextIteration)){
                        System.out.println("Former state!");
                        formerState = true;
                        break;
                    }
                }

            }   


            if(formerState || finalState){
                break;
            }

            previousStates.add(nextIteration.clone());


            previousIteration = nextIteration.clone();
            TimeUnit.SECONDS.sleep(1);
        }
    }


    public static void printTwoDimArray(int [][] array){

        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }

    }


    public static int [][] generateHeatmap(int [][] world){

        int [][] heatmap = new int [world.length][world[0].length];

        for(int i = 0; i < world.length; i++){               // row loop
            for(int j = 0; j < world[0].length; j++){           // column loop
                heatmap[i][j] = calculateNeighbors(world, i, j);
            }
        }


        return heatmap;

    }


    public static int calculateNeighbors(int [][] world, int row, int column){


        int startRow = row - 1;
        int startColumn = column - 1;
        int endRow = row + 1;
        int endColumn = column + 1;

        if(startRow < 0){
            startRow = 0;
        }

        if(startColumn < 0){
            startColumn = 0;
        }

        if(endRow > world.length-1){
            endRow = world.length-1;
        }

        if(endColumn > world[0].length-1){
            endColumn = world[0].length-1;
        }


        int neighbors = 0;

        for(int i = startRow; i <= endRow; i++){
            for(int j = startColumn; j <= endColumn; j++){
                if(world[i][j] == 1){
                    if(!(i == row && j == column)){               // Do not count the cell as its own neighbor
                        neighbors++;
                    }
                    // System.out.println("Neighbor found at row:" + i + ", column: " + j);
                } else {
                    // System.out.println("No neighbnor found at row:" + i + ", column: " + j);
                }
            }

        }


        return neighbors;

    }


    public static int [][] generateNextIteration(int [][] world){

        int [][] nextIteration = new int [world.length][world[0].length];


        for(int i = 0; i < world.length; i++){
            for(int j = 0; j < world[0].length; j++){
                int neighbors = calculateNeighbors(world, i, j);
                if(world[i][j] == 0 && neighbors == 3){    // if dead cell has 3 neighbors
                    nextIteration[i][j] = 1;                                     // new cell is born
                }
                if(world[i][j] == 1 && (neighbors == 2 || neighbors == 3)){ // if cell has 2 or 3 neighbors
                    nextIteration[i][j] = 1;                                                                            // cell survive
                }
            }
        }


        return nextIteration;

    }




}