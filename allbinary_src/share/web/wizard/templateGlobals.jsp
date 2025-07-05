<%
/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 * 
 */
%>
<%
   //template view - for page configuration

   final String CONTEXTROOTVIEWOBJECTFILE = CONTEXTCLASSPATH + "RootContextView";
   final String OVERRIDEOBEJCTCONFIGCONTEXTROOTVIEWOBJECTFILE = CONTEXTCLASSPATH + "OverrideObjectConfigRootContextView";
   //final String PREVIEWCONTEXTROOTVIEWOBJECTFILE = CONTEXTCLASSPATH + "PreviewRootContextView";

   final String STOREFRONTCONTEXTGENERATORVIEWOBJECTFILE = STOREFRONTCONTEXTCLASSPATH + "generator.GeneratorStoreFrontContextView";
   final String PREVIEWSTOREFRONTCONTEXTGENERATORVIEWOBJECTFILE = STOREFRONTCONTEXTCLASSPATH + "generator.PreviewGeneratorStoreFrontContextView";
   //final String PREVIEWIMAGESTOREFRONTCONTEXTGENERATORVIEWOBJECTFILE = STOREFRONTCONTEXTCLASSPATH + "generator.PreviewImageGeneratorStoreFrontContextView";

   final String GENERICTEMPLATEFILE = "template.xsl";
   final String PREVIEWGENERICTEMPLATEFILE = "previewTemplate.xsl";
   final String GENERICERRORTEMPLATEFILE = "error.xsl";

   final String GENERICBODYTEMPLATEOBJECTCONFIGFILE = TEMPLATEDIR + BODYDIR + "genericBodyTemplateObjectConfig.xml";

   final String GENERICCOMPOUNDTEMPLATEOBJECTCONFIGFILE = TEMPLATEDIR + "compound/genericCompoundTemplateObjectConfig.xml";

   final String GENERICBODYCUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "genericBodyCustomizerObjectConfig.xml";
   final String TITLEBODYCUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + BODYDIR + "titleBodyCustomizerObjectConfig.xml";

   final String GENERICCUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + GENERICDIR + "genericCustomizerObjectConfig.xml";

   final String ROOTTEMPLATESCUSTOMIZEROBJECTCONFIGFILE = TEMPLATEDIR + "genericRootTemplateObjectConfig.xml";

   final String GENERICTEMPLATEOBJECTCONFIG = "genericTemplateObjectConfig.xml";

   Vector templateTypesVector = new Vector();
   templateTypesVector.add("");
   templateTypesVector.add(" " + TransformInfosData.PREVIEW);
%>
