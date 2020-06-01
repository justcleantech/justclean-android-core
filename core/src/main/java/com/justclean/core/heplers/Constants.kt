package com.justclean.core.heplers

object Constants {
    enum class Payment(val id: Int) {
        CASH_ON_DELIVERY(1),
        KNET(2),
        JC_CREDIT(3),
        GIFT(4),
        CASH_BACK(14),
        PAY_BY_OOREDOO(7),
        TAP_KNET(9),
        CREDIT_CARD(10),
        BENEFIT(11),
        MADA(12),
        REFERRAL(15),
        CHECKOUT_CREDIT_CARD(16),
        CHECKOUT_KNET(17),
        CHECKOUT_BENEFIT(18),
        CHECKOUT_MADA(19),
        CHECKOUT_QPAY(20)
    }

    enum class Fee(val id: Int) {
        DELIVERY_FEE(102),
        SERVICE_FEE(103)
    }

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
}