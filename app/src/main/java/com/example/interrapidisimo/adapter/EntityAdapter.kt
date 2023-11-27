package com.example.interrapidisimo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interrapidisimo.data.TableData
import com.example.interrapidisimo.databinding.EntityItemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class EntityAdapter(
    private var tableData: List<TableData>
) : RecyclerView.Adapter<EntityAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = EntityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tableData[position])
    }

    override fun getItemCount(): Int {
        return tableData.size
    }

    inner class ViewHolder(private val binding: EntityItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tableData: TableData) {
            binding.tittleTableTextView.text = tableData.name
            binding.dataOneTextView.text = tableData.primaryKey
            binding.dataTwoTextView.text = tableData.size
            binding.dataThreeTextView.text = tableData.field
            binding.dataFourTextView.text = formatterDate(tableData.updateDate)
        }

        /**
         * Formatea una cadena de fecha eliminando los milisegundos y cambiando su formato a "dd MMMM yyyy HH:mm:ss" en español
         */
        private fun formatterDate(date: String): String{
            val withoutMilliseconds = date.substring(0, date.indexOf('.'))
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
            val dateFormatter = LocalDateTime.parse(withoutMilliseconds, formatter)
            val newFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss", Locale("es", "ES"))
            return dateFormatter.format(newFormatter)
        }
    }

}