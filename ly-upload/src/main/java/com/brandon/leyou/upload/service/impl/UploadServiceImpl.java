package com.brandon.leyou.upload.service.impl;

import com.brandon.leyou.upload.service.IUploadService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadServiceImpl implements IUploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    // 支持的文件上传类型
    private static final List<String> suffixes = Arrays.asList("image/png", "image/jpg", "image/jpeg");
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Override
    public String upload(MultipartFile file) {
        try {
            // 校验文件类型
            String contentType = file.getContentType();
            if(!suffixes.contains(contentType)){
                logger.info("上传失败，文件类型不匹配，{}", contentType);
                return null;
            }
            // 校验图片内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image == null){
                logger.info("上传失败，文件内容不符合要求");
                return null;
            }

            /*File dir = new File("D:\\opt\\leyou");
            if(!dir.exists()){
                dir.mkdirs();
            }
            file.transferTo(new File(dir, file.getOriginalFilename()));
            String url = "http://image.leyou.com/upload/" + file.getOriginalFilename();
            return url;*/
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            return "http://192.168.93.195:8090/" + storePath.getFullPath();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
