package com.yangshj.test_demo.design_pattern.behavior.chain_of_responsibility;

/**
 * 块存储处理接口
 *
 * @author yangshj  2021.07.26
 */
public interface VolumeHandler {

    /**
     * 设置下一个处理类
     *
     * @param volumeHandler 处理接口类
     */
    void setNext(VolumeHandler volumeHandler);

    /**
     * 处理业务
     *
     * @param volumeVOWrapper 入参
     */
    void handle(VolumeVOWrapper volumeVOWrapper);
}
