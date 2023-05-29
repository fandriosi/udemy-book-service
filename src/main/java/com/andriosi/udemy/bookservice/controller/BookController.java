package com.andriosi.udemy.bookservice.controller;

import com.andriosi.udemy.bookservice.model.Book;
import com.andriosi.udemy.bookservice.proxy.CambioProxy;
import com.andriosi.udemy.bookservice.repositories.BookRepository;
import com.andriosi.udemy.bookservice.response.Cambio;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private BookRepository repository;
    @Autowired
    private Environment environment;

    @Autowired
    private CambioProxy proxy;

    @GetMapping(value = "/{id}/{currency}", produces = MediaType.APPLICATION_JSON)
    public Book getBook(@PathVariable("id")Long id,
                        @PathVariable("currency") String currency){
        Book entity = repository.getById(id);
        if(entity == null) throw new RuntimeException("Book not found");

        String environment = this.environment.getProperty("local.server.port");

        Cambio cambio = proxy.getCambio(entity.getPrice(), "USD", currency);

        entity.setEnvironment("Book prot: "+environment+  " Cambio prot: "+ cambio.getEnviroment());

        entity.setPrice(cambio.getConvertedValue());
        return entity;
    }

    /**
     *  public Book getBook(@PathVariable("id")Long id,
     *                         @PathVariable("currency") String currency){
     *         String environment = this.environment.getProperty("local.server.port");
     *         Book entity = repository.getById(id);
     *         if(entity == null) throw new RuntimeException("Book not found");
     *         entity.setEnvironment(environment);
     *
     *         HashMap<String, String> params = new HashMap<>();
     *         params.put("amount", entity.getPrice().toString());
     *         params.put("from", "USD");
     *         params.put("to", currency);
     *
     *         Cambio cambio = new RestTemplate().getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}",
     *                 Cambio.class,
     *                 params).getBody();
     *         entity.setPrice(cambio.getConvertedValue());
     *         return entity;
     *     }
      */

}
