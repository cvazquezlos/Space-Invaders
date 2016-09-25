package com.example.android.spaceinvaders;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void actualizaPosicion(View v) {
        switch (v.getId()) {
            case R.id.control_derecha:
                findViewById(R.id.nave).setX(findViewById(R.id.nave).getX() - 30);
                break;
            case R.id.control_izquierda:
                findViewById(R.id.nave).setX(findViewById(R.id.nave).getX() + 30);
                break;
        }
    }
}
