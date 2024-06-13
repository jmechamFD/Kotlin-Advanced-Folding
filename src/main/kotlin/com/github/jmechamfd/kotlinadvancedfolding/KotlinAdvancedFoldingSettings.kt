package com.github.jmechamfd.kotlinadvancedfolding
import com.intellij.ide.util.PropertiesComponent

object KotlinAdvancedFoldingSettings {
    private const val ANNOTATION_FOLDING_ENABLED_KEY = "KotlinAnnotationFoldingEnabled"
    private const val ANNOTATION_FOLDED_BY_DEFAULT = "KotlinAnnotationFoldingByDefault"
    private const val ANNOTATION_SHOW_FIRST_PARAM_KEY = "KotlinAnnotationShowFirstParam"
    private const val CALL_EXPRESSION_FOLDING_ENABLED_KEY = "KotlinCallExpressionFoldingEnabled"
    private const val CALL_EXPRESSION_FOLDED_BY_DEFAULT = "KotlinCallExpressionFoldingByDefault"
    private const val CALL_EXPRESSION_SHOW_FIRST_PARAM_KEY = "KotlinCallExpressionShowFirstParam"
    private const val COLLECTION_LITERAL_FOLDING_ENABLED_KEY = "KotlinCollectionLiteralFoldingEnabled"
    private const val COLLECTION_LITERAL_FOLDED_BY_DEFAULT = "KotlinCollectionLiteralFoldingByDefault"
    private const val COLLECTION_LITERAL_SHOW_FIRST_ELEMENT_KEY = "KotlinCollectionLiteralShowFirstElement"
    private const val NAMED_FUNCTION_FOLDING_ENABLED_KEY = "KotlinNamedFunctionFoldingEnabled"
    private const val NAMED_FUNCTION_FOLDED_BY_DEFAULT = "KotlinNamedFunctionFoldingByDefault"
    private const val NAMED_FUNCTION_SHOW_FIRST_PARAM_KEY = "KotlinNamedFunctionShowFirstParam"

    var annotationFoldingEnabled: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(ANNOTATION_FOLDING_ENABLED_KEY, true)
        set(value) = PropertiesComponent.getInstance().setValue(ANNOTATION_FOLDING_ENABLED_KEY, value, true)

    var annotationShowFirstParam: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(ANNOTATION_SHOW_FIRST_PARAM_KEY, true)
        set(value) = PropertiesComponent.getInstance().setValue(ANNOTATION_SHOW_FIRST_PARAM_KEY, value, true)

    var callExpressionFoldingEnabled: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(CALL_EXPRESSION_FOLDING_ENABLED_KEY, true)
        set(value) = PropertiesComponent.getInstance().setValue(CALL_EXPRESSION_FOLDING_ENABLED_KEY, value, true)

    var callExpressionShowFirstParam: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(CALL_EXPRESSION_SHOW_FIRST_PARAM_KEY, true)
        set(value) = PropertiesComponent.getInstance().setValue(CALL_EXPRESSION_SHOW_FIRST_PARAM_KEY, value, true)

    var collectionLiteralFoldingEnabled: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(COLLECTION_LITERAL_FOLDING_ENABLED_KEY, true)
        set(value) = PropertiesComponent.getInstance().setValue(COLLECTION_LITERAL_FOLDING_ENABLED_KEY, value, true)

    var collectionLiteralShowFirstElement: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(COLLECTION_LITERAL_SHOW_FIRST_ELEMENT_KEY, false)
        set(value) = PropertiesComponent.getInstance().setValue(COLLECTION_LITERAL_SHOW_FIRST_ELEMENT_KEY, value, false)

    var namedFunctionFoldingEnabled: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(NAMED_FUNCTION_FOLDING_ENABLED_KEY, true)
        set(value) = PropertiesComponent.getInstance().setValue(NAMED_FUNCTION_FOLDING_ENABLED_KEY, value, true)

    var namedFunctionShowFirstParam: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(NAMED_FUNCTION_SHOW_FIRST_PARAM_KEY, true)
        set(value) = PropertiesComponent.getInstance().setValue(NAMED_FUNCTION_SHOW_FIRST_PARAM_KEY, value, true)

    var annotationFoldedByDefault: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(ANNOTATION_FOLDED_BY_DEFAULT, false)
        set(value) = PropertiesComponent.getInstance().setValue(ANNOTATION_FOLDED_BY_DEFAULT, value, false)

    var callExpressionFoldedByDefault: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(CALL_EXPRESSION_FOLDED_BY_DEFAULT, false)
        set(value) = PropertiesComponent.getInstance().setValue(CALL_EXPRESSION_FOLDED_BY_DEFAULT, value, false)

    var collectionLiteralFoldedByDefault: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(COLLECTION_LITERAL_FOLDED_BY_DEFAULT, false)
        set(value) = PropertiesComponent.getInstance().setValue(COLLECTION_LITERAL_FOLDED_BY_DEFAULT, value, false)

    var namedFunctionFoldedByDefault: Boolean
        get() = PropertiesComponent.getInstance().getBoolean(NAMED_FUNCTION_FOLDED_BY_DEFAULT, false)
        set(value) = PropertiesComponent.getInstance().setValue(NAMED_FUNCTION_FOLDED_BY_DEFAULT, value, false)
}