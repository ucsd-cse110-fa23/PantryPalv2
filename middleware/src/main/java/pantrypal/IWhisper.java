package pantrypal;

import java.io.*;
import java.net.*;

public interface IWhisper {
    public String getTranscript(String filePath) throws IOException, URISyntaxException;
}
