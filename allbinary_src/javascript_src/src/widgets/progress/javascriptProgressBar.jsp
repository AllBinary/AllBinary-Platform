var PLEASEWAIT = "Please Wait";

function ProgressBar()
{
   this.id = "wizardProgressBar";
   this.value = 0;
   this.show = ProgressBarShow;
   this.hide = ProgressBarHide;
   this.set = ProgressBarSet;
   this.add = ProgressBarAdd;
   this.isVisible = ProgressBarIsVisible;
}

function ProgressBarShow()
{
   display(document.getElementById(this.id));
}

function ProgressBarHide()
{
   hide(document.getElementById(this.id));
}

function ProgressBarSet(aValue)
{
   this.value = aValue;
}

function ProgressBarAdd(aValue)
{
   this.value = this.value + aValue;
}

function ProgressBarIsVisible()
{
   if(document.getElementById(this.id).style.display == 'block')
   {
      return true;
   }
   else
   if(document.getElementById(this.id).style.display == 'none')
   {
      return false;
   }
   else
   {
      //log("ProgressBar isShown Error");
      return false;
   }
}
