package nl.suriani.java21.sparks.of.design.snapshot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

public class Snapshot {

    private final String resourceName;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private Snapshot(String resourceName) {
        this.resourceName = resourceName;
    }

    public static Snapshot of(String resourceName) {
        return new Snapshot(resourceName);
    }

    public void compareAsJsonWith(Object o) throws URISyntaxException, IOException {
        var expected = readResource();
        var content = GSON.toJson(o);
        JSONAssert.assertEquals(expected, content, true);
    }

    public void compareAsJsonStringWith(Object o) throws URISyntaxException, IOException {
        var expected = readResource();
        var content = GSON.toJson(o);
        Assertions.assertEquals(expected, content);
    }

    private String readResource() throws IOException {
        var file = new File("src/test/resources/" + resourceName);
        return Files.readString(file.toPath());
    }
}
