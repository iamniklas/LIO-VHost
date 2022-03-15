import com.github.iamniklas.colorspaces.ColorRGB
import com.github.iamniklas.liocore.config.ProgramConfiguration
import com.github.iamniklas.liocore.led.LEDDataBundle
import com.github.iamniklas.liocore.led.LEDStripManager
import com.github.iamniklas.liocore.network.*
import com.github.iamniklas.liocore.network.mqtt.IMqttCallback
import com.github.iamniklas.liocore.network.mqtt.MQTTListener
import com.github.iamniklas.liocore.procedures.ProcedureFactory
import com.github.iamniklas.liocore.procedures.ProcedureType
import com.google.gson.Gson
import led.SwingRenderer
import java.awt.Color
import java.awt.EventQueue
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.*

class Main {
    private lateinit var frmLioVhost: JFrame
    private lateinit var frmLioVhostScriptEditor: JFrame

    /**
     * Initialize the contents of the frame.
     */
    private fun initialize() {
        frmLioVhost = JFrame()
        frmLioVhost.isResizable = false
        frmLioVhost.contentPane.background = Color.DARK_GRAY
        frmLioVhost.contentPane.layout = FlowLayout(FlowLayout.LEFT, 0, 0)
        frmLioVhost.title = "LIO V-Host"
        frmLioVhost.setBounds(50, 100, 1825, 50)
        frmLioVhost.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        for (i in 0..299) {
            val lblNewLabel = JLabel("■")
            lblNewLabel.font = Font("Tahoma", Font.BOLD, 10)
            lblNewLabel.foreground = Color.BLACK
            leds!![i] = lblNewLabel
            frmLioVhost.contentPane.add(lblNewLabel)
        }


        frmLioVhostScriptEditor = JFrame()
        frmLioVhostScriptEditor.isResizable = false
        frmLioVhostScriptEditor.contentPane.background = Color.BLACK
        frmLioVhostScriptEditor.title = "Script Editor"
        frmLioVhostScriptEditor.setBounds(50, 200, 1825, 500)
        frmLioVhostScriptEditor.contentPane.layout = null

        val input = JTextArea("function update() {\n\n}")
        input.setBounds(0, 0, 1825, 350)

        //input.text = "indices = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 299]; function update(strip, procedure, step, totalSteps) {d = Math.abs(Math.sin(step * Math.PI/180)); for(i = 0; i < indices.length; i++) { strip.setPixel(indices[i], 0, parseInt(d * 255), 0); } if(step >= totalSteps) { procedure.finish(); } }"
        input.text =
            """
                indices = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 299];
                function update(strip, procedure, step, totalSteps) {
                    d = Math.abs(Math.sin(step * Math.PI/180)); 
                    for(i = 0; i < indices.length; i++) { 
                        strip.setPixel(indices[i], 0, parseInt(d * 255), 0); 
                    } 
                    if(step >= totalSteps) { 
                        procedure.finish(); 
                    } 
                }
            """.trimIndent()
        frmLioVhostScriptEditor.contentPane.add(input)

        val btn = JButton("Run Script")
        btn.setBounds(0, 350, 1825, 125)
        btn.addActionListener {
            val ledUpdateModel = LEDUpdateModel()
            ledUpdateModel.procedure = ProcedureType.Javascript
            ledUpdateModel.bundle = LEDDataBundle()
            ledUpdateModel.bundle.data = input.text
            ledUpdateModel.bundle.procedureCalls = ledMng
            ledUpdateModel.bundle.ledStrip = ledMng

            val p = ProcedureFactory.getProcedure(ledUpdateModel.procedure, ledUpdateModel.bundle)!!
            ledMng.procContainer.removeCurrentProcedure()
            ledMng.procContainer.queueProcedure(p)
        }
        frmLioVhostScriptEditor.contentPane.add(btn)

        frmLioVhostScriptEditor.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        mqttClient = MQTTListener(object : IMqttCallback {
            override fun onLEDUpdateModelReceive(_updateModel: LEDUpdateModel?, _callForAllDevices: Boolean) {
                _updateModel!!.bundle.ledStrip = ledMng
                _updateModel.bundle.procedureCalls = ledMng
                val p = ProcedureFactory.getProcedure(_updateModel.procedure, _updateModel.bundle)!!
                ledMng.procContainer.removeCurrentProcedure()
                ledMng.procContainer.queueProcedure(p)
            }

            override fun onLEDValueUpdateModelReceive(_valueUpdateModel: LEDUpdateModel?, _callForAllDevices: Boolean) {
                println("Call for Live Update")
                println(Gson().toJson(_valueUpdateModel))
                ledMng.procContainer.activeProcedure.updateLEDDataBundle(_valueUpdateModel!!.bundle)
            }
        })

        mqttClient.connect()
    }

    companion object {
        private var leds: Array<JLabel?>? = null
        private var renderer: SwingRenderer? = null

        private var windowReady = false
        private lateinit var ledMng: LEDStripManager

        private lateinit var mqttClient: MQTTListener

        /**
         * Launch the application.
         * @throws InterruptedException
         */
        @Throws(InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            ProgramConfiguration.configuration = ProgramConfiguration.readConfigFromFile()

            leds = arrayOfNulls(ProgramConfiguration.configuration.ledCount)
            renderer = SwingRenderer(leds!!)

            EventQueue.invokeLater {
                try {
                    val window = Main()
                    window.frmLioVhost.isVisible = true
                    window.frmLioVhostScriptEditor.isVisible = true
                    windowReady = true
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            Thread.sleep(1000)
            ledMng = LEDStripManager(renderer, ProgramConfiguration.configuration.ledCount)

            val ledDataBundle = LEDDataBundle()
            ledDataBundle.ledStrip = ledMng
            ledDataBundle.procedureCalls = ledMng
            ledDataBundle.colorPrimary = ColorRGB.WHITE50
            ledMng.procContainer.queueProcedure(ProcedureFactory.getProcedure(ProcedureType.ColorInstantSet, ledDataBundle))

            while (true) {
                ledMng.update()
                ledMng.render()
                ledMng.waitFrametime()
            }
        }
    }

    init {
        initialize()
    }
}