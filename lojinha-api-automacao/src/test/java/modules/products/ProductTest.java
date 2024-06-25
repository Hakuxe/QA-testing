package modules.products;

import dataFactory.ProductDataFactory;
import dataFactory.UserDataFactory;
import io.restassured.http.ContentType;
import pojo.UserPojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes módulo de produto")
public class ProductTest {

    private String token;

    @BeforeEach
    public void beforeEach(){
        baseURI = "http://165.227.93.41";
        //port = 3333;
        basePath = "/lojinha/v2";



        this.token = given()
                .contentType(ContentType.JSON)
                .body(UserDataFactory.createUser())
                .when().post("/login")
                .then().extract().path("data.token");

    }

    // mudar o token pra privado
    // obter token no before each
    @Test
    @DisplayName("Validando valores inválidos para o campo valor do produto")
    public void testSendInvalidEntriesToProductValue() {



        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProductDataFactory.createProductWithValue(0.00))
                .when().post("/produtos")
                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);

    }

}
