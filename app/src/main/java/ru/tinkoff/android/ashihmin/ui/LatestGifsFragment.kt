package ru.tinkoff.android.ashihmin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.tinkoff.android.ashihmin.R
import ru.tinkoff.android.ashihmin.api.NetworkService
import ru.tinkoff.android.ashihmin.model.GifImageList

class LatestGifsFragment : Fragment() {
    val data = MutableLiveData<GifImageList>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_latest_gifs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.latest_gifs_recycler)

        downloadLatestGifsList()

        recycler.layoutManager = GridLayoutManager(activity, 1)

        data.observe(this) { gifImageList ->
            val adapter = CaptionedImagesAdapter(gifImageList.list)
            recycler.adapter = adapter
        }
    }

    private fun downloadLatestGifsList() {
        NetworkService.getInstance()
            ?.getJSONApi()
            ?.getLatestImagesList()?.enqueue(object : Callback<GifImageList> {
                override fun onFailure(call: Call<GifImageList>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Произошла ошибка при загрузке данных",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<GifImageList>,
                    response: Response<GifImageList>
                ) {
                    data.postValue(response.body())
                }
            })
    }
}