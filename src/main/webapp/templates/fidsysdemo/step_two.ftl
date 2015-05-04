<#import "/layout/mainlayout.ftl" as layout>
<@layout.layout title="FidSys Step 2">
<div class="row">
    <h2>Product info</h2>
    <div class="col-md-12">
        <div class="col-md-12">
            <div >
                <label class="control-label">Angles</label>
                <textarea  id="description" onfocus="init_desc()" class="form-control" readonly></textarea>
            </div>
            <form class="form" role="form" >
                <div class="control-group">
                    <label class="control-label">Product type</label>
                    <input type="text" class="form-control" rows="1">
                    <label class="control-label">Description</label>
                    <div class="controls">
                        <textarea rows="8" class="form-control" placeholder="Description" required></textarea>
                    </div>
                </div>
                <br>
                <div class="form-actions">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-danger pull-right">Save</button>
                    </div>
                </div>
            </form>
        </div>
    </div>


</div>

</@layout.layout>