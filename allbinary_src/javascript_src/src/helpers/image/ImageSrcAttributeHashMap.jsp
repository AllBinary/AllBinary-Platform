//Image Src Modifier
function ImageSrcAttributeHashMap(imageTagId)
{
   //properties
   this.images = new Array();
   this.imageTagId = imageTagId;

   //methods
   this.put = ImageSrcAttributeHashMapPut;
   this.remove = ImageSrcAttributeHashMapRemove;
   this.get = ImageSrcAttributeHashMapGet;

   this.set = ImageSrcAttributeHashMapSet;
}

//Adds a new image key and fileName 
function ImageSrcAttributeHashMapPut(key, value)
{
   this.images[key] = value;
}

function ImageSrcAttributeHashMapRemove(key)
{
   this.images[key] = null;
}

function ImageSrcAttributeHashMapGet(key)
{
   return this.images[key];
}

function ImageSrcAttributeHashMapSet(htmlElement)
{
   //HTMLImageElement
   var htmlImageElement = document.getElementById(this.imageTagId);
   htmlImageElement.src = this.get(htmlElement.value);
}

function swapImage(imageOne, imageTwo)
{

}

//setImageSrcByIdWithUpdatedInputValue('themeImageTagId', 'inputTagId' or this)
function setImageSrcByIdWithUpdatedInputValue(imageTagId, inputTagId)
{
}

//set from array of images
function setImageSrcById(imageTagId, imageArrayKey, imageIndex)
{
}
 