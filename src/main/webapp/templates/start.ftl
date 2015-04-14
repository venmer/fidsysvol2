<#import "/templates/mainlayout.ftl" as layout />
<@layout.layout title="Get photo">
<!--<div class="row">
    <div class="col-md-12   ">
    <div class="page-header">
        <h2>FIDsys</h2>
    </div>
<div class="panel-body" id="container"  >
    <div class="select">
        <label for="audioSource">Audio source: </label><select id="audioSource"></select>
    </div>
    <div class="select">
        <label for="videoSource">Video source: </label><select id="videoSource"></select>
    </div>
        <video id="video" autoplay="autoplay" ></video>
  <script src="/public/js/main.js"></script>
    <p>This demo requires Chrome 30 or later.</p>
</div>
    </div>
</div>-->
<video id="webcam" width="640" height="480" style="display:none;"></video>
<div style=" width:640px;height:480px;margin: 10px auto;">
    <canvas id="canvas" width="640" height="480"></canvas>
    <div id="no_rtc" class="alert alert-error" style="display:none;"></div>
    <div id="log" class="alert alert-info"></div>
</div>
<form id="testform" align="center">
    <input type="text" id="text_area"/>
    <input type="button" value="Submit" onclick="test_this()"/>
</form>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="/public/js/jsfeat/jsfeat-min.js"></script>
<script type="text/javascript" src="/public/js/jsfeat/compatibility.js"></script>
<script type="text/javascript" src="/public/js/jsfeat/profiler.js"></script>
<script type="text/javascript" src="/public/js/jsfeat/dat.gui.min.js"></script>
<script type="text/javascript" src="/public/js/jsfeat/fun.js"></script>
</@layout.layout>