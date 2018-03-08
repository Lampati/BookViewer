package com.lampati.bookviewer.bookList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.lampati.bookviewer.R
import com.lampati.bookviewer.bookList.entities.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_book.view.*
import java.util.ArrayList
import android.widget.TextView
import java.net.URLDecoder
import java.net.URLEncoder


/**
 * Created by FEDE on 7/3/2018.
 */

class BookAdapter (private val context: Context, private var list: List<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val inflator: LayoutInflater

    init {
        this.inflator = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_book, parent, false)
        return BookViewHolder(v)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun refrehList(newList: List<Book>) {
        list = newList
        notifyDataSetChanged()
    }

    inner class BookViewHolder(private var view: View) : RecyclerView.ViewHolder(view){
        fun bind(row : Book){
            view.row_book_title.text = row.title
            Picasso.with(context).load(row.imageURL).into(view.row_book_image)
            view.row_book_author.text = row.author
        }
    }
}