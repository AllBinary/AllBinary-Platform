String.prototype.equalsIgnoreCase = StringEqualsIgnoreCase;
String.prototype.indexOfIgnoreCase = StringIndexOfIgnoreCase;

function StringEqualsIgnoreCase(strTerm)
{
   var strToSearch = this.toLowerCase();
   strTerm = strTerm.toLowerCase();
   if(strToSearch == strTerm)
   {
      return true;
   }
   else
   {
      return false;
   }
}

function StringIndexOfIgnoreCase(strTerm)
{
   var strToSearch = this.toLowerCase();
   strTerm = strTerm.toLowerCase();

   return strToSearch.indexOf(strTerm);
}
