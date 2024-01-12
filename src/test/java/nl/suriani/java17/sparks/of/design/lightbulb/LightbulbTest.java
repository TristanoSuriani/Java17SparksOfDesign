package nl.suriani.java17.sparks.of.design.lightbulb;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LightbulbTest {
    @Test
    void thisTestsAreUselessAsCorrectnessOfTransitionIsVerifiedAtCompileTime() {
        var nonFunctioning1 = new Lightbulb.NoLightbulb()
                .acquiredAndNonFunctioningDetected();

        var nonFunctioning2 = new Lightbulb.NoLightbulb()
                .acquired()
                .broken();

        var nonFunctioning3 = new Lightbulb.NoLightbulb()
                .acquired()
                .pluggedInAndNoStreamDetected()
                .broken();

        var nonFunctioning4 = new Lightbulb.NoLightbulb()
                .acquired()
                .pluggedInAndNoStreamDetected()
                .broken();

        var nonFunctioning5 = new Lightbulb.NoLightbulb()
                .acquired()
                .pluggedInAndNoStreamDetected()
                .turnedOn()
                .broken();

        var nonFunctioning6 = new Lightbulb.NoLightbulb()
                .acquired()
                .pluggedInAndNoStreamDetected()
                .turnedOn()
                .flickeringInitiated()
                .broken();

        var disconnected = new Lightbulb.NoLightbulb()
                .acquired();

        var cycles = new Lightbulb.NoLightbulb()
                .acquired()
                .pluggedInAndStreamDetected()
                .turnedOff()
                .turnedOn()
                .turnedOff()
                .turnedOn()
                .flickeringInitiated()
                .flickeringTerminated()
                .flickeringInitiated()
                .turnedOff();
    }
}