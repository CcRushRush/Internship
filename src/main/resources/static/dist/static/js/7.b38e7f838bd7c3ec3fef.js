webpackJsonp([7],{B6Sz:function(e,t){},pOcf:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=i("fZjL"),s=i.n(a),r=i("mvHQ"),n=i.n(r),o={name:"Update",data:function(){return{obj:{},value:[],keys:[],dialogVisible:!1,addData:{key:"",value:""},eMessage:[],messageArr:[],shuxing:["设备外网IP","设备内网IP","设备开放端口","设备域名","设备域名负责人id","负责人名字"],eId:""}},methods:{update:function(e){var t=this;this.$prompt("修改设备信息","提示",{confirmButtonText:"确定",cancelButtonText:"取消"}).then(function(i){var a=i.value;t.$forceUpdate(),t.value[e]=a,t.obj[t.keys[e]]=a;var s=JSON.parse(localStorage.getItem("data"));s[t.keys[e]]=a,localStorage.setItem("data",n()(s)),t.$axios.put("http://192.168.1.155:8888/equipmentController",t.obj).then(function(e){e.data?t.$message({message:"恭喜你，修改成功",type:"success"}):t.$message.error("修改失败")})})},delate:function(e){var t=this;delete this.obj[this.keys[e]];var i=JSON.parse(localStorage.getItem("data"));delete i[this.keys[e]],this.shuxing.splice(e,1),this.keys.splice(e,1),this.value.splice(e,1),localStorage.setItem("data",n()(i)),this.$axios.put("http://192.168.1.155:8888/equipmentController",this.obj).then(function(e){e.data?t.$message({message:"恭喜你，删除成功",type:"success"}):t.$message.error("删除失败")})},addMessageshow:function(){this.dialogVisible=!0},addMessage:function(){var e=this;if(this.addData.key||this.$message({message:"属性名不能为空",type:"warning"}),this.addData.value||this.$message({message:"属性值不能为空",type:"warning"}),this.addData.key&&this.addData.value){var t={key:this.addData.key,value:this.addData.value};this.messageArr.push(t),this.obj.eMessage=n()(this.messageArr),this.$axios.put("http://192.168.1.155:8888/equipmentController",this.obj).then(function(t){t.data?(e.$message({message:"恭喜你，添加成功",type:"success"}),e.dialogVisible=!1):e.$message.error("添加失败")})}},serverService:function(){var e=this;this.$router.push({name:"ServerService"}).then(function(){localStorage.setItem("eId",e.eId)})},updateMessage:function(e){var t=this;this.$prompt("修改设备信息","提示",{confirmButtonText:"确定",cancelButtonText:"取消"}).then(function(i){var a=i.value;t.messageArr[e].value=a,t.obj.eMessage=n()(t.messageArr),t.$axios.put("http://192.168.1.155:8888/equipmentController",t.obj).then(function(e){e.data?t.$message({message:"恭喜你，修改成功",type:"success"}):t.$message.error("修改失败")})})},delateMessage:function(e){var t=this;this.messageArr.splice(e,1),this.obj.eMessage=n()(this.messageArr),this.$axios.put("http://192.168.1.155:8888/equipmentController",this.obj).then(function(e){e.data?t.$message({message:"恭喜你，删除成功",type:"success"}):t.$message.error("删除失败")})}},mounted:function(){var e=JSON.parse(localStorage.getItem("data"));this.eId=e.eId,this.obj=e,this.messageArr=JSON.parse(this.obj.eMessage)||[];for(var t=s()(e),i=0;i<t.length;i++)"eId"!==t[i]&&"eName"!==t[i]&&"eMessage"!==t[i]&&this.keys.push(t[i]);for(var a in e)"eId"!==a&&"eName"!==a&&"eMessage"!==a&&this.value.push(e[a])}},l={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"update"},[i("div",{staticStyle:{width:"1202px",margin:"20px auto 0","text-align":"center",height:"60px","line-height":"60px","background-color":"#545c64","border-radius":"10px 10px 0 0",color:"white"}},[e._v("设备信息\n    ")]),e._v(" "),i("div",{staticStyle:{width:"1200px",margin:"auto",display:"flex",border:"1px solid"}},[i("div",{staticStyle:{width:"33.3%","border-right":"1px solid"}},[i("ul",[i("li",{staticStyle:{height:"50px","text-align":"center","line-height":"50px","font-size":"20px","background-color":"#909399"}},[e._v("属性名")]),e._v(" "),e._l(e.shuxing,function(t,a){return i("li",{key:a,staticStyle:{"border-bottom":"1px solid #545c64"}},[i("div",{staticStyle:{height:"50px","text-align":"center","line-height":"50px","font-size":"20px"}},[e._v(e._s(t))])])})],2)]),e._v(" "),i("div",{staticStyle:{width:"33.3%","border-right":"1px solid"}},[i("ul",[i("li",{staticStyle:{height:"50px","text-align":"center","line-height":"50px","font-size":"20px","background-color":"#909399"}},[e._v("属性值")]),e._v(" "),e._l(e.value,function(t,a){return i("li",{key:a,staticStyle:{"border-bottom":"1px solid #545c64"}},[i("div",{staticStyle:{height:"50px","text-align":"center","line-height":"50px","font-size":"20px"}},[e._v(e._s(t))])])})],2)]),e._v(" "),i("div",{staticStyle:{width:"33.4%"}},[i("ul",[i("li",{staticStyle:{height:"50px","text-align":"center","line-height":"50px","font-size":"20px","background-color":"#909399"}},[e._v("操作")]),e._v(" "),e._l(e.value,function(t,a){return i("li",{key:a,staticStyle:{"border-bottom":"1px solid #545c64","text-align":"center","line-height":"50px"}},[i("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.update(a)}}},[e._v("修改")]),e._v(" "),i("el-button",{attrs:{type:"danger"},on:{click:function(t){return e.delate(a)}}},[e._v("删除")])],1)})],2)])]),e._v(" "),i("div",{staticClass:"emassege",staticStyle:{width:"1202px","text-align":"center",margin:"auto","background-color":"#545c64",height:"50px","line-height":"50px",color:"white"}},[e._v("\n        自定义配置信息\n    ")]),e._v(" "),i("div",{staticStyle:{width:"1200px",margin:"auto",display:"flex",border:"1px solid"}},[i("div",{staticStyle:{width:"33.3%","border-right":"1px solid"}},[i("ul",[i("li",{staticStyle:{height:"50px","text-align":"center","line-height":"50px","font-size":"20px","background-color":"#909399"}},[e._v("属性名")]),e._v(" "),e._l(e.messageArr,function(t,a){return i("li",{key:a,staticStyle:{"border-bottom":"1px solid #545c64",height:"50px","line-height":"50px","text-align":"center"}},[e._v(e._s(t.key))])})],2)]),e._v(" "),i("div",{staticStyle:{width:"33.3%","border-right":"1px solid"}},[i("ul",[i("li",{staticStyle:{height:"50px","text-align":"center","line-height":"50px","font-size":"20px","background-color":"#909399"}},[e._v("属性值")]),e._v(" "),e._l(e.messageArr,function(t,a){return i("li",{key:a,staticStyle:{"border-bottom":"1px solid #545c64",height:"50px","line-height":"50px","text-align":"center"}},[e._v(e._s(t.value))])})],2)]),e._v(" "),i("div",{staticStyle:{width:"33.4%"}},[i("ul",[i("li",{staticStyle:{height:"50px","text-align":"center","line-height":"50px","font-size":"20px","background-color":"#909399"}},[e._v("操作")]),e._v(" "),e._l(e.messageArr,function(t,a){return i("li",{key:a,staticStyle:{"border-bottom":"1px solid #545c64","text-align":"center","line-height":"50px"}},[i("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.updateMessage(a)}}},[e._v("修改")]),e._v(" "),i("el-button",{attrs:{type:"danger"},on:{click:function(t){return e.delateMessage(a)}}},[e._v("删除")])],1)})],2)])]),e._v(" "),i("div",{staticStyle:{width:"1202px","text-align":"center",margin:"auto"}},[i("el-button",{staticStyle:{width:"100%"},attrs:{type:"primary"},on:{click:e.addMessageshow}},[e._v("增加配置信息")])],1),e._v(" "),i("div",{staticStyle:{width:"1202px",margin:"20px auto 0","font-size":"20px",color:"#409EFF",cursor:"pointer"},on:{click:e.serverService}},[e._v("服务管理>>>")]),e._v(" "),i("el-dialog",{attrs:{title:"增加配置信息",visible:e.dialogVisible,width:"30%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[i("span",[e._v("属性名")]),e._v(" "),i("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.addData.key,callback:function(t){e.$set(e.addData,"key",t)},expression:"addData.key"}}),e._v(" "),i("span",[e._v("属性值")]),e._v(" "),i("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.addData.value,callback:function(t){e.$set(e.addData,"value",t)},expression:"addData.value"}}),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),e._v(" "),i("el-button",{attrs:{type:"primary"},on:{click:e.addMessage}},[e._v("确 定")])],1)],1)],1)},staticRenderFns:[]};var c=i("VU/8")(o,l,!1,function(e){i("B6Sz")},"data-v-2e8c269f",null);t.default=c.exports}});
//# sourceMappingURL=7.b38e7f838bd7c3ec3fef.js.map