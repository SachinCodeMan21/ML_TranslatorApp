package com.example.mltranslatorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

public class MainActivity extends AppCompatActivity {

    Spinner fSpinner,tSpinner;
    EditText userInput;
    TextView textArea;
    Button button;
    String fromLanguage,toLanguage = "";

    String[] fLanguages = {"from",
            "English", "Spanish", "French", "German", "Chinese", "Japanese",
            "Russian", "Portuguese", "Arabic", "Hindi", "Bengali", "Italian",
            "Dutch", "Swedish", "Turkish", "Korean", "Polish", "Thai",
            "Indonesian", "Greek", "Vietnamese", "Hebrew", "Romanian", "Hungarian"
    };


    String[] tLanguages = {"to",
            "English", "Spanish", "French", "German", "Chinese", "Japanese",
            "Russian", "Portuguese", "Arabic", "Hindi", "Bengali", "Italian",
            "Dutch", "Swedish", "Turkish", "Korean", "Polish", "Thai",
            "Indonesian", "Greek", "Vietnamese", "Hebrew", "Romanian", "Hungarian"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fSpinner = findViewById(R.id.spinner);
        tSpinner = findViewById(R.id.spinner2);
        userInput = findViewById(R.id.editTextTextPersonName);
        textArea = findViewById(R.id.textView2);
        button = findViewById(R.id.button);


        //from spinner

        fSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromLanguage = getLanguageCode(fLanguages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,fLanguages);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fSpinner.setAdapter(fromAdapter);

        //To spinner

        tSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toLanguage = getLanguageCode(tLanguages[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> toAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,tLanguages);
        toAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tSpinner.setAdapter(fromAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = userInput.getText().toString().trim();
                if (text.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter the text ", Toast.LENGTH_SHORT).show();
                }
                else if (fromLanguage.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please select source language", Toast.LENGTH_SHORT).show();
                }
                else if (toLanguage.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please select target language", Toast.LENGTH_SHORT).show();
                }
                else {
                    translateText(fromLanguage,toLanguage,text);
                }
            }
        });



    }

    private void translateText(String fromLanguage, String toLanguage, String input) {
        textArea.setText(R.string.downloadLanguageModel);
        try {
            TranslatorOptions options = new TranslatorOptions.Builder().setSourceLanguage(fromLanguage).setTargetLanguage(toLanguage).build();
            Translator translator = Translation.getClient(options);
            DownloadConditions conditions = new DownloadConditions.Builder().build();

            translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    textArea.setText(R.string.translating);
                    translator.translate(input).addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {
                            textArea.setText(s);
                            Toast.makeText(MainActivity.this, "Translation Completed", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Translation failed", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Failed to download language ", Toast.LENGTH_SHORT).show();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String getLanguageCode(String selectedLanguage) {
        String languageCode;
        switch(selectedLanguage) {
            case "English":
                languageCode = TranslateLanguage.ENGLISH;
                break;
            case "Spanish":
                languageCode = TranslateLanguage.SPANISH;
                break;
            case "French":
                languageCode = TranslateLanguage.FRENCH;
                break;
            case "German":
                languageCode = TranslateLanguage.GERMAN;
                break;
            case "Chinese":
                languageCode = TranslateLanguage.CHINESE;
                break;
            case "Japanese":
                languageCode = TranslateLanguage.JAPANESE;
                break;
            case "Russian":
                languageCode = TranslateLanguage.RUSSIAN;
                break;
            case "Portuguese":
                languageCode = TranslateLanguage.PORTUGUESE;
                break;
            case "Arabic":
                languageCode = TranslateLanguage.ARABIC;
                break;
            case "Hindi":
                languageCode = TranslateLanguage.HINDI;
                break;
            case "Bengali":
                languageCode = TranslateLanguage.BENGALI;
                break;
            case "Italian":
                languageCode = TranslateLanguage.ITALIAN;
                break;
            case "Dutch":
                languageCode = TranslateLanguage.DUTCH;
                break;
            case "Swedish":
                languageCode = TranslateLanguage.SWEDISH;
                break;
            case "Turkish":
                languageCode = TranslateLanguage.TURKISH;
                break;
            case "Korean":
                languageCode = TranslateLanguage.KOREAN;
                break;
            case "Polish":
                languageCode = TranslateLanguage.POLISH;
                break;
            case "Thai":
                languageCode = TranslateLanguage.THAI;
                break;
            case "Indonesian":
                languageCode = TranslateLanguage.INDONESIAN;
                break;
            case "Greek":
                languageCode = TranslateLanguage.GREEK;
                break;
            case "Vietnamese":
                languageCode = TranslateLanguage.VIETNAMESE;
                break;
            case "Hebrew":
                languageCode = TranslateLanguage.HEBREW;
                break;
            case "Romanian":
                languageCode = TranslateLanguage.ROMANIAN;
                break;
            case "Hungarian":
                languageCode = TranslateLanguage.HUNGARIAN;
                break;
            case "from":
                // Handle the "from" case if needed
            default:
                // Handle the case when selectedLanguage is not found in fLanguages
                languageCode = "";
                break;
        }
        return languageCode;
    }

}



















