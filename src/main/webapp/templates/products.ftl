<#-- @ftlvariable name="model" type="ru.mremne.view.ViewData" -->
<#assign products = model>
<#import "/layout/mainlayout.ftl" as layout/>
<@layout.layout title="Products">
<div class="col-md-8" style="overflow-y: auto">
    <h2>All Products</h2>
    <br>
        <header class="entry-header">
            <table class="table table-bordered" id="products">
                <thead>
                <th>Type</th>
                <th>Description</th>
                </thead>
                <tbody>
                    <#list products.allProducts as products>
                    <tr>
                        <td>${products.type}</td>
                        <td>${products.description}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </header>
  </div>
</@layout.layout>