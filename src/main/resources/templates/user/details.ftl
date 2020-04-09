<#import "/spring.ftl" as spring/>
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<body>
    <#if page??>
    <h1> ${page} </h1>
    </#if>

    Firstname: ${item.firstname}
    <br>
    Lastname: ${item.lastname}
    <br>
    Produits :
    <br>
    <#list item.products as item>
        Name: ${item.name}
      <br>
      Price: ${item.price}
      <br>
    </#list>
    <br>
    <br>

  <a href="${backlink}">Retour à l'index<a/>
</body>
