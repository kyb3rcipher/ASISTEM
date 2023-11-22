package com.kyb3r.asistem;

import android.os.Bundle;

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

        // Obtener la referencia del TextView
        TextView lives = view.findViewById(R.id.livesLeft), days = view.findViewById(R.id.streaksDays);

        DatabaseHelper db = new DatabaseHelper(getContext());

        int livesNumber = db.getLivesCount();
        lives.setText(livesNumber + " lives");
        ImageView heart = view.findViewById(R.id.imageHeart), fire = view.findViewById(R.id.imageFire);
        if (livesNumber > 0) {
            heart.setImageResource(R.drawable.logo_duetone);
        } else {
            heart.setImageResource(R.drawable.logo_duetone_black);
        }

        days.setText("7 days");
        if (1 > 0) {
            fire.setImageResource(R.drawable.ic_fire);
        } else {
            fire.setImageResource(R.drawable.ic_fire_black);
        }

        /*ImageView heart1 = view.findViewById(R.id.liveHeart1), heart2 = view.findViewById(R.id.liveHeart2), heart3 = view.findViewById(R.id.liveHeart3), heart4 = view.findViewById(R.id.liveHeart4), heart5 = view.findViewById(R.id.liveHeart5);
        ImageView[] hearts = new ImageView[]{heart1, heart2, heart3, heart4, heart5};
        for (int i = 0; i < lives; i++) {
            hearts[i].setImageResource(R.drawable.logo_duetone);
        }*/

        // Inflate the layout for this fragment
        return view;
    }
}