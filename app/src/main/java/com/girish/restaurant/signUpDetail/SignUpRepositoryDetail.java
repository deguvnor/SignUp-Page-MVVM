package com.girish.restaurant.signUpDetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class SignUpRepositoryDetail {
    MutableLiveData signUpData= new MutableLiveData();
    public LiveData signUpData(String firstName, String lastName, String email, String phone){
        //Network call to the resturant api's

        return signUpData;
    }
}
