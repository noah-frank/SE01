public class Main{

    public static void main(String args []){

        int [][] world = {{0,1,0},{0,10},{0,1,0}}; 


    }


    public int [][] generateHeatmap(int [][] world){

        int [][] heatmap = {{0,0,0},{0,0,0},{0,0,0}};

        for(int i = 0; i < 3; i++){               // row loop
            for(int j = 0; j < 3; j++){           // column loop
                heatmap[i][j] = calculateNeighbors(world, i, j);
            }
        }


        return null;

    }


    public int calculateNeighbors(int [][] world, int row, int column){


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
            endRow = 0;
        }

        if(endColumn > 2){
            endColumn = 0;
        }







        return null;

    }









}