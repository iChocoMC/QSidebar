package ichocomc.qscoreboard.placeholders;

public class PlaceholderLine {

	private final String line;
	private final byte score;

	public PlaceholderLine(String line, int score) {
		this.line = line;
		this.score = (byte)score;
	}

	public String getLine() {
		return line;
	}

	public byte getScore() {
		return score;
	}
}
