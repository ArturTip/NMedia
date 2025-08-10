package ru.netology.nmedia.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

import ru.netology.nmedia.viewmodel.PostViewModel
import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log

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
        Log.d("MainActivity", "${this.hashCode()}")
        val viewModel: PostViewModel by viewModels()

        viewModel.data.observe(this) { post ->
            with(binding) {
                autor.text = post.author
                content.text = post.content
                published.text = post.published
                likeCount.text = formatCount(post.likes)
                repostCount.text = formatCount(post.repost)
                if (post.likedByMe) {
                    like.setImageResource(R.drawable.ic_liked_24)
                } else
                    like.setImageResource(R.drawable.ic_like_24)
            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }
        binding.repost.setOnClickListener {
            viewModel.repost()
        }
    }
}




