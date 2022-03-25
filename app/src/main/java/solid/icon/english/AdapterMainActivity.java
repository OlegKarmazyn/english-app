package solid.icon.english;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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

import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.words_by_levels.universal_topic_level.EnglishLevel;

public class AdapterMainActivity extends RecyclerView.Adapter<AdapterMainActivity.MyViewHolder> {

    Context context;
    String[] titlesArray;
    String[] keysArray;
    String[] checkingArray;
    MainActivity mainActivity;

    public AdapterMainActivity(Context context, String[] titlesArray, String[] keysArray, String[] checkingArray, MainActivity mainActivity) {
        this.context = context;
        this.titlesArray = titlesArray;
        this.keysArray = keysArray;
        this.checkingArray = checkingArray;
        this.mainActivity = mainActivity;
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

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    Log.d("Recycle", "b1_checkBox_1 - onCheckedChanged - if - true");
                    holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }
                else {
                    holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

                }
            }
        });

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
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

                holder.relativeLayout.setBackground(context.getResources().getDrawable(R.drawable.rounded_table_clicked));

                context.startActivity(intent);
                mainActivity.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

//                new ElasticAnimation(v).setScaleX(0.90f).setScaleY(0.90f).setDuration(400)
//                        .setOnFinishListener(new ElasticFinishListener() {
//                            @Override
//                            public void onFinished() {
//
//                            }
//                        }).doAction();
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
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            checkBox = itemView.findViewById(R.id.checkBox);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
