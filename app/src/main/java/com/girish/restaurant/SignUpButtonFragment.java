package com.girish.restaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.girish.restaurant.signUpDetail.SignUpFragmentDetail;
import com.girish.restaurant.signUpDetail.SignUpViewModelDetail;


// Dont try to copy classes

public class SignUpButtonFragment extends BaseFragment implements View.OnClickListener{
    private Button signUpButton;
    SignUpViewModelDetail signUpViewModel;// We dont need View models for this class at all.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_button, container, false);
        init(view);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUpViewModel= ViewModelProviders.of(this).get(SignUpViewModelDetail.class);
    }
    //Below line of code is copied from other class.
    public void inputValidation( String firstname,String lastName,String email,String phone){
        if(firstname.isEmpty() || lastName.isEmpty() || email.isEmpty()){
            Toast.makeText(getActivity(),"Enter all the details, Phone number(Optional) ",Toast.LENGTH_SHORT).show();
        }
        else{
            signUpViewModel.signUpData(firstname,lastName,email,phone);
            Toast.makeText(getActivity(), "Sucess", Toast.LENGTH_LONG).show();
        }
    }
    public void init(View view){
        signUpButton=view.findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_button:
                //SignUpFragmentDetail fragment = new SignUpFragmentDetail();
                ((MainActivity) getActivity()).addFragment(new SignUpFragmentDetail());
                break;
        }
    }
}
