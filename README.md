# registration
Funkids school students registration

openssl pkcs12 -in yourfile.pfx -nocerts -out key.pem -nodes
openssl pkcs12 -in yourfile.pfx -clcerts -nokeys -out cert.pem


openssl pkcs12 -export -in cert.pem -inkey key.pem -out yourfile.p12 -name youralias -passout pass:yourpassword


openssl pkcs12 -info -in certificate.p12 -noout -passin pass:mypassword

