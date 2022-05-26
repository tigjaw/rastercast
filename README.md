<html>
<body>
  
  <div id="header">
    <h1>RasterCast</h1>
    <h2>Included in this repo:</h2>
    <ul>
      <li>
        RasterCast - a library which allows you to read and write various image file formats, including .ico files.
      </li>
      <li>
        An application which utilises the library to batch convert images to a chosen raster filetype and create your own ico files.
      </li>
    </ul>
    <p>
    I wrote this as part of a larger application but decided to upload it individually and work on it as a side-project.
    <br>
    Utilises the <a href="https://github.com/imcdonagh/image4j">imag4j</a> library to handle ico files. All other code is my own.
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
        choose a raster format to save as
      </li>
      <li>
        option to delete original images
      </li>
      <li>
        save (as chosen image format)
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
    <img src="https://github.com/tigjaw/rastercast/blob/main/screenshots/1-rastercast-main.png" width="23%"></img>
    <img src="https://github.com/tigjaw/rastercast/blob/main/screenshots/2-rastercast-open.png" width="23%"></img>
    <img src="https://github.com/tigjaw/rastercast/blob/main/screenshots/3-rastercast-opened.png" width="23%"></img>
    <img src="https://github.com/tigjaw/rastercast/blob/main/screenshots/4-rastercast-saved.png" width="23%"></img>
  </div>
  
  <div id="credits">
    <h2>Credits</h2>
    - ico file handling by <a href="https://github.com/imcdonagh/image4j">imag4j</a>
  </div>

</body>
</html>
