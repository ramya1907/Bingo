import java.util.Arrays;

public class BingoCard {
    /*
      The two arrays are private and their structure is NEVER exposed to another
      class, which is why the getCardNumbers returns a String that needs
      further processing.

      While this is not computationally efficient, it is good programming
      practice to hide data structures (information hiding).
     */
    private int[][] numbers;
    private boolean[][] markedOff;

    private int numberOfRows;
    private int numberOfColumns;

    public BingoCard(int numberOfRows, int numberOfColumns) {
        setNumberOfRows(numberOfRows);
        setNumberOfColumns(numberOfColumns);

        numbers = new int[numberOfRows][numberOfColumns];
        markedOff = new boolean[numberOfRows][numberOfColumns];
        resetMarked();
    }

    public void resetMarked() {
    /*
          Reset the data structure to be entirely false. */

        for (int x = 0; x < getNumberOfRows(); x++) {
            for (int y = 0; y < getNumberOfColumns(); y++) {
                markedOff[x][y] = false;
            }
        }
    }


    public int getNumberOfRows() {

        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {

        return numberOfColumns;
    }


    public void setNumberOfColumns(int numberOfColumns) {

        this.numberOfColumns = numberOfColumns;
    }

    public String getCardNumbers() {

        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < getNumberOfRows(); x++) {
            for (int y = 0; y < getNumberOfColumns(); y++) {
                sb.append(numbers[x][y]);
                sb.append(Defaults.getNumberSeparator());
            }
        }

    /*
          all the cards are stored as a grid ([][] numbers) of rows / columns, so for example, numbers 3 4 5 6 will be
          printed as follows:
          3  4
          5  6
     */


        return sb.toString().trim();
    }

    public void setCardNumbers(String[] numbersAsStrings) {

        int[] numbersList =
                Arrays.stream(numbersAsStrings).mapToInt(Integer::parseInt).toArray();

    /*
          the goal of this method is to get the numbers entered into the [][] numbers format
     */
        int indexCount = 0;
        for (int x = 0; x < getNumberOfRows(); x++) {
            for (int y = 0; y < getNumberOfColumns(); y++) {
                numbers[x][y] = numbersList[indexCount];
                indexCount++;
            }
        }
    }

    public void markNumber(int number) {

        boolean numberMatch = false;

        for (int x = 0; x < getNumberOfRows(); x++) {
            for (int y = 0; y < getNumberOfColumns(); y++) {
                if (numbers[x][y] == number) {
                    markedOff[x][y] = true;
                    System.out.printf("Marked off %d%n", number);
                    numberMatch = true;
                }
            }
        }
        if (!numberMatch)
            System.out.printf("Number %d not on this card%n", number);
    }

    public boolean isWinner() {
    /*
          check if there is a winner or not
          all elements of [][] markedOff should be marked off to have a winner (full house)
     */

        for (int x = 0; x < getNumberOfRows(); x++) {
            for (int y = 0; y < getNumberOfColumns(); y++) {
                if (!markedOff[x][y])
                    return false;
            }
        }
        return true;
    }
}
