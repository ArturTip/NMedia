package ru.netology.nmedia.dto

import java.text.DecimalFormat
import kotlin.math.floor

data class Post( val id: Long,
                 val author: String,
                 val content: String,
                 val published: String,
                 val repost: Int = 998,
                 val likes: Int = 0,
                 val likedByMe: Boolean = false
)


