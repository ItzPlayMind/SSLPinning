
# @criusm/ssl-pinning

## Getting started

`$ npm install @criusm/ssl-pinning`

### Mostly automatic installation

## Usage
```javascript
import SslPinning from '@criusm/ssl-pinning';

//Setup the RNSslPinning for Android and iOS
SslPinning.setup();
//Add a Public key to the list
SslPinning.addPublicKey("www.your-url.com", "YOUR_PUBLIC_KEY");
//Save all keys added and update on Android and iOS
SslPinning.save();
```

To get the public key of a Server use the following command (Replace example.com with your server adress):
```
openssl s_client -servername example.com -connect example.com:443 | openssl x509 -pubkey -noout | openssl pkey -pubin -outform der | openssl dgst -sha256 -binary | openssl enc -base64
```