package property.application.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import property.application.controller.constants.ApplicationConstants;
import property.application.controller.constants.BaseErrorCode;
import property.application.dto.request.FileUploadRequest;
import property.application.dto.response.UploadFileResponse;
import property.application.exception.BadRequestException;
import property.application.exception.FileStorageException;
import property.application.exception.MyFileNotFoundException;
import property.application.model.PropertyImage;
import property.application.repo.PropertyImageRepo;
import property.application.repo.PropertyRepository;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileUploadUtil {

    private final PropertyImageRepo propertyImageRepo;
    private final PropertyRepository propertyRepository;

    String userHome = System.getProperty("user.home");

    public List<UploadFileResponse> uploadMultipleFiles(FileUploadRequest files, Long id){
        return files.getFiles()
                .stream()
                .map(file -> uploadFile(file,id))
                .collect(Collectors.toList());
    }

    public UploadFileResponse uploadFile(MultipartFile file, Long id) {
        if (!isImage(file)) {
            throw new FileStorageException("Invalid file type. Only image files are allowed.");
        }
        var property = propertyRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(BaseErrorCode.VALIDATION_FAILED, "Property not found"));
        String fileName = copyFile(file, id);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/").path(id.toString()).path("/").path(fileName).toUriString();

        var image = PropertyImage.builder()
                .property(property)
                .downloadURL(fileDownloadUri)
                .build();
        propertyImageRepo.save(image);

        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    String copyFile(MultipartFile file, Long id) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (originalFileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + originalFileName);
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String newFileName = "image-" + timestamp + "." + extension;

            Path targetLocation = getUploadPath(id).resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return newFileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + originalFileName + ". Please try again!");
        }
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

    private boolean isImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        for (String imageExtension : ApplicationConstants.IMAGE_EXTENSIONS) {
            if (extension.equalsIgnoreCase(imageExtension)) {
                return true;
            }
        }

        return false;
    }

    public Resource downloadFile(String fileName, HttpServletRequest request, Long id) {
        Resource resource = loadFileAsResource(fileName,id);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return resource;
    }

    public Resource loadFileAsResource(String fileName, Long id) {
        try {
            Path filePath = getUploadPath(id).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName);
        }
    }

}
