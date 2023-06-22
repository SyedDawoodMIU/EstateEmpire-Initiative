package property.application.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import property.application.controller.constants.ApplicationConstants;
import property.application.controller.constants.BaseErrorCode;
import property.application.exception.BadRequestException;
import property.application.exception.FileStorageException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Base64FileUpload {

    String userHome = System.getProperty("user.home");

    public String uploadBase64(String baseEncoded, Long id, String fileName) {
        baseEncoded = removeDataURIPrefix(baseEncoded);
        byte[] fileBytes = Base64.getDecoder().decode(baseEncoded);

        if (isImageFile(fileBytes)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));

            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            String uploadFileName = "image_" + timestamp + "." + extension;

            try {
                uploadImageFile(fileBytes, id,uploadFileName);
            }catch (IOException ex){
                throw new BadRequestException(BaseErrorCode.VALIDATION_FAILED,"Inavlid File Uploaded");
            }
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/").path(id.toString()).path("/").path(uploadFileName).toUriString();

            return fileDownloadUri;
        }
        throw new BadRequestException(BaseErrorCode.VALIDATION_FAILED,"Inavlid File Uploaded");
    }

    private String removeDataURIPrefix(String baseEncoded) {
        String dataURIPattern = "^data:[^;]+;base64,";

        Pattern pattern = Pattern.compile(dataURIPattern);
        Matcher matcher = pattern.matcher(baseEncoded);

        // Remove data URI prefix if found
        return matcher.replaceFirst("");
    }

    Path getUploadPath(Long id) {
        String path = userHome + ApplicationConstants.FILE_UPLOAD_DIR + File.separator + id;
        var storage = Paths.get(path).toAbsolutePath().normalize();
        try {
            Files.createDirectories(storage);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.");
        }
        return storage;
    }

    private boolean isImageFile(byte[] fileBytes) {
        return fileBytes.length > 3 &&
                fileBytes[0] == (byte) 0xFF &&
                fileBytes[1] == (byte) 0xD8 &&
                fileBytes[fileBytes.length - 2] == (byte) 0xFF &&
                fileBytes[fileBytes.length - 1] == (byte) 0xD9;
    }

    private void uploadImageFile(byte[] fileBytes, Long imageId, String fileName) throws IOException {

        Path directoryPath =getUploadPath(imageId);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Path filePath = directoryPath.resolve(fileName);

        Files.copy(new ByteArrayInputStream(fileBytes), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

}
