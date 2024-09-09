import java.util.Scanner;

public class Kellerautomat {
    private double[] stack;
    int stackPointer;
    int state;
    double result = 0;
    double z;
    char operand;

    Kellerautomat() {
        System.out.print("Please define the required stack size: ");
        Scanner scanner = new Scanner(System.in);
        int stackSize = scanner.nextInt();
        reset(stackSize);

        String nextcalc = "yes";
        while (!"no".equals(nextcalc)) {
            reset(stackSize);

            String inputLine = readInput();
            processInput(inputLine);

            System.out.print("Next calculation (yes/no)? : ");
            scanner = new Scanner(System.in);
            nextcalc = scanner.next();
        }
    }

    private void reset(int stackSize) {
        stack = new double[stackSize];
        stackPointer = 0;
        z = 0;
        operand = 'e';
        state = 0;
    }

    private String readInput() {
        System.out.print("Please enter your Input (in reverse polish notation): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void processInput(String inputLine) {
        for(int i = 0; i < inputLine.length(); i++) {
            char currentChar = inputLine.charAt(i);
            if(charIsOperand(currentChar) || Character.isDigit(currentChar)) {
                nextStateLogic(inputLine.charAt(i));
            }
        }
        //final String
        nextStateLogic('e');

        if(state == 3) {
            System.out.println("accepting");
            System.out.println("Result: " + result);
        } else {
            System.out.println("Throwing");
        }
    }

    private void nextStateLogic(char input) {
        switch (state) {
            case 0:
                processStateZero(input);
                break;
            case 1:
                processStateOne(input);
                break;
            case 2:
                processStateTwo(input);
                break;
            default:
                state = 4;
        }
    }
    
    private void processStateZero(char input) {
        if(Character.isDigit(input)) {
            z = pop();
            push(z);
            push(Character.getNumericValue(input));
            state = 0;
        } else if(charIsOperand(input) && stackPointer > 1){
            operand = input;
            z = pop();
            state = 1;
            nextStateLogic('e');
        } else if (stackPointer == 1) {
            result = pop();
            state = 2;
            nextStateLogic('e');
        } else {
            state = 4;
        }
    }

    private void processStateOne(char input) {
        if (stackPointer >= 1) {
            double z_old = z;
            z = pop();
            push(calculcate(z, z_old, operand));
            state = 0;
        }
    }

    private void processStateTwo(char input) {
        if(stackPointer == 0) {
            state = 3;
        } else {
            state = 4;
        }
    }
    
    private double calculcate(double a, double b, char operand) {
        double result = 0;

        switch(operand) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
        }

        return result;
    }

    private boolean charIsOperand(char input) {
        if (input == '+' || input == '-' || input == '*' || input == '/') {
            return true;
        } else {
            return false;
        }
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
}
