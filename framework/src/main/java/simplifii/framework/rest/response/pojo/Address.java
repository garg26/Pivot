package simplifii.framework.rest.response.pojo;

import java.io.Serializable;

/**
 * Created by aman on 21/02/17.
 */

public class Address implements Serializable{

        private String addressLine1;
        private String addressLine2;
        private String latitude;
        private String longitude;
        private String city;
        private String state;
        private String pincode;
        private String country;
        private String description;
    private String fullAddtess;

    public String getFullAddtess() {
        return fullAddtess = addressLine1 + ", " + addressLine2 + ", " + city + ", " + state + ", " + pincode + ", " + country;
    }

    public void setFullAddtess(String fullAddtess) {
        this.fullAddtess = fullAddtess;
    }

    public String getAddressLine1() {
            return addressLine1;
        }
        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }
        public String getAddressLine2() {
            return addressLine2;
        }
        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }
        public String getLatitude() {
            return latitude;
        }
        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
        public String getLongitude() {
            return longitude;
        }
        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }
        public String getState() {
            return state;
        }
        public void setState(String state) {
            this.state = state;
        }
        public String getPincode() {
            return pincode;
        }
        public void setPincode(String pincode) {
            this.pincode = pincode;
        }
        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        @Override
        public String toString() {
            return "Address [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", latitude=" + latitude
                    + ", longitude=" + longitude + ", city=" + city + ", state=" + state + ", pincode=" + pincode
                    + ", country=" + country + ", description=" + description + "]";
        }
}
