import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * <pre>
 * This class is used to create a component which displays the details of a product in the database
 * </pre>
 */
public class EachProductView extends JPanel
{
    /**
     * <pre>
     * Constructor for objects of class EachProductView
     * </pre>
     */
    public EachProductView(ShopController c, Product p)
    {
        setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel(p.getName());
		panel.add(title);
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JLabel imgLabel = new JLabel();
		imgLabel.setIcon(p.getImage());
		panel_2.add(imgLabel);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JButton view = new JButton("View");
		panel_1.add(view);
		view.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ProductDetailsForAdmin.display(c, p);
			}
		});
		
		JButton addOne = new JButton("Add 1 to database");
		panel_1.add(addOne);
		addOne.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				c.addToProducts(p, 1);
			}
		});
    }
}
