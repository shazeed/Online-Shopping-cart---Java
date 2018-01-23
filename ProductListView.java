import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * <pre>
 * This class is used to create a view for a customer 
 * after he successfully logs in the system.
 * 
 * This view displays all available products in the database.
 * 
 * In this view, customers can choose to view the details of a product or add one product into their cart.
 * </pre>
 */
public class ProductListView extends View {
    
    private static final long serialVersionUID = 1L;
    
    private JPanel scrollPanel;
    
    /**
	 * <pre>
	 * ProductListView constructor.
	 * </pre>
	*/
    public ProductListView() {
        setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        add(panel, BorderLayout.NORTH);
        
        JButton myInfoButton = new JButton("My account");
        myInfoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                UserDetails.display(getController());
            }
        });
        
        JButton cartButton = new JButton("View cart");
        cartButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().showCartView();
            }
        });
        
        
        JButton createReport = new JButton("Purchased History");
        createReport.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                getController().showReport();
            }
        });
        
        JButton logoutButton = new JButton ("Log Out");
        logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //getController().logout();
                getController().init();
            }
        });
        
        panel.add(myInfoButton);
        panel.add(createReport);
        panel.add(cartButton);
        panel.add(logoutButton);
        
        scrollPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new GridLayout(0, 3, 0, 0));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scroll, BorderLayout.CENTER);

    }

    /**
     * <pre>
	 * Use this method to initialize the components in the view 
	 * with neccessary information of all the available products in the database.
	 * </pre>
	 */
    public void initialize() {
        scrollPanel.removeAll();
        List<Product> list = getController().getBackend().getProducts();
        for(Product p : list){
            if(p.getAmount() > 0)
                scrollPanel.add(new ProductThumbnail(getController(), p));
        }
        revalidate();
    }

}
