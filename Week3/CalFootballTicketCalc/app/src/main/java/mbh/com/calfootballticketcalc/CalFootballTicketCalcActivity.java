package mbh.com.calfootballticketcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mbh.com.calfootballticketcalc.ui.main.CalFootballTicketCalcFragment;

public class CalFootballTicketCalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cal_football_ticket_calc_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CalFootballTicketCalcFragment.newInstance())
                    .commitNow();
        }
    }
}