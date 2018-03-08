package americanCommunitySurvey;

public class DataCode {
	private String indicator;
	private String code;
	
	public DataCode(String i, String c) {
		indicator = i;
		code = c;
	}
	
	@Override
	public String toString() {
		return indicator + " " + code;
	}

	public String getIndicator() {
		return indicator;
	}

	public String getCode() {
		return code;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}