package com.github.jmechamfd.kotlinadvancedfolding

import com.intellij.application.options.editor.CodeFoldingOptionsProvider
import com.intellij.openapi.options.BeanConfigurable

class KotlinAdvancedFoldingOptionsProvider : BeanConfigurable<KotlinAdvancedFoldingSettings>(
    KotlinAdvancedFoldingSettings, "Kotlin Advanced Folding"
), CodeFoldingOptionsProvider {
    init {
        checkBox("Enable Annotation Folding", KotlinAdvancedFoldingSettings::annotationFoldingEnabled)
        checkBox("Enable Method Calls", KotlinAdvancedFoldingSettings::callExpressionFoldingEnabled)
        checkBox("Enable Collections Folding", KotlinAdvancedFoldingSettings::collectionLiteralFoldingEnabled)
    }
}