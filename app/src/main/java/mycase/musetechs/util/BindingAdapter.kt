package mycase.musetechs.util


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


object BindingAdapter {

    @BindingAdapter("setImageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }
}