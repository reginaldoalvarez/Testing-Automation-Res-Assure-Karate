package com.tevolvers.qa.jsonplaceholder.stepdefinitions;

import com.tevolvers.qa.jsonplaceholder.models.Publicacion;
import com.tevolvers.qa.jsonplaceholder.questions.ElTitulo;
import com.tevolvers.qa.jsonplaceholder.questions.LaPublicacion;
import com.tevolvers.qa.jsonplaceholder.task.ObtenerPublicacion;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.hamcrest.Matchers;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.is;

public class PostsStepDefinitions {

    @Given("que {string} se conecta al api")
    public void queSeConectaAlApi(String actor) {
        OnStage.setTheStage(Cast.ofStandardActors());
        OnStage.theActorCalled(actor).can(CallAnApi.at("https://jsonplaceholder.typicode.com"));
    }
    @When("el obtiene el post {int}")
    public void elObtieneElPost(Integer idPost) {
        OnStage.theActorInTheSpotlight().attemptsTo(ObtenerPublicacion.conId(idPost));
    }
    @Then("el deberia de ver los datos del post")
    public void elDeberiaDeVerLosDatosDelPost() {
        SerenityRest.lastResponse().then().log().all();
        OnStage.theActorInTheSpotlight().should(seeThatResponse(response ->
              response.body("title", Matchers.equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                      .body("id", Matchers.equalTo(1))));
    }

    @DataTableType
    public Publicacion publicacion(Map<String, String> map){
        Publicacion publicacion = new Publicacion();
        publicacion.setTitle(map.get("title"));
        publicacion.setBody(map.get("body"));
        publicacion.setUserId(Integer.parseInt(map.get("userId")));
        return publicacion;
    }


    @When("el crea un post")
    public void elCreaUnPost(Publicacion publicacion) {
        OnStage.theActorInTheSpotlight().attemptsTo(Post.to("/posts/")
                .with(req->req.body(publicacion)));
        OnStage.theActorInTheSpotlight().remember("publicacion", publicacion);
    }

    @Then("el deberia de ver el post creado")
    public void elDeberiaDeVerElPostCreado() {
        SerenityRest.lastResponse().then().log().all();
        Publicacion publicacion = OnStage.theActorInTheSpotlight().recall("publicacion");
        OnStage.theActorInTheSpotlight().should(seeThatResponse(response ->
                response.statusCode(201)
                        .body("title", is(publicacion.getTitle()))));
        OnStage.theActorInTheSpotlight().should(seeThat(ElTitulo.enLaRespuesta(),
                Matchers.equalTo(publicacion.getTitle())));
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(LaPublicacion.enLaUltimaRespuesta(),
                Matchers.hasProperty("title", Matchers.equalTo(publicacion.getTitle()))));
    }
}
