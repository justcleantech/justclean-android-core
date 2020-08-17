package com.justclean.core.custom.bottomsheet

interface BottomSheetStateChanged {
    fun onSheetDragging()
    fun onSheetSettling()
    fun onSheetExpanded()
    fun onSheetCollapsed()
    fun onSheetHidden()
    fun onSheetHaveExpanded()
}