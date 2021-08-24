package com.yangshj.test_demo.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Operator {



    public static void main(String[] args) {


        // 6、裁剪操作符
        // 6.1、any操作符，检测是否至少有一个元素具有所指定的属性
        Flux.just(3, 5, 7, 9, 11, 15, 17, 20).any(e -> e % 2 == 0).subscribe(System.out::println);

        // 6.2、all操作符，检测元素是否都具有一定属性
        Flux.just("all", "any", "apple", "ha", "yang", "jie").all(e -> e.contains("a")).subscribe(System.out::println);

        // 6.3、concat，顺序合并
        Flux.concat(Flux.range(1, 4), Flux.range(4, 4), Flux.range(6, 5)).subscribe(System.out::println);

        // 6.4、reduce，对数据流进行累计
        Flux.range(1, 10).reduce(Integer::sum).subscribe(System.out::println);

        // 6.5、reduceWith，提供一个默认值的累计
        Flux.range(1, 10).reduceWith(() -> 5, Integer::sum).subscribe(System.out::println);


        // 7、工具操作符
        // 7.1、subscribe操作符，订阅

        // 7.2、timeout操作符，当特定时间段内没有产生任何事件时，将生成一个异常
        Flux.range(1, 0).timeout(Duration.ofSeconds(2)).subscribe(System.out::println);

        // 7.3、block操作符，在接收到下一个元素之前会一直阻塞
        Mono.just(1).block(Duration.ofSeconds(2));

        // 7.4、log操作符，日志操作符
        Flux.just("a", "b").log().subscribe(System.out::println);
        Flux.just("a", "b").log("logResult:").subscribe(System.out::println);

        // 7.5、debug操作符，调试模式
        Hooks.onOperatorDebug();

        // 7.6、checkpoint操作符，观察单独的流的日志堆栈
        Mono.just(0).map(x -> 1/x).checkpoint("debug").subscribe(System.out::println);


    }

}
