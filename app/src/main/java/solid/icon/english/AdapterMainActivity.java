package solid.icon.english;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EnglishLevel.class);
                if(position == 0) {
                    intent.putExtra("level", "a2");
                }else if(position == 1){
                    intent.putExtra("level", "b1");
                }else if(position == 2){
                    intent.putExtra("level", "b2");
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
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            checkBox = itemView.findViewById(R.id.checkBox);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}
