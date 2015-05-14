<#import "../../layout/mainlayout.ftl" as layout />
<@layout.layout title="User exists">

<div class="row">
    <div class="col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10">
        <div class="alert alert-danger" role="alert">
            Sorry, but login that you specified is busy.
            <a class="alert-link" href="#" data-toggle="modal" data-target=".register-modal" >
                Enter another one
            </a>.
        </div>
    </div>
</div>

</@layout.layout>