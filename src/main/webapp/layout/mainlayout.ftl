
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
    <!-- Wrap all page content here -->
    <div id="wrap">

        <!-- Begin page content -->
        <div class="container">
    <div>
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
                            <a href="/products/all" id="allproducts">All products</a>
                        </li>
                        <li>
                            <a href="/users/all" id="allusers">All users</a>
                        </li>
                        <li><a href="/try">Try FidSys!</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">About</a></li>
                        <li><a href="/users/new">Register</a></li>
            </div>
        </nav>
    </div>
    <div class="container" align="center" id="content" style="padding-top: 50px">
        <#nested />
    </div>


        </div>
    </div>

    <div id="footer">
        <div class="container">
            <div class="col-lg-12">
                <br>
                <p>SPBGTI 2015</p>
            </div>
        </div>

    </div>
</div>


<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="/public/js/main.js"></script>
</body>
</html>
</#macro>