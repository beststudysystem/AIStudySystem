package com.AIStudySystem.content.service.jobhandler;

import com.AIStudySystem.messagesdk.model.po.MqMessage;
import com.AIStudySystem.messagesdk.service.MessageProcessAbstract;
import com.AIStudySystem.messagesdk.service.MqMessageService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Jasper
 * @version 1.0
 * @description 课程发布任务类
 * @date 2024/2/22 12:29
 */
@Slf4j
@Component
public class CoursePublishTask extends MessageProcessAbstract {

    //任务调度入口
    @XxlJob("CoursePublishJobHandler")
    public void coursePublishJobHandler() throws Exception {
        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        log.debug("shardIndex="+shardIndex+",shardTotal="+shardTotal);
        //参数:分片序号、分片总数、消息类型、一次最多取到的任务数量、一次任务调度执行的超时时间
        process(shardIndex,shardTotal,"course_publish",30,60);
    }


    //执行课程发布任务的逻辑, 如果此方法抛出异常说明任务执行失败
    @Override
    public boolean execute(MqMessage mqMessage) {
        //获取消息相关的业务信息（这里是课程id）「放在哪个位置就拿哪个位置的值」
        String businessKey1 = mqMessage.getBusinessKey1();
        long courseId = Integer.parseInt(businessKey1);
        //课程静态化（第一阶段）
        generateCourseHtml(mqMessage,courseId);
        //课程索引（第二阶段）
        saveCourseIndex(mqMessage,courseId);
        //课程缓存（第三阶段）
        saveCourseCache(mqMessage,courseId);
        return true;
    }


    //生成课程静态化页面并上传至文件系统（第一阶段）
    public void generateCourseHtml(MqMessage mqMessage, long courseId){

        log.debug("开始进行课程静态化,课程id:{}",courseId);
        //消息id
        Long id = mqMessage.getId();
        //消息处理的service
        MqMessageService mqMessageService = this.getMqMessageService();
        //消息幂等性处理：判断是否已经处理过
        int stageOne = mqMessageService.getStageOne(id);  // 取出第一阶段的状态，如果大于0则表示已经处理过
        if(stageOne >0){
            log.debug("课程静态化已处理直接返回，课程id:{}",courseId);
            return ;
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //保存第一阶段状态
        mqMessageService.completedStageOne(id);

    }

    //将课程信息缓存至redis（第二阶段）
    public void saveCourseCache(MqMessage mqMessage,long courseId){
        log.debug("将课程信息缓存至redis,课程id:{}",courseId);
        //消息id
        Long id = mqMessage.getId();
        //消息处理的service
        MqMessageService mqMessageService = this.getMqMessageService();
        //消息幂等性处理：判断是否已经处理过
        int stageTwo = mqMessageService.getStageTwo(id);  // 取出第一阶段的状态，如果大于0则表示已经处理过
        if(stageTwo >0){
            log.debug("将课程信息缓存至redis已处理直接返回，课程id:{}",courseId);
            return ;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    //保存课程索引信息（第三阶段）
    public void saveCourseIndex(MqMessage mqMessage,long courseId){
        log.debug("保存课程索引信息,课程id:{}",courseId);
        //消息id
        Long id = mqMessage.getId();
        //消息处理的service
        MqMessageService mqMessageService = this.getMqMessageService();
        //消息幂等性处理：判断是否已经处理过
        int stageThree = mqMessageService.getStageThree(id);  // 取出第一阶段的状态，如果大于0则表示已经处理过
        if(stageThree >0){
            log.debug("保存课程索引信息已处理直接返回，课程id:{}",courseId);
            return ;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
