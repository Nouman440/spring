$(function (){
    var $order = $('#orders')
    $.ajax({

        type: 'GET',
        url: '/listJobs',
        success: function (orders) {
            $.forEach(orders,function (i,order) {
                $orders.append('<li>model: '+order.name )


    });



}
});

});
