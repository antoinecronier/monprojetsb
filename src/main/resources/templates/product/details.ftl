<#import "/spring.ftl" as spring/>
<head>
    <#include "../includable/bootstrap.ftl">
</head>
<body>
    <#if page??>
    <h1> ${page} </h1>
    </#if>

    Name: ${item.name}
    <br>
    Price: ${item.price}
    <br><br>

  <a href="${backlink}">Retour à l'index<a/>
</body>
