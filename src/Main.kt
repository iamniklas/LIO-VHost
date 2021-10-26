import javax.swing.JFrame
import java.awt.FlowLayout
import javax.swing.JLabel
import led.LEDStripManager
import kotlin.Throws
import java.lang.InterruptedException
import kotlin.jvm.JvmStatic
import network.ReceiveCallback
import network.LEDChangeModel
import com.google.gson.GsonBuilder
import com.google.gson.FieldNamingPolicy
import procedures.ProcedureType
import led.LEDDataBundle
import network.Server
import procedures.Procedure
import procedures.ProcedureFactory
import java.awt.Color
import java.awt.EventQueue
import java.awt.Font
import java.lang.Runnable
import java.lang.Exception

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
            leds[i] = lblNewLabel
            frmLioVhost!!.contentPane.add(lblNewLabel)
        }
    }

    companion object {
        private val leds = arrayOfNulls<JLabel>(300)
        private var windowReady = false
        private var ledMng: LEDStripManager? = null
        private var mServer: Server? = null

        /**
         * Launch the application.
         * @throws InterruptedException
         */
        @Throws(InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            mServer = Server(3333)

            mServer!!.setListener(object : ReceiveCallback {
                override fun onReceiveMessage(_message: String?) {
                    println(_message)
                    val changeModel = GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                        .fromJson(_message, LEDChangeModel::class.java)
                    val type = changeModel.mProcedure
                    val bundle = changeModel.mBundle!!
                    bundle.ledStrip = ledMng
                    bundle.procedureCalls = ledMng
                    val p = ProcedureFactory.getProcedure(type, bundle)!!
                    ledMng!!.mProcContainer.removeCurrentProcedure()
                    ledMng!!.mProcContainer.queueProcedure(p)
                }
            })

            mServer!!.start()
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
            ledMng = LEDStripManager(leds, false)
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