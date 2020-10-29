import java.util.*;

enum Players {
    PLAYER,
    CPU
}

public class TickTacToe {
    static char[][] gameBoard = {
            {' ', '|', ' ', '|', ' '},
            {'–', '+', '–', '+', '–'},
            {' ', '|', ' ', '|', ' '},
            {'–', '+', '–', '+', '–'},
            {' ', '|', ' ', '|', ' '},
    };
    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();
    static Boolean isGameOver = false;

    public static void main(String[] args) {
        printGameBoard();

        Scanner scan = new Scanner(System.in);

        while (isGameOver == false) {

            System.out.print("Enter your placement (1-9):");

            int playerPos = scan.nextInt();

            while (isPositionTaken(playerPos)) {
                System.out.print("Position taken! Enter a correct position:");
                playerPos = scan.nextInt();
            }

            placePiece(playerPos, Players.PLAYER);

            checkForWinner();

            if (isGameOver == true) {
                return;
            }

            int cpuPos = new Random().nextInt(9) + 1;
            while (isPositionTaken(cpuPos)) {
                cpuPos = new Random().nextInt(9) + 1;
            }

            placePiece(cpuPos, Players.CPU);

            checkForWinner();

            if (isGameOver == true) {
                return;
            }

            printGameBoard();
        }
    }

    public static void printGameBoard() {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static Boolean isPositionTaken(int position) {
        return playerPositions.contains(position) || cpuPositions.contains(position);
    }

    public static void placePiece(int position, Players user) {
        char symbol = ' ';

        if (user.equals(Players.PLAYER)) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals(Players.CPU)) {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static void checkForWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List middleCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(middleRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftCol);
        winningConditions.add(middleCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        String result = "";

        for (List l : winningConditions) {
            if (playerPositions.containsAll(l)) {
                result = "Congratulations you won!";
            } else if (cpuPositions.containsAll(l)) {
                result = "CPU wins! Sorry :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                result = "CAT!";
            }
        }

        if (result.length() > 0) {
            printGameBoard();
            System.out.println(result);
            isGameOver = true;
        }
    }
}
