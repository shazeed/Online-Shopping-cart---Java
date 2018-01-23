import javax.swing.JDialog;
import javax.swing.JPanel;
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
import javax.swing.JTextField;

/**
 * <pre>
 * This class is used to create a dialog box which provides administrators the details of the products chose by them.
 * 
 * In the dialog box, administrators can add the products to database.
 * </pre>
 */
public class ProductDetailsForAdmin extends JDialog
{
    private final JPanel contentPanel = new JPanel();
    JSpinner spinner;

    /**
     * Constructor for objects of class ProductDetailsForAdmin
     * 
     * @param c the controller of the system
     * @param p the product chose by an administrator
     */
    public ProductDetailsForAdmin(ShopController c, Product p)
    {
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
                    JLabel jlName = new JLabel(p.getName());
                    panel_1.add(jlName);
                    jlName.setHorizontalAlignment(SwingConstants.LEFT);
                    jlName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
                }
            }
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1);
                FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
                fl_panel_1.setAlignment(FlowLayout.LEFT);
                panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
                {
                    JLabel lblNewLabel_2 = new JLabel(p.getPropertyDisplayName("price")+ ":" + p.getPropertyValue("price"));
                    //JTextField jtPrice = new JTextField(p.getPropertyValue("price").toString(),15);
                    panel_1.add(lblNewLabel_2);
                    //panel_1.add(jtPrice);
                }
            }
            {
                JPanel panel_10 = new JPanel();
                panel.add(panel_10);
                FlowLayout fl_panel_10 = (FlowLayout) panel_10.getLayout();
                fl_panel_10.setAlignment(FlowLayout.LEFT);
                panel_10.setBorder(new EmptyBorder(10, 10, 10, 10));
                
                {
                    JLabel lblNewLabel_3 = new JLabel("CurrentQuantity : " + p.getAmount());
                    //JTextField jtAmount = new JTextField(p.getAmount()+ "",15);
                    panel_10.add(lblNewLabel_3);
                    //panel_1.add(jtAmount);
                }
            }
            
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1);
                panel_1.setBorder(null);
                FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
                fl_panel_1.setAlignment(FlowLayout.RIGHT);
                JButton editInfo = new JButton("Edit");
                //panel_1.add(editInfo);
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
                spinner.setModel(new SpinnerNumberModel(0, 0, 10000, 1));
                spinner.setPreferredSize(new Dimension(100, 30));
                buttonPane.add(spinner);
            }
            {
                JDialog me = this;
                JButton okButton = new JButton("Add to Database");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                       //System.out.println(spinner.getValue());
                       c.addToProducts(p, Integer.parseInt(spinner.getValue() + ""));
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
    
    /**
     * <pre>
     * Use this method to display the dialog box for administrators.
     * </pre>
     * 
     * @param c the controller of the system
     * @param p the product chose by an administrator
     */
    public static void display(ShopController c, Product p){
        ProductDetailsForAdmin dialog = new ProductDetailsForAdmin(c, p);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
    }
}
