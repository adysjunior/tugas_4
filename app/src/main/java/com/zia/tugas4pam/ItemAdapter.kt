package com.zia.tugas4pam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val itemList: ArrayList<Student>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(R.id.tvName)
        val textViewNim: TextView = itemView.findViewById(R.id.tvNim)
        val textViewJenisKelamin: TextView = itemView.findViewById(R.id.tvJenisKelamin)
        val ivStudent: ImageView = itemView.findViewById(R.id.ivStudent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val student = itemList[position]
        holder.textViewName.text = String.format("Nama: %s", student.name)
        holder.textViewNim.text = String.format("NIM: %s", student.nim)
        holder.textViewJenisKelamin.text = String.format("Jenis Kelamin: %s", student.jenis_kelamin)

        if (holder.textViewJenisKelamin.text == "Laki-laki") {
            holder.ivStudent.setImageResource(R.drawable.avatar_male)
        }else{
           holder.ivStudent.setImageResource(R.drawable.avatar_female)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(student: Student) {
        itemList.add(student)
        notifyItemInserted(itemList.size - 1)
    }
}
