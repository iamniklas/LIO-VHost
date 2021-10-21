import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.Color;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import led.LEDDataBundle;
import led.LEDStripManager;
import network.LEDChangeModel;
import network.ReceiveCallback;
import network.Server;
import procedures.Procedure;
import procedures.ProcedureFactory;
import procedures.ProcedureType;
import java.awt.FlowLayout;

public class Main {

	private JFrame frmLioVhost;
	private static JLabel[] leds = new JLabel[300];
	private static boolean windowReady = false;
	
	private static LEDStripManager ledMng;
	private static Server mServer;
	
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		mServer = new Server(3333);
		mServer.setListener(new ReceiveCallback() {
			
			@Override
			public void onReceiveMessage(String _message) {
				System.out.println(_message);
				LEDChangeModel changeModel = 
						new GsonBuilder()
						.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
						.create()
							.fromJson(_message, LEDChangeModel.class);
				
				ProcedureType type = changeModel.mProcedure;
				LEDDataBundle bundle = changeModel.mBundle;
				bundle.ledStrip = ledMng;
				bundle.procedureCalls = ledMng;
				
				Procedure p = ProcedureFactory.getProcedure(type, bundle);
				
				ledMng.mProcContainer.removeCurrentProcedure();
				ledMng.mProcContainer.queueProcedure(p);
			}
		});
		mServer.start();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmLioVhost.setVisible(true);
					windowReady = true;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread.sleep(1000);
		ledMng = new LEDStripManager(leds, false);
		
		while(true) {
			ledMng.update();
		}
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLioVhost = new JFrame();
		frmLioVhost.setResizable(false);
		frmLioVhost.getContentPane().setBackground(Color.DARK_GRAY);
		frmLioVhost.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		frmLioVhost.setTitle("LIO V-Host");
		frmLioVhost.setBounds(50, 100, 1825, 50);
		frmLioVhost.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (int i = 0; i < 300; i++) {
			JLabel lblNewLabel = new JLabel("■");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
			lblNewLabel.setForeground(Color.BLACK);
			leds[i] = lblNewLabel;
			
			frmLioVhost.getContentPane().add(lblNewLabel);
		}
	}
}
