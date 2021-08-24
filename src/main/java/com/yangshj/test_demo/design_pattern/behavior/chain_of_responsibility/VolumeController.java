package com.yangshj.test_demo.design_pattern.behavior.chain_of_responsibility;


/**
 * 责任链模式
 *
 */
public class VolumeController {

    public static void main(String[] args) {

        VolumeVOWrapper volumeVOWrapper = new VolumeVOWrapper();
        volumeVOWrapper.setVolumeName("volumeTest01");
        volumeVOWrapper.setVolumeSize(50);

        Chain chain = new Chain();
        chain.processVolume(volumeVOWrapper);
    }
}
