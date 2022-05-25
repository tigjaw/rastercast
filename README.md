<html>
<body>
  
  <div id="header">
    <h1>Rastercast</h1>
    <p>
    A small Java application to batch convert images to a chosen raster filetype.
    Can also convert images to .ico, using the image4j library.
    </p>
    <p>
    These features were written as part of a larger application but I decided to upload it individually and work on it as a side-project.
    </p>
  </div>
  
  <hr>
    
  <div id="features">
    <h2>Features</h2>
    <ul>
      <li>
        supported file-types: png, jpg, gif, tif, bmp, ico
      </li>
      <li>
        select images to convert
      </li>
      <li>
        choose a file-type to save to
      </li>
      <li>
        delete original images - yes/no checkbox
      </li>
      <li>
        save as chosen filetype
      </li>
      <li>
        logs all events to a text area
      </li>
    </ul>
  </div>
  
  <div id="todo" align="right">
    <h2>Todo</h2>
    <ul dir="rtl">
      <li>
        add optional save location
      </li>
      <li>
        add optional filename suffix/prefix
      </li>
      <li>
        move save-options into separate dialog
      </li>
    </ul>
  </div>
  
  <hr>
    
  <!--
    1 image per row = 90%
    2 image per row = 45%
    3 image per row = 30%
    4 image per row = 23%
  -->
  <div id="gui" align="center">
    <h2>GUI</h2>
    <img src="https://github.com/tigjaw/rastercast/blob/2bc43db316471c000f62705e8fcff8c39aac6741/screenshots/1-rastercast-main.png" width="23%"></img>
    <img src="https://github.com/tigjaw/rastercast/blob/2bc43db316471c000f62705e8fcff8c39aac6741/screenshots/2-rastercast-open.png" width="23%"></img>
    <img src="https://github.com/tigjaw/rastercast/blob/2bc43db316471c000f62705e8fcff8c39aac6741/screenshots/3-rastercast-opened.png" width="23%"></img>
    <img src="https://github.com/tigjaw/rastercast/blob/2bc43db316471c000f62705e8fcff8c39aac6741/screenshots/4-rastercast-saved.png" width="23%"></img>
  </div>

</body>
</html>
