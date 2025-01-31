package com.example.amirfoodapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AlertDialogLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.amirfoodapp.databinding.ActivityMainBinding
import com.example.amirfoodapp.databinding.DialogAddFoodBinding
import com.example.amirfoodapp.databinding.DialogDeletBinding
import com.example.amirfoodapp.databinding.DialogUpdateFoodBinding

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity(), FoodAdapter.send_data_into_main {
    lateinit var foodAdapter: FoodAdapter
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listfood = arrayListOf<Food>(
            Food(
                "ghorme sabzi",
                "iran",
                "10",
                "1.2",
                90,
                4.1F,
                "https://last-cdn.com/2024/01/03/FsmToF0yjCbT2LEPiZhmgfhF4wNcqKSPpWh5dF4U.jpg"
            ),
            Food(
                "gheymeh",
                "iran",
                "8",
                "1",
                80,
                4.0F,
                "https://last-cdn.com/2024/01/03/YTRBXvrxwjcTrOCCmLI9P9zRp6FPi3N4GInQ39QD.jpg"

            ),
            Food(
                "dolme",
                "iran",
                "5",
                "1.3",
                60,
                2.3F,
                "https://last-cdn.com/2024/01/04/7huSLicltUsPQkETtlLRISxtjPbWaxPZIpoG1C8A.jpg"
            ),
            Food(
                "fesenjan",
                "iran",
                "12",
                "1.8",
                97,
                4.5F,
                "https://last-cdn.com/2024/01/03/L09k1EHHeb5rmXAD8VpChKonwh9zjaGG9Qgs8gNf.jpg"
            ),
            Food(
                "lobia polo",
                "iran",
                "6",
                "0.8",
                40,
                3.5F,
                "https://last-cdn.com/2024/01/03/m8rOeVPyOncB24WBuDoppXBvjC3sqveu7MzzQYn1.jpg"
            ),
            Food(
                "baghali polo",
                "iran",
                "11",
                "2.0",
                98,
                4.7F,
                "https://last-cdn.com/2024/01/03/heV7SCjL4izdBp6S5xct7PBYLC36iDi2etZMbnGj.jpg"
            ),
            Food(
                "kashk bademjan",
                "iran",
                "7.2",
                "0.4",
                67,
                3.6F,
                "https://last-cdn.com/2024/01/04/3cr0mHQ5zadLwJY4RKc9Uz29Vi7RxA0sqJQxccvT.jpg"
            ),
            Food(
                "sabzi polo ba mahi",
                "iran",
                "11",
                "1.4",
                85,
                4.0F,
                "https://last-cdn.com/2024/01/03/tffKIKZgTnGhdFyvqJCbwIJTQVbBVozVCEGo6SnX.jpg"
            ),
            Food(
                "zereshk polo",
                "iran",
                "10",
                "1.5",
                90,
                4.1F,
                "https://last-cdn.com/2024/01/03/XlzceZUOguzOdrQvIdjuAqfbBcEhgSw5SCrDyQUj.jpg"
            ),
            Food(
                "kabab Kobide",
                "iran",
                "13",
                "1.7",
                97,
                4.7F,
                "https://last-cdn.com/2024/01/04/8pD3rTMsTJA8MQYhqJsr4mTYBFF9eNRpCASJuxwn.jpg"
            ),
            Food(
                "abgoosht iran",
                "iran",
                "10",
                "1.0",
                80,
                5F,
                "https://last-cdn.com/2024/01/04/Q8k1Y4UF0x2rh0b6oHivHmvWJBPL5XyZlvB8VSEJ.jpg"
            ),
            Food(
                "koko sabzi",
                "iran",
                "3.5",
                "1.9",
                32,
                2F,
                "https://last-cdn.com/2024/01/04/AuOjF2z4VW2rV0RaWKbyYH4lQjm3bZeQqtP7T0CX.jpg"
            )


        )

        foodAdapter = FoodAdapter(listfood.clone() as ArrayList<Food>, sendDataIntoMain = this)

        binding.recyclerviewMain.adapter = foodAdapter

        binding.recyclerviewMain.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)








        binding.addBtn.setOnClickListener {

            val dialog = AlertDialog.Builder(this).create()
            val bindingviewadd = DialogAddFoodBinding.inflate(layoutInflater)
            dialog.setView(bindingviewadd.root)
            dialog.setCancelable(true)
            dialog.show()

            bindingviewadd.btnDone.setOnClickListener {

                if (bindingviewadd.foodName.length() > 0 &&
                    bindingviewadd.foodCity.length() > 0 &&
                    bindingviewadd.price.length() > 0 &&
                    bindingviewadd.distance.length() > 0
                ) {

                    val nam_food = bindingviewadd.foodName.text.toString()
                    val name_city = bindingviewadd.foodCity.text.toString()
                    val price = bindingviewadd.price.text.toString()
                    val Ditsance = bindingviewadd.distance.text.toString()
                    val text_rating: Int = (1..110).random()
                    val ratingBar: Float = (1..5).random().toFloat()
                    val urlrandom = (0..11).random()
                    val pic_url = listfood[urlrandom].imagemain

                    val new_Food =
                        Food(nam_food, name_city, price, Ditsance, text_rating, ratingBar, pic_url)

                    foodAdapter.addfood(new_Food)
                    dialog.dismiss()
                    binding.recyclerviewMain.smoothScrollToPosition(0)

                } else {
                    Toast.makeText(this, "لطفا مقادیر را وارد کنید (:", Toast.LENGTH_LONG).show()
                }


            }

            binding.findFood.addTextChangedListener { data_search ->

                if (data_search!!.isNotEmpty()) {

                    val clone_list2 = listfood.clone() as ArrayList<Food>

                    val list_search = clone_list2.filter { fooditem->

                    fooditem.textsubject.contains(data_search)
                    }

                    foodAdapter.New_List_Food(ArrayList(list_search))

                } else {

                    val clone_list = listfood.clone() as ArrayList<Food>
                    foodAdapter.New_List_Food(clone_list)

                }
            }

        }


    }


    override fun click(position: Int, food: Food) {

        val dialog = AlertDialog.Builder(this).create()
        val dialog_update = DialogUpdateFoodBinding.inflate(layoutInflater)
        dialog.setView(dialog_update.root)
        dialog.setCancelable(true)
        dialog.show()


        dialog_update.foodName.setText(food.textsubject)
        dialog_update.foodCity.setText(food.textfood)
        dialog_update.price.setText(food.textprice)
        dialog_update.distance.setText(food.textdistance)



        dialog_update.btnCancelUpdate.setOnClickListener {
            dialog.dismiss()
        }



        dialog_update.btnDoneUpdate.setOnClickListener {

            if (dialog_update.foodName.length() > 0 &&
                dialog_update.foodCity.length() > 0 &&
                dialog_update.price.length() > 0 &&
                dialog_update.distance.length() > 0
            ) {
                val nam_food = dialog_update.foodName.text.toString()
                val name_city = dialog_update.foodCity.text.toString()
                val price = dialog_update.price.text.toString()
                val Ditsance = dialog_update.distance.text.toString()

                val new_food = Food(
                    nam_food,
                    name_city,
                    price,
                    Ditsance,
                    food.taxtratings,
                    food.ratingbar,
                    food.imagemain
                )
                foodAdapter.opdate_food(position, new_food)



                dialog.dismiss()


            } else {
                Toast.makeText(this, "لطفا مقادیر را وارد کنید", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun lungclick(food: Food, pos: Int) {

        val dialog = AlertDialog.Builder(this).create()
        val viewdialog_delet = DialogDeletBinding.inflate(layoutInflater)
        dialog.setView(viewdialog_delet.root)
        dialog.setCancelable(true)
        dialog.show()


        viewdialog_delet.btnDialogdeletNo.setOnClickListener {
            dialog.dismiss()
        }

        viewdialog_delet.btnDialogdeletYes.setOnClickListener {

            dialog.dismiss()

            foodAdapter.delet_food(food, pos)

        }


    }


}




