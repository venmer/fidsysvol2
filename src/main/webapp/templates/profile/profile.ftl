<#-- @ftlvariable name="model" type="ru.mremne.view.ViewUserData" -->
<#import "../../layout/mainlayout.ftl" as layout />
<@layout.layout title="FidSys.${model.authUser.login?html}" >
<div class="row user-info">
    <div class="col-xs-12 col-md-8">
        <div class="panel panel-default ">
            <div class="panel-body">
                <div class="col-xs-8" >
                    <div class="col-sm-10">
                        <h2 class="details-view" id="profile-name">${model.authUser.name?html}</h2>
                    </div>
                </div>
            </div>
            <#if model.authUser??>
                <div class="panel-footer">
                    <div class="row">
                        Some description
                    </div>
                </div>
            </form>
            </#if>
        </div>
    </div>

    <div class="col-xs-12 col-sm-12 col-md-4">
        <div class="panel panel-primary">
            <div class="panel-heading ">
                <h3 class="panel-title">
                    Info
                </h3>
            </div>
            <div class="panel-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-5 text-right">Login</label>

                        <div class="col-sm-7" id="profile-login">
                        ${model.authUser.login?html}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 text-right">Email</label>

                        <div class="col-sm-7" id="profile-email">
                        ${model.authUser.email}
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="col-xs-12 col-sm-12 col-md-6">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4>Results</h4>
        </div>
        <div class="panel-body">
        <table class="table table-bordered" id="products">
            <thead>
            <th>Id</th>
            <th>Result</th>
            <th>Status</th>
            <th>Time</th>
            <th>Match</th>
            </thead>
            <tbody>
                <#if layout.user.authUser.results??>
                    <#list layout.user.authUser.results as result>
                    <tr>
                        <td>${result.userId}</td>
                        <td>${result.idResult}</td>
                        <td>${result.status}</td>
                        <td>${result.timestamp?number_to_datetime}</td>
                        <td>${result.match?string["0.##"]}%</td>
                    </tr>
                    </#list>
                <#else >
                <p>No results yet..</p>
                </#if>
            </tbody>
        </table
    </div>
        </div>

</@layout.layout>