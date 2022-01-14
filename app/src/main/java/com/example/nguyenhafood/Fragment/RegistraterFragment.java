package com.example.nguyenhafood.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.nguyenhafood.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistraterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistraterFragment extends Fragment implements View.OnClickListener{

    RadioGroup rdigr_gioitinh;
    Button btn_dk;
    DatePickerDialog datepicke;
    private TextInputLayout text_input_dateofbirth_dk;
    private TextInputLayout text_input_name_dk, text_input_phone_dk, text_input_Address_dk, text_input_username_dk,
            text_input_password_dk, text_input_passwordagain_dk, text_input_referralcode_dk, text_input_email_dk, text_input_gender;

    private TextInputEditText edt_dayofbirth,edt_phone_dk, edt_Name_dk,edt_username, edt_pass_dk, edt_passa_dk, edt_eamil_dk;
    private static final Pattern USERNAME_PRATTERN = Pattern.compile(".*[!@#$%^&*+=., <>()|`~?-].*");
    private static final Pattern PASSWORD_PRATTERN = Pattern.compile(("^" +
            "(?=.*[@#$%^&+=])" +     // Có ít nhất một ký tự đặt biệt
            "(?=\\S+$)" +            // Không có khoản trắng
            ".{8,}" +                // Có ít nhất 8 ký tự
            "$"));
    private static final Pattern EMAIL_PRATTERN = Pattern.compile("[a-zA-Z0-9+._%-+]{1,256}" + "@"
            + "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" + "(" + "."
            + "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" + ")+");
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistraterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistraterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistraterFragment newInstance(String param1, String param2) {
        RegistraterFragment fragment = new RegistraterFragment();
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
        final View view = inflater.inflate(R.layout.fragment_registrater, container, false);
        text_input_dateofbirth_dk = view.findViewById(R.id.text_input_dateofbirth_dk);
        text_input_name_dk = view.findViewById(R.id.text_input_name_dk);
        text_input_phone_dk = view.findViewById(R.id.text_input_phone_dk);
        text_input_Address_dk = view.findViewById(R.id.text_input_Address_dk);
        text_input_username_dk = view.findViewById(R.id.text_input_username_dk);
        text_input_password_dk = view.findViewById(R.id.text_input_password_dk);
        text_input_passwordagain_dk = view.findViewById(R.id.text_input_passwordagain_dk);
        text_input_gender = view.findViewById(R.id.text_input_gender);
        text_input_referralcode_dk = view.findViewById(R.id.text_input_referralcode_dk);
        text_input_email_dk = view.findViewById(R.id.text_input_email_dk);

        rdigr_gioitinh = view.findViewById(R.id.rdigr_gioitinh);
        edt_dayofbirth = view.findViewById(R.id.edt_dayofbirth);
        edt_phone_dk = view.findViewById(R.id.edt_phone_dk);
        edt_Name_dk = view.findViewById(R.id.edt_Name_dk);
        edt_username = view.findViewById(R.id.edt_username);
        edt_pass_dk = view.findViewById(R.id.edt_pass_dk);
        edt_passa_dk = view.findViewById(R.id.edt_passa_dk);
        edt_eamil_dk = view.findViewById(R.id.edt_eamil_dk);

        edt_dayofbirth.setInputType(InputType.TYPE_NULL);
        edt_dayofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicke();
            }
        });
        btn_dk=view.findViewById(R.id.btn_dk);
        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDangky();
            }
        });

        edt_phone_dk.addTextChangedListener(new MytextWatcher(edt_phone_dk));
        edt_dayofbirth.addTextChangedListener(new MytextWatcher(edt_dayofbirth));
        edt_eamil_dk.addTextChangedListener(new MytextWatcher(edt_eamil_dk));
        edt_Name_dk.addTextChangedListener(new MytextWatcher(edt_Name_dk));
        edt_pass_dk.addTextChangedListener(new MytextWatcher(edt_pass_dk));
        edt_passa_dk.addTextChangedListener(new MytextWatcher(edt_passa_dk));
        edt_username.addTextChangedListener(new MytextWatcher(edt_username));

        return  view;
    }
    public void datepicke(){
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        datepicke = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                edt_dayofbirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, year, month, day);

        datepicke.getDatePicker().setCalendarViewShown(false);
        datepicke.show();
    }
    public boolean validateName(){
        String Nameinput = text_input_name_dk.getEditText().getText().toString().trim();
        if (Nameinput.isEmpty()){
            text_input_name_dk.setError("Item này không được rỗng");
            return false;
        }
        else
            text_input_name_dk.setError(null);
            return true;

    }
    public boolean validatePhone(){
        String phoneinput = text_input_phone_dk.getEditText().getText().toString().trim();
        if (phoneinput.isEmpty()){
            text_input_phone_dk.setError("Item này không được rỗng");
            return false;
        }
        else if (phoneinput.length() > 10 | phoneinput.length() < 9){
            text_input_phone_dk.setError("Số điện thoại bạn không phù hợp");
            return false;
        }
        else
            text_input_phone_dk.setError(null);
        return true;
    }
    public boolean validateUsername(){
        String Usernameinput = text_input_username_dk.getEditText().getText().toString().trim();
        if (Usernameinput.isEmpty()){
            text_input_username_dk.setError("Item này không được rỗng");
            return false;
        }
        else if (Usernameinput.length() > 20){
            text_input_username_dk.setError("Không được vượt quá 20 kí tự");
            return false;
        }
        else if (USERNAME_PRATTERN.matcher(Usernameinput).matches()){
            text_input_username_dk.setError("Username không được chứa kí tự đặt biệt");
            return false;
        }
        else
            text_input_username_dk.setError(null);
        return true;

    }
    public boolean validatePassword(){
        String passwordnameinput = text_input_password_dk.getEditText().getText().toString().trim();
        if (passwordnameinput.isEmpty()){
            text_input_password_dk.setError("Item này không được rỗng");
            return false;
        }
        else if (!PASSWORD_PRATTERN.matcher(passwordnameinput).matches()){
            text_input_password_dk.setError("Mật khẩu của bạn quá yếu(có ít nhất một ký tự đặc biệt, không có khoản trắng,có ít nhất 8 ký tự)");
            return false;
        }
        else
            text_input_password_dk.setError(null);
        return true;

    }
    public boolean validatePasswordagain(){
        String passwordagaininput = text_input_passwordagain_dk.getEditText().getText().toString().trim();
        String passwordnameinput = text_input_password_dk.getEditText().getText().toString().trim();
        if (passwordagaininput.isEmpty()){
            text_input_passwordagain_dk.setError("Item này không được rỗng");
            return false;
        }
        else if (!passwordnameinput.equals(passwordagaininput)){
            text_input_passwordagain_dk.setError("Bạn nhập lại mật khẩu không khớp");
            return false;
        }
        else
            text_input_passwordagain_dk.setError(null);
        return true;

    }
    public boolean validateEmail(){
        String Emailinput = text_input_email_dk.getEditText().getText().toString().trim();
        if (Emailinput.isEmpty()){
            text_input_email_dk.setError("Item này không được rỗng");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(Emailinput).matches()){
            text_input_email_dk.setError("Email bạn nhập không hợp lệ");
            return false;
        }
        else
            text_input_email_dk.setError(null);
        return true;

    }
    public boolean validatedateofbirt(){
        String dateofbirtinput = text_input_dateofbirth_dk.getEditText().getText().toString().trim();
        if (dateofbirtinput.isEmpty()){
            text_input_dateofbirth_dk.setError("Item này không được rỗng");
            return false;
        }
        else
            text_input_dateofbirth_dk.setError(null);
        return true;

    }
    public boolean validateGENDER(){
        if (rdigr_gioitinh.getCheckedRadioButtonId() == -1){
            text_input_gender.setError("Vui lòng chọn giới tính");
            return false;
        }
        else
            text_input_gender.setError(null);
            return true;

    }

    @Override
    public void onClick(View view) {

    }

        public void confirmDangky(){

        if ( !validateGENDER() | !validatePhone()| !validatedateofbirt() | !validateEmail() | !validatePassword() | !validatePasswordagain() | !validateUsername() | !validateName()){
            return;
        }

    }
    private class MytextWatcher implements TextWatcher{
        private View view;
        private MytextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.edt_Name_dk:
                    validateName();
                    break;
                case R.id.edt_phone_dk:
                    validatePhone();
                    break;
                case R.id.edt_dayofbirth:
                    validatedateofbirt();
                    break;
                case R.id.edt_username:
                    validateUsername();
                    break;
                case R.id.edt_pass_dk:
                    validatePassword();
                    break;
                case R.id.edt_passa_dk:
                    validatePasswordagain();
                    break;
                case R.id.edt_eamil_dk:
                    validateEmail();
                    break;
            }
        }
    }

}
