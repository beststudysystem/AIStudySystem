package com.AIStudySystem.media.service;

import com.AIStudySystem.media.model.po.MediaFiles;
import com.AIStudySystem.media.model.po.MediaProcess;

import java.util.List;

public interface MediaFileProcessService {
    /**
     * 获取待处理任务
     * @param shardIndex    分片序号
     * @param shardTotal    分片总数
     * @param count         获取记录数
     * @return  待处理任务集合
     */
    List<MediaProcess> getMediaProcessList(int shardIndex, int shardTotal, int count);

    /**
     *  开启一个任务
     * @param id 任务id
     * @return true开启任务成功，false开启任务失败
     */
    public boolean startTask(long id);

    /**
     * @description 保存任务结果
     * @param taskId  任务id
     * @param status 任务状态
     * @param fileId  文件id
     * @param url url
     * @param errorMsg 错误信息
     * @return void
     * @date 2024/2/1 18:18
     */
    void saveProcessFinishStatus(Long taskId,String status,String fileId,String url,String errorMsg);

    /**
     * 添加待处理任务
     * @param mediaFiles 媒资文件信息
     */
    void addWaitingTask(MediaFiles mediaFiles);


}
