document.write("<script src='javascriptapi/jquery-1.9.1.min.js' type='text/javascript'></script>");

/**
 * 管理退出登录
 */
function consulLogOut() {
    var url = '/consuls/login_archon_out';

    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success: function (rr) {
            if (rr.state == 200) {
                alert('退出登录成功');
                location.reload();
            } else {
                console.log('退出登录失败')
            }
        }
    });
}

/**
 * 会员退出登录
 */
function customerLogOut() {
    var url = '/customers/login_out_customer';

    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success: function (rr) {
            if (rr.state == 200) {
                alert('退出登录成功');
                location.reload();
            } else {
                console.log('退出登录失败')
            }
        }
    });
}