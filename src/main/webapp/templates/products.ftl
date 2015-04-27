<#-- @ftlvariable name="model" type="ru.mremne.view.ViewData" -->
<#assign products = model>
<#import "/layout/mainlayout.ftl" as layout/>
<@layout.layout title="Products">
    <article class="product">
        <header class="entry-header">
            <table class="table table-striped header-fixed" id="products" align="center">
                <thead>
                <th>ID</th>
                <th>Type</th>
                <th>Description</th>
                </thead>
                <tbody>
                    <#list products.allProducts as products>
                    <tr>
                        <td>${products.id}</td>
                        <td>${products.type}</td>
                        <td>${products.description}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </header>
    </article>
</@layout.layout>