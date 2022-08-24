public class Board {
    public enum Cell {
        N,
        X,
        O
    }
    
    Cell[][] boardState;

    public Board() {
        boardState = new Cell[][]{
            {Cell.N, Cell.N, Cell.N},
            {Cell.N, Cell.N, Cell.N},
            {Cell.N, Cell.N, Cell.N}
        };
    }

    // For playing a moves
    public void move(int[] place, Cell player) {
        boardState[place[0]][place[1]] = player;
    }

    // String representation for the board
    public String display() {
        String result = "";
        result += "___________\n";

        for (Cell[] i: boardState){
            result += "|";
            for (Cell j: i) {
                if (j == Cell.O) {
                    result += "_O_";
                } else if(j == Cell.X) {
                    result += "_X_";
                } else {
                    result += "_ _";
                }
            }
            result += "|\n";
        }

        return result;
    }
}
