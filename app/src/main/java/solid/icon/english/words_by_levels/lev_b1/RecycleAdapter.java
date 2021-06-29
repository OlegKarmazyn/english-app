package solid.icon.english.words_by_levels.lev_b1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import solid.icon.english.R;
import solid.icon.english.words_by_levels.study_way.MainStudyAction;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    Context context; String[] name_topic;
    int[] key_topics;

    public RecycleAdapter(Context context, String[] name_topic, int [] key_topics) {
        this.context = context;
        this.name_topic = name_topic;
        this.key_topics = key_topics;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecycleAdapter.MyViewHolder holder, int position) {
        holder.b1_tema_0.setText(name_topic[position]);


            if (key_topics[position] == 1) {
//                Log.d("RecycleAdapter", "position = " + position + " thue " + " key_topic[position] = " + key_topics[position]);
                holder.b1_checkBox_0.setChecked(true);
            }else {
                holder.b1_checkBox_0.setChecked(false);
            }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainStudyAction.class);
                intent.putExtra("num_of_topic", position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name_topic.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView b1_tema_0; CheckBox b1_checkBox_0; CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            b1_tema_0 = itemView.findViewById(R.id.b1_tema_0);
            b1_checkBox_0 = itemView.findViewById(R.id.b1_checkBox_0);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
