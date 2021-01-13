package windpark.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;

public class WindengineData implements Serializable {
	private static final long serialVersionUID = 300002228479017363L;
	
	private String windengineID;
	private String timestamp;
	
	private double windspeed;
	private String unitWindspeed;

	private double temperature;
	private String unitTemperature;

	private double power;
	private String unitPower;

	private double blindpower;
	private String unitBlindpower;

	private double rotationspeed;
	private String unitRotationspeed;

	private double bladeposition;
	private String unitBladeposition;

	/**
	 * Constructor
	 */
	public WindengineData() {
		
		this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		this.unitWindspeed = "kmH";
		this.unitTemperature = "C";
		this.unitPower = "kwH";
		this.unitBlindpower = "kwH";
		this.unitRotationspeed = "uM";
		this.unitBladeposition = "grad";
		
	}
	
	/**
	 * Setter and Getter Methods
	 */
	public String getWindengineID() {
		return windengineID;
	}
	
	public void setWindengineID(String windengineID) {
		this.windengineID = windengineID;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setWindspeed(double windspeed) {
		this.windspeed = windspeed;
	}
	
	public double getWindspeed() {
		return windspeed;
	}
	
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getBlindpower() {
		return blindpower;
	}

	public void setBlindpower(double blindpower) {
		this.blindpower = blindpower;
	}

	public double getRotationspeed() {
		return rotationspeed;
	}

	public void setRotationspeed(double rotationspeed) {
		this.rotationspeed = rotationspeed;
	}

	public double getBladeposition() {
		return bladeposition;
	}

	public void setBladeposition(double bladeposition) {
		this.bladeposition = bladeposition;
	}

	public String getUnitWindspeed() {
		return unitWindspeed;
	}

	public String getUnitTemperature() {
		return unitTemperature;
	}

	public String getUnitPower() {
		return unitPower;
	}

	public String getUnitBlindpower() {
		return unitBlindpower;
	}

	public String getUnitRotationspeed() {
		return unitRotationspeed;
	}

	public String getUnitBladeposition() {
		return unitBladeposition;
	}

	/**
	 * Methods
	 */
	@Override
	public String toString() {
		String info = String.format("Windengine Info: ID = %s, timestamp = %s, windspeed = %f", 
			windengineID, timestamp, windspeed );
		return info;
	}

	private double getRandomDouble( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum;
		double rounded = Math.round(number * 100.0) / 100.0;
		return rounded;

	}

	private int getRandomInt( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum;
		Long rounded = Math.round(number);
		return rounded.intValue();

	}

	public WindengineData getData( String inWindengineID ) {

		WindengineData data = new WindengineData();
		data.setWindengineID( inWindengineID );
		data.setWindspeed( getRandomDouble( 0, 80 ) );
		data.setTemperature( getRandomDouble( -40, 40 ) );
		data.setPower( getRandomDouble( 0, 2000 ) );
		data.setBlindpower( getRandomDouble( 0, 200 ) );
		data.setRotationspeed( getRandomDouble( 0, 200 ) );
		data.setBladeposition( getRandomInt( 0, 45 ) );
		return data;

	}

	public String getDataforJson( String inWindengineID ) {

		WindengineData data = new WindengineData();
		data.setWindengineID( inWindengineID );
		ObjectMapper o = new ObjectMapper();
		String s = "";
		try {
			s = o.writeValueAsString(data);
		} catch (Exception e) {
			System.out.println("Fehler");
		}
		return s;

	}
}
