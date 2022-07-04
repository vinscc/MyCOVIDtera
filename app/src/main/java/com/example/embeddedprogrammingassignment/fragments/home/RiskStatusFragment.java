package com.example.embeddedprogrammingassignment.fragments.home;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.embeddedprogrammingassignment.R;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class RiskStatusFragment extends Fragment {

    MaterialButtonToggleGroup toggleGroupQ2,toggleGroupQ3,toggleGroupQ4;
    Button q2YesBtn,q2NoBtn,q3YesBtn,q3NoBtn,q4YesBtn,q4NoBtn, submitRiskBtn;
    ImageView riskLogoIv;
    TextView statusTv, riskTv;
    CardView riskCv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_risk_status, container, false);

        toggleGroupQ2=view.findViewById(R.id.tgq2);
        toggleGroupQ3=view.findViewById(R.id.tgq3);
        toggleGroupQ4=view.findViewById(R.id.tgq4);
        q2YesBtn=view.findViewById(R.id.btnq2Yes);
        q2NoBtn=view.findViewById(R.id.btnq2No);
        q3YesBtn=view.findViewById(R.id.btnq3Yes);
        q3NoBtn=view.findViewById(R.id.btnq3No);
        q4YesBtn=view.findViewById(R.id.btnq4Yes);
        q4NoBtn=view.findViewById(R.id.btnq4No);
        submitRiskBtn = view.findViewById(R.id.btnSubmitRiskStatus);

        riskLogoIv = view.findViewById(R.id.ivCardviewCovidRisk);
        statusTv = view.findViewById(R.id.tvCardviewCovidTitle);
        riskTv = view.findViewById(R.id.tvCardviewCovidRisk);
        riskCv = view.findViewById(R.id.cvCovidExposureRisk);

        q2YesBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),R.color.riskstatus_question_option_selected));
        q2NoBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),R.color.riskstatus_question_option_selected));
        q3YesBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),R.color.riskstatus_question_option_selected));
        q3NoBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),R.color.riskstatus_question_option_selected));
        q4YesBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),R.color.riskstatus_question_option_selected));
        q4NoBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(),R.color.riskstatus_question_option_selected));

        toggleGroupQ2.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {

                q2YesBtn.setSelected(false);
                q2NoBtn.setSelected(false);

                if(isChecked){
                    if(checkedId==R.id.btnq2Yes){
                        q2YesBtn.setSelected(true);
                    }
                    if(checkedId==R.id.btnq2No){
                        q2NoBtn.setSelected(true);
                    }
                }
            }
        });

        toggleGroupQ3.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {

                q3YesBtn.setSelected(false);
                q3NoBtn.setSelected(false);

                if(isChecked){
                    if(checkedId==R.id.btnq3Yes){
                        q3YesBtn.setSelected(true);
                    }
                    if(checkedId==R.id.btnq3No){
                        q3NoBtn.setSelected(true);
                    }
                }
            }
        });

        toggleGroupQ4.addOnButtonCheckedListener(new MaterialButtonToggleGroup.OnButtonCheckedListener() {
            @Override
            public void onButtonChecked(MaterialButtonToggleGroup group, int checkedId, boolean isChecked) {

                q4YesBtn.setSelected(false);
                q4NoBtn.setSelected(false);

                if(isChecked){
                    if(checkedId==R.id.btnq4Yes){
                        q4YesBtn.setSelected(true);
                    }
                    if(checkedId==R.id.btnq4No){
                        q4NoBtn.setSelected(true);
                    }
                }
            }
        });

        submitRiskBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                int score = 0;
                boolean isSelected = false;
                isSelected = (q2YesBtn.isSelected() || q2NoBtn.isSelected()) && (q3YesBtn.isSelected() || q3NoBtn.isSelected()) && (q4YesBtn.isSelected() || q4NoBtn.isSelected());
                if(!isSelected) {
                    Toast.makeText(requireContext(), "Please answer all the questions selected.", Toast.LENGTH_SHORT).show();
                    return ;
                }

                if(q2YesBtn.isSelected())
                    score += 1;
                if(q3YesBtn.isSelected())
                    score += 1;
                if(q4YesBtn.isSelected())
                    score += 1;

                Log.i("Risk score", "="+ score);

                if(score < 3)
                    setStatus(R.drawable.risk_green, R.color.green_warning, R.color.white, "No Exposure Detected");
                else
                    setStatus(R.drawable.risk_warning, R.color.yellow_warning, R.color.black_txt, "You Are At High Risk!");


            }
        });



        // Inflate the layout for this fragment
        return view;
    }

    private void setStatus(int imageRes, int bgRes,int colorRes, String riskState) {
        riskLogoIv.setImageResource(imageRes);
        riskCv.setCardBackgroundColor(bgRes);
        riskLogoIv.setImageTintList(ColorStateList.valueOf(colorRes));
        statusTv.setTextColor(colorRes);
        riskTv.setTextColor(colorRes);
        riskTv.setText(riskState);
    }
}