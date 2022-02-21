package com.tevolvers.qa.jsonplaceholder.questions;

import com.tevolvers.qa.jsonplaceholder.models.Publicacion;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class LaPublicacion implements Question<Publicacion> {
    @Override
    public Publicacion answeredBy(Actor actor) {
        return SerenityRest.lastResponse().then().extract().as(Publicacion.class);
    }

    public static LaPublicacion enLaUltimaRespuesta(){
        return new LaPublicacion();
    }
}
