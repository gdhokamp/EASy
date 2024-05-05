package de.gdhokamp.easy.organism.phenotype;

public class StateTransitionRow {
    private StateAction action;

    private int endState;

    public void setEndState(int endState) {
        this.endState = endState;
    }

    public void setAction(StateAction action) {
        this.action = action;
    }

    public int getEndState() {
        return endState;
    }

    public StateAction getAction() {
        return action;
    }

}
