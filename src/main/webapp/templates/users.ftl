<#-- @ftlvariable name="model" type="ru.mremne.view.ViewUserData" -->
<#assign users = model>
<#import "/layout/mainlayout.ftl" as layout/>
<@layout.layout title="Users">
<div class="col-md-8">
    <h2>All Users</h2>
    <br>
    <header class="entry-header">
        <table class="table table-bordered header-fixed" id="products" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <th>Name</th>
            <th>Surname</th>
            </thead>
            <tbody>
                <#list users.users as user>
                <tr>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                </tr>
                </#list>
            </tbody>
        </table>
    </header>
</div>
</@layout.layout>