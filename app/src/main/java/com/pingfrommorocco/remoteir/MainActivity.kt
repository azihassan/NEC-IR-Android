package com.pingfrommorocco.remoteir

import android.hardware.ConsumerIrManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private val TAG = "RemoteIR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val infraredService = getSystemService(CONSUMER_IR_SERVICE) as ConsumerIrManager
        val transformer = NecTransformer()

        Log.d(TAG, "has emitter: ${infraredService.hasIrEmitter()}")
        infraredService.carrierFrequencies.forEach {
            Log.d(TAG, "carrier frequency: ${it.minFrequency}-${it.maxFrequency}")
        }

        val buttonCommandMapping = mapOf(
            R.id.power_button to NecCode.POWER,
            R.id.source_button to NecCode.SOURCE,
            R.id.channel_up_button to NecCode.CHANNEL_UP,
            R.id.channel_down_button to NecCode.CHANNEL_DOWN,
            R.id.ok_button to NecCode.OK,
            R.id.up_button to NecCode.UP,
            R.id.down_button to NecCode.DOWN,
            R.id.left_button to NecCode.LEFT,
            R.id.right_button to NecCode.RIGHT,
            R.id.play_button to NecCode.PLAY,
            R.id.stop_button to NecCode.STOP,
            R.id.fast_backward_button to NecCode.FAST_BACKWARD,
            R.id.fast_forward_button to NecCode.FAST_FORWARD,
        )

        val repeatableButtonCommandMapping = mapOf(
            R.id.volume_up_button to NecCode.VOLUME_UP,
            R.id.volume_down_button to NecCode.VOLUME_DOWN,
        )

        buttonCommandMapping.entries.forEach {
            val (button, command) = it
            findViewById<Button>(button).setOnClickListener {
                val pulses = transformer.transformMessage(NecCode.ADDRESS.code, command.code)
                Log.d(TAG, "emitting: ${pulses.map { it }}")
                infraredService.transmit(38000, pulses.toIntArray())
            }
        }

        repeatableButtonCommandMapping.entries.forEach {
            val (button, command) = it
            findViewById<MaterialButton>(button).setOnTouchListener { view, motionEvent ->
                when(motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val pulses =
                            transformer.transformMessage(NecCode.ADDRESS.code, command.code)
                        Log.d(TAG, "emitting: ${pulses.map { it }}")
                        infraredService.transmit(38000, pulses.toIntArray())
                    }
                    MotionEvent.ACTION_BUTTON_PRESS -> {
                        view.performClick()
                    }
                }
                true
            }
        }
    }
}