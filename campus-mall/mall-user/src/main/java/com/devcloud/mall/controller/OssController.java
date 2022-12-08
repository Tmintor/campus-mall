package com.devcloud.mall.controller;

import com.devcloud.mall.common.R;
import com.devcloud.mall.service.OssService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 吴员外
 * @date 2022/9/21 20:32
 */
@RestController
@RequestMapping("/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("上传图片")
    @PostMapping("/upload")
    public R upLoadFile(MultipartFile multipartFile) {
        String url = ossService.fileUpLoad(multipartFile);
        return R.ok().data("imageUrl", url);
    }

}
