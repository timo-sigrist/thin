public class TransitionFunction {

    public enum Movement {
        LEFT,
        RIGHT,
        STAY
    }

    private State currentState;
    private String readSymbol;
    private String writeSymbol;
    private Movement movement;

    public TransitionFunction(State currentState, String readSymbol, String writeSymbol, Movement movement) {
        this.currentState = currentState;
        this.readSymbol = readSymbol;
        this.writeSymbol = writeSymbol;
        this.movement = movement;
    }

    public String getReadSymbol() {
        return readSymbol;
    }

    public String getWriteSymbol() {
        return writeSymbol;
    }

    public Movement getMovement() {
        return movement;
    }

    public State getCurrentState() {
        return currentState;
    }
}

