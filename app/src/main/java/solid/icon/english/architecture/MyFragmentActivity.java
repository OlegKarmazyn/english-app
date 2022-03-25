package solid.icon.english.architecture;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

import solid.icon.english.architecture.res.Res_array;

public abstract class MyFragmentActivity extends Fragment {

    protected Serializable what_level;
    protected int num_of_topic;
    protected int[][] main_1 = new int[][]{};
    protected int[][] main_2 = new int[][]{};

    protected void defineArrays() {
        if(what_level == ActivityGlobal.LessonsName.A2) {
            main_1 = new Res_array().main_1_learn_a2.clone();
            main_2 = new Res_array().main_2_learn_a2.clone();
        }else if(what_level == ActivityGlobal.LessonsName.B1){
            main_1 = new Res_array().main_1_learn_b1.clone();
            main_2 = new Res_array().main_2_learn_b1.clone();
        } else if(what_level == ActivityGlobal.LessonsName.B2){
            main_1 = new Res_array().main_1_learn_b2.clone();
            main_2 = new Res_array().main_2_learn_b2.clone();
        }
    }
}
