
<#import "/templates/mainlayout.ftl" as layout/>
<@layout.layout title="Products">
    <article class="product">
        <header class="entry-header">
            <table class="table table-striped" id="products" cellspacing="0" cellpadding="0" border="0" align="center">
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