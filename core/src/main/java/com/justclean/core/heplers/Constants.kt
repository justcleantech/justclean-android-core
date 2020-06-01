package com.justclean.core.heplers

object Constants {

    enum class CountryCode(val code: String) {
        KUWAIT("+965"),
        UAE("+971"),
        BAHRAIN("+973"),
        KSA("+966"),
        QATAR("+974")
    }

    enum class Country(val code: String) {
        KUWAIT("KW"),
        UAE("AE"),
        BAHRAIN("BH"),
        KSA("SA"),
        QATAR("QA")
    }

    enum class CountryId(val id: Int) {
        KUWAIT(1),
        UAE(2),
        BAHRAIN(3),
        KSA(4),
        QATAR(5)
    }

    enum class Language(val language:String){
        ENGLISH("en"),
        ARABIC("ar"),
    }
    enum class LanguageId(val id:Int){
        ENGLISH(1),
        ARABIC(2),
    }
}