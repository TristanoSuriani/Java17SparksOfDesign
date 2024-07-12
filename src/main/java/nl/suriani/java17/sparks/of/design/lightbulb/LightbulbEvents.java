package nl.suriani.java17.sparks.of.design.lightbulb;

public interface LightbulbEvents {
    record Acquired() implements LightbulbEvents {}

    record AcquiredInBrokenState() implements LightbulbEvents {}

    record TurnedOn() implements LightbulbEvents {}

    record TurnedOff() implements LightbulbEvents {}

    record FlickeringStarted() implements LightbulbEvents {}

    record FlickeringStopped() implements LightbulbEvents {}
}
