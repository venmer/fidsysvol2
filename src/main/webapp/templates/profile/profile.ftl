<#-- @ftlvariable name="model" type="ru.mremne.view.ViewUserData" -->
<#import "../../layout/mainlayout.ftl" as layout />
<@layout.layout title="FidSys.${model.authUser.login?html}" >
<div class="row">
    <div class="col-xs-12 col-md-8">
        <div class="panel panel-default user-info">
            <#if model.authUser??>
            <form action="/user/save" method="post">
            </#if>
            <div class="panel-body">
                <div class="col-xs-8" >
                    <div class="col-sm-10">
                        <h2 class="details-view">${model.authUser.name?html}</h2>
                        <div class="styled-input wide edit-view hidden">
                            <input type="text"
                                   class="form-control"
                                   name="user-profile-name"
                                   id="user-profile-name"
                                   value="${model.authUser.name}" required >
                            <label>Name</label>
                            <span></span>
                        </div>

                        <div class="styled-input wide edit-view hidden">
                            <input type="password"
                                   class="form-control"
                                   name="user-profile-password"
                                   id="user-profile-password"
                                   value="${model.authUser.password}" >
                            <label>Password</label>
                            <span></span>
                        </div>
                    </div>
                </div>
            </div>

            <#if model.authUser??>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-xs-6" >
                            <div class="uploader edit-view hidden">
                                <input  type="hidden" role="uploadcare-uploader"
                                        data-images-only="true" name="user-profile-avatar"/>
                            </div>
                        </div>
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
                        <div class="col-sm-7">
                        ${model.authUser.login?html}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-5 text-right">Email</label>
                        <div class="col-sm-7">
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
            </thead>
            <tbody>
                <#if layout.user.authUser.results??>
                    <#list layout.user.authUser.results as result>
                    <tr>
                        <td>${result.id}</td>
                        <td>${result.idResult}</td>
                        <td>${result.status}</td>
                        <td>${result.timestamp?number_to_date}</td>
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