package com.example.user.firstgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int player = 0;

    boolean gameIsActive = true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winPositions = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                                {0,4,8},{2,4,6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        //counter.getTag().toString();
        int tabbedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tabbedCounter] == 2 && gameIsActive) {

            gameState[tabbedCounter] = player;
            counter.setTranslationY(-1000f);

            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                player = 0;
            }

            counter.animate().translationYBy(1000f)
                    .rotation(360).setDuration(300);

            for (int[] win : winPositions){
                if(gameState[win[0]] == gameState[win[1]] &&
                        gameState[win[1]] == gameState[win[2]] && gameState[win[0]] != 2){
//Someone has won
                    gameIsActive = false;

                    String winner = "Red";

                    if (gameState[win[0]] == 0) {
                        winner = "Yello";
                    }

                    TextView winnerMsg = (TextView) findViewById(R.id.winnerMsg);
                    winnerMsg.setText(winner + " has won");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgain);
                    layout.setVisibility(View.VISIBLE);
                }
                else {

                    boolean gameOver = true;

                    for (int counterStage : gameState){

                        if(counterStage == 2) gameOver = false;
                    }
                    if (gameOver){

                        TextView winnerMsg = (TextView) findViewById(R.id.winnerMsg);
                        winnerMsg.setText("Its a draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgain);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }

    public void playAgain(View view){

        gameIsActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgain);
        layout.setVisibility(View.INVISIBLE);

        player = 0;

        for (int i = 0;i < gameState.length; i++){

            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for (int i = 0; i< gridLayout.getChildCount();i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
