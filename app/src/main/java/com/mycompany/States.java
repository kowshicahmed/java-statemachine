package com.mycompany;

import java.util.Arrays;
import java.util.Scanner;

public enum States implements Actions {
    INIT {
        @Override
        public void entry() {
            System.out.println("Entering init");
            currentState = States.INIT;
        }

        @Override
        public void exec() {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();
            for (Signals signal : Signals.values()) {
                if(command.contains(signal.toString())) {
                    signal.transition();
                    System.out.println("transition to " + signal.name());
                }
            }
        }

        @Override
        public void exit() {

        }
    },
    INIT_ERROR {
        @Override
        public void entry() {

        }

        @Override
        public void exec() {

        }

        @Override
        public void exit() {

        }
    },
    IDLE {
        @Override
        public void entry() {
            System.out.println("Entering Idle");
        }

        @Override
        public void exec() {

        }

        @Override
        public void exit() {

        }
    },
    RUNNING {
        @Override
        public void entry() {

        }

        @Override
        public void exec() {

        }

        @Override
        public void exit() {

        }
    },
    ERROR {
        @Override
        public void entry() {

        }

        @Override
        public void exec() {

        }

        @Override
        public void exit() {

        }
    },
    FROZEN {
        @Override
        public void entry() {

        }

        @Override
        public void exec() {

        }

        @Override
        public void exit() {

        }
    },
    FORCE_RESTART {
        @Override
        public void entry() {

        }

        @Override
        public void exec() {

        }

        @Override
        public void exit() {

        }
    },
    SOFT_RESTART {
        @Override
        public void entry() {

        }

        @Override
        public void exec() {

        }

        @Override
        public void exit() {

        }
    },
    WAIT {
        @Override
        public void entry() {

        }

        @Override
        public void exec() {

        }

        @Override
        public void exit() {

        }
    },
    CLOSE_RESTART {
        @Override
        public void entry() {

        }

        @Override
        public void exec() {

        }

        @Override
        public void exit() {

        }
    };

    static {
        INIT.allowTransitionTo(INIT_ERROR, IDLE, RUNNING, ERROR);
        INIT_ERROR.allowTransitionTo(FORCE_RESTART);
        IDLE.allowTransitionTo(RUNNING, ERROR, WAIT);
        RUNNING.allowTransitionTo(IDLE, ERROR, WAIT, FROZEN);
        ERROR.allowTransitionTo(SOFT_RESTART);
        FROZEN.allowTransitionTo(FORCE_RESTART);
        WAIT.allowTransitionTo(CLOSE_RESTART, ERROR, FROZEN);
    }

    private States[] possibleTransitions;

    private void allowTransitionTo(States... allowableStates) {
        possibleTransitions = allowableStates;
    }

    public boolean canTransitionTo(States anotherState) {
        return Arrays.stream(possibleTransitions).anyMatch(anotherState::equals);
    }

    public States transitionTo(States newState) {
        if (!canTransitionTo(newState))
            throw new IllegalStateException(String.format("Illegal transition: %s -> %s", this, newState));
        return newState;
    }

    public static States currentState;
}
