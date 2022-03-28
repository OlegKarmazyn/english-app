package solid.icon.english.main_adapters;


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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.MainActivity;
import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.words_by_levels.universal_topic_level.EnglishLevel;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyViewHolder> {

    Context context;
    String[] titlesArray;
    boolean[] isCheckArray;
    MainActivity mainActivity;
    private ActivityGlobal.LessonsName[] lessonsNames = ActivityGlobal.LessonsName.values();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public AdapterUsers(Context context, String[] titlesArray, boolean[] isCheckArray, MainActivity mainActivity) {
        this.context = context;
        this.titlesArray = titlesArray;
        this.isCheckArray = isCheckArray;
        this.mainActivity = mainActivity;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(titlesArray[position]);
        holder.checkBox.setChecked(isCheckArray[position]);

        if (isCheckArray[position]){
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        if(position == 0){
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_top));
        }else if(position == titlesArray.length - 1){
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_bottom));
        }else{
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_middle));
        }


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                editor.putBoolean(lessonsNames[position].name(), isChecked);
                editor.apply();

                if(isChecked) {
                    holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }

            }
        });

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EnglishLevel.class);
                if(position == 0) {
                    intent.putExtra(String.valueOf(ActivityGlobal.KeysExtra.level), ActivityGlobal.LessonsName.A2);
                }else if(position == 1){
                    intent.putExtra(String.valueOf(ActivityGlobal.KeysExtra.level), ActivityGlobal.LessonsName.B1);
                }else if(position == 2){
                    intent.putExtra(String.valueOf(ActivityGlobal.KeysExtra.level), ActivityGlobal.LessonsName.B2);
                }
                Log.e("position = ", String.valueOf(position));

                context.startActivity(intent);
                mainActivity.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
            }
        });

    }

    @Override
    public int getItemCount() {
        return titlesArray.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CheckBox checkBox;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            checkBox = itemView.findViewById(R.id.checkBox);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
