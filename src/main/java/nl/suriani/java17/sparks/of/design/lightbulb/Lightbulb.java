package nl.suriani.java17.sparks.of.design.lightbulb;

public sealed interface Lightbulb {
    record NoLightbulb() implements Lightbulb {
        public Functioning.Disconnected acquired() {
            return new Functioning.Disconnected();
        }

        public NonFunctioning acquiredAndNonFunctioningDetected() {
            return new NonFunctioning();
        }
    }

    record NonFunctioning() implements Lightbulb {}

    sealed interface Functioning extends Lightbulb {

        default NonFunctioning broken() {
            return new NonFunctioning();
        }

        record On() implements Functioning {
            public Off turnedOff() {
                return new Off();
            }

            public OnButFlickering flickeringInitiated() {
                return new OnButFlickering();
            }
        }

        record OnButFlickering() implements Functioning {
            public Off turnedOff() {
                return new Off();
            }

            public On flickeringTerminated() {
                return new On();
            }
        }

        record Off() implements Functioning {
            public On turnedOn() {
                return new On();
            }
        }

        record Disconnected() implements Functioning {
            public On pluggedInAndStreamDetected() {
                return new On();
            }

            public Off pluggedInAndNoStreamDetected() {
                return new Off();
            }
        }

    }
}
