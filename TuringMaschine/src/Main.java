public class Main {

    private static TuringMaschine turingMaschine;

    public static void main(String[] args) {
        int factor_1 = Integer.parseInt(args[0]);
        int factor_2 = Integer.parseInt(args[1]);
        boolean stepMode = Integer.parseInt(args[2]) == 1;

        setUpTuring(factor_1, factor_2, stepMode);
        turingMaschine.run();
    }

    private static void setUpTuring(int factor_1, int factor_2, boolean stepMode) {

        //Set up Steps
        State q0 = new State("Q0", false);
        State q1 = new State("Q1", false);
        State q2 = new State("Q2", false);
        State q3 = new State("Q3", false);
        State q4 = new State("Q4", false);
        State q5 = new State("Q5", false);
        State q6 = new State("Q6", false);
        State q7 = new State("Q7", false);
        State q8 = new State("Q8", false);
        State q9 = new State("Q9", true);

        //Set up TransitionFunction
        q0.addTransitionFunction(new TransitionFunction(q1, ReadWriteHead.TAPE_SYMBOL, ReadWriteHead.EMPTY_SYMBOL, TransitionFunction.Movement.RIGHT));
        q0.addTransitionFunction(new TransitionFunction(q7, ReadWriteHead.TAPE_SEP_SYMBOL, ReadWriteHead.EMPTY_SYMBOL, TransitionFunction.Movement.RIGHT));
        q1.addTransitionFunction(new TransitionFunction(q1, ReadWriteHead.TAPE_SYMBOL, ReadWriteHead.TAPE_SYMBOL, TransitionFunction.Movement.RIGHT));
        q1.addTransitionFunction(new TransitionFunction(q2, ReadWriteHead.TAPE_SEP_SYMBOL, ReadWriteHead.TAPE_SEP_SYMBOL, TransitionFunction.Movement.RIGHT));
        q2.addTransitionFunction(new TransitionFunction(q3, ReadWriteHead.TAPE_SYMBOL, ReadWriteHead.TAPE_MARKER, TransitionFunction.Movement.RIGHT));
        q2.addTransitionFunction(new TransitionFunction(q6, ReadWriteHead.TAPE_SEP_SYMBOL, ReadWriteHead.TAPE_SEP_SYMBOL, TransitionFunction.Movement.LEFT));
        q2.addTransitionFunction(new TransitionFunction(q6, ReadWriteHead.EMPTY_SYMBOL, ReadWriteHead.EMPTY_SYMBOL, TransitionFunction.Movement.LEFT));
        q3.addTransitionFunction(new TransitionFunction(q3, ReadWriteHead.TAPE_SYMBOL, ReadWriteHead.TAPE_SYMBOL, TransitionFunction.Movement.RIGHT));
        q3.addTransitionFunction(new TransitionFunction(q4, ReadWriteHead.TAPE_SEP_SYMBOL, ReadWriteHead.TAPE_SEP_SYMBOL, TransitionFunction.Movement.RIGHT));
        q3.addTransitionFunction(new TransitionFunction(q4, ReadWriteHead.EMPTY_SYMBOL, ReadWriteHead.TAPE_SEP_SYMBOL, TransitionFunction.Movement.RIGHT));
        q4.addTransitionFunction(new TransitionFunction(q4, ReadWriteHead.TAPE_SYMBOL, ReadWriteHead.TAPE_SYMBOL, TransitionFunction.Movement.RIGHT));
        q4.addTransitionFunction(new TransitionFunction(q5, ReadWriteHead.EMPTY_SYMBOL, ReadWriteHead.TAPE_SYMBOL, TransitionFunction.Movement.LEFT));
        q5.addTransitionFunction(new TransitionFunction(q5, ReadWriteHead.TAPE_SYMBOL, ReadWriteHead.TAPE_SYMBOL, TransitionFunction.Movement.LEFT));
        q5.addTransitionFunction(new TransitionFunction(q5, ReadWriteHead.TAPE_SEP_SYMBOL, ReadWriteHead.TAPE_SEP_SYMBOL, TransitionFunction.Movement.LEFT));
        q5.addTransitionFunction(new TransitionFunction(q2, ReadWriteHead.TAPE_MARKER, ReadWriteHead.TAPE_SYMBOL, TransitionFunction.Movement.RIGHT));
        q6.addTransitionFunction(new TransitionFunction(q6, ReadWriteHead.TAPE_SEP_SYMBOL, ReadWriteHead.TAPE_SEP_SYMBOL, TransitionFunction.Movement.LEFT));
        q6.addTransitionFunction(new TransitionFunction(q6, ReadWriteHead.TAPE_SYMBOL, ReadWriteHead.TAPE_SYMBOL, TransitionFunction.Movement.LEFT));
        q6.addTransitionFunction(new TransitionFunction(q0, ReadWriteHead.EMPTY_SYMBOL, ReadWriteHead.EMPTY_SYMBOL, TransitionFunction.Movement.RIGHT));
        q7.addTransitionFunction(new TransitionFunction(q7, ReadWriteHead.TAPE_SYMBOL, ReadWriteHead.EMPTY_SYMBOL, TransitionFunction.Movement.RIGHT));
        q7.addTransitionFunction(new TransitionFunction(q8, ReadWriteHead.TAPE_SEP_SYMBOL, ReadWriteHead.EMPTY_SYMBOL, TransitionFunction.Movement.RIGHT));
        q7.addTransitionFunction(new TransitionFunction(q8, ReadWriteHead.EMPTY_SYMBOL, ReadWriteHead.EMPTY_SYMBOL, TransitionFunction.Movement.RIGHT));
        q8.addTransitionFunction(new TransitionFunction(q8, ReadWriteHead.TAPE_SYMBOL, ReadWriteHead.TAPE_SYMBOL, TransitionFunction.Movement.RIGHT));
        q8.addTransitionFunction(new TransitionFunction(q9, ReadWriteHead.EMPTY_SYMBOL, ReadWriteHead.EMPTY_SYMBOL, TransitionFunction.Movement.STAY));

        //Set up Turing Maschine
        turingMaschine = new TuringMaschine(q0, stepMode, factor_1, factor_2);

    }
}
