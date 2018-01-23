import javax.swing.JPanel;

/**
 * <pre>
 * A view is a self contained "page" in the application.
 * 
 * None of the views or dialogs are commented as they do not need to be used when creating a back-end.
 * </pre>
 */
public abstract class View extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ShopController controller;
	
	/**
	 * <pre>
	 * This sets the controller for this view, this controller is often used by the views to trigger events or request data from the back-end.
	 * </pre>
	 * @param c The controller
	 */
	public void setController(ShopController c){
		this.controller = c;
	}

	/**
	 * <pre>
	 * Returns this view's controller.
	 * </pre>
	 * @return This views controller
	 */
	public ShopController getController(){
		return controller;
	}
	
	/**
	 * <pre>
	 * This method is called every time the view is displayed to the screen, just after the controller has been set.
	 * Use this method to initialize all of the components in the view with data from the controller and to bind events.
	 * </pre>
	 */
	public abstract void initialize();
}
