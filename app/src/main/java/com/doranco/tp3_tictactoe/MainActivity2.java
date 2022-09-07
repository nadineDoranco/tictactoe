package com.doranco.tp3_tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    //  0  1  2
    //  3  4  5
    //  6  7  8
    public Button[] buttons;
    public String[] grid;
    public String[] winnings;
    public List<String> winningsMid;
    int tour =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        grid = new String[9];
        buttons = new Button[9];
        winnings = new String[8];
        winnings[0] = "012";
        winnings[1] = "345";
        winnings[2] = "678";
        winnings[3] = "036";
        winnings[4] = "147";
        winnings[5] = "258";
        winnings[6] = "048";
        winnings[7] = "642";

        winningsMid = new ArrayList<>();
        winningsMid.add(winnings[1]);
        winningsMid.add(winnings[4]);
        winningsMid.add(winnings[6]);
        winningsMid.add(winnings[7]);

        System.out.println(winningsMid.get(0));

        buttons[0]= findViewById(R.id.btn0);
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(0);
                botStrategy("O");
            }
        });
        buttons[1]=(findViewById(R.id.btn1));
        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn( 1);
            }
        });
        buttons[2]=(findViewById(R.id.btn2));
        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn( 2);
            }
        });
        buttons[3]=(findViewById(R.id.btn3));
        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(3);
            }
        });
        buttons[4]=(findViewById(R.id.btn4));
        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(4);
            }
        });
        buttons[5]=(findViewById(R.id.btn5));
        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(5);
            }
        });
        buttons[6]=(findViewById(R.id.btn6));
        buttons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(6);
            }
        });
        buttons[7]=(findViewById(R.id.btn7));
        buttons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(7);
            }
        });
        buttons[8]=(findViewById(R.id.btn8));
        buttons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playTurn(8);
            }
        });
    }

    public void playTurn(int buttonIndex){
        String element = "X";
        String elementO = "O";

        int result = (int) ((Math.random() * (0 - 9)) + 9);

        grid[buttonIndex] = element;
        buttons[buttonIndex].setText(element);
        buttons[buttonIndex].setOnClickListener(null);

        // Si le contenu du bouton du milieu = X
        // on cherche dans les winnings dont chartAt(1) != 4
        // SINON!
        // on cherche dans les winnings dont chartAt(1) = 4
        // il met le rond au milieu
        // l'utilisateur met ailleurs
        // On regarde le charAt(0) & charAt(2)
        // Si disponible, l'un ou l'autre
        // Si l'un, alors l'autre

        tour++;
        checkwin(element);

    }

    public void botStrategy(String element)
    {
        for (String winning : winningsMid) {
            int first = Character.getNumericValue(winning.charAt(1));

            if (grid[first] != element) {
                grid[4] = element;
                buttons[4].setText(element);
                buttons[4].setOnClickListener(null);
            }
            int second = Character.getNumericValue(winning.charAt(0));
            if (grid[second] != element) {
                playTurn(second);
            }
            int third = Character.getNumericValue(winning.charAt(2));
            if (grid[third] != element) {
                playTurn(third);
            }

            if (element == "X") {
                gameDone(1);
                continue;
            } else {
                gameDone(2);
                continue;
            }
        }
        if(tour == 9){
            gameDone(0);
        }
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

    public void resetBtns(View view) {
        for(int i=0; i < 9; i++){
            buttons[i].setText("");
            // buttons[i].setOnClickListener();

        }
    }

    public void goToActivity2(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}