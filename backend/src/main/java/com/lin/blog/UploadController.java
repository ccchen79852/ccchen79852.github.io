package com.lin.blog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(originPatterns={"http://localhost:*","https://*.up.railway.app"})
public class UploadController {
  private final AdminAuth auth;
  private final Path dir;
  UploadController(AdminAuth auth, @Value("${app.upload-dir}") String uploadDir) throws IOException {
    this.auth=auth; this.dir=Paths.get(uploadDir).toAbsolutePath().normalize(); Files.createDirectories(dir);
  }
  @PostMapping(value="/upload",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
  Map<String,String> upload(@RequestHeader("Authorization") String token,@RequestPart("file") MultipartFile file) throws IOException {
    auth.check(token);
    if(file.isEmpty()||file.getSize()>5*1024*1024) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"图片不能为空且不能超过5MB");
    String type=Optional.ofNullable(file.getContentType()).orElse("");
    if(!Set.of("image/jpeg","image/png","image/webp","image/gif").contains(type)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"图片格式不支持");
    String original=Optional.ofNullable(file.getOriginalFilename()).orElse("image.jpg");
    String ext=original.contains(".")?original.substring(original.lastIndexOf('.')).toLowerCase():".jpg";
    String name=UUID.randomUUID()+ext;
    Files.copy(file.getInputStream(),dir.resolve(name),StandardCopyOption.REPLACE_EXISTING);
    return Map.of("url","/uploads/"+name);
  }
}
