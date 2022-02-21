package com.tevolvers.qa.jsonplaceholder.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class ObtenerPublicacion implements Task {
    private int id;
    public ObtenerPublicacion(int id) {
        this.id = id;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource("/posts/{id}")
                .with(req -> req.pathParam("id",id)));
    }

    public static ObtenerPublicacion conId(int id){
        return Tasks.instrumented(ObtenerPublicacion.class, id);
    }
}
