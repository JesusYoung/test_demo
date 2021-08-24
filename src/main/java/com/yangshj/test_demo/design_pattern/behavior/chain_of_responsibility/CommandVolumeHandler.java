package com.yangshj.test_demo.design_pattern.behavior.chain_of_responsibility;

import java.util.HashMap;
import java.util.Map;

public class CommandVolumeHandler implements VolumeHandler {

    private VolumeHandler nextHandler;

    @Override
    public void setNext(VolumeHandler volumeHandler) {
        this.nextHandler = volumeHandler;
    }

    @Override
    public void handle(VolumeVOWrapper volumeVOWrapper) {
        buildRequestParams(volumeVOWrapper);
        CmdInfo cmdInfo = buildVolumeCreateCommand(volumeVOWrapper);
        sendMsg(cmdInfo);

        System.out.println("指令拼接完成，cmd：" + cmdInfo.toString());

        if (null != nextHandler) {
            this.nextHandler.handle(volumeVOWrapper);
        } else {
            System.out.println("处理结束");
        }
    }


    private void buildRequestParams(VolumeVOWrapper volumeVOWrapper) {
        Map<String, Object> paramsMap = new HashMap<>();

        paramsMap.put("name", volumeVOWrapper.getVolumeName());
        paramsMap.put("size", volumeVOWrapper.getVolumeSize());

        volumeVOWrapper.setRequestParams(paramsMap.toString());
    }

    private CmdInfo buildVolumeCreateCommand(VolumeVOWrapper volumeVOWrapper) {

        CmdInfo cmdInfo = new CmdInfo();
        cmdInfo.setRequestParams(volumeVOWrapper.getRequestParams());

        return cmdInfo;
    }

    private void sendMsg(CmdInfo cmdInfo) {
        // 发送消息
        System.out.println("指令消息发送完成。。。");
    }
}
