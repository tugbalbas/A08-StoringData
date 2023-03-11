package com.tugbaalbas.storingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);

        //SharedPreferences; bilgilerimizi saklayabilmek için çok küçük bir veri tabanı veriyor bize (app silinmediği sürece)
        //package name ve mode ister
        // Context.MODE_PRIVATE -> mini veryitabanına sadece benim uygulamamdan ulaşılsın
        sharedPreferences = this.getSharedPreferences("com.tugbaalbas.storingdata", Context.MODE_PRIVATE);

        int storedAge = sharedPreferences.getInt("storedAge",0); // geri alma işlemi default(kayıtlı veri yoksa default) O at

        if (storedAge == 0){
            textView.setText("Your age: ");
        }else {
            textView.setText("Your age: " + storedAge);
        }
    }

    public void save(View view){
        if (!editText.getText().toString().matches("")){
            int userAge = Integer.parseInt(editText.getText().toString());
            textView.setText("Your age: " +userAge);

            sharedPreferences.edit().putInt("storedAge",userAge).apply(); // bu satır ile kaydediyoruz (en sonki değer kaydedilecektir)

            //güncelleme yapmak aynı keyi farklı değerle kullanmak demek bu yüzden farklı bir işlem yapmamaıza gerek yok

            //kaydedilmiş veriyi silmek istersek farklı bir işlem yapmak gerekir


        }
    }

    public void delete(View view){
        // delete basıldığında storedAge 0 değilse silsin
        int storedData = sharedPreferences.getInt("storedAge",0);

        if (storedData != 0){
            sharedPreferences.edit().remove("storedAge").apply(); // VERECEĞİM KEY İLE KAYITLI BİR DEĞER VARSA ARTIK O DEĞERİ SİL
            textView.setText("Your age: ");
        }

    }
}