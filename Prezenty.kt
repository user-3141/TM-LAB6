package com.example.lab6_2024_2025_pl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import java.util.*

class Prezenty : AppCompatActivity() {
    var giftList = ArrayList<String>()
    
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

    @JavascriptInterface
    fun addGift(gift: String) {
        giftList.add(gift)
    }

    @JavascriptInterface
    fun generate(name: String) {
        val intent = Intent(this, Kartka::class.java)
        intent.putExtra("name", name)
        var gifts = ""
        for (gift in giftList) {
            gifts += "$gift<br>" // Append each item followed by a space
        }
        intent.putExtra("giftList", gifts)
        startActivity(intent)
    }
}
