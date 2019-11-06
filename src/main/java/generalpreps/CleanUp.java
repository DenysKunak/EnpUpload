package generalpreps;

import java.nio.file.*;

public class CleanUp {

    public void cleanUp(String trash, String trash1) {
        try {
            Files.deleteIfExists(Paths.get(trash));
            Files.deleteIfExists(Paths.get(trash1));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}
