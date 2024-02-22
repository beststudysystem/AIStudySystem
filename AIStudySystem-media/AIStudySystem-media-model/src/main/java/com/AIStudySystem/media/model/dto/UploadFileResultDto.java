package com.AIStudySystem.media.model.dto;

import com.AIStudySystem.media.model.po.MediaFiles;
import lombok.Data;
import lombok.ToString;

/**
 * @description 上传普通文件成功响应结果
 * @author Jasper
 * @date 2023/1/7 14:48
 * @version 1.1
 */
@Data
@ToString
public class UploadFileResultDto extends MediaFiles {
    // 为了方便以后扩展（前端要求加一些返回的数据），这里继承了MediaFiles


}
