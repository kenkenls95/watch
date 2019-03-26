package application.controller.api;

import application.constant.Constant;
import application.model.Meta;
import application.model.ResponseModel;
import application.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadApiController {

    @Autowired
    FileStorageService storageService;

    @PostMapping("/image")
    public ResponseModel uploadImage(@RequestParam("file") MultipartFile file) {
        ResponseModel result = new ResponseModel();
        Meta meta =  new Meta();
        try {
            String newFilename = storageService.store(file);
            meta.setMessage(Constant.PREFIX_LINK_UPLOAD +
                    newFilename);
            meta.setStatusCode(200);
        } catch (Exception e) {
            meta.setStatusCode(500);
        }
        result.setMeta(meta);
        return result;
    }

}
