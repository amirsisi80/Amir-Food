package com.example.amirfoodapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FoodAdapter(
    private val data: ArrayList<Food>,
    private val sendDataIntoMain: send_data_into_main
) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {


    inner class FoodViewHolder(ItemView: View, private val context: Context) :
        RecyclerView.ViewHolder(ItemView) {
        val imagemain = ItemView.findViewById<ImageView>(R.id.imageViewMain)
        val textsubject = ItemView.findViewById<TextView>(R.id.textViewSubject)
        val food = ItemView.findViewById<TextView>(R.id.textViewFood)
        val price = ItemView.findViewById<TextView>(R.id.textViewPrice)
        val distance = ItemView.findViewById<TextView>(R.id.textViewDistance)
        val textratings = ItemView.findViewById<TextView>(R.id.textViewRatings)
        val ratingbar = ItemView.findViewById<RatingBar>(R.id.ratingBar)

        @SuppressLint("SetTextI18n")
        fun binddata(position: Int) {
            textsubject.text = data[position].textsubject
            food.text = data[position].textfood
            price.text = "$" + data[position].textprice + "vip"
            distance.text = data[position].textdistance + " k/m from you"
            ratingbar.rating = data[position].ratingbar
            textratings.text = "(Ratings " + data[position].taxtratings.toString() + ")"

            Glide.with(context)
                .load(data[position].imagemain)
                .transform(RoundedCornersTransformation(20, 8))
                .into(imagemain)



            itemView.setOnClickListener {
                sendDataIntoMain.click(adapterPosition,data[adapterPosition])
            }

            itemView.setOnLongClickListener {

                sendDataIntoMain.lungclick(data[adapterPosition],adapterPosition)

                true

            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_view, parent, false)
        return FoodViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return data.size

    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        holder.binddata(position)

    }

    fun addfood(new_food: Food) {
        data.add(0, new_food)
        notifyItemInserted(0)
    }

    fun delet_food(view: Food, position: Int) {
        data.remove(view)
        notifyItemRemoved(position)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun opdate_food(position: Int, food:Food ){
        data.set(position,food)
        notifyItemChanged(position)
    }



    @SuppressLint("NotifyDataSetChanged")
    fun New_List_Food (newListFood:ArrayList<Food>){
        data.clear()
        data.addAll(newListFood)
        notifyDataSetChanged()


    }


    interface send_data_into_main {

        fun click(position: Int,food: Food)
        fun lungclick(food: Food, pos: Int)

    }

}



