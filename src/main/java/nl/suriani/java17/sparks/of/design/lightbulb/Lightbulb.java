package nl.suriani.java17.sparks.of.design.lightbulb;

public sealed interface Lightbulb {
    int DEFAULT_FLICKERING_RATE = 30;
    int DEFAULT_INTENSITY = 100;

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

        record On(int intensity) implements Functioning {
            public Off turnedOff() {
                return new Off();
            }

            public OnButFlickering flickeringInitiated() {
                return new OnButFlickering(Lightbulb.DEFAULT_INTENSITY,
                        Lightbulb.DEFAULT_FLICKERING_RATE);
            }
        }

        record OnButFlickering(int intensity, int flickeringRate) implements Functioning {
            public Off turnedOff() {
                return new Off();
            }

            public On flickeringTerminated() {
                return new On(Lightbulb.DEFAULT_INTENSITY);
            }
        }

        record Off() implements Functioning {
            public On turnedOn() {
                return new On(Lightbulb.DEFAULT_INTENSITY);
            }
        }

        record Disconnected() implements Functioning {
            public On pluggedInAndStreamDetected() {
                return new On(Lightbulb.DEFAULT_INTENSITY);
            }

            public Off pluggedInAndNoStreamDetected() {
                return new Off();
            }
        }
    }
}
