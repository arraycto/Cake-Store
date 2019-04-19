/**
 * 封装之多选之函数
 * 针对用户操作类别
 * @param arr
 * @returns {*}
 */
function multiChoice(arr) {
    var uids = $('input[class="customer_item"]:checked');
    console.log(uids.length);
    if (uids.length < 1) {
        alert('请至少先选中1名用户');
        return;
    }

    uids.each(function () {
        arr.push(this.value);
    });

    console.log(arr);
    return arr;
}

/**
 * 针对多选蛋糕之情形
 * from operation.html
 * @param array
 * @returns {*}
 */
function multipleAngelCakeChoices(array) {
    var aids = $('input[class="checked-items"]:checked');
    if (aids.length < 1) {
        alert('您还未有所选择');
        return;
    }

    aids.each(function () {
        array.push(this.value);
    });

    console.log(array);
    return array;
}