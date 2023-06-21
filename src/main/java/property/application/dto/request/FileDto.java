package property.application.dto.request;

import lombok.Data;

@Data
public class FileDto {
    private String fileName;
    private String base64Content;
}
