webpackJsonp([3],{911:function(e,t,a){a(928);var l=a(3)(a(930),a(931),null,null);e.exports=l.exports},915:function(e,t,a){"use strict";var l=a(916),n=a.n(l);t.a={components:{reportForm:n.a},data:function(){return{data:[],total:0,loading:!1,tableLoading:!1}}}},916:function(e,t,a){a(917);var l=a(3)(a(919),a(923),"data-v-43b2ee82",null);e.exports=l.exports},917:function(e,t,a){var l=a(918);"string"==typeof l&&(l=[[e.i,l,""]]),l.locals&&(e.exports=l.locals);a(909)("0423660c",l,!0)},918:function(e,t,a){t=e.exports=a(908)(),t.push([e.i,".deposit[data-v-43b2ee82]{display:flex;flex-flow:column;height:100%;position:relative}.deposit-table[data-v-43b2ee82]{flex:1}.deposit .cus-scroll[data-v-43b2ee82]{width:100%;overflow-x:scroll;height:20px;background:rgba(0,0,0,.4);position:absolute;top:38px}.deposit .cus-scroll-child[data-v-43b2ee82]{height:10px;background:#fff}.deposit .cus-scroll[data-v-43b2ee82]::-webkit-scrollbar,.deposit .cus-scroll[data-v-43b2ee82]::-webkit-scrollbar-track{height:20px}","",{version:3,sources:["D:/gdnybank/hnm-web/src/views/pages/achievement/components/index.vue"],names:[],mappings:"AACA,0BACE,aAAc,AACd,iBAAkB,AAClB,YAAa,AACb,iBAAmB,CACpB,AACD,gCACI,MAAQ,CACX,AACD,sCACI,WAAkB,AAClB,kBAAmB,AACnB,YAAa,AACb,0BAA+B,AAC/B,kBAAmB,AACnB,QAAU,CACb,AACD,4CACM,YAAa,AACb,eAAkB,CACvB,AAID,wHACI,WAAa,CAChB",file:"index.vue",sourcesContent:["\n.deposit[data-v-43b2ee82] {\n  display: flex;\n  flex-flow: column;\n  height: 100%;\n  position: relative;\n}\n.deposit-table[data-v-43b2ee82] {\n    flex: 1;\n}\n.deposit .cus-scroll[data-v-43b2ee82] {\n    width: calc(100%);\n    overflow-x: scroll;\n    height: 20px;\n    background: rgba(0, 0, 0, 0.4);\n    position: absolute;\n    top: 38px;\n}\n.deposit .cus-scroll-child[data-v-43b2ee82] {\n      height: 10px;\n      background: white;\n}\n.deposit .cus-scroll[data-v-43b2ee82]::-webkit-scrollbar {\n    height: 20px;\n}\n.deposit .cus-scroll[data-v-43b2ee82]::-webkit-scrollbar-track {\n    height: 20px;\n}\n"],sourceRoot:""}])},919:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l=a(6),n=a.n(l),r=a(33),i=a.n(r),s=a(920),o=a(921),p=a(922),u=a(22),c=a.n(u),d=[{key:"1",label:"1",title:"分行"},{key:"2",label:"2",title:"支行/团队"},{key:"3",label:"3",title:"客户经理"},{key:"4",label:"4",title:"服务点"}];t.default={components:{BranchTreePicker:c.a},props:{data:{type:Array,default:function(){return[]}},total:0,loading:{type:Boolean,default:!1},tableLoading:{type:Boolean,default:!1}},data:function(){return{form:{query_org:"1",query_type:"1",query_level:"1",query_date:"/expand"===this.$route.path?new Date:new Date((new Date).getTime()-864e5)},dateRange:[new Date((new Date).getTime()-864e5),new Date((new Date).getTime()-864e5)],items2:[{key:"1",label:"1",title:"客户纬度"},{key:"2",label:"2",title:"账户纬度"}],items3:[{key:"1",label:"1",title:"所属机构"},{key:"2",label:"2",title:"落地支行"}],columns:s.a,expandCol:o.a,helpFarmerCol:p.a,showColumns:[],showData:[],bankid:"",bankname:"",pageNum:1,pageSize:10,siteList:[],sysUserList:[],cusScrollWidth:0}},created:function(){},computed:{isHelpFarmers:function(){return"/helpfarmers"===this.$route.path},isExpand:function(){return"/expand"===this.$route.path},items:function(){return"2"==this.form.query_org?d.filter(function(e){return 3!=parseInt(e.key)}):JSON.parse(i()(d))}},mounted:function(){this.getAccountList(),this.getSiteSelectList();var e=document.querySelector(".cus-scroll"),t=document.querySelector(".el-table__body-wrapper");e.addEventListener("scroll",function(){console.log(e.scrollLeft),t.scrollLeft=e.scrollLeft})},methods:{handleOverWidth:function(){var e=document.querySelector(".el-table__header");this.cusScrollWidth=e.style.width},nodeselectForSerch:function(e){this.bankname=e.branch_name,this.bankid=e.branch_id},cleanQureyData:function(){"3"!=this.form.query_level&&this.$set(this.form,"mgr_id",null),"4"!=this.form.query_level&&this.$set(this.form,"site_no",null)},formateTime:function(e){var t=new Date(e),a=t.getFullYear(),l=t.getMonth()+1,n=t.getDate();return a+""+(l<10?"0"+l:l)+(n<10?"0"+n:n)},getData:function(){this.getSiteSelectList(),this.showData=[];var e=JSON.parse(i()(this.form));this.isHelpFarmers&&(delete e.query_date,this.dateRange?(e.start_date=this.formateTime(this.dateRange[0]),e.end_date=this.formateTime(this.dateRange[1])):(e.start_date="",e.end_date="")),void 0!==e.query_date&&(this.isHelpFarmers||(this.isExpand?e.query_date=this.formateTime(e.query_date||new Date):e.query_date=this.formateTime(e.query_date||new Date((new Date).getTime()-864e5)))),this.$emit("get-data",n()({page:this.pageNum-1,number:this.pageSize,bankid:this.bankid},e))},getAccountList:function(){var e=this;0==this.sysUserList.length&&this.$api.getAccountList({}).then(function(t){e.sysUserList=t.data})},getSiteSelectList:function(){var e=this;"1"==this.form.query_org?this.$api.getSiteSelectList().then(function(t){e.siteList=t.data.siteList}):this.$api.getSiteSelectList({zh_flag:"1"}).then(function(t){e.siteList=t.data.siteList})},exportDataInfos:function(){var e=JSON.parse(i()(this.form));this.isHelpFarmers&&(delete e.query_date,this.dateRange?(e.start_date=this.formateTime(this.dateRange[0]),e.end_date=this.formateTime(this.dateRange[1])):(e.start_date="",e.end_date="")),void 0!==e.query_date&&(this.isHelpFarmers||(this.isExpand?e.query_date=this.formateTime(e.query_date||new Date):e.query_date=this.formateTime(e.query_date||new Date((new Date).getTime()-864e5)))),this.$emit("export-data",n()({bankid:this.bankid},e))},handleColumns:function(){"1"==this.form.query_level?this.showColumns=[{label:"分行号",prop:"fh_branch_id"},{label:"分行",prop:"fh_branch_name"}].concat(this.showColumns):"2"==this.form.query_level?this.showColumns=[{label:"支行号/团队号",prop:"branch_id"},{label:"支行/团队",prop:"branch_name"}].concat(this.showColumns).concat([{label:"所属分行",prop:"fh_branch_name"}]):"3"==this.form.query_level?this.showColumns=[{label:"客户经理号",prop:"mgr_id"},{label:"客户经理",prop:"mgr_name"}].concat(this.showColumns).concat([{label:"所属分行",prop:"fh_branch_name"}]):"4"==this.form.query_level&&(this.showColumns=[{label:"服务点号",prop:"site_no"},{label:"服务点",prop:"site_name"}].concat(this.showColumns).concat([{label:"所属分行",prop:"fh_branch_name"},{label:"所属支行/团队",prop:"branch_name"}]))},changeSize:function(e){this.pageSize=e,this.getData()},changePage:function(e){this.pageNum=e,this.getData()}},watch:{data:{handler:function(){var e=this;this.showData=JSON.parse(i()(this.data)),this.$nextTick(function(){e.handleOverWidth()})},deep:!0},"form.query_level":{handler:function(){"/expand"===this.$route.path?this.showColumns=JSON.parse(i()(this.expandCol)):"/helpfarmers"===this.$route.path?this.showColumns=JSON.parse(i()(this.helpFarmerCol)):this.showColumns=JSON.parse(i()(this.columns)),this.handleColumns(),this.pageNum=1,this.pageSize=10,this.getData()},immediate:!0}}}},920:function(e,t,a){"use strict";t.a=[{label:"余额（元）",children:[{label:"时点",prop:"amt1"},{label:"比上日",prop:"amt1_than_last_day"},{label:"比上月",prop:"amt1_than_last_month"},{label:"比上年",prop:"amt1_than_last_year"}]},{label:"月日均（元）",children:[{label:"时点",prop:"amt3"},{label:"比上日",prop:"amt3_than_last_day"},{label:"比上月",prop:"amt3_than_last_month"},{label:"比上年",prop:"amt3_than_last_year"}]},{label:"年日均（元）",children:[{label:"时点",prop:"amt2"},{label:"比上日",prop:"amt2_than_last_day"},{label:"比上月",prop:"amt2_than_last_month"},{label:"比上年",prop:"amt2_than_last_year"}]}]},921:function(e,t,a){"use strict";t.a=[{label:"已开业服务点数",children:[{label:"时点",prop:"site_num3"},{label:"比上日",prop:"sitenum3_than_last_day"},{label:"比上月",prop:"sitenum3_than_last_month"},{label:"比上年",prop:"sitenum3_than_last_year"}]},{label:"试营业服务点数",children:[{label:"时点",prop:"site_num1"},{label:"比上日",prop:"sitenum1_than_last_day"},{label:"比上月",prop:"sitenum1_than_last_month"},{label:"比上年",prop:"sitenum1_than_last_year"}]},{label:"试营业（已申请pos）服务点数",children:[{label:"时点",prop:"site_num2"},{label:"比上日",prop:"sitenum2_than_last_day"},{label:"比上月",prop:"sitenum2_than_last_month"},{label:"比上年",prop:"sitenum2_than_last_year"}]},{label:"已退出服务点数",children:[{label:"时点",prop:"site_num4"},{label:"比上日",prop:"sitenum4_than_last_day"},{label:"比上月",prop:"sitenum4_than_last_month"},{label:"比上年",prop:"sitenum4_than_last_year"}]}]},922:function(e,t,a){"use strict";t.a=[{label:"小额取款",children:[{label:"笔数",prop:"count1"},{label:"金额（元）",prop:"amt1"}]},{label:"转账汇款",children:[{label:"笔数",prop:"count2"},{label:"金额（元）",prop:"amt2"}]},{label:"现金汇款",children:[{label:"笔数",prop:"count3"},{label:"金额（元）",prop:"amt3"}]},{label:"余额查询",children:[{label:"笔数",prop:"count4"},{label:"金额（元）",prop:"amt4"}]},{label:"汇总",children:[{label:"笔数",prop:"count5"},{label:"金额（元）",prop:"amt5"}]}]},923:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"deposit"},[e.isHelpFarmers||e.isExpand?e._e():a("el-popover",{attrs:{placement:"top-start",trigger:"hover"}},[a("div",{staticStyle:{color:"red"}},[a("h5",[e._v("客户调整到惠农服务点:")]),e._v(" "),a("h5",[e._v("1、当月的业绩数据会同步变化。")]),e._v(" "),a("h5",[e._v("2、上月(含)以前的业绩数据不变。")])]),e._v(" "),a("span",{staticStyle:{color:"red"},attrs:{slot:"reference"},slot:"reference"},[e._v("说明")])]),e._v(" "),a("el-button",{staticStyle:{width:"200px"},attrs:{type:"warning",size:"small",loading:e.loading},on:{click:e.exportDataInfos}},[e._v("导出全部查询数据")]),e._v(" "),a("el-form",{attrs:{"label-width":"120"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"选择条件:"}},[a("el-radio-group",{on:{change:e.getData},model:{value:e.form.query_org,callback:function(t){e.$set(e.form,"query_org",t)},expression:"form.query_org"}},e._l(e.items3,function(t){return a("el-radio",{key:t.key,attrs:{label:t.label}},[e._v("\n              "+e._s(t.title)+"\n            ")])}),1)],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},["/deposit"==e.$route.path||"/AUM"==e.$route.path?a("el-form-item",{attrs:{label:"选择纬度:"}},[a("el-radio-group",{on:{change:e.getData},model:{value:e.form.query_type,callback:function(t){e.$set(e.form,"query_type",t)},expression:"form.query_type"}},e._l(e.items2,function(t){return a("el-radio",{key:t.key,attrs:{label:t.label}},[e._v("\n              "+e._s(t.title)+"\n            ")])}),1)],1):e._e()],1)],1),e._v(" "),a("el-form-item",{attrs:{label:"选择报表类型:"}},[a("el-radio-group",{on:{change:e.cleanQureyData},model:{value:e.form.query_level,callback:function(t){e.$set(e.form,"query_level",t)},expression:"form.query_level"}},e._l("/expand"===e.$route.path?e.items.filter(function(e){return"4"!=e.key}):e.items,function(t){return a("el-radio",{key:t.key,attrs:{label:t.label}},[e._v("\n          "+e._s(t.title)+"\n        ")])}),1)],1),e._v(" "),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"数据日期:"}},[e.isHelpFarmers?a("el-date-picker",{attrs:{size:"small",type:"daterange","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.dateRange,callback:function(t){e.dateRange=t},expression:"dateRange"}}):a("el-date-picker",{attrs:{clearable:!1,size:"small",type:"date"},model:{value:e.form.query_date,callback:function(t){e.$set(e.form,"query_date",t)},expression:"form.query_date"}})],1)],1),e._v(" "),a("el-col",{attrs:{span:6}},[a("el-form-item",{attrs:{label:"查询机构:",prop:"bankid"}},[a("div",{staticStyle:{display:"flex"}},[a("el-input",{attrs:{readonly:"",size:"small"},model:{value:e.bankname,callback:function(t){e.bankname=t},expression:"bankname"}}),e._v(" "),a("BranchTreePicker",{on:{nodeselect:e.nodeselectForSerch}})],1)])],1)],1),e._v(" "),a("el-row",{staticStyle:{position:"relative"}},[a("el-col",{attrs:{span:9}},["3"==e.form.query_level&&"1"==e.form.query_org?a("el-form-item",{attrs:{label:"查询客户经理:",prop:"mgr_id"}},[a("el-select",{attrs:{filterable:"",clearable:"",size:"small"},model:{value:e.form.mgr_id,callback:function(t){e.$set(e.form,"mgr_id",t)},expression:"form.mgr_id"}},e._l(e.sysUserList,function(e){return a("el-option",{key:e.account_id,attrs:{label:e.name,value:e.account_id}})}),1)],1):e._e()],1),e._v(" "),a("el-col",{attrs:{span:9}},["4"==e.form.query_level?a("el-form-item",{attrs:{label:"查询服务点:",prop:"site_no"}},[a("el-select",{attrs:{filterable:"",clearable:"",size:"small"},model:{value:e.form.site_no,callback:function(t){e.$set(e.form,"site_no",t)},expression:"form.site_no"}},e._l(e.siteList,function(e){return a("el-option",{key:e.site_no,attrs:{label:e.site_name,value:e.site_no}})}),1)],1):e._e()],1),e._v(" "),a("el-col",{attrs:{span:3}},[a("el-form-item",{attrs:{"label-width":"0"}},[a("el-button",{attrs:{type:"primary",size:"small",icon:"el-icon-search"},on:{click:e.getData}},[e._v("查询")])],1)],1),e._v(" "),a("div",{staticClass:"cus-scroll"},[a("div",{staticClass:"cus-scroll-child",style:{width:e.cusScrollWidth}})])],1)],1),e._v(" "),a("div",{ref:"table",staticClass:"deposit-table"},[a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.tableLoading,expression:"tableLoading"}],attrs:{border:"",data:e.showData}},[e._l(e.showColumns,function(t,l){return[t.children?a("el-table-column",{key:l,attrs:{label:t.label,align:"center",width:"320"}},e._l(t.children,function(e,t){return a("el-table-column",{key:t,attrs:{label:e.label,prop:e.prop,align:"center"}})}),1):a("el-table-column",{key:l,attrs:{label:t.label,prop:t.prop,align:"center",width:"80"}})]})],2)],1),e._v(" "),a("el-pagination",{staticStyle:{"text-align":"center",margin:"10px 0"},attrs:{"current-page":e.pageNum,"page-sizes":[10,20,20,40],"page-size":e.pageSize,size:"small",layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.changeSize,"current-change":e.changePage}})],1)},staticRenderFns:[]}},928:function(e,t,a){var l=a(929);"string"==typeof l&&(l=[[e.i,l,""]]),l.locals&&(e.exports=l.locals);a(909)("56487c5f",l,!0)},929:function(e,t,a){t=e.exports=a(908)(),t.push([e.i,"","",{version:3,sources:[],names:[],mappings:"",file:"aum.vue",sourceRoot:""}])},930:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l=a(915);t.default={mixins:[l.a],created:function(){},methods:{getData:function(e){var t=this,a=e.query_type;"1"===a?this.$api.getAumList(e).then(function(e){t.data=e.data.returnList,t.total=e.data.total}):"2"===a&&this.$api.getAumList2(e).then(function(e){t.data=e.data.returnList,t.total=e.data.total})},exportDataInfos:function(e){var t=this,a=e.query_type;"1"===a?this.$api.exportAumDataInfos(e).then(function(e){var a=e.data.fileName,l=e.data.filePath;if(null==a||null==l)return t.$message({message:"导出失败，系统异常",type:"error"}),!1;var n=document.createElement("form");document.body.appendChild(n),n.method="post",n.action="/hnm/txCtrl?txcode=mediaFile",n.enctype="multipart/form-data";var r=document.createElement("input"),i=document.createElement("input"),s=document.createElement("input"),o=document.createElement("input");r.setAttribute("name","filePath"),r.setAttribute("type","hidden"),r.setAttribute("value",l),i.setAttribute("name","fileName"),i.setAttribute("type","hidden"),i.setAttribute("value",a),s.setAttribute("name","isDownload"),s.setAttribute("type","hidden"),s.setAttribute("value","1"),o.setAttribute("name","isDelete"),o.setAttribute("type","hidden"),o.setAttribute("value","1"),n.appendChild(r),n.appendChild(i),n.appendChild(s),n.appendChild(o),n.submit(),t.$message({message:"导出成功",type:"success"})}):"2"===a&&this.$api.exportAumDataInfos2(e).then(function(e){var a=e.data.fileName,l=e.data.filePath;if(null==a||null==l)return t.$message({message:"导出失败，系统异常",type:"error"}),!1;var n=document.createElement("form");document.body.appendChild(n),n.method="post",n.action="/hnm/txCtrl?txcode=mediaFile",n.enctype="multipart/form-data";var r=document.createElement("input"),i=document.createElement("input"),s=document.createElement("input"),o=document.createElement("input");r.setAttribute("name","filePath"),r.setAttribute("type","hidden"),r.setAttribute("value",l),i.setAttribute("name","fileName"),i.setAttribute("type","hidden"),i.setAttribute("value",a),s.setAttribute("name","isDownload"),s.setAttribute("type","hidden"),s.setAttribute("value","1"),o.setAttribute("name","isDelete"),o.setAttribute("type","hidden"),o.setAttribute("value","1"),n.appendChild(r),n.appendChild(i),n.appendChild(s),n.appendChild(o),n.submit(),t.$message({message:"导出成功",type:"success"})})}}}},931:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement;return(e._self._c||t)("report-form",{ref:"reportForms",attrs:{tableLoading:e.tableLoading,loading:e.loading,data:e.data,total:e.total},on:{"get-data":e.getData,"export-data":e.exportDataInfos}})},staticRenderFns:[]}}});
//# sourceMappingURL=3.de156e85ed3dcb20f8c6.js.map