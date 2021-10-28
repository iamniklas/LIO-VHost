# LIO-VHost

Executable virtual host controller for the LIO System. (Basically behaves like the LIO-Host software on embedded systems but uses an LED strip in GUI)

Has to be run with Java SE 1.8 since the Nashorn JS Script Engine is no longer available in higher versions.

How to control the strip:
- Run the jar file on your device

- Install the LIO Controller Android app on your phone available at [Google Play](https://play.google.com/store/apps/details?id=de.niklasenglmeier.liocontroller)
- Alternatively, you can use an HTTP client like Postman

- Send a POST request with the following options:
- URL: **[API_SERVER_URL]**/lio/game?ip=**[DEVICE_IP]**&event_type=none ¹
- Body: JSON object to describe procedures ²
<br />
<br />

¹ Request URL
(Default API-Server-URL: 000raspberry.ddns.net or the ip for your host server) 
(DEVICE-IP: Your network's ip address + port, address name + port or, if you're hosting the API-Server in your own network, the local device ip) 
(event_type parameter will be removed in a future release)
==> Example URL: http://000raspberry.ddns.net/lio/game?ip=255.255.255.255&event_type=none
<br />
<br />
² Request Body

Format ```{"procedure": "[PROCEDURE_TYPE]", "bundle":{[BUNDLE_PARAMS]}}```