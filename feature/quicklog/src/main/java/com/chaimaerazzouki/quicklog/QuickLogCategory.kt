package com.chaimaerazzouki.quicklog

data class QuickLogCategory(
    val id: String,
    val name: String
)

val quickLogCategories = listOf(
    QuickLogCategory(
        id = QuickLogViewModel.CATEGORY_FOOD,
        name = "Food"
    ),
    QuickLogCategory(
        id = QuickLogViewModel.CATEGORY_TRANSPORT,
        name = "Transport"
    ),
    QuickLogCategory(
        id = QuickLogViewModel.CATEGORY_BILLS,
        name = "Bills"
    ),
    QuickLogCategory(
        id = QuickLogViewModel.CATEGORY_SHOPPING,
        name = "Shopping"
    ),
    QuickLogCategory(
        id = QuickLogViewModel.CATEGORY_HEALTH,
        name = "Health"
    ),
    QuickLogCategory(
        id = QuickLogViewModel.CATEGORY_ENTERTAINMENT,
        name = "Entertainment"
    ),
    QuickLogCategory(
        id = QuickLogViewModel.CATEGORY_EDUCATION,
        name = "Education"
    ),
    QuickLogCategory(
        id = QuickLogViewModel.CATEGORY_INCOME,
        name = "Income"
    ),
    QuickLogCategory(
        id = QuickLogViewModel.CATEGORY_OTHER,
        name = "Other"
    )
)
