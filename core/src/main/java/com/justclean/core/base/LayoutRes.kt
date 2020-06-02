package com.justclean.core.base

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class LayoutRes(// value is the resource id of the view
    val layout: Int = 0,
    val titleId: Int = 0,
    val menu: Int = 0,
    val withBack: Boolean = false)