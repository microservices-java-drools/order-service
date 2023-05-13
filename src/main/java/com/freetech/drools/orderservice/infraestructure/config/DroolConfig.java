package com.freetech.drools.orderservice.infraestructure.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Configuration
public class DroolConfig {
    private KieServices kieServices = KieServices.Factory.get();

    @Bean
    public KieContainer getKieContainer() {
        System.out.println("Container created ...");
        ReleaseId releaseId = kieServices.newReleaseId(
                "pe.gob.contraloria.sisco",
                "drools-kjar",
                "LATEST");
        KieContainer kieContainer = kieServices.newKieContainer(releaseId);

        KieScanner kscanner = kieServices.newKieScanner(kieContainer);
        kscanner.start(15000);
        /*TimerTask task = new TimerTask() {
            public void run() {
                // Detiene el KieScanner y borra la caché existente
                kscanner.shutdown();
                //kscanner.clearStatus();

                // Inicia un nuevo KieScanner y realiza un escaneo inmediato en busca de nuevas versiones de las reglas
                kscanner = kieServices.newKieScanner(kieContainer);
                kscanner.scanNow();
            }
        };

        // Programa la tarea para que se ejecute cada 10 segundos
        Timer timer = new Timer();
        timer.schedule(task, 0, 10000);*/
        return kieContainer;
    }

    @Bean
    public KieSession getKieSession() throws IOException {
        System.out.println("Session created ...");
        return getKieContainer().newKieSession("myKieSession1");
    }
}
/*package com.freetech.drools.orderservice.infraestructure.config;

import org.apache.maven.shared.utils.io.IOUtil;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Configuration
public class DroolConfig {

    private KieServices kieServices = KieServices.Factory.get();

    private KieFileSystem getKieFileSystem() throws IOException {
        //Funciona para reglas locales
        //KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        //kieFileSystem.write(ResourceFactory.newClassPathResource("rules/order.drl"));
        //return kieFileSystem;

        //Codigo experimental
        URL ruleUrl = new URL("https://raw.githubusercontent.com/microservices-java-drools/config-rules/main/order.drl");
        InputStream inputStream = ruleUrl.openStream();
        byte[] bytes = IOUtil.toByteArray(inputStream);

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newByteArrayResource(bytes).setSourcePath("order.drl"));

        return kieFileSystem;
    }

    private void getKieRespository() throws IOException {
        final KieRepository kieRepository = kieServices.getRepository();
        //Funciona
        kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());

        //codigo experimental
        //kieRepository.addKieModule(() -> getReleaseID());
    }

    @Bean
    public KieContainer getKieContainer() throws IOException {
        System.out.println("Container created ...");
        getKieRespository();
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        //Codigo experimental
        KieScanner kscanner = kieServices.newKieScanner(kieContainer);
        kscanner.start(1000);
        return kieContainer;
    }

    @Bean
    public KieSession getKieSession() throws IOException {
        System.out.println("Session created ...");
        return getKieContainer().newKieSession();
    }

    private ReleaseId getReleaseID() {
        ReleaseId releaseId = kieServices.newReleaseId("com.example", "my-rules", "1.0.0");
        return releaseId;
    }
}*/