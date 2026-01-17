public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        
        System.out.println("=================================");
        System.out.println("  Weather Station Observer Demo  ");
        System.out.println("=================================\n");
        
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        
        System.out.println("\n--- Simulating weather changes ---\n");
        
        System.out.println("\n>>> Update 1: Warm and humid");
        weatherData.setMeasurements(26.6f, 65.0f, 1013.1f);
        
        System.out.println("\n>>> Update 2: Getting warmer");
        weatherData.setMeasurements(28.5f, 70.0f, 1012.0f);
        
        System.out.println("\n>>> Update 3: Cooling down");
        weatherData.setMeasurements(22.0f, 90.0f, 1011.5f);
        
        System.out.println("\n\n--- Removing Forecast Display ---");
        weatherData.removeObserver(forecastDisplay);
        
        System.out.println("\n>>> Update 4: After removing forecast display");
        weatherData.setMeasurements(20.5f, 85.0f, 1010.0f);
        
        System.out.println("\n=================================");
        System.out.println("         Demo Complete           ");
        System.out.println("=================================");
    }
}
