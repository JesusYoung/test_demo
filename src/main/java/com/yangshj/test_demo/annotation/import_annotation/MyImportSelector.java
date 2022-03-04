package com.yangshj.test_demo.annotation.import_annotation;

import java.util.function.Predicate;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Create by yangshijie on 3/4/22
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[] { "com.yangshj.test_demo.annotation.import_annotation.ImportTestBean" };
    }


    // 忽略对应的bean生成
//    @Override
//    public Predicate<String> getExclusionFilter() {
//        return new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                if (s.matches("com.yangshj.test_demo.annotation.import_annotation.ImportTestBean")) {
//                    return true;
//                }
//                return false;
//            }
//        };
//    }
}
