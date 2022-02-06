package ru.tinkoff.android.ashihmin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.tinkoff.android.ashihmin.R
import ru.tinkoff.android.ashihmin.model.GifImage

class CaptionedImagesAdapter(
    var loadedGigs: List<GifImage>,
) : RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>() {

    class ViewHolder(cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun getItemCount(): Int {
        return loadedGigs.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_captioned_image, parent, false) as CardView
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.itemView
        val gifImageView = cardView.findViewById<ImageView>(R.id.gif_image)

        Glide.with(cardView)
            .asGif()
            .load(loadedGigs[position].gifURL)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .error(R.drawable.baseline_cloud_off_24)
            .into(gifImageView)

        val textView = cardView.findViewById<TextView>(R.id.gif_text)
        textView.text = loadedGigs[position].description
    }

}