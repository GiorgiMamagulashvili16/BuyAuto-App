package com.example.buyauto_app.presentation.add_car_screen.util

import com.example.buyauto_app.presentation.add_car_screen.bottom_sheet.BottomSheetModel

object CarList {
    val mercedesModels = listOf(
        BottomSheetModel(title = "E-class"),
        BottomSheetModel(title = "A-class"),
        BottomSheetModel(title = "GT-class"),
        BottomSheetModel(title = "B-class"),
        BottomSheetModel(title = "S-class"),
        BottomSheetModel(title = "G-class"),
        BottomSheetModel(title = "CLS-class"),
    )
    val audiModels = listOf(
        BottomSheetModel(title = "TT"),
        BottomSheetModel(title = "S1"),
        BottomSheetModel(title = "S2"),
        BottomSheetModel(title = "S3"),
        BottomSheetModel(title = "A6"),
        BottomSheetModel(title = "A4"),
        BottomSheetModel(title = "RS6"),
    )
    val nissanModels = listOf(
        BottomSheetModel(title = "Skyline"),
        BottomSheetModel(title = "350z"),
        BottomSheetModel(title = "370z"),
        BottomSheetModel(title = "Cube"),
        BottomSheetModel(title = "Silvia"),
        BottomSheetModel(title = "X-Terra"),
    )
    val yearsList = listOf(
        BottomSheetModel(title = "1999"),
        BottomSheetModel(title = "2000"),
        BottomSheetModel(title = "2001"),
        BottomSheetModel(title = "2002"),
        BottomSheetModel(title = "2003"),
        BottomSheetModel(title = "2004"),
        BottomSheetModel(title = "2005"),
        BottomSheetModel(title = "2006"),
        BottomSheetModel(title = "2007"),
        BottomSheetModel(title = "2008"),
        BottomSheetModel(title = "2009"),
        BottomSheetModel(title = "2010"),
        BottomSheetModel(title = "2011"),
        BottomSheetModel(title = "2012"),
        BottomSheetModel(title = "2013"),
        BottomSheetModel(title = "2014"),
        BottomSheetModel(title = "2015"),
        BottomSheetModel(title = "2016"),
        BottomSheetModel(title = "2017"),
        BottomSheetModel(title = "2018"),
    )
}

enum class CarManufactures(val value: Int, val title: String) {
    MERCEDES(1, "Mercedes-Benz"), AUDI(2, "Audi"), NISSAN(3, "Nissan")
}