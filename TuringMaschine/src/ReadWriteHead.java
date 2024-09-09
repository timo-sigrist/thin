import java.util.ArrayList;

public class ReadWriteHead {
    private ArrayList<String> tape;
    private int headIndex;

    public static final String EMPTY_SYMBOL = "_";
    public static final String TAPE_SYMBOL = "1";
    public static final String TAPE_SEP_SYMBOL = "0";
    public static final String TAPE_MARKER = "X";

    /**
     * Constructor
     */
    public ReadWriteHead(int factor_1, int factor_2) {
        this.tape = new ArrayList<>();
        this.writeInitialToTape(factor_1);
        tape.add(TAPE_SEP_SYMBOL);
        this.writeInitialToTape(factor_2);
    }

    /**
     * Write initial to tape
     *
     * @param factor
     */
    private void writeInitialToTape(int factor) {
        for (int i = 0; i < factor; i++) {
            tape.add(TAPE_SYMBOL);
        }
    }

    /**
     * Get Current Symbol on tape
     *
     * @return String
     */
    public String getCurrentSymbol() {
        return this.getSymbolAtIndex(this.headIndex);
    }

    public void writeToTape(String newSymbol, TransitionFunction.Movement movement) {
        switch (movement) {
            case LEFT:
                this.setTapeSymbol(newSymbol);
                this.headIndex--;
                break;
            case STAY:
                this.setTapeSymbol(newSymbol);
                break;
            case RIGHT:
                this.setTapeSymbol(newSymbol);
                this.headIndex++;
                break;
            default:
                throw new IllegalArgumentException("No movment provided");
        }
    }

    private void setTapeSymbol(String newSymbol) {
        try {
            this.tape.set(this.headIndex, newSymbol);
        } catch (IndexOutOfBoundsException e) {
            this.tape.add(this.headIndex, newSymbol);
        }
    }

    private String getSymbolAtIndex(int index) {
        try {
            return this.tape.get(index);
        } catch (IndexOutOfBoundsException e) {
            return EMPTY_SYMBOL;
        }
    }

    public int getHeadIndex() {
        return headIndex;
    }

    public int getResult() {
        int counter = 0;
        for (String symbol : this.tape) {
            if (symbol.equals(this.TAPE_SYMBOL)) counter++;
        }
        return counter;
    }

    /**
     * Print Tape
     */
    public void printTape() {
        System.out.print("|");
        for (int j = this.headIndex - 15; j < this.headIndex; j++) {
            System.out.print(getSymbolAtIndex(j));
            System.out.print("|");
        }

        // Head Index
        System.out.print("q");

        for (int i = this.headIndex; i < this.headIndex + 16; i++) {
            System.out.print(getSymbolAtIndex(i));
            System.out.print("|");
        }
        System.out.println("");
    }
}
