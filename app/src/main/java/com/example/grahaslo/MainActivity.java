package com.example.grahaslo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String[] words = {"krowa", "pies", "owca"};
    private String selectedWord;
    private ArrayList<Character> guessedLetters;
    private int chances;
    private TextView wordTextView, chancesTextView;
    private EditText letterEditText;
    ImageView imageView;
    Button reset;
    StringBuilder hiddenWord;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordTextView = findViewById(R.id.Haslo);
        letterEditText = findViewById(R.id.litera);
        Button guessButton = findViewById(R.id.sprawdz);
        reset = findViewById(R.id.reset);
        imageView = findViewById(R.id.imageView);

        guessedLetters = new ArrayList<>();
        chances = 1;
        pickWord();


        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String letter = letterEditText.getText().toString();
                if (letter.length() == 1) {
                    checkLetter(letter.toLowerCase().charAt(0));
                    letterEditText.setText("");

                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset.setVisibility(View.INVISIBLE);
                pickWord();
                chances=1;
                imageView.setImageResource(R.drawable.hangman1);
                guessedLetters.clear();

            }
        });
    }


    private void pickWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(words.length);
        selectedWord = words[randomIndex];
        hiddenWord = new StringBuilder();

        for (int i = 0; i < selectedWord.length(); i++) {
            hiddenWord.append("_ ");
        }

        wordTextView.setText(hiddenWord.toString());

    }

    private void checkLetter(char letter) {
        if (!guessedLetters.contains(letter)) {
            guessedLetters.add(letter);
            updateGuessedWord();
            if (!selectedWord.contains(String.valueOf(letter))) {
                chances++;
                    switch (chances){
                        case 1: imageView.setImageResource(R.drawable.hangman1);
                        break;
                        case 2: imageView.setImageResource(R.drawable.hangman2);
                        break;
                        case 3: imageView.setImageResource(R.drawable.hangman3);
                            break;
                        case 4: imageView.setImageResource(R.drawable.hangman4);
                            break;
                        case 5: imageView.setImageResource(R.drawable.hangman5);
                            break;
                        case 6: imageView.setImageResource(R.drawable.hangman6);
                            break;
                        case 7: imageView.setImageResource(R.drawable.hangman7);
                            break;
                        case 8: imageView.setImageResource(R.drawable.hangman8);
                            break;
                        case 9: imageView.setImageResource(R.drawable.hangman9);
                            break;
                    }
                if (chances == 9) {
                    wordTextView.setText("Przegrywasz! Haslo to: " + selectedWord);
                    reset.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void updateGuessedWord() {
        int win=0;
        StringBuilder displayWord = new StringBuilder();
        for (int i = 0; i < selectedWord.length(); i++) {
            char letter = selectedWord.charAt(i);
            if (guessedLetters.contains(letter)) {
                displayWord.append(letter).append(" ");
                win++;
            } else {
                displayWord.append("_ ");
            }
        }
        wordTextView.setText(displayWord.toString());
        if(win == selectedWord.length()){
            wordTextView.setText("Win");
            reset.setVisibility(View.VISIBLE);
        }


    }

}
