package com.example.guessmaster3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class GuessMaster extends AppCompatActivity {
    // Instance Variables
    private TextView entityName;
    private TextView ticketSum;
    private Button guessButton;
    private EditText userIn;
    private Button btnClearContent;
    private ImageView entityImage;
    private Entity[] entities;
    private int numberOfCandidateEntities;
    private int totalTickets;
    private int numOfTickets;
    private int[] tickets;
    int entityId = 0;
    String answer;

    public GuessMaster() {
        this.numberOfCandidateEntities = 0;
        this.entities = new Entity[10];
        this.tickets = new int[100];
        this.totalTickets = 0;
        this.numOfTickets = 0;
    }

    // Adding the entities into the array while increasing the entity counter
    public void addEntity(Entity entity) {
        entities[numberOfCandidateEntities++] = entity;
    }

    // Game logic
    public void playGame(Entity entity) {
        entityName.setText(entity.getName());

        answer = userIn.getText().toString();
        answer = answer.replace("\n", "").replace("\r", "").trim();

        if (answer.isEmpty() || !answer.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            Toast.makeText(getBaseContext(), "Enter a valid date as mm/dd/yyyy", Toast.LENGTH_SHORT).show();
            return;
        }

        Date guessDate;
        try {

            guessDate = new Date(answer);
            AlertDialog.Builder gameAlert = new AlertDialog.Builder(GuessMaster.this);

            // If the user's guess was earlier than the entity's birthday
            if (guessDate.precedes(entity.getBorn())) {
                gameAlert.setTitle("Try Again");
                gameAlert.setMessage("Try a later date");
                gameAlert.create().show();

                // If the user's guess was later than the entity's birthday
            } else if (entity.getBorn().precedes(guessDate)) {
                gameAlert.setTitle("Try Again");
                gameAlert.setMessage("Try an earlier date");
                gameAlert.create().show();

                // Exact match
            } else {
                int won = entity.getAwardedTicketNumber(entity.getDifficulty());
                tickets[numOfTickets++] = won;
                totalTickets = 0;
                for (int i = 0; i < numOfTickets; i++) {
                    totalTickets = totalTickets + tickets[i];
                }
                ticketSum.setText(String.valueOf(totalTickets));

                gameAlert.setTitle("You Won");
                gameAlert.setMessage("BINGO! " + entity.closingMessage());

                gameAlert.setNegativeButton("Continue Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "You have earned " + won + " tickets.", Toast.LENGTH_SHORT).show();
                        continueGame();
                    }
                });
                gameAlert.create().show();
            }

        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Invalid date format. Use mm/dd/yyyy", Toast.LENGTH_SHORT).show();
        }
    }

    public void continueGame() {
        Random rand = new Random();
        entityId = rand.nextInt(numberOfCandidateEntities);
        Entity entity = entities[entityId];

        String eName = entity.getName();

        // Update the name FIRST so ImageSetter() reads the new entity name
        entityName.setText(eName);
        ImageSetter();

        // Clear previous entry
        userIn.getText().clear();
    }

    public void welcomeToGame(Entity entity) {
        AlertDialog.Builder welcomeAlert = new AlertDialog.Builder(GuessMaster.this);
        welcomeAlert.setTitle("GuessMaster Game V3");
        welcomeAlert.setMessage(entity.welcomeMessage());
        welcomeAlert.setCancelable(false);

        welcomeAlert.setNegativeButton(
                "Start Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(), "Game is Starting ... Enjoy", Toast.LENGTH_SHORT).show();
                        continueGame();
                    }
                }
        );
        welcomeAlert.create().show();
    }

    public Entity changeEntity() {
        Random rand = new Random();
        entityId = rand.nextInt(numberOfCandidateEntities);
        Entity next = entities[entityId];
        entityName.setText(next.getName());
        ImageSetter();
        userIn.getText().clear();
        return next;
    }

    public void ImageSetter() {
        String eName = entityName.getText().toString();
        switch (eName) {
            case "Justin Trudeau":
                entityImage.setImageResource(R.drawable.justint);
                break;
            case "Celine Dion":
                entityImage.setImageResource(R.drawable.celidion);
                break;
            case "myCreator":
                entityImage.setImageResource(R.drawable.my_creater);
                break;
            case "United States":
                entityImage.setImageResource(R.drawable.usaflag);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guess_activity);

        // 1. Bind all UI views BEFORE setting listeners
        guessButton = findViewById(R.id.btnGuess);
        userIn = findViewById(R.id.guessinput);
        ticketSum = findViewById(R.id.ticket);
        entityName = findViewById(R.id.entityName);
        btnClearContent = findViewById(R.id.btnClear);
        entityImage = findViewById(R.id.entityImage);

        // 2. Attach click listeners
        btnClearContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeEntity();
            }
        });

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(entities[entityId]);
            }
        });

        // 3. Populate entities into THIS Activity instance
        Politician trudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), "Male", "Liberal", 0.25);
        Singer dion = new Singer("Celine Dion", new Date("March", 30, 1961), "Female", "La voix du bon Dieu", new Date("November", 6, 1981), 0.5);
        Person myCreator = new Person("myCreator", new Date("September", 1, 2000), "Male", 1);
        Country usa = new Country("United States", new Date("July", 4, 1776), "Washington D.C.", 0.1);

        addEntity(trudeau);
        addEntity(dion);
        addEntity(myCreator);
        addEntity(usa);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 4. Launch welcome dialog
        welcomeToGame(trudeau);
    }
}