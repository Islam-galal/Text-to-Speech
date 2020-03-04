package com.example.text_to_speech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Button  btnSpeak ;
    EditText edSpeak;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == textToSpeech.SUCCESS);

                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result == textToSpeech.LANG_MISSING_DATA ||
                     result == textToSpeech.LANG_NOT_SUPPORTED)

                            Toast.makeText(MainActivity.this , "this language is not supported",
                                    Toast.LENGTH_SHORT).show();

                            else
                                    btnSpeak.setEnabled(true);
                                    textToSpeech.setPitch(0.6f);
                                    textToSpeech.setSpeechRate(1.0f);
                                    speak();


            }
        });
                edSpeak = (EditText)findViewById(R.id.edite_speak);
                btnSpeak = (Button) findViewById(R.id.btn_speak);
                btnSpeak.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        speak();
                    }
                });
    }

    private void speak() {
        String text = edSpeak.getText().toString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            textToSpeech.speak(text , textToSpeech.QUEUE_FLUSH , null , null);

        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text , textToSpeech.QUEUE_FLUSH , null , null);
        }

    }

    @Override
    protected void onDestroy() {
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();

        }

        super.onDestroy();
    }
}
