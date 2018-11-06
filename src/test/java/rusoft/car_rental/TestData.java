package rusoft.car_rental;


public class TestData {
    public static final String USER_REQUEST_JSON = "{\n" +
            "    \"userName\": \"Travis\",\n" +
            "    \"userBirthYear\": \"1999\",\n" +
            "    \"carBrand\": \"Audi\",\n" +
            "    \"carReleaseYear\": \"2017\"\n" +
            "}";
    public static final String USER_RESPONSE_JSON = "{\n" +
            "    \"id\": 2,\n" +
            "    \"name\": \"Travis\",\n" +
            "    \"birthDay\": \"1999-01-01T03:00:00\"\n" +
            "}";
    public static final String USER_EXIST_REQUEST_JSON = "{\n" +
            "    \"userName\": \"Jankins\",\n" +
            "    \"userBirthYear\": \"1990\",\n" +
            "    \"carBrand\": \"Audi\",\n" +
            "    \"carReleaseYear\": \"2017\"\n" +
            "}";
    public static final String REQUEST_CAR_HAS_OWNER = "{\n" +
            "    \"userName\": \"Kamila\",\n" +
            "    \"userBirthYear\": \"1994\",\n" +
            "    \"carBrand\": \"bmw\",\n" +
            "    \"carReleaseYear\": \"2019\"\n" +
            "}";
    public static final String REQUEST_INVALID_DATE = "{\n" +
            "    \"userName\": \"Jankins\",\n" +
            "    \"userBirthYear\": \"199inv\",\n" +
            "    \"carBrand\": \"Audi\",\n" +
            "    \"carReleaseYear\": \"2017\"\n" +
            "}";
}
