package com.yangshj.test_demo.design_pattern.behavior.chain_of_responsibility;

public class CheckVolumeHandler implements VolumeHandler {

    private VolumeHandler nextHandler;

    @Override
    public void setNext(VolumeHandler volumeHandler) {
        this.nextHandler = volumeHandler;
    }

    @Override
    public void handle(VolumeVOWrapper volumeVOWrapper) {

        checkVolumeName(volumeVOWrapper);
        checkVolumeSize(volumeVOWrapper);

        System.out.println("参数校验完成。");

        if (null != nextHandler) {
            this.nextHandler.handle(volumeVOWrapper);
        } else {
            System.out.println("处理结束");
        }
    }



    private void checkVolumeName(VolumeVOWrapper volumeVOWrapper) {
        // 校验名称
        System.out.println("校验磁盘名称。。。");
    }

    private void checkVolumeSize(VolumeVOWrapper volumeVOWrapper) {
        // 校验磁盘大小
        System.out.println("校验磁盘大小。。。");
    }
}
