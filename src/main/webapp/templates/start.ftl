<#import "/templates/mainlayout.ftl" as layout />
<@layout.layout title="Get photo">
<div class="row">
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
</div>

</@layout.layout>