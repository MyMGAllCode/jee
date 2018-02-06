package com.textmagic.sdk;
 
import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.instance.*;
import java.util.Arrays;
 
public class Example{
  public static void main(String[] args) throws RestException {
    RestClient client = new RestClient("dkp89", "dkp@1989");
 
    TMNewMessage m = client.getResource(TMNewMessage.class);
    m.setText("Hello from TextMagic Java");
    m.setPhones(Arrays.asList(new String[] {"8120234006"}));
    try {
      m.send();
    } catch (final RestException e) {
      System.out.println(e.getErrors());
      throw new RuntimeException(e);
    }
    System.out.println(m.getId());
  }
}