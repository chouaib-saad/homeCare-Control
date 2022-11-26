package com.example.myapplication;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataClass {

    public String name,email,password,phoneNumber;
//    public static String nameDB,emailDB,phoneDB,passwordDB;


    public DataClass() {
    }

    public DataClass(String name, String email, String password, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    //    public DataClass(String name, String email, String password, String phoneNumber) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.phoneNumber = phoneNumber;
//    }


//    public static String getName(String userPhone) {
//
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
//        DatabaseReference user = usersRef.child(userPhone);
//        DatabaseReference userNameRef = user.child("name");
//
//
//
//        userNameRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                nameDB = snapshot.getValue(String.class);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//
//        });
//
////        this.name = nameDB;
//        return nameDB;
//    }
//
////    public void setName(String name) {
////        this.name = name;
////
////            }
//
//
//    public static String getEmail(String userPhone) {
//
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
//        DatabaseReference user = usersRef.child(userPhone);
//        DatabaseReference userEmailRef = user.child("email");
//
//
//
//        userEmailRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                emailDB = snapshot.getValue(String.class);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//
//        });
//
////        this.email = emailDB;
//        return emailDB;
//    }
//
////    public void setEmail(String email) {
////        this.email = email;
////    }
//
//    public static String getPassword(String userPhone) {
//
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
//        DatabaseReference user = usersRef.child(userPhone);
//        DatabaseReference userPasswordRef = user.child("password");
//
//
//
//        userPasswordRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                passwordDB = snapshot.getValue(String.class);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//
//        });
//
////        this.password = passwordDB;
//        return passwordDB;
//    }
//
////    public void setPassword(String password) {
////        this.password = password;
////    }
//
//    public static String getPhoneNumber(String userPhone) {
//
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
//        DatabaseReference user = usersRef.child(userPhone);
//        DatabaseReference userPhoneRef = user.child("phoneNumber");
//
//
//
//        userPhoneRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                phoneDB = snapshot.getValue(String.class);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//
//        });
//
////        this.phoneNumber = phoneDB;
//        return phoneDB;
//    }

//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }







}
