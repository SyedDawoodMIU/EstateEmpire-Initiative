package property.application.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import property.application.dto.AddressDto;
import property.application.dto.PropertyImageDto;
import property.application.model.PropertyDetails;
import property.application.model.enums.PropertyType;

import java.util.List;

@Data
public class PropertyDtoRequest {

    private Long propertyId;
    private PropertyType type;
    private int viewCount;
    private AddressDto address;
    private PropertyDetails propertyDetails;
    private List<FileDto> files;

}
