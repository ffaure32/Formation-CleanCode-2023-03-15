package zenika.supple.iri;

import static java.util.Objects.requireNonNull;

public class Flight {

    private final String CODE_TEMPLATE = "fromCity-toCity-departureAirport";
    
    private final City from, to;
    private String departureAirportCode;
    private String code;

    private Flight(City from, City to) {
        this.from = from;
        this.to = to;
        this.code = CODE_TEMPLATE
                .replace("fromCity", from.name())
                .replace("toCity", to.name());
    }

    public String getDepartureAirport() {
        return departureAirportCode;
    }
    
    public String getCode() {
        return this.code;
    }
    
    
    public static TripBuilder from(City departure) {
        return new TripBuilder(departure);
    }

    public static class TripBuilder {
        private City from, to;
        
        public TripBuilder(City from) {
            this.from = requireNonNull(from);
        }

        public Flight to(City to) {
            this.to = requireNonNull(to);
            return new Flight(this.from, this.to);
        }
    }
    
    public void setDepartureAirport(String code) {
        this.departureAirportCode = code;
        this.code = this.code.replace("departureAirport", departureAirportCode);
    }
}
