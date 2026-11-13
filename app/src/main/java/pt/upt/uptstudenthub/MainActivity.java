package pt.upt.uptstudenthub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        Button buttonOpen = findViewById(R.id.buttonOpen); // não existe necessidade de uma variável global

        buttonOpen.setOnClickListener(v -> openTimeTable());
    }

    private void openTimeTable() {
        String name = editName.getText().toString().trim();

        if (name.isEmpty()) {
            editName.setError(getString(R.string.error_name_empty));
            return;
        }
        Intent intent = new Intent(this, TimetableActivity.class);
        intent.putExtra(TimetableActivity.EXTRA_STUDENT_NAME, name);
        startActivity(intent);
    }
}