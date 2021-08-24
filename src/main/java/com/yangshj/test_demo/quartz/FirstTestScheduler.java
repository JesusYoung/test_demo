package com.yangshj.test_demo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class FirstTestScheduler {

    public static void main(String[] args) throws SchedulerException {

        // 1、创建任务
        // 创建一个JobDetail，将该实例与Job(FirstTestJob.class)绑定
//        JobDetail jobDetail = JobBuilder.newJob(FirstTestJob.class)
//                .withIdentity("myFirstJob", "group1").build();

        // A、JobDataMap测试
        JobDetail jobDetail = JobBuilder.newJob(FirstTestJob.class)
                .withIdentity("myFirstJob", "group1")
                .usingJobData("message", "HelloMyJob1")
                .usingJobData("floatJobValue", 3.14F)
                .build();


        // 2、创建触发器
        // 2.1、创建一个SchedulerBuilder，2秒执行一次，一直执行
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();
        // 2.2、创建一个Trigger实例，定义该Job立即执行
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("myFirstTrigger", "group1")
//                .startNow()
//                .withSchedule(scheduleBuilder)
//                .build();

        // B、指定执行时间和结束时间测试
        Date startTime = new Date();
        // 指定3秒后执行
        startTime.setTime(startTime.getTime() + 3000);
        // 指定6秒后停止
        Date endTime = new Date();
        endTime.setTime(endTime.getTime() + 6000);


        // A、JobDataMap测试
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myFirstTrigger", "group1")
                .usingJobData("message", "HelloMyTrigger1")
                .usingJobData("DoubleTriggerValue", 2.0D)
                // 指定开始时间和结束时间
                .startAt(startTime)
                .endAt(endTime)
//                .startNow()
//                .withSchedule(scheduleBuilder)
                // SimpleTrigger使用
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                // 指定执行次数，无限次，等同于repeatForever()
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY))

                // CronTrigger使用
                // 每秒钟触发一次任务
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *"))
                .build();


        // 3、创建调度器
        //
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();

        scheduler.start();

        // 绑定任务和触发器
        scheduler.scheduleJob(jobDetail, trigger);



        String a = "0，1，1，2，3，5，8，13，21，34，55，89，144……";
    }
}
