package property.application.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import property.application.dto.request.FileUploadRequest;
import property.application.dto.response.UploadFileResponse;
import property.application.util.FileUploadUtil;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
public class FileUploadController {

    @Autowired
    FileUploadUtil fileUploadUtil;

    @PostMapping("{id}")
    public List<UploadFileResponse> uploadMultipleFiles(@ModelAttribute FileUploadRequest fileUploadRequest, @PathVariable("id") Long id) {
        return fileUploadUtil.uploadMultipleFiles(fileUploadRequest, id);
    }

    @GetMapping("{id}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id,
                                                 @PathVariable String fileName,
                                                 HttpServletRequest request) throws IOException {
        var resource = fileUploadUtil.downloadFile(fileName,request,id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(request.getServletContext()
                        .getMimeType(resource.getFile().getAbsolutePath())))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

}
