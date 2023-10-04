import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class BingoController {

    private final String[] mainMenuItems = {"Exit",
            "Play bingo",
            "Set number separator",
            "Create a bingo card",
            "List existing cards",
            "Set bingo card size"};

    private final String OPTION_EXIT = "0";
    private final String OPTION_PLAY = "1";
    private final String OPTION_SEPARATOR = "2";
    private final String OPTION_CREATE_CARD = "3";
    private final String OPTION_LIST_CARDS = "4";
    private final String OPTION_SIZE = "5";


    private int currentRowSize = Defaults.DEFAULT_NUMBER_OF_ROWS;
    private int currentColumnSize = Defaults.DEFAULT_NUMBER_OF_COLUMNS;

    /*
          create an ArrayList of BingoCard cards
     */

    ArrayList<BingoCard> cardsArrayList = new ArrayList<>();

    public int getCurrentRowSize() {

        return currentRowSize;
    }

    public void setCurrentRowSize(int currentRowSize) {

        this.currentRowSize = currentRowSize;
    }

    public int getCurrentColumnSize() {

        return currentColumnSize;
    }

    public void setCurrentColumnSize(int currentColumnSize) {

        this.currentColumnSize = currentColumnSize;
    }

    /*
          add a new BingoCard card
     */
    public void addNewCard(BingoCard card) {

        cardsArrayList.add(card);
    }


    public void setSize() {
        setCurrentRowSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of rows for the card")));
        setCurrentColumnSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of columns for the card")));
        System.out.printf("The bingo card size is set to %d rows X %d columns%n",
                getCurrentRowSize(),
                getCurrentColumnSize());
    }

    /*
           ensure that the correct amount of numbers are entered
           print a message that shows the numbers entered using Toolkit.printArray(numbers)
           create, setCardNumbers and add the card as a BingoCard
     */
    public void createCard() {
        /*
              calculate how many numbers are required to be entered based on the number or rows / columns
         */
        int numbersRequired = getCurrentColumnSize() * getCurrentRowSize();

        String[] numbers;

        boolean correctAmountOfNumbersEntered;

        do {
            numbers = Toolkit.getInputForMessage(
                    String.format(
                            "Enter %d numbers for your card (separated by " +
                                    "'%s')",
                            numbersRequired, Defaults.getNumberSeparator())).trim().split(Defaults.getNumberSeparator());
        /*
              verify if the correctAmountOfNumbersEntered is true or false dependant on the numbersRequired calculation
         */
            if (numbers.length == numbersRequired)
                correctAmountOfNumbersEntered = true;
            else {
                correctAmountOfNumbersEntered = false;
                System.out.printf("Try again: you entered %d numbers instead of %d%n", numbers.length, numbersRequired);
            }

        }
        while (!correctAmountOfNumbersEntered);


        System.out.printf("You entered%n%s%n", Toolkit.printArray(numbers));

        /*
              create new BingoCard
              setCardNumbers for the new card
              add the card to the ArrayList
         */

        BingoCard newBingoCard = new BingoCard(getCurrentRowSize(), getCurrentColumnSize());
        newBingoCard.setCardNumbers(numbers);
        addNewCard(newBingoCard);
    }

    public void listCards() {


        for (int a = 0; a < cardsArrayList.size(); a++) {
            System.out.printf("Card %2d numbers:%n", a);
            printCardAsGrid(cardsArrayList.get(a).getCardNumbers());
        }

    }

    public void printCardAsGrid(String numbers) {
        String[] numbersArray = numbers.split(Defaults.getNumberSeparator());
        int newLineCounter = 0;

        for (int i = 0; i < (getCurrentColumnSize() * getCurrentRowSize()); i++) {

            if (newLineCounter < (getCurrentColumnSize() - 1))
                System.out.printf("%2s%s", numbersArray[i], Defaults.getNumberSeparator());

            else
                System.out.printf("%2s", numbersArray[i]);

            newLineCounter++;

            if (newLineCounter == getCurrentColumnSize()) {
                System.out.print("\n");
                newLineCounter = 0;
            }
        }
    }


    public void setSeparator() {
        String sep = Toolkit.getInputForMessage("Enter the new separator");
        Defaults.setNumberSeparator(sep);
        System.out.printf("Separator is '%s'%n", Defaults.getNumberSeparator());
    }

    /*
         reset all BingoCards using resetMarked (to false)
     */
    public void resetAllCards() {

        for (int a = 0; a < cardsArrayList.size(); a++) {
            cardsArrayList.get(a).resetMarked();
        }
    }

    /*
          mark off a number that was called when it equals one of the numbers on the BingoCard
     */
    public void markNumbers(int number) {

        for (int i = 0; i < cardsArrayList.size(); i++) {
            System.out.printf("Checking card %d for %d%n", i, number);
            cardsArrayList.get(i).markNumber(number);
        }
    }

    /*
          isWinner() to determine who the winner is
       Ï
     */
    public int getWinnerId() {

        int winnerID = 0;

        for (int i = 0; i < cardsArrayList.size(); i++) {
            if (cardsArrayList.get(i).isWinner()) {
                winnerID = i;
                break;
            } else
                winnerID = Defaults.NO_WINNER;
        }
        return winnerID;
    }

    /*
        the game will not end until there is a winning sequence
     */
    public void play() {
        System.out.println("Eyes down, look in!");
        resetAllCards();

        boolean weHaveAWinner;
        do {
            markNumbers(parseInt(
                    Toolkit.getInputForMessage("Enter the next number")
                            .trim()));

            int winnerID = getWinnerId();
            weHaveAWinner = winnerID != Defaults.NO_WINNER;
            if (weHaveAWinner)
                System.out.printf("And the winner is card %d%n", winnerID);
        }
        while (!weHaveAWinner);
    }

    public String getMenu(String[] menuItems) {

        StringBuilder menuText = new StringBuilder();
        for (int i = 0; i < menuItems.length; i++) {
            menuText.append(String.format(" %d: %s%n", i, menuItems[i]));
        }
        return menuText.toString();
    }

    /*
         menu using switch to call the appropriate method calls
     */
    public void run() {
        boolean finished = false;
        do {
            switch (Toolkit.getInputForMessage(getMenu(mainMenuItems))) {
                case (OPTION_EXIT): {
                    System.out.printf("%s%n", Toolkit.GOODBYEMESSAGE);
                    finished = true;
                    break;
                }

                case (OPTION_PLAY): {
                    play();
                    break;
                }

                case (OPTION_SEPARATOR): {
                    setSeparator();
                    break;
                }

                case (OPTION_CREATE_CARD): {
                    createCard();
                    break;
                }
                case (OPTION_LIST_CARDS): {
                    listCards();
                    break;
                }
                case (OPTION_SIZE): {
                    setSize();
                    break;
                }
            }
        } while (!finished);
    }
}
