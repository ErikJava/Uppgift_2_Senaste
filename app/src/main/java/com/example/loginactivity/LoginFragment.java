package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class LoginFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public LoginFragment() {

    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnmain = view.findViewById(R.id.mainBtn2);
        btnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button btnsubmit = view.findViewById(R.id.submitBtn);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                collectUserData();
                Toast.makeText(getActivity(), "Information sent!", Toast.LENGTH_SHORT).show();
                clearUserInputFields();
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    private void collectUserData() {
        EditText nameEditText = getView().findViewById(R.id.names);
        String fullName = nameEditText.getText().toString();

        EditText ageEditText = getView().findViewById(R.id.age);
        String age = ageEditText.getText().toString();

        EditText phoneEditText = getView().findViewById(R.id.phone);
        String phone = phoneEditText.getText().toString();

        CheckBox conditionsCheckBox = getView().findViewById(R.id.conditions);
        boolean conditionsChecked = conditionsCheckBox.isChecked();

        RadioGroup radioGroup = getView().findViewById(R.id.radioGroup);
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = getView().findViewById(selectedRadioButtonId);
        String selectedRadioText = "";

        if (selectedRadioButton != null) {
            selectedRadioText = selectedRadioButton.getText().toString();
        }
    }


    private void clearUserInputFields() {
        EditText nameEditText = getView().findViewById(R.id.names);
        EditText ageEditText = getView().findViewById(R.id.age);
        EditText phoneEditText = getView().findViewById(R.id.phone);


        nameEditText.setText("");
        ageEditText.setText("");
        phoneEditText.setText("");

        CheckBox conditionsCheckBox = getView().findViewById(R.id.conditions);
        conditionsCheckBox.setChecked(false);

        RadioGroup radioGroup = getView().findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

    }

}