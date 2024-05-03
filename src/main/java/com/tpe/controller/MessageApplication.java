package com.tpe.controller;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MessageService;
import com.tpe.service.SmsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

class MessageApplicationSpring {
    public static void main(String[] args) {

        Message message = new Message();
        message.setBody("Welcome Spring :)");

        //config classını okur ve componentScan ile componentları ve beenleri tarar ve her birinden
        // sadece 1 tane Spring bean oluşturur ve contexte atar ve hazır olarak bekletilir
        //bean istendiğinde gerekliyse içine bağımlılığını da enjekte ederek gönderir.
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(AppConfiguration.class);

//        //mesajımızı SmsService ile gönderelim.
//        MessageService service=context.getBean(SmsService.class);//newlemedik, rica ettik:)
//        service.sendMessage(message);

//        MessageService service2=context.getBean(SmsService.class);
//        service2.sendMessage(message);

        //mesajımızı slack service ile gönderelim
        //   MessageService service3=context.getBean(SlackService.class);
//        MessageService service3 = context.getBean("slack_service", MessageService.class);
 //       service3.sendMessage(message);
        //getBean metoduna parametre olarak parentı verirsek ve birden fazla childı varsa
        //beanin ismini de belirtmeliyiz.

        //bağımlılık varsa ne olur
        MessageService service4=context.getBean("smsService",MessageService.class);
        service4.saveMessage(message);//sms servisi newlemedim
                                    //service repoya bağımlı ama biz enjekte etmedik
                                    //repo objesini de biz oluşturmadık
                                    //Spring SAĞOLSUN:)



        //smsServicede setter injection yapılırsa sonradan repo değiştirilebilir.

//        SmsService service5=context.getBean(SmsService.class);
//        service5.saveMessage(message);
//        Repository repository=context.getBean(DbRepository.class);
//        service5.setRepo(repository);
//        service5.saveMessage(message);

        //random bir değer yazdıralım

//        Random random=new Random();
        Random random =context.getBean(Random.class);
        System.out.println(random.nextInt(100));

        //context objemiz varsa beani getBean ile isteyip kullanabiliriz
        //diğer classlarda ise enjekte ederek aynı beani kullanabiliriz


        //bean scope
        //1-singleton:default
        //sadece 1 tane bean üretilir her istekte aynı bean verilir
        //beanin tüm life cycle ından Spring sorumludur.


        //2-prototype
        //her istekte yeni bir bean üretilip gönderilir
        //beanin imhasından Spring SORUMLU DEĞİLDİR.


        MessageService service6=context.getBean(SmsService.class);
        MessageService service7=context.getBean(SmsService.class);

        if (service6==service7){
            System.out.println("Aynı objeler");
        }else {
            System.out.println("Farklı objeler");







            context.close();

            System.out.println("context kapatıldı");
            // context.getBean(SmsService.class);
        }
    }
}
