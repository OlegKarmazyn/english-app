package solid.icon.english.main_adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import solid.icon.english.MainActivity;
import solid.icon.english.R;
import solid.icon.english.architecture.ActivityGlobal;
import solid.icon.english.architecture.room.App;
import solid.icon.english.architecture.room.TopicModel;
import solid.icon.english.architecture.room.TopicModelDao;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyViewHolder> {

    Context context;
    String[] titlesArray;
    boolean[] isCheckArray;
    MainActivity mainActivity;
    private ActivityGlobal.LessonsName[] lessonsNames = ActivityGlobal.LessonsName.values();
    int size;

    public AdapterUsers(Context context, String[] titlesArray, MainActivity mainActivity) {
        this.context = context;
        this.titlesArray = titlesArray;
        this.mainActivity = mainActivity;
        getIsCheckArray();
    }

    private void getIsCheckArray(){
        isCheckArray = new boolean[titlesArray.length];
        //todo
        for (int i = 0; i < isCheckArray.length; i++) {
            isCheckArray[i] = false;
        }
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row_custom, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(position != size){

            holder.title.setText(titlesArray[position]);
            holder.checkBox.setChecked(isCheckArray[position]);

            if (position == 0) {
                holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_top));
            } else if (position == titlesArray.length - 1) {
                holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_bottom));
            } else {
                holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_middle));
            }

            if (isCheckArray[position]) {
                holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }

        }else {

            holder.add_topic.setVisibility(View.VISIBLE);
            holder.title.setVisibility(View.GONE);
            holder.checkBox.setVisibility(View.GONE);

        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //todo
                if (isChecked) {
                    holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTopic();
                mainActivity.setDataToUserAdapter();
            }
        });


    }

    private void addNewTopic(){
        TopicModelDao topicModelDao = App.getInstance().getDatabase().topicModelDao();
        TopicModel topicModel = new TopicModel();
        topicModel.topicsName = "topic 1";
        topicModel.subTopicsName1 = "subTopics";
        topicModelDao.insert(topicModel);
    }

    @Override
    public int getItemCount() {
        size = titlesArray.length;
        return size + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        CheckBox checkBox;
        ImageView add_topic;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            checkBox = itemView.findViewById(R.id.checkBox);
            add_topic = itemView.findViewById(R.id.add_topic);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
