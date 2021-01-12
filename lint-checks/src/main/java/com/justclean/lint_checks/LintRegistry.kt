package com.justclean.lint_checks

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

class LintRegistry : IssueRegistry() {

    override val api: Int = com.android.tools.lint.detector.api.CURRENT_API

    override val issues: List<Issue> = listOf(ButtonHeightDetector.HEIGHT_WILL_BE_IGNORED)
}