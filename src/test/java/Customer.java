public enum Customer {

    FIRST_NAME("Signal"),
    LAST_NAME("Whistler"),
    EMAIL("sigwhist@qa.com"),
    PASSWORD("#Pojo9"),
    GENDER("Male");

    private String description;

    Customer(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
