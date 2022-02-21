package com.tevolvers.qa.jsonplaceholder.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ElTitulo implements Question<String> {


    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().then().extract().jsonPath().getString("title");
    }

    public static ElTitulo enLaRespuesta(){
        return new ElTitulo();
    }
}
