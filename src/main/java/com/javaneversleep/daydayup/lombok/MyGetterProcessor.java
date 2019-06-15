package com.javaneversleep.daydayup.lombok;

import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@AutoService(MyGetterProcessor.class)
public class MyGetterProcessor extends AbstractProcessor {

    private Trees trees;

    private Names names;

    private TreeMaker treeMaker;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        this.trees = Trees.instance(env);
        Context context = ((JavacProcessingEnvironment) env).getContext();
        this.names = Names.instance(context);
        this.treeMaker = TreeMaker.instance(context);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            roundEnv.getRootElements().stream()
                .filter(e -> e.getKind().isClass())
                .forEach(e -> {
                    JCTree tree = (JCTree) trees.getTree(e);
                    tree.accept(new MyTreeTranslator(treeMaker, names));
                });
        }
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
