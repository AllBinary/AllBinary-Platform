var selectedColorDivTag = new Array();
var selectedColorInputTag = new Array();
var selectedColorTextInputTag = new Array();
var selectedColorTag = new Array();
var selectedRootCssElementTree = new Array();
var selectedCssProperty = new Array();

var colorSelectorId = "colorSelector";
var selectedColorId = "selectedColor";

<%@ include file="/javascript/css/form/update/preview/javascriptUpdatePreview.jsp" %>

<%@ include file="/javascript/css/form/update/javascriptCssElementChanged.jsp" %>

<%@ include file="/javascript/css/form/update/color/javascriptMoveColorSelector.jsp" %>

<%@ include file="/javascript/css/form/update/color/javascriptSetColorRunnable.jsp" %>

<%@ include file="/javascript/css/form/update/events/onchange/input/javascriptOnChangeInputRunnable.jsp" %>
<%@ include file="/javascript/css/form/update/events/onchange/select/javascriptOnChangeSelectRunnable.jsp" %>
