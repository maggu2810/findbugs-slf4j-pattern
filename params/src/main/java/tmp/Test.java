package tmp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    private static Logger logger() {
        final Logger logger = LoggerFactory.getLogger(Test.class);
        assert logger != null;
        return logger;
    }

    public static @Nullable String test(final File file) {
        final StringBuffer data = new StringBuffer(1000);
        try (final BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            final char[] buf = new char[1024];
            int numRead;
            while ((numRead = reader.read(buf)) != -1) {
                final String readData = String.valueOf(buf, 0, numRead);
                data.append(readData);
            }
        } catch (final FileNotFoundException ex) {
            logger().debug("File '{}' not found.", file, ex);
            return null;
        } catch (final IOException ex) {
            logger().warn("Error on reading file '{}'.", file, ex);
            return null;
        }
        return data.toString();
    }
}
