import java.util.Scanner;

public class RPNPushdownAutomataOld {
    public static final String EMPTY_WORD = "e";
    
    private double[] stack;
    int stackPointer;
    State state;
    double result = 0;
    double stackNumber;
    String operand;

    enum State {
        Q0, Q1, Q2, Q3, QTRASH;
    }

    RPNPushdownAutomataOld() {
        String nextcalc = "yes";
        while (!"no".equals(nextcalc)) {
            String[] inputArray = readInput().split(" ");

            reset(inputArray.length);
            processInput(inputArray);

            System.out.print("Next calculation (yes/no)? : ");
            nextcalc = new Scanner(System.in).next();
        }
    }

    private void reset(int stackSize) {
        stack = new double[stackSize];
        stackPointer = 0;
        stackNumber = 0;
        operand = EMPTY_WORD;
        state = State.Q0;
    }

    private String readInput() {
        System.out.print("Please enter your Input (in reverse polish notation): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void processInput(String[] input) {
        for(String word : input) {
            if(isNumber(word) || isOperator(word)) {
                nextStateLogic(word);
            }
        }
        //final String
        nextStateLogic(EMPTY_WORD);

        if(state == State.Q3) {
            System.out.println("accepting");
            System.out.println("Result: " + result);
        } else {
            System.out.println("Throwing");
        }
    }

    private void nextStateLogic(String input) {
        switch (state) {
            case Q0:
                processStateZero(input);
                break;
            case Q1:
                processStateOne(input);
                break;
            case Q2:
                processStateTwo(input);
                break;
            default:
                state = State.QTRASH;
        }
    }

    private void processStateZero(String input) {
        if(isNumber(input)) {
            stackNumber = pop();
            push(stackNumber);
            push(Integer.parseInt(input));
            state = State.Q0;
        } else if(isOperator(input) && stackPointer > 1){
            operand = input;
            stackNumber = pop();
            state = State.Q1;
            nextStateLogic(EMPTY_WORD);
        } else if (stackPointer == 1) {
            result = pop();
            state = State.Q2;
            nextStateLogic(EMPTY_WORD);
        } else {
            state = State.QTRASH;
        }
    }

    private void processStateOne(String input) {
        if (stackPointer >= 1) {
            double zOld = stackNumber;
            stackNumber = pop();
            push(calculcate(stackNumber, zOld, operand));
            state = State.Q0;
        }
    }

    private void processStateTwo(String input) {
        if(stackPointer == 0) {
            state = State.Q3;
        } else {
            state = State.QTRASH;
        }
    }

    private double calculcate(double a, double b, String operand) {
        double result = 0;

        switch(operand) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }

        return result;
    }

    private void push(double value) {
        stackPointer++;
        stack[stackPointer] = value;
    }

    private double pop() {
        double tmp = stack[stackPointer];
        stack[stackPointer] = 0.0;
        stackPointer--;

        return tmp;
    }

    private boolean isNumber(String word) {
        return word.matches("[0-9]+");
    }

    private boolean isOperator(String word) {
        return word.matches("\\+|-|\\*|\\/");
    }
}
