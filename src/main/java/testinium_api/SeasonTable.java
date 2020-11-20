package testinium_api;

public class SeasonTable {
    private String driverStandings;
    private String driverId;


    public String getDriverStandings() {
        return driverStandings;
    }

    public void setDriverStandings(String constructorId) {
        this.driverStandings = driverStandings;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public SeasonTable(String driverStandings, String driverId) {
        this.driverStandings = driverStandings;
        this.driverId = driverId;
    }
}
