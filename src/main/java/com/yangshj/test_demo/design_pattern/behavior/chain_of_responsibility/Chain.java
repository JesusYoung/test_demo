package com.yangshj.test_demo.design_pattern.behavior.chain_of_responsibility;

public class Chain {

    VolumeHandler volumeHandler;

    public Chain() {
        buildVolumeProcessChain();
    }

    private void buildVolumeProcessChain() {

        VolumeHandler check = new CheckVolumeHandler();
        VolumeHandler atomic = new AtomicVolumeHandler();
        VolumeHandler cmd = new CommandVolumeHandler();

        check.setNext(atomic);
        atomic.setNext(cmd);

        this.volumeHandler = check;
    }

    public void processVolume(VolumeVOWrapper volumeVOWrapper) {
        volumeHandler.handle(volumeVOWrapper);
    }
}
