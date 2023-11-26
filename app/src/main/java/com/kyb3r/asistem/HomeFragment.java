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

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView lives = view.findViewById(R.id.livesLeft), days = view.findViewById(R.id.streaksDays);

        DatabaseHelper db = new DatabaseHelper(getContext());

        int livesNumber = db.getLivesCount();
        lives.setText(livesNumber + " " + getString(R.string.lives));
        ImageView heart = view.findViewById(R.id.imageHeart), fire = view.findViewById(R.id.imageFire);
        if (livesNumber > 0) {
            heart.setImageResource(R.drawable.logo_duetone);
        } else {
            heart.setImageResource(R.drawable.logo_duetone_black);
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
                Intent LivesActivity = new Intent(getContext(), LivesActivity.class);
                startActivity(LivesActivity);
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