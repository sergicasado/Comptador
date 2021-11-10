package me.sergicasado.dam.comptador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import me.sergicasadopellissa.dam.comptador.R

class MainActivity : AppCompatActivity() {

    private val INITIAL_TIME = 20

    private val TAG = MainActivity::class.java.simpleName

    internal lateinit var tapMeButton : Button
    internal lateinit var timeTextView : TextView
    internal lateinit var counterTextView : TextView
    internal var counter = 0
    internal var time = INITIAL_TIME

    internal var appStarted = false
    internal lateinit var countdownTimer : CountDownTimer
    internal val initialCountDownTimer: Long = time.toLong() *1000
    internal val intervalCountDownTimer: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"Hola mon! onCreate")
        Log.d(TAG,counter.toString())
        Log.d(TAG,time.toString())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCountdown()

        tapMeButton = findViewById(R.id.tapMeButton)
        timeTextView = findViewById(R.id.timeTextView)
        counterTextView = findViewById(R.id.counterTextView)

        tapMeButton.setOnClickListener {
            if (!appStarted) {
                startGame()
            }
            incrementCounter()
        }
        timeTextView.text = getString(R.string.timeText, time)
    }

    private fun startGame() {
        countdownTimer.start()
        appStarted = true
    }

    private fun initCountdown() {
        countdownTimer = object : CountDownTimer(initialCountDownTimer,intervalCountDownTimer) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                timeTextView.text = timeLeft.toString()
            }

            override fun onFinish() {
                endGame()
            }
        }
    }

    private fun incrementCounter() {
        //        counter = counter +1
        counter += 1
        counterTextView.text = counter.toString()
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.endGame, counter), Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun resetGame() {
        // RESET PUNTUACIÓ A ZERO
        counter = 0
        counterTextView.text = counter.toString()

        // REINICIALITZAR EL COMPTADOR
        time = INITIAL_TIME
        timeTextView.text = time.toString()
        initCountdown()

        // GAME STARTED A FALSE
        appStarted = false


    }
}