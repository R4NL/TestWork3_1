package testFenin.servise.fileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GetURL {

    public GetURL() {
    }

    public static GetURL get() {
        return new GetURL();
    }

    public List<String> getURL() {
        try {
            return Files.readAllLines(Paths.get("URL.txt"));
        } catch (NoSuchFileException e) {
            try {
                Files.createFile(Paths.get("URL.txt"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
