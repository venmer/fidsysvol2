<#import "/layout/mainlayout.ftl" as layout>
<@layout.layout title="FidSys Step 2">
<div style="height:100px;overflow: auto;">
    <table class="table table-bordered" id="testTable1" align="center">
        <thead>
        <tr>
            <th>n</th>
            <th>x</th>
            <th>y</th>
        </tr>
        </thead>
        <tr id="tr1">

        </tr>

    </table>
</div>
<input type="button" value="Fill Cell" onclick="fillCell('testTable1','tr1')" />
</@layout.layout>