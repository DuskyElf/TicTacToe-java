public class Game {
    public Board board;
    public Board.Cell currentPlayer;
    boolean running;
    int gameLap;
    Board.Cell winner;

    public Game() {
        board = new Board();
        currentPlayer = Board.Cell.N;
        running = true;
        gameLap = 0;
        winner = Board.Cell.N;
    }

    // Utility
    public static char s(Board.Cell player) {
        if (player == Board.Cell.X) return 'X';
        if (player == Board.Cell.O) return 'O';
        return 'n';
    }

    // Main Game loop
    public void gameLoop() {
        currentPlayer = Board.Cell.X;

        while (running) {
            gameLap += 1;

            int[] response = askPlayer(currentPlayer);
            board.move(response, currentPlayer);
            incrementPlayer();
            System.out.println(board.display());

            winner = winCheck();
            drawCheckEnd();
            if (winner != Board.Cell.N)
                winAnnounce();
        }
    }

    // Player input
    int[] askPlayer(Board.Cell player) {
        boolean done = false;
        int[] move = new int[2];

        while (! done) {
            System.out.printf
            ("[%d] Player %c your turn, row number (1, 2, 3): ",
                      gameLap,    s(player));
            int row = Input.sc.nextInt() - 1;

            System.out.printf
            ("[%d] Player %c your turn, collum number (1, 2, 3): ",
                      gameLap,    s(player));
            int collum = Input.sc.nextInt() - 1;

            move[0] = row;
            move[1] = collum;
            done = validMove(move);
        }

        return move;
    }
    
    // Checking if the move is valid or not 
    boolean validMove(int[] move) {
        if (move[0] > 2 | move[1] > 2) return false;
        return board.boardState[move[0]][move[1]] == Board.Cell.N;
    }

    // Incrementing to the next Player
    void incrementPlayer() {
        if (currentPlayer == Board.Cell.X)
            currentPlayer = Board.Cell.O;
        else currentPlayer = Board.Cell.X;
    }

    // Checking if someone won
    Board.Cell winCheck() {
        Board.Cell[][] b = board.boardState;
        
        for (int i = 0; i < 3; i++) {
            // Horizontal Checks
            if (b[i][0] != Board.Cell.N)
            if (b[i][0] == b[i][1] & b[i][1] == b[i][2])
                return b[i][0];
            
            // Virtical Checks
            if (b[i][0] != Board.Cell.N)
            if (b[0][i] == b[1][i] & b[1][i] == b[2][i])
                return b[0][i];
        }
        
        // Diagonal Checks
        if (b[0][0] != Board.Cell.N)
        if (b[0][0] == b[1][1] & b[1][1] == b[2][2])
            return b[0][0];

        if (b[0][0] != Board.Cell.N)
        if (b[2][0] == b[1][1] & b[1][1] == b[0][2])
            return b[2][0];
        
        return Board.Cell.N;
    }

    // Checking if the game drawed, if so ending it
    void drawCheckEnd() {
        if (winner != Board.Cell.N) return;

        for (Board.Cell[] row: board.boardState)
            for (Board.Cell cell: row)
                if (cell == Board.Cell.N) return;
        
        System.out.println("It's a draw, Bruh!!");
        running = false;
    }

    // Announcment that someone won, and killing the gameloop
    void winAnnounce() {
        System.out.printf("Player %s won the game in %d Game laps.\n",
                                          s(winner),         gameLap
        );
        System.out.println("Whooo!");
        running = false;
    }
}
