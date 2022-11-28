package com.mycompany;

public class StateMachine {
    private States state;

    public StateMachine() {
        States.currentState = States.INIT;
        this.state = States.currentState;
    }

    public void run() {
        if(state != null) {
            state.entry();
            state.exec();
            state.exit();
        }
    }
}
