<#import "../../layout/mainlayout.ftl" as layout />
<@layout.layout title="Register error">

<div class="row">
    <div class="col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10">
        <div class="alert alert-warning" role="alert">
            For registration you need to enter:
            <ol>
                <li>Your real name (it should not be empty or consists only spaces).</li>
                <li>Specify your login (it should be unique).</li>
                <li>Specify your password (it should not be empty or consists only spaces too).</li>
            </ol>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".register-modal" >
                Try again
            </button>
        </div>
    </div>
</div>

</@layout.layout>