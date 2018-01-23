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
 * This class is used to create a view for an adminstrator 
 * after he successfully log in the system.
 * </pre>
 */
public class AdminView extends View
{
    private JPanel scrollPanel;

    /**
     * <pre>
     * Constructor for objects of class CreateItemView
     * </pre>
     */
    public AdminView()
    {
        setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        add(panel, BorderLayout.NORTH);
        
        JButton createReport = new JButton("Create Report");
        createReport.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //getController().createReportForAdmin();
                getController().showReport();
            }
        });
        
        JButton createNewItem = new JButton("Create New Item");
        createNewItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().showCreateNewItemView();
            }
        });
        /*
        JButton btnBack = new JButton("Back to products");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getController().showProductList();
            }
        });*/
        //panel.add(btnBack);
        JButton logoutButton = new JButton ("Log Out");
        logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //getController().logout();
                getController().init();
            }
        });
        
        panel.add(createReport);
        panel.add(createNewItem);
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
	 * with neccessary information of all of the products in the database.
	 * </pre>
	 */
    public void initialize() {
        scrollPanel.removeAll();
        List<Product> list = getController().getBackend().getProducts();
        for(Product p : list){
            scrollPanel.add(new EachProductView(getController(), p));
        }
        revalidate();
    }
}
