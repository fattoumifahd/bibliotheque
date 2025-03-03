package Controller;

public class ProjectController {
	private UtilisateurController userController;
	private LivreController livreController;
	private EmpruntController empruntController;
	
	public ProjectController( ) {
		userController = new UtilisateurController();
		userController.getView().setVisible(false);
		
		livreController = new LivreController();
		livreController.getFrame().setVisible(false);
		
		
		
	}
}
