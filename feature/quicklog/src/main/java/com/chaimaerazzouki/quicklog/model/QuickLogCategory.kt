package com.chaimaerazzouki.quicklog.model

data class QuickLogCategory(
    val id: String,
    val name: String
)

val quickLogCategories = listOf(
    QuickLogCategory(
        id = "food",
        name = "Food"
    ),
    QuickLogCategory(
        id = "transport",
        name = "Transport"
    ),
    QuickLogCategory(
        id = "bills",
        name = "Bills"
    ),
    QuickLogCategory(
        id = "shopping",
        name = "Shopping"
    ),
    QuickLogCategory(
        id = "health",
        name = "Health"
    ),
    QuickLogCategory(
        id = "entertainment",
        name = "Entertainment"
    ),
    QuickLogCategory(
        id = "education",
        name = "Education"
    ),
    QuickLogCategory(
        id = "income",
        name = "Income"
    ),
    QuickLogCategory(
        id = "other",
        name = "Other"
    )
)