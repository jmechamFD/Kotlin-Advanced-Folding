package com.github.jmechamfd.kotlinadvancedfolding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtCollectionLiteralExpression
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid

class KotlinAdvancedFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()

            root.accept(object : KtTreeVisitorVoid() {

                override fun visitAnnotationEntry(annotation: KtAnnotationEntry) {
                    super.visitAnnotationEntry(annotation)
                    if(KotlinAdvancedFoldingSettings.annotationFoldingEnabled) {
                        checkAndAddFoldingDescriptor(annotation, document, descriptors)
                    }
                }

                override fun visitCallExpression(expression: KtCallExpression) {
                    super.visitCallExpression(expression)
                    if(KotlinAdvancedFoldingSettings.callExpressionFoldingEnabled){
                        checkAndAddFoldingDescriptor(expression, document, descriptors)
                    }
                }

                override fun visitCollectionLiteralExpression(expression: KtCollectionLiteralExpression) {
                    super.visitCollectionLiteralExpression(expression)
                    if(KotlinAdvancedFoldingSettings.collectionLiteralFoldingEnabled) {
                        checkAndAddFoldingDescriptor(expression, document, descriptors)
                    }
                }
            })

        return descriptors.toTypedArray()
    }

    // Adds folding descriptors to elements that span multiple lines
    private fun checkAndAddFoldingDescriptor(element: PsiElement, document: Document, descriptors: MutableList<FoldingDescriptor>) {
        if (document.getLineNumber(element.textRange.startOffset) != document.getLineNumber(element.textRange.endOffset)) {
            descriptors.add(FoldingDescriptor(element.node, element.textRange))
        }
    }

    override fun getPlaceholderText(node: ASTNode): String? {
        return when (val psi = node.psi) {
            is KtAnnotationEntry -> { // All Annotations
                val annotationName = psi.children.firstOrNull()?.text ?: "Annotation"
                "@$annotationName(...)"
            }
            is KtCallExpression -> { // All Function Calls
                val functionName = psi.calleeExpression?.text ?: "Function"
                var firstParamText = psi.valueArguments.first().text
                // if the first parameter spans multiple lines, truncate it
                if (firstParamText.split("\n").size > 1){
                    firstParamText = firstParamText.split("\n").first()
                }
                "$functionName($firstParamText...)"
            }
            is KtCollectionLiteralExpression -> "[...]" // all arrays
            else -> "..."
        }
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return when (node.psi) {
            is KtAnnotationEntry -> true
            is KtCallExpression -> false
            is KtCollectionLiteralExpression -> false
            else -> false
        }
    }
}