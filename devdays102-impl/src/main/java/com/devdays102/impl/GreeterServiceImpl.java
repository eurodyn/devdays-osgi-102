package com.devdays102.impl;

import com.devdays102.api.GreeterService;
import com.devdays102.api.dto.NameDTO;
import com.devdays102.impl.model.Greeter;
import com.devdays102.lib.LibUtil;
import org.ops4j.pax.cdi.api.OsgiServiceProvider;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
@Transactional
@OsgiServiceProvider(classes = {GreeterService.class})
public class GreeterServiceImpl implements GreeterService {
    @PersistenceContext(unitName = "devdays102PU")
    private EntityManager em;

    public String greet(String name) {
        LibUtil libUtil = new LibUtil();
        String processedName = libUtil.capitaliser(name);

        Greeter entity = new Greeter();
        entity.setName(processedName);
        entity.setId(UUID.randomUUID());
        em.persist(entity);

        return "Hello " + processedName;
    }

    public List<NameDTO> getGreeters() {
        final List<NameDTO> retVal = new ArrayList<>();

        ((List<Greeter>)em.createQuery("select g from Greeter g").getResultList())
                .stream().forEach(greeter -> retVal.add(new NameDTO(greeter.getName())));

        return retVal;
    }
}
