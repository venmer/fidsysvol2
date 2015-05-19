<#-- @ftlvariable name="model" type="ru.mremne.view.ViewUserData" -->
<#assign user=model>
<#macro layout title="Fidsys">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <!-- Bootstrap -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <link href="/public/css/superhero/bootstrap.min.css" rel="stylesheet">
    <!--JsFeat-->
    <link href="/public/css/main.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 web.elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the web.page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

        <![endif]-->
    </head>
    <body>
        <nav class="navbar navbar-default  navbar-fixed-top padding" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a href="/" class="navbar-brand">FidSys</a>
                </div>
                <div class="navbar-collapse collapse" id="navbar-main">
                    <ul class="nav navbar-nav">
                        <#if model.authUser??>
                        <li>
                            <a href="/profile" id="allusers">Profile</a>
                        </li>
                        <li><a href="/try">Try FidSys!</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right" >

        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
               id="user-menu" aria-expanded="false">
            ${model.authUser.login?html}
                <span class="fa fa-user"></span>
                <span class="caret"></span>
            </a>
            <ul class="dropdown-menu" role="menu">
                <li><a id="user-profile" href="/profile">Profile</a></li>
                <li><a id="user-signout" href="/auth/signout">Sign out</a></li>
            </ul>
        </li>
    <#else >
    </ul>
    <ul class="nav navbar-nav navbar-right" >
                        <li>
                            <a href="#" id="register" data-toggle="modal" data-target=".register-modal" >Register</a>
                        </li>
                        <li>
                            <a href="#" id="signin" data-toggle="modal" data-target=".signin-modal" >Sign in</a>
                        </li>
    </#if>
                    </ul>
            </div>
        </nav>
    </div>
    <div class="container" align="left" id="content" style="padding-top: 50px">
        <#nested />
         <#include "/templates/login/modal.ftl">
        <#include "/templates/register/modal.ftl">
    </div>
        </div>
    </div>
    <nav  class="navbar navbar-default navbar-fixed-bottom" id="footer" >
        <div class="container">
            <div class="col-lg-12">
                <br>
                <p>SPBGTI 2015</p>
            </div>
        </div>
    </nav>
</div>
    <script type="text/javascript" src="/public/js/jsfeat/jsfeat-min.js"></script>
    <script type="text/javascript" src="/public/js/jsfeat/compatibility.js"></script>
    <script type="text/javascript" src="/public/js/jsfeat/profiler.js"></script>
    <script type="text/javascript" src="/public/js/jsfeat/dat.gui.min.js"></script>
    <script type="text/javascript" src="/public/js/jsfeat/fun.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

</body>
</html>
</#macro>