# registration
Funkids school students registration


openssl pkcs12 -in yourfile.pfx -nocerts -out key.pem -nodes
openssl pkcs12 -in yourfile.pfx -clcerts -nokeys -out cert.pem


openssl pkcs12 -export -in cert.pem -inkey key.pem -out yourfile.p12 -name youralias -passout pass:yourpassword


openssl pkcs12 -info -in certificate.p12 -noout -passin pass:mypassword

openssl pkcs12 -info -in certificate.p12 -noout -passin pass:mypassword

Extract the Private Key
Run the following command to extract the private key from the .p12 file:

openssl pkcs12 -in GenSADA.p12 -nocerts -out key.pem -nodes -passin pass:pocpwd

Extract the Certificate
Run the following command to extract the certificate from the .p12 file:
openssl pkcs12 -in GenSADA.p12 -clcerts -nokeys -out cert.pem -passin pass:pocpwd


Combine into a New PKCS12 File
If both the certificate and the private key are extracted successfully, you can combine them back into a new .p12 file:

openssl pkcs12 -export -in cert.pem -inkey key.pem -out new_certificate.p12 -name myappkey -passout pass:pocpwd

After performing these steps, check the new .p12 file to ensure it contains the private key:

openssl pkcs12 -info -in new_certificate.p12 -noout -passin pass:pocpwd

To open .pfx
openssl pkcs12 -info -in yourfile.pfx -noout -passin pass:yourpassword



