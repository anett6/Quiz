package com.example.android.quiz;

import android.content.Context;
import android.content.res.Configuration;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //initialize the score to be 0.
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen and keep the data while rotating
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    //When you have the keyboard seen in the screen, because of the EditText, this`ll hide the keyboard when you click somewhere else on the screen
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void checkAnswers(View view) {

        //disable submit button to prevent resubmissions
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setEnabled(false);

        //Check if the correct answer is checked or not, if yes add 1 point to the score, else nothing.
        RadioButton radio_button1_A3 = (RadioButton) findViewById(R.id.radio_button_right1_3);
        Boolean correct1_A3 = radio_button1_A3.isChecked();
        if (correct1_A3) score++;

        //Check if the correct answer is checked or not, if yes add 1 point to the score, else nothing.
        RadioButton radio_button2_A2 = (RadioButton) findViewById(R.id.radio_button_right2_2);
        Boolean correct2_A2 = radio_button2_A2.isChecked();
        if (correct2_A2) score++;

        //You can type your answer and it`s stored in a String variable. If it`s the right answer add 1 point
        EditText edit_text_answer3 = (EditText) findViewById(R.id.edit_text_answer3_0);
        String answer3 = edit_text_answer3.getText().toString().trim();
        if (!TextUtils.isEmpty(answer3)) //If it`s not empty
            if (answer3.equalsIgnoreCase(getString(R.string.q3_right))) score++;


        //Check if the correct answer is checked or not, if yes add 1 point to the score, else nothing.
        RadioButton radio_button4_A4_1 = (RadioButton) findViewById(R.id.radio_button_right4_4_1);
        RadioButton radio_button4_A1_2 = (RadioButton) findViewById(R.id.radio_button_right4_1_2);
        Boolean correct4_A4_1 = radio_button4_A4_1.isChecked();
        Boolean correct4_A1_2 = radio_button4_A1_2.isChecked();
        if (correct4_A4_1 && correct4_A1_2) score++; //if both answers are checked add 1 point

        //Figure out if the 2 correct answers are checked, and no wrong answers checked, if so add 1 point to the score, otherwise nothing.
        CheckBox check_box5_A1 = (CheckBox) findViewById(R.id.check_box_answer5_1);
        CheckBox check_box5_A2 = (CheckBox) findViewById(R.id.check_box_right5_2);
        CheckBox check_box5_A3 = (CheckBox) findViewById(R.id.check_box_right5_3);
        CheckBox check_box5_A4 = (CheckBox) findViewById(R.id.check_box_answer5_4);
        boolean correct5_A2 = check_box5_A2.isChecked();
        boolean correct5_A3 = check_box5_A3.isChecked();
        boolean wrong5_A1 = check_box5_A1.isChecked();
        boolean wrong5_A4 = check_box5_A4.isChecked();
        if (correct5_A2 && correct5_A3 && !wrong5_A1 && !wrong5_A4)
            score++; //2 correct answers are checked, the 2 wrong one don`t

        //Check if the correct answer is checked or not, if yes add 1 point to the score, else nothing.
        RadioButton radio_button6_A4 = (RadioButton) findViewById(R.id.radio_button_right6_4);
        boolean correct6_A4 = radio_button6_A4.isChecked();
        if (correct6_A4) score++;

        //Check if the correct answer is checked or not, if yes add 1 point to the score, else nothing.
        RadioButton radio_button7_A1 = (RadioButton) findViewById(R.id.radio_button_right7_1);
        boolean correct7_A1 = radio_button7_A1.isChecked();
        if (correct7_A1) score++;

        //Check if the correct answer is checked or not, if yes add 1 point to the score, else nothing.
        RadioButton radio_button8_A3 = (RadioButton) findViewById(R.id.radio_button_right8_3);
        boolean correct8_A3 = radio_button8_A3.isChecked();
        if (correct8_A3) score++;

        //You can type your answer and it`s stored in a String variable. If it`s the right answer add 1 point
        EditText edit_text_answer9 = (EditText) findViewById(R.id.edit_text_answer9_0);
        String answer9 = edit_text_answer9.getText().toString().trim();
        if (!TextUtils.isEmpty(answer9)) //if it`s not empty
            if (answer9.equalsIgnoreCase(getString(R.string.q9_right1)))
                score++; //1st right option for answer
            else if (answer9.equalsIgnoreCase(getString(R.string.q9_right2)))
                score++; //2nd right option for answer

        //You can type your answer and it`s stored in a String variable. If it`s the right answer add 1 point
        EditText edit_text_answer10 = (EditText) findViewById(R.id.edit_text_answer10_0);
        String answer10 = edit_text_answer10.getText().toString().trim();
        if (!TextUtils.isEmpty(answer10)) //if it`s not empty
            if (answer10.equalsIgnoreCase(getString(R.string.q10_right))) score++;

        //Toast Message if you have 0 point
        if (score == 0)
            Toast.makeText(this, R.string.toast_for_0_point, Toast.LENGTH_SHORT).show();

        //Toast Message if you have 1 point
        if (score == 1)
            Toast.makeText(this, R.string.toast_for_1_point, Toast.LENGTH_LONG).show();

        //Toast Message if you have more than 1 point
        if (score > 1)
            Toast.makeText(this, "Your score is " + score + " points out of 10.", Toast.LENGTH_LONG).show();
    }


    public void resetTheGame(View view) {

        //Reset the score to 0
        score = 0;

        //Scroll up to the top of the screen after hit the Restart Button
        ScrollView scroll_view = findViewById(R.id.scroll_view);
        scroll_view.smoothScrollTo(0, 0);

        //enable submit button after resetting the questions
        Button submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setEnabled(true);

        //Find and reset all of the RadioGroups so all the radioButtons will be unchecked.
        RadioGroup radio_group1 = (RadioGroup) findViewById(R.id.radio_group1);
        RadioGroup radio_group2 = (RadioGroup) findViewById(R.id.radio_group2);
        RadioGroup radio_group4_1 = (RadioGroup) findViewById(R.id.radio_group4_1);
        RadioGroup radio_group4_2 = (RadioGroup) findViewById(R.id.radio_group4_2);
        RadioGroup radio_group6 = (RadioGroup) findViewById(R.id.radio_group6);
        RadioGroup radio_group7 = (RadioGroup) findViewById(R.id.radio_group7);
        RadioGroup radio_group8 = (RadioGroup) findViewById(R.id.radio_group8);
        radio_group1.clearCheck();
        radio_group2.clearCheck();
        radio_group4_1.clearCheck();
        radio_group4_2.clearCheck();
        radio_group6.clearCheck();
        radio_group7.clearCheck();
        radio_group8.clearCheck();

        //Find and reset all of CheckBoxes to be unchecked
        CheckBox checkBox5_A1 = (CheckBox) findViewById(R.id.check_box_answer5_1);
        CheckBox checkBox5_A2 = (CheckBox) findViewById(R.id.check_box_right5_2);
        CheckBox checkBox5_A3 = (CheckBox) findViewById(R.id.check_box_right5_3);
        CheckBox checkBox5_A4 = (CheckBox) findViewById(R.id.check_box_answer5_4);
        checkBox5_A1.setChecked(false);
        checkBox5_A2.setChecked(false);
        checkBox5_A3.setChecked(false);
        checkBox5_A4.setChecked(false);

        //Set no text to the EditTexts, so they will be empty
        EditText edit_text_answer3 = (EditText) findViewById(R.id.edit_text_answer3_0);
        EditText edit_text_answer9 = (EditText) findViewById(R.id.edit_text_answer9_0);
        EditText edit_text_answer10 = (EditText) findViewById(R.id.edit_text_answer10_0);
        edit_text_answer10.setText("");
        edit_text_answer9.setText("");
        edit_text_answer3.setText("");
    }
}