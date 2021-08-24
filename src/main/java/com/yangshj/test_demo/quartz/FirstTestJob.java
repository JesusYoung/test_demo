package com.yangshj.test_demo.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FirstTestJob implements Job {

    private String message;
    private Float floatJobValue;
    private Double DoubleTriggerValue;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 打印当前时间
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time is: " + sf.format(date));


        // A、JobDataMap测试
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        System.out.println("job name: " + jobKey.getName());
        System.out.println("job group: " + jobKey.getGroup());

        TriggerKey trKey = jobExecutionContext.getTrigger().getKey();
        System.out.println("trigger name: " + trKey.getName());
        System.out.println("trigger group: " + trKey.getGroup());


        // A、JobDataMap测试
        // A.1、直接从Map获取
//        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
//        String jobMsg = jobDataMap.getString("message");
//        float jobFloatValue = jobDataMap.getFloatValue("floatJJobValue");
//        System.out.println("job message: " + jobMsg);
//        System.out.println("jobFloatValue: " + jobFloatValue);
//
//        JobDataMap triggerDataMap = jobExecutionContext.getTrigger().getJobDataMap();
//        String triggerMsg = triggerDataMap.getString("message");
//        double triggerDoubleValue = triggerDataMap.getDoubleValue("DoubleTriggerValue");
//        System.out.println("trigger message: " + triggerMsg);
//        System.out.println("triggerDoubleValue: " + triggerDoubleValue);
//
//        // 同时获取job和trigger的dataMap
//        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
//        // job和trigger有相同的key值，trigger会覆盖job的
//        dataMap.getString("message");


        // A.2、通过setter方法
        System.out.println("message: " + message);
        System.out.println("jobFloatValue: " + floatJobValue);
        System.out.println("triggerDoubleValue: " + DoubleTriggerValue);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Float getFloatJobValue() {
        return floatJobValue;
    }

    public void setFloatJobValue(Float floatJobValue) {
        this.floatJobValue = floatJobValue;
    }

    public Double getDoubleTriggerValue() {
        return DoubleTriggerValue;
    }

    public void setDoubleTriggerValue(Double doubleTriggerValue) {
        DoubleTriggerValue = doubleTriggerValue;
    }
}
