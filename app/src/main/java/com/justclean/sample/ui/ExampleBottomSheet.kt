package com.justclean.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.justclean.core.R
import com.justclean.core.custom.bottomsheet.CustomBottomSheet
import com.justclean.core.custom.bottomsheet.BottomSheetDataSource

class ExampleBottomSheet(dataSource: BottomSheetDataSource) :
    CustomBottomSheet(dataSource = dataSource) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.example_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //Bind Views
    }

}