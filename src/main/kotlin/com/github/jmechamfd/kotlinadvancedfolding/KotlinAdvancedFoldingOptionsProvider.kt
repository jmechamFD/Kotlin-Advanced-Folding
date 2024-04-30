package com.github.jmechamfd.kotlinadvancedfolding

import com.intellij.application.options.editor.CodeFoldingOptionsProvider
import com.intellij.openapi.options.BeanConfigurable

class KotlinAdvancedFoldingOptionsProvider : BeanConfigurable<KotlinAdvancedFoldingSettings>(
    KotlinAdvancedFoldingSettings, "Kotlin Advanced Folding"
), CodeFoldingOptionsProvider {
    init {
        checkBox("Annotations", KotlinAdvancedFoldingSettings::annotationFoldingEnabled)
        checkBox("Method Calls", KotlinAdvancedFoldingSettings::callExpressionFoldingEnabled)
        checkBox("Collections", KotlinAdvancedFoldingSettings::collectionLiteralFoldingEnabled)
    }
}