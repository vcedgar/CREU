package americanCommunitySurvey;

public class CityCode {
	private String city;
	private String code;
	
	public CityCode(String info) {
		String[] cityName = info.split("\"");
		city = cityName[1];
		String[] cityCode = cityName[0].split(",");
		code = cityCode[0];
	}
	
	@Override
	public String toString() {
		return city + " " + code;
	}

	public String getCity() {
		return city;
	}

	public String getCode() {
		return code;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
