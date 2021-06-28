package solid.icon.english.words_by_levels.lev_b1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import solid.icon.english.R;
import solid.icon.english.db_pac.DBHelper;
import solid.icon.english.db_pac.DBmoveINFO;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    Context context; String name_topic[];

    public RecycleAdapter(Context context, String name_topic[]) {
        this.context = context;
        this.name_topic = name_topic;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }
    private int i = 0;
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecycleAdapter.MyViewHolder holder, int position) {
        holder.b1_tema_0.setText(name_topic[position]);

        int[] key_topics = new int[51];
        DBmoveINFO dBmoveINFO = new DBmoveINFO(context);

        for (int i = 0; i < 51; i++){
            key_topics[i] = dBmoveINFO.back_check_info(i, DBHelper.KNOW_TOPIC_B1);

        }
        if(i <= name_topic.length - 1) {
            if (key_topics[i] == 1) {
                holder.b1_checkBox_0.setChecked(true);
            }
            i++;
        }
    }

    @Override
    public int getItemCount() {
        return name_topic.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView b1_tema_0; CheckBox b1_checkBox_0;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            b1_tema_0 = itemView.findViewById(R.id.b1_tema_0);
            b1_checkBox_0 = itemView.findViewById(R.id.b1_checkBox_0);
        }
    }
}
