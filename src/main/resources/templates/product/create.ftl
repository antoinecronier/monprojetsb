<#import "/spring.ftl" as spring/>
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<body>
  <H1>Product Create</H1>

  <form action="<@spring.url '/products/create'/>" method="POST">
    Name:<br>
    <input type="text" name="name" value="">
    <br>
    Price:<br>
    <input type="numeric" name="price" value="">
    <br><br>

    <input type="submit" value="Submit">
  </form>
</body>
