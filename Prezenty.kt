package com.example.lab6_2024_2025_pl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import java.util.*

class Prezenty : AppCompatActivity() {
    var giftList = ArrayList<String>()
    var name = ""
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //WebView - kontrolka wyswietlajaca html
        val page = WebView(this)

        //wlaczenie obslugi JS
        page.settings.javaScriptEnabled=true

        //dodanie interfejsu pomiędzy Kotlinem a JS
        //this - obiekt tej klasy dostarcza metody JSInterface, activity - nazwa widoczna w JS
        page.addJavascriptInterface(this, "activity")

        //zaladowanie zawartosci kontroli WebView - pliki z katalogu assests w projekcie
        page.loadUrl("file:///android_asset/Prezenty.html")

        //wstawienie kontrolki WebView jako calej fasady aktywnosci
        setContentView(page)
    }

    @JavascriptInterface //adnotacja sygnalizujaca ze metoda bedzie dostepna z poziomu JS
    fun sayHello(name: String) {
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun getDate(): String {
        return Date().toString()
    }

    @JavascriptInterface
    fun addGift(gift: String) {
        giftList.add(gift)
    }

    @JavascriptInterface
    fun generate(setname: String) {
        name = setname
        
    }
}
