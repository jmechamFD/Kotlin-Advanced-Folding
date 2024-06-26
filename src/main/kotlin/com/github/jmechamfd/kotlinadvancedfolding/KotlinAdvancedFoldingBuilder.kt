package com.github.jmechamfd.kotlinadvancedfolding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.openapi.editor.Document
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtCallExpression
import org.jetbrains.kotlin.psi.KtCollectionLiteralExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid

class KotlinAdvancedFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        val descriptors = mutableListOf<FoldingDescriptor>()

            root.accept(object : KtTreeVisitorVoid() {

                override fun visitNamedFunction(function: KtNamedFunction) {
                    super.visitNamedFunction(function)
                    if(KotlinAdvancedFoldingSettings.namedFunctionFoldingEnabled){
                        checkAndAddFoldingDescriptor(function, document, descriptors)
                    }
                }

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
        return when (element) {
            is KtNamedFunction -> {
                val startLineOffset = element.nameIdentifier?.textRange?.endOffset ?: return
                val endLineOffset = element.valueParameterList?.textRange?.endOffset ?: return
                val startLine = document.getLineNumber(startLineOffset)
                val endLine = document.getLineNumber(endLineOffset)
                if (startLine == endLine) {
                    return
                }
                val textRange = TextRange(startLineOffset + 1, endLineOffset - 1)
                descriptors.add(FoldingDescriptor(element.node, textRange))
                return
            }
            is KtCallExpression -> {
                val startLineOffset = element.calleeExpression?.textRange?.endOffset ?: return
                val endLineOffset = element.valueArgumentList?.textRange?.endOffset ?: return
                val startLine = document.getLineNumber(startLineOffset)
                val endLine = document.getLineNumber(endLineOffset)
                if (startLine == endLine) {
                    return
                }
                val textRange = TextRange(startLineOffset + 1, endLineOffset - 1)
                descriptors.add(FoldingDescriptor(element.node, textRange))
                return
            }
            is KtAnnotationEntry -> {
                val startLineOffset = element.children.firstOrNull()?.textRange?.endOffset ?: return
                val endLineOffset = element.children.lastOrNull()?.textRange?.endOffset ?: return
                val startLine = document.getLineNumber(startLineOffset)
                val endLine = document.getLineNumber(endLineOffset)
                if (startLine == endLine) {
                    return
                }
                val textRange = TextRange(startLineOffset + 1, endLineOffset - 1)
                descriptors.add(FoldingDescriptor(element.node, textRange))
                return
            }
            is KtCollectionLiteralExpression -> {
                if (document.getLineNumber(element.textRange.startOffset) != document.getLineNumber(element.textRange.endOffset)) {
                    val arrayTextRange = TextRange(element.textRange.startOffset + 1, element.textRange.endOffset - 1)
                    descriptors.add(FoldingDescriptor(element.node, arrayTextRange))
                }
                return
            }
            else -> {}
        }
    }

    override fun getPlaceholderText(node: ASTNode): String? {
        return when (val psi = node.psi) {
            is KtAnnotationEntry -> { // All Annotations
                val annotationName = psi.children.firstOrNull()?.text ?: "Annotation"
                if (!KotlinAdvancedFoldingSettings.annotationShowFirstParam) {
                    return "@$annotationName(...)"
                }
                var firstParamText = psi.lastChild.text
                // if the first parameter spans multiple lines, truncate it
                if (firstParamText.split("\n").size > 1){
                    firstParamText = firstParamText.split("\n")[1].trim().replace(",", "")
                }
               "$firstParamText..."
            }
            is KtCallExpression -> { // All Function Calls
                if (!KotlinAdvancedFoldingSettings.callExpressionShowFirstParam) {
                    return "..."
                }
                var firstParamText = psi.valueArguments.first().text
                // if the first parameter spans multiple lines, truncate it
                if (firstParamText.split("\n").size > 1){
                    firstParamText = firstParamText.split("\n").first()
                }
                "$firstParamText..."
            }
            is KtCollectionLiteralExpression -> { // all arrays
                if (!KotlinAdvancedFoldingSettings.collectionLiteralShowFirstElement) {
                    return "..."
                }
                var firstElement = psi.children.firstOrNull()?.text ?: "Element"
                if (firstElement.split("\n").size > 1){
                    firstElement = firstElement.split("\n").first()
                }
                "$firstElement..."
            }
            is KtNamedFunction -> { // All Function Definitions
                if (!KotlinAdvancedFoldingSettings.namedFunctionShowFirstParam) {
                    return "..."
                }
                var firstParamText = psi.valueParameters.firstOrNull()?.text ?: "param"
                if (firstParamText.split("\n").size > 1){
                    firstParamText = firstParamText.split("\n").first()
                }
                "$firstParamText..."
            }
            else -> "..."
        }
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean {
        return when (node.psi) {
            is KtAnnotationEntry -> {
                KotlinAdvancedFoldingSettings.annotationFoldedByDefault
            }
            is KtCallExpression -> {
                // if call expression is the direct child of an collection literal
                if (node.treeParent?.psi is KtCollectionLiteralExpression) {
                    if (KotlinAdvancedFoldingSettings.callExpressionInsideArrayFoldingEnabledByDefault) {
                        return true
                    }
                }
                KotlinAdvancedFoldingSettings.callExpressionFoldedByDefault
            }
            is KtCollectionLiteralExpression -> {
                KotlinAdvancedFoldingSettings.collectionLiteralFoldedByDefault
            }
            is KtNamedFunction -> {
                KotlinAdvancedFoldingSettings.namedFunctionFoldedByDefault
            }
            else -> {
                println("Defaulting to false")
                // print node.psi to see what type of element is being folded
                println(node.psi.elementType)
                false
            }
        }
    }
}