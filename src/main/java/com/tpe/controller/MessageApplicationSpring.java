package com.tpe.controller;

import com.tpe.domain.Message;
import com.tpe.repository.FileRepository;
import com.tpe.repository.Repository;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import com.tpe.service.WhatsappService;

class MessageApplication {
    public static void main(String[] args) {
        Message message=new Message();
        message.setBody("Spring is COMING:)");


        //bu mesajı maille gönderelim
//        MailService mailService=new MailService();
//        mailService.sendMessage(message);

        //bu mesajı Whatsapp gönderelim:service için interface tanımlamalıydık
 //       WhatsappService whatsappService=new WhatsappService();
  //      whatsappService.sendMessage(message);

        //ref interface almalıydık
        Repository repo=new FileRepository();
        MessageService service=new WhatsappService(repo);
        service.sendMessage(message);

        //newleyerek obje oluşturmak maliyetli, yönetimi zahmetli?

        //mesajı DB ye kaydedelim.
        service.saveMessage(message);

        //mesajı dosyaya kaydedelim.
        service.saveMessage(message);

        //yeni mesaj service
        MessageService service2=new MailService(repo);

        //spring is COMING:)

    }
}
