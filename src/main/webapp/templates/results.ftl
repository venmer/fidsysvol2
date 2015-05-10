<#-- @ftlvariable name="model" type="ru.mremne.view.ViewResultData" -->
<#assign results = model>
<#import "/layout/mainlayout.ftl" as layout/>
<@layout.layout title="Welcome to FidSys">
<div class="col-md-8">
    <header class="entry-header">
        <h2>Latest notes</h2>
        <header class="entry-header">
            <table class="table table-bordered" id="products">
                <thead>
                <th>Id</th>
                <th>Result</th>
                <th>Status</th>
                <th>Time</th>
                </thead>
                <tbody>
                    <#list results.results as result>
                    <tr>
                        <td>${result.id}</td>
                        <td>${result.idResult}</td>
                        <td>${result.status}</td>
                        <td>${result.timestamp?number_to_date}</td>
                    </tr>
                    </#list>
                </tbody>
            </table
        </header>
</div>
</@layout.layout>