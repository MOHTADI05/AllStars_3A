/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteCmd;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author user
 */
public class SendSMS {
    
    
    public static final String ACCOUNT_SID = "ACaccbb1cd47dea3464d2548aee7b47b2f9";     /// 
    public static final String AUTH_TOKEN = "a042056be7d721a216a26cf98a6824ca"; ///   
    public static final String TWILIO_NUMBER = "+16202979995";
    
    public static void sendSms(String code,String num) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

       String phoneNumber = "+216"+num;
       String msg="Votre code ugo est "+code;
      //  Message message = Message.creator(new PhoneNumber(phoneNumber),new PhoneNumber(TWILIO_NUMBER),"garage ajouter").create();
         Message message = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(TWILIO_NUMBER), code).create();
        
        System.out.println(message.getSid());
        
        
        
	}
    
}
