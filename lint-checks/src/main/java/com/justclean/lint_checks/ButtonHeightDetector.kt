package com.justclean.lint_checks

import com.android.SdkConstants
import com.android.tools.lint.detector.api.*
import org.w3c.dom.Element

class ButtonHeightDetector : LayoutDetector() {

    companion object {
        @JvmStatic
        internal val HEIGHT_WILL_BE_IGNORED = Issue.create(
            id = "IgnoredHeightDimension",
            briefDescription = "Make height wrap_content while using withLoading = true",
            explanation = "Height will be overridden to 56dp when loading is enabled so use wrap_content instead",
            category = Category.USABILITY,
            priority = 8,
            severity = Severity.ERROR,
            implementation = Implementation(
                ButtonHeightDetector::class.java,
                Scope.ALL_RESOURCES_SCOPE
            )
        )
    }

    override fun getApplicableElements(): Collection<String> {
        return listOf("com.justclean.uikit.atoms.JCButton")
    }

    override fun visitElement(context: XmlContext, element: Element) {
        if (element.getAttribute("app:withLoading") == "true"
            && element.getAttribute(SdkConstants.ATTR_LAYOUT_HEIGHT) != "wrap_content")
            context.report(
                issue = HEIGHT_WILL_BE_IGNORED,
                location = context.getLocation(element),
                message = HEIGHT_WILL_BE_IGNORED.getExplanation(TextFormat.TEXT)
            )
    }
}