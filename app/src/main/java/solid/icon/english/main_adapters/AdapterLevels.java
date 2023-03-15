package solid.icon.english.main_adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import es.dmoral.toasty.Toasty;
import solid.icon.english.MainActivity;
import solid.icon.english.R;
import solid.icon.english.architecture.parents.ActivityGlobal;

public class AdapterLevels extends RecyclerView.Adapter<AdapterLevels.MyViewHolder> {

    Context context;
    String[] titlesArray;
    boolean[] isCheckArray;
    MainActivity mainActivity;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private final ActivityGlobal.LessonsName[] lessonsNames = ActivityGlobal.LessonsName.values();

    public AdapterLevels(Context context, String[] titlesArray, MainActivity mainActivity) {
        this.context = context;
        this.titlesArray = titlesArray;
        this.mainActivity = mainActivity;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        getIsCheckArray();
    }

    private void getIsCheckArray() {
        boolean[] isCheckArray = new boolean[lessonsNames.length];
        for (int i = 0; i < lessonsNames.length; i++) {
            isCheckArray[i] = preferences.getBoolean(lessonsNames[i].name(), false);
        }
        this.isCheckArray = isCheckArray;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(titlesArray[position]);
        holder.checkBox.setChecked(isCheckArray[position]);

        if (isCheckArray[position]) {
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        if (position == 0) {
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_top));
        } else if (position == titlesArray.length - 1) {
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_bottom));
        } else {
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.row_middle));
        }


        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean(lessonsNames[position].name(), isChecked);
            editor.apply();

            if (isChecked) {
                holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });

        holder.constraintLayout.setOnClickListener(v -> {
            Toasty.info(context, "This part in developing").show();
            // TODO: 15.03.2023 redone all resources with words
            /*
            Intent intent = new Intent(context, EnglishLevel.class);
            intent.putExtra(String.valueOf(ActivityGlobal.KeysExtra.level), lessonsNames[position].name());
            Log.e("lessonsNames", lessonsNames[position].name());
            context.startActivity(intent);
            mainActivity.overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
            */
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
