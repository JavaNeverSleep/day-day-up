package com.javaneversleep.daydayup;

import com.javaneversleep.daydayup.lombok.MyGetterProcessor;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JSR199Demo {

    private static final String CODE = "package com.javaneversleep.daydayup;\n" +
        "\n" +
        "Class HelloWorld {\n" +
        "\n" +
        "    public static void main(String[] args) {\n" +
        "        System.out.println(\"Hello World\");\n" +
        "    }\n" +
        "\n" +
        "}";

    private static class JavaSource extends SimpleJavaFileObject {
        private final String sourceCode;

        private JavaSource(String className, String sourceCode) {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.sourceCode = sourceCode;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return this.sourceCode;
        }
    }

    public static void main(String[] args) {
        JavaFileObject sjo = new JavaSource("chapter1.Hello", CODE);
        Iterable<? extends JavaFileObject> sources = Collections.singletonList(sjo);
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        Iterable<String> options = Arrays.asList("-d", "target/classes");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        try (StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null)) {
            CompilationTask task = compiler.getTask(null, fileManager, diagnostics,
                options, null, sources);
            // Lombok here!
            task.setProcessors(Collections.singletonList(new MyGetterProcessor()));
            boolean success = task.call();
            if (!success) {
                List<Diagnostic<? extends JavaFileObject>> errors = diagnostics.getDiagnostics();
                System.out.println(errors.size() + "个可能的编译问题！");
                errors.forEach(System.out::println);
            } else {
                // Load the compiled class and invoke the main method
                Class<?> clazz = Class.forName("com.javaneversleep.daydayup.HelloWorld");
                System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
                clazz.getMethod("main", String[].class).invoke(null, (Object) null);
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
