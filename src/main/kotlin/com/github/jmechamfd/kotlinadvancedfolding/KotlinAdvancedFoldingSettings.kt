package com.github.jmechamfd.kotlinadvancedfolding
import com.intellij.ide.util.PropertiesComponent

object KotlinAdvancedFoldingSettings {
    private const val ANNOTATION_FOLDING_ENABLED_KEY = "KotlinAnnotationFoldingEnabled"
    private const val CALL_EXPRESSION_FOLDING_ENABLED_KEY = "KotlinCallExpressionFoldingEnabled"
    private const val COLLECTION_LITERAL_FOLDING_ENABLED_KEY = "KotlinCollectionLiteralFoldingEnabled"
    var annotationFoldingEnabled: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(ANNOTATION_FOLDING_ENABLED_KEY, true)
        set(value) = PropertiesComponent.getInstance().setValue(ANNOTATION_FOLDING_ENABLED_KEY, value, true)

    var callExpressionFoldingEnabled: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(CALL_EXPRESSION_FOLDING_ENABLED_KEY, true)
        set(value) = PropertiesComponent.getInstance().setValue(CALL_EXPRESSION_FOLDING_ENABLED_KEY, value, true)

    var collectionLiteralFoldingEnabled: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(COLLECTION_LITERAL_FOLDING_ENABLED_KEY, true)
        set(value) = PropertiesComponent.getInstance().setValue(COLLECTION_LITERAL_FOLDING_ENABLED_KEY, value, true)
}