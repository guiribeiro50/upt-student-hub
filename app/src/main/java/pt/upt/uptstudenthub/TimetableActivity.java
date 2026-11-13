package pt.upt.uptstudenthub;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TimetableActivity extends AppCompatActivity {
    public static final String EXTRA_STUDENT_NAME = "STUDENT_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        TextView textTitle = findViewById(R.id.textTitle);

        String name = getIntent().getStringExtra(EXTRA_STUDENT_NAME);
        if (name == null) {
            name = "";
        }

        textTitle.setText(getString(R.string.timetable_of, name));
    }


}