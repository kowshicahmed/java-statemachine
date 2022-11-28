package com.mycompany;

public enum Signals {
    INIT_DONE ("Init was successful") {
        @Override
        public void transition() {
            if(States.currentState.canTransitionTo(States.IDLE))
                Signals.nextState = States.currentState.transitionTo(States.IDLE);
        }
    },
    INIT_ERROR ("Another process is running") {
        @Override
        public void transition() {
            if(States.currentState.canTransitionTo(States.FORCE_RESTART))
                Signals.nextState = States.currentState.transitionTo(States.FORCE_RESTART);
        }
    },
    IDLE ("idle") {
        @Override
        public void transition() {
            if(States.currentState.canTransitionTo(States.IDLE))
                Signals.nextState = States.currentState.transitionTo(States.IDLE);
        }
    },
    RUNNING ("Running") {
        @Override
        public void transition() {
            if(States.currentState.canTransitionTo(States.IDLE))
                nextState = States.IDLE;
        }
    },
    ERROR ("Error") {
        @Override
        public void transition() {
            if(States.currentState.canTransitionTo(States.IDLE))
                nextState = States.IDLE;
        }
    },
    DISCONNECT("Close requested") {
        @Override
        public void transition() {
            if(States.currentState.canTransitionTo(States.IDLE))
                nextState = States.IDLE;
        }
    },
    DISCONNECTED("Disconnected") {
        @Override
        public void transition() {
            if(States.currentState.canTransitionTo(States.IDLE))
                nextState = States.IDLE;
        }
    };

    private final String signal;

    private Signals(String s) {
        signal = s;
    }

    public boolean equalsSignal(String otherSignal) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return signal.equals(otherSignal);
    }

    public String toString() {
        return this.signal;
    }

    public abstract void transition();
    public static States nextState = States.currentState;
}
