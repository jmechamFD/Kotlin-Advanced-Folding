package com.github.jmechamfd.kotlinadvancedfolding

import com.intellij.application.options.editor.CodeFoldingOptionsProvider
import com.intellij.openapi.options.BeanConfigurable

class KotlinAdvancedFoldingOptionsProvider : BeanConfigurable<KotlinAdvancedFoldingSettings>(
    KotlinAdvancedFoldingSettings, "Kotlin Advanced Folding"
), CodeFoldingOptionsProvider {
    init {
        checkBox("Enable Annotation Folding", KotlinAdvancedFoldingSettings::annotationFoldingEnabled)
        checkBox("Fold annotations by default", KotlinAdvancedFoldingSettings::annotationFoldedByDefault)
        checkBox("Show first parameter in annotation previews", KotlinAdvancedFoldingSettings::annotationShowFirstParam)
        checkBox("Enable Method Definition Folding", KotlinAdvancedFoldingSettings::namedFunctionFoldingEnabled)
        checkBox("Fold method definitions by default", KotlinAdvancedFoldingSettings::namedFunctionFoldedByDefault)
        checkBox("Show first parameter in method definition previews", KotlinAdvancedFoldingSettings::namedFunctionShowFirstParam)
        checkBox("Enable Method Call Folding", KotlinAdvancedFoldingSettings::callExpressionFoldingEnabled)
        checkBox("Fold method calls by default", KotlinAdvancedFoldingSettings::callExpressionFoldedByDefault)
        checkBox("Show first parameter in method call previews", KotlinAdvancedFoldingSettings::callExpressionShowFirstParam)
        checkBox("Enable Collection Folding", KotlinAdvancedFoldingSettings::collectionLiteralFoldingEnabled)
        checkBox("Fold collections by default", KotlinAdvancedFoldingSettings::collectionLiteralFoldedByDefault)
        checkBox("Show first element in collection previews", KotlinAdvancedFoldingSettings::collectionLiteralShowFirstElement)
    }
}