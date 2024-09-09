import java.io.IOException;

public class TuringMaschine {
    private State startState;
    private ReadWriteHead readWriteHead;

    private boolean stepModus;

    public TuringMaschine(State startState, boolean stepModus, int factor_1, int factor_2) {
        this.startState = startState;
        this.stepModus = stepModus;
        this.readWriteHead = new ReadWriteHead(factor_1, factor_2);
    }

    public void run() {
        State currentState = this.startState;
        TransitionFunction currentTransition = currentState.getNextTransitionFunction(this.readWriteHead.getCurrentSymbol());
        int stepCounter = 1;
        while (!currentState.isAccepted()) {
            this.readWriteHead.writeToTape(currentTransition.getWriteSymbol(), currentTransition.getMovement());
            String newReadSymbol = this.readWriteHead.getCurrentSymbol();
            currentState = currentTransition.getCurrentState();
            currentTransition = currentState.getNextTransitionFunction(newReadSymbol);
            if (stepModus) {
                this.printTuringMaschine(currentState, stepCounter);
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            stepCounter++;
        }

        this.printFinalTuringMaschine(currentState, stepCounter);
    }

    private void printTuringMaschine(State currentState, int countSteps) {

        System.out.println("----------------------------------------------------");
        System.out.println("Aktueller Zustand: " + currentState.getName() + " Akzeptiert: " + currentState.isAccepted());
        System.out.println("Aktuelle Lese- und Schriebekopf Position: " + this.readWriteHead.getHeadIndex());
        System.out.println("Anzahl durchläufe: " + countSteps);
        this.readWriteHead.printTape();
        System.out.println("----------------------------------------------------");

    }

    private void printFinalTuringMaschine(State currentState, int countSteps) {
        if (!this.stepModus) {
            this.printTuringMaschine(currentState, countSteps);
        }
        System.out.println("Ergebnis: " + this.readWriteHead.getResult());
    }
}
