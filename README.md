# Aspose-Demo

curl --location --request GET 'localhost:8080/pdf/generate' \
--header 'Content-Type: text/html' \
--data-raw '${add-html}'


curl --location --request GET 'localhost:8080/pdf/generate/template' \
--header 'Content-Type: application/json' \
--data-raw '{
    "path" : "/html-templates/document.html"
}'
