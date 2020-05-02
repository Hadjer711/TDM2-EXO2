package com.example.tdm2exo2

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.tdm2exo2.R

class InterventionAdapter(val activity: MainActivity,var clickListner: OnIntervListener) : RecyclerView.Adapter<InterventionAdapter.TachViewHolder>() {


    class TachViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val IntervDate = v.findViewById<TextView>(R.id.intervDateView)
        val IntervType = v.findViewById<TextView>(R.id.IntervTypeView)
        val NamePlo =v.findViewById<TextView>(R.id.IntervNomView)
        val tacheLayout = v.findViewById<RelativeLayout>(R.id.tacheLayoutView)
        fun initialize( item:Intervention, action: OnIntervListener){

            IntervDate.text = item.date.toString()
            IntervType.text = item.type
            NamePlo.text = item.nom
            tacheLayout.setOnClickListener {
                action.OnItemClick(item,adapterPosition)
            }

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TachViewHolder {
        return TachViewHolder(
            LayoutInflater.from(activity).inflate(R.layout.interv_view, parent, false
            ))
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: TachViewHolder, position: Int) {

        holder.initialize(activity.interventionList!!.get(position),clickListner)



    }
    public interface OnIntervListener{
        fun OnItemClick(item:Intervention,position: Int)
    }


}


