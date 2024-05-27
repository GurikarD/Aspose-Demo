# Aspose-Demo

- Aspose.PDF is used to generate PDF from HTML and vice versa.
- Using Thymeleaf template engine to process and create html in this project thymeleaf template is used to generate HTML in-turn this HTML is used to generate PDF 

### CURL command to API which takes raw html as request body and generate PDF
curl --location --request GET 'localhost:8080/pdf/generate' \
--header 'Content-Type: text/html' \
--data-raw '${add-html}'

### CURL command to API which request body with relative thymeleaf path and generate PDF
curl --location --request GET 'localhost:8080/pdf/generate/template' \
--header 'Content-Type: application/json' \
--data-raw '{
    "path" : "/html-templates/document.html"
}'
