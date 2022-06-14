package com.yangshj.test_demo.annotation.custom_annotation;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

/**
 * @author yangshijie
 * @since 6/14/22 2:24 PM
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.yangshj.test_demo.annotation.custom_annotation.MyGetter")
public class MyGetterProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        StringBuilder builder = new StringBuilder()
                .append("package com.yangshj.test_demo.annotation.custom_annotation;\n\n")
                .append("public class GeneratedClass {\n\n")
                .append("\tpublic String getMessage() {\n")
                .append("\t\treturn \"");

        for (Element element : roundEnv.getElementsAnnotatedWith(MyGetter.class)) {
            String objectType = element.getSimpleName().toString();
            builder.append(objectType).append(" say hello!\\n");
        }
        builder.append("\";\n")
                .append("\t}\n")
                .append("}\n");

        try {
            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.yangshj.test_demo.annotation.custom_annotation.GeneratedClass");
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }
        return true;
    }
}
