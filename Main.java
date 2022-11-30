public class Main{

    public static void main(String args []){

        int [][] world = {{0,1,0},{0,1,0},{0,1,0}}; 

        int test = calculateNeighbors(world, 1, 0);

        int heatmap [][] = generateHeatmap(world);

        

    }


    public static int [][] generateHeatmap(int [][] world){

        int [][] heatmap = {{0,0,0},{0,0,0},{0,0,0}};

        for(int i = 0; i < 3; i++){               // row loop
            for(int j = 0; j < 3; j++){           // column loop
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

        if(endRow > 2){
            endRow = 2;
        }

        if(endColumn > 2){
            endColumn = 2;
        }


        int neighbors = 0;

        for(int i = startRow; i <= endRow; i++){
            for(int j = startColumn; j <= endColumn; j++){
                if(world[i][j] == 1){
                    neighbors++;
                    System.out.println("Neighbor found at row:" + i + ", column: " + j);
                } else {
                    System.out.println("No neighbnor found at row:" + i + ", column: " + j);
                }
            }

        }




        return neighbors;

    }









}