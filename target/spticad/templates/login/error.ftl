<#import "/layout/mainlayout.ftl" as layout />
<@layout.layout title="Login error">

<div class="row">
    <div class="col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10">
        <div class="alert alert-danger" role="alert">
            Wrong user name or password.
            Please,
            <a class="alert-link" href="#" data-toggle="modal" data-target=".signin-modal" >try again</a>.
        </div>
    </div>
</div>

</@layout.layout>