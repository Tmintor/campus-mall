package com.devcloud.mall.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.devcloud.mall.constant.AliyunConstant;
import com.devcloud.mall.service.OssService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author 吴员外
 * @date 2022/9/21 20:34
 */
@Service
public class OssServiceImpl implements OssService {

    @Override
    public String fileUpLoad(MultipartFile multipartFile) {

        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = AliyunConstant.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = AliyunConstant.ACCESS_KEY_ID;
        String accessKeySecret = AliyunConstant.ACCESS_KEY_SECRET;
        // 填写Bucket名称
        String bucketName = AliyunConstant.BUCKET_NAME;
        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String fileName = new DateTime().toString("yyyy/MM/dd")
                + "/" + UUID.randomUUID().toString().replaceAll("-", "")
                + multipartFile.getOriginalFilename();

        OSS ossClient = null;
        try {
            InputStream file = multipartFile.getInputStream();
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file);
            // 上传文件。
            ossClient.putObject(putObjectRequest);

            return "https://"  + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return null;
    }

}
