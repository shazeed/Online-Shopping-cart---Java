import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.List;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
/**
 * <pre>
 * This class is used to create a report view.
 * Users can look for purchased items during a specific time.
 * Also, for customers, they can look for all items purchased by them, 
 * and for administrators, they can look for all items purchased by all customers
 * </pre>
 */
public class ReportView extends View {
    
    private JPanel scrollPanel;
    
    /**
     * <pre>
     * ReportView constructor.
     * </pre>
    */
    public ReportView() {
        setLayout(new BorderLayout(0, 0));
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 5, 5));
        add(topPanel, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        
        JButton myInfoButton = new JButton("Back to products");
        myInfoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (getController().getCurrentUserType().equals("normal"))
                    getController().showProductList();
                if (getController().getCurrentUserType().equals("admin"))
                    getController().showAdminView();
            }
        });
        
        topPanel.add(panel);
        panel.add(myInfoButton);
        
        JPanel panel2 = new JPanel();
        FlowLayout flowLayout2 = (FlowLayout) panel.getLayout();
        flowLayout2.setAlignment(FlowLayout.LEFT);
        //add(panel2, BorderLayout.CENTER);
        //panel2.setSize(new Dimension(2, 3));
        
        String years[] = new String[]{"2010", "2011", "2012", "2013", "2014", "2015", "2016"};
        JComboBox  year1 =new JComboBox(years);
        JComboBox  year2 =new JComboBox(years);
        
        String months[] = new String[12];
        for (int i = 0; i < months.length; i++)
        {
            months[i] = ((i<9)?"0":"") + (i+1);
        }
        JComboBox  month1 =new JComboBox(months);
        JComboBox  month2 =new JComboBox(months);
        
        String[] days = new String[31];        
        for (int i = 0; i < days.length; i++)
        {
            days[i] = ((i<9)?"0":"") + (i+1);
        }
        JComboBox  day1 =new JComboBox(days);
        JComboBox  day2 =new JComboBox(days);
        
        JLabel start = new JLabel("Start Date");
        /*
        JTextField start_year = new JTextField(3);
        JTextField start_month = new JTextField(3);
        JTextField start_day = new JTextField(3);*/
        
        JLabel finish = new JLabel("Finish Date");
        /*
        JTextField finish_year = new JTextField(3);
        JTextField finish_month = new JTextField(3);
        JTextField finish_day = new JTextField(3);*/
        
        topPanel.add(panel2);
        panel2.add(start);       
        panel2.add(year1);
        panel2.add(month1);
        panel2.add(day1);
        
        panel2.add(finish);
        panel2.add(year2);
        panel2.add(month2);
        panel2.add(day2);
        
                
 
        
        
        

        //JComboBox  days=new JComboBox(day);
        
        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //if ((String)month1.getSelectedItem().equals())
                getController().searchPurchasedItems((String)year1.getSelectedItem()
                        + (String)month1.getSelectedItem()+ 
                        (String)day1.getSelectedItem(), (String)year2.getSelectedItem()+
                         (String)month2.getSelectedItem()+ 
                        (String)day2.getSelectedItem());
            }
        });
        panel2.add(search);
        
        JButton searchAll = new JButton("Show All Purchased Items");
        searchAll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().searchPurchasedItems("0","0");
            }
        });
        panel2.add(searchAll);
        
        scrollPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new GridLayout(0, 5, 5, 5));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scroll, BorderLayout.CENTER);

    }

    /**
     * <pre>
     * Use this method to initialize the components in the view 
     * with neccessary information of purchased items which are searched by users.
     * </pre>
     */
    public void initialize() {
        scrollPanel.removeAll();
        List<Report> records = getController().getBackend().getReportList();
        if (records.size() > 0)
        {
            scrollPanel.add(new JLabel("Order ID",JLabel.CENTER));
            scrollPanel.add(new JLabel("Item Name",JLabel.CENTER));
            scrollPanel.add(new JLabel("Quantity",JLabel.CENTER));
            scrollPanel.add(new JLabel("Total Price",JLabel.CENTER));
            scrollPanel.add(new JLabel("Purchased Time",JLabel.CENTER));
            for(Report r : records){
                List<CartItem> items = r.getItems().getList();
                for(int i = 0; i < items.size(); i++)
                {
                    if (i == 0)
                    {
                        scrollPanel.add(new JLabel((r.getOrderId() + ""), JLabel.CENTER));                  
                        scrollPanel.add(new JLabel(items.get(i).getProduct().getName(), JLabel.CENTER));
                        scrollPanel.add(new JLabel((items.get(i).getQuantity() + ""), JLabel.CENTER));
                        scrollPanel.add(new JLabel((r.getTotalPrice() + ""), JLabel.CENTER));
                        scrollPanel.add(new JLabel(r.getPurchasedDate(), JLabel.CENTER));
                    }
                    else
                    {
                        scrollPanel.add(new JLabel("", JLabel.CENTER));                  
                        scrollPanel.add(new JLabel(items.get(i).getProduct().getName(), JLabel.CENTER));
                        scrollPanel.add(new JLabel((items.get(i).getQuantity() + ""), JLabel.CENTER));
                        scrollPanel.add(new JLabel("", JLabel.CENTER));
                        scrollPanel.add(new JLabel("", JLabel.CENTER));
                    }
                }
            }
             
        }
        getController().getBackend().getReportList().clear();
        revalidate();
    }

}
