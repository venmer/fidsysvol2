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

                        <div class="col-xs-6 text-right" >
                            <button type="button" class="btn btn-success btn-sm details-view"
                                    onclick="turnToEdit()">Edit</button>

                            <div class="btn-group edit-view hidden" >
                                <button type="submit" class="btn btn-success btn-sm"
                                        onsubmit="turnToDetails()">Save</button>

                                <button type="button" class="btn btn-default btn-sm"
                                        onclick="turnToDetails()">Cancel</button>
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

<div class="row">
    <div class="col-xs-12 col-sm-6">
        <div class="user-profile-collapse-button">
            <a data-toggle="collapse"
               onclick="if ( $('#user-comments').hasClass('in') ) {$('#user-comments').collapse('hide')}"
               class="btn btn-success col-xs-12"
               href="#user-posts" aria-expanded="false" aria-controls="user-posts">
                <h4>Results</h4>
            </a>
        </div>
    </div>

    <div class="col-xs-12 col-sm-6">
        <div class="user-profile-collapse-button">
            <a data-toggle="collapse"
               onclick="if ( $('#user-posts').hasClass('in') ) {$('#user-posts').collapse('hide')}"
               class="btn btn-warning col-xs-12 "
               href="#user-comments" aria-expanded="false" aria-controls="user-comments" >
                <h4>Additional result info </h4>
            </a>
        </div>
    </div>
    <script>
        UPLOADCARE_LOCALE = "en";
        UPLOADCARE_TABS = "file";
        UPLOADCARE_AUTOSTORE = true;
        UPLOADCARE_PUBLIC_KEY = "6840cb6125ff4ccc690f";
    </script>
    <script src="//ucarecdn.com/widget/1.5.3/uploadcare/uploadcare.full.min.js"></script>

    <script>
        setTimeout( function() {
            $(".uploadcare-widget-button").addClass("btn btn-primary btn-sm");
        }, 500);

        function turnToEdit() {
            $(".edit-view").removeClass( "hidden");
            $(".details-view").addClass( "hidden");
        }

        function turnToDetails() {
            $(".edit-view").addClass( "hidden");
            $(".details-view").removeClass( "hidden");
        }
    </script>

</@layout.layout>