package com.doranco.tp3_tictactoe;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button[] buttons;
    public int[] btnIDs;
    public String[] grid;
    public String[] winnings;
    int tour = 0;


    public void playTurn(int buttonIndex){
        String element = "X";

        grid[buttonIndex] = element;
        buttons[buttonIndex].setText(element);
        buttons[buttonIndex].setTag(element);
        buttons[buttonIndex].setOnClickListener(null);

        tour++;
        checkwin(element);
    }

    public void botTurn(int buttonIndex){
        String element = "X";
        String elementO = "O";

        int result = (int) ((Math.random() * (0 - 9)) + 9);
        System.out.println(result);

        for(Button button: buttons) {
            do {
                grid[result] = elementO;
                buttons[result].setText(elementO);
                buttons[result].setTag(elementO);
                buttons[result].setOnClickListener(null);
            } while (buttons[result].getTag() != element && buttons[result].getTag() != elementO);
        }

        tour++;
        checkwin(elementO);
    }

    public void checkwin(String element)
    {
        for(String winning : winnings){
            int first= Character.getNumericValue(winning.charAt(0));
            if(grid[first] != element){
                continue;
            }

            int second = Character.getNumericValue(winning.charAt(1));
            if(grid[second] != element){
                continue;
            }

            int third =  Character.getNumericValue(winning.charAt(2));
            if(grid[third] != element){
                continue;
            }

            if(element == "X"){
                gameDone(1);
                return;
            }else{
                gameDone(2);
                return;
            }
        }

        if(tour == 9){
            gameDone(0);
        }
    }


    public void gameDone(int winingOptions)
    {
        if(winingOptions == 0)
        {
            Toast.makeText(getApplicationContext(),"match nul",Toast.LENGTH_LONG).show();
        }else if(winingOptions == 1){
            Toast.makeText(getApplicationContext(),"les croix gagnent !",Toast.LENGTH_LONG).show();
        }else if(winingOptions == 2){
            Toast.makeText(getApplicationContext(),"les ronds gagnent !",Toast.LENGTH_LONG).show();
        }
        for(Button button:buttons){
            button.setOnClickListener(null);
        }
    }

    public void initGame() {
        buttons = new Button[9];
        grid = new String[9];
        tour = 0;

        winnings = new String[8];
        winnings[0] = "012";
        winnings[1] = "345";
        winnings[2] = "678";
        winnings[3] = "036";
        winnings[4] = "147";
        winnings[5] = "258";
        winnings[6] = "048";
        winnings[7] = "642";

        btnIDs = new int[]{
                R.id.btn0,
                R.id.btn1,
                R.id.btn2,
                R.id.btn3,
                R.id.btn4,
                R.id.btn5,
                R.id.btn6,
                R.id.btn7,
                R.id.btn8};

        for(int i = 0; i < 9; i++) {
            buttons[i]= findViewById(btnIDs[i]);
            int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playTurn(finalI);
                    botTurn(finalI);
                }
            });
        }
    }

    public void goToActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToActivity2(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGame();
    }

}