package com.chaimaerazzouki.coinscious.core.utils

import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@JvmInline
value class Money(val cents: Long) {

    constructor(amount: Double) : this((amount * 100).toLong())

    val value: Double get() = cents / 100.0

    operator fun plus(other: Money): Money = Money(cents + other.cents)
    operator fun minus(other: Money): Money = Money(cents - other.cents)
    operator fun compareTo(other: Money): Int = cents.compareTo(other.cents)

    fun isZero(): Boolean = cents == 0L

    fun format(currencyCode: String = "USD"): String {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.currency = Currency.getInstance(currencyCode)
        return format.format(value)
    }

    companion object {
        val ZERO = Money(0)
    }
}