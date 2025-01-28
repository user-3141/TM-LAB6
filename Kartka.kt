package com.example.lab6_2024_2025_pl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import java.util.*

class Kartka : AppCompatActivity() {
    var name = "";
    var gifts = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //WebView - kontrolka wyswietlajaca html
        val page = WebView(this)

        //wlaczenie obslugi JS
        page.settings.javaScriptEnabled=true

        //dodanie interfejsu pomiędzy Kotlinem a JS
        //this - obiekt tej klasy dostarcza metody JSInterface, activity - nazwa widoczna w JS
        page.addJavascriptInterface(this, "activity") //ODKOMENTOWAC DLA JS

        name = intent.getStringExtra("name").toString()
        gifts = intent.getStringExtra("gifts").toString()
        
        //zaladowanie zawartosci kontroli WebView - pliki z katalogu assests w projekcie
        page.loadUrl("file:///android_asset/Kartka.html")

        //wstawienie kontrolki WebView jako calej fasady aktywnosci
        setContentView(page)

    }

    @JavascriptInterface
    fun getName(): String {
        return name
    }

    @JavascriptInterface
    fun getGifts(): String {
        return gifts
    }
}
