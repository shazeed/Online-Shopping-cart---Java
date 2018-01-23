import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * <pre>
 * The object of this class is the sign up view for customers to create a new account.
 * 
 * To create a new account, customers are required to enter their user id and password.
 * </pre>
 */
public class SignupView extends View {

	private static final long serialVersionUID = 1L;
	
	private JTextField userID, pwd;
	private JButton newAccButton;
	private JPanel panel_2;
	private JPanel panel_1;
	private JLabel lblUserId;
	private JPanel panel_3;
	private JLabel lblPassword;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblNewLabel;
	private Component verticalStrut;
	private JPanel panel_7;
	private JLabel lblConfirmPassword;
	private JTextField pwdConfirm;
	private JButton btnNewButton;

	/**
	 * <pre>
	 * SignupView constructor.
	 * </pre>
	 */
	public SignupView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		panel_6 = new JPanel();
		panel_5.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		lblNewLabel = new JLabel();
		//lblNewLabel.setIcon(ShopController.LOGO_ICON);
		panel_6.add(lblNewLabel);
		
		panel_4 = new JPanel();
		panel_5.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		verticalStrut = Box.createVerticalStrut(20);
		panel_4.add(verticalStrut);
		
		panel_1 = new JPanel();
		panel_4.add(panel_1);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		lblUserId = new JLabel("Choose a user ID");
		panel_1.add(lblUserId);
		
		userID = new JTextField();
		panel_4.add(userID);
		userID.setColumns(10);
		
		panel_3 = new JPanel();
		panel_4.add(panel_3);
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		
		lblPassword = new JLabel("Choose a password");
		panel_3.add(lblPassword);
		
		pwd = new JTextField();
		panel_4.add(pwd);
		pwd.setColumns(10);
		
		panel_7 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_4.add(panel_7);
		
		lblConfirmPassword = new JLabel("Confirm your password");
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
		panel_7.add(lblConfirmPassword);
		
		pwdConfirm = new JTextField();
		pwdConfirm.setColumns(10);
		panel_4.add(pwdConfirm);
		
		panel_2 = new JPanel();
		panel_4.add(panel_2);
		
		newAccButton = new JButton("Create my account");
		newAccButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getController().signup(userID.getText(), pwd.getText(), pwdConfirm.getText());
			}
		});
		
		btnNewButton = new JButton("Back to login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getController().setView(new LoginView());
			}
		});
		panel_2.add(btnNewButton);
		panel_2.add(newAccButton);
		
		panel_4.setMaximumSize( new Dimension(300, 200) );
		
	}

	/**
	 * <pre>
	 * Override abstract method initialize() in View Class
	 * </pre>
	 */
	public void initialize() {
		
	}

}
