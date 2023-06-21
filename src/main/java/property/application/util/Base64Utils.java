package property.application.util;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

public class Base64Utils {

    public static MultipartFile convertBase64ToMultipartFile(String base64String) throws IOException {
        byte[] bytes = Base64.getMimeDecoder().decode(base64String);

        // Create a temporary file
        Path tempFile = Files.createTempFile("temp", null);
        Files.write(tempFile, bytes, StandardOpenOption.CREATE);

        // Create a multipart file from the temporary file
        MultipartFile multipartFile = new MockMultipartFile("file", tempFile.getFileName().toString(), null, Files.newInputStream(tempFile));

        return multipartFile;
    }
}

