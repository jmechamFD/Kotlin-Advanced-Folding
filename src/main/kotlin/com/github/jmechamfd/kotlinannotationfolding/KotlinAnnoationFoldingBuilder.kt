package com.github.jmechamfd.kotlinannotationfolding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.idea.completion.argList
import org.jetbrains.kotlin.psi.KtAnnotatedExpression
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid
import org.jetbrains.kotlin.psi.KtVisitorVoid
import org.jetbrains.kotlin.psi.psiUtil.children
import org.jetbrains.kotlin.psi.psiUtil.siblings
import java.util.*

class KotlinAnnotationFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()
        root.accept(object : KtTreeVisitorVoid() {
            override fun visitAnnotationEntry(annotation: KtAnnotationEntry) {
                super.visitAnnotationEntry(annotation)
                checkAndAddFoldingDescriptor(annotation, document, descriptors)
            }
        })
        return descriptors.toTypedArray()
    }

    private fun checkAndAddFoldingDescriptor(annotationEntry: KtAnnotationEntry, document: Document, descriptors: MutableList<FoldingDescriptor>) {
        val textRange = annotationEntry.textRange
        if (document.getLineNumber(textRange.startOffset) != document.getLineNumber(textRange.endOffset)) {
            descriptors.add(FoldingDescriptor(annotationEntry.node, textRange))
        }
    }

    override fun getPlaceholderText(node: ASTNode): String? {
        // Placeholder text displayed when the annotation is folded
        val annotationName = node.psi.children[0].text
        return String.format("@%s...", annotationName)
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return true
    }
}