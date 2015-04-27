<#-- @ftlvariable name="model" type="ru.mremne.view.ViewData" -->
<#macro layout title="Fidsys">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FIDsys demo</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!--JsFeat-->
    <link href="/public/css/jsfeat/jsfeat.css" rel="stylesheet">
    <link href="/public/css/main.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
    <div id="wrap">
        <nav class="navbar navbar-inverse navbar-fixed-top padding" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a href="/" class="navbar-brand">FidSys</a>
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="navbar-collapse collapse" id="navbar-main">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="/products/" id="allproducts">All products</a>
                        </li>
                        <li>
                            <a href="#" id="allusers">All users</a>
                        </li>
                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="/about">About</a></li>

            </div>
        </nav>

    </div>
    <div class="container" id="table" style="padding-top: 50px" overflow="auto">
    <table class="table table-striped" id="products" cellspacing="0" cellpadding="0" border="0" align="center">
        <thead>
        <th>ID</th>
        <th>Type</th>
        <th>Description</th>
        </thead>
        <tbody>
        <#list model.allProducts as products>
            <tr>
                <td>${products.id}</td>
                <td>${products.type}</td>
                 <td>${products.description}</td>
        </tr>
        </#list>
        </tbody>
    </table>
        </div>


    <div class="container" id="content">
        <#nested />
    </div>

    <div id="push"></div>
</div>

    <div class="navbar navbar-inverse navbar-fixed-bottom">
        <div class="container">
    <span class="navbar-text">
      SPTICAD
    </span>
        </div>
    </div>

<div id="back-to-top" >
    <a href="#" style=""><i class="fa fa-angle-up"></i></a>
</div>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="/public/js/main.js"></script>
</body>
</html>
</#macro>