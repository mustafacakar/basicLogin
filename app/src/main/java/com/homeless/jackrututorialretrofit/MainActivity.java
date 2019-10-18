package com.homeless.jackrututorialretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.homeless.jackrututorialretrofit.model.ResObj;
import com.homeless.jackrututorialretrofit.remote.ApiUtils;
import com.homeless.jackrututorialretrofit.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPasword;
    Button btnLogin;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = (EditText) findViewById(R.id.editUsername);
        edtPasword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        userService = ApiUtils.getUserService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPasword.getText().toString();
                // validate form
                if(validateLogin(username,password)){
                    //doLogin
                    doLogin(username,password);

                }

            }
        });




    }

    private boolean validateLogin(String username , String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this,"Username is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || username.trim().length() == 0){
            Toast.makeText(this,"Password is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;


    }

    private void doLogin(final String username, final String password){
        Call<ResObj> call = userService.login(username,password);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if(response.isSuccessful()){
                    ResObj resObj = response.body();
                    if(resObj.getMessage().equals("true")){
                        //login start main activity
                        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                        intent.putExtra("username",username);
                     //   intent.putExtra("password",password);
                        startActivity(intent);

                    }else {
                        Toast.makeText(MainActivity.this,"Kullanıcı adı veya Şifre Yanlış",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Hata,Tekrar Deneyiniz",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
