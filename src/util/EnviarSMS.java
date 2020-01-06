/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.twilio.Twilio; import com.twilio.rest.api.v2010.account.Message; import com.twilio.type.PhoneNumber;
/**
 *
 * @author Kevin Castillo
 */
public class EnviarSMS {
  // Find your Account Sid and Token at twilio.com/user/account
  private static final String ACCOUNT_SID = "AC03650c517a22774518475bd4318c4a98";
  private static final String AUTH_TOKEN = "945b0e91ee60d74f3726d2e54a0fb700";
  
  public static void enviarSMS(String pTelefono,String pMensaje){
  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message
      .creator(new PhoneNumber("+506"+pTelefono), new PhoneNumber("+16194042838"),
        pMensaje)
      .create();
  System.out.println(message.getSid()); 
  }
}