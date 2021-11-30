package com.programing.cprograming.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.programing.cprograming.BiginnerTips;
import com.programing.cprograming.CodeTitleShow;
import com.programing.cprograming.IntroductionActivity;
import com.programing.cprograming.LoginActivity;
import com.programing.cprograming.MainHomeActivity;
import com.programing.cprograming.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgrammeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgrammeListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProgrammeListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProgrammeListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgrammeListFragment newInstance(String param1, String param2) {
        ProgrammeListFragment fragment = new ProgrammeListFragment();
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
    private CardView introduction,basic,statement,loop,function,array,pointer,string,union,file,math;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_programme_list, container, false);

        introduction=(CardView)view.findViewById(R.id.introduction);
        basic=(CardView)view.findViewById(R.id.basic);
        statement=(CardView)view.findViewById(R.id.statement);
        loop=(CardView)view.findViewById(R.id.loop);
       function=(CardView)view.findViewById(R.id.function);
        array=(CardView)view.findViewById(R.id.array);
        string=(CardView)view.findViewById(R.id.string);
        union=(CardView)view.findViewById(R.id.union);
        file=(CardView)view.findViewById(R.id.file);
        pointer=(CardView)view.findViewById(R.id.pointer);


        /// New Activity Button Started ///////
        introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IntroductionActivity.class);
                startActivity(intent);
                //getActivity().finish();
            }
        });

        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), CodeTitleShow.class);
                Bundle bundle = new Bundle();
                bundle.putString("CodeT", "Basic Programs");
                intent.putExtras(bundle);
                intent.putExtra("helloCode","BasicPrograms");
                startActivity(intent);
            }
        });

        statement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), CodeTitleShow.class);
                Bundle bundle = new Bundle();
                bundle.putString("CodeT", "Control Statement");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        function.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CodeTitleShow.class);
                Bundle bundle = new Bundle();
                bundle.putString("CodeT", "Function");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        array.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CodeTitleShow.class);
                Bundle bundle = new Bundle();
                bundle.putString("CodeT", "Array");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        pointer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CodeTitleShow.class);
                Bundle bundle = new Bundle();
                bundle.putString("CodeT", "Pointer");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        string.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CodeTitleShow.class);
                Bundle bundle = new Bundle();
                bundle.putString("CodeT", "String");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        union.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CodeTitleShow.class);
                Bundle bundle = new Bundle();
                bundle.putString("CodeT", "Structure Union");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CodeTitleShow.class);
                Bundle bundle = new Bundle();
                bundle.putString("CodeT", "File Handling");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CodeTitleShow.class);
                Bundle bundle = new Bundle();
                bundle.putString("CodeT", "Loop");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        /////    New Activity Started End/////
        return view;

    }
}