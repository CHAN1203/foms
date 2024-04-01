package view;

public abstract class MainView {
<<<<<<< HEAD
	
	protected abstract void printAction();
	
	public abstract void viewApp();
	
	public MainView() {
		
	}
	
	protected void printBreadCrumbs(String breadcrumb) {
=======
    /**
     * Abstract method for view menu
     */
    protected abstract void printActions();

    /**
     * Abstract method for view app
     */
    public abstract void viewApp();

    /**
     * Default constructor for main view
     */
    public MainView() {
  
    }

    /**
     * Method to print breadcrumbs for navigation purposes
     * @param breadcrumb breadcumbs description
     */
    protected void printBreadCrumbs(String breadcrumb) {
>>>>>>> 4d30621f56df80bf895e8dc787741f37b7fbd288
        String spaces = String.format("%" + (105 - breadcrumb.length()) + "s", "");
        System.out.println(
                "╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║ " + breadcrumb + spaces + "║");
        System.out.println(
                "╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }
<<<<<<< HEAD
}
=======
    
}
>>>>>>> 4d30621f56df80bf895e8dc787741f37b7fbd288
