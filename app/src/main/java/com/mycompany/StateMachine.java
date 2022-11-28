package com.mycompany;

public class StateMachine {

    public StateMachine() {
        States.currentState = States.INIT;
        if(States.currentState != null) {
            States.currentState.entry();
        }
    }

    public void run() {
        if(States.currentState != null) {
            States.currentState.exec();
        }
    }
}
