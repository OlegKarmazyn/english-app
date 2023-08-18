package solid.icon.english.navigation_menu.account.profile.budget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import solid.icon.english.R
import solid.icon.english.navigation_menu.account.profile.budget.firebase.HistoryModel

class HistoryAdapter(
    var listItems: List<HistoryModel>
) : RecyclerView.Adapter<HistoryAdapter.MyHolder>() {

    companion object {
        private const val TAG = "AccountAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_cell, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val model = listItems[position]

        holder.itemView.findViewById<TextView>(R.id.description).text = model.description
        holder.itemView.findViewById<TextView>(R.id.date).text = model.date
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}