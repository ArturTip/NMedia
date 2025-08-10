package ru.netology.nmedia.activity

import java.text.DecimalFormat
import kotlin.math.floor

fun formatCount(count: Int): String {
    val df = DecimalFormat("#.0")
    return when {
        count < 1000 -> "$count"
        count < 10000 -> {
            val rounded = floor((count / 100.0)) / 10
            df.format(rounded).replace(",0", "") + "K"
        }
        count < 1000000 -> "${floor(count / 1000.0).toInt()}K"
        count < 10000000 -> {
            val rounded = floor((count / 100000.0)) / 10
            df.format(rounded).replace(",0", "") + "M"
        }
        else -> "${floor(count / 1000000.0).toInt()}M"
    }
}