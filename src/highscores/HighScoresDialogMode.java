package highscores;

/**
 * This interface is used to inform the <code>HighScoresDialog</code> what
 * it's purpose is:
 * <li>update the high score for the expert mode</li>
 * <li>update the high socre for medium mode</li>
 * <li>update the high score for junior mode</li>
 * <li>just display the high scores</li>
 * <p>
 * 
 * @author Sorin ( soriniulus@yahoo.com ) At: Apr 17, 2007, 10:38:17 PM
 */
public interface HighScoresDialogMode {

	/**
	 * The high scores dialog will be displayed to updte the time for the expert
	 * mode.
	 */
	public static final String EXPERT_UPDATE_MODE = "EXPERT_UPDATE_MODE";

	/**
	 * The high scores dialog will be displayed to updte the time for the medium
	 * mode.
	 */
	public static final String MEDIUM_UPDATE_MODE = "MEDIUM_UPDATE_MODE";

	/**
	 * The high scores dialog will be displayed to updte the time for the junior
	 * mode.
	 */
	public static final String JUNIOR_UPDATE_MODE = "JUNIOR_UPDATE_MODE";

	/**
	 * The high scores dialog will display the best times.
	 */
	public static final String DISPLAY_MODE = "DISPLAY_MODE";
}
