package com.kyb3r.asistem;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView bands = view.findViewById(R.id.bandsLeft), days = view.findViewById(R.id.streaksDays);

        DatabaseHelper db = new DatabaseHelper(getContext());

        int bandsNumber = db.getBandsCount();
        bands.setText(bandsNumber + " " + getString(R.string.bands));
        ImageView band = view.findViewById(R.id.imageBand), fire = view.findViewById(R.id.imageFire);
        if (bandsNumber > 0) {
            band.setImageResource(R.drawable.ic_band);
        } else {
            band.setImageResource(R.drawable.ic_band_black);
        }

        days.setText("7 " + getString(R.string.days));
        if (1 > 0) {
            fire.setImageResource(R.drawable.ic_fire);
        } else {
            fire.setImageResource(R.drawable.ic_fire_black);
        }


        CardView livesCard = view.findViewById(R.id.livesCard), emergencyNumbers = view.findViewById(R.id.emergencyNumbersCard);
        livesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent BandsActivity = new Intent(getContext(), BandsActivity.class);
                startActivity(BandsActivity);
            }
        });
        emergencyNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EmergencyNumbersActivity = new Intent(getContext(), EmergencyNumbersActivity.class);
                startActivity(EmergencyNumbersActivity);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}