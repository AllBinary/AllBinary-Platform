//Image Src Modifier
function ImageSrcAttributeHashMapPreload(imageTagId)
{
   //properties
   this.images = new Array();
   this.imageTagId = imageTagId;

   //methods
   this.put = ImageSrcAttributeHashMapPutPreload;
   this.remove = ImageSrcAttributeHashMapRemovePreload;
   this.get = ImageSrcAttributeHashMapGetPreload;

   this.set = ImageSrcAttributeHashMapSetPreload;
   //log("ImageSrcAttributeHashMapPreload: Constructing");
}

//Adds a new image key and fileName 
function ImageSrcAttributeHashMapPutPreload(key, value)
{
   //log("ImageSrcAttributeHashMapPreload: put");
   //log("Adding: " + key + "=" + value);
   this.images[key] = new Image();
   this.images[key].src = value;
   //log("Adding: " + key + "=" + this.images[key].src);
}

function ImageSrcAttributeHashMapRemovePreload(key)
{
   this.images[key] = null;
}

function ImageSrcAttributeHashMapGetPreload(key)
{
   //log("Getting: " + key + "=" + this.images[key].src);
   return this.images[key].src;
}

function ImageSrcAttributeHashMapSetPreload(htmlElement)
{
   //HTMLImageElement
   var htmlImageElement = document.getElementById(this.imageTagId);
   //log("Get: " + htmlElement.value);
   htmlImageElement.src = this.get(htmlElement.value);
}
