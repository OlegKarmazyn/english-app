package solid.icon.english.words_by_levels.universal_topic_level;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import solid.icon.english.R;
import solid.icon.english.db_pac.DBmoveINFO;
import solid.icon.english.words_by_levels.study_way.MainStudyAction;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context context;
    private String[] name_topic;
    private int[] key_topics;
    private EnglishLevel englishLevel;

    public RecycleAdapter(Context context, String[] name_topic, int [] key_topics, EnglishLevel englishLevel) {
        this.context = context;
        this.name_topic = name_topic;
        this.key_topics = key_topics;
        this.englishLevel = englishLevel;
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

        holder.b1_checkBox_0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DBmoveINFO dBmoveINFO = new DBmoveINFO(context);
                if(isChecked) {
                    Log.d("Recycle", "b1_checkBox_1 - onCheckedChanged - if - true");
                    dBmoveINFO.go_check_info(position, EnglishLevel.which_KNOW_TOPIC);
                    holder.b1_tema_0.setPaintFlags(holder.b1_tema_0.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    key_topics[position] = 1;
                }
                else {
                    holder.b1_tema_0.setPaintFlags(holder.b1_tema_0.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    dBmoveINFO.delete_check_info(position, EnglishLevel.which_KNOW_TOPIC);
                    key_topics[position] = 0;
                }
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.cardView.setBackgroundColor(R.color.colorLightBlue);
                Intent intent = new Intent(context, MainStudyAction.class);
                intent.putExtra("num_of_topic", position);
                context.startActivity(intent);
                englishLevel.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
            }
        });

        if (key_topics[position] == 1) {
//          Log.d("RecycleAdapter", "position = " + position + " thue " + " key_topic[position] = " + key_topics[position]);
            holder.b1_checkBox_0.setChecked(true);
//            holder.b1_tema_0.setPaintFlags(holder.b1_tema_0.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            holder.b1_checkBox_0.setChecked(false);
//            holder.b1_tema_0.setPaintFlags(holder.b1_tema_0.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
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
