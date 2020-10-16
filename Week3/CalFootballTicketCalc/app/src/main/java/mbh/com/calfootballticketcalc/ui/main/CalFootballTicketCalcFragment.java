package mbh.com.calfootballticketcalc.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

import mbh.com.calfootballticketcalc.R;

public class CalFootballTicketCalcFragment extends Fragment {

    private double costPerTicket=75.00;

    private int numberOfTickets;    //这三个是下面三个转换成合理的形式
    private double totalCost;
    private String gameChoice;

    private EditText tickets;      //how many tickets you need: object
    private Spinner game;
    private TextView result;
    private Button cost;


    private CalFootballTicketCalcViewModel mViewModel;

    public static CalFootballTicketCalcFragment newInstance() {
        return new CalFootballTicketCalcFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.cal_football_ticket_calc_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        tickets = (EditText) getActivity().findViewById(R.id.cal_tickets_number);
        game = (Spinner) getActivity().findViewById(R.id.cal_tickets_game);
        result = ((TextView) getActivity().findViewById (R.id.cal_tickets_result));
        cost = (Button) getActivity().findViewById(R.id.cal_tickets_cost_button);

        cost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTicketCost(); }
        });
//        mViewModel = ViewModelProviders.of(this).get(CalFootballTicketCalcViewModel.class);
        // TODO: Use the ViewModel
    }

    private void getTicketCost(){
        String ticketsNo = tickets.getText().toString();

        gameChoice = game.getSelectedItem().toString();
        if (!TextUtils.isEmpty(ticketsNo)) {
            numberOfTickets =Integer.parseInt(ticketsNo);
            totalCost = costPerTicket * numberOfTickets;
            DecimalFormat currency = new DecimalFormat("$###,###.##");
            String message = getResources().getString(R.string.total_cost)+ " "
                    + gameChoice + " "
                    + getResources().getString(R.string.is)+ " " + currency.format(totalCost);
            result.setText(message);
        } else {
            result.setText(getResources().getString(R.string.need_number)); }
    }


}