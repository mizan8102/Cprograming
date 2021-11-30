package com.programing.cprograming.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.indiandev.smartrateapp.util.RateDialogManager;
import com.programing.cprograming.AboutActivity;
import com.programing.cprograming.BiginnerTips;
import com.programing.cprograming.BookDetailsActivity;
import com.programing.cprograming.FeedbackForm;
import com.programing.cprograming.LoginActivity;
import com.programing.cprograming.MainHomeActivity;
import com.programing.cprograming.Model.DataModel;
import com.programing.cprograming.R;
import com.programing.cprograming.SharingCenter.SocialHome;
import com.programing.cprograming.Splash_Activity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private CardView begginer,book,interview,aboutid,faq,txtcommunity;
    private View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_dashboard, container, false);
        begginer=(CardView)view.findViewById(R.id.txtbeginnerTips);
        book=(CardView)view.findViewById(R.id.txtbook);
        interview=(CardView)view.findViewById(R.id.interview_question);
        aboutid=(CardView)view.findViewById(R.id.aboutidtxt);
        faq=(CardView)view.findViewById(R.id.faq);
        txtcommunity=(CardView)view.findViewById(R.id.txtcommunity);
        allWork();

        return view;
    }

    private void allWork(){
        begginer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                
                Intent intent = new Intent(getContext(), BiginnerTips.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", "Tips for Beginner");
                intent.putExtras(bundle);
                
                intent.putExtra("array_des","Beginer");
                startActivity(intent);
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", "C Programing Books");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        interview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), BiginnerTips.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", "Interview Questions");
                intent.putExtras(bundle);
                intent.putExtra("array_des","InterviewQuestion");
                startActivity(intent);
            }
        });
        aboutid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AboutActivity.class));
                Animatoo.animateSlideUp(getContext());
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RateDialogManager.showRateDialog(MainHomeActivity.this,savedInstanceState);
                startActivity(new Intent(getContext(), FeedbackForm.class));
                Animatoo.animateSlideDown(getContext());
            }
        });

        txtcommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sp=getActivity().getSharedPreferences
                        ("com.programing.cprograming", Context.MODE_PRIVATE);
                String email=sp.getString("user_id", "");
                if (TextUtils.isEmpty(email)){
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    getActivity().finish();
                    Animatoo.animateSlideLeft(getContext());
                }
                else {
                    startActivity(new Intent(getContext(), SocialHome.class));
                    Animatoo.animateSlideDown(getContext());
                }




            }
        });
    }
}