// 单选确定
$('#concatUserTree').click(function() {
    var $inputDOM = $('#selUser input[name="selUser"]:checked');

    // 获取选中的人员
    if ($inputDOM.length === 0) {
        alert('请最少选择一个审核人员！');

        return;
    };

    // 写入值
    currInput.val($inputDOM.eq(0).parent().data('userid'));

    $('#selUser').hide();
});

// 多选确定
$('#concatUsersTree').click(function() {
    var $inputDOM = $('#selUsers input[name="selUsers"]:checked');

    // 获取选中的人员
    if ($inputDOM.length === 0) {
        alert('请最少选择一个审核人员！');

        return;
    };

    // 写入值
    var writeVal = '';
    $inputDOM.each(function(key) {

        if (key === 0) {
            writeVal += $(this).parent().data('userid');
        } else {
            writeVal += ';' + $(this).parent().data('userid');
        };
    });
    currInput.val(writeVal);

    $('#selUsers').hide();
});