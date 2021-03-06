$(document).ready(function(){
    var $table = $('#productList');
    var $button_add = $('#btn_add');
    var $button_edit = $('#btn_edit');
    $button_add.click(function(){
        $("#showProductModal").removeData("modal");
        $('#showProductModal').modal('show');
        $(".addSysUserForm").show();
        $(".updateSysUserForm").hide();

        $(".modal-title").html("新增用户角色");
        $("#id").val("");
        $("#alterSysRoleNm").val("");
        $("#alterPosition").val("");
    });



    $button_edit.click(function () {
        if(!$table.bootstrapTable('getSelections')[0]){
            alert('请选择一行数据！');
            return;
        }
        var id =  $table.bootstrapTable('getSelections')[0].id;
        $.ajax({
            type:"POST",
            url:webPath.webRoot+"/product/findOne.json",
            data:{id:id},
            dataType: "json",
            async: false,//同步
            success:function(data) {
                $('#showProductModal').modal('show');
                $(".addSysRoleForm").hide();
                $(".updateSysRoleForm").show();

                $(".modal-title").html("修改商品");
                $("#id").val(data.id);

                $("#alterProductNm").val(data.productNm);
                $("#alterSellingPoint").val(data.sellingPoint);
                $("#alterProductCoding").val(data.productCoding);
                $("#alterProductTag").val(data.productTag);
                $("#alterMarketPrice").val(data.marketPrice);

                $("input[name='isOnSale']").each(function(){
                    if($(this).val()==data.isOnSale){
                        $(this).attr("checked",true);
                    }
                })


                $("#alterPosition").val(data.position);
            },
            error:function(XMLHttpRequest, textStatus) {
                alert(result.jsonError.errorText);
            }
        });
    });
    var btn_delete = $('#btn_delete');

    btn_delete.click(function () {
        if(!$table.bootstrapTable('getSelections')[0]){
            alert('请选择一行数据！');
            return;
        }
        var id =  $table.bootstrapTable('getSelections')[0].id;
        $.ajax({
            type:"POST",
            url:webPath.webRoot+"/product/delete.json",
            data:{id:id},
            dataType: "json",
            success:function(data) {
                if (data.success == true) {
                    $("#productList").bootstrapTable('refresh', {url: webPath.webRoot+'/product/findProductList.json'});
                }
                else{

                }
            },
            error:function(XMLHttpRequest, textStatus) {
                alert(result.jsonError.errorText);
            }
        });

    });
});
var imageFile = function(value,rows){
    return "<img src="+webPath.webRoot+"/upload/temp/"+value+"/>";

};

var isOnSale = function(value,rows){
    switch(value) {
        case 'Y':
            return '是';
        case 'N':
            return '否';
    }
};