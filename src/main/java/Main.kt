import com.github.iamniklas.liocore.config.ProgramConfiguration
import com.github.iamniklas.liocore.led.LEDStripManager
import com.github.iamniklas.liocore.network.*
import com.github.iamniklas.liocore.network.mqtt.IMqttCallback
import com.github.iamniklas.liocore.network.mqtt.MQTTListener
import com.github.iamniklas.liocore.procedures.ProcedureFactory
import led.SwingRenderer
import java.awt.Color
import java.awt.EventQueue
import java.awt.FlowLayout
import java.awt.Font
import javax.swing.JFrame
import javax.swing.JLabel

class Main {
    private var frmLioVhost: JFrame? = null

    /**
     * Initialize the contents of the frame.
     */
    private fun initialize() {
        frmLioVhost = JFrame()
        frmLioVhost!!.isResizable = false
        frmLioVhost!!.contentPane.background = Color.DARK_GRAY
        frmLioVhost!!.contentPane.layout = FlowLayout(FlowLayout.LEFT, 0, 0)
        frmLioVhost!!.title = "LIO V-Host"
        frmLioVhost!!.setBounds(50, 100, 1825, 50)
        frmLioVhost!!.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        for (i in 0..299) {
            val lblNewLabel = JLabel("■")
            lblNewLabel.font = Font("Tahoma", Font.BOLD, 10)
            lblNewLabel.foreground = Color.BLACK
            leds!![i] = lblNewLabel
            frmLioVhost!!.contentPane.add(lblNewLabel)
        }

        mqttClient = MQTTListener(object : IMqttCallback {
            override fun onLEDUpdateModelReceive(_updateModel: LEDUpdateModel?) {
                _updateModel!!.bundle.ledStrip = ledMng
                _updateModel.bundle.procedureCalls = ledMng
                val p = ProcedureFactory.getProcedure(_updateModel.procedure, _updateModel.bundle)!!
                ledMng!!.procContainer.removeCurrentProcedure()
                ledMng!!.procContainer.queueProcedure(p)
            }

            override fun onLEDUpdateModelReceiveAll(_updateModel: LEDUpdateModel?) {
                _updateModel!!.bundle.ledStrip = ledMng
                _updateModel.bundle.procedureCalls = ledMng
                val p = ProcedureFactory.getProcedure(_updateModel.procedure, _updateModel.bundle)!!
                ledMng!!.procContainer.removeCurrentProcedure()
                ledMng!!.procContainer.queueProcedure(p)
            }

            override fun onLEDValueUpdateModelReceive(_valueUpdateModel: LEDUpdateModel?) {

            }

            override fun onLEDValueUpdateModelReceiveAll(_valueUpdateModel: LEDUpdateModel?) {

            }
        })

        //ASUS-Niklas-2020:D8-3B-BF-12-68-21
        mqttClient.connect()
    }

    companion object {
        private var leds: Array<JLabel?>? = null
        private var renderer: SwingRenderer? = null

        private var windowReady = false
        private var ledMng: LEDStripManager? = null

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
            renderer = SwingRenderer(leds!!, ProgramConfiguration.configuration.ledCount)

            EventQueue.invokeLater {
                try {
                    val window = Main()
                    window.frmLioVhost!!.isVisible = true
                    windowReady = true
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            Thread.sleep(1000)

            ledMng = LEDStripManager(renderer, ProgramConfiguration.configuration.ledCount)
            while (true) {
                ledMng!!.update()
            }
        }
    }

    /**
     * Create the application.
     */
    init {
        initialize()
    }
}