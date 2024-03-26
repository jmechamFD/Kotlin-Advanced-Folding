package com.github.jmechamfd.kotlinannotationfolding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtVisitorVoid
import java.util.*

class KotlinAnnotationFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = ArrayList<FoldingDescriptor>()
        // Initialize and use a visitor to find and process annotation entries
        if (root::class.simpleName == "KtFile") {
            root as KtFile
            root.accept(object : KtVisitorVoid() {
                override fun visitAnnotationEntry(annotationEntry: KtAnnotationEntry) {
                    super.visitAnnotationEntry(annotationEntry)
                    val textRange =
                        TextRange(annotationEntry.textRange.startOffset, annotationEntry.textRange.endOffset)
                    // Check if the annotation spans multiple lines
                    if (document.getLineNumber(textRange.startOffset) != document.getLineNumber(textRange.endOffset)) {
                        val node = annotationEntry.node as com.intellij.lang.ASTNode
                        descriptors.add(FoldingDescriptor(node, textRange))
                    }
                }
            })
        }
        return descriptors.toArray(arrayOfNulls(descriptors.size))
    }


    override fun getPlaceholderText(node: ASTNode): String? {
        // Placeholder text displayed when the annotation is folded
        return "@Annotation..."
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        // You can customize this to make annotations folded by default or based on user preferences
        return false
    }
}