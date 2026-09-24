package com.chaimaerazzouki.ui.component

import androidx.annotation.DrawableRes
import com.chaimaerazzouki.ui.R

object CategoryIcon {

    @DrawableRes
    fun forCategory(categoryId: String): Int? {
        return when (categoryId) {
            "food" -> R.drawable.ic_category_food
            "transport" -> R.drawable.ic_category_transport
            "bills" -> R.drawable.ic_category_bills
            "shopping" -> R.drawable.ic_category_shopping
            "health" -> R.drawable.ic_category_health
            "entertainment" -> R.drawable.ic_category_entertainment
            "education" -> R.drawable.ic_category_education
            "income" -> R.drawable.ic_category_income
            else -> null
        }
    }
}