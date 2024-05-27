# Aspose-Demo

curl --location --request GET 'localhost:8080/pdf/generate' \
--header 'Content-Type: text/html' \
--data-raw '<!DOCTYPE html>
<HTML>

<HEAD>

<TITLE>Your Title Here</TITLE>

</HEAD>

<BODY BGCOLOR="#FFFFFF">


<p><img src="https://www.w3schools.com/images/lamp.jpg" width="50" height="50" border="0" title="Bank"></img></p>
<HR/>



<a href="http://somegreatsite.com">Link Name</a>

is a link to another nifty site

<H1>This is a Header</H1>

<H2>This is a Medium Header</H2> 
<HR/>

</BODY>

</HTML> '
