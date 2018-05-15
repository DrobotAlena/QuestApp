package nsu.fit.questapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import nsu.fit.questapp.R;

/**
 * Created by Alena Drobot
 */
public class StartActivity extends AppCompatActivity {

    private Button startButton;
    private Button selectButton;

    public static void start(Context context) {
        context.startActivity(new Intent(context, StartActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initViews();
    }

    private void initViews() {
        startButton = findViewById(R.id.start_activity_start_button);
        selectButton = findViewById(R.id.start_activity_select_button);

        setClickListeners();
    }

    private void setClickListeners() {
        startButton.setOnClickListener(v -> {
            QuestActivity.start(StartActivity.this);
            finish();
        });

        selectButton.setOnClickListener(v -> GalleryActivity.start(StartActivity.this));
    }
}
