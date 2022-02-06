package ru.tinkoff.android.ashihmin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.tinkoff.android.ashihmin.R
import ru.tinkoff.android.ashihmin.api.NetworkService
import ru.tinkoff.android.ashihmin.model.GifImage

class RandomGifFragment : Fragment() {
    lateinit var prevButton: FloatingActionButton
    lateinit var nextButton: FloatingActionButton
    lateinit var progressBar: ProgressBar
    lateinit var descriptionTextView: TextView

    companion object {
        var currentGifUrlPosition = 0
        var loadedGifs = ArrayList<GifImage>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_gif, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nextButton = view.findViewById(R.id.next_btn)
        prevButton = view.findViewById(R.id.prev_btn)
        prevButton.isEnabled = false
        progressBar = view.findViewById(R.id.progress)
        descriptionTextView = view.findViewById(R.id.gif_description)

        this.initListeners(view)
        this.downloadRandomImage(view)
    }

    private fun initListeners(view: View) {
        nextButton.setOnClickListener {
            currentGifUrlPosition++
            if (loadedGifs.size <= currentGifUrlPosition) {
                this.downloadRandomImage(view)
            } else {
                val gif = loadedGifs[currentGifUrlPosition]
                this.fillImageView(view, gif.gifURL, gif.description)
            }
            prevButton.isEnabled = true
        }

        prevButton.setOnClickListener {
            currentGifUrlPosition--
            this.downloadImageFromCache(view)
        }
    }

    private fun downloadImageFromCache(view: View) {
        val gifImageView = view.findViewById<ImageView>(R.id.gif_image)
        val gif = loadedGifs[currentGifUrlPosition]
        Glide.with(view)
            .asGif()
            .load(gif.gifURL)
            .onlyRetrieveFromCache(true)
            .error(R.drawable.baseline_cloud_off_24)
            .into(gifImageView)
        descriptionTextView.text = gif.description
        if (currentGifUrlPosition == 0) {
            prevButton.isEnabled = false
        }
    }

    private fun downloadRandomImage(view: View) {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.getRandomImage()?.enqueue(object : Callback<GifImage> {
                override fun onFailure(call: Call<GifImage>, t: Throwable) {
                    Toast.makeText(context, "Произошла ошибка при загрузке данных", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                    descriptionTextView.text = ""
                }

                override fun onResponse(
                    call: Call<GifImage>,
                    response: Response<GifImage>
                ) {
                    val gif = response.body()
                    fillImageView(view, gif?.gifURL ?: "", gif?.description ?: "")
                    loadedGifs.add(response.body()!!)
                }
            })
    }

    private fun fillImageView(view: View, gifUrl: String, gifDescription: String) {
        progressBar.visibility = View.VISIBLE
        val gifImageView = view.findViewById<ImageView>(R.id.gif_image)
        Glide.with(view)
            .asGif()
            .load(gifUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(context, "Произошла ошибка при загрузке данных", Toast.LENGTH_SHORT).show()
                    progressBar.visibility = View.GONE
                    descriptionTextView.text = ""
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    descriptionTextView.text = gifDescription
                    return false
                }

            })
            .error(R.drawable.baseline_cloud_off_24)
            .into(gifImageView)
    }

}