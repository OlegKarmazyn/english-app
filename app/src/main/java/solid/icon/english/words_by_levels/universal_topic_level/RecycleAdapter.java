package solid.icon.english.words_by_levels.universal_topic_level;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skydoves.elasticviews.ElasticAnimation;
import com.skydoves.elasticviews.ElasticFinishListener;

import org.jetbrains.annotations.NotNull;

import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.words_by_levels.study_way.MainStudyAction;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context context;
    private String[] name_topic;
    private boolean[] key_topics;
    private EnglishLevel englishLevel;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public RecycleAdapter(Context context, String[] name_topic, boolean [] key_topics, EnglishLevel englishLevel) {
        this.context = context;
        this.name_topic = name_topic;
        this.key_topics = key_topics;
        this.englishLevel = englishLevel;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
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
    public void onBindViewHolder(@NonNull @NotNull RecycleAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(name_topic[position]);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String mod_key = String.valueOf(englishLevel.level) + position;
                Log.e("onBindViewHolder", mod_key + " - " + isChecked);
                if(isChecked) {
                    holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    editor.putBoolean(mod_key, isChecked);
                    editor.apply();
                }
                else {
                    holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                    editor.putBoolean(mod_key, isChecked);
                    editor.apply();
                }
            }
        });

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.cardView.setBackgroundColor(R.color.colorLightBlue);
                Intent intent = new Intent(context, MainStudyAction.class);
                intent.putExtra(String.valueOf(ActivityGlobal.KeysExtra.num_of_topic), position);
                intent.putExtra(String.valueOf(ActivityGlobal.KeysExtra.level), englishLevel.level);

                new ElasticAnimation(v).setScaleX(0.90f).setScaleY(0.90f).setDuration(500)
                        .setOnFinishListener(new ElasticFinishListener() {
                            @Override
                            public void onFinished() {
                                context.startActivity(intent);
                                englishLevel.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
                            }
                        }).doAction();
            }
        });

        if (key_topics[position]) {
            holder.checkBox.setChecked(true);
        }else {
            holder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return name_topic.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title; CheckBox checkBox; RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            checkBox = itemView.findViewById(R.id.checkBox);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
