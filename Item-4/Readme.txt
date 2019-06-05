## HTTPS and Jacoco instructions

Due to https implementation, to deploy in the developer machine, is necessary to make some changes.

	1- Generate a keystore
	2- Modify server.xml

#Generate keystore

Open a command promp and go to the java folder (cd %JAVA_HOME%/bin)
In the bin folder inside, execute the following
	keytool -genkey -alias tomcat -keyalg RSA

Then you will have to answer some questions, do it, do not forget the password.

#Server.xml

On eclipse, open the Servers directory and open the Tomcat folder.
Inside this folder, you will find the server.xml file. Open it.

Replace the following:

	<!--
	<Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
	    maxThreads="150" scheme="https" secure="true"
	    clientAuth="false" sslProtocol="TLS" />
	-->

By this: (change the password field with yours)

	<Connector SSLEnabled="true" acceptCount="100" clientAuth="false"
	    disableUploadTimeout="true" enableLookups="false" maxThreads="25"
	    port="8443" keystoreFile="C:/Documents and Settings/Boss/.keystore" keystorePass="password"
	    protocol="org.apache.coyote.http11.Http11NioProtocol" scheme="https"
	    secure="true" sslProtocol="TLS" />


#Functional testing

Se ha automatizado el analisis de la cobertura de los test mediante la libreria jacoco

Se pueden volver a conseguir los resultados ejecutando el siguiente comando en
la carpeta del proyecto: "mvn test"

los resultados se encuentran en target/site/jacoco-ut/index.html