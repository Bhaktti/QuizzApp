package com.example.android.quizzapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    public String[] evaluateGui() {
        String[] ret = new String[4];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2Greece = findViewById(R.id.question_2_CafeCentralPerk);
        CheckBox checkBoxQuestion2Burma = findViewById(R.id.question_2_CentralPerk);
        CheckBox checkBoxQuestion2Luxembourg = findViewById(R.id.question_2_Somepark);

        Boolean answerQuestion2 = false;

        if (checkBoxQuestion2Greece.isChecked() == true && checkBoxQuestion2Burma.isChecked() == true && checkBoxQuestion2Luxembourg.isChecked() == false) {
            answerQuestion2 = true;
        }


        ret[0] = editTextQuestion1.getText().toString();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3);
        ret[3] = evaluateRadioGroup(R.id.radio_group_question_4);

        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"gunther", "true", "What are they feeding you", "Moist Maker"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    public void toastResult(int result) {
        String message = result + " out of 4. ";

        if (result == 0) {
            message += "Better Luck next time.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Quite nice.";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Great!";
        }
        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }

    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2_CentralPerk);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_CafeCentralPerk);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_Somepark);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        radioGroup = findViewById(R.id.radio_group_question_4);
        radioGroup.clearCheck();
    }
}