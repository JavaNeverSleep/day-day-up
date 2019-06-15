package com.javaneversleep.daydayup.lombok;

import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCAnnotation;
import com.sun.tools.javac.tree.JCTree.JCClassDecl;
import com.sun.tools.javac.tree.JCTree.JCExpression;
import com.sun.tools.javac.tree.JCTree.JCMethodDecl;
import com.sun.tools.javac.tree.JCTree.JCModifiers;
import com.sun.tools.javac.tree.JCTree.JCVariableDecl;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Names;

public class MyTreeTranslator extends TreeTranslator {

    private final TreeMaker treeMaker;

    private final Names names;

    private List<JCTree> getters = List.nil();

    MyTreeTranslator(TreeMaker treeMaker, Names names) {
        this.treeMaker = treeMaker;
        this.names = names;
    }

    @Override
    public void visitClassDef(JCClassDecl decl) {
        super.visitClassDef(decl);
        if (!getters.isEmpty()) {
            decl.defs = decl.defs.appendList(this.getters);
        }
        this.result = decl;
    }

    @Override
    public void visitVarDef(JCVariableDecl field) {
        super.visitVarDef(field);
        JCModifiers modifiers = field.getModifiers();
        List<JCAnnotation> annotations = modifiers.getAnnotations();
        if (annotations == null || annotations.size() <= 0) {
            return;
        }
        for (JCAnnotation annotation : annotations) {
            if (!MyGetter.class.getName().equals(annotation.type.toString())) {
                continue;
            }
            String name = field.getName().toString();
            String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            JCMethodDecl method = treeMaker.MethodDef(
                treeMaker.Modifiers(Flags.PUBLIC),
                names.fromString(methodName),
                (JCExpression) field.getType(),
                List.nil(),
                List.nil(),
                List.nil(),
                treeMaker.Block(0L, List.of(treeMaker.Return(treeMaker.Select(treeMaker.Ident(names.fromString("this")),
                    names.fromString(field.getName().toString()))))),
                null
            );
            this.getters = this.getters.append(method);
        }
    }
}
