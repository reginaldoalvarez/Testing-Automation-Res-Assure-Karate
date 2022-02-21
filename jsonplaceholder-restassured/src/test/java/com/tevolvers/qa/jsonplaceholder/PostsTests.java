package com.tevolvers.qa.jsonplaceholder;

import com.tevolvers.qa.jsonplaceholder.models.Post;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostsTests {

    @BeforeEach
    public void setUpTests(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void getPostById() {
        RestAssured.given().contentType(ContentType.JSON)
                .when().get("/posts/1").then()
                .statusCode(200)
                .body("userId", Matchers.equalTo(1))
                .body("id", Matchers.equalTo(1))
                .body("id", Matchers.isA(Integer.class))
                .log().all();
    }

    @Test
    public void createPostsWithBodyString() {
        RestAssured.given().contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"title\": \"Capacitacion T-E\",\n" +
                        "    \"body\": \"Aprendiendo sobre rest api y testing api\",\n" +
                        "    \"userId\": 1\n" +
                        "}")
                .when().post("/posts")
                .then()
                .statusCode(201)
                .body("title", Matchers.equalTo("Capacitacion T-E"))
                .log().all();

    }

    @Test
    public void createPostWithModel(){
        Post post = new Post();
        post.setTitle("Capa Model T-E");
        post.setBody("Este body esta hecho con un model");
        post.setUserId(1);
        RestAssured.given().contentType(ContentType.JSON)
                .body(post)
                .when().post("/posts")
                .then()
                .statusCode(201)
                .body("title", Matchers.is(post.getTitle()))
                .body("body", Matchers.is(post.getBody()))
                .log().all();
    }

    @Test
    public void getPostByIdDeserialized(){
        Post post  = RestAssured.given().contentType(ContentType.JSON)
                .when().get("/posts/1").then()
                .statusCode(200)
                .body("userId", Matchers.equalTo(1))
                .body("id", Matchers.equalTo(1))
                .body("id", Matchers.isA(Integer.class))
                .extract().body().as(Post.class);
        System.out.println(post.getId());
    }
}
