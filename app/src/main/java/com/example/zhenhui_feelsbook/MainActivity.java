package com.example.zhenhui_feelsbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "File.sav";
    private EditText bodyText;
    private ListView oldEmotionList;
    private ArrayList<Emotion> emotions = new ArrayList<Emotion>();
    private ArrayAdapter<Emotion> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bodyText = (EditText) findViewById(R.id.body);
        Button saveButton = (Button) findViewById(R.id.save);
        Button clearButton = (Button) findViewById(R.id.clear);
        /**
        Button angerButton = (Button) findViewById(R.id.anger);
        Button fearButton = (Button) findViewById(R.id.fear);
        Button joyButton = (Button) findViewById(R.id.love);
        Button sadnessButton = (Button) findViewById(R.id.sadness);
        Button surpriseButton = (Button) findViewById(R.id.surprise);
         **/
        oldEmotionList = (ListView) findViewById(R.id.oldEmotion);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                String text = bodyText.getText().toString();
                Emotion newEmotion = new Emotion();

                if(v.getId() == R.id.anger){
                    newEmotion.setEmotion("Anger");
                }
                else if(v.getId() == R.id.fear){
                    newEmotion.setEmotion("Fear");
                }
                else if(v.getId() == R.id.joy){
                    newEmotion.setEmotion("Joy");
                }
                else if(v.getId() == R.id.sadness){
                    newEmotion.setEmotion("Sadness");
                }
                else if(v.getId() == R.id.surprise){
                    newEmotion.setEmotion("Surprise");
                }
                else if(v.getId() == R.id.love){
                    newEmotion.setEmotion("Love");
                }
                else if(v.getId() == R.id.clear){
                    cleanFIle();
                }

                try{
                    newEmotion.setComment(text);
                    newEmotion.setTime(new Date());
                    emotions.add(newEmotion);
                    adapter.notifyDataSetChanged();
                    saveInFile();


                }
                catch(TooLongCommentException e){

                }
            }
    });
}
    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Emotion>(this, R.layout.list_item, emotions);
        oldEmotionList.setAdapter(adapter);
    }
/* loadFromFile and saveInFile are based on methods with same name in longTweeter*/
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            Gson gson = new Gson();
            Type listEmotionType = new TypeToken<ArrayList<Emotion>>(){}.getType();
            emotions = gson.fromJson(reader, listEmotionType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            emotions = new ArrayList<Emotion>();
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            Gson gson = new Gson();
            gson.toJson(emotions, writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void cleanFIle(){
        emotions.clear();
        adapter.notifyDataSetChanged();
        oldEmotionList.setAdapter(null);
    }
}

