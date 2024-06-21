package com.ukma.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("com.ukma.annotations.NullValidator")
public class NullValidatorProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!annotations.iterator().hasNext()) {
            return false;
        }
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotations.iterator().next());
        for (Element element : elements) {
            List<String> methods = ElementFilter.methodsIn(element.getEnclosedElements())
                    .stream()
                    .map(method -> method.getSimpleName().toString())
                    .filter(method -> method.startsWith("get"))
                    .toList();

            try {
                String className = element.getSimpleName().toString();
                String packageName = element.getEnclosingElement().toString();

                createValidatorClass(
                        className,
                        packageName,
                        methods
                );
            }  catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }

    private void createValidatorClass(String className, String packageName, List<String> getMethods)
            throws Exception {
        String fileName = className + "NullValidator";
        JavaFileObject builderFile = processingEnv.getFiler()
                .createSourceFile(fileName);

        PrintWriter writer = new PrintWriter(builderFile.openWriter());
        writer.println("package com.ukma.validators;");
        writer.println("import " + packageName + "." + className + ";");
        writer.println();
        writer.print("public class ");
        writer.print(fileName);
        writer.println(" {");
        writer.print(createValidateMethod(className, getMethods));
        writer.println("}");
        writer.flush();
        writer.close();

        System.out.println(builderFile.getName() + " here");
    }

    private String createValidateMethod (String className, List<String> getMethods) {
        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append("\tpublic boolean validate(Object object) {\n");

        stringBuilder.append("\t\t")
                     .append("if (object == null) {\n")
                     .append("\t\t\t")
                     .append("return false;\n")
                     .append("\t\t")
                     .append("}\n");

        stringBuilder.append("\t\t")
                     .append(className)
                     .append(" objectToValidate = (")
                     .append(className)
                     .append(") object;\n")
                     .append("\n");

        getMethods.forEach(method -> stringBuilder.append("\t\t")
                .append("if (objectToValidate.")
                .append(method)
                .append("() == null) {\n")
                .append("\t\t\t")
                .append("return false;\n")
                .append("\t\t")
                .append("}\n")
        );


        stringBuilder.append("\n")
                     .append("\t\t")
                     .append("return true;\n")
                     .append("\t}\n");

        return stringBuilder.toString();
    }

}
