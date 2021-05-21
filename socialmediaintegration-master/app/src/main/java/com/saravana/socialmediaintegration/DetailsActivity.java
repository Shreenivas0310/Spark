package com.saravana.socialmediaintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private TextView usernameText,emailTextView;
    private CircleImageView imageView;
    private Button logOutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        initWidgets();
        setUserDetails();
    }

    private void initWidgets() {
        usernameText = findViewById(R.id.unameText);
        emailTextView = findViewById(R.id.emailText);
        imageView = findViewById(R.id.imageView);
        logOutBtn  = findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(view-> signOut());
    }

    private void signOut(){
        if(mAuth!=null)
             mAuth.signOut();
        finish();
    }
    private void setUserDetails(){
        if(firebaseUser!=null) {
            usernameText.setText(firebaseUser.getDisplayName());
            emailTextView.setText(firebaseUser.getEmail());
            Glide.with(this)
                    .load(firebaseUser.getPhotoUrl())
                    .placeholder(R.drawable.dp_placeholder)
                    .into(imageView);
        }else{
            Toast.makeText(this,"Not logged in",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        signOut();
    }
}