package org.example;

public class Board {
    private int dimension;
    private char[][] cells;

    public Board(int dimension) {
        this.dimension = dimension;
        cells = new char[dimension][dimension];
    }

    public int getDimension() {
        return dimension;
    }

    public char[][] getCells() {
        return cells;
    }

    public char getElement(int row, int column) {
        return cells[row][column];
    }

    public boolean cellAvailable(int row, int column) {
        return cells[row][column] == 0;
    }


    public boolean placePiece(int row, int column, char cell) {
        if (cellAvailable(row, column) == true) {
            cells[row][column] = cell;
            return true;
        }
        return false;
    }

    public char checkWinner() {
        // Check the horizontal gain
        for (int i = 0; i < dimension; i++) {
            if (cells[i][0] != '-' && allEqual(cells[i])) {
                return cells[i][0];
            }
        }

        // Check the vertical gain
        for (int j = 0; j < dimension; j++) {
            char firstElement = cells[0][j];
            boolean areEqual = true;
            for (int i = 1; i < dimension; i++) {
                if (cells[i][j] != firstElement) {
                    areEqual = false;
                    break;
                }
            }
            if (areEqual && firstElement != '-') {
                return firstElement;
            }
        }

        // check the gain on the main diagonal
        char firstElement = cells[0][0];
        boolean areEqual = true;
        for (int i = 1; i < dimension; i++) {
            if (cells[i][i] != firstElement) {
                areEqual = false;
                break;
            }
        }
        if (areEqual && firstElement != '-') {
            return firstElement;
        }

        // We check the gain on the secondary diagonal
        firstElement = cells[0][dimension - 1];
        areEqual = true;
        for (int i = 1; i < dimension; i++) {
            if (cells[i][dimension - i - 1] != firstElement) {
                areEqual = false;
                break;
            }
        }
        if (areEqual && firstElement != '-') {
            return firstElement;
        }

        // If there is no winner, we return null
        return '\0';
    }

    private boolean allEqual(char[] array) {
        char firstElement = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] != firstElement) {
                return false;
            }
        }
        return true;
    }

    public void showTable() {
        int dimensiune = cells.length;

        // Display the column numbers
        System.out.print("  ");
        for (int i = 0; i < dimension; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();

        // Display the separator
        System.out.print(" ");
        for (int i = 0; i < dimension; i++) {
            System.out.print("--");
        }
        System.out.println();

        // Display the game board
        for (int i = 0; i < dimension; i++) {
            // Display the line number
            System.out.print(i + 1 + "|");

            for (int j = 0; j < dimension; j++) {
                char element = getElement(i,j);
                if (element == '-') {
                    System.out.print(" |");
                } else {
                    System.out.print(element + "|");
                }
            }

            System.out.println();

            // Display the separator
            System.out.print(" ");
            for (int j = 0; j < dimension; j++) {
                System.out.print("--");
            }
            System.out.println();
        }
    }
}
