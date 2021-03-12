package com.asp.careernettest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asp.careernettest.R
import com.asp.careernettest.ext.loadUrl
import com.asp.careernettest.listener.OnItemClickListener
import com.asp.domain.model.CountryModel

class CountryAdapter(private var content: ArrayList<CountryModel>, private val listener: OnItemClickListener<CountryModel>): RecyclerView.Adapter<CountryAdapter.ViewHolder>(), Filterable {

    private val originalList = mutableListOf<CountryModel>().apply {
        addAll(content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_item_country, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.flag.setImageDrawable(null)

        holder.country.text = content[position].name
        holder.capital.text = content[position].capital
        holder.region.text = content[position].region
        holder.population.text = content[position].population.toString()
        holder.area.text = content[position].area.toString()

        holder.flag.loadUrl(content[position].flag, R.drawable.ic_broken_image_24)

        holder.itemView.setOnClickListener {
            listener?.onClick(position, content[position])
        }
    }

    override fun getItemCount(): Int {
        return content.size
    }

    override fun getFilter(): Filter {
        return CountryNameFilter()
    }

    fun updateCountries(list: ArrayList<CountryModel>){
        content.clear()
        content.addAll(list)
        originalList.addAll(content)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        var country: TextView = view.findViewById(R.id.name)
        var capital: TextView = view.findViewById(R.id.capital)
        var region: TextView = view.findViewById(R.id.region)
        var population: TextView = view.findViewById(R.id.population)
        var area: TextView = view.findViewById(R.id.area)
        var flag: ImageView = view.findViewById(R.id.flag)
    }

    inner class CountryNameFilter(): Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val tempList = mutableListOf<CountryModel>()

            if(constraint.isBlank()){
                tempList.addAll(originalList)
            }else{
                originalList.forEach {model ->
                    if(model.name.contains(constraint.toString(), true))
                        tempList.add(model)
                }
            }

            return FilterResults().apply {
                values = ArrayList(tempList)
                count = tempList.size
            }
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            content = results.values as ArrayList<CountryModel>
            notifyDataSetChanged()
        }
    }
}