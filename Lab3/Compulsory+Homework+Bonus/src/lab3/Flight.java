package lab3;

public class Flight {
    private Aircraft assignedAircraft;
    private int idNumber;
    private TimeInterval landingInterval;

    public TimeInterval getLandingInterval() {
        return landingInterval;
    }

    public void setLandingInterval(TimeInterval landingInterval) {
        this.landingInterval = landingInterval;
    }

    public Flight(int idNumber, Aircraft assignedAircraft, TimeInterval landingInterval) {
        this.idNumber = idNumber;
        this.assignedAircraft = assignedAircraft;
        this.landingInterval = landingInterval;
    }

    public Flight(Aircraft assignedAircraft, int idNumber) {
        this.assignedAircraft = assignedAircraft;
        this.idNumber = idNumber;
    }

    public Flight(int idNumber, TimeInterval landingInterval) {
        this.idNumber = idNumber;
        this.landingInterval = landingInterval;
    }

    public void setAssignedAircraft(Aircraft assignedAircraft) {
        this.assignedAircraft = assignedAircraft;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }
}
