package solid.icon.english;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class RandomOrg extends AppCompatActivity{
    private int [] id_topics = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};
    private int [] id_words = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};
    private int [] id_what_topics = new int[]{55, 66, 77, 88, 99, 100, 110, 112, 114, 124, 1234, 124, 768, 345, 98};
    public int[] random_words_english = new int[15];
    public int[] random_words_translation = new int[15];


    public int a2_or_b1(){
        int a = 0; // Начальное значение диапазона - "от"
        int b = 2; // Конечное значение диапазона - "до"

        return a + (int) (Math.random() * b); // Генерация 1-го числа
    }

    private void full_topics_array(){
        int rand = 0;
        boolean isTrue = false;
        int k = 0;


        for (int iter = 0; iter < 15; iter++) {
            do {
                k = 0;
                rand = (int) (Math.random() * 15);
                for (int j = 0; j < 15; j++) {
                    if (rand == id_words[j]) {
                        isTrue = true;
                    }else{
                        k++;
                    }
                }
                if(k == 15){
                    isTrue = false;
                }

            } while (isTrue);
            id_words[iter] = rand;
        }
    }

    private void full_words_array(){
        int rand = 0;
        boolean isTrue = false;
        int k = 0;


        for (int iter = 0; iter < 15; iter++) {
            do {
                k = 0;
                rand = (int) (Math.random() * 51);
                for (int j = 0; j < 15; j++) {
                    if (rand == id_topics[j]) {
                        isTrue = true;
                    }else{
                        k++;
                    }
                }
                if(k == 15){
                    isTrue = false;
                }

            } while (isTrue);
            id_topics[iter] = rand;
        }
    }

    public void invoke_all_doing(){
        log_method();
        getStringArray_english();
        getStringArray_translation();
    }

    public void log_method(){
        full_topics_array();
        full_words_array();
        for (int i = 0; i < 15; i++) {
            Log.e("full_topics_array", String.valueOf(id_topics[i]));
        }
        for (int i = 0; i < 15; i++) {
            Log.e("full_words_array", String.valueOf(id_words[i]));
        }
        for (int i = 0; i < 15; i++) {
            id_what_topics[i] = a2_or_b1();
            Log.e("a2_or_b1", String.valueOf(a2_or_b1()));
        }
    }

    public int[] getStringArray_english(){


        int[][] mas_a2 = new Res_array().main_1_learn_a2;
        int[][] mas_b1 = new Res_array().main_1_learn_b1;
        for(int i = 0; i < 15; i++) {
            if (id_what_topics[i] == 0) {
                random_words_english[i] =  mas_a2[id_topics[i]][id_words[i]];
            } else if(id_what_topics[i] == 1){
                random_words_english[i] =  mas_b1[id_topics[i]][id_words[i]];
            }
        }

        return random_words_english;
    }

    public int[] getStringArray_translation(){


        int[][] mas_a2 = new Res_array().main_2_learn_a2;
        int[][] mas_b1 = new Res_array().main_2_learn_b1;
        for(int i = 0; i < 15; i++) {
            if (id_what_topics[i] == 0) {
                random_words_translation[i] =  mas_a2[id_topics[i]][id_words[i]];
            } else if(id_what_topics[i] == 1){
                random_words_translation[i] =  mas_b1[id_topics[i]][id_words[i]];
            }
        }

        return random_words_translation;
    }

}
