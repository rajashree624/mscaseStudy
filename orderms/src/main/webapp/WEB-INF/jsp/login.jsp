<html>

<head>
<title>Spring Boot Application</title>
</head>

<body>
    <font color="red">${errorMessage}</font>
    <form method="post">
        Name : <input type="text" name="name" />
        Password : <input type="password" name="password" /> 
        <input type="submit" />
        Token : <label id="token">${token}</label>
    </form>
</body>

</html>