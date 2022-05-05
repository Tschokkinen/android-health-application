package com.ericaskari.healthapplication.validators;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

/**
 * Used to check if all of the user data input fields are valid
 *  @author Gavril Tschokkinen, Mohammad Askari
 */
public class UserModelValidation {
    TextView firstName;
    TextView lastName;
    TextView birthdate;
    TextView height;
    TextView weight;

    public UserModelValidation(
            TextView firstName,
            TextView lastName,
            TextView birthdate,
            TextView height,
            TextView weight
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.height = height;
        this.weight = weight;
    }

    /**
     * Used to check if any of the required fields is empty.
     * returns true if form is valid
     */
    public boolean validate() {
        Log.d("FirstLaunch", "A required field is null");
        boolean isValid = true;

        if(TextUtils.isEmpty(firstName.getText())) {
            firstName.setError("Pakollinen kenttä");
            isValid = false;
        }

        if(TextUtils.isEmpty(lastName.getText())) {
            lastName.setError("Pakollinen kenttä");
            isValid = false;
        }

        if(TextUtils.isEmpty(birthdate.getText())) {
            birthdate.setError("Pakollinen kenttä");
            isValid = false;
        }

        if(TextUtils.isEmpty(height.getText())) {
            height.setError("Pakollinen kenttä");
            isValid = false;
        }

        if(TextUtils.isEmpty(weight.getText())) {
            weight.setError("Pakollinen kenttä");
            isValid = false;
        }

        return isValid;
    }
}
