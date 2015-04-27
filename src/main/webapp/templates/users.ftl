<#-- @ftlvariable name="model" type="ru.mremne.view.ViewUserData" -->
<#assign users = model>
<#import "/layout/mainlayout.ftl" as layout/>
<@layout.layout title="Users">
<article class="product">
    <header class="entry-header">
        <table class="table table-striped header-fixed" id="products" cellspacing="0" cellpadding="0" border="0" align="center">
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
</article>
</@layout.layout>