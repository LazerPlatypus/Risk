package risk.controllers.viewControllers.interfaces;

public interface View {
	abstract public void showError(String error);
	abstract public void hideError();
	abstract public void updateDisplay();
}
