package org.gerenciador.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class BaseApi {
    public static RequestSpecification reqSpec;
    public static ResponseSpecification resSpec;

   @BeforeClass
        public static void config(){
            RestAssured.baseURI = "http://localhost:8089/api/";

            RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
            reqBuilder.setBasePath("/v1");
            reqBuilder.setContentType(ContentType.JSON);
            reqBuilder.log(LogDetail.URI);
            reqBuilder.log(LogDetail.HEADERS);
            reqSpec = reqBuilder.build();

            ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
            resBuilder.log(LogDetail.BODY);
            resBuilder.log(LogDetail.STATUS);
            resSpec = resBuilder.build();
        }

}

