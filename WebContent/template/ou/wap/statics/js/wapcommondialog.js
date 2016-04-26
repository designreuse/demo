/**
 * 需要jquery 1.6
 */
$(function() {
    /*默认按钮点击事件*/
    $(".commonBtn").live("click", function() {
        $(".myDialog").hide();
    })
})

/**
 * 自定义alert，替代原生的alert。依赖 wapcommondialog.css
 * 该提示做为窗口的提示
 * @param message  消息内容
 * @param type 弹框类型 0 叹号(默认) 1 打勾
 */
function alert(message,type){
    $(".myDialog").parent().remove();
    var str = '<div class="myDialog">'+
        '<div class="overlay">'+
        '</div>'+
        '<div class="remind">';
    if(!isNotNull(type) || type == '0') {
        str += '<span class="dialog-icon-warn"></span>';
    } else {
        str += '<span class="dialog-icon-info"></span>';
    }
    str += '<p id="myWarmMessage"></p>'+
    '<a href="javascript:;" class="btn01 commonBtn">确定</a>'+
    '</div>'+'</div>';
    var div = document.createElement("div"); div.innerHTML = str; document.body.appendChild(div);
    $(".myDialog").show();
    $("#myWarmMessage").text(message);
}

/**
 * 自定义弹出框 依赖 wapcommondialog.css
 * @param message 提示信息
 * @param confirmBtnStr 按钮显示名称
 * @param result 按钮点击事件
 * @param type 弹出框提示图标 0 叹号(默认) 1 打勾
 *
 */
function lcokBox(message, confirmBtnStr, result, type) {
    $(".myDialog").parent().remove();
    var str = '<div class="myDialog">'+
        '<div class="overlay">'+
        '</div>'+
        '<div class="remind">';
    if(!isNotNull(type) || type == '0') {
        str += '<span class="dialog-icon-warn"></span>';
    } else {
        str += '<span class="dialog-icon-info"></span>';
    }
    str += '<p id="myWarmMessage"></p>'+
    '<a href="javascript:;" class="btn01" id="confirmButton">'+confirmBtnStr+'</a>'+
    '</div>'+'</div>';
    var div = document.createElement("div"); div.innerHTML = str; document.body.appendChild(div);
    $(".myDialog").show();
    $("#myWarmMessage").html(message);
    $("#confirmButton").live('click', function() {
        $(".myDialog").hide();
        result.onSuccess();
    })
}

/**
 * 自定义弹出窗口 目的取代confirm  该方法依赖 wapcommondialog.css
 * @param message 窗口的提示标题
 * @param result 按钮点击事件
 * @param type 弹出框提示图标 0 叹号(默认) 1 打勾
 */
var confirm =  function  (message,result,type){
    $(".myDialog").parent().remove();
    var str = '<div class="myDialog">'+
        '<div class="overlay">'+
        '</div>'+
        '<div class="remind">';
    if(!isNotNull(type) || type == '0') {
        str += '<span class="dialog-icon-warn"></span>';
    } else {
        str += '<span class="dialog-icon-info"></span>';
    }
    str += '<p id="myWarmMessage"></p>'+
    '<div class="m-btn">'+
    '<a href="javascript:;" class="btn03" id="confirmButton">确定</a>'+
    '<a href="javascript:;" class="btn02 commonBtn">取消</a>'+
    '</div>'+'</div>'+'</div>';
    var div = document.createElement("div"); div.innerHTML = str; document.body.appendChild(div);
    $(".myDialog").show();
    $("#myWarmMessage").html(message);
    $('#confirmButton').live('click',function(){
        $(".myDialog").hide();
        result.onSuccess();
    });
}

/**
 * 点击确定和取消执行不同的结果 该方法依赖 wapcommondialog.css
 * @param message 窗口提示信息
 * @param confirmBtnStr 确认按钮 名称
 * @param cancelBtnStr 取消按钮 名称
 * @param result 确认按钮点击事件
 * @param cancel 取消按钮点击事件
 * @param type 弹出框提示图标 0 叹号(默认) 1 打勾
 * */
var confirmOrCancel =  function  (message, confirmBtnStr, cancelBtnStr, result, cancel, type){
    $(".myDialog").parent().remove();
    var str = '<div class="myDialog">' +
        '<div class="overlay">'+
        '</div>'+
        '<div class="remind">';
    if(!isNotNull(type) || type == '0') {
        str += '<span class="dialog-icon-warn"></span>';
    } else {
        str += '<span class="dialog-icon-info"></span>';
    }
    str += '<p id="myWarmMessage"></p>'+
    '<div class="m-btn">'+
    '<a href="javascript:;" class="btn03" id="confirmButton">'+confirmBtnStr+'</a>'+
    '<a href="javascript:;" class="btn02" id="cancelBtn">'+cancelBtnStr+'</a>'+
    '</div>'+'</div>'+'</div>';
    var div = document.createElement("div"); div.innerHTML = str; document.body.appendChild(div);
    $(".myDialog").show();
    $('#myWarmMessage').html(message);
    $('#confirmButton').live('click',function(){
        $(".myDialog").hide();
        result.onSuccess();
    });
    $('#cancelBtn').live('click',function(){
        $(".myDialog").hide();
        cancel.onCancel();
    });
}


/**
 * 自定义alert，替代原生的alert。依赖bootstrap.css
 * 该提示做为窗口的提示
 * @param message  消息内容
 */
function showLotteryMsg(message,showDivNode,hideDivNode){
    var str = '<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >'+
        '<div class="modal-dialog" style="margin-top: 100px;">'+
        '<div class="modal-content">'+
        '<div class="modal-header">'+
        '<button type="button" class="close"  onclick="toReload()" id="lotteryCloseBtn" data-dismiss="modal" aria-hidden="true">&times;</button>'+
        '<span class="modal-title" id="myModalLabel"></span>'+
        '</div>'+
        '<div class="modal-body" style="padding:0px; text-align: center;">'+
        '<h4 class="modal-title" style="line-height: 6rem;" id="lotteryTip"></h4>'+
        '</div>'+
        '<div class="modal-footer" style="border -top:0px;margin-top: 0px;">'+
        '<div class="row" id="toLogin" style="display: none;">'+
        '<div class="col-xs-4 col-xs-push-4">'+
        '<button type="button" class="btn btn-primary btn-block" onclick="toLogin()" id="toLoginButton" style="margin-right: 20px;">立即登录</button>'+
        '</div>'+'</div>'+
        '<div class="row" id="toLook" style="display: none;">'+
        '<div class="col-xs-4 col-xs-push-4">'+
        '<button type="button" class="btn btn-primary btn-block" onclick="toIndex()" style="margin-right: 20px;">去逛逛</button>'+
        '</div>'+'</div>'+
        '<div class="row" id="toIndex">'+
        '<div class="col-xs-4 col-xs-push-1">'+
        '<button type="button" class="btn btn-primary btn-block" onclick="toIndex()" id="toIndexButton" style="margin-right: 20px;">去逛逛</button>'+
        '</div>'+
        '<div class="col-xs-4 col-xs-push-1">'+
        '<button type="button" class="btn btn-default btn-block" onclick="toReload()" id="keepDoLottery" data-dismiss="modal">继续抽奖</button>'+
        '</div>'+'</div>'+'</div>'+'</div>'+'</div>'+'</div>';
    var div = document.createElement("div"); div.innerHTML = str; document.body.appendChild(div);
    $('#myModal').modal('show');
    $('#myModalLabel').text("提示信息");
    $('#lotteryTip').text(message);
    if(isNotNull(showDivNode)) {
        $(showDivNode).show();
    }
    if(isNotNull(hideDivNode)) {
        $(hideDivNode).hide();
    }
}

function isNotNull(value) {
    return value!=undefined&&value!=""&&value!=null;
}
