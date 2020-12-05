package br.com.house.digital.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.house.digital.R
import br.com.house.digital.model.Comic
import com.bumptech.glide.Glide

class AdapterComics(
    val onClickListener: IOnClickListenerComic,
) : RecyclerView.Adapter<AdapterComics.ComicsViewHolder>() {
    val listComics = ArrayList<Comic>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_grid_comics, parent, false)
        return ComicsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        val item = listComics[position]

        holder.textViewThumbnail.text = "#${item.id}"

        val circularProgressDrawable = CircularProgressDrawable(holder.imageViewThumbnail.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(holder.itemView.context).asBitmap()
            .load(item.thumbnail.getUrl())
            .placeholder(circularProgressDrawable)
            .into(holder.imageViewThumbnail)
    }

    override fun getItemCount() = listComics.size

    inner class ComicsViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }

        val imageViewThumbnail: ImageView = view.findViewById(R.id.imageView_thumbnail_comic)
        val textViewThumbnail: TextView = view.findViewById(R.id.textView_thumbnail_comic)

        override fun onClick(v: View?) {
            val position = adapterPosition

            if (position != RecyclerView.NO_POSITION) {
                onClickListener.onClickListenerComic(position)
            }
        }
    }

    fun addList(comic: ArrayList<Comic>) {
        listComics.addAll(comic)
        notifyDataSetChanged()
    }

    interface IOnClickListenerComic {
        fun onClickListenerComic(item: Int)
    }
}