package com.AIStudySystem.media.model.dto;

import lombok.Data;

/**
 * @description 上传普通文件请求参数
 * @author Jasper
 * @date 2023/1/7 15:43
 * @version 1.1
 */
@Data
public class UploadFileParamsDto {

    /**
     * 文件名称
     */
    private String filename;


    /**
     * 文件类型（文档，音频，视频）
     */
    private String fileType;
    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 标签
     */
    private String tags;

    /**
     * 上传人
     */
    private String username;

    /**
     * 备注
     */
    private String remark;



}

