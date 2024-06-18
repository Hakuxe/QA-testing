package modules.products;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes módulo de produto")
public class ProductTest {

    @Test
    @DisplayName("Validando valores inválidos para o campo valor do produto")
    public void testSendInvalidEntriesToProductValue() {
        baseURI = "http://165.227.93.41";
        //port = 3333;
        basePath = "/lojinha/v2";

        String token = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"usuarioLogin\": \"luffy\",\n" +
                        "  \"usuarioSenha\": \"luffy\"\n" +
                        "}")
                .when().post("/login")
                .then().extract().path("data.token");

        given()
                .contentType(ContentType.JSON)
                .header("token", token)
                .body("{\n" +
                        "  \"produtoNome\": \"IPHONE 15 PRO MAX\",\n" +
                        "  \"produtoValor\": 0,\n" +
                        "  \"produtoCores\": [\n" +
                        "    \"preto\", \"rosa\"\n" +
                        "  ],\n" +
                        "  \"produtoUrlMock\": \"\",\n" +
                        "  \"componentes\": [\n" +
                        "    {\n" +
                        "      \"componenteNome\": \"carregador\",\n" +
                        "      \"componenteQuantidade\": 1\n" +
                        "    },\n" +
                        "     {\n" +
                        "      \"componenteNome\": \"capinha\",\n" +
                        "      \"componenteQuantidade\": 1\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when().post("/produtos")
                .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);

    }

}
