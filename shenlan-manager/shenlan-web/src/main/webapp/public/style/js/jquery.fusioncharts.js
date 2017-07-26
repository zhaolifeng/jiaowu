/*!
 * FusionCharts Free jQuery Library and Plugin
 * Copyright (c) 2010 FusionCharts
 * http://www.fusioncharts.com/
 *
 * @publish: March 31, 2010
 * @version: 1.0.0b2 (build: 108)
 *
 */
jQuery.FusionCharts={version:[1,0,0,108,'free','b2'],config:{id:'fusioncharts',type:'Column2D',width:'320',height:'240',insertMode:'clear',swfPath:'FusionChartsFree/Charts',swfURL:undefined,data:undefined,dataFormat:undefined,dataOptions:{},debugMode:false,scaleMode:'noScale',wMode:'transparent',allowScriptAccess:'always',quality:'high',bgColor:undefined,lang:'en',className:'FusionCharts',product:'free',swfName:undefined,detectFlashVersion:false,autoInstallRedirect:false,registerWithJS:true,resizable:false,update:false,forceReload:true,extend:jQuery.fn.extend},meta:{products:{v3:[0,'','.swf','8,0,0,0','chart','FusionCharts'],free:[1,'FCF_','.swf','6,0,54,0','graph','FusionCharts']},datavariants:{'undefined':['Undefined',false],'singleseries':['Single-Series',false],'multiseries':['Multi-Series',true],'multiseriesstacked':['Multi-Series Stacked',true],'singlevaluegauge':['Single-Value Gauge',false],'multivaluegauge':['Multi-Value Gauge',false],'multilevelpie':['Multi-Level Pie',true],'gantt':['Gantt',false],'drawingpad':['Drawing Pad',false]},alias:{dragcolumn2d:[['DragColumn2D',-1],'multiseries'],dragline:[['DragLine',-1],'multiseries'],dragarea:[['DragArea',-1],'multiseries'],errorbar2D:[['ErrorBar2D',-1],'multiseries'],selectscatter:[['SelectScatter',-1],'multiseries'],dragnode:[['DragNode',-1],'multiseries'],kagi:[['Kagi',-1],'singleseries'],logcolumn2d:[['LogMSColumn2D',-1],'multiseries'],logline2d:[['LogMSLine',-1],'multiseries'],multilevelpie:[['MultiLevelPie',-1],'multilevelpie'],multiaxisline:[['MultiAxisLine',-1],'multiseriesstacked'],radar:[['Radar',-1],'multiseries'],funnel:[['Funnel',0],'singleseries'],candlestick:[['Candlestick',0],'singleseries'],gantt:[['Gantt',0],'gantt'],spline2d:[['Spline',-1],'singleseries'],msspline2d:[['MSSpline',-1],'multiseries'],splinearea2d:[['SplineArea',-1],'singleseries'],mssplinearea2d:[['MSSplineArea',-1],'multiseries'],inversearea2d:[['InverseMSArea',-1],'multiseries'],inversecolumn2d:[['InverseMSColumn2D',-1],'multiseries'],inverseline2d:[['InverseMSLine',-1],'multiseries'],waterfall:[['Waterfall2D',-1],'singleseries'],scatter:[['Scatter',-1],'multiseries'],bubble:[['Bubble',-1],'multiseries'],column3d:[['Column3D',0],'singleseries'],column2d:[['Column2D',0],'singleseries'],mscolumn3d:[['MSColumn3D',0],'multiseries'],mscolumn2d:[['MSColumn2D',0],'multiseries'],stackedbar2d:[['StackedBar2D',0],'multiseries'],stackedcolumn3d:[['StackedColumn3D',0],'multiseries'],stackedcolumn2d:[['StackedColumn2D',0],'multiseries'],stackedbar3d:[['StackedBar3D',-1],'multiseries'],stackedarea2d:[['StackedArea2D',0],'multiseries'],stackedcolumn3dlinedy:[['StackedColumn3DLineDY',-1],'multiseries'],pie2d:[['Pie2D',0],'singleseries'],pie3d:[['Pie3D',0],'singleseries'],doughnut2d:[['Doughnut2D',0],'singleseries'],donut2d:[['Doughnut2D',0],'singleseries'],doughnut3d:[['Doughnut3D',-1],'singleseries'],donut3d:[['Doughnut3D',-1],'singleseries'],line2d:[['Line',0],'singleseries'],msline2d:[['MSLine',0],'multiseries'],bar2d:[['Bar2D',0],'singleseries'],msbar2d:[['MSBar2D',0],'multiseries'],msbar3d:[['MSBar3D',-1],'multiseries'],area2d:[['Area2D',0],'singleseries'],msarea2d:[['MSArea','MSArea2D'],'multiseries'],mscombi2d:[['MSCombi2D',-1],'multiseries'],mscombi3d:[['MSCombi3D',-1],'multiseries'],mscombidy2d:[['MSCombiDY2D','MSColumn2DLineDY'],'multiseries'],msstackedcolumn2d:[['MSStackedColumn2D',-1],'multiseriesstacked'],msstackedcolumn2dlinedy:[['MSStackedColumn2DLineDY',-1],'multiseriesstacked'],mscolumn3dlinedy:[['MSColumn3DLineDY',0],'multiseries'],mscolumn3dline:[['MSColumnLine3D',-1],'multiseries'],scrollarea2d:[['ScrollArea2D',-1],'multiseries'],scrollcolumn2d:[['ScrollColumn2D',-1],'multiseries'],scrollline2d:[['ScrollLine2D',-1],'multiseries'],scrollcombi2d:[['ScrollCombi2D',-1],'multiseries'],scrollcombidy2d:[['ScrollCombiDY2D',-1],'multiseries'],scrollstackedcolumn2d:[['ScrollStackedColumn2D',-1],'multiseries'],realtimearea:[['RealTimeArea',-1],'multiseries'],realtimecolumn:[['RealTimeColumn',-1],'multiseries'],realtimeline:[['RealTimeLine',-1],'multiseries'],realtimestackedarea:[['RealTimeStackedArea',-1],'multiseries'],realtimestackedcolumn:[['RealTimeStackedColumn',-1],'multiseries'],realtimeangular:[['AngularGauge',-1],'multivaluegauge'],realtimebulb:[['Bulb',-1],'singlevaluegauge'],realtimecylinder:[['Cylinder',-1],'singlevaluegauge'],realtimehorizontalled:[['HLED',-1],'singlevaluegauge'],realtimehorizontallinear:[['HLinearGauge',-1],'multivaluegauge'],realtimethermometer:[['Thermometer',-1],'singlevaluegauge'],realtimeverticalled:[['VLED',-1],'singlevaluegauge'],sparkline:[['SparkLine',-1],'multiseries'],sparkcolumn:[['SparkColumn',-1],'multiseries'],sparkwinloss:[['SparkWinLoss',-1],'multiseries'],horizontalbullet:[['HBullet',-1],'singlevaluegauge'],verticalbullet:[['VBullet',-1],'singlevaluegauge'],pyramid:[['Pyramid',-1],'singleseries'],drawingpad:[['DrawingPad',-1],'drawingpad'],exportcomponent:[['FCExporter',-1],'undefined'],ssgrid:[['SSGrid',-1],'singleseries']}},countOf:{},count:0,policies:{vars:{chartWidth:['width','length',true,false],chartHeight:['height','length',true,false],lang:['lang','word',false,false],debugMode:['debugMode','bool',false,false],registerWithJS:['registerWithJS','bool',false,false],DOMId:['id','string',true,true]},attrs:{width:['width','length',true,false],height:['height','length',true,false],lang:['lang','word',false,false],'class':['className','string',true,true]},params:{scaleMode:['scaleMode','smode',false,true],wMode:['wMode','wmode',false,true],bgColor:['bgColor','color',false,true],allowScriptAccess:['allowScriptAccess','script',false,true],quality:['quality','quality',false,true]},options:{id:['id','string',true,true],type:['type','word',true,false],width:['width','length',true,false],height:['height','length',true,false],dataFormat:['dataFormat','dataformat',true,false],debugMode:['debugMode','bool',false,false],registerWithJS:['registerWithJS','bool',false,false],scaleMode:['scaleMode','smode',false,true],wMode:['wMode','wmode',false,true],allowScriptAccess:['allowScriptAccess','script',false,true],quality:['quality','quality',false,true],bgColor:['bgColor','color',false,true],lang:['lang','word',false,true],className:['className','string',true,true],product:['product','product',true,false],insertMode:['insertMode','insertmode',true,false]}},validators:{validate:function(param,type,test,required,caseSensitive){if(!__fcl.specified(param)){return test?false:''}if(typeof __fcv[type]==='undefined'){throw"Data type does not exist!"}if(!jQuery.isFunction(param.toString)){throw"Parameter cannot be parsed as string."}var $1=param.toString().match(__fcv[type]);var isMatch=$1&&$1.length&&$1.length>0;if(test==true){return isMatch}if(!isMatch){if(required){throw"Validation failed for: {"+type+"} "+param;}}return isMatch?$1[0][caseSensitive?'toString':'toLowerCase']():'';},update:function(v,o,u){var $1,p=[],s=$.extend({},o,u);for($1 in s){if(s[$1]!==null){p.push('^'+$1+'$');}}__fcv[v]=new RegExp(p.join('|'),'ig');},length:/^auto$|^[+-]?[0-9]+\.?([0-9]+)?%?/ig,color:/^[\S\s]+$/ig,bool:/^true$|^false$|^[01]$/ig,word:/^\S+$/ig,smode:/^exactFit$|^noBorder$|^noScale$|^showAll$/ig,wmode:/^opaque$|^transparent$|^window$/ig,script:/^always$|^never$/ig,quality:/^high$|^medium$|^low$/ig,string:/.+/ig,number:/^[-+]{0,1}[\d]*\.{0,1}\d+/ig,integer:/^\d+$/ig,percent:/^[-+]?([\d]+(,\d+)*)?(\.\d+)?%/ig,strictnumber:/\s*[-]{0,1}[0-9]*(\.[0-9]+)?\s*/ig,major:/^row$|^col$/ig,insertmode:/^append$|^prepend$|^clear$/ig,xmldata:null,product:null,dataformat:null},lib:{specified:function(p){return!(p===undefined||p===null);},serialize:function(o){var html='',key;for(key in o){if(typeof o[key]!=='string'){continue;}html+=key+'="'+o[key].toString().replace('"','&quot;')+'" ';}return html;},bool:function(o){return(typeof o==='boolean')?(o?'1':'0'):(o=='1'||o=='true'?'1':'0');},identify:function(obj){return jQuery(obj).attr('rel')==='FusionCharts';},updateXML:function(xml,attribute,value){if(arguments.length==2&&typeof attribute==='object'){for(var item in attribute){xml=__fcl.updateXML(xml,item,attribute[item]);}return xml;}var r=new RegExp(attribute+"=\\\"[^\"]+?\\\"|"+attribute+"=\\\'[^']+?\\\'",'gi'),v=attribute+'=\"'+value.replace(/\"/ig,'&quot;')+'\"';return r.test(xml)?xml.replace(r,v):xml.replace(/(\<\w+? )/,'$1'+v+' ');},sanitizeXML:function(xml){var arrDQAtt=xml.match(/=\s*\".*?\"/g);if(arrDQAtt){for(var i=0;i<arrDQAtt.length;i++){var repStr=arrDQAtt[i].replace(/^=\s*\"|\"$/g,"");repStr=repStr.replace(/\'/g,"%26apos;");var strTo=xml.indexOf(arrDQAtt[i]);var repStrr="='"+repStr+"'";var strStart=xml.substring(0,strTo);var strEnd=xml.substring(strTo+arrDQAtt[i].length);xml=strStart+repStrr+strEnd;}}xml=xml.replace(/\"/g,"%26quot;");xml=xml.replace(/%(?![\da-f]{2}|[\da-f]{4})/ig,"%25");xml=xml.replace(/\&/g,"%26");return xml;}},resize:function(context,width,height,bind){var o=$(context);if(width.toString().toLowerCase()=='auto'){o.width(o.parent().width());}if(height.toString().toLowerCase()=='auto'){o.height(o.parent().height());}if(bind){throw"NotImplementedException()";var $1;$(window).resize(function(){clearTimeout($1);$1=setTimeout(function(){__fc.update(o);},100);});}else{}},datahandlers:{extend:function(o){for(var $tmp in o){if(!jQuery.isFunction(o[$tmp]))continue;__fc.datahandlers[$tmp.toLowerCase()]=o[$tmp];}__fcv.update('dataformat',__fc.datahandlers,{extend:null});},uridata:function(o){return o.data;},xmldata:function(o){return __fcl.sanitizeXML(o.data);},ajax:function(o){},csvdata:function(o){}},filters:{validator:function(o,s){var ring,walk=function(ring,keys){for(var key in keys){ring[key]=__fcv.validate(o[keys[key][0]],keys[key][1],false,keys[key][2],keys[key][3]);}};s.options=o;for(ring in __fc.policies){walk(s[ring],__fc.policies[ring]);}s.vars.debugMode=__fcl.bool(s.vars.debugMode);s.vars.registerWithJS=__fcl.bool(s.vars.registerWithJS);s.attrs['rel']='FusionCharts';if(jQuery.browser.msie){s.attrs['classid']='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000';s.attrs['codeBase']='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version='+__fc.meta.products[o.product][3];}else{s.attrs['type']='application/x-shockwave-flash';s.attrs['pluginspage']='http://get.adobe.com/flashplayer/';}},id:function(o,s){if(!__fc.countOf[o.id]){__fc.countOf[o.id]=0;}},alias:function(o){if(__fcl.specified(o.swfName))return;if(!__fc.meta.alias[o.type]){throw"Chart Type Not Found!";}var swf=__fc.meta.alias[o.type][0][__fc.meta.products[o.product][0]];if(typeof swf==='number'){if(swf==-1){throw"Chart type not available in this version";}swf=__fc.meta.alias[o.type][0][swf];}o.swfName=__fc.meta.products[o.product][1]+swf+__fc.meta.products[o.product][2];},uri:function(o,s){var srcKey=jQuery.browser.msie?'movie':'src';if(__fcl.specified(o.swfURL)){s.params[srcKey]=o.swfURL;return;}if(!__fcl.specified(o.swfPath)||(o.swfPath=='')){s.params[srcKey]=o.swfURL=o.swfName;return;}o.swfPath=o.swfPath.replace(/\/$/,'');o.swfName=o.swfName.replace(/^\//,'');s.params[srcKey]=o.swfURL=o.swfPath+'/'+o.swfName;},data:function(o,s){if(o.data==undefined){return;}if(o.dataFormat==0){if(__fcv.validate(o.data,'xmldata',true)){dataFormat='xmldata';}else{return;}}s.vars[o.dataFormat=='uridata'?'dataURL':'dataXML']=__fc.datahandlers[o.dataFormat](o);}},synthesize:function(options,filters){var s={attrs:{},params:{},vars:{}};if(typeof options!=='object'){options={};}if(jQuery.isFunction(filters)){filters={'anonymous':filters};}var o=jQuery.extend(true,{},__fc.config,options);var exec=function($1,$2){return jQuery.isFunction($2)?$2(o,s):true;};jQuery.each(__fc.filters,exec);if(filters){jQuery.each(filters,exec);}return{stack:s,options:o};},build:function(s){var html=(jQuery.browser.msie?'<object ':'<embed ')+__fcl.serialize(s.attrs),vars=jQuery.param(s.vars);if(jQuery.browser.msie){html+='>';for(var key in s.params){html+='<param name="'+key+'" value="'+s.params[key]+'"></param>'}html+='<param name="flashVars" value="'+vars+'"></param></object>';}else{html+=__fcl.serialize(s.params)+'flashVars="'+vars+'"></embed>';}return html;},render:function(context,options,filters){var brew=__fc.synthesize(options,filters),html='';html=__fc.build(brew.stack);context=(context instanceof jQuery?context:jQuery(context));context.each(function(){var obj=jQuery(this),elm=jQuery(html),id=brew.options.id+(__fc.countOf[brew.options.id]==0?'':__fc.countOf[brew.options.id]);elm.attr('id',id).data('FusionCharts',brew.options);brew.stack.vars['DOMId']=id;if(jQuery.browser.msie){elm.children('param[name=flashVars]').attr('value',jQuery.param(brew.stack.vars));}else{elm.attr('flashVars',jQuery.param(brew.stack.vars));}if(brew.options.insertMode==='clear'){obj.empty();}obj.addClass(brew.options.className+'-container')[(brew.options.insertMode==='prepend')?'prepend':'append'](elm);__fc.resize(elm,brew.options.width,brew.options.height,brew.options.resizable);__fc.countOf[brew.options.id]++;});this.count+=context.length;return context.length;},update:function(context,options,filters){if(!(context instanceof jQuery)){context=jQuery(context);}if(!__fcl.identify(context)){context=context.find(':FusionCharts');}var key,$1;context.each(function(){var obj=this,elm=jQuery(this),data=elm.data('FusionCharts');var op=jQuery.extend({},data,options);if(op.swfURL==data.swfURL){delete op['swfURL'];}if(op.type!=data.type){if(__fcl.specified(op.swfName)){delete op['swfName'];}delete op['swfURL'];}var brew=__fc.synthesize(op,filters);__fc.resize(elm,brew.options.width,brew.options.height,brew.options.resizable);delete brew.stack.attrs['classid'];var flagReload=false,reloadKeys={'data':true,'update':true,'dataFormat':true,'dataOptions':true,'forceReload':true,'className':true};for(key in __fc.policies.params){reloadKeys[key]=true;}$1=0;for(key in options){if(reloadKeys[key]==undefined){flagReload=true;break;}}if(flagReload==false&&options['forceReload']==undefined){brew.options.forceReload=false;}if(brew.options.forceReload==false){$1=jQuery.browser.msie?'movie':'src';if((elm.attr($1)==brew.stack.params[$1])){delete brew.stack.params[$1];}}if(jQuery.browser.msie){elm.children('param[name=flashVars]').attr('value',jQuery.param(brew.stack.vars));elm.children('param').each(function(){var param=jQuery(this);if(brew.stack.params[param.attr('name')]){param.attr('value',brew.stack.params[param.attr('name')]);}});}else{elm.attr('flashVars',jQuery.param(brew.stack.vars));elm.attr(brew.stack.params);}elm.attr(brew.stack.attrs);if(brew.options.product.toLowerCase()=='free'){var legacySetXML=function(xml){obj.SetVariable("_root.dataURL","");obj.SetVariable("_root.isNewData","1");obj.SetVariable("_root.newData",xml);obj.TGotoLabel("/","JavaScriptHandler");if(brew.options.forceReload!=false){}};if(brew.options.dataFormat.toLowerCase()=='uridata'){jQuery.ajax({cache:false,global:false,url:brew.stack.vars['dataURL'],success:legacySetXML});}else{legacySetXML(brew.stack.vars['dataXML']);}}else if(brew.options.forceReload==false){if(brew.options.dataFormat.toLowerCase()=='uridata'&&jQuery.isFunction(this.setDataURL)){this.setDataURL(brew.stack.vars['dataURL']);}else if(jQuery.isFunction(this.setDataXML)){this.setDataXML(brew.stack.vars['dataXML']);}else{throw"FusionCharts object integrity error. "+"Missing critical ExternalInterfaces";}}elm.data('FusionCharts',brew.options);});return context;},attr:function(context,name,value){var transfer={},operand={},isJSON=false,update=(value!==undefined);if(!(context instanceof jQuery)){context=jQuery(context);}if(!__fcl.identify(context)){context.find(':FusionCharts');}if(name===undefined&&!update){context.each(function(){if(!(this.getXML&&this.setDataXML)){throw"FusionCharts object integrity error. "+"Missing critical ExternalInterfaces";}var id=this.id;transfer[id]={};this.getXML().replace(/<\w+? (.*?)>.*/,'$1').replace(/\s*?(\w+?)=\"([^"]*?)\"/g,function($1,$2,$3){transfer[id][$2]=$3;});});return transfer;}if(!(value instanceof Array)){value=[value];}if(typeof name==='object'){if(name instanceof Array){$(name).each(function($1,$2){operand[$2]=value[$1]});}else{operand=$.extend(operand,name);isJSON=true;}}else if(typeof name==='string'){operand[name]=value[0];}if(update||isJSON){context.each(function(){this.setDataXML(__fcl.updateXML(this.getXML(),operand));});return context;}else{context.each(function(){transfer[this.id]={};for(var item in operand){transfer[this.id][item]=this.getChartAttribute(item);}});return transfer;}},data:function(context,type,value){throw"NotImplementedException()";},init:function(){__fcv.update('product',__fc.meta.products);__fcv.update('dataformat',__fc.datahandlers,{extend:null});var p=[];for(var i in __fc.meta.products){p.push('(\\<'+__fc.meta.products[i][4]+'.*\\</'+__fc.meta.products[i][4]+'\\>)');}__fcv.xmldata=new RegExp(p.join('|'),'ig');}};var __fc=jQuery.FusionCharts,__fcl=jQuery.FusionCharts.lib,__fcv=jQuery.FusionCharts.validators;jQuery.FusionCharts.init();jQuery.fn.extend({FusionCharts:function(options,filter){if(options&&options['update']==true){this.update=__fc.update;this.update(this,options,filter);}else{this.render=__fc.render;this.render(this,options,filter);}return this;}});jQuery.fn.extend({insertFusionCharts:function(options,filter){this.render=__fc.render;this.render(this,$.extend(options,{update:false}),filter);return this;}});jQuery.fn.extend({appendFusionCharts:function(options,filter){this.render=__fc.render;this.render(this,$.extend(options,{update:false,insertMode:'append'}),filter);return this;}});jQuery.fn.extend({prependFusionCharts:function(options,filter){this.render=__fc.render;this.render(this,$.extend(options,{update:false,insertMode:'prepend'}),filter);return this;}});jQuery.fn.extend({updateFusionCharts:function(options,filter){this.update=__fc.update;this.update(this,$.extend(options,{update:true}),filter);return this;}});jQuery.fn.extend({convertToFusionCharts:function(options,filter){this.each(function(){var o=$(this),$tmp;options=$.extend({className:__fc.config.className},options,{data:this,dataFormat:'HTMLTable'});var context=$('<span class="'+options.className+'-container"></span>');o.before(context);__fc.render(context,options,filter);});return this}});jQuery.fn.extend({attrFusionCharts:function(name,value,options){this.attr=__fc.attr;return this.attr(this,name,value);}});jQuery.extend(jQuery.expr[':'],{FusionCharts:function(o){return __fcl.identify(o);},fusioncharts:function(o){return __fcl.identify(o);}});jQuery.extend({overlayFusionCharts:function(options,filter){throw"NotImplementedException()";}});__fc.datahandlers.extend({'HTMLTable':function(o){var tbl=jQuery(o.data).filter('table:first'),$tmp;if(tbl.length===0){throw"No <table> data source found";}var opts={chartAttributes:{},major:'row',useLabels:true,useLegend:true,labelSource:1,legendSource:1,ignoreCols:[],ignoreRows:[],seriesColors:[],labels:jQuery([]),legend:jQuery([]),data:jQuery([])},variant=__fc.meta.alias[o.type][1],ignore={};if(o.product==='free'){opts.seriesColors=['AFD8F8','F6BD0F','8BBA00','FF8E46','008E8E','D64646','8E468E','588526','B3AA00','008ED6','9D080D','A186BE','CC6600','FDC689','ABA000','F26D7D','FFF200','0054A6','F7941C','CC3300','006600','663300','6DCFF6'];}$.extend(opts,o.dataOptions);opts.labelSource=opts.useLabels?parseInt(opts.labelSource):0;opts.legendSource=opts.useLegend?parseInt(opts.legendSource):0;if(variant=='singleseries'){if(opts.major=='row'){opts.useLegend=false;opts.legendSource=0;}else{opts.useLabels=false;opts.labelSource=0;}}if(opts.ignoreRows instanceof Array){ignore.rows=opts.ignoreRows.length?tbl.find('tr:nth-child('+opts.ignoreRows.join('),tr:nth-child(')+')').find('td,th'):jQuery([]);}if(opts.ignoreCols instanceof Array){ignore.cols=opts.ignoreCols.length?tbl.find('tr').find('td:nth-child('+opts.ignoreCols.join('),td:nth-child(')+'),th:nth-child('+opts.ignoreCols.join('),th:nth-child(')+')'):jQuery([]);}opts.data=tbl.find('th,td').not(ignore.cols).not(ignore.rows);if(opts.useLabels){opts.labels=tbl.find('tr:nth-child('+opts.labelSource+'),'+'tr:nth-child('+opts.labelSource+')').find('th,td');opts.data=opts.data.not(opts.labels);if(opts.legendSource-1>=0){opts.labels=opts.labels.not(':eq('+(opts.legendSource-1)+')');}opts.labels=opts.labels.not(ignore.cols).not(ignore.rows);}if(opts.useLegend){opts.legend=tbl.find('tr').find('td:nth-child('+opts.legendSource+'),'+'th:nth-child('+opts.legendSource+'),');opts.data=opts.data.not(opts.legend);if(opts.labelSource-1>=0){opts.legend=opts.legend.not(':eq('+(opts.labelSource-1)+')');}opts.legend=opts.legend.not(ignore.rows).not(ignore.cols);}$tmp=opts.data.parent(':first').children();var w=$tmp.length-$tmp.not(opts.data).length,h=w>0?opts.data.length/w:0,l=opts.data.length;if(opts.major==='col'){$tmp=opts.labels;opts.labels=opts.legend;opts.legend=$tmp;$tmp=w;w=h;h=$tmp;}var tag=__fc.meta.products[o.product][4],xml='<'+tag+' '+__fcl.serialize(o.dataOptions.chartAttributes)+'>',nodeHTML;if(variant==='singleseries'){nodeHTML='<set '+(o.product==='free'?'name':'label')+'="';opts.data.each(function($1){xml+=nodeHTML+opts.labels.eq($1%w).text()+'" value="'+__fcv.validate(jQuery(this).text().replace(',',''),'strictnumber')+'" />';});}else if(variant==='multiseries'){xml+='<categories>';nodeHTML='<category '+((o.product==='free')?'name':'label')+'="';for($tmp=0;$tmp<w;$tmp++){xml+=nodeHTML+opts.labels.eq($tmp).text()+'" />';}xml+='</categories>';var map=[],i,j;if(opts.major==='row'){for(i=0;i<l;i++){map.push(i);}}else{for(i=0;i<h;i++){for(j=0;j<l;j+=h){map.push(i+j);}}}var bof;$tmp=0;for(i=0;i<l;i++){if((bof=i%w)===0){xml+='<dataset color="'+(opts.seriesColors[$tmp%opts.seriesColors.length]||'')+'" seriesName="'+opts.legend.eq($tmp++).text()+'">';}xml+='<set value="'+__fcv.validate(opts.data.eq(map[i]).text().replace(',',''),'strictnumber')+'"/>';if(bof+1===w){xml+='</dataset>';}}}else{throw"NotImplementedException()"}return __fcl.sanitizeXML(xml+'</'+tag+'>')}});