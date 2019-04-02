package response;

/**
 * Reponse de base
 * 
 */
public class GeneralResponse {
	private int codeRetour;
	private String message;

	public final int getCodeRetour() {
		return this.codeRetour;
	}
	public final void setCodeRetour(final int codeRetour) {
		this.codeRetour = codeRetour;
	}

	public final String getMessage() {
		return this.message;
	}
	public final void setMessage(final String message) {
		this.message = message;
	}
}
