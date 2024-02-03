package org.philip.controller;

import org.philip.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        // 把文件的內容儲存到本地磁盤上
        String originalFilename = file.getOriginalFilename();
        // 保證文件的名字是唯一的，從而防止文件覆蓋
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("/Users/linzhuofei/Desktop/黑馬/Vue3+SpringBoot3/files/" + filename));
        return Result.success("url訪問地址...");
    }
}
