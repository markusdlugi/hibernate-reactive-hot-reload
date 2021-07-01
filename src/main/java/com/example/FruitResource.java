package com.example;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.hibernate.reactive.mutiny.Mutiny;

import io.quarkus.security.identity.CurrentIdentityAssociation;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/fruits")
@PermitAll
public class FruitResource {

    @Inject
    Mutiny.SessionFactory sessionFactory;

    @GET
    public Uni<List<Fruit>> getFruits() {
        System.out.println("TEST");
        return sessionFactory.withSession(session -> session.createNamedQuery("fruits.findAll", Fruit.class).getResultList());
    }

    @POST
    public Uni<Fruit> createFruit(Fruit fruit) {
        return sessionFactory.withSession(session -> session.persist(fruit).chain(session::flush)).onItem().transform(ignore -> fruit);
    }
}
