package com.chaimaerazzouki.coinscious.core.utils

import android.content.Context
import com.chaimaerazzouki.coinscious.R

// String Resource Helper to avoid typing R.string everywhere
object Strings {

    // Encouragement
    fun getEncouragement(context: Context, isHealthy: Boolean, isCaution: Boolean, isCritical: Boolean): String {
        return when {
            isCritical -> context.getString(R.string.encouragement_critical)
            isCaution -> context.getString(R.string.encouragement_caution)
            isHealthy -> context.getString(R.string.encouragement_healthy)
            else -> context.getString(R.string.encouragement_on_track)
        }
    }

    // Category labels
    fun getCategoryLabel(context: Context, categoryName: String): String {
        return when (categoryName.uppercase()) {
            "FOOD" -> context.getString(R.string.cat_food)
            "TRANSPORT" -> context.getString(R.string.cat_transport)
            "SHOPPING" -> context.getString(R.string.cat_shopping)
            "ENTERTAINMENT" -> context.getString(R.string.cat_entertainment)
            "BILLS" -> context.getString(R.string.cat_bills)
            "HEALTH" -> context.getString(R.string.cat_health)
            "EDUCATION" -> context.getString(R.string.cat_education)
            "SALARY" -> context.getString(R.string.cat_salary)
            "INVESTMENT" -> context.getString(R.string.cat_investment)
            else -> context.getString(R.string.cat_other)
        }
    }

    // Format with arguments
    fun getProgressLabel(context: Context, percentage: Int): String {
        return context.getString(R.string.dashboard_progress_label, percentage)
    }

    fun getDaysLeft(context: Context, days: Int): String {
        return context.getString(R.string.dashboard_days_left, days)
    }

    fun getChartAverage(context: Context, amount: String): String {
        return context.getString(R.string.chart_average, amount)
    }
}
