package com.yangshj.test_demo.design_pattern.behavior.chain_of_responsibility;

public class AtomicVolumeHandler implements VolumeHandler {

    private VolumeHandler nextHandler;

    @Override
    public void setNext(VolumeHandler volumeHandler) {
        this.nextHandler = volumeHandler;
    }

    @Override
    public void handle(VolumeVOWrapper volumeVOWrapper) {
        volumeUcsSave(volumeVOWrapper);
        volumeSave(volumeVOWrapper);

        System.out.println("数据入库完成。");

        if (null != nextHandler) {
            this.nextHandler.handle(volumeVOWrapper);
        } else {
            System.out.println("处理结束");
        }
    }


    private void volumeUcsSave(VolumeVOWrapper volumeVOWrapper) {
        // 块存储主表入库
        System.out.println("磁盘主表数据入库。。。");
    }

    private void volumeSave(VolumeVOWrapper volumeVOWrapper) {
        // 块存储子表入库
        System.out.println("磁盘子表数据入库。。。");
    }
}
