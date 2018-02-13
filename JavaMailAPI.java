package com.test;


	import java.util.Properties;  
	import javax.mail.*;  
	import javax.mail.internet.*;  
	public class JavaMailAPI {
	 public static void main(String[] args) {  
	  
	  String host="smtp.gmail.com";  
	  final String user="dktech89@gmail.com";//change accordingly  
	  //final String password="";//change accordingly  
	    
	  String to="dharmendrakp89@gmail.com";//change accordingly  
	  
	   /*//Get the session object  
	   Properties props = new Properties();  
	   props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
	  // props.put("mail.smtp.host",host);  
	 //  props.put("mail.smtp.auth", "true");
	   props.put("mail.defaultEncoding", "UTF-8"); 
	  // props.put(" mail.smtp.starttls.required", "true");
	  // props.put("mail.smtp.starttls.enable", "true");
	  // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  // props.put("mail.smtp.socketFactory.fallback", "false");
	  // props.put("mail.smtp.port", "465");
	  // props.put("mail.smtp.socketFactory.port", "465");
	     
	   Session session = Session.getDefaultInstance(props,  
	    new javax.mail.Authenticator() {  
	      protected PasswordAuthentication getPasswordAuthentication() {  
	    return new PasswordAuthentication(user,password);  
	      }  
	    });  
	  
	   //Compose the message  
	    try {  
	     MimeMessage message = new MimeMessage(session);  
	     message.setFrom(new InternetAddress(user));  
	     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	     message.setSubject("Test");  
	     message.setText("This is simple program of sending email using JavaMail API");  
	       
	    //send the message  
	     Transport.send(message);  
	  
	     System.out.println("message sent successfully...");  
	   
	     } catch (MessagingException e) {e.printStackTrace();}  
	 }  */
		 /*
		 <?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<include resource="org/springframework/boot/logging/logback/defaults.xml" />
	<property name="LOG_FILE" value="/logs/tigslogs/tigs.log" />
	<property name="PERFORMANCE_LOG_FILE" value="/logs/tigslogs/performance/tigsperformance.log" />

	<springProfile name="dev">
		<include
			resource="org/springframework/boot/logging/logback/console-appender.xml" />
		<appender name="ROLLING-FILE"
			class="ch.qos.logback.core.rolling.RollingFileAppender">
			<encoder>
				<pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{70}.%M - %msg%n
				</pattern>
			</encoder>
			<file>${LOG_FILE}</file>
			<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
				<fileNamePattern>${LOG_FILE}.%d{yyyy-MM-dd_HH}.log</fileNamePattern>
			</rollingPolicy>
		</appender>
		<appender name="PERFORMANCE_ROLLING-FILE"
			class="ch.qos.logback.core.rolling.RollingFileAppender">
			<encoder>
				<pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{70}.%M - %msg%n
				</pattern>
			</encoder>
			<file>${PERFORMANCE_LOG_FILE}</file>
			<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
				<fileNamePattern>${PERFORMANCE_LOG_FILE}.%d{yyyy-MM-dd}.log</fileNamePattern>
			</rollingPolicy>
		</appender>
		<root level="INFO,ERROR">
			<appender-ref ref="CONSOLE" />
			<appender-ref ref="ROLLING-FILE" />
		</root>
		
		<logger name="PERFORMANCE_LOG" level="info" additivity="false">
			<appender-ref ref="PERFORMANCE_ROLLING-FILE" />
		</logger>
	</springProfile>

	<springProfile name="prod">
		<appender name="ROLLING-FILE"
			class="ch.qos.logback.core.rolling.RollingFileAppender">
			<encoder>
				<pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{70}.%M - %msg%n
				</pattern>
			</encoder>
			<file>${LOG_FILE}</file>
			<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
				<fileNamePattern>${LOG_FILE}.%d{yyyy-MM-dd_HH}.log</fileNamePattern>
				<!-- <timeBasedFileNamingAndTriggeringPolicy
					class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
					<maxFileSize>100MB</maxFileSize>
				</timeBasedFileNamingAndTriggeringPolicy>-->
			</rollingPolicy>
		</appender>
		<appender name="PERFORMANCE_ROLLING-FILE"
			class="ch.qos.logback.core.rolling.RollingFileAppender">
			<encoder>
				<pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{70}.%M - %msg%n
				</pattern>
			</encoder>
			<file>${PERFORMANCE_LOG_FILE}</file>
			<rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
				<fileNamePattern>${PERFORMANCE_LOG_FILE}.%d{yyyy-MM-dd}.log</fileNamePattern>
			</rollingPolicy>
		</appender>

		<root level="INFO,ERROR">
			<appender-ref ref="ROLLING-FILE" />
		</root>
		<logger name="PERFORMANCE_LOG" level="info" additivity="false">
			<appender-ref ref="PERFORMANCE_ROLLING-FILE" />
		</logger>
	</springProfile>

</configuration>*/
	  
	  final String username = "dharmendrakp89@gmail.com";
		final String password = "";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		//props.put("mail.smtp.port", "465");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");;
	  
	  Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });

			try {

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("dharmendrakp89@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("dharmendrakp89@gmail.com"));
				message.setSubject("Testing Subject");
				message.setText("Dear Mail Crawler,"
					+ "\n\n No spam to my email, please!");

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
	 }
		/*
		https://github.com/gustavoponce7/SpringBootUnitTestTutorial/blob/master/src/main/java/com/example/service/ToDoServiceImpl.java
		
		*/
	}
	


