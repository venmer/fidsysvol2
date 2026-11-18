<#-- @ftlvariable name="model" type="ru.mremne.view.ViewUserData" -->
<#import "/layout/mainlayout.ftl" as layout />
<@layout.layout title="FidSys. Results">
<div class="col-xs-12 col-sm-12 col-md-6">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h4>Results</h4>
        </div>
        <div class="panel-body">
            <table class="table table-bordered">
                <thead>
                <th>Id</th>
                <th>Result</th>
                <th>Status</th>
                <th>Time</th>
                <th>Match</th>
                </thead>
                <tbody>
                <#if model.authUser?? && model.authUser.results??>
                    <#list model.authUser.results as result>
                        <tr>
                            <td>${result.userId}</td>
                            <td>${result.idResult}</td>
                            <td>${result.status}</td>
                            <td>${result.timestamp?number_to_datetime}</td>
                            <td>${result.match?string["0.##"]}%</td>
                        </tr>
                    </#list>
                <#else>
                    <tr>
                        <td colspan="5">No results yet..</td>
                    </tr>
                </#if>
                </tbody>
            </table>
        </div>
    </div>
</div>
</@layout.layout>
