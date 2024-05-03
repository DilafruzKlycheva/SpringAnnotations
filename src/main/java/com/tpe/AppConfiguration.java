package com.tpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Random;

@Configuration//bu classta konfigurasyon yapılacak
@ComponentScan("com tpe")//bu pathdaki tüm componentleri tarar
//default path:AppConfiguration classının içinde bulunduğu path tanımlıdır
@PropertySource("classpath:application.properties")//application.properties dosyasındaki bilgilerin okunmasını sağlar
public class AppConfiguration {

    //Springe ait bir interface, bean oluşturmadan enjekte edilebilir,
    // PropertySourcedaki dosyanın içindeki özelliklere(propertylere)
    // ve hatta uygulamanın çalıştığı ortam değişkenlerine
    // erişmemizi sağlayan metodlar içerir.

    @Autowired
    private Environment environment;

    @Bean//thirdParty classtan bean oluşturulmasını sağlar
    public Random random() {
        return new Random();
    }



    //value anatasyonu ile yaptığımız işlemi Environment ve Properties Classı ile de yapabiliriz.

    @Bean
    public Properties properties(){
        Properties properties=new Properties();//hash table ı extend eder
        properties.put("mymail",environment.getProperty("eposta"));
        properties.put("myphone",environment.getProperty("phone"));
        return properties;
    }

}
