<html>
<head>
  <title>Repo</title>
</head>
<body>
<h1>Repository title: ${repository}</h1>

<h2>Images:</h2>
<#if images?size == 0>
  <p>No images found.</p>
<#else>
  <ul>
    <#list images as name>
      <li>${name}</li>
    </#list>
  </ul>
</#if>
</body>
</html>
