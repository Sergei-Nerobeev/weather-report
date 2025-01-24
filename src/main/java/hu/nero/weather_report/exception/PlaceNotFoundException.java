package hu.nero.weather_report.exception;

public class PlaceNotFoundException extends RuntimeException {

  public PlaceNotFoundException(String message) {
    super(message);
  }
}
