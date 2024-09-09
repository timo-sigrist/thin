import java.util.ArrayList;

public class State {

    private String name;
    private boolean accepted;
    private ArrayList<TransitionFunction> transitionFunctions;

    public State(String name, boolean accepted) {
        this.name = name;
        this.accepted = accepted;
        this.transitionFunctions = new ArrayList<>();
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getName() {
        return name;
    }

    public TransitionFunction getNextTransitionFunction(String readSymbol) {
        if (this.transitionFunctions.size() == 0) return null;
        for (TransitionFunction transitionFunction : this.transitionFunctions) {
            if (transitionFunction.getReadSymbol().equals(readSymbol)) {
                return transitionFunction;
            }
        }
        throw new IllegalArgumentException("Language not accepted!");
    }

    public void addTransitionFunction(TransitionFunction transitionFunction) {
        this.transitionFunctions.add(transitionFunction);
    }
}
