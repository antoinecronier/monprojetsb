<#import "/spring.ftl" as spring/>
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<body>
  <H1>Product Create</H1>

  <form action="<@spring.url '/users/create'/>" method="POST">
    Firstname:<br>
    <input type="text" name="firstname" value="">
    <br>
    Lastname:<br>
    <input type="text" name="lastname" value="">
    <br><br>

    <input type="submit" value="Submit">
  </form>
</body>
