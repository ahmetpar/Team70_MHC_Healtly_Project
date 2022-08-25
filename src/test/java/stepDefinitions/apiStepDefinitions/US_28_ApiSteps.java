package stepDefinitions.apiStepDefinitions;

import com.github.javafaker.Country;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.codehaus.jackson.map.ObjectMapper;
import pojos.pojo_US_28.Country1;
import utilities.Authentication;
import utilities.ConfigReader;

import static io.restassured.RestAssured.*;
import static utilities.Authentication.generateToken;

public class US_28_ApiSteps {

    Response response;

    //TC_01

    @Given("kullanici medunna apiden {string} ulke bilgilerini okur")
    public void kullanici_medunna_apiden_ulke_bilgilerini_okur(String string) {
        response = given().headers(
                        "Authorization",
                        "Bearer " + Authentication.generateToken("team70", "team7044."),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .get(ConfigReader.getProperty("url"))
                .then()
                .extract()
                .response();
        response.then().assertThat().statusCode(200);
        response.prettyPrint();

    }

    //TC_02
    @Given("kullanici api end point {string} den {string} ve {string} kullanarak ulke olusturur")
    public void kullanici_api_end_point_den_ve_kullanarak_ulke_olusturur(String string, String string2, String string3) {

        response = given().headers(
                        "Authorization",
                        "Bearer " + Authentication.generateToken("team70", "team7044."),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                // .when().body("{\"" + type + "\":\"" + country + "\"}")
                .post(ConfigReader.getProperty("EndPoint"))
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
    }

    @Then("Kullanici post request validation yapar")
    public void kullanici_post_request_validation_yapar() {
        response.then().assertThat().statusCode(201);
        response.prettyPrint();

    }

    //TC_03
    @Given("kullanici api end point {string} den {string} ve {string} kullanarak guncelleme yapar")
    public void kullanici_api_end_point_den_ve_kullanarak_guncelleme_yapar(String endPoint, String id, String name) {

        response = given().headers(
                        "Authorization",
                        "Bearer " + Authentication.generateToken("team70", "team7044."),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when().body("{\"id\": " + id + ",\"name\": \"" + name + "\"}")
                .put(endPoint)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
    }

    @Then("Kullanici pull request validation yapar")
    public void kullaniciPullRequestValidationYapar() {

    }

    //TC_04
    @Given("kullanici endpoint {string} ve id ile {string} ulke siler")
    public void kullanici_endpoint_ve_id_ile_ulke_siler(String endPoint, String id) {

        response = given().headers(
                        "Authorization",
                        "Bearer " + Authentication.generateToken("team70", "team7044."),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .delete(endPoint + id)
                .then()
                .extract()
                .response();
    }


}
