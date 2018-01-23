import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * <pre>
 * This class is used to create a dialog box for customers 
 * to confirm the details of the products chose by them as well as their personal information.
 * </pre>
 */
public class ConfirmDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     * Use this method to display the dialog box for customers.
     * </pre>
     * @param c the controller of the system
     */
    public static void display(ShopController c){
        ConfirmDialog dialog = new ConfirmDialog(c);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
    }
    
    /**
     * <pre>
     * ConfirmDialog constructor.
     * </pre>
     * @param c the controller of the system
     */
    public ConfirmDialog(ShopController c) {
        JDialog me = this;
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            {
                JLabel lblNewLabel = new JLabel("ORDER DETAILS:");
                panel.add(lblNewLabel);
            }
            {
                JLabel spacer = new JLabel("  ");
                panel.add(spacer);
            }
            {
                String text = "<html>";
                for(CartItem item : c.getCart().getList()){
                    Cart thisItemInACart = new Cart();
                    thisItemInACart.add(item);
                    text += "ITEM: "+item.product.getName() +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QTY: " + item.quantity +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PRICE: " + c.getBackend().getPrice(thisItemInACart) + "<br>";
                }
                Customer currentCustomer = c.getCurrentCustomerDetails();
                text += "<br/>Name: " + currentCustomer.name + "<br/>"
                        + "Address: " + currentCustomer.address + "<br/>"
                        + "Phone Number: " + currentCustomer.phoneNumber 
                        + "</html>";
                JLabel details = new JLabel(text);
                panel.add(details);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton confirmButton = new JButton("Confirm order");
                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        c.attemptTransaction();
                        me.dispose();
                    }
                });
                confirmButton.setActionCommand("OK");
                buttonPane.add(confirmButton);
                getRootPane().setDefaultButton(confirmButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        me.dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

}
