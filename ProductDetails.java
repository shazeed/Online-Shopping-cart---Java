import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * <pre>
 * This class is used to create a dialog box which provides customers the details of the product they choose.
 * 
 * In the dialog box, customers can add the product into cart.
 */
public class ProductDetails extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private final JPanel contentPanel = new JPanel();
    JSpinner spinner;

    /**
     * <pre>
     * Use this method to display the dialog box for customers.
     * </pre>
     * 
     * @param c the controller of the system
     * @param p the product chose by a customer
     */
    public static void display(ShopController c, Product p){
        ProductDetails dialog = new ProductDetails(c, p);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
    }

    /**
     * <pre>
     * ProductDetails constructor.
     * </pre>
     * 
     * @param c the controller of the system
     * @param p the product chose by a customer
     */
    public ProductDetails(ShopController c, Product p) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.NORTH);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            contentPanel.add(panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            {
                JLabel lblNewLabel_3 = new JLabel();
                lblNewLabel_3.setIcon(p.getImage());
                panel.add(lblNewLabel_3);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1);
                panel_1.setBorder(null);
                FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
                fl_panel_1.setAlignment(FlowLayout.LEFT);
                {
                    JLabel lblNewLabel_1 = new JLabel(p.getName());
                    panel_1.add(lblNewLabel_1);
                    lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
                    lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
                }
            }
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1);
                FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
                fl_panel_1.setAlignment(FlowLayout.LEFT);
                panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
                {
                    JLabel lblNewLabel_2 = new JLabel(p.getPropertiesAsText());
                    panel_1.add(lblNewLabel_2);
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JLabel lblNewLabel = new JLabel("Quantity:");
                buttonPane.add(lblNewLabel);
            }
            {
                spinner = new JSpinner();
                spinner.setModel(new SpinnerNumberModel(0, 0, p.getAmount(), 1));
                spinner.setPreferredSize(new Dimension(100, 30));
                buttonPane.add(spinner);
            }
            {
                JDialog me = this;
                JButton okButton = new JButton("Add to cart");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                       //System.out.println(spinner.getValue());
                       c.addToCart(p, Float.parseFloat(spinner.getValue() + ""));
                       me.dispose();
                    }
                });
                getRootPane().setDefaultButton(okButton);
            }
            {
                JDialog me = this;
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        me.dispose();
                    }
                });
                buttonPane.add(cancelButton);
            }
        }

}
}
