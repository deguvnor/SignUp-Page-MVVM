package com.girish.restaurant.signUpDetail;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import com.girish.restaurant.BaseFragment;
import com.girish.restaurant.R;


public class SignUpFragmentDetail extends BaseFragment implements View.OnClickListener {

    // mSubmitBtn - is the naming conventions for member variables
    // Also use private methods.
    private Button submitButton;
    private EditText firstNameEdit;
    private String firstname = ""; // mark everything private(Encapsulation)
    private EditText lastNameEdit;
    private String lastName = "";
    private EditText emailEdit;
    private String email = "";
    private EditText phoneNumbers;
    String phone = "";
    SignUpViewModelDetail signUpViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_detail, container, false);
        System.out.println("***** A Fragments stack size : " + getActivity().getSupportFragmentManager().getFragments().size()); //Dont use S.O.P
        init(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModelDetail.class);
    }

    //Make this method private.
    public void inputValidation(String firstname, String lastName, String email, String phone) {
        if (firstNameEdit.getText().toString().isEmpty() || lastName.isEmpty() || email.isEmpty() && emailValidation(email)) {
            hideKeyboard(getActivity());
            Toast.makeText(getActivity(), "Enter all the details, Phone number(Optional) ", Toast.LENGTH_SHORT).show();
        } else {
            signUpViewModel.signUpData(firstname, lastName, email, phone);
            Toast.makeText(getActivity(), "Sucess", Toast.LENGTH_LONG).show();
            new AlertDialog.Builder(getContext())
                    .setTitle("Loyalty Signup") // Localize this to Strings.
                    .setMessage("Sucess")
                    .setPositiveButton(android.R.string.ok, null)
                    .show();

            clearEditText();
        }
    }

    public void clearEditText() {
        firstNameEdit.setText(" ");// Try to clear the focus instead of setting the null text.
        lastNameEdit.setText(" ");
        emailEdit.setText(" ");
        phoneNumbers.setText(" ");
    }

    public void init(View view) {
        firstNameEdit = view.findViewById(R.id.edit_first_name);
        lastNameEdit = view.findViewById(R.id.edit_last_name);
        emailEdit = view.findViewById(R.id.edit_email_id);
        phoneNumbers = view.findViewById(R.id.edit_phone_number);
        submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(this);
    }

    public void getData() {
        firstname = firstNameEdit.getText().toString();
        lastName = lastNameEdit.getText().toString();
        email = emailEdit.getText().toString();
        phone = phoneNumbers.getText().toString();
    }
//Test this method first
    // Validation code should be global to all classes in util class. NO logical code in views
    public boolean emailValidation(CharSequence target) {

        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    //Dont use static methods
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // Dont overide unnecessary lifecycle methods until you use it.
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_button:
                getData();
                inputValidation(firstname, lastName, email, phone);
                break;
        }
    }


}
