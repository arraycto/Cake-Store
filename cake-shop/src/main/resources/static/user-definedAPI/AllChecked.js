var willCheckAll = false;

function allChoice(arrays) {
    if (willCheckAll) {
        $("input[type='checkbox']").each(function () {
            this.checked = false;
        });
        willCheckAll = false;
    } else {
        $("input[type='checkbox']").each(function () {
            this.checked = true;
        });
        willCheckAll = true;
    }

    //压入值
    $('input[type="checkbox"]').each(function () {
        arrays.push(this.value);
    });

    return arrays;
}