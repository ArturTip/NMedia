package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = 1239,
            likedByMe = false
        )
        with(binding) {
            autor.text = post.author
            content.text = post.content
            published.text = post.published
            likeQuantity.text = formatCount(post.likes)
            repostQuantity.text = formatCount(post.repost)
            like.setImageResource(R.drawable.ic_like_24)
            like.setOnClickListener {
                println("сработал like")
                post.likedByMe = !post.likedByMe
                if (post.likedByMe) {
                    like.setImageResource(R.drawable.ic_liked_24)
                    post.likes++
                    likeQuantity.text = formatCount(post.likes)

                } else {
                    like.setImageResource(R.drawable.ic_like_24)
                    post.likes--
                    likeQuantity.text = formatCount(post.likes)
                }
            }
            repost.setOnClickListener {
                post.repost += 10
                repostQuantity.text = formatCount(post.repost)
            }
        }
    }
}


fun formatCount(count: Int): String {
    val df = DecimalFormat("#.0")
    return when {
        count < 1000 -> "$count"
        count < 10000 -> df.format((count / 1000.0)).replace(",0", "") + "K"
        count < 1000000 -> "${count / 1000}K"
        count < 10000000 -> df.format(count / 1000000.0).replace(",0", "") + "M"
        else -> "${count / 1000000}M"

    }
}


